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
package com.feilong.hibernate.search.model.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLockType;

import com.feilong.hibernate.search.model.BaseModel;

/**
 * 商品标签。
 * 
 * <pre>
 * 是系统中重要的基础实体。
 * 
 * 商品标签是系统支持的除渠道外另外一种划分商品范围的方式。
 * 拥有相同特性的商品可以被贴上一个用以标示此特性的标签，如小家电、训练等等。
 *  
 * 因此商品和商品标签是一个多对多的关系。 
 *  
 * 商品标签自身之间也存在一个网状关系，这种网状关系可以用来表达商城中的浏览顺序，如男子-->服装-->运动休闲等等。
 * </pre>
 * 
 * .
 *
 * @author benjamin
 * @see Sku
 */
@Entity
@Table(name = "T_MA_SKU_CATEGORY")
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.VERSION)
public class SkuCategory implements BaseModel,Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID				= -7006715425766334798L;

	/** 充值卡产品标签ID. */
	public static final Long	PREPAID_CARD_SKU_CATEGORY_ID	= 103L;						//充值卡产品标签ID

	/** 团购标签 code ="TUANGOU". */
	public static final String	CATEGORY_TUANGOU				= "TUANGOU";

	/** 限量版 limited. */
	public static final String	CATEGORY_LIMITED				= "limited";

	/** PK. */
	private Long				id;

	/** 商品标签编码. */
	private String				code;

	/** 商品标签顺序（保留）. */
	private String				displayOrder;

	/** 商品标签名称. */
	private String				name;

	/** 商品标签描述. */
	private String				description;

	/** 商品标签入口地址（保留）. */
	private String				entranceUrl;

	/** The is most preferred. */
	private Boolean				isMostPreferred;

	/** 是否是关键字（保留）. */
	private Boolean				isKeyword;

	/** The is related. */
	private Boolean				isRelated;

	/** 创建时间. */
	private Date				createTime						= new Date();

	/** 生命周期. */
	private Integer				lifeCycleStatus					= DEFAULT_STATUS;

	/** Version. */
	private Date				version;

	/** (保留)---->用于补货报表 主类别,标识是否是主类别,默认是false,如果是主类别会依据最小的id 来统计数据 use by jinxin. */
	private Boolean				isStaffCate;

	/** 关键字（保留）. */
	private String				keyword;

	/** 关键字描述（保留）. */
	private String				keyDescription;

	/** 父标签列表. */
	private List<SkuCategory>	fromSkuCategories				= new ArrayList<SkuCategory>();

	/** 子标签列表. */
	private List<SkuCategory>	toSkuCategories					= new ArrayList<SkuCategory>();

	/** 商品列表. */
	private List<Sku>			skus							= new ArrayList<Sku>();

	/**
	 * 获得 pK.
	 *
	 * @return the pK
	 */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "SEQ_SKUCAT",sequenceName = "S_T_MA_SKU_CATEGORY",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_SKUCAT")
	public Long getId(){
		return id;
	}

	/**
	 * 设置 pK.
	 *
	 * @param id
	 *            the new pK
	 */
	public void setId(Long id){
		this.id = id;
	}

	/**
	 * 获得 商品标签编码.
	 *
	 * @return the 商品标签编码
	 */
	@Column(name = "CODE",length = 20)
	public String getCode(){
		return code;
	}

	/**
	 * 设置 商品标签编码.
	 *
	 * @param code
	 *            the new 商品标签编码
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * 获得 商品标签顺序（保留）.
	 *
	 * @return the 商品标签顺序（保留）
	 */
	@Column(name = "DISPLAY_ORDER",length = 50)
	public String getDisplayOrder(){
		return displayOrder;
	}

	/**
	 * 设置 商品标签顺序（保留）.
	 *
	 * @param displayOrder
	 *            the new 商品标签顺序（保留）
	 */
	public void setDisplayOrder(String displayOrder){
		this.displayOrder = displayOrder;
	}

	/**
	 * 获得 商品标签名称.
	 *
	 * @return the 商品标签名称
	 */
	@Column(name = "NAME",length = 50)
	public String getName(){
		return name;
	}

	/**
	 * 设置 商品标签名称.
	 *
	 * @param name
	 *            the new 商品标签名称
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 获得 商品标签描述.
	 *
	 * @return the 商品标签描述
	 */
	@Column(name = "DESCRIPTION",length = 500)
	public String getDescription(){
		return description;
	}

	/**
	 * 设置 商品标签描述.
	 *
	 * @param description
	 *            the new 商品标签描述
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * 获得 商品标签入口地址（保留）.
	 *
	 * @return the 商品标签入口地址（保留）
	 */
	@Column(name = "ENTRANCE_URL",length = 100)
	public String getEntranceUrl(){
		return entranceUrl;
	}

	/**
	 * 设置 商品标签入口地址（保留）.
	 *
	 * @param entranceUrl
	 *            the new 商品标签入口地址（保留）
	 */
	public void setEntranceUrl(String entranceUrl){
		this.entranceUrl = entranceUrl;
	}

	/**
	 * 获得 is most preferred.
	 *
	 * @return the checks if is most preferred
	 */
	@Column(name = "IS_MOST_PREFERRED")
	public Boolean getIsMostPreferred(){
		return isMostPreferred;
	}

	/**
	 * 设置 is most preferred.
	 *
	 * @param isMostPreferred
	 *            the checks if is most preferred
	 */
	public void setIsMostPreferred(Boolean isMostPreferred){
		this.isMostPreferred = isMostPreferred;
	}

	/**
	 * 获得 是否是关键字（保留）.
	 *
	 * @return the 是否是关键字（保留）
	 */
	@Column(name = "IS_KEYWORD")
	public Boolean getIsKeyword(){
		return isKeyword;
	}

	/**
	 * 设置 是否是关键字（保留）.
	 *
	 * @param isKeyword
	 *            the new 是否是关键字（保留）
	 */
	public void setIsKeyword(Boolean isKeyword){
		this.isKeyword = isKeyword;
	}

	/**
	 * 获得 is related.
	 *
	 * @return the checks if is related
	 */
	@Column(name = "IS_RELATED")
	public Boolean getIsRelated(){
		return isRelated;
	}

	/**
	 * 设置 is related.
	 *
	 * @param isRelated
	 *            the checks if is related
	 */
	public void setIsRelated(Boolean isRelated){
		this.isRelated = isRelated;
	}

	/**
	 * 获得 创建时间.
	 *
	 * @return the 创建时间
	 */
	@Column(name = "CREATE_TIME")
	public Date getCreateTime(){
		return createTime;
	}

	/**
	 * 设置 创建时间.
	 *
	 * @param createTime
	 *            the new 创建时间
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	/**
	 * 获得 生命周期.
	 *
	 * @return the 生命周期
	 */
	@Column(name = "LIFE_CYCLE_STATUS")
	public Integer getLifeCycleStatus(){
		return lifeCycleStatus;
	}

	/**
	 * 设置 生命周期.
	 *
	 * @param lifeCycleStatus
	 *            the new 生命周期
	 */
	public void setLifeCycleStatus(Integer lifeCycleStatus){
		this.lifeCycleStatus = lifeCycleStatus;
	}

	/**
	 * 获得 version.
	 *
	 * @return the version
	 */
	@Version
	@Column(name = "VERSION")
	public Date getVersion(){
		return version;
	}

	/**
	 * 设置 version.
	 *
	 * @param version
	 *            the new version
	 */
	public void setVersion(Date version){
		this.version = version;
	}

	/**
	 * (保留)---->用于补货报表 主类别,标识是否是主类别,默认是false,如果是主类别会依据最小的id 来统计数据 use by jinxin.
	 *
	 * @return the (保留)---->用于补货报表 主类别,标识是否是主类别,默认是false,如果是主类别会依据最小的id 来统计数据 use by jinxin
	 */
	@Column(name = "IS_STAFF_CATE")
	public Boolean getIsStaffCate(){
		return isStaffCate;
	}

	/**
	 * (保留)---->用于补货报表 主类别,标识是否是主类别,默认是false,如果是主类别会依据最小的id 来统计数据 use by jinxin.
	 *
	 * @param isStaffCate
	 *            the new (保留)---->用于补货报表 主类别,标识是否是主类别,默认是false,如果是主类别会依据最小的id 来统计数据 use by jinxin
	 */
	public void setIsStaffCate(Boolean isStaffCate){
		this.isStaffCate = isStaffCate;
	}

	/**
	 * 获得 关键字（保留）.
	 *
	 * @return the 关键字（保留）
	 */
	@Column(name = "KEYWORD",length = 1000)
	public String getKeyword(){
		return keyword;
	}

	/**
	 * 设置 关键字（保留）.
	 *
	 * @param keyword
	 *            the new 关键字（保留）
	 */
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}

	/**
	 * 获得 关键字描述（保留）.
	 *
	 * @return the 关键字描述（保留）
	 */
	@Column(name = "KEY_DESCRIPTION",length = 1000)
	public String getKeyDescription(){
		return keyDescription;
	}

	/**
	 * 设置 关键字描述（保留）.
	 *
	 * @param keyDescription
	 *            the new 关键字描述（保留）
	 */
	public void setKeyDescription(String keyDescription){
		this.keyDescription = keyDescription;
	}

	/**
	 * 获得 父标签列表.
	 *
	 * @return the 父标签列表
	 */
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "toSkuCategories")
	@OrderBy("id")
	public List<SkuCategory> getFromSkuCategories(){
		return fromSkuCategories;
	}

	/**
	 * 设置 父标签列表.
	 *
	 * @param fromSkuCategories
	 *            the new 父标签列表
	 */
	public void setFromSkuCategories(List<SkuCategory> fromSkuCategories){
		this.fromSkuCategories = fromSkuCategories;
	}

	/**
	 * 获得 子标签列表.
	 *
	 * @return the 子标签列表
	 */
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "T_MA_SKU_CATEGORY_RELATION",joinColumns = @JoinColumn(name = "FROM_CATEGORY_ID",referencedColumnName = "ID"),inverseJoinColumns = @JoinColumn(name = "TO_CATEGORY_ID",referencedColumnName = "ID"))
	@OrderBy("id")
	public List<SkuCategory> getToSkuCategories(){
		return toSkuCategories;
	}

	/**
	 * 设置 子标签列表.
	 *
	 * @param toSkuCategories
	 *            the new 子标签列表
	 */
	public void setToSkuCategories(List<SkuCategory> toSkuCategories){
		this.toSkuCategories = toSkuCategories;
	}

	/**
	 * 获得 商品列表.
	 *
	 * @return the 商品列表
	 */
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "skuCategories")
	@OrderBy("id")
	public List<Sku> getSkus(){
		return skus;
	}

	/**
	 * 设置 商品列表.
	 *
	 * @param skus
	 *            the new 商品列表
	 */
	public void setSkus(List<Sku> skus){
		this.skus = skus;
	}

	/**
	 * Please note: previous method name is getIsUsed.
	 *
	 * @return true, if checks if is used
	 */
	@Transient
	public boolean IsUsed(){
		return !(skus == null || skus.size() == 0);
	}

	/**
	 * 获得 avai to sku categories.
	 *
	 * @return the avai to sku categories
	 */
	@Transient
	public List<SkuCategory> getAvaiToSkuCategories(){
		TreeMap<String, SkuCategory> skuCates = new TreeMap<String, SkuCategory>();
		if (this.toSkuCategories != null){
			for (Object toSkuCategory : toSkuCategories){
				SkuCategory category = (SkuCategory) toSkuCategory;
				if (category.getLifeCycleStatus().equals(STATUS_ENABLE) && category.getIsMostPreferred().equals(Boolean.FALSE)){
					String order = category.getDisplayOrder() == null ? "" : category.getDisplayOrder();
					skuCates.put(order + category.getId(), category);
				}
			}
		}
		List<SkuCategory> result = new ArrayList<SkuCategory>();
		result.addAll(skuCates.values());
		if (result.isEmpty()){
			return null;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if (obj == null)
			return false;
		if (!(obj instanceof SkuCategory))
			throw new IllegalArgumentException();
		SkuCategory sc = (SkuCategory) obj;
		return this.getId().equals(sc.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erry.model.BaseModel#getModelClass()
	 */
	@Transient
	public Class<?> getModelClass(){
		return SkuCategory.class;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
