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
package com.feilong.commons.core.enumeration;

/**
 * 飞龙图片类型
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-30 下午01:19:46
 * @version 1.0.5 2014-5-4 00:24 改成interface
 * @since 1.0
 */
public interface ImageType{

	/**
	 * JPG Joint Photograhic Experts Group（联合图像专家组）,JPEG的文件格式一般有两种文件扩展名：.jpg和.jpeg，这两种扩展名的实质是相同的 <code>{@value}</code>
	 */
	String	JPG		= "JPG";

	/**
	 * JPEG Joint Photograhic Experts Group（联合图像专家组）,JPEG的文件格式一般有两种文件扩展名：.jpg和.jpeg，这两种扩展名的实质是相同的 <code>{@value}</code>
	 */
	String	JPEG	= "JPEG";

	/**
	 * PNG (Portable Network Graphic Format，PNG) 流式网络图形格式 <code>{@value}</code>
	 */
	String	PNG		= "PNG";

	/**
	 * GIF Graphics Interchange format（图形交换格式） <code>{@value}</code>
	 */
	String	GIF		= "GIF";

	/**
	 * BMP Windows 位图<br>
	 * 为了保证照片图像的质量，请使用 PNG 、JPEG、TIFF 文件。<br>
	 * BMP 文件适用于 Windows 中的墙纸 <code>{@value}</code>
	 */
	String	BMP		= "BMP";
}