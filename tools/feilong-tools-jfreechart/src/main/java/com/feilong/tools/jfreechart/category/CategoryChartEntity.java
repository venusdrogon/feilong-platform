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

import java.util.Map;

import org.jfree.chart.plot.PlotOrientation;

import com.feilong.tools.jfreechart.BaseChartEntity;

/**
 * The Class CategoryChartEntity.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 13 00:11:17
 */
public class CategoryChartEntity extends BaseChartEntity{

	/** The Constant serialVersionUID. */
	private static final long		serialVersionUID	= -5823129547441566221L;

	// 目录轴的显示标签
	/** The category axis label. */
	private String					categoryAxisLabel	= "";

	// 数值轴的显示标签
	/** The value axis label. */
	private String					valueAxisLabel		= "";

	/** X轴数据. */
	private String[]				rowKeys;

	/** The category and values. */
	private Map<String, double[]>	categoryAndValues;

	/** 图片方向 默认竖型 垂直（竖向）horizontal平行的. */
	private PlotOrientation			plotOrientation		= PlotOrientation.VERTICAL;

	/**
	 * Instantiates a new category chart entity.
	 */
	public CategoryChartEntity(){}

	/**
	 * Gets the category axis label.
	 * 
	 * @return the categoryAxisLabel
	 */
	public String getCategoryAxisLabel(){
		return categoryAxisLabel;
	}

	/**
	 * Sets the category axis label.
	 * 
	 * @param categoryAxisLabel
	 *            the categoryAxisLabel to set
	 */
	public void setCategoryAxisLabel(String categoryAxisLabel){
		this.categoryAxisLabel = categoryAxisLabel;
	}

	/**
	 * Gets the value axis label.
	 * 
	 * @return the valueAxisLabel
	 */
	public String getValueAxisLabel(){
		return valueAxisLabel;
	}

	/**
	 * Sets the value axis label.
	 * 
	 * @param valueAxisLabel
	 *            the valueAxisLabel to set
	 */
	public void setValueAxisLabel(String valueAxisLabel){
		this.valueAxisLabel = valueAxisLabel;
	}

	/**
	 * Gets the category and values.
	 * 
	 * @return the categoryAndValues
	 */
	public Map<String, double[]> getCategoryAndValues(){
		return categoryAndValues;
	}

	/**
	 * 设置 category and values.
	 * 
	 * @param categoryAndValues
	 *            the categoryAndValues to set
	 */
	public void setCategoryAndValues(Map<String, double[]> categoryAndValues){
		this.categoryAndValues = categoryAndValues;
	}

	/**
	 * 获得 x轴数据.
	 * 
	 * @return the rowKeys
	 */
	public String[] getRowKeys(){
		return rowKeys;
	}

	/**
	 * 设置 x轴数据.
	 * 
	 * @param rowKeys
	 *            the rowKeys to set
	 */
	public void setRowKeys(String[] rowKeys){
		this.rowKeys = rowKeys;
	}

	/**
	 * 获得 图片方向 默认竖型 垂直（竖向）horizontal平行的.
	 * 
	 * @return the plotOrientation
	 */
	public PlotOrientation getPlotOrientation(){
		return plotOrientation;
	}

	/**
	 * 设置 图片方向 默认竖型 垂直（竖向）horizontal平行的.
	 * 
	 * @param plotOrientation
	 *            the plotOrientation to set
	 */
	public void setPlotOrientation(PlotOrientation plotOrientation){
		this.plotOrientation = plotOrientation;
	}
}
