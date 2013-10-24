package com.feilong.tools.jfreechart;

import java.io.Serializable;

/**
 * JFreeChart 用到的实体类
 * 
 * @author 金鑫 2010-1-16 下午06:14:54
 */
public abstract class BaseChartEntity implements Serializable{

	private static final long	serialVersionUID	= 1L;

	// 图表标题
	private String				chartTitle			= "";

	/**
	 * 是否显示legend，即下面说明
	 */
	private boolean				isShowLegend		= true;

	/**
	 * configure chart to generate tool tips? <br>
	 * 是否生成工具 当鼠标放在某个块上面是否显示该块信息（如所占百分比）
	 */
	private boolean				tooltips			= false;

	/**
	 * configure chart to generate URLs?<br>
	 * 是否生成URL链接
	 */
	private boolean				urls				= false;

	/**
	 * @return the chartTitle
	 */
	public String getChartTitle(){
		return chartTitle;
	}

	/**
	 * @param chartTitle
	 *            the chartTitle to set
	 */
	public void setChartTitle(String chartTitle){
		this.chartTitle = chartTitle;
	}

	/**
	 * @return the isShowLegend
	 */
	public boolean isShowLegend(){
		return isShowLegend;
	}

	/**
	 * @param isShowLegend
	 *            the isShowLegend to set
	 */
	public void setShowLegend(boolean isShowLegend){
		this.isShowLegend = isShowLegend;
	}

	/**
	 * @return the tooltips
	 */
	public boolean isTooltips(){
		return tooltips;
	}

	/**
	 * @param tooltips
	 *            the tooltips to set
	 */
	public void setTooltips(boolean tooltips){
		this.tooltips = tooltips;
	}

	/**
	 * @return the urls
	 */
	public boolean isUrls(){
		return urls;
	}

	/**
	 * @param urls
	 *            the urls to set
	 */
	public void setUrls(boolean urls){
		this.urls = urls;
	}
}
