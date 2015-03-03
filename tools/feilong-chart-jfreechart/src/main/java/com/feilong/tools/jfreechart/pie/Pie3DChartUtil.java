/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.tools.jfreechart.pie;

import java.awt.Color;
import java.awt.Paint;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.plot.PiePlot3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3D饼图.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-31 下午05:16:30
 */
@SuppressWarnings("all")
public final class Pie3DChartUtil extends BasePieChartUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(Pie3DChartUtil.class);

	/** 3d饼状图图表区域对象. */
	protected PiePlot3D			piePlot3D;

	/**
	 * Instantiates a new pie3 d chart util.
	 * 
	 * @param pieChartEntity
	 *            the pie chart entity
	 */
	public Pie3DChartUtil(PieChartEntity pieChartEntity){
		super(pieChartEntity, PieType.PIE3D);
		setDefaultPiePlot3D(pieChartEntity);
	}

	/**
	 * 饼状图.
	 * 
	 * @param pieChartEntity
	 *            the new default pie plot3 d
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
	 * 获得 3d饼状图图表区域对象.
	 * 
	 * @return the piePlot3D
	 */
	public PiePlot3D getPiePlot3D(){
		return piePlot3D;
	}
}
