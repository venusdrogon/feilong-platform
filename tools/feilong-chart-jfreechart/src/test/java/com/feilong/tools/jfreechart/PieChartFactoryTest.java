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

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.feilong.tools.jfreechart.pie.PieChartEntity;
import com.feilong.tools.jfreechart.pie.PieChartFactory;
import com.feilong.tools.jfreechart.pie.PieType;

/**
 * The Class PieChartFactoryTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 16 01:20:44
 */
public class PieChartFactoryTest{

    /**
     * Creates the image.
     *
     * @throws IOException
     *             the IO exception
     */
    @Test
    public void createImage() throws IOException{
        PieType pieType = PieType.RING;
        Map<String, Number> keyAndDataMap = new LinkedHashMap<String, Number>();
        keyAndDataMap.put("失败率", 50);
        keyAndDataMap.put("成功率", 250);
        String chartTitle = "饼状图" + pieType;
        String imageNameOrOutputStream = chartTitle + ".png";
        int width = 800;
        int height = 600;
        PieChartEntity chartEntiry = new PieChartEntity();
        chartEntiry.setChartTitle(chartTitle);
        chartEntiry.setKeyAndDataMap(keyAndDataMap);
        ChartInfoEntity chartInfoEntity = new ChartInfoEntity();
        chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
        chartInfoEntity.setWidth(width);
        chartInfoEntity.setHeight(height);
        ChartUtil jfreeChartUtil = PieChartFactory.createChartUtil(chartEntiry, pieType);
        //jfreeChartUtil.getPiePlot3D().setBackgroundPaint(Color.black);
        jfreeChartUtil.createImage(chartInfoEntity);
    }
}
