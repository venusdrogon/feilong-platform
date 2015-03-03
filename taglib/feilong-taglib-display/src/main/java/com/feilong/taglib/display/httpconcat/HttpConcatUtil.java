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
package com.feilong.taglib.display.httpconcat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.configure.ResourceBundleUtil;
import com.feilong.commons.core.entity.JoinStringEntity;
import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.commons.core.text.MessageFormatUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.CollectionsUtil;
import com.feilong.commons.core.util.ListUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.display.httpconcat.command.HttpConcatParam;
import com.feilong.taglib.display.httpconcat.directive.Concat;
import com.feilong.tools.security.oneway.MD5Util;

/**
 * http concat的核心工具类.
 * <p>
 * 类加载的时候,会使用 {@link ResourceBundleUtil} 来读取{@link HttpConcatConstants#CONFIG_FILE} 配置文件中的 {@link HttpConcatConstants#KEY_TEMPLATE_CSS}
 * css模板 以及 {@link HttpConcatConstants#KEY_TEMPLATE_JS} JS模板<br>
 * 请确保文件路径中有配置文件,以及正确的key<br>
 * 如果获取不到,会 throw {@link IllegalArgumentException}
 * 
 * <h3>全局合并开关</h3>
 * 
 * 
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月19日 下午2:50:43
 * @see HttpConcatTag
 * @see Concat
 * @see HttpConcatConstants
 * @see HttpConcatParam
 * @since 1.0.7
 */
//XXX 丰富 JavaDOC
public final class HttpConcatUtil implements HttpConcatConstants{

	/** The Constant log. */
	private static final Logger							log						= LoggerFactory.getLogger(HttpConcatUtil.class);

	/** The Constant TEMPLATE_CSS. */
	private static final String							TEMPLATE_CSS;

	/** The Constant TEMPLATE_JS. */
	private static final String							TEMPLATE_JS;

	/** 是否支持 HTTP_CONCAT (全局参数). */
	private static final Boolean						GLOBAL_HTTP_CONCAT_SUPPORT;

	//*****************************************************************************

	/**
	 * 设置缓存是否开启.
	 * 
	 * @since 1.0.7
	 */
	private static final boolean						DEFAULT_CACHEENABLE		= true;

	/**
	 * cache size 限制,仅当 {@link #DEFAULT_CACHEENABLE}开启生效, 当cache数达到 {@link #DEFAULT_CACHESIZELIMIT},将不会再缓存结果
	 * 经过测试
	 * <ul>
	 * <li>300000 size cache占用 内存 :87.43KB(非精准)</li>
	 * <li>304850 size cache占用 内存 :87.43KB(非精准)</li>
	 * <li>400000 size cache占用 内存 :8.36MB(非精准)</li>
	 * </ul>
	 * 
	 * 对于一个正式项目而言,http concat的cache, size极限大小会是 <blockquote><i>页面总数(P)*页面concat标签数(C)*i18N数(I)*版本号(V)</i></blockquote><br>
	 * 如果一个项目 页面有1000个,每个页面有5个concat块,一共有5种国际化语言,如果应用重启前支持5次版本更新,那么计算公式会是 <blockquote><i>1000*5*5*5=50000</i></blockquote>
	 * <b>注意:此公式中的页面总数是指,VM/JSP的数量,除非参数不同导致VM/JSP渲染的JS也不同,另当别论</b>
	 * 
	 * @since 1.0.7
	 */
	private static final int							DEFAULT_CACHESIZELIMIT	= 300000;

	/**
	 * 将结果缓存到map.<br>
	 * key是入参 {@link HttpConcatParam}对象,value是解析完的字符串<br>
	 * 该cache里面value不会存放null/empty
	 * 
	 * @since 1.0.7
	 */
	//TODO change to ConcurrentHashMap
	//这里对 线程安全的要求不高, 仅仅是 插入和读取的操作,即时出了线程安全问题, 重新解析js/css 标签代码 并加载 即可
	private static final Map<HttpConcatParam, String>	cache					= new HashMap<HttpConcatParam, String>();

