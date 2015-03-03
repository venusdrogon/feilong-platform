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
package com.feilong.tools.jfreechart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.VerticalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.FontUtil;
import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.util.Validator;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 飞龙chart最base 的父类,是所有chart的 base 类<br>
 * 包含了 以下功能:
 * <ul>
 * <li>protected setChartCommonAttributes 设置freeChart公共属性</li>
 * <li>private setTextTitle 设置标题</li>
 * <li>private setLegendTitle 设置图例</li>
 * <li>protected createImage 创建图</li>
 * </ul>
 * 
 * <pre>
 * 
 * //createBoxAndWhiskerChart
 * //createBoxAndWhiskerChart
 * //createBubbleChart
 * //createCandlestickChart
 * //createGanttChart
 * //createHighLowChart
 * //createHistogram
 * //createMultiplePieChart
 * //				createMultiplePieChart3D
 * //				createPieChart
 * //				
 * //				createPolarChart
 * //				createRingChart
 * //				createScatterPlot
 * //createStackedXYAreaChart
 * //				createTimeSeriesChart
 * //				createWaferMapChart
 * //				createWaterfallChart
 * //				createWindPlot
 * //				createXYAreaChart
 * //				createXYBarChart
 * //				createXYLineChart
 * //				createXYStepAreaChart
 * // createXYStepChart
 * 
 * </pre>
 * 
 * .
 * 
 * @author 金鑫 2010-1-20 下午09:03:11
 */
