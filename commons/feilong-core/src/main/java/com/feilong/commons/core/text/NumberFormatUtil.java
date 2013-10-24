/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.commons.core.text;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NumberFormat 是所有数值格式的抽象基类,此类提供格式化和解析数值的接口<br>
 * 直接已知子类： ChoiceFormat, DecimalFormat
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-27 上午1:39:53
 * @see Format
 * @see NumberFormat
 * @see DecimalFormat
 */
public class NumberFormatUtil{

	private static final Logger	log	= LoggerFactory.getLogger(NumberFormatUtil.class);

	public static String format(Number value,String pattern){
		try{
			DecimalFormat decimalFormat = new DecimalFormat(pattern);
			// decimalFormat.applyPattern("##,###.000");
			return decimalFormat.format(value);
		}catch (Exception e){
			Object[] objects = { e.getMessage(), value, pattern };
			log.error("{},传入的参数为,[value:{}],[pattern:{}]", objects);
		}
		return null;
	}
}
