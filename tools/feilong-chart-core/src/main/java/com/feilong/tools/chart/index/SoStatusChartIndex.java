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
package com.feilong.tools.chart.index;

/**
 * 指数.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 19, 2012 12:43:36 AM
 */
public class SoStatusChartIndex{

    /** 维度,比如按照分钟 维度,秒维度. */
    private String  dimension;

    /** 订单创建量. */
    private Integer createCount;

    /** 订单付款量. */
    private Integer paymentCount;

    /** 个人订单取消量. */
    private Integer personalCancelCount;

    /** 商城订单取消量. */
    private Integer storeCancelCount;

    /** 客服确认量. */
    private Integer confirmCount;

    /**
     * The Constructor.
     *
     * @param dimension
     *            the dimension
     * @param createCount
     *            the create count
     */
    public SoStatusChartIndex(String dimension, Integer createCount){
        super();
        this.dimension = dimension;
        this.createCount = createCount;
    }

    /**
     * The Constructor.
     *
     * @param dimension
     *            the dimension
     * @param createCount
     *            the create count
     * @param paymentCount
     *            the payment count
     */
    public SoStatusChartIndex(String dimension, Integer createCount, Integer paymentCount){
        super();
        this.dimension = dimension;
        this.createCount = createCount;
        this.paymentCount = paymentCount;
    }

    /**
     * The Constructor.
     *
     * @param dimension
     *            the dimension
     * @param createCount
     *            the create count
     * @param paymentCount
     *            the payment count
     * @param personalCancelCount
     *            the personal cancel count
     * @param storeCancelCount
     *            the store cancel count
     */
    public SoStatusChartIndex(String dimension, Integer createCount, Integer paymentCount, Integer personalCancelCount,
                    Integer storeCancelCount){
        super();
        this.dimension = dimension;
        this.createCount = createCount;
        this.paymentCount = paymentCount;
        this.personalCancelCount = personalCancelCount;
        this.storeCancelCount = storeCancelCount;
    }

    /**
     * 获得 维度,比如按照分钟 维度,秒维度.
     *
     * @return the dimension
     */
    public String getDimension(){
        return dimension;
    }

    /**
     * 设置 维度,比如按照分钟 维度,秒维度.
     *
     * @param dimension
     *            the dimension to set
     */
    public void setDimension(String dimension){
        this.dimension = dimension;
    }

    /**
     * 获得 订单创建量.
     *
     * @return the createCount
     */
    public Integer getCreateCount(){
        return createCount;
    }

    /**
     * 设置 订单创建量.
     *
     * @param createCount
     *            the createCount to set
     */
    public void setCreateCount(Integer createCount){
        this.createCount = createCount;
    }

    /**
     * 获得 订单付款量.
     *
     * @return the paymentCount
     */
    public Integer getPaymentCount(){
        return paymentCount;
    }

    /**
     * 设置 订单付款量.
     *
     * @param paymentCount
     *            the paymentCount to set
     */
    public void setPaymentCount(Integer paymentCount){
        this.paymentCount = paymentCount;
    }

    /**
     * 获得 个人订单取消量.
     *
     * @return the personalCancelCount
     */
    public Integer getPersonalCancelCount(){
        return personalCancelCount;
    }

    /**
     * 设置 个人订单取消量.
     *
     * @param personalCancelCount
     *            the personalCancelCount to set
     */
    public void setPersonalCancelCount(Integer personalCancelCount){
        this.personalCancelCount = personalCancelCount;
    }

    /**
     * 获得 商城订单取消量.
     *
     * @return the storeCancelCount
     */
    public Integer getStoreCancelCount(){
        return storeCancelCount;
    }

    /**
     * 设置 商城订单取消量.
     *
     * @param storeCancelCount
     *            the storeCancelCount to set
     */
    public void setStoreCancelCount(Integer storeCancelCount){
        this.storeCancelCount = storeCancelCount;
    }

    /**
     * 获得 客服确认量.
     *
     * @return the confirmCount
     */
    public Integer getConfirmCount(){
        return confirmCount;
    }

    /**
     * 设置 客服确认量.
     *
     * @param confirmCount
     *            the confirmCount to set
     */
    public void setConfirmCount(Integer confirmCount){
        this.confirmCount = confirmCount;
    }

}
