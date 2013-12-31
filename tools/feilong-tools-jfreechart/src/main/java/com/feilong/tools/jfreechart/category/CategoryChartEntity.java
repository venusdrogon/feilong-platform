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

import java.util.Map;

import org.jfree.chart.plot.PlotOrientation;

import com.feilong.tools.jfreechart.BaseChartEntity;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 13 00:11:17
 */
public class CategoryChartEntity extends BaseChartEntity{

	private static final long		serialVersionUID	= -5823129547441566221L;

	// 目录轴的显示标签
	private String					categoryAxisLabel	= "";

	// 数值轴的显示标签
	private String					valueAxisLabel		= "";

	/**
	 * X轴数据
	 */
	private String[]				rowKeys;

	private Map<String, double[]>	categoryAndValues;

	/**
	 * 图片方向 默认竖型 垂直（竖向）horizontal平行的
	 */
	private PlotOrientation			plotOrientation		= PlotOrientation.VERTICAL;

	public CategoryChartEntity(){}

	/**
	 * @return the categoryAxisLabel
	 */
	public String getCategoryAxisLabel(){
		return categoryAxisLabel;
	}

	/**
	 * @param categoryAxisLabel
	 *            the categoryAxisLabel to set
	 */
	public void setCategoryAxisLabel(String categoryAxisLabel){
		this.categoryAxisLabel = categoryAxisLabel;
	}

	/**
	 * @return the valueAxisLabel
	 */
	public String getValueAxisLabel(){
		return valueAxisLabel;
	}

	/**
	 * @param valueAxisLabel
	 *            the valueAxisLabel to set
	 */
	public void setValueAxisLabel(String valueAxisLabel){
		this.valueAxisLabel = valueAxisLabel;
	}

	/**
	 * @return the categoryAndValues
	 */
	public Map<String, double[]> getCategoryAndValues(){
		return categoryAndValues;
	}

	/**
	 * @param categoryAndValues
	 *            the categoryAndValues to set
	 */
	public void setCategoryAndValues(Map<String, double[]> categoryAndValues){
		this.categoryAndValues = categoryAndValues;
	}

	/**
	 * @return the rowKeys
	 */
	public String[] getRowKeys(){
		return rowKeys;
	}

	/**
	 * @param rowKeys
	 *            the rowKeys to set
	 */
	public void setRowKeys(String[] rowKeys){
		this.rowKeys = rowKeys;
	}

	/**
	 * @return the plotOrientation
	 */
	public PlotOrientation getPlotOrientation(){
		return plotOrientation;
	}

	/**
	 * @param plotOrientation
	 *            the plotOrientation to set
	 */
	public void setPlotOrientation(PlotOrientation plotOrientation){
		this.plotOrientation = plotOrientation;
	}
}
