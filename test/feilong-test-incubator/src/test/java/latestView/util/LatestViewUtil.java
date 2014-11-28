/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package latestView.util;

import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import latestView.shop.web.command.BrowsingHistoryCommand;
import latestView.shop.web.command.SimpleSkuCommand;
import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.TimeInterval;
import com.feilong.commons.core.io.IOConstants;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.CookieEntity;
import com.feilong.servlet.http.CookieUtil;
import com.feilong.servlet.http.RequestUtil;

/**
 * 最近浏览记录.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-22 下午4:43:59
 */
public class LatestViewUtil{

	/** The Constant log. */
	private static final Logger		log								= LoggerFactory.getLogger(LatestViewUtil.class);

	/** 最近浏览记录 保存3个月 <code>{@value}</code>. */
	public static final Integer		interval						= 3 * TimeInterval.SECONDS_PER_MONTH;

	/** The Constant LATEST_VIEW_SIZE. */
	private static final Integer	LATEST_VIEW_SIZE				= 5;

	/** The Constant COOKIE_NAME_LATEST_VIEW_SKUS. */
	private static final String		COOKIE_NAME_LATEST_VIEW_SKUS	= "LATEST_VIEW_SKUS";

	/**
	 * 合并.
	 *
	 * @param browsingHistoryCommandSet
	 *            the browsing history command set
	 * @param simpleSkuCommand
	 *            the simple sku command
	 * @return the tree set< browsing history command>
	 * @see BrowsingHistoryCommand#compareTo(BrowsingHistoryCommand)
	 */
	public static TreeSet<BrowsingHistoryCommand> mergerBrowsingHistoryCommandSet(
					TreeSet<BrowsingHistoryCommand> browsingHistoryCommandSet,
					SimpleSkuCommand simpleSkuCommand){
		// 最近访问的记录 是当前页(当前页刷新),什么都不做
		// browsingHistoryCommandSet 的 compareTo 采用倒序排序
		String code = simpleSkuCommand.getCode();
		if (Validator.isNotNullOrEmpty(browsingHistoryCommandSet)
						&& browsingHistoryCommandSet.first().getSimpleSkuCommand().getCode().equals(code)){
			// nothing to do
			log.debug("page refresh or vist the same,code:{}", code);
		}else{
			BrowsingHistoryCommand browsingHistoryCommand = new BrowsingHistoryCommand();
			browsingHistoryCommand.setDate(new Date().getTime());
			browsingHistoryCommand.setSimpleSkuCommand(simpleSkuCommand);
			// note:see com.jumbo.controller.command.BrowsingHistoryCommand.compareTo(BrowsingHistoryCommand)
			if (browsingHistoryCommandSet.contains(browsingHistoryCommand)){
				browsingHistoryCommandSet.remove(browsingHistoryCommand);// 移除
			}else{
				//
				int difference = browsingHistoryCommandSet.size() - LATEST_VIEW_SIZE;
				if (difference >= 0){
					Object[] logParams = { difference, difference + 1 };
					log.debug("difference:{},will be delete:{}", logParams);
					// 此处 的故事 是 以前 收藏是8个 现在改成了4个 需要将以前客户的 收藏处理掉
					for (int i = 0; i <= difference; ++i){
						// BrowsingHistoryCommand last = browsingHistoryCommandSet.last();// 移除第一个
						// log.debug("the param last,{}:{}", last.getDate(), last.getSimpleSkuCommand().getCode());
						// browsingHistoryCommandSet.remove(last);
						// jDK1.6 可以使用
						browsingHistoryCommandSet.pollLast();
					}
				}
			}
			browsingHistoryCommandSet.add(browsingHistoryCommand);
		}
		return browsingHistoryCommandSet;
	}

