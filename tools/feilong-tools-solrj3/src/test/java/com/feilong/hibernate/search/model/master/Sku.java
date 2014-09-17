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
import java.math.BigDecimal;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.FullTextFilterDef;
import org.hibernate.search.annotations.FullTextFilterDefs;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.feilong.hibernate.search.bridge.SkuCategoryBridge;
import com.feilong.hibernate.search.bridge.SkuDynaPropertyBridge;
import com.feilong.hibernate.search.filter.SkuStatusFilterFactory;
import com.feilong.hibernate.search.model.BaseModel;

/**
 * 商品。是系统中重要的基础实体。虽然名称使用的是SKU，但是不一定是最终的最小仓储单位。 对于服装类商品，一个商品由于尺寸的不同还会被拆分成很多个最终仓储意义上的SKU，因此为避免混淆，在当前系统中我们称此类为商品。
 * 不同类型的商品可能存在不同的属性，为能记录这些属性，系统将用来描述商品信息的属性划分为两类：固定属性和动态属性<br>
 * 固定属性是指对于所有类型的商品都一定拥有的共性，如商品一定有品名<br>
 * 动态属性是指对于某类商品才会拥有的特性，如鞋类商品会有材质，快速消费类商品会有保质期等<br>
 * 属性会有多种类型，对于固定属性，其类型已经预先定义完毕；对于动态属性，其类型将主要会是：字符串类型、URL类型、数值类型、布尔类型、日期类型和列表类型这几类。
 * 而这些类型中可能还会细分，如字符串类型还会分是短文本还是长文本，URL是Http的还是文件类型的，日期是日期类型还是日期时间类型等等。
 * 其中要注意的是列表类型，此类型的数据是指此属性中会维护一个列表信息用来列举此属性的所有可能数据，如服装就会有尺寸这个列表类型的动态属性用于表征衣服可能会有哪些尺寸。 列表类型里记录的其实是一段XML文本，这段文本的格式会是
 * <code><enum type="{element type}"><item>{available value1}</item><item>{available value2}</item></enum></code>
 * type定义了列表中每个元素的类型，每一个item就是列表中的一个元素。<br>
 * 商品会拥有哪些属性将通过商品模板来定义，商品将会继承其模板的全部属性定义，一个商品有且只有一个商品模板。 商品还可以通过和商品标签关联的方式为其增加特性信息，一个商品可以同时被贴多个商品标签。
 * 商品的生命周期状态中会有未上架和已上架状态，只有上架的商品才能在商城中展示和销售。.
 *
 * @author benjamin
 * @see Supplier
 * @see SkuCategory
 * @see Channel
 */
