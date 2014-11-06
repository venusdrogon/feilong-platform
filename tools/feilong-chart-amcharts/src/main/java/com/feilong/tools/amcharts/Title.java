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
package com.feilong.tools.amcharts;

import java.io.Serializable;

/**
 * 标题 Creates a title on above the chart, multiple can be assigned.
 * 
 * <pre>
 * 
 * Example 1:
 * {@code
 * var chart = AmCharts.makeChart("chartdiv",{
 * 	...
 * 	"titles": [
 * 		{
 * 			"text": "Chart Title",
 * 			"size": 15
 * 		}
 * 	]
 * });
 * }
 * </pre>
 * 
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年10月24日 下午4:15:01
 * @since 1.0.8
 */
public class Title implements Serializable{

	private static final long	serialVersionUID	= 288232184048495608L;

	/**
	 * Unique id of a Title. <br>
	 * You don't need to set it, unless you want to.
	 */
	private String				id;

	/** Opacity of a title. */
	private Number				alpha				= 1;

	/** Specifies if title should be bold or not.; */
	private boolean				bold				= false;

	/** Color Text color of a title. */
	private String				color;

	/** Text size of a title. */
	private Integer				size;

	/**
	 * Text of a title
	 */
	private String				text;

	/**
	 * @return the id
	 */
	public String getId(){
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id){
		this.id = id;
	}

	/**
	 * @return the alpha
	 */
	public Number getAlpha(){
		return alpha;
	}

	/**
	 * @param alpha
	 *            the alpha to set
	 */
	public void setAlpha(Number alpha){
		this.alpha = alpha;
	}

	/**
	 * @return the bold
	 */
	public boolean getBold(){
		return bold;
	}

	/**
	 * @param bold
	 *            the bold to set
	 */
	public void setBold(boolean bold){
		this.bold = bold;
	}

	/**
	 * @return the color
	 */
	public String getColor(){
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color){
		this.color = color;
	}

	/**
	 * @return the size
	 */
	public Integer getSize(){
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Integer size){
		this.size = size;
	}

	/**
	 * @return the text
	 */
	public String getText(){
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text){
		this.text = text;
	}
}
