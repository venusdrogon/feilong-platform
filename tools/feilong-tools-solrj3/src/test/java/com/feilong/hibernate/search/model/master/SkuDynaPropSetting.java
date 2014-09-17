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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLockType;

import com.feilong.hibernate.search.model.BaseModel;

/**
 * 商品动态属性。是系统中重要的基础实体。对于一个商品模板，可以添加任意多个动态属性。这些动态属性都会被继承到商品上。.
 *
 * @author benjamin
 * @see Sku
 */
@Entity
@Table(name = "T_MA_SKU_DYNA_PROP_SETTING")
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.VERSION)
public class SkuDynaPropSetting implements BaseModel,Serializable,Comparable<SkuDynaPropSetting>{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID			= 4296809551576724581L;

	/** 外部jdi积分. */
	public static final String	NAME_EXTERNAL_JDI_POINTS	= "外部jdi积分";

	/** PK. */
	private Long				id;

	/** 属性名称. */
	private String				name;

	/** 属性类型，具体类型列表见商品. */
	private String				dataType;

	/** 会员是否可以编辑，一般来说商品属性会员都只是查看，不能修改，但某些属性可能客户在购买时有定制需求时就需要出现此属性的修改界面，如列表属性中挑选一个候选值。 此属性曾用于多类型商品的自动显示，现基本不使用，但仍保持此概念. */
	private Boolean				isFinalCustEditable;

	/** 是否允许为空. */
	private Boolean				isblankable;

	/** 是否显示给会员查看. */
	private Boolean				isdisplayable;

	/** 是否启用，和生命周期的意义不一样，一个有效的动态属性可以不启用，此设定可以更加灵活的控制商品动态属性的展示. */
	private Boolean				isenable;

	/** 是否可检索（保留）. */
	private Boolean				isqueryable;

	/** 属性组. */
	private String				group;

	/** 创建时间. */
	private Date				createTime					= new Date();

	/** 生命周期. */
	private Integer				lifeCycleStatus				= DEFAULT_STATUS;

	/** Version. */
	private Date				version;

	/** 商品的动态属性的具体内容. */
	private Map<Sku, String>	skuDpValues					= new HashMap<Sku, String>();