@Indexed(index = "product")
@FullTextFilterDefs({ @FullTextFilterDef(name = "status",impl = SkuStatusFilterFactory.class) })
@Entity
@Table(name = "T_MA_SKU")
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.VERSION)
public class Sku implements BaseModel,Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID				= 7485685519464640874L;

	//sku life cycle status
	/** 5. */
	public static final Integer	SKU_STATUS_ONSALES				= 5;

	/** 1. */
	public static final Integer	SKU_STATUS_OFFSALES				= 1;

	/** 0. */
	public static final Integer	SKU_STATUS_DISABLED				= 0;

	//sku stock status
	/** 5 现货. */
	public static final Integer	SKU_STOCK_STATUS_ENOUGH			= 5;

	/** 0 缺货. */
	public static final Integer	SKU_STOCK_STATUS_OUT_OF_STOCK	= 0;

	/** 1 预订. */
	public static final Integer	SKU_STOCK_STATUS_RESERVE		= 1;

	//Property Data Type
	/** The Constant PROP_DATA_TYPE_SHORT_STRING. */
	public static final String	PROP_DATA_TYPE_SHORT_STRING		= "short-string";

	/** The Constant PROP_DATA_TYPE_STRING. */
	public static final String	PROP_DATA_TYPE_STRING			= "string";

	/** The Constant PROP_DATA_TYPE_LARGE_STRING. */
	public static final String	PROP_DATA_TYPE_LARGE_STRING		= "large-string";

	/** The Constant PROP_DATA_TYPE_INTEGER. */
	public static final String	PROP_DATA_TYPE_INTEGER			= "int";

	/** The Constant PROP_DATA_TYPE_NUMBER. */
	public static final String	PROP_DATA_TYPE_NUMBER			= "number";

	/** The Constant PROP_DATA_TYPE_BOOLEAN. */
	public static final String	PROP_DATA_TYPE_BOOLEAN			= "boolean";

	/** The Constant PROP_DATA_TYPE_IMAGE_URL. */
	public static final String	PROP_DATA_TYPE_IMAGE_URL		= "image";

	/** The Constant PROP_DATA_TYPE_VIDEO_URL. */
	public static final String	PROP_DATA_TYPE_VIDEO_URL		= "video";

	/** The Constant PROP_DATA_TYPE_AUDIO_URL. */
	public static final String	PROP_DATA_TYPE_AUDIO_URL		= "audio";

	/** The Constant PROP_DATA_TYPE_FILE_URL. */
	public static final String	PROP_DATA_TYPE_FILE_URL			= "file";

	/** The Constant PROP_DATA_TYPE_DATE. */
	public static final String	PROP_DATA_TYPE_DATE				= "date";

	/** The Constant PROP_DATA_TYPE_TIME. */
	public static final String	PROP_DATA_TYPE_TIME				= "time";

	/** The Constant PROP_DATA_TYPE_DATE_TIME. */
	public static final String	PROP_DATA_TYPE_DATE_TIME		= "datetime";

	/** The Constant PROP_DATA_TYPE_ENTITY. */
	public static final String	PROP_DATA_TYPE_ENTITY			= "entity";

	/** The Constant PROP_DATA_TYPE_LIST. */
	public static final String	PROP_DATA_TYPE_LIST				= "list";

	/** The Constant PROP_DATA_TYPE_URL. */
	public static final String	PROP_DATA_TYPE_URL				= "url";

	/* (non-Javadoc)
	 * @see com.erry.model.BaseModel#getModelClass()
	 */
	@Transient
	public Class<?> getModelClass(){
		return Sku.class;
	}

	/** PK. */
	private Long							id;

	/** 商品编码. */
	private String							code;

	/** 供应商编码. */
	private String							supplierSkuCode;

	/** 商品名称. */
	private String							name;

	/** 商品模板名称. */
	private String							skuTemplateName;

	/** 物流方式（保留）. */
	private String							logisticType;

	/** 商品显示顺序. */
	private String							reserved1;

	/** 保留字段. */
	private String							reserved2;

	/** 保留字段. */
	private String							reserved3;

	/** 保留字段. */
	private String							reserved4;

	/** 保留字段. */
	private String							reserved5;

	/** 保留字段. */
	private String							reserved6;

	/** 保留字段. */
	private String							reserved7;

	/** 保留字段. */
	private String							reserved8;

	/** 商品的NIKEID链接. */
	private String							reserved9;

	/** 颜色. */
	private String							reserved10;

	/** 保留字段. */
	private String							reserved11;

	/** 保留字段. */
	private String							reserved12;

	/** 保留字段. */
	private String							reserved13;

	/** 保留字段. */
	private String							reserved14;

	/** 保留字段. */
	private String							reserved15;

	/** 保留字段. */
	private String							hugePic2;

	/** 保留字段. */
	private String							hugePic3;

	/** 保留字段. */
	private String							hugePic4;

	/** 保留字段. */
	private String							hugePic5;

	/** 毛重. */
	private BigDecimal						grossWeight;

	/** 净重. */
	private BigDecimal						netWeight;

	/** 长度. */
	private BigDecimal						length;

	/** 宽度. */
	private BigDecimal						width;

	/** 高度. */
	private BigDecimal						height;

	/** 创建时间. */
	private Date							createTime		= new Date();

	/** 最近修改时间. */
	private Date							lastModifyTime	= new Date();

	/** 最近生命周期修改时间. */
	private Date							lifeCycleOpTime	= new Date();

	/** 生命周期，默认是未上架. */
	private Integer							lifeCycleStatus	= SKU_STATUS_OFFSALES;

	/** 库存状态（保留）. */
	private Integer							stockStatus		= SKU_STOCK_STATUS_ENOUGH;

	/** 商品的动态属性集，其中key就是动态属性，value是其对应的值，所有类型的值在这里都以字符串表示. */
	private Map<SkuDynaPropSetting, String>	skuDpValues		= new HashMap<SkuDynaPropSetting, String>();

	/** Version. */
	private Date							version;

	/** 商品标签列表. */
	private List<SkuCategory>				skuCategories	= new ArrayList<SkuCategory>();

	/**
	 * 获得 pK.
	 *
	 * @return the pK
	 */
	@DocumentId
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "SEQ_SKU",sequenceName = "S_T_MA_SKU",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_SKU")
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
	 * 获得 商品编码.
	 *
	 * @return the 商品编码
	 */
	@Field(name = "code",store = Store.YES,index = org.hibernate.search.annotations.Index.TOKENIZED,boost = @Boost(2f))
	@Column(name = "CODE",length = 20)
	public String getCode(){
		return code;
	}

	/**
	 * 设置 商品编码.
	 *
	 * @param code
	 *            the new 商品编码
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * 获得 供应商编码.
	 *
	 * @return the 供应商编码
	 */
	@Column(name = "SUPPLIER_SKU_CODE",length = 50)
	public String getSupplierSkuCode(){
		return supplierSkuCode;
	}

	/**
	 * 设置 供应商编码.
	 *
	 * @param supplierSkuCode
	 *            the new 供应商编码
	 */
	public void setSupplierSkuCode(String supplierSkuCode){
		this.supplierSkuCode = supplierSkuCode;
	}

	/**
	 * 获得 商品名称.
	 *
	 * @return the 商品名称
	 */
	@Field(name = "name",store = Store.YES,index = org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
	@Column(name = "NAME",length = 300)
	public String getName(){
		return name;
	}

	/**
	 * 设置 商品名称.
	 *
	 * @param name
	 *            the new 商品名称
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 获得 商品模板名称.
	 *
	 * @return the 商品模板名称
	 */
	@Column(name = "SKU_TEMPLATE_NAME",length = 50)
	public String getSkuTemplateName(){
		return skuTemplateName;
	}

	/**
	 * 设置 商品模板名称.
	 *
	 * @param skuTemplateName
	 *            the new 商品模板名称
	 */
	public void setSkuTemplateName(String skuTemplateName){
		this.skuTemplateName = skuTemplateName;
	}

	/**
	 * 获得 物流方式（保留）.
	 *
	 * @return the 物流方式（保留）
	 */
	@Column(name = "LOGISTIC_TYPE",length = 50)
	public String getLogisticType(){
		return logisticType;
	}

	/**
	 * 设置 物流方式（保留）.
	 *
	 * @param logisticType
	 *            the new 物流方式（保留）
	 */
	public void setLogisticType(String logisticType){
		this.logisticType = logisticType;
	}

	/**
	 * 获得 商品显示顺序.
	 *
	 * @return the 商品显示顺序
	 */
	@Column(name = "RESERVED_1",length = 100)
	public String getReserved1(){
		return reserved1;
	}

	/**
	 * 设置 商品显示顺序.
	 *
	 * @param reserved1
	 *            the new 商品显示顺序
	 */
	public void setReserved1(String reserved1){
		this.reserved1 = reserved1;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_2",length = 100)
	public String getReserved2(){
		return reserved2;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved2
	 *            the new 保留字段
	 */
	public void setReserved2(String reserved2){
		this.reserved2 = reserved2;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_3",length = 100)
	public String getReserved3(){
		return reserved3;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved3
	 *            the new 保留字段
	 */
	public void setReserved3(String reserved3){
		this.reserved3 = reserved3;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_4",length = 100)
	public String getReserved4(){
		return reserved4;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved4
	 *            the new 保留字段
	 */
	public void setReserved4(String reserved4){
		this.reserved4 = reserved4;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_5",length = 100)
	public String getReserved5(){
		return reserved5;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved5
	 *            the new 保留字段
	 */
	public void setReserved5(String reserved5){
		this.reserved5 = reserved5;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_6",length = 100)
	public String getReserved6(){
		return reserved6;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved6
	 *            the new 保留字段
	 */
	public void setReserved6(String reserved6){
		this.reserved6 = reserved6;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_7",length = 500)
	public String getReserved7(){
		return reserved7;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved7
	 *            the new 保留字段
	 */
	public void setReserved7(String reserved7){
		this.reserved7 = reserved7;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_8",length = 500)
	public String getReserved8(){
		return reserved8;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved8
	 *            the new 保留字段
	 */
	public void setReserved8(String reserved8){
		this.reserved8 = reserved8;
	}

	/**
	 * 获得 商品的NIKEID链接.
	 *
	 * @return the 商品的NIKEID链接
	 */
	@Column(name = "RESERVED_9",length = 500)
	public String getReserved9(){
		return reserved9;
	}

	/**
	 * 设置 商品的NIKEID链接.
	 *
	 * @param reserved9
	 *            the new 商品的NIKEID链接
	 */
	public void setReserved9(String reserved9){
		this.reserved9 = reserved9;
	}

	/**
	 * 获得 颜色.
	 *
	 * @return the 颜色
	 */
	@Field(name = "color",store = Store.YES,index = org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
	@Column(name = "RESERVED_10",length = 500)
	public String getReserved10(){
		return reserved10;
	}

	/**
	 * 设置 颜色.
	 *
	 * @param reserved10
	 *            the new 颜色
	 */
	public void setReserved10(String reserved10){
		this.reserved10 = reserved10;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_11",length = 100)
	public String getReserved11(){
		return reserved11;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved11
	 *            the new 保留字段
	 */
	public void setReserved11(String reserved11){
		this.reserved11 = reserved11;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_12",length = 100)
	public String getReserved12(){
		return reserved12;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved12
	 *            the new 保留字段
	 */
	public void setReserved12(String reserved12){
		this.reserved12 = reserved12;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_13",length = 100)
	public String getReserved13(){
		return reserved13;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved13
	 *            the new 保留字段
	 */
	public void setReserved13(String reserved13){
		this.reserved13 = reserved13;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_14",length = 100)
	public String getReserved14(){
		return reserved14;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved14
	 *            the new 保留字段
	 */
	public void setReserved14(String reserved14){
		this.reserved14 = reserved14;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "RESERVED_15",length = 100)
	public String getReserved15(){
		return reserved15;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param reserved15
	 *            the new 保留字段
	 */
	public void setReserved15(String reserved15){
		this.reserved15 = reserved15;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "HUGE_PIC2",length = 100)
	public String getHugePic2(){
		return hugePic2;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param hugePic2
	 *            the new 保留字段
	 */
	public void setHugePic2(String hugePic2){
		this.hugePic2 = hugePic2;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "HUGE_PIC3",length = 100)
	public String getHugePic3(){
		return hugePic3;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param hugePic3
	 *            the new 保留字段
	 */
	public void setHugePic3(String hugePic3){
		this.hugePic3 = hugePic3;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "HUGE_PIC4",length = 100)
	public String getHugePic4(){
		return hugePic4;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param hugePic4
	 *            the new 保留字段
	 */
	public void setHugePic4(String hugePic4){
		this.hugePic4 = hugePic4;
	}

	/**
	 * 获得 保留字段.
	 *
	 * @return the 保留字段
	 */
	@Column(name = "HUGE_PIC5",length = 100)
	public String getHugePic5(){
		return hugePic5;
	}

	/**
	 * 设置 保留字段.
	 *
	 * @param hugePic5
	 *            the new 保留字段
	 */
	public void setHugePic5(String hugePic5){
		this.hugePic5 = hugePic5;
	}

	/**
	 * 获得 毛重.
	 *
	 * @return the 毛重
	 */
	@Column(name = "GROSS_WEIGHT",precision = 15,scale = 5)
	public BigDecimal getGrossWeight(){
		return grossWeight;
	}

	/**
	 * 设置 毛重.
	 *
	 * @param grossWeight
	 *            the new 毛重
	 */
	public void setGrossWeight(BigDecimal grossWeight){
		this.grossWeight = grossWeight;
	}

	/**
	 * 获得 净重.
	 *
	 * @return the 净重
	 */
	@Column(name = "NET_WEIGHT",precision = 15,scale = 5)
	public BigDecimal getNetWeight(){
		return netWeight;
	}

	/**
	 * 设置 净重.
	 *
	 * @param netWeight
	 *            the new 净重
	 */
	public void setNetWeight(BigDecimal netWeight){
		this.netWeight = netWeight;
	}

	/**
	 * 获得 长度.
	 *
	 * @return the 长度
	 */
	@Column(name = "SLENGTH",precision = 15,scale = 5)
	public BigDecimal getLength(){
		return length;
	}

	/**
	 * 设置 长度.
	 *
	 * @param length
	 *            the new 长度
	 */
	public void setLength(BigDecimal length){
		this.length = length;
	}

	/**
	 * 获得 宽度.
	 *
	 * @return the 宽度
	 */
	@Column(name = "WIDTH",precision = 15,scale = 5)
	public BigDecimal getWidth(){
		return width;
	}

	/**
	 * 设置 宽度.
	 *
	 * @param width
	 *            the new 宽度
	 */
	public void setWidth(BigDecimal width){
		this.width = width;
	}

	/**
	 * 获得 高度.
	 *
	 * @return the 高度
	 */
	@Column(name = "HEIGHT",precision = 15,scale = 5)
	public BigDecimal getHeight(){
		return height;
	}

	/**
	 * 设置 高度.
	 *
	 * @param height
	 *            the new 高度
	 */
	public void setHeight(BigDecimal height){
		this.height = height;
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
	 * 获得 最近修改时间.
	 *
	 * @return the 最近修改时间
	 */
	@Column(name = "LAST_MODIFY_TIME")
	public Date getLastModifyTime(){
		return lastModifyTime;
	}

	/**
	 * 设置 最近修改时间.
	 *
	 * @param lastModifyTime
	 *            the new 最近修改时间
	 */
	public void setLastModifyTime(Date lastModifyTime){
		this.lastModifyTime = lastModifyTime;
	}

	/**
	 * 获得 最近生命周期修改时间.
	 *
	 * @return the 最近生命周期修改时间
	 */
	@Column(name = "LIFE_CYCLE_OP_TIME")
	public Date getLifeCycleOpTime(){
		return lifeCycleOpTime;
	}

	/**
	 * 设置 最近生命周期修改时间.
	 *
	 * @param lifeCycleOpTime
	 *            the new 最近生命周期修改时间
	 */
	public void setLifeCycleOpTime(Date lifeCycleOpTime){
		this.lifeCycleOpTime = lifeCycleOpTime;
	}

	/**
	 * 获得 生命周期，默认是未上架.
	 *
	 * @return the 生命周期，默认是未上架
	 */
	@Field(name = "status",store = Store.YES,index = org.hibernate.search.annotations.Index.TOKENIZED)
	@Column(name = "LIFE_CYCLE_STATUS")
	public Integer getLifeCycleStatus(){
		return lifeCycleStatus;
	}

	/**
	 * 设置 生命周期，默认是未上架.
	 *
	 * @param lifeCycleStatus
	 *            the new 生命周期，默认是未上架
	 */
	public void setLifeCycleStatus(Integer lifeCycleStatus){
		this.lifeCycleStatus = lifeCycleStatus;
	}

	/**
	 * 获得 库存状态（保留）.
	 *
	 * @return the 库存状态（保留）
	 */
	@Column(name = "STOCK_STATUS")
	public Integer getStockStatus(){
		return stockStatus;
	}

	/**
	 * 设置 库存状态（保留）.
	 *
	 * @param stockStatus
	 *            the new 库存状态（保留）
	 */
	public void setStockStatus(Integer stockStatus){
		this.stockStatus = stockStatus;
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
	 * 获得 商品标签列表.
	 *
	 * @return the 商品标签列表
	 */
	@FieldBridge(impl = SkuCategoryBridge.class)
	@Field(name = "category",store = Store.NO,index = org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "T_MA_SC_SKU_RELATION",joinColumns = @JoinColumn(name = "SKU_ID",referencedColumnName = "ID"),inverseJoinColumns = @JoinColumn(name = "SKU_CATEGORY_ID",referencedColumnName = "ID"))
	@OrderBy("id")
	public List<SkuCategory> getSkuCategories(){
		return skuCategories;
	}

	/**
	 * 设置 商品标签列表.
	 *
	 * @param skuCategories
	 *            the new 商品标签列表
	 */
	public void setSkuCategories(List<SkuCategory> skuCategories){
		this.skuCategories = skuCategories;
	}

	/**
	 * 获得 shop top sku category.
	 *
	 * @return the shop top sku category
	 */
	@Transient
	public SkuCategory getShopTopSkuCategory(){
		for (SkuCategory sc : skuCategories){
			if (Boolean.TRUE.equals(sc.getIsStaffCate())){
				return sc;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sku other = (Sku) obj;
		if (id == null){
			if (other.id != null)
				return false;
		}else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * 获得 商品的动态属性集，其中key就是动态属性，value是其对应的值，所有类型的值在这里都以字符串表示.
	 *
	 * @return the 商品的动态属性集，其中key就是动态属性，value是其对应的值，所有类型的值在这里都以字符串表示
	 */
	@FieldBridge(impl = SkuDynaPropertyBridge.class)
	@Field(name = "prop",store = Store.NO,index = org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
	@ElementCollection
	@JoinTable(name = "T_MA_SKU_DP_VALUE",joinColumns = @JoinColumn(name = "SKU_ID"))
	@Column(name = "VALUE",length = 2000)
	@MapKeyJoinColumn(name = "DYN_PROP_ID",referencedColumnName = "ID")
	public Map<SkuDynaPropSetting, String> getSkuDpValues(){
		return skuDpValues;
	}

	/**
	 * 设置 商品的动态属性集，其中key就是动态属性，value是其对应的值，所有类型的值在这里都以字符串表示.
	 *
	 * @param skuDpValues
	 *            the new 商品的动态属性集，其中key就是动态属性，value是其对应的值，所有类型的值在这里都以字符串表示
	 */
	public void setSkuDpValues(Map<SkuDynaPropSetting, String> skuDpValues){
		this.skuDpValues = skuDpValues;
	}
}
