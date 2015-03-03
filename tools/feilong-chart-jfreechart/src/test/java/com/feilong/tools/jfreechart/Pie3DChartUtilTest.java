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
package com.feilong.tools.jfreechart;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.feilong.tools.jfreechart.pie.Pie3DChartUtil;
import com.feilong.tools.jfreechart.pie.PieChartEntity;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-31 下午04:49:25
 */
public class Pie3DChartUtilTest{

	private ChartInfoEntity	chartInfoEntity;

	/**
	 * 生成饼状图
	 * 
	 * @throws IOException
	 */
	@Test
	public void makePie3DChart() throws IOException{
		Map<String, Number> keyAndDataMap = new LinkedHashMap<String, Number>();
		keyAndDataMap.put("失败率", 50);
		keyAndDataMap.put("成功率", 250);
		String chartTitle = "饼状图";
		String imageNameOrOutputStream = chartTitle + ".png";
		int width = 800;
		int height = 600;
		PieChartEntity chartEntiry = new PieChartEntity();
		chartEntiry.setChartTitle(chartTitle);
		chartEntiry.setKeyAndDataMap(keyAndDataMap);
		Pie3DChartUtil jfreeChartUtil = new Pie3DChartUtil(chartEntiry);
		//jfreeChartUtil.getPiePlot3D().setBackgroundPaint(Color.black);
		chartInfoEntity = new ChartInfoEntity();
		chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
		chartInfoEntity.setWidth(width);
		chartInfoEntity.setHeight(height);
		jfreeChartUtil.createImage(chartInfoEntity);
	}
}
