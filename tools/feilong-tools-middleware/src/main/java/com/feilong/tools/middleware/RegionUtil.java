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
package com.feilong.tools.middleware;

/**
 * 地区操作
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-4-15 上午11:11:00
 * @since 1.0
 */
public class RegionUtil{

	/**
	 * 直辖市
	 */
	public static String[]	municipalities					= { "北京市", "上海市", "天津市", "重庆市" };

	/**
	 * 特别行政区
	 */
	public static String[]	specialAdministrativeRegions	= { "香港", "澳门" };

	/**
	 * 是否是直辖市
	 * 
	 * @param provinceName
	 *            省份名称
	 * @return
	 */
	public static boolean isMunicipality(String provinceName){
		boolean flag = false;
		for (String municipality : municipalities){
			if (municipality.equals(provinceName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
