/**
 * Copyright (c) 2008-2012 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.web.entity.domain;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Validator;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ESAPIValidatorUtil
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-8-16 下午2:25:36
 */
public class ESAPIValidatorUtil{

	private static final Logger	log			= LoggerFactory.getLogger(ESAPIValidatorUtil.class);

	public static Validator		validator	= ESAPI.validator();

	/**
	 * 获得安全的html代码
	 * 
	 * @param input
	 * @return
	 */
	public static String getValidSafeHTML(String input){
		try{
			return validator.getValidSafeHTML("html", input, Integer.MAX_VALUE, true);
		}catch (ValidationException e){
			e.printStackTrace();
		}catch (IntrusionException e){
			e.printStackTrace();
		}

		return null;
	}
}