	// XXX 支持多变量
	static{
		GLOBAL_HTTP_CONCAT_SUPPORT = ResourceBundleUtil.getValue(CONFIG_FILE, KEY_HTTPCONCAT_SUPPORT, Boolean.class);

		if (Validator.isNullOrEmpty(GLOBAL_HTTP_CONCAT_SUPPORT)){
			log.warn(
							"can not find key:[{}],pls ensure you have put the correct configuration file path:[{}]",
							KEY_HTTPCONCAT_SUPPORT,
							CONFIG_FILE);
		}
	}

	// 加载模板
	static{
		TEMPLATE_CSS = ResourceBundleUtil.getValue(CONFIG_FILE, KEY_TEMPLATE_CSS);
		TEMPLATE_JS = ResourceBundleUtil.getValue(CONFIG_FILE, KEY_TEMPLATE_JS);
		if (Validator.isNullOrEmpty(TEMPLATE_CSS)){
			String messagePattern = "can not find key:[{}],pls ensure you have put the correct configuration file path:[{}]";
			throw new IllegalArgumentException(Slf4jUtil.formatMessage(messagePattern, KEY_HTTPCONCAT_SUPPORT, CONFIG_FILE));

		}
		if (Validator.isNullOrEmpty(TEMPLATE_JS)){
			String messagePattern = "can not find key:[{}],pls ensure you have put the correct configuration file path:[{}]";
			throw new IllegalArgumentException(Slf4jUtil.formatMessage(messagePattern, KEY_HTTPCONCAT_SUPPORT, CONFIG_FILE));
		}
	}

