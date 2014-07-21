/**
 * Copyright (c) 2013 Jumbomart All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Jumbomart.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jumbo.
 *
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.feilong.framework.bind.parse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.feilong.framework.bind.parse.varcommand.VarCommand;

/**
 * Item
 * 
 * @author: fan.chen1
 * @date: 2013年11月18日
 **/
public class ItemDto implements Serializable,VarCommand{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8216660496058800809L;

	private Long				id;

	private String				title;

	private Long				categoryId;

	private String				type;

	private BigDecimal			price;

	private String				description;

	private String				properties;

	private String				propertyNames;

	private String				propertyAlias;

	private String				sellPoint;

	private String				nickName;

	private String				postageId;

	private boolean				timing;

	private Date				listTime;

	private Date				delistTime;

	private Long				templateId;

	private String				imageUrl;

	private boolean				supportPOD;

	/** pod费用 **/
	private BigDecimal			podFee;

	private Date				created;

	private String				cTime;

	private Date				modified;

	private String				status;

	private Long				shopId;

	private Long				sellerId;

	private List<Long>			shopCategoryId		= new ArrayList<Long>();

	private BigDecimal			listPrice;

	private String				state;

	private String				city;

	private String				district;

	/**
	 * fake variable, only for jackson to call the getAllowedActions method
	 */
	private Set<String>			allowedActions		= new HashSet<String>();

	private Double				weight;

	//    private String volume;

	private String				startime;

	private String				endtime;

	private String				shopname;

	//商品打的标签
	private String				attachTags;

	//isHaveTag -->allItem 查所有商品   isHaveTag --> tag 查打了标签的商品    isHaveTag -->untag 查没有打标签的商品
	private String				isHaveTag;

	/** 不在表中出现的数据 start **/
	/**
	 * 库存，库存数据现在是存在于SKU中，这里是汇总库存，也就是查出该商品对应的SKU，累加所有库存
	 */
	private int					inStock;

	/** seller（卖家承担），buyer(买家承担） */
	private String				freightPayer;

	/** 快递费用 **/
	private Integer				expressFee;

	/** 长 */
	private Integer				length;

	/** 宽 */
	private Integer				width;

	/** 高 */
	private Integer				height;

	/** 用户返点积分 */
	private Integer				buyerObtainPoint;

	private String				oldSku;

	/** 是否修改敏感信息 */
	private boolean				mustCheck;

	private String				excelPath;

	private int					excelIndex;

