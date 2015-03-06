/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.tools.solr;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Sku Item for solr.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-18 下午8:01:33
 */
public class SkuItem implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8190409138133199386L;

    // ***********************form Sku***********************
    /** The id. */
    @Field
    private Long              id;

    /** Sku 编码. */
    @Field
    private String            code;

    /** sku name 名称. */
    @Field
    private String            name;

    /** detailedDescription 详细描述. */
    @Field
    private String            detailedDescription;

    /** showOrder 显示顺序. */
    @Field
    private String            showOrder;

    // ************form Property***********************
    /** sku color 颜色. */
    @Field
    private String            color;

    /** sku brand 品牌. */
    @Field
    private String            brand;

    /** The lables. */
    @Field
    private String            lables;

    // ***********form category************************************
    /** categoryCode 分类code. */
    @Field
    private String            categoryCode;

    /** categoryName 分类name. */
    @Field
    private String            categoryName;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public Long getId(){
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Gets the sku 编码.
     * 
     * @return the code
     */
    public String getCode(){
        return code;
    }

    /**
     * Sets the sku 编码.
     * 
     * @param code
     *            the code to set
     */
    public void setCode(String code){
        this.code = code;
    }

    /**
     * Gets the sku name 名称.
     * 
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the sku name 名称.
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the detailedDescription 详细描述.
     * 
     * @return the detailedDescription
     */
    public String getDetailedDescription(){
        return detailedDescription;
    }

    /**
     * Sets the detailedDescription 详细描述.
     * 
     * @param detailedDescription
     *            the detailedDescription to set
     */
    public void setDetailedDescription(String detailedDescription){
        this.detailedDescription = detailedDescription;
    }

    /**
     * Gets the showOrder 显示顺序.
     * 
     * @return the showOrder
     */
    public String getShowOrder(){
        return showOrder;
    }

    /**
     * Sets the showOrder 显示顺序.
     * 
     * @param showOrder
     *            the showOrder to set
     */
    public void setShowOrder(String showOrder){
        this.showOrder = showOrder;
    }

    /**
     * Gets the sku color 颜色.
     * 
     * @return the color
     */
    public String getColor(){
        return color;
    }

    /**
     * Sets the sku color 颜色.
     * 
     * @param color
     *            the color to set
     */
    public void setColor(String color){
        this.color = color;
    }

    /**
     * Gets the sku brand 品牌.
     * 
     * @return the brand
     */
    public String getBrand(){
        return brand;
    }

    /**
     * Sets the sku brand 品牌.
     * 
     * @param brand
     *            the brand to set
     */
    public void setBrand(String brand){
        this.brand = brand;
    }

    /**
     * Gets the lables.
     * 
     * @return the lables
     */
    public String getLables(){
        return lables;
    }

    /**
     * Sets the lables.
     * 
     * @param lables
     *            the lables to set
     */
    public void setLables(String lables){
        this.lables = lables;
    }

    /**
     * Gets the categoryCode 分类code.
     * 
     * @return the categoryCode
     */
    public String getCategoryCode(){
        return categoryCode;
    }

    /**
     * Sets the categoryCode 分类code.
     * 
     * @param categoryCode
     *            the categoryCode to set
     */
    public void setCategoryCode(String categoryCode){
        this.categoryCode = categoryCode;
    }

    /**
     * Gets the categoryName 分类name.
     * 
     * @return the categoryName
     */
    public String getCategoryName(){
        return categoryName;
    }

    /**
     * Sets the categoryName 分类name.
     * 
     * @param categoryName
     *            the categoryName to set
     */
    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
}
