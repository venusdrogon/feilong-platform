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
package com.feilong.tools.jfreechart.category.gantt;

import java.util.List;
import java.util.Map;

import org.jfree.data.gantt.Task;

import com.feilong.tools.jfreechart.BaseChartEntity;

/**
 * The Class GanttChartEntity.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 21 02:37:29
 */
public class GanttChartEntity extends BaseChartEntity{

    /** The Constant serialVersionUID. */
    private static final long       serialVersionUID  = 2226545713841259013L;

    // 目录轴的显示标签
    /** The category axis label. */
    private String                  categoryAxisLabel = "";

    // 日期轴的显示标签
    /** The date axis label. */
    private String                  dateAxisLabel     = "";

    /** The series and data map. */
    private Map<String, List<Task>> seriesAndDataMap;

    /**
     * 获得 category axis label.
     *
     * @return the categoryAxisLabel
     */
    public String getCategoryAxisLabel(){
        return categoryAxisLabel;
    }

    /**
     * 设置 category axis label.
     *
     * @param categoryAxisLabel
     *            the categoryAxisLabel to set
     */
    public void setCategoryAxisLabel(String categoryAxisLabel){
        this.categoryAxisLabel = categoryAxisLabel;
    }

    /**
     * 获得 date axis label.
     *
     * @return the dateAxisLabel
     */
    public String getDateAxisLabel(){
        return dateAxisLabel;
    }

    /**
     * 设置 date axis label.
     *
     * @param dateAxisLabel
     *            the dateAxisLabel to set
     */
    public void setDateAxisLabel(String dateAxisLabel){
        this.dateAxisLabel = dateAxisLabel;
    }

    /**
     * 获得 series and data map.
     *
     * @return the seriesAndDataMap
     */
    public Map<String, List<Task>> getSeriesAndDataMap(){
        return seriesAndDataMap;
    }

    /**
     * 设置 series and data map.
     *
     * @param seriesAndDataMap
     *            the seriesAndDataMap to set
     */
    public void setSeriesAndDataMap(Map<String, List<Task>> seriesAndDataMap){
        this.seriesAndDataMap = seriesAndDataMap;
    }
}
