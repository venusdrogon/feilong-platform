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
package com.feilong.tools.jfreechart.pie;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;

import com.feilong.commons.core.awt.FontUtil;
import com.feilong.tools.jfreechart.ChartUtil;

/**
 * pie 饼图.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 3 02:13:44
 */
public abstract class BasePieChartUtil extends ChartUtil{

    /** The pie plot. */
    protected PiePlot piePlot;

    /**
     * Instantiates a new base pie chart util.
     * 
     * @param jFreeChart
     *            the j free chart
     */
    public BasePieChartUtil(JFreeChart jFreeChart){
        super(jFreeChart);
        setDefaultPiePlotAttribute();
    }

    /**
     * Instantiates a new base pie chart util.
     * 
     * @param pieChartEntity
     *            the pie chart entity
     * @param pieType
     *            the pie type
     */
    public BasePieChartUtil(PieChartEntity pieChartEntity, PieType pieType){
        this(PieChartFactory.createJFreeChart(pieChartEntity, pieType));
    }

    /**
     * 设置 default pie plot attribute.
     */
    private void setDefaultPiePlotAttribute(){
        piePlot = (PiePlot) getJFreeChart().getPlot();
        piePlot.setLabelFont(new Font("SansSerif", Font.TRUETYPE_FONT, 12));
        // 设置无数据时的信息
        piePlot.setNoDataMessage("无对应的数据，请重新查询。");
        // 设置无数据时的信息显示颜色
        piePlot.setNoDataMessagePaint(Color.red);
        // 设置无数据时的信息字体
        piePlot.setNoDataMessageFont(FontUtil.KAITI_PLAIN_16);
        // piePlot3D背景颜色
        piePlot.setBackgroundPaint(Color.WHITE);
        // 边框
        piePlot.setOutlineVisible(true);
        // 指定图片的透明度(0.0-1.0)
        piePlot.setForegroundAlpha(0.65f);
        // 图片中显示百分比:默认方式
        // 指定饼图轮廓线的颜色
        // plot.setBaseSectionOutlinePaint(Color.BLACK);
        // plot.setBaseSectionPaint(Color.BLACK);
        // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat(
                        "0.00%")));
        // 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
        piePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
        // 指定显示的饼图上圆形(false)还椭圆形(true)
        piePlot.setCircular(false, true);
        // 设置第一个 饼块section 的开始位置，默认是12点钟方向
        piePlot.setStartAngle(90);
    }

    /**
     * Gets the pie plot.
     * 
     * @return the piePlot
     */
    public PiePlot getPiePlot(){
        return piePlot;
    }
}