public abstract class ChartUtil implements Chart{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ChartUtil.class);

	/** The jfree chart. */
	private JFreeChart			jfreeChart;

	/** The rendering hints. */
	private RenderingHints		renderingHints;

	/** The text title. */
	private TextTitle			textTitle;

	/** The legend title. */
	private LegendTitle			legendTitle;

	/**
	 * Instantiates a new chart util.
	 * 
	 * @param jfreeChart
	 *            the jfree chart
	 */
	public ChartUtil(JFreeChart jfreeChart){
		if (null == jfreeChart){
			throw new IllegalArgumentException("jFreeChart can't be null/empty!");
		}
		this.jfreeChart = jfreeChart;
		setDefaultJFreeChart();
		setDefaultRenderingHints();
		setDefaultTextTitle();
		setDefaultLegendTitle();
	}

	/**
	 * setFreeChart.
	 */
	private void setDefaultJFreeChart(){
		// String pathname =TestConstants.WATERMARK_PRESSIMG;
		// jFreeChart.setBackgroundImage(FeiLongImageUtil.getImage(pathname));
		// 设置背景图片对齐方式
		// jFreeChart.setBackgroundImageAlignment(Align.BOTTOM_RIGHT);
		// 设置背景图片的透明度
		// jFreeChart.setBackgroundImageAlpha(0.6f);
		// 定义整张图片的背景颜色: GradientPaint渐变色
		// Paint bgPaint = new GradientPaint(50, 0, new Color(0xD1DEEF), 50, 700, new Color(0xFFFFFF));
		// jFreeChart.setBackgroundPaint(bgPaint); // set chart bg
		// 设置背景颜色为白色
		// jFreeChart.setBackgroundPaint(Color.WHITE);
		// 关闭抗文字锯齿 设置标题平滑效果
		jfreeChart.setTextAntiAlias(false);// false = renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		// 设置内边框平滑效果 消除锯齿
		jfreeChart.setAntiAlias(true);
		RectangleInsets rectangleInsets = new RectangleInsets(2, 2, 16, 8);
		jfreeChart.setPadding(rectangleInsets);
		/************** Border *********************************/
		// 设置freeChart 最外面的边框可见
		jfreeChart.setBorderVisible(false);// set chart border, true in debug mode
		// jFreeChart.setBorderPaint(Color.GREEN);
	}

	/**
	 * 设置RenderingHints 呈现提示.
	 */
	private void setDefaultRenderingHints(){
		renderingHints = jfreeChart.getRenderingHints();
		/*************** set by freeChart.setTextAntiAlias(false); ***************************/
		// 使用的关闭抗锯齿后，字体尽量选择12到14号的宋体字,这样文字最清晰好看
		// 抗锯齿关闭 VALUE_TEXT_ANTIALIAS_OFF表示将文字的抗锯齿关闭,
		// renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}

	/**
	 * setTextTitle 设置标题.
	 */
	private void setDefaultTextTitle(){
		// 设置标题的字体
		textTitle = jfreeChart.getTitle();
		textTitle.setVisible(true);
		textTitle.setFont(FontUtil.getYaHeiPlainFont(22));
		textTitle.setPosition(RectangleEdge.TOP);
		textTitle.setMargin(12, 4, 2, 4);
		textTitle.setPadding(6, 12, 6, 12);
		int border = 0;
		textTitle.setBorder(border, border, border, border);
		// 标题的边框
		// BlockFrame blockFrame_title =new BlockBorder(1, 1, 1, 1, Color.RED);
		// textTitle.setFrame(blockFrame_title );
		// textTitle.setHeight(150);
		// textTitle.setWidth(400);
		// 背景颜色
		// textTitle.setBackgroundPaint(Color.RED);
		/****************** Alignment 对齐方式 *********************************/
		textTitle.setTextAlignment(HorizontalAlignment.CENTER);
		textTitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
		textTitle.setVerticalAlignment(VerticalAlignment.CENTER);
		// textTitle.setPaint(paint);
	}

	/**
	 * setLegendTitle 设置图例.
	 */
	private void setDefaultLegendTitle(){
		legendTitle = jfreeChart.getLegend();
		if (null != legendTitle){
			legendTitle.setVisible(true);
			// 设置图例文字的字体
			legendTitle.setItemFont(FontUtil.getYaHeiPlainFont(16));
			// 位置
			legendTitle.setPosition(RectangleEdge.BOTTOM);
			// 垂直对齐
			legendTitle.setVerticalAlignment(VerticalAlignment.CENTER);
			// 水平对齐
			legendTitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
			legendTitle.setBackgroundPaint(Color.white);
			// 边框
			legendTitle.setBorder(1, 1, 1, 1);
			RectangleInsets rectangleInsets = new RectangleInsets(6, 6, 6, 6);
			legendTitle.setItemLabelPadding(rectangleInsets);
			// *************************************************
			// legendTitle.setHeight(120);
			// legendTitle.setID("jinxin");
			// legendTitle.setItemPaint(Color.red);
			// legendTitle.setWidth(30);
		}
	}

	/**
	 * Gets the j free chart.
	 * 
	 * @return the jFreeChart
	 */
	public JFreeChart getJFreeChart(){
		return jfreeChart;
	}

	/**
	 * Gets the rendering hints.
	 * 
	 * @return the renderingHints
	 */
	public RenderingHints getRenderingHints(){
		return renderingHints;
	}

	/**
	 * Gets the text title.
	 * 
	 * @return the textTitle
	 */
	public TextTitle getTextTitle(){
		return textTitle;
	}

	/**
	 * Gets the legend title.
	 * 
	 * @return the legendTitle
	 */
	public LegendTitle getLegendTitle(){
		return legendTitle;
	}

	/**
	 * Creates the image.
	 * 
	 * @param chartInfoEntity
	 *            the chart info entity
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	public final void createImage(ChartInfoEntity chartInfoEntity) throws IOException{
		createImage(getJFreeChart(), chartInfoEntity);
	}

	/**
	 * 创建图片<br>
	 * 需要feiLongChartInfoEntity 以及freeChart.
	 * 
	 * @param freeChart
	 *            the free chart
	 * @param chartInfoEntity
	 *            the chart info entity
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static final void createImage(JFreeChart freeChart,ChartInfoEntity chartInfoEntity) throws IOException{
		if (Validator.isNullOrEmpty(chartInfoEntity)){
			throw new IllegalArgumentException("chartInfoEntity can't be null/empty!");
		}
		Object imageNameOrOutputStream = chartInfoEntity.getImageNameOrOutputStream();
		/** OutputStream */
		if (imageNameOrOutputStream instanceof OutputStream){
			writeChartAsPNG(freeChart, chartInfoEntity, (OutputStream) imageNameOrOutputStream);
		}
		/** String */
		else if (imageNameOrOutputStream instanceof String){
			String chartPath = chartInfoEntity.getChartPath();
			FileUtil.createDirectory(chartPath);

			String chartName = chartPath + "/" + (String) imageNameOrOutputStream;

			File file = new File(chartName);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			log.debug("[file.getAbsolutePath()]:{}", file.getAbsolutePath());

			writeChartAsPNG(freeChart, chartInfoEntity, fileOutputStream);

			fileOutputStream.close();
		}
	}

	/**
	 * Write chart as png.
	 *
	 * @param freeChart
	 *            the free chart
	 * @param chartInfoEntity
	 *            the chart info entity
	 * @param imageNameOrOutputStream
	 *            the image name or output stream
	 * @throws IOException
	 *             the IO exception
	 */
	private static void writeChartAsPNG(JFreeChart freeChart,ChartInfoEntity chartInfoEntity,OutputStream imageNameOrOutputStream)
					throws IOException{
		// 默认图片宽度
		int width = chartInfoEntity.getWidth();
		// 默认图片高度
		int height = chartInfoEntity.getHeight();
		// ServletUtilities.saveChartAsJPEG(chart, width, height, session)
		// 绘制热点地图
		// ChartUtilities.writeImageMap(writer, name, info, useOverLibForToolTips)
		// 将报表保存为png文件
		ChartUtilities.writeChartAsPNG(imageNameOrOutputStream, freeChart, width, height);
	}

	/**
	 * Write chart as pdf.
	 * 
	 * @param out
	 *            the out
	 * @param jFreeChart
	 *            the j free chart
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void writeChartAsPDF(OutputStream out,JFreeChart jFreeChart,int width,int height) throws IOException{
		Rectangle rectangle = new Rectangle(width, height);
		Document document = new Document(rectangle, 50, 50, 50, 50);
		try{
			PdfWriter pdfWriter = PdfWriter.getInstance(document, out);
			document.addAuthor("JFreeChart");
			document.addSubject("Demonstration");
			document.open();
			PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
			PdfTemplate pdfTemplate = pdfContentByte.createTemplate(width, height);
			Graphics2D graphics2d = pdfTemplate.createGraphics(width, height, new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);
			jFreeChart.draw(graphics2d, rectangle2d);
			graphics2d.dispose();
			pdfContentByte.addTemplate(pdfTemplate, 0, 0);
		}catch (DocumentException de){
			System.err.println(de.getMessage());
		}
		document.close();
	}
}