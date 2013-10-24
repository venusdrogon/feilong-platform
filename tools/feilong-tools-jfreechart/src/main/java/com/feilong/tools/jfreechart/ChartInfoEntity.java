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

import com.feilong.commons.core.io.SpecialFolder;

/**
 * chart info 信息<br>
 * 封装了图片的基本信息<br>
 * 包括:
 * <ul>
 * <li>chartPath 目录文件夹路径</li>
 * <li>width 图片宽度</li>
 * <li>height 图片高度</li>
 * <li>imageNameOrOutputStream 输出流/或者圖片名稱</li>
 * </ul>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 2 20:15:58
 */
public final class ChartInfoEntity{

	/**
	 * 目录文件夹路径,默认桌面,<br>
	 * 仅当输出的是硬盘图片文件时需要设置改值,<br>
	 * 自动将其拼接成图片路径,转成fileOutputStream
	 */
	private String	chartPath	= SpecialFolder.getDesktop();

	/**
	 * 图片宽度 默认500
	 */
	private int		width		= 500;

	/**
	 * 图片高度 默认400
	 */
	private int		height		= 400;

	/**
	 * 输出流,或者图片名称<br>
	 * 代码中会自动识别
	 */
	private Object	imageNameOrOutputStream;

	/**
	 * 
	 */
	public ChartInfoEntity(){
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param imageNameOrOutputStream
	 * @param width
	 * @param height
	 */
	public ChartInfoEntity(Object imageNameOrOutputStream, int width, int height){
		this.imageNameOrOutputStream = imageNameOrOutputStream;
		this.width = width;
		this.height = height;
	}

	/**
	 * 获得图片宽度 默认500
	 * 
	 * @return the width
	 */
	public int getWidth(){
		return width;
	}

	/**
	 * 设置图片宽度
	 * 
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width){
		this.width = width;
	}

	/**
	 * 返回图片高度 默认400
	 * 
	 * @return the height
	 */
	public int getHeight(){
		return height;
	}

	/**
	 * 设置图片高度
	 * 
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height){
		this.height = height;
	}

	/**
	 * 目录文件夹路径,默认桌面,<br>
	 * 仅当输出的是硬盘图片文件时需要设置改值,<br>
	 * 自动将其拼接成图片路径,转成fileOutputStream
	 * 
	 * @return the chartPath
	 */
	public String getChartPath(){
		return chartPath;
	}

	/**
	 * 目录文件夹路径,默认桌面,<br>
	 * 仅当输出的是硬盘图片文件时需要设置改值,<br>
	 * 自动将其拼接成图片路径,转成fileOutputStream
	 * 
	 * @param chartPath
	 *            the chartPath to set
	 */
	public void setChartPath(String chartPath){
		this.chartPath = chartPath;
	}

	/**
	 * 输出流,或者图片名称<br>
	 * 代码中会自动识别
	 * 
	 * @return the imageNameOrOutputStream
	 */
	public Object getImageNameOrOutputStream(){
		return imageNameOrOutputStream;
	}

	/**
	 * 输出流,或者图片名称<br>
	 * 代码中会自动识别
	 * 
	 * @param imageNameOrOutputStream
	 *            the imageNameOrOutputStream to set
	 */
	public void setImageNameOrOutputStream(Object imageNameOrOutputStream){
		this.imageNameOrOutputStream = imageNameOrOutputStream;
	}
}
