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
package com.feilong.tools.jfreechart.category;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;

/**
 * The Class Line3DChartUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 16 00:13:00
 */
public final class Line3DChartUtil extends CategoryChartUtil{

	/**
	 * Instantiates a new line3 d chart util.
	 * 
	 * @param categoryChartEntity
	 *            the category chart entity
	 */
	public Line3DChartUtil(CategoryChartEntity categoryChartEntity){
		super(categoryChartEntity, CategoryChartType.LINE3D);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultCategoryAxisAttributes(org.jfree.chart.axis.CategoryAxis
	 * )
	 */
	@Override
	protected void setChildDefaultCategoryAxisAttributes(CategoryAxis categoryAxis){
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultCategoryPlotAttributes(org.jfree.chart.plot.CategoryPlot
	 * )
	 */
	@Override
	protected void setChildDefaultCategoryPlotAttributes(CategoryPlot categoryPlot){
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultNumberAxisAttributes(org.jfree.chart.axis.NumberAxis)
	 */
	@Override
	protected void setChildDefaultNumberAxisAttributes(NumberAxis numberAxis){
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setDefaultCategoryItemRenderer()
	 */
	@Override
	protected void setDefaultCategoryItemRenderer(){
		// TODO Auto-generated method stub
	}
}
