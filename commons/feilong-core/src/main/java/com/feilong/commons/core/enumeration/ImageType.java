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
package com.feilong.commons.core.enumeration;

/**
 * 飞龙图片类型
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-30 下午01:19:46
 * @since 1.0
 */
public final class ImageType{

	/**
	 * JPG Joint Photograhic Experts Group（联合图像专家组）,JPEG的文件格式一般有两种文件扩展名：.jpg和.jpeg，这两种扩展名的实质是相同的
	 */
	public final static String	JPG		= "JPG";

	/**
	 * JPEG Joint Photograhic Experts Group（联合图像专家组）,JPEG的文件格式一般有两种文件扩展名：.jpg和.jpeg，这两种扩展名的实质是相同的
	 */
	public final static String	JPEG	= "JPEG";

	/**
	 * PNG (Portable Network Graphic Format，PNG) 流式网络图形格式
	 */
	public final static String	PNG		= "PNG";

	/**
	 * GIF Graphics Interchange format（图形交换格式）
	 */
	public final static String	GIF		= "GIF";

	/**
	 * BMP Windows 位图<br>
	 * 为了保证照片图像的质量，请使用 PNG 、JPEG、TIFF 文件。<br>
	 * BMP 文件适用于 Windows 中的墙纸
	 */
	public final static String	BMP		= "BMP";
}