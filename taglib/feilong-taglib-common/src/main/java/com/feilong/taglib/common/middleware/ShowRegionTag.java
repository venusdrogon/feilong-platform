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
package com.feilong.taglib.common.middleware;

import java.math.BigDecimal;

import com.feilong.taglib.base.AbstractCommonTag;
import com.feilong.tools.middleware.RegionUtil;

/**
 * 显示地区(除去直辖市中的城市).
 * 
 * @author 金鑫 2009-12-4下午01:54:34
 */
public class ShowRegionTag extends AbstractCommonTag{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** 省份名称. */
	private String				provinceName;

	/** 城市名称. */
	private String				cityName;

	/** 地区名称. */
	private String				districtName;

	/** 省份id. */
	private BigDecimal			provinceId;

	/** 显示等级 支持 3全部显示,2 如果是省份显示省份+城市,如果是直辖市,显示直辖市+区县 默认3(省份+城市+区县). */
	private int					showLevel			= 3;

	/* (non-Javadoc)
	 * @see com.feilong.taglib.base.AbstractCommonTag#writeContent()
	 */
	@Override
	public String writeContent(){
		/**
		 * 是否是直辖市
		 */
		boolean isMunicipality = RegionUtil.isMunicipality(provinceName);
		// 显示2级
		if (2 == showLevel){
			if (isMunicipality){
				return cityName + districtName;
			}
			return provinceName + cityName;
		}
		if (isMunicipality){
			return provinceName + districtName;
		}
		return provinceName + cityName + districtName;
	}

	/**
	 * 设置 省份名称.
	 * 
	 * @param provinceName
	 *            the provinceName to set
	 */
	public void setProvinceName(String provinceName){
		this.provinceName = provinceName;
	}

	/**
	 * 设置 城市名称.
	 * 
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	/**
	 * 设置 地区名称.
	 * 
	 * @param districtName
	 *            the districtName to set
	 */
	public void setDistrictName(String districtName){
		this.districtName = districtName;
	}

	/**
	 * 设置 省份id.
	 * 
	 * @param provinceId
	 *            the provinceId to set
	 */
	public void setProvinceId(BigDecimal provinceId){
		this.provinceId = provinceId;
	}

	/**
	 * 设置 显示等级 支持 3全部显示,2 如果是省份显示省份+城市,如果是直辖市,显示直辖市+区县 默认3(省份+城市+区县).
	 * 
	 * @param showLevel
	 *            the showLevel to set
	 */
	public void setShowLevel(int showLevel){
		this.showLevel = showLevel;
	}
}
