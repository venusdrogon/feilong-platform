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
package com.feilong.tools.jfreechart.category.gantt;

import java.util.List;
import java.util.Map;

import org.jfree.data.gantt.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.jfreechart.BaseChartEntity;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 21 02:37:29
 */
public class GanttChartEntity extends BaseChartEntity{

	private static final long		serialVersionUID	= 2226545713841259013L;

	@SuppressWarnings("unused")
	private static final Logger		log					= LoggerFactory.getLogger(GanttChartEntity.class);

	// 目录轴的显示标签
	private String					categoryAxisLabel	= "";

	// 日期轴的显示标签
	private String					dateAxisLabel		= "";

	private Map<String, List<Task>>	seriesAndDataMap;

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
	 * @return the dateAxisLabel
	 */
	public String getDateAxisLabel(){
		return dateAxisLabel;
	}

	/**
	 * @param dateAxisLabel
	 *            the dateAxisLabel to set
	 */
	public void setDateAxisLabel(String dateAxisLabel){
		this.dateAxisLabel = dateAxisLabel;
	}

	/**
	 * @return the seriesAndDataMap
	 */
	public Map<String, List<Task>> getSeriesAndDataMap(){
		return seriesAndDataMap;
	}

	/**
	 * @param seriesAndDataMap
	 *            the seriesAndDataMap to set
	 */
	public void setSeriesAndDataMap(Map<String, List<Task>> seriesAndDataMap){
		this.seriesAndDataMap = seriesAndDataMap;
	}
}
