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
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
@SuppressWarnings("all")public class UrlController{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(UrlController.class);

	// @RequestMapping("/{c1}-{c2}-{c3}-{c4}-{label1}-{label2}-{label3}.htm")
	public void search(
			@PathVariable("c1") Integer c1,
			@PathVariable("c2") Integer c2,
			@PathVariable("c3") Integer c3,
			@PathVariable("c4") Integer c4,
			@PathVariable("label1") Integer label1,
			@PathVariable("label2") Integer label2,
			@PathVariable("label3") Integer label3){
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(label1);
		System.out.println(label2);
		System.out.println(label3);
	}

	// /
	// @RequestMapping("/{c1}-{c2}-{c3}-{c4}.htm")
	public void category(@PathVariable("c1") Integer c1,@PathVariable("c2") Integer c2,@PathVariable("c3") Integer c3,@PathVariable("c4") Integer c4){
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
	}

	// /2-5-3-138-8-8-8.htm
	// /2-5-3-138-8.htm
	// @RequestMapping("/{c1}-{c2}-{c3}-{c4}-{label}.htm")
	public void label(
			@PathVariable("c1") Integer c1,
			@PathVariable("c2") Integer c2,
			@PathVariable("c3") Integer c3,
			@PathVariable("c4") Integer c4,
			@PathVariable("label") Integer label){
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(label);
	}
}
