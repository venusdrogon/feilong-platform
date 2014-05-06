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
package com.feilong.commons.core.awt.toolkit;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 飞龙截屏操作
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-30 下午01:33:59
 * @since 1.0
 */
public final class ScreenShotUtil{

	private final static Logger	log	= LoggerFactory.getLogger(ScreenShotUtil.class);

	/**
	 * 全屏幕截取
	 * 
	 * @param fileName
	 *            文件名称
	 * @param formatName
	 *            图片格式
	 */
	public static void screenshot(String fileName,String formatName){
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) dimension.getWidth();
		int height = (int) dimension.getHeight();
		screenshot(fileName, formatName, 0, 0, width, height);
	}

	/**
	 * 区域截图
	 * 
	 * @param fileName
	 *            文件名称
	 * @param formatName
	 *            图片格式
	 * @param x
	 *            左上角的 X 坐标
	 * @param y
	 *            左上角的 Y 坐标
	 * @param width
	 *            高度
	 * @param height
	 *            宽度
	 */
	public static void screenshot(String fileName,String formatName,int x,int y,int width,int height){
		try{
			Robot robot = new Robot();
			Rectangle rectangle = new Rectangle(x, y, width, height);
			BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
			File file = new File(fileName);
			ImageIO.write(bufferedImage, formatName, file);
		}catch (HeadlessException e){
			log.debug(e.getMessage());
		}catch (AWTException e){
			log.debug(e.getMessage());
		}catch (IOException e){
			log.debug(e.getMessage());
		}
	}
}
