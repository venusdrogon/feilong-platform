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

import com.feilong.commons.core.lang.MathUtil;

/**
 * 飞龙地球测数据
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-6 下午11:13:57
 * @since 1.0
 */
public class EarthUtil{

	/**
	 * 2000国家大地坐标系采用的地球椭球参数为: a(长半轴) = 6378137m
	 */
	private static final double	EARTH_RADIUS	= 6378137;

	/**
	 * 根据两点间经纬度坐标（double值） 计算两点间距离，单位为米 <br/>
	 * Lat/Long 纬度/经度<br/>
	 * Radius 半径
	 * 
	 * @param lng1
	 *            经度
	 * @param lat1
	 *            纬度
	 * @param lng2
	 *            经度
	 * @param lat2
	 *            纬度
	 * @return 根据两点间经纬度坐标（double值） 计算两点间距离，单位为米
	 */
	public static double getDistance(double lng1,double lat1,double lng2,double lat2){
		double radLat1 = MathUtil.degree2Radian(lat1);
		double radLat2 = MathUtil.degree2Radian(lat2);
		// 纬度
		double cha_rad_lat = radLat1 - radLat2;

		// 经度
		double cha_rad_lng = MathUtil.degree2Radian(lng1) - MathUtil.degree2Radian(lng2);
		// 距离
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(cha_rad_lat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
						* Math.pow(Math.sin(cha_rad_lng / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
}
