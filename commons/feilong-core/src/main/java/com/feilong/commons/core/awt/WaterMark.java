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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.ImageType;

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

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(WaterMark.class);

    /** Don't let anyone instantiate this class. */
    private WaterMark(){
        //AssertionError不是必须的。但它可以避免不小心在类的内部调用构造器。保证该类在任何情况下都不会被实例化。
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

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
    public static final void pressImage(String targetImg,String pressImg,OutputStream outputStream){
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
     *            x坐标（待解析）
     * @param y
     *            y坐标（待解析）
     * @param outputFile
     *            输出的文件
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static final void pressImage(String targetImg,String pressImg,int x,int y,String outputFile) throws IOException{
        OutputStream outputStream = FileUtil.getFileOutputStream(outputFile);
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
     *            x坐标（待解析）
     * @param y
     *            y坐标（待解析）
     * @param outputStream
     *            输出流(可以来自HttpServletReponse的输出)
     */
    public static final void pressImage(String targetImg,String pressImg,int x,int y,OutputStream outputStream){
        // 原始图片
        BufferedImage targetBufferedImage = ImageUtil.getBufferedImage(targetImg);
        // 基于原始图片产生一个新的BufferedImage
        BufferedImage newBufferedImage = ImageUtil.getNewBufferedImageFromFile(targetBufferedImage);

        // 基于原始图片,获得一个Graphics2D,大小和原图相等
        Graphics2D graphics2D = ImageUtil.getGraphics2DByImage(newBufferedImage, targetBufferedImage);

        // 水印
        BufferedImage waterMarkBufferedImage = ImageUtil.getBufferedImage(pressImg);
        int widthWaterMark = waterMarkBufferedImage.getWidth();
        int heightWaterMark = waterMarkBufferedImage.getHeight();
        int x2 = (newBufferedImage.getWidth() - widthWaterMark) - 25;// / 2
        int y2 = (newBufferedImage.getHeight() - heightWaterMark) - 25;// / 2

        //TODO 解析XY
        if (log.isDebugEnabled()){
            log.debug("x:{},y:{}", x, y);
        }

        // 设置透明
        // graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1f));// 开始
        graphics2D.drawImage(waterMarkBufferedImage, x2, y2, widthWaterMark, heightWaterMark, null);
        // graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); // 结束

        // 水印文件结束
        graphics2D.dispose();

        ImageUtil.write(outputStream, newBufferedImage, ImageType.PNG);
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
     *             Signals that an I/O exception has occurred.
     */
    public static void pressText(String targetImg,String pressText,Font font,Color color,int x,int y,String outputFile) throws IOException{
        OutputStream outputStream = FileUtil.getFileOutputStream(outputFile);
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
        BufferedImage targetBufferedImage = ImageUtil.getBufferedImage(targetImg);
        // 基于原始图片产生一个新的BufferedImage
        BufferedImage newBufferedImage = ImageUtil.getNewBufferedImageFromFile(targetBufferedImage);

        // 基于原始图片,获得一个Graphics2D,大小和原图相等
        Graphics2D graphics2D = ImageUtil.getGraphics2DByImage(newBufferedImage, targetBufferedImage);
        int width = newBufferedImage.getWidth();
        int height = newBufferedImage.getHeight();

        // 只有图片的宽或高大于200的时候才添加水印（小图片不添加）
        // if (width > 200 || height > 200){
        graphics2D.setColor(color);
        graphics2D.setFont(font);

        int fontSize = font.getSize();
        int x2 = width - fontSize - x;
        int y2 = height - fontSize / 2 - y;
        graphics2D.drawString(pressText, x2, y2);
        graphics2D.dispose();

        ImageUtil.write(outputStream, newBufferedImage, ImageType.PNG);
    }
}