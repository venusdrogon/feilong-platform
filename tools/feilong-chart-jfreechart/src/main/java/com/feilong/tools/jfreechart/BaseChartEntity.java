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
package com.feilong.tools.jfreechart;

import java.io.Serializable;

/**
 * JFreeChart 用到的实体类.
 * 
 * @author 金鑫 2010-1-16 下午06:14:54
 */
public abstract class BaseChartEntity implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // 图表标题
    /** The chart title. */
    private String            chartTitle       = "";

    /** 是否显示legend，即下面说明. */
    private boolean           isShowLegend     = true;

    /**
     * configure chart to generate tool tips? <br>
     * 是否生成工具 当鼠标放在某个块上面是否显示该块信息（如所占百分比）.
     */
    private boolean           tooltips         = false;

    /**
     * configure chart to generate URLs?<br>
     * 是否生成URL链接.
     */
    private boolean           urls             = false;

    /**
     * Gets the chart title.
     * 
     * @return the chartTitle
     */
    public String getChartTitle(){
        return chartTitle;
    }

    /**
     * Sets the chart title.
     * 
     * @param chartTitle
     *            the chartTitle to set
     */
    public void setChartTitle(String chartTitle){
        this.chartTitle = chartTitle;
    }

    /**
     * Checks if is show legend.
     * 
     * @return the isShowLegend
     */
    public boolean isShowLegend(){
        return isShowLegend;
    }

    /**
     * Sets the show legend.
     * 
     * @param isShowLegend
     *            the isShowLegend to set
     */
    public void setShowLegend(boolean isShowLegend){
        this.isShowLegend = isShowLegend;
    }

    /**
     * Checks if is configure chart to generate tool tips? <br>
     * 是否生成工具 当鼠标放在某个块上面是否显示该块信息（如所占百分比）.
     * 
     * @return the tooltips
     */
    public boolean isTooltips(){
        return tooltips;
    }

    /**
     * 设置 configure chart to generate tool tips? <br>
     * 是否生成工具 当鼠标放在某个块上面是否显示该块信息（如所占百分比）.
     * 
     * @param tooltips
     *            the tooltips to set
     */
    public void setTooltips(boolean tooltips){
        this.tooltips = tooltips;
    }

    /**
     * Checks if is configure chart to generate URLs?<br>
     * 是否生成URL链接.
     * 
     * @return the urls
     */
    public boolean isUrls(){
        return urls;
    }

    /**
     * 设置 configure chart to generate URLs?<br>
     * 是否生成URL链接.
     * 
     * @param urls
     *            the urls to set
     */
    public void setUrls(boolean urls){
        this.urls = urls;
    }
}
