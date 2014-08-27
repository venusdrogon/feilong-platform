/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.controller.taglib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UrlPathHelper;

import com.feilong.commons.core.entity.BackWarnEntity;
import com.feilong.commons.core.entity.Pager;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.ParamUtil;
import com.feilong.spring.util.UriTemplateUtil;
import com.feilong.spring.util.UrlPathHelperUtil;
import com.feilong.spring.web.controller.AbstractController;
import com.feilong.taglib.display.pager.PagerUtil;
import com.feilong.taglib.display.pager.command.PagerConstants;

/**
 * The Class PagerController.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class PagerController extends AbstractController{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(PagerController.class);

	/**
	 * Pager.
	 * 
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @return the string
	 */
	@RequestMapping("/pager")
	public String pager(Model model,HttpServletRequest request){
		List<BackWarnEntity> list = new ArrayList<BackWarnEntity>();
		BackWarnEntity backWarnEntity = null;
		for (int i = 0; i < 10; i++){
			backWarnEntity = new BackWarnEntity();
			backWarnEntity.setDescription("xy2venus_" + i + "@163.com");
			list.add(backWarnEntity);
		}
		Pager pager = new Pager();
		pager.setItemList(list);
		pager.setCount(1024);
		/**
		 * 当前页码
		 */
		int currentPageNo = PagerUtil.getCurrentPageNo(request, PagerConstants.DEFAULT_PAGE_PARAM_NAME);
		/**
		 * 总页数
		 */
		int allPageNo = pager.getAllPageNo();
		// 当前页码大于总页数
		if (currentPageNo > allPageNo){
			currentPageNo = allPageNo;
		}
		// 当前页码
		pager.setCurrentPageNo(currentPageNo);
		request.setAttribute("pageModel", pager);

		// ${requestScope['org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE']}
		request.setAttribute("locale", getLocale());

		if (log.isDebugEnabled()){
			Map<String, Object> map = UrlPathHelperUtil.getUrlPathHelperMapForLog(request);
			log.debug(JsonUtil.format(map));
		}

		String queryString = request.getQueryString();
		String expandUrl = UriTemplateUtil.getBestMatchingPattern(request);

		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String _url = urlPathHelper.getOriginatingContextPath(request) + expandUrl
				+ (Validator.isNotNullOrEmpty(queryString) ? "?" + queryString : "");

		request.setAttribute("aaaa", ParamUtil.removeParameter(_url, PagerConstants.DEFAULT_PAGE_PARAM_NAME, CharsetType.UTF8));

		return "feilong.taglibTest.pagerTest";
	}
}
