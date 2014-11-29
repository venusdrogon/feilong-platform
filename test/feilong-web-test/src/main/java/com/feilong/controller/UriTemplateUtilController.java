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
package com.feilong.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feilong.servlet.http.RequestUtil;
import com.feilong.spring.web.util.UriTemplateUtil;

@Controller
@SuppressWarnings("all")public class UriTemplateUtilController{

	private static final Logger	log	= LoggerFactory.getLogger(UriTemplateUtilController.class);

	@RequestMapping("/c{categoryCode}/m{material}-c{color}-s{size}-k{kind}-s{style}.htm")
	public String category1(
			@PathVariable("categoryCode") String categoryCode,
			@PathVariable("material") String material,
			@PathVariable("color") String color,
			@PathVariable("size") String size,
			@PathVariable("kind") String kind,
			@PathVariable("style") String style,
			HttpServletRequest request,
			Model model){
		// /c2-5-3-11/mpige-c黑-s52-kchuck taylor all star-svintage.htm
		log.info(categoryCode);
		log.info(material);
		log.info(color);
		log.info(size);
		log.info(style);
		log.info(kind);

		String requestStringForLog = RequestUtil.getRequestStringForLog(request);

		log.info(requestStringForLog);

		// request.getRequestURL()

		String expandBestMatchingPattern = UriTemplateUtil.expandBestMatchingPattern(request, "material", "a");

		log.info(expandBestMatchingPattern);

		model.addAttribute("isSuccess", true);

		return "UriTemplateUtilTest";
	}
}
