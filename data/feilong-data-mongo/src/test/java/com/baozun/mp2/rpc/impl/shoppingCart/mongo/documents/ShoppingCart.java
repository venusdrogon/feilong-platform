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
package com.baozun.mp2.rpc.impl.shoppingCart.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class ShoppingCart.
 *
 * @author moxun.zhang
 */
@Document(collection = "shoppingCart")
public class ShoppingCart{

    /** The id. */
    @Id()
    private String  id;

    /** The member id. */
    @Indexed
    private Long    memberId;

    /** The shop id. */
    private Long    shopId;

    /** The sku id. */
    @Indexed
    private Long    skuId;

    /** The quality. */
    //	@Field
    private Integer quantity;

    /**
     * 获得 the id.
     *
     * @return the id
     */
    public String getId(){
        return id;
    }

    /**
     * 设置 the id.
     *
     * @param id
     *            the id to set
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * 获得 the member id.
     *
     * @return the memberId
     */
    public Long getMemberId(){
        return memberId;
    }

    /**
     * 设置 the member id.
     *
     * @param memberId
     *            the memberId to set
     */
    public void setMemberId(Long memberId){
        this.memberId = memberId;
    }

    /**
     * 获得 the shop id.
     *
     * @return the shopId
     */
    public Long getShopId(){
        return shopId;
    }

    /**
     * 设置 the shop id.
     *
     * @param shopId
     *            the shopId to set
     */
    public void setShopId(Long shopId){
        this.shopId = shopId;
    }

    /**
     * 获得 the sku id.
     *
     * @return the skuId
     */
    public Long getSkuId(){
        return skuId;
    }

    /**
     * 设置 the sku id.
     *
     * @param skuId
     *            the skuId to set
     */
    public void setSkuId(Long skuId){
        this.skuId = skuId;
    }

    /**
     * 获得 the quality.
     *
     * @return the quantity
     */
    public Integer getQuantity(){
        return quantity;
    }

    /**
     * 设置 the quality.
     *
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

}