	// *****************************************************************************
	/**
	 * 获得解析的内容.
	 * 
	 * @param httpConcatParam
	 *            the http concat param
	 * @return <ul>
	 *         <li>如果 isNullOrEmpty httpConcatParam.getItemSrcList() ,return null</li>
	 *         <li>如果支持 concat,那么生成concat字符串</li>
	 *         <li>如果不支持 concat,那么生成多行js/css 原生的字符串</li>
	 *         </ul>
	 * @throws NullPointerException
	 *             if isNullOrEmpty(httpConcatParam)
	 * @throws UnsupportedOperationException
	 *             if httpConcatParam.getType() 不是 js或者css
	 */
	public static String getWriteContent(HttpConcatParam httpConcatParam) throws NullPointerException,UnsupportedOperationException{
		if (Validator.isNullOrEmpty(httpConcatParam)){
			throw new NullPointerException("the httpConcatParam is null or empty!");
		}

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(httpConcatParam));
		}

		// 判断item list
		List<String> itemSrcList = httpConcatParam.getItemSrcList();
		if (Validator.isNullOrEmpty(itemSrcList)){
			log.warn("the param itemSrcList isNullOrEmpty,need itemSrcList to create links,return null");
			return null;
		}

		//是否使用cache
		boolean isWriteCache = DEFAULT_CACHEENABLE;

		int cacheKeyHashCode = httpConcatParam.hashCode();
		//*************************************************************************************
		//缓存
		if (DEFAULT_CACHEENABLE){
			//返回此映射中的键-值映射关系数。如果该映射包含的元素大于 Integer.MAX_VALUE，则返回 Integer.MAX_VALUE。 
			int cacheSize = cache.size();

			String content = cache.get(httpConcatParam);
			//包含
			if (null != content){
				if (log.isInfoEnabled()){
					log.info("hashcode:[{}],get httpConcat info from httpConcatCache,cache.size:[{}]", cacheKeyHashCode, cacheSize);
				}
				return content;
			}else{
				//超出cache 数量
				boolean outOfCacheItemSizeLimit = (cacheSize >= DEFAULT_CACHESIZELIMIT);
				if (outOfCacheItemSizeLimit){
					log.warn(
									"hashcode:[{}],cache.size:[{}] >= DEFAULT_CACHESIZELIMIT:[{}],this time will not put result to cache",
									cacheKeyHashCode,
									cacheSize,
									DEFAULT_CACHESIZELIMIT);

					//超过,那么就不记录cache
					isWriteCache = false;
				}else{

					if (log.isInfoEnabled()){
						log.info(
										"hashcode:[{}],httpConcatCache.size:[{}] not contains httpConcatParam,will do parse",
										cacheKeyHashCode,
										cacheSize);
					}
				}
			}
		}

		// **********是否开启了连接********************************************************
		Boolean httpConcatSupport = httpConcatParam.getHttpConcatSupport();
		//如果没有设置就使用默认的全局设置
		if (null == httpConcatSupport){
			httpConcatSupport = (null == GLOBAL_HTTP_CONCAT_SUPPORT) ? false : GLOBAL_HTTP_CONCAT_SUPPORT;
		}

		// *******************************************************************
		// 标准化 httpConcatParam,比如list去重,标准化domain等等
		// 下面的解析均基于standardHttpConcatParam来操作
		// httpConcatParam只做入参判断,数据转换,以及cache存取
		HttpConcatParam standardHttpConcatParam = standardHttpConcatParam(httpConcatParam);

		// *********************************************************************************

		String type = standardHttpConcatParam.getType();
		String template = getTemplate(type);

		// *********************************************************************************
		String content = "";
		if (httpConcatSupport){
			// concat
			String concatLink = getConcatLink(standardHttpConcatParam);
			content = MessageFormatUtil.format(template, concatLink);
		}else{ // 本地开发环境支持的.
			itemSrcList = standardHttpConcatParam.getItemSrcList();
			StringBuilder sb = new StringBuilder();
			for (String itemSrc : itemSrcList){
				String noConcatLink = getNoConcatLink(itemSrc, standardHttpConcatParam);
				sb.append(MessageFormatUtil.format(template, noConcatLink));
			}
			content = sb.toString();
		}

		// **************************log***************************************************
		if (log.isDebugEnabled()){
			log.debug("returnValue:[{}],length:[{}]", content, content.length());
		}
		//********************设置cache***********************************************
		if (isWriteCache){
			cache.put(httpConcatParam, (null == content) ? "" : content);
			if (log.isInfoEnabled()){
				log.info("key's hashcode:[{}] put to cache,cache size:[{}]", httpConcatParam.hashCode(), cache.size());
			}
		}else{
			if (DEFAULT_CACHEENABLE){
				log.warn(
								"hashcode:[{}],DEFAULT_CACHEENABLE:[{}],but isWriteCache:[{}],so http concat result not put to cache",
								cacheKeyHashCode,
								DEFAULT_CACHEENABLE,
								isWriteCache);
			}
		}
		//************************************************************************
		return content;
	}

	/**
	 * 标准化 httpConcatParam,比如list去重,标准化domain等等.
	 * 
	 * @param httpConcatParam
	 *            the http concat param
	 * @return the http concat param
	 */
	private static HttpConcatParam standardHttpConcatParam(HttpConcatParam httpConcatParam){

		//******************domain*******************************************
		String domain = httpConcatParam.getDomain();
		// 格式化 domain 成 http://www.feilong.com/ 形式
		if (Validator.isNotNullOrEmpty(domain)){
			if (!domain.endsWith("/")){
				domain = domain + "/";
			}
		}else{
			domain = "";
		}

		//********************root*****************************************
		String root = httpConcatParam.getRoot();
		// 格式化 root 成 xxxx/xxx/ 形式,
		if (Validator.isNotNullOrEmpty(root)){
			if (!root.endsWith("/")){
				root = root + "/";
			}
			if (root.startsWith("/")){
				root = StringUtil.substring(root, 1);
			}
		}else{
			root = "";
		}

		// ********************itemSrcList*******************************************************
		// 判断item list
		List<String> itemSrcList = httpConcatParam.getItemSrcList();

		// 去重,元素不重复
		List<String> noRepeatitemList = ListUtil.removeDuplicate(itemSrcList);

		//**************************************************************
		if (Validator.isNullOrEmpty(noRepeatitemList)){
			log.warn("the param noRepeatitemList isNullOrEmpty,need noRepeatitemList to create links");
			return null;
		}
		int noRepeatitemListSize = noRepeatitemList.size();
		int itemSrcListSize = itemSrcList.size();

		if (noRepeatitemListSize != itemSrcListSize){
			log.warn(
							"noRepeatitemList.size():[{}] != itemSrcList.size():[{}],httpConcatParam:{}",
							noRepeatitemListSize,
							itemSrcListSize,
							JsonUtil.format(httpConcatParam));
		}

		// *******************************************************************
		HttpConcatParam standardHttpConcatParam = new HttpConcatParam();
		standardHttpConcatParam.setItemSrcList(noRepeatitemList);
		standardHttpConcatParam.setDomain(domain);
		standardHttpConcatParam.setRoot(root);
		standardHttpConcatParam.setHttpConcatSupport(httpConcatParam.getHttpConcatSupport());
		standardHttpConcatParam.setType(httpConcatParam.getType());
		standardHttpConcatParam.setVersion(httpConcatParam.getVersion());

		// *******************************************************************
		if (log.isDebugEnabled()){
			log.debug("standardHttpConcatParam:{}", JsonUtil.format(standardHttpConcatParam));
		}
		return standardHttpConcatParam;

	}

	// *****************************************************************************
	/**
	 * 生成version号<br>
	 * 目前采用md5加密.
	 * 
	 * @param origin
	 *            the origin
	 * @return the string
	 */
	public static String createVersion(String origin){
		String version = MD5Util.encode(origin);
		if (log.isInfoEnabled()){
			log.info("origin:[{}],version:[{}]", origin, version);
		}
		return version;
	}

	// *****************************************************************************
	/**
	 * 获得合并的链接.
	 * 
	 * @param httpConcatParam
	 *            the http concat param
	 * @return the link
	 */
	private static String getConcatLink(HttpConcatParam httpConcatParam){
		List<String> itemSrcList = httpConcatParam.getItemSrcList();
		String domain = httpConcatParam.getDomain();
		String root = httpConcatParam.getRoot();
		String version = httpConcatParam.getVersion();

		// **********************************************************************************

		StringBuilder sb = new StringBuilder();
		sb.append(domain);
		sb.append(root);

		int size = itemSrcList.size();
		// 只有一条 输出原生字符串
		if (size == 1){
			sb.append(itemSrcList.get(0));
			if (log.isDebugEnabled()){
				log.debug("itemSrcList size==1,will generate primary {}.", httpConcatParam.getType());
			}
		}else{
			sb.append("??");

			JoinStringEntity joinStringEntity = new JoinStringEntity(JoinStringEntity.DEFAULT_CONNECTOR);
			sb.append(CollectionsUtil.toString(itemSrcList, joinStringEntity));
		}
		appendVersion(version, sb);

		return sb.toString();
	}

	/**
	 * 获得不需要 Concat 的连接.
	 * 
	 * @param itemSrc
	 *            the src
	 * @param httpConcatParam
	 *            the http concat param
	 * @return the string
	 */
	private static String getNoConcatLink(String itemSrc,HttpConcatParam httpConcatParam){
		String domain = httpConcatParam.getDomain();
		String root = httpConcatParam.getRoot();
		String version = httpConcatParam.getVersion();
		StringBuilder sb = new StringBuilder();
		sb.append(domain);
		sb.append(root);
		sb.append(itemSrc);
		appendVersion(version, sb);
		return sb.toString();
	}

	// /**
	// * 加工结果
	// *
	// * @param sb
	// * @return
	// */
	// private static String handleResult(StringBuilder sb){
	// String string = sb.toString();
	// if (log.isDebugEnabled()){
	// log.debug("the param sb:{}", sb);
	// }
	// // return string.replace("//", "/");
	// return string;
	// }

	/**
	 * Append version.
	 * 
	 * @param version
	 *            the version
	 * @param sb
	 *            the sb
	 */
	private static void appendVersion(String version,StringBuilder sb){
		if (Validator.isNotNullOrEmpty(version)){
			sb.append("?");
			sb.append(version);
		}else{
			if (log.isDebugEnabled()){
				log.debug("the param version isNullOrEmpty,we suggest you to set version value");
			}
		}
	}

	// *****************************************************************************

	/**
	 * 不同的type不同的模板.
	 * 
	 * @param type
	 *            类型 {@link HttpConcatConstants#TYPE_CSS} 以及{@link HttpConcatConstants#TYPE_JS}
	 * @return 目前仅支持 {@link HttpConcatConstants#TYPE_CSS} 以及{@link HttpConcatConstants#TYPE_JS},其余不支持,会抛出
	 *         {@link UnsupportedOperationException}
	 * @throws UnsupportedOperationException
	 *             type not css and not js
	 */
	private static String getTemplate(String type) throws UnsupportedOperationException{
		if (TYPE_CSS.equalsIgnoreCase(type)){
			return TEMPLATE_CSS;
		}else if (TYPE_JS.equalsIgnoreCase(type)){
			return TEMPLATE_JS;
		}
		throw new UnsupportedOperationException("type:[" + type + "] not support!,current time,only support js or css");
	}

}
