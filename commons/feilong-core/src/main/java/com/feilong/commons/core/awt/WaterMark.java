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
package com.feilong.commons.core.awt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import com.feilong.commons.core.enumeration.ImageType;
import com.feilong.commons.core.io.IOUtil;

/**
 * 水印.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-30 下午02:55:23
 * @since 1.0.0
 * @deprecated 水印技术 建议使用第三方工具或者nginx配置
 */
@Deprecated
public final class WaterMark{

	/**
	 * 默认 水印在右下角.
	 * 
	 * @param targetImg
	 *            the target img
	 * @param pressImg
	 *            the press img
	 * @param outputStream
	 *            the output stream
	 */
	public final static void pressImage(String targetImg,String pressImg,OutputStream outputStream){
		pressImage(targetImg, pressImg, 0, 0, outputStream);
	}

	/**
	 * 把图片印刷到图片上(水印)<br>
	 * 由于是动态从流中加水印，因此不会修改服务器上原图片.
	 * 
	 * @param targetImg
	 *            目标文件
	 * @param pressImg
	 *            水印文件
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param outputFile
	 *            输出的文件
	 * @throws IOException
	 */
	public final static void pressImage(String targetImg,String pressImg,int x,int y,String outputFile) throws IOException{
		OutputStream outputStream = IOUtil.getFileOutputStream(outputFile);
		pressImage(targetImg, pressImg, x, y, outputStream);
	}

	/**
	 * 把图片印刷到图片上(水印)<br>
	 * 由于是动态从流中加水印，因此不会修改服务器上原图片.
	 * 
	 * @param targetImg
	 *            目标文件
	 * @param pressImg
	 *            水印文件
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param outputStream
	 *            输出流(可以来自HttpServletReponse的输出)
	 */
	public final static void pressImage(String targetImg,String pressImg,int x,int y,OutputStream outputStream){
		// 原始图片
		BufferedImage image_target = ImageUtil.getBufferedImage(targetImg);
		// 基于原始图片产生一个新的BufferedImage
		BufferedImage bufferedImage_new = ImageUtil.getNewBufferedImageFromFile(image_target);
		// 基于原始图片,获得一个Graphics2D,大小和原图相等
		Graphics2D graphics2D = ImageUtil.getGraphics2DByImage(bufferedImage_new, image_target);
		/**********************************************************************/
		// 水印
		BufferedImage image_press = ImageUtil.getBufferedImage(pressImg);
		int width_biao = image_press.getWidth();
		int height_biao = image_press.getHeight();
		int x2 = (bufferedImage_new.getWidth() - width_biao) - 25;// / 2
		int y2 = (bufferedImage_new.getHeight() - height_biao) - 25;// / 2
		// 设置透明
		// graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1f));// 开始
		graphics2D.drawImage(image_press, x2, y2, width_biao, height_biao, null);
		// graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); // 结束
		// 水印文件结束
		graphics2D.dispose();
		/*************************************************************/
		ImageUtil.write(outputStream, bufferedImage_new, ImageType.PNG);
	}

	/**
	 * 文字水印图片 (水印).
	 * 
	 * @param targetImg
	 *            目标图片
	 * @param pressText
	 *            文字
	 * @param font
	 *            字体
	 * @param color
	 *            字体颜色
	 * @param x
	 *            偏移量(从右下角算起)
	 * @param y
	 *            偏移量(从右下角算起)
	 * @param outputFile
	 *            输出文件
	 * @throws IOException
	 */
	public static void pressText(String targetImg,String pressText,Font font,Color color,int x,int y,String outputFile) throws IOException{
		OutputStream outputStream = IOUtil.getFileOutputStream(outputFile);
		pressText(targetImg, pressText, font, color, x, y, outputStream);
	}

	/**
	 * 文字水印图片 (水印).
	 * 
	 * @param targetImg
	 *            目标图片
	 * @param pressText
	 *            文字
	 * @param font
	 *            字体
	 * @param color
	 *            字体颜色
	 * @param x
	 *            偏移量(从右下角算起)
	 * @param y
	 *            偏移量(从右下角算起)
	 * @param outputStream
	 *            输出流(可以来自HttpServletReponse的输出)
	 */
	public static void pressText(String targetImg,String pressText,Font font,Color color,int x,int y,OutputStream outputStream){
		// 原始图片
		BufferedImage image_target = ImageUtil.getBufferedImage(targetImg);
		// 基于原始图片产生一个新的BufferedImage
		BufferedImage bufferedImage_new = ImageUtil.getNewBufferedImageFromFile(image_target);
		// 基于原始图片,获得一个Graphics2D,大小和原图相等
		Graphics2D graphics2D = ImageUtil.getGraphics2DByImage(bufferedImage_new, image_target);
		int width = bufferedImage_new.getWidth();
		int height = bufferedImage_new.getHeight();
		// 只有图片的宽或高大于200的时候才添加水印（小图片不添加）
		// if (width > 200 || height > 200){
		graphics2D.setColor(color);
		graphics2D.setFont(font);
		int fontSize = font.getSize();
		int x2 = width - fontSize - x;
		int y2 = height - fontSize / 2 - y;
		graphics2D.drawString(pressText, x2, y2);
		graphics2D.dispose();
		/*************************************************************/
		ImageUtil.write(outputStream, bufferedImage_new, ImageType.PNG);
	}
}