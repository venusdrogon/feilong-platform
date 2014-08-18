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
package temple.awt;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.io.ImageType;

//97.区域截幕
/**
 * The Class ScreenDumpHelper.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 15:38:52
 */
public class ScreenDumpHelper{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ScreenDumpHelper.class);

	/** The screen area. */
	private Rectangle			screenArea;

	/**
	 * Gets the screen area.
	 * 
	 * @return the screen area
	 */
	public Rectangle getScreenArea(){
		return this.screenArea;
	}

	/**
	 * Sets the screen area.
	 * 
	 * @param screenArea
	 *            the new screen area
	 */
	public void setScreenArea(Rectangle screenArea){
		this.screenArea = screenArea;
	}

	/**
	 * 设置 screen area.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void setScreenArea(int x,int y,int width,int height){
		this.screenArea = new Rectangle(x, y, width, height);
	}

	/**
	 * 将 图像数据写到 输出流中去,方便再处理.
	 * 
	 * @param fileFormat
	 *            the file format
	 * @param output
	 *            the output
	 * @return true, if successful
	 */
	public boolean saveToOutputStream(String fileFormat,OutputStream output){
		try{
			Robot robot = new Robot();
			//创建包含从屏幕中读取的像素的图像。该图像不包括鼠标光标。 
			BufferedImage imgBuf = robot.createScreenCapture(this.getScreenArea());
			ImageIO.write(imgBuf, fileFormat, output);
		}catch (AWTException e){
			return false;
		}catch (IOException e){
			return false;
		}
		return true;
	}

	/**
	 * 抓取 指定区域的截图 到指定格式的文件中 -- 这个函数是核心,所有的都是围绕它来展开的 <br>
	 * 图片的编码并不是以后缀名来判断: 比如s.jpg 如果其采用png编码,那么这个图片就是png格式的
	 * 
	 * @param fileName
	 *            the file name
	 * @param fileFormat
	 *            the file format
	 * @return boolean
	 */
	public boolean saveToFile(String fileName,String fileFormat){
		if (fileName == null)
			fileName = "screenDump_" + (new Date()).getTime() + "." + fileFormat;
		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			this.saveToOutputStream(fileFormat, fos);
		}catch (FileNotFoundException e){
			log.info("非常规文件或不能创建抑或覆盖此文件: " + fileName);
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 抓取 指定 Rectangle 区域的截图 到指定格式的文件中.
	 * 
	 * @param fileName
	 *            the file name
	 * @param fileFormat
	 *            the file format
	 * @param screenArea
	 *            the screen area
	 * @return true, if successful
	 */
	public boolean saveToFile(String fileName,String fileFormat,@SuppressWarnings("hiding") Rectangle screenArea){
		this.setScreenArea(screenArea);
		return this.saveToFile(fileName, fileFormat);
	}

	/**
	 * 抓取 指定区域的截图 到指定格式的文件中.
	 * 
	 * @param fileName
	 *            the file name
	 * @param fileFormat
	 *            the file format
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return true, if successful
	 */
	public boolean saveToFile(String fileName,String fileFormat,int x,int y,int width,int height){
		this.setScreenArea(x, y, width, height);
		return this.saveToFile(fileName, fileFormat);
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args){
		ScreenDumpHelper screenDumpHelper = new ScreenDumpHelper();
		for (int i = 0; i < 5; i++){
			screenDumpHelper.saveToFile(
					"E:/" + DateUtil.date2String(new Date(), DatePattern.timestamp) + i + ".jpg",
					ImageType.PNG,
					i * 150,
					i * 150,
					400,
					300);
		}
	}
}