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
package com.feilong.commons.core.awt;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图片工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-11-30 下午03:24:45
 * @since 1.0
 */
public class ImageUtil{

	private static final Logger	log	= LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * @param outputStream
	 *            outputStream will close
	 * @param renderedImage
	 *            renderedImage
	 * @param formatName
	 *            formatName a String containg the informal name of the format.
	 */
	public static void write(OutputStream outputStream,RenderedImage renderedImage,String formatName){
		try{
			// JPEGImageEncoder jpegImageEncoder = JPEGCodec.createJPEGEncoder(outputStream);
			// jpegImageEncoder.encode(bufferedImage);
			ImageIO.write(renderedImage, formatName, outputStream);
			outputStream.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 从一个old图片,生成一个新的 new BufferedImage,<br>
	 * 该BufferedImage 的Width Height 和原图一样<br>
	 * 该BufferedImage操作不会影响原图
	 * 
	 * @param imagePath
	 *            图片
	 * @return new BufferedImage
	 */
	public static BufferedImage getNewBufferedImageFromFile(BufferedImage bufferedImage_old){
		int width = bufferedImage_old.getWidth();
		int height = bufferedImage_old.getHeight();
		BufferedImage bufferedImage_new = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		return bufferedImage_new;
	}

	/**
	 * 从一个old图片,生成一个新的 new BufferedImage,<br>
	 * 该BufferedImage 的Width Height 和原图一样<br>
	 * 该BufferedImage操作不会影响原图
	 * 
	 * @param imagePath
	 *            图片
	 * @return new BufferedImage
	 */
	public static BufferedImage getNewBufferedImageFromFile(String imagePath){
		BufferedImage bufferedImage = getBufferedImage(imagePath);
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		BufferedImage bufferedImage_new = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		return bufferedImage_new;
	}

	/**
	 * 基于原始图片,获得一个Graphics2D,大小和原图相等<br>
	 * 并 drawImage原始图
	 * 
	 * @param bufferedImage_new
	 * @param bufferedImage_old
	 * @return
	 */
	public static Graphics2D getGraphics2DByImage(BufferedImage bufferedImage_new,BufferedImage bufferedImage_old){
		Graphics2D graphics2D = bufferedImage_new.createGraphics();
		int width = bufferedImage_old.getWidth();
		int height = bufferedImage_old.getHeight();
		graphics2D.drawImage(bufferedImage_old, 0, 0, width, height, null);
		return graphics2D;
	}

	/**
	 * 获得image/BufferedImage 对象<br>
	 * BufferedImage 子类描述具有 可访问图像数据缓冲区的 Image
	 * 
	 * @param filePath
	 *            图像路径
	 * @return
	 */
	public static BufferedImage getBufferedImage(String filePath){
		File file = new File(filePath);
		try{
			BufferedImage bufferedImage = ImageIO.read(file);
			log.debug("image filePath:{}", filePath);
			log.debug("image width:{}", bufferedImage.getWidth());
			log.debug("image height:{}", bufferedImage.getHeight());
			// log.debug("getPropertyNames:{}", bufferedImage.getData());
			return bufferedImage;
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 是否是cmyk类型
	 * 
	 * @param filename
	 *            文件
	 * @return 是否是cmyk类型,是返回true
	 */
	public static boolean isCMYKType(String filename){
		boolean flag = false;
		BufferedImage bufferedImage = null;
		File file = new File(filename);
		try{
			ImageInputStream imageInputStream = ImageIO.createImageInputStream(file);
			bufferedImage = ImageIO.read(imageInputStream);
			// bufferedImage = ImageIO.read(file);
		}catch (IOException e){
			e.printStackTrace();
		}
		if (bufferedImage != null){
			ColorModel colorModel = bufferedImage.getColorModel();
			ColorSpace colorSpace = colorModel.getColorSpace();
			int colorSpaceType = colorSpace.getType();
			flag = colorSpaceType == ColorSpace.TYPE_CMYK;
		}
		return flag;
	}
}
