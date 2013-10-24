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
package com.feilong.commons.core.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数学工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 11, 2013 12:30:10 AM
 */
public class MathUtil{

	private static final Logger	log	= LoggerFactory.getLogger(MathUtil.class);

	/**
	 * 弧度转成度 <br>
	
	 * 
	 * @param radians
	 * @return
	 */
	public static double radian2Degree(double radians){
		double degree = radians * 180 / Math.PI;
		return degree;
	}

	/**
	 * 度转成弧度
	 * 
	 * @param degree
	 * @return
	 */
	public static double degree2Radian(double degree){
		double radians = degree * Math.PI / 180;
		return radians;
	}

}
