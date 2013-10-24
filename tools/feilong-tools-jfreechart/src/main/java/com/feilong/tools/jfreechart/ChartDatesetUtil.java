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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.tools.jfreechart.category.CategoryChartEntity;

import com.feilong.tools.jfreechart.pie.PieChartEntity;

/**
 * ChartDatesetUtil 生成Dateset
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 14 05:59:29
 */
public final class ChartDatesetUtil{

	/**
	 * 获得 PieDataset
	 * 
	 * @param pieChartEntity
	 * @return
	 */
	public final static PieDataset getPieDataset(PieChartEntity pieChartEntity){
		Map<String, Number> keyAndDataMap = pieChartEntity.getKeyAndDataMap();
		if (null != keyAndDataMap){
			DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
			for (Entry<String, Number> entry : keyAndDataMap.entrySet()){
				defaultPieDataset.setValue(entry.getKey(), entry.getValue());
			}
			return defaultPieDataset;
		}
		return null;
	}

	/**
	 * 获得数据集 CategoryDataset
	 * 
	 * @param freeChartEntity
	 * @return
	 */
	public static CategoryDataset getCategoryDataset(CategoryChartEntity freeChartEntity){
		Map<String, double[]> categoryAndValues = freeChartEntity.getCategoryAndValues();
		int size = categoryAndValues.size();
		//*****************************************************
		double[][] data = null;
		String[] columnKeys = new String[size];
		int i = 0;
		// 是否初始化
		boolean flag = false;
		for (Map.Entry<String, double[]> entry : categoryAndValues.entrySet()){
			double[] ds = entry.getValue();
			if (!flag){
				data = new double[ds.length][size];
				flag = true;
			}
			String columnKey = entry.getKey();
			columnKeys[i] = columnKey;
			int j = 0;
			for (double d : ds){
				data[j][i] = d;
				j++;
			}
			i++;
		}
		String[] rowKeys = freeChartEntity.getRowKeys();
		CategoryDataset categoryDataset = DatasetUtilities.createCategoryDataset(//
				rowKeys,// Y轴数据 
				columnKeys,// 填充所要的实际数据
				data// X轴数据
				);
		return categoryDataset;
	}

	/**
	 * 将list 转换成两个数组(columnKeys,data)并存放到object数组中
	 * 
	 * @param list
	 *            存放(key,value) 的数组集合 数组的第一个元素存放的是下面的标题,第二个元素存放的是值
	 * @return
	 */
	private Object[] getColumnKeysAndData(List list){
		// 返回的数组 包含columnKey 和data
		Object[] returnObjects = new Object[2];
		int size = list.size();
		// 声明并初始化 columnKey data
		String[] columnKey = new String[size];
		double[] data = new double[size];
		// 数组对象 以保存集合中的数据
		Object[] objects = null;
		for (int i = 0; i < size; i++){
			objects = (Object[]) list.get(i);
			// x 坐标数据
			if (objects[0] instanceof String){
				columnKey[i] = (String) objects[0];
			}else if (objects[0] instanceof Date){
				columnKey[i] = DateUtil.date2String((Date) objects[0], "MM-dd");
			}else{
				columnKey[i] = objects[0].toString();
			}
			// 值 y坐标数据
			// 数字
			data[i] = Double.parseDouble(objects[1].toString());
		}
		returnObjects[0] = columnKey;
		double[][] d = { data };
		returnObjects[1] = d;
		return returnObjects;
	}

	/**
	 * 用于甘特图
	 * 
	 * @param seriesAndDataMap
	 * @return
	 */
	public static TaskSeriesCollection getTaskSeriesCollection(Map<String, List<Task>> seriesAndDataMap){
		TaskSeriesCollection taskSeriesCollection = new TaskSeriesCollection();
		TaskSeries taskSeries = null;
		for (Map.Entry<String, List<Task>> entry : seriesAndDataMap.entrySet()){
			String key = entry.getKey();
			taskSeries = new TaskSeries(key);
			List<Task> taskList = entry.getValue();
			for (int i = 0; i < taskList.size(); ++i){
				taskSeries.add(taskList.get(i));
			}
			taskSeriesCollection.add(taskSeries);
		}
		return taskSeriesCollection;
	}
}
