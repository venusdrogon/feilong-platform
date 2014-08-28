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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.spring.beans.propertyeditors.URLDecoderEditor;

@Controller
@SuppressWarnings("all")public class PathVariableController{

	private static final Logger	log	= LoggerFactory.getLogger(PathVariableController.class);

	@InitBinder({ "categoryCode" })
	// 此处的参数也可以是ServletRequestDataBinder类型
	public void initBinder(WebDataBinder binder){
		// 注册自定义的属性编辑器
		binder.registerCustomEditor(String.class, new URLDecoderEditor(CharsetType.UTF8));
	}

	@RequestMapping("/c{categoryCode}-{name}")
	public String category1(@PathVariable("categoryCode") String categoryCode,@PathVariable("name") String name){
		log.info("categoryCode:{}", categoryCode);
		log.info("name:{}", name);
		return null;
	}
}
