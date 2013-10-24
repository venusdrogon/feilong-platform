package elsepackage.temple.awt;

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

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.ImageType;

//97.区域截幕
public class ScreenDumpHelper{

	private Rectangle	screenArea;

	public Rectangle getScreenArea(){
		return this.screenArea;
	}

	public void setScreenArea(Rectangle screenArea){
		this.screenArea = screenArea;
	}

	public void setScreenArea(int x,int y,int width,int height){
		this.screenArea = new Rectangle(x, y, width, height);
	}

	/**
	 * 将 图像数据写到 输出流中去,方便再处理
	 * 
	 * @param fileFormat
	 * @param output
	 * @return
	 */
	public boolean saveToOutputStream(String fileFormat,OutputStream output){
		try{
			BufferedImage imgBuf = (new Robot()).createScreenCapture(this.getScreenArea());
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
	 * @param fileFormat
	 * @return boolean
	 */
	public boolean saveToFile(String fileName,String fileFormat){
		if (fileName == null)
			fileName = "screenDump_" + (new Date()).getTime() + "." + fileFormat;
		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			this.saveToOutputStream(fileFormat, fos);
		}catch (FileNotFoundException e){
			System.out.println("非常规文件或不能创建抑或覆盖此文件: " + fileName);
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 抓取 指定 Rectangle 区域的截图 到指定格式的文件中
	 * 
	 * @param fileName
	 * @param fileFormat
	 * @param screenArea
	 * @return
	 */
	public boolean saveToFile(String fileName,String fileFormat,Rectangle screenArea){
		this.setScreenArea(screenArea);
		return this.saveToFile(fileName, fileFormat);
	}

	/**
	 * 抓取 指定区域的截图 到指定格式的文件中
	 * 
	 * @param fileName
	 * @param fileFormat
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public boolean saveToFile(String fileName,String fileFormat,int x,int y,int width,int height){
		this.setScreenArea(x, y, width, height);
		return this.saveToFile(fileName, fileFormat);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args){
		for (int i = 0; i < 5; i++)
			new ScreenDumpHelper().saveToFile(
					"E:/" + DateUtil.date2String(new Date(), DatePattern.timestamp) + i + ".jpg",
					ImageType.PNG,
					i * 150,
					i * 150,
					400,
					300);
	}
}