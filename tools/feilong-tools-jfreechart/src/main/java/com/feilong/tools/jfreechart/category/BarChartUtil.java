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
package com.feilong.tools.jfreechart.category;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.FontUtil;

/**
 * 柱形图
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-31 下午05:09:42
 */
public final class BarChartUtil extends CategoryChartUtil{

	private static final Logger	log	= LoggerFactory.getLogger(BarChartUtil.class);

	private BarRenderer			barRenderer;

	public BarChartUtil(CategoryChartEntity categoryChartEntity){
		super(categoryChartEntity, CategoryChartType.bar);
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.xy.FeiLongBaseXYChartEntity#setDefaultCategoryItemRenderer()
	 */
	protected void setDefaultCategoryItemRenderer(){
		barRenderer = (BarRenderer) categoryPlot.getRenderer();
		// Paint[] arrayOfPaint = createPaint();
		// barRenderer = new CustomBarRenderer(arrayOfPaint);
		/************************* Base *********************************************/
		GradientPaint paint = new GradientPaint(0.0f, 0.0f, Color.orange, 0.0f, 0.0f, Color.yellow);
		// StandardBarPainter painter = new StandardBarPainter();
		barRenderer.setBasePaint(paint);
		// 设置柱子边框颜色
		// barRenderer.setBaseOutlinePaint(freeChartEntity.getPaint_barRenderer_BaseOutline());
		// 设置鼠标提示信息
		//barRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		/************************* BaseItemLabel *********************************************/
		// 显示每个柱的数值，并修改该数值的字体属性
		barRenderer.setBaseItemLabelsVisible(true);
		barRenderer.setBaseItemLabelFont(FontUtil.getFont_verdana_Plain(18));
		barRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		/************************* BaseSeries *********************************************/
		barRenderer.setBaseSeriesVisible(true);
		barRenderer.setBaseSeriesVisibleInLegend(true);
		/*****************************************************************************************/
		barRenderer.setIncludeBaseInRange(false);
		StandardGradientPaintTransformer transformer = new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL);
		barRenderer.setGradientPaintTransformer(transformer);
		/***************************** Width *****************************************************/
		// 设置柱子最大宽度
		barRenderer.setMaximumBarWidth(0.08);
		// 设置柱子最小高度
		barRenderer.setMinimumBarLength(0.2);
		// 设置每个地区所包含的平行柱的之间距离,组内柱子间隔为组宽的10%
		barRenderer.setItemMargin(0);
		// 设置柱子边框可见
		barRenderer.setDrawBarOutline(false);
		/***************************** Series *************************************************************/
		//某组 是否在 legend里面显示
		//barRenderer.setSeriesVisibleInLegend(1, true);
		// 设置柱的颜色
		// 渐变颜色
		//GradientPaint gradientPaint = new GradientPaint(0.0f, 8.0f, Color.LIGHT_GRAY, 1.0f, 1.0f, new Color(0, 0, 64));
		//GradientPaint gradientPaint1 = new GradientPaint(1.0f, 9.0f, Color.GREEN, 1.0f, 1.0f, new Color(0, 64, 0));
		//barRenderer.setSeriesPaint(0, gradientPaint);
		//barRenderer.setSeriesPaint(1, gradientPaint1);
		//barRenderer.setSeriesItemLabelFont(1, new Font("黑体", Font.BOLD, 12));// 12号黑体加粗
		//barRenderer.setSeriesItemLabelsVisible(0, true);
		//barRenderer.setSeriesItemLabelPaint(2, Color.RED);// 字体为黑色
		//barRenderer.setShadowPaint(Color.red);
		// barRenderer.setSeriesOutlinePaint(0, Color.BLACK);// 边框为黑色
		// barRenderer.setSeriesOutlinePaint(1, Color.BLUE);// 边框为黑色
		// barRenderer.setSeriesOutlinePaint(2, Color.RED);// 边框为黑色
	}

	class CustomBarRenderer extends BarRenderer{

		private static final long	serialVersionUID	= 1L;

		private Paint[]				paint;

		public CustomBarRenderer(Paint[] paramArrayOfPaint){
			this.paint = paramArrayOfPaint;
		}

		/*
		 * (non-Javadoc)
		 * @see org.jfree.chart.renderer.AbstractRenderer#getItemPaint(int, int)
		 */
		public Paint getItemPaint(int paramInt1,int paramInt2){
			log.debug("paramInt1:{},paramInt2:{}", paramInt1, paramInt2);
			return this.paint[paramInt1];
			//return this.paint[(paramInt2 % this.paint.length)];
		}
	}

	protected static Paint[] createPaint(){
		Paint[] arrayOfPaint = new Paint[5];
		arrayOfPaint[0] = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.white);
		arrayOfPaint[1] = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, Color.white);
		arrayOfPaint[2] = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.white);
		arrayOfPaint[3] = new GradientPaint(0.0F, 0.0F, Color.orange, 0.0F, 0.0F, Color.white);
		arrayOfPaint[4] = new GradientPaint(0.0F, 0.0F, Color.magenta, 0.0F, 0.0F, Color.white);
		return arrayOfPaint;
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultCategoryPlotAttributes(org.jfree.chart.plot.CategoryPlot)
	 */
	@Override
	protected void setChildDefaultCategoryPlotAttributes(CategoryPlot categoryPlot){
	// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultCategoryAxisAttributes(org.jfree.chart.axis.CategoryAxis)
	 */
	@Override
	protected void setChildDefaultCategoryAxisAttributes(CategoryAxis categoryAxis){
	// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultNumberAxisAttributes(org.jfree.chart.axis.NumberAxis)
	 */
	protected void setChildDefaultNumberAxisAttributes(NumberAxis numberAxis){
		numberAxis.setAutoRangeIncludesZero(true);
	}

	/**
	 * @return the barRenderer
	 */
	public BarRenderer getBarRenderer(){
		return barRenderer;
	}
}
