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
package com.feilong.tools.jfreechart;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.jfreechart.pie.PieChartEntity;
import com.feilong.tools.jfreechart.pie.PieChartFactory;
import com.feilong.tools.jfreechart.pie.PieType;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 16 01:20:44
 */
public class PieChartFactoryTest{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(PieChartFactoryTest.class);

	@Test
	public void createImage() throws IOException{
		PieType pieType = PieType.ring;
		Map<String, Number> keyAndDataMap = new LinkedHashMap<String, Number>();
		keyAndDataMap.put("失败率", 50);
		keyAndDataMap.put("成功率", 250);
		String chartTitle = "饼状图" + pieType;
		String imageNameOrOutputStream = chartTitle + ".png";
		int width = 800;
		int height = 600;
		PieChartEntity chartEntiry = new PieChartEntity();
		chartEntiry.setChartTitle(chartTitle);
		chartEntiry.setKeyAndDataMap(keyAndDataMap);
		ChartInfoEntity chartInfoEntity = new ChartInfoEntity();
		chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
		chartInfoEntity.setWidth(width);
		chartInfoEntity.setHeight(height);
		ChartUtil jfreeChartUtil = PieChartFactory.createChartUtil(chartEntiry, pieType);
		//jfreeChartUtil.getPiePlot3D().setBackgroundPaint(Color.black);
		jfreeChartUtil.createImage(chartInfoEntity);
	}
}