	/**
	 * 将 browsingHistoryCommandSet 转成Cookie.
	 *
	 * @param browsingHistoryCommandSet
	 *            the browsing history command set
	 * @param response
	 *            the response
	 */
	public static void toCookie(TreeSet<BrowsingHistoryCommand> browsingHistoryCommandSet,HttpServletResponse response){
		// ******************写cookie********************
		JSONArray jsonArray = JSONArray.fromObject(browsingHistoryCommandSet);
		String json = jsonArray.toString();
		// 因为cookie 的name 和value 不能出现特殊字符 (空白字符 以及下列字符 ： @ : ;? , " / [ ] ( ) =),所以加密
		String hexJsonValue = StringUtil.toHexStringUpperCase(json);
		int length = hexJsonValue.length();
		Object[] objects = { json, json.length(), hexJsonValue, length };
		// 大于3K
		if (length >= 3 * IOConstants.KB){
			log.warn("json:{},length:{},hexJsonValue:{},length:{}", objects);
		}else{
			log.debug("json:{},length:{},hexJsonValue:{},length:{}", objects);
		}

		CookieEntity cookieEntity = new CookieEntity(COOKIE_NAME_LATEST_VIEW_SKUS, hexJsonValue, interval);
		CookieUtil.addCookie(cookieEntity, response);
	}

	/**
	 * 读取 浏览记录 转成TreeSet<BrowsingHistoryCommand>.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the browsing history command tree set from cookie
	 */
	public static TreeSet<BrowsingHistoryCommand> getBrowsingHistoryCommandTreeSetFromCookie(
					HttpServletRequest request,
					HttpServletResponse response){
		String hexJsonValue = CookieUtil.getCookieValue(request, COOKIE_NAME_LATEST_VIEW_SKUS);
		return toBrowsingHistoryCommandTreeSet(hexJsonValue, request, response);
	}

	/**
	 * 16进制代码 转成 TreeSet<BrowsingHistoryCommand> browsingHistoryCommandSet.
	 *
	 * @param hexJsonValue
	 *            the hex json value
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the tree set< browsing history command>
	 */
	public static TreeSet<BrowsingHistoryCommand> toBrowsingHistoryCommandTreeSet(
					String hexJsonValue,
					HttpServletRequest request,
					HttpServletResponse response){
		// 换成 TreeSet
		TreeSet<BrowsingHistoryCommand> browsingHistoryCommandSet = null;
		try{
			browsingHistoryCommandSet = toBrowsingHistoryCommandTreeSet(hexJsonValue);
		}catch (Exception e){
			Object jsonValue = StringUtil.toOriginal(hexJsonValue);
			Object[] objects = { hexJsonValue, jsonValue, RequestUtil.getClientIp(request), RequestUtil.getHeaderUserAgent(request) };
			log.error("hexJsonValue:{},jsonValue:{},ip:{},UserAgent:{},will delete cookie!", objects);
			log.error(e.getClass().getName(), e);
			// 删除 cookie
			CookieUtil.deleteCookie(COOKIE_NAME_LATEST_VIEW_SKUS, response);
		}
		if (Validator.isNullOrEmpty(browsingHistoryCommandSet)){
			browsingHistoryCommandSet = new TreeSet<BrowsingHistoryCommand>();
		}
		return browsingHistoryCommandSet;
	}

	/**
	 * 16进制代码 转成 TreeSet<BrowsingHistoryCommand> browsingHistoryCommandSet.
	 *
	 * @param hexJsonValue
	 *            the hex json value
	 * @return the tree set< browsing history command>
	 * @throws Exception
	 *             the exception
	 */
	public static TreeSet<BrowsingHistoryCommand> toBrowsingHistoryCommandTreeSet(String hexJsonValue) throws Exception{
		if (Validator.isNullOrEmpty(hexJsonValue)){
			// nothing to do
			return null;
		}
		String jsonValue = StringUtil.toOriginal(hexJsonValue);
		int length = hexJsonValue.length();
		if (length >= 3 * IOConstants.KB){
			Object[] objects = { jsonValue, hexJsonValue, length };
			log.warn("json:{},hexJsonValue:{},length:{}", objects);
		}else{
			log.debug("hexJsonValue:{},length:{}", hexJsonValue, length);
		}
		JSONArray jsonArray = JSONArray.fromObject(jsonValue);
		@SuppressWarnings("unchecked")
		Collection<BrowsingHistoryCommand> collection = JSONArray.toCollection(jsonArray, BrowsingHistoryCommand.class);
		return new TreeSet<BrowsingHistoryCommand>(collection);
	}
}