	/**
	 * 获得 pK.
	 *
	 * @return the pK
	 */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "SEQ_SDPS",sequenceName = "S_T_MA_SKU_DYNA_PROP_SETTING",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_SDPS")
	public Long getId(){
		return this.id;
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
	 * 获得 是否允许为空.
	 *
	 * @return the 是否允许为空
	 */
	@Column(name = "ISBLANKABLE")
	public Boolean getIsblankable(){
		return this.isblankable;
	}

	/**
	 * 设置 是否允许为空.
	 *
	 * @param isblankable
	 *            the new 是否允许为空
	 */
	public void setIsblankable(Boolean isblankable){
		this.isblankable = isblankable;
	}

	/**
	 * 获得 是否显示给会员查看.
	 *
	 * @return the 是否显示给会员查看
	 */
	@Column(name = "ISDISPLAYABLE")
	public Boolean getIsdisplayable(){
		return this.isdisplayable;
	}

	/**
	 * 设置 是否显示给会员查看.
	 *
	 * @param isdisplayable
	 *            the new 是否显示给会员查看
	 */
	public void setIsdisplayable(Boolean isdisplayable){
		this.isdisplayable = isdisplayable;
	}

	/**
	 * 获得 是否启用，和生命周期的意义不一样，一个有效的动态属性可以不启用，此设定可以更加灵活的控制商品动态属性的展示.
	 *
	 * @return the 是否启用，和生命周期的意义不一样，一个有效的动态属性可以不启用，此设定可以更加灵活的控制商品动态属性的展示
	 */
	@Column(name = "ISENABLE")
	public Boolean getIsenable(){
		return this.isenable;
	}

	/**
	 * 设置 是否启用，和生命周期的意义不一样，一个有效的动态属性可以不启用，此设定可以更加灵活的控制商品动态属性的展示.
	 *
	 * @param isenable
	 *            the new 是否启用，和生命周期的意义不一样，一个有效的动态属性可以不启用，此设定可以更加灵活的控制商品动态属性的展示
	 */
	public void setIsenable(Boolean isenable){
		this.isenable = isenable;
	}

	/**
	 * 获得 是否可检索（保留）.
	 *
	 * @return the 是否可检索（保留）
	 */
	@Column(name = "ISQUERYABLE")
	public Boolean getIsqueryable(){
		return this.isqueryable;
	}

	/**
	 * 设置 是否可检索（保留）.
	 *
	 * @param isqueryable
	 *            the new 是否可检索（保留）
	 */
	public void setIsqueryable(Boolean isqueryable){
		this.isqueryable = isqueryable;
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
	 * 获得 属性类型，具体类型列表见商品.
	 *
	 * @return the 属性类型，具体类型列表见商品
	 */
	@Column(name = "DATA_TYPE",length = 20)
	public String getDataType(){
		return dataType;
	}

	/**
	 * 设置 属性类型，具体类型列表见商品.
	 *
	 * @param dataType
	 *            the new 属性类型，具体类型列表见商品
	 */
	public void setDataType(String dataType){
		this.dataType = dataType;
	}

	/**
	 * 获得 会员是否可以编辑，一般来说商品属性会员都只是查看，不能修改，但某些属性可能客户在购买时有定制需求时就需要出现此属性的修改界面，如列表属性中挑选一个候选值。 此属性曾用于多类型商品的自动显示，现基本不使用，但仍保持此概念.
	 *
	 * @return the 会员是否可以编辑，一般来说商品属性会员都只是查看，不能修改，但某些属性可能客户在购买时有定制需求时就需要出现此属性的修改界面，如列表属性中挑选一个候选值。 此属性曾用于多类型商品的自动显示，现基本不使用，但仍保持此概念
	 */
	@Column(name = "IS_FINAL_CUST_EDITABLE")
	public Boolean getIsFinalCustEditable(){
		return isFinalCustEditable;
	}

	/**
	 * 设置 会员是否可以编辑，一般来说商品属性会员都只是查看，不能修改，但某些属性可能客户在购买时有定制需求时就需要出现此属性的修改界面，如列表属性中挑选一个候选值。 此属性曾用于多类型商品的自动显示，现基本不使用，但仍保持此概念.
	 *
	 * @param isFinalCustEditable
	 *            the new 会员是否可以编辑，一般来说商品属性会员都只是查看，不能修改，但某些属性可能客户在购买时有定制需求时就需要出现此属性的修改界面，如列表属性中挑选一个候选值。 此属性曾用于多类型商品的自动显示，现基本不使用，但仍保持此概念
	 */
	public void setIsFinalCustEditable(Boolean isFinalCustEditable){
		this.isFinalCustEditable = isFinalCustEditable;
	}

	/**
	 * 获得 属性名称.
	 *
	 * @return the 属性名称
	 */
	@Column(name = "NAME",length = 50)
	public String getName(){
		return name;
	}

	/**
	 * 设置 属性名称.
	 *
	 * @param name
	 *            the new 属性名称
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 获得 属性组.
	 *
	 * @return the 属性组
	 */
	@Column(name = "NAME_GROUP",length = 100)
	public String getGroup(){
		return group;
	}

	/**
	 * 设置 属性组.
	 *
	 * @param group
	 *            the new 属性组
	 */
	public void setGroup(String group){
		this.group = group;
	}

	/**
	 * 获得 商品的动态属性的具体内容.
	 *
	 * @return the 商品的动态属性的具体内容
	 */
	@ElementCollection
	@JoinTable(name = "T_MA_SKU_DP_VALUE",joinColumns = @JoinColumn(name = "DYN_PROP_ID"))
	@Column(name = "VALUE")
	@MapKeyJoinColumn(name = "SKU_ID",referencedColumnName = "ID")
	public Map<Sku, String> getSkuDpValues(){
		return skuDpValues;
	}

	/**
	 * 设置 商品的动态属性的具体内容.
	 *
	 * @param skuDpValues
	 *            the new 商品的动态属性的具体内容
	 */
	public void setSkuDpValues(Map<Sku, String> skuDpValues){
		this.skuDpValues = skuDpValues;
	}

	/**
	 * 获得 condition.
	 *
	 * @return the condition
	 */
	@Transient
	public String getCondition(){
		if (!isblankable){
			return "notnull";
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		SkuDynaPropSetting other = (SkuDynaPropSetting) obj;
		if (id == null){
			if (other.id != null)
				return false;
		}else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(SkuDynaPropSetting sdps){
		if (sdps == null)
			return 1;
		if (this.id == null)
			return 0;
		return this.id.compareTo(sdps.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erry.model.BaseModel#getModelClass()
	 */
	@Transient
	public Class<?> getModelClass(){
		return SkuDynaPropSetting.class;
	}
}
