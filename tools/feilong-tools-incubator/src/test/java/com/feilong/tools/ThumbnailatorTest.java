package com.feilong.tools;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.TestConstants;
import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.awt.ImageUtil;
import com.feilong.commons.core.lang.MathUtil;
import com.feilong.commons.core.util.RandomUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 10, 2013 10:06:45 AM
 */
@SuppressWarnings("all")
public class ThumbnailatorTest{

	private static final Logger	log	= LoggerFactory.getLogger(ThumbnailatorTest.class);

	@Test
	public void watermark() throws IOException{
		String backimage = "E:\\DataCommon\\test\\background.png";
		String watermark = "";
		// TestConstants.WATERMARK_PRESSIMG;

		Point a = new Point(234, 56);
		Point b = new Point(363, 132);
		Point c = new Point(118, 248);
		Point d = new Point(245, 326);
		watermark = "E:\\DataCommon\\test\\1.png";
		BufferedImage bufferedImage = watermark(backimage, watermark, a, b, c, d);

		a = new Point(371, 229);
		b = new Point(429, 378);
		c = new Point(141, 315);
		d = new Point(197, 467);
		watermark = "E:\\DataCommon\\test\\2.png";
		bufferedImage = watermark(bufferedImage, watermark, a, b, c, d);

		a = new Point(493, 342);
		b = new Point(592, 219);
		c = new Point(704, 507);
		d = new Point(799, 384);
		watermark = "E:\\DataCommon\\test\\3.png";
		bufferedImage = watermark(bufferedImage, watermark, a, b, c, d);

		a = new Point(639, 348);
		b = new Point(747, 417);
		c = new Point(543, 501);
		d = new Point(652, 570);
		watermark = "E:\\DataCommon\\test\\4.png";
		bufferedImage = watermark(bufferedImage, watermark, a, b, c, d);

		String outputfile = "E:\\DataCommon\\test\\product\\" + System.currentTimeMillis() + " 1 2" + RandomUtil.createRandom(10) + ".png";

		File file = new File(outputfile);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()){
			if (parent.mkdirs() == false){
				log.error("File '" + file + "' could not be created");
			}
		}

		Thumbnails.of(bufferedImage).scale(1).toFile(outputfile);

		// Command.execFileOrDirectoryFocus(outputfile);

		DesktopUtil.open(outputfile);

	}

	/**
	 * @param backgroundImage
	 * @param watermark
	 * @param outputfile
	 * @throws IOException
	 */
	public BufferedImage watermark(String backgroundImage,String watermark,Point a,Point b,Point c,Point d) throws IOException{
		BufferedImage backgroundBufferedImage = ImageUtil.getBufferedImage(backgroundImage);
		return watermark(backgroundBufferedImage, watermark, a, b, c, d);
	}

	/**
	 * @param backimage
	 * @param watermark
	 * @param outputfile
	 * @throws IOException
	 */
	public BufferedImage watermark(BufferedImage backgroundBufferedImage,String watermark,Point a,Point b,Point c,Point d)
					throws IOException{
		Float opacity = 1.0f;

		// 是否是顺时针旋转
		boolean isClockwise = a.x > c.x;

		double amountOfRotation = 0d;

		int x = 0;
		int y = 0;

		// 顺时针计算方式
		if (isClockwise){
			x = c.x;
			y = a.y;

			// 弧度
			// 以弧度为单位计算并返回点 y /x 的角度，
			// 该角度从圆的 x 轴（0 点在其上，0 表示圆心）沿逆时针方向测量。返回值介于正 pi 和负 pi 之间。

			// angel是一个弧度值,也可以表示相对直角三角形对角的角
			double angle = Math.atan2(d.y - b.y, b.x - d.x);
			// 换算成角度 旋转量
			amountOfRotation = (90 - MathUtil.radian2Degree(angle));

		}
		// 逆时针计算方式
		else{

			x = a.x;
			y = b.y;

			// 弧度
			// 以弧度为单位计算并返回点 y /x 的角度，
			// 该角度从圆的 x 轴（0 点在其上，0 表示圆心）沿逆时针方向测量。返回值介于正 pi 和负 pi 之间。

			// angel是一个弧度值,也可以表示相对直角三角形对角的角
			double angle = Math.atan2(c.y - a.y, c.x - a.x);
			// 换算成角度 旋转量
			amountOfRotation = -(90 - MathUtil.radian2Degree(angle));
		}

		File watermarkFile = new File(watermark);

		// 水印 转成 width /height 图片
		Double width = a.distance(b);
		Double height = a.distance(c);

		log.debug("width:{}", width);
		log.debug("height:{}", height);

		// 转换水印图
		BufferedImage watermarkBufferedImage = Thumbnails.of(watermarkFile)//
						.forceSize(width.intValue(), height.intValue())//
						.rotate(amountOfRotation)// 设置的转动量
						.asBufferedImage();

		// 绘制水印图到背景图
		Point point = new Point(x, y);
		MyPositions myPositions = new MyPositions(point);

		BufferedImage bufferedImage = Thumbnails.of(backgroundBufferedImage)//
						.watermark(myPositions, watermarkBufferedImage, opacity)//
						.outputQuality(opacity)//
						.scale(1)//
						.asBufferedImage();

		return bufferedImage;
	}
}
