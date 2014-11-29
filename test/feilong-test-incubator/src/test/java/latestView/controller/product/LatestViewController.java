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
package latestView.controller.product;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import latestView.shop.web.command.BrowsingHistoryCommand;
import latestView.shop.web.command.SimpleSkuCommand;
import latestView.util.LatestViewUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Class ProductController.
 */
@Controller
public class LatestViewController{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(LatestViewController.class);

	/**
	 * 历史记录.
	 *
	 * @param code
	 *            the code
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 * @deprecated
	 */
	@Deprecated
	@RequestMapping({ "/product/latestView.json" })
	public String latestView(@RequestParam(required = false) String code,Model model,HttpServletRequest request,HttpServletResponse response){
		setLatestViewJson(code, model, request, response);
		return "json";
	}

	/**
	 * 设置最近访问历史记录<br>
	 * <ul>
	 * <li>html</li>
	 * </ul>
	 * .
	 *
	 * @param code
	 *            the code
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @see "UserDetailsInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)"
	 */
	public void setLatestViewJson(String code,Model model,HttpServletRequest request,HttpServletResponse response){
		TreeSet<BrowsingHistoryCommand> browsingHistoryCommandSet = LatestViewUtil.getBrowsingHistoryCommandTreeSetFromCookie(
						request,
						response);
		//		List<Long> channelIds = getUserDetails().getChannels();
		//		 		SimpleSkuCommand simpleSkuCommand = skuManager.getLeastSkuPriceSimpleSkuCommand(code, channelIds);
		SimpleSkuCommand simpleSkuCommand = null;
		browsingHistoryCommandSet = LatestViewUtil.mergerBrowsingHistoryCommandSet(browsingHistoryCommandSet, simpleSkuCommand);
		LatestViewUtil.toCookie(browsingHistoryCommandSet, response);
		// ****************parse vm***************
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("browsingHistoryCommandSet", browsingHistoryCommandSet);
		for (BrowsingHistoryCommand browsingHistoryCommand : browsingHistoryCommandSet){
			log.debug("browsingHistoryCommandSet:{}:{}", browsingHistoryCommand.getDate(), browsingHistoryCommand.getSimpleSkuCommand()
							.getCode());
		}
		//		NumberFormat format = NumberFormat.getInstance();
		//		format.setMaximumFractionDigits(0);
		//		param.put("format", format);
		//		String html = scriptManager.parseVMTemplate(ScriptConstants.SCRIPT_PRODUCT_LATEST_VIEW_JSP, param);
		//		model.addAttribute("html", html);
	}

}