	/**
	 * @return the id
	 */
	public Long getId(){
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id){
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * @return the categoryId
	 */
	public Long getCategoryId(){
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Long categoryId){
		this.categoryId = categoryId;
	}

	/**
	 * @return the type
	 */
	public String getType(){
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type){
		this.type = type;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice(){
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price){
		this.price = price;
	}

	/**
	 * @return the description
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * @return the properties
	 */
	public String getProperties(){
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(String properties){
		this.properties = properties;
	}

	/**
	 * @return the propertyNames
	 */
	public String getPropertyNames(){
		return propertyNames;
	}

	/**
	 * @param propertyNames
	 *            the propertyNames to set
	 */
	public void setPropertyNames(String propertyNames){
		this.propertyNames = propertyNames;
	}

	/**
	 * @return the propertyAlias
	 */
	public String getPropertyAlias(){
		return propertyAlias;
	}

	/**
	 * @param propertyAlias
	 *            the propertyAlias to set
	 */
	public void setPropertyAlias(String propertyAlias){
		this.propertyAlias = propertyAlias;
	}

	/**
	 * @return the sellPoint
	 */
	public String getSellPoint(){
		return sellPoint;
	}

	/**
	 * @param sellPoint
	 *            the sellPoint to set
	 */
	public void setSellPoint(String sellPoint){
		this.sellPoint = sellPoint;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName(){
		return nickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName){
		this.nickName = nickName;
	}

	/**
	 * @return the postageId
	 */
	public String getPostageId(){
		return postageId;
	}

	/**
	 * @param postageId
	 *            the postageId to set
	 */
	public void setPostageId(String postageId){
		this.postageId = postageId;
	}

	/**
	 * @return the timing
	 */
	public boolean getTiming(){
		return timing;
	}

	/**
	 * @param timing
	 *            the timing to set
	 */
	public void setTiming(boolean timing){
		this.timing = timing;
	}

	/**
	 * @return the listTime
	 */
	public Date getListTime(){
		return listTime;
	}

	/**
	 * @param listTime
	 *            the listTime to set
	 */
	public void setListTime(Date listTime){
		this.listTime = listTime;
	}

	/**
	 * @return the delistTime
	 */
	public Date getDelistTime(){
		return delistTime;
	}

	/**
	 * @param delistTime
	 *            the delistTime to set
	 */
	public void setDelistTime(Date delistTime){
		this.delistTime = delistTime;
	}

	/**
	 * @return the templateId
	 */
	public Long getTemplateId(){
		return templateId;
	}

	/**
	 * @param templateId
	 *            the templateId to set
	 */
	public void setTemplateId(Long templateId){
		this.templateId = templateId;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl(){
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the supportPOD
	 */
	public boolean getSupportPOD(){
		return supportPOD;
	}

	/**
	 * @param supportPOD
	 *            the supportPOD to set
	 */
	public void setSupportPOD(boolean supportPOD){
		this.supportPOD = supportPOD;
	}

	/**
	 * @return the podFee
	 */
	public BigDecimal getPodFee(){
		return podFee;
	}

	/**
	 * @param podFee
	 *            the podFee to set
	 */
	public void setPodFee(BigDecimal podFee){
		this.podFee = podFee;
	}

	/**
	 * @return the created
	 */
	public Date getCreated(){
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created){
		this.created = created;
	}

	/**
	 * @return the cTime
	 */
	public String getcTime(){
		return cTime;
	}

	/**
	 * @param cTime
	 *            the cTime to set
	 */
	public void setcTime(String cTime){
		this.cTime = cTime;
	}

	/**
	 * @return the modified
	 */
	public Date getModified(){
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified){
		this.modified = modified;
	}

	/**
	 * @return the status
	 */
	public String getStatus(){
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status){
		this.status = status;
	}

	/**
	 * @return the shopId
	 */
	public Long getShopId(){
		return shopId;
	}

	/**
	 * @param shopId
	 *            the shopId to set
	 */
	public void setShopId(Long shopId){
		this.shopId = shopId;
	}

	/**
	 * @return the sellerId
	 */
	public Long getSellerId(){
		return sellerId;
	}

	/**
	 * @param sellerId
	 *            the sellerId to set
	 */
	public void setSellerId(Long sellerId){
		this.sellerId = sellerId;
	}

	/**
	 * @return the shopCategoryId
	 */
	public List<Long> getShopCategoryId(){
		return shopCategoryId;
	}

	/**
	 * @param shopCategoryId
	 *            the shopCategoryId to set
	 */
	public void setShopCategoryId(List<Long> shopCategoryId){
		this.shopCategoryId = shopCategoryId;
	}

	/**
	 * @return the listPrice
	 */
	public BigDecimal getListPrice(){
		return listPrice;
	}

	/**
	 * @param listPrice
	 *            the listPrice to set
	 */
	public void setListPrice(BigDecimal listPrice){
		this.listPrice = listPrice;
	}

	/**
	 * @return the state
	 */
	public String getState(){
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state){
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity(){
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city){
		this.city = city;
	}

	/**
	 * @return the district
	 */
	public String getDistrict(){
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district){
		this.district = district;
	}

	/**
	 * @return the allowedActions
	 */
	public Set<String> getAllowedActions(){
		return allowedActions;
	}

	/**
	 * @param allowedActions
	 *            the allowedActions to set
	 */
	public void setAllowedActions(Set<String> allowedActions){
		this.allowedActions = allowedActions;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight(){
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Double weight){
		this.weight = weight;
	}

	/**
	 * @return the startime
	 */
	public String getStartime(){
		return startime;
	}

	/**
	 * @param startime
	 *            the startime to set
	 */
	public void setStartime(String startime){
		this.startime = startime;
	}

	/**
	 * @return the endtime
	 */
	public String getEndtime(){
		return endtime;
	}

	/**
	 * @param endtime
	 *            the endtime to set
	 */
	public void setEndtime(String endtime){
		this.endtime = endtime;
	}

	/**
	 * @return the shopname
	 */
	public String getShopname(){
		return shopname;
	}

	/**
	 * @param shopname
	 *            the shopname to set
	 */
	public void setShopname(String shopname){
		this.shopname = shopname;
	}

	/**
	 * @return the attachTags
	 */
	public String getAttachTags(){
		return attachTags;
	}

	/**
	 * @param attachTags
	 *            the attachTags to set
	 */
	public void setAttachTags(String attachTags){
		this.attachTags = attachTags;
	}

	/**
	 * @return the isHaveTag
	 */
	public String getIsHaveTag(){
		return isHaveTag;
	}

	/**
	 * @param isHaveTag
	 *            the isHaveTag to set
	 */
	public void setIsHaveTag(String isHaveTag){
		this.isHaveTag = isHaveTag;
	}

	/**
	 * @return the inStock
	 */
	public int getInStock(){
		return inStock;
	}

	/**
	 * @param inStock
	 *            the inStock to set
	 */
	public void setInStock(int inStock){
		this.inStock = inStock;
	}

	/**
	 * @return the freightPayer
	 */
	public String getFreightPayer(){
		return freightPayer;
	}

	/**
	 * @param freightPayer
	 *            the freightPayer to set
	 */
	public void setFreightPayer(String freightPayer){
		this.freightPayer = freightPayer;
	}

	/**
	 * @return the expressFee
	 */
	public Integer getExpressFee(){
		return expressFee;
	}

	/**
	 * @param expressFee
	 *            the expressFee to set
	 */
	public void setExpressFee(Integer expressFee){
		this.expressFee = expressFee;
	}

	/**
	 * @return the length
	 */
	public Integer getLength(){
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(Integer length){
		this.length = length;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth(){
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Integer width){
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight(){
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Integer height){
		this.height = height;
	}

	/**
	 * @return the buyerObtainPoint
	 */
	public Integer getBuyerObtainPoint(){
		return buyerObtainPoint;
	}

	/**
	 * @param buyerObtainPoint
	 *            the buyerObtainPoint to set
	 */
	public void setBuyerObtainPoint(Integer buyerObtainPoint){
		this.buyerObtainPoint = buyerObtainPoint;
	}

	/**
	 * @return the oldSku
	 */
	public String getOldSku(){
		return oldSku;
	}

	/**
	 * @param oldSku
	 *            the oldSku to set
	 */
	public void setOldSku(String oldSku){
		this.oldSku = oldSku;
	}

	/**
	 * @return the mustCheck
	 */
	public boolean getMustCheck(){
		return mustCheck;
	}

	/**
	 * @param mustCheck
	 *            the mustCheck to set
	 */
	public void setMustCheck(boolean mustCheck){
		this.mustCheck = mustCheck;
	}

	/**
	 * @return the excelPath
	 */
	public String getExcelPath(){
		return excelPath;
	}

	/**
	 * @param excelPath
	 *            the excelPath to set
	 */
	public void setExcelPath(String excelPath){
		this.excelPath = excelPath;
	}

	/**
	 * @return the excelIndex
	 */
	public int getExcelIndex(){
		return excelIndex;
	}

	/**
	 * @param excelIndex
	 *            the excelIndex to set
	 */
	public void setExcelIndex(int excelIndex){
		this.excelIndex = excelIndex;
	}

}
