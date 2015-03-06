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

import org.jfree.chart.JFreeChart;

/**
 * The Class DefaultPieChartUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 16 01:22:13
 */
public class DefaultPieChartUtil extends BasePieChartUtil{

    /**
     * Instantiates a new default pie chart util.
     * 
     * @param freeChart
     *            the free chart
     */
    public DefaultPieChartUtil(JFreeChart freeChart){
        super(freeChart);
    }

    /**
     * Instantiates a new default pie chart util.
     * 
     * @param pieChartEntity
     *            the pie chart entity
     * @param pieType
     *            the pie type
     */
    public DefaultPieChartUtil(PieChartEntity pieChartEntity, PieType pieType){
        super(pieChartEntity, pieType);
    }
}
