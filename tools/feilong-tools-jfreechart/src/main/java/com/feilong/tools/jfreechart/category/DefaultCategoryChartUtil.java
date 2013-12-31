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
package com.feilong.tools.jfreechart.category;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 16 00:38:28
 */
public class DefaultCategoryChartUtil extends CategoryChartUtil{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(DefaultCategoryChartUtil.class);

	public DefaultCategoryChartUtil(JFreeChart freeChart){
		super(freeChart);
	}

	public DefaultCategoryChartUtil(CategoryChartEntity categoryChartEntity, CategoryChartType chartType){
		super(categoryChartEntity, chartType);
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultCategoryAxisAttributes(org.jfree.chart.axis.CategoryAxis)
	 */
	@Override
	protected void setChildDefaultCategoryAxisAttributes(CategoryAxis categoryAxis){
	// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultCategoryPlotAttributes(org.jfree.chart.plot.CategoryPlot)
	 */
	@Override
	protected void setChildDefaultCategoryPlotAttributes(CategoryPlot categoryPlot){
	// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultNumberAxisAttributes(org.jfree.chart.axis.NumberAxis)
	 */
	@Override
	protected void setChildDefaultNumberAxisAttributes(NumberAxis numberAxis){
	// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setDefaultCategoryItemRenderer()
	 */
	@Override
	protected void setDefaultCategoryItemRenderer(){
	// TODO Auto-generated method stub
	}
}
