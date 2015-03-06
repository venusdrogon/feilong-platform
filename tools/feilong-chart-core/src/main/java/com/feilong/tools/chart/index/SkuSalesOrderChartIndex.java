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
 * 商品下单指数.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 14, 2013 11:23:20 AM
 */
public final class SkuSalesOrderChartIndex{

    /** 商品code. */
    private String  skuCode;

    /** 总库存. */
    private Integer totalInventoryCount;

    /** 下单数. */
    private Integer createSalesOrderCount;

    /** 个人取消数量. */
    private Integer personalCancelCount;

    /** 商城取消数量. */
    private Integer storeCancelCount;

    /**
     * Instantiates a new sku sales order chart index.
     * 
     * @param skuCode
     *            the sku code
     * @param totalInventoryCount
     *            the total inventory count
     * @param createSalesOrderCount
     *            the create sales order count
     * @param personalCancelCount
     *            the personal cancel count
     * @param storeCancelCount
     *            the store cancel count
     */
    public SkuSalesOrderChartIndex(String skuCode, Integer totalInventoryCount, Integer createSalesOrderCount, Integer personalCancelCount,
                    Integer storeCancelCount){
        super();
        this.skuCode = skuCode;
        this.createSalesOrderCount = createSalesOrderCount;
        this.personalCancelCount = personalCancelCount;
        this.storeCancelCount = storeCancelCount;
        this.totalInventoryCount = totalInventoryCount;
    }

    /**
     * Gets the 商品code.
     * 
     * @return the skuCode
     */
    public String getSkuCode(){
        return skuCode;
    }

    /**
     * Sets the 商品code.
     * 
     * @param skuCode
     *            the skuCode to set
     */
    public void setSkuCode(String skuCode){
        this.skuCode = skuCode;
    }

    /**
     * Gets the 下单数.
     * 
     * @return the createSalesOrderCount
     */
    public Integer getCreateSalesOrderCount(){
        return createSalesOrderCount;
    }

    /**
     * Sets the 下单数.
     * 
     * @param createSalesOrderCount
     *            the createSalesOrderCount to set
     */
    public void setCreateSalesOrderCount(Integer createSalesOrderCount){
        this.createSalesOrderCount = createSalesOrderCount;
    }

    /**
     * Gets the 个人取消数量.
     * 
     * @return the personalCancelCount
     */
    public Integer getPersonalCancelCount(){
        return personalCancelCount;
    }

    /**
     * Sets the 个人取消数量.
     * 
     * @param personalCancelCount
     *            the personalCancelCount to set
     */
    public void setPersonalCancelCount(Integer personalCancelCount){
        this.personalCancelCount = personalCancelCount;
    }

    /**
     * Gets the 商城取消数量.
     * 
     * @return the storeCancelCount
     */
    public Integer getStoreCancelCount(){
        return storeCancelCount;
    }

    /**
     * Sets the 商城取消数量.
     * 
     * @param storeCancelCount
     *            the storeCancelCount to set
     */
    public void setStoreCancelCount(Integer storeCancelCount){
        this.storeCancelCount = storeCancelCount;
    }

    /**
     * Gets the 总库存.
     * 
     * @return the totalInventoryCount
     */
    public Integer getTotalInventoryCount(){
        return totalInventoryCount;
    }

    /**
     * Sets the 总库存.
     * 
     * @param totalInventoryCount
     *            the totalInventoryCount to set
     */
    public void setTotalInventoryCount(Integer totalInventoryCount){
        this.totalInventoryCount = totalInventoryCount;
    }

}
