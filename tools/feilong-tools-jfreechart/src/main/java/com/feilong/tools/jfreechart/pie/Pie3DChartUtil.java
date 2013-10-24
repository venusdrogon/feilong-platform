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
package com.feilong.tools.jfreechart.pie;

import java.awt.Color;
import java.awt.Paint;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.plot.PiePlot3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 3D饼图
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-31 下午05:16:30
 */
public final class Pie3DChartUtil extends BasePieChartUtil{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(Pie3DChartUtil.class);

	/**
	 * 3d饼状图图表区域对象
	 */
	protected PiePlot3D			piePlot3D;

	/**
	 * @param jFreeChart
	 */
	public Pie3DChartUtil(PieChartEntity pieChartEntity){
		super(pieChartEntity, PieType.pie3D);
		setDefaultPiePlot3D(pieChartEntity);
	}

	/**
	 * 饼状图
	 * 
	 * @param freeChartEntity
	 *            freeChartEntity实体,包含freeChart需要的不定属性
	 * @throws IOException
	 */
	public void setDefaultPiePlot3D(PieChartEntity pieChartEntity){
		piePlot3D = (PiePlot3D) getJFreeChart().getPlot();
		/**
		 * piePlot3D 每个面饼的颜色
		 */
		Paint[] paints_PiePlot3DPaint = { new Color(244, 194, 144), new Color(144, 233, 144), Color.BLACK, Color.BLUE };
		Map<String, Number> keyAndDataMap = pieChartEntity.getKeyAndDataMap();
		int i = 0;
		for (Entry<String, Number> entry : keyAndDataMap.entrySet()){
			// 设置分饼颜色
			piePlot.setSectionPaint(entry.getKey(), paints_PiePlot3DPaint[i]);
			i++;
		}
	}

	/**
	 * @return the piePlot3D
	 */
	public PiePlot3D getPiePlot3D(){
		return piePlot3D;
	}
}
