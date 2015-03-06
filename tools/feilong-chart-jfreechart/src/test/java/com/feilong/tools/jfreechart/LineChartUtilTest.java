package com.feilong.tools.jfreechart;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.junit.Test;

import com.feilong.tools.jfreechart.category.CategoryChartEntity;
import com.feilong.tools.jfreechart.category.LineChartUtil;

/**
 * <pre>
 * jfreechart应用_经典例题
 * 生成饼状图、
 * 生成单组柱状图、
 * 生成多组柱状图、
 * 生成堆积柱状图、
 * 生成折线图 
 * 实际取色的时候一定要16位的，这样比较准确
 * </pre>
 */
public class LineChartUtilTest{

    private ChartInfoEntity chartInfoEntity;

    /**
     * 生成折线图
     * 
     * @throws IOException
     */
    @Test
    public void makeOneLineAndShapeChart() throws IOException{
        String[] rowKeys = { "招工指数", "是是是", "sdasd", "jinxn" };
        LinkedHashMap<String, double[]> categoryAndValues = new LinkedHashMap<String, double[]>();
        categoryAndValues.put("1", new double[] { 100, 150, 88, 102 });
        categoryAndValues.put("2", new double[] { 99.74, 99.74 + 20, 66, 104 });
        categoryAndValues.put("3", new double[] { 99.74, 99.74 + 50, 88, 105 });
        categoryAndValues.put("4", new double[] { 99.74, 99.74 - 10, 44, 109 });
        categoryAndValues.put("5", new double[] { 99.87, 99.87 + 50, 99, 100 });
        categoryAndValues.put("6", new double[] { 98.20, 98.20 + 50, 66, 104 });
        String chartTitle = "招工指数";
        String categoryAxisLabel = "";
        String valueAxisLabel = "";
        String imageNameOrOutputStream = chartTitle + ".png";
        int width = 194 * 3;
        int height = 120 * 2;
        CategoryChartEntity categoryChartEntity = new CategoryChartEntity();
        categoryChartEntity.setRowKeys(rowKeys);
        categoryChartEntity.setChartTitle(chartTitle);
        categoryChartEntity.setCategoryAndValues(categoryAndValues);
        categoryChartEntity.setCategoryAxisLabel(categoryAxisLabel);
        categoryChartEntity.setValueAxisLabel(valueAxisLabel);

        LineChartUtil jfreeChartUtil = new LineChartUtil(categoryChartEntity);
        chartInfoEntity = new ChartInfoEntity();
        chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
        chartInfoEntity.setWidth(width);
        chartInfoEntity.setHeight(height);
        jfreeChartUtil.createImage(chartInfoEntity);
    }

    @Test
    public void makeOneLineAndShapeChart1() throws IOException{
        String[] rowKeys = { "基调网络压力并发测试 订单创建情况" };
        LinkedHashMap<String, double[]> categoryAndValues = new LinkedHashMap<String, double[]>();

        categoryAndValues.put("30", new double[] { 15 });
        categoryAndValues.put("31", new double[] { 21 });
        categoryAndValues.put("32", new double[] { 10 });
        categoryAndValues.put("33", new double[] { 12 });
        categoryAndValues.put("34", new double[] { 4 });
        categoryAndValues.put("35", new double[] { 173 });
        categoryAndValues.put("38", new double[] { 315 });
        categoryAndValues.put("39", new double[] { 68 });
        categoryAndValues.put("40", new double[] { 153 });
        categoryAndValues.put("41", new double[] { 200 });
        categoryAndValues.put("43", new double[] { 32 });
        categoryAndValues.put("44", new double[] { 16 });
        categoryAndValues.put("45", new double[] { 20 });
        categoryAndValues.put("46", new double[] { 9 });
        categoryAndValues.put("47", new double[] { 5 });
        categoryAndValues.put("48", new double[] { 21 });
        categoryAndValues.put("52", new double[] { 34 });
        categoryAndValues.put("53", new double[] { 22 });
        categoryAndValues.put("54", new double[] { 1 });

        String chartTitle = "基调网络压力并发测试 订单创建情况";
        String categoryAxisLabel = "";
        String valueAxisLabel = "";
        String imageNameOrOutputStream = chartTitle + ".png";

        int width = 194 * 3;
        int height = 120 * 2;

        CategoryChartEntity categoryChartEntity = new CategoryChartEntity();
        categoryChartEntity.setRowKeys(rowKeys);
        categoryChartEntity.setChartTitle(chartTitle);
        categoryChartEntity.setCategoryAndValues(categoryAndValues);
        categoryChartEntity.setCategoryAxisLabel(categoryAxisLabel);
        categoryChartEntity.setValueAxisLabel(valueAxisLabel);

        LineChartUtil jfreeChartUtil = new LineChartUtil(categoryChartEntity);
        chartInfoEntity = new ChartInfoEntity();
        chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
        chartInfoEntity.setWidth(width);
        chartInfoEntity.setHeight(height);
        jfreeChartUtil.createImage(chartInfoEntity);
    }
}