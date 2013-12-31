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
package com.feilong.taglib.common.middleware;

import java.math.BigDecimal;

import com.feilong.taglib.base.AbstractCommonTag;
import com.feilong.tools.middleware.RegionUtil;

/**
 * 显示地区(除去直辖市中的城市)
 * 
 * @author 金鑫 2009-12-4下午01:54:34
 */
public class ShowRegionTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 省份名称
	 */
	private String				provinceName;

	/**
	 * 城市名称
	 */
	private String				cityName;

	/**
	 * 地区名称
	 */
	private String				districtName;

	/**
	 * 省份id
	 */
	private BigDecimal			provinceId;

	/**
	 * 显示等级 支持 3全部显示,2 如果是省份显示省份+城市,如果是直辖市,显示直辖市+区县 默认3(省份+城市+区县)
	 */
	private int					showLevel			= 3;

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
	 * @param provinceName
	 *            the provinceName to set
	 */
	public void setProvinceName(String provinceName){
		this.provinceName = provinceName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	/**
	 * @param districtName
	 *            the districtName to set
	 */
	public void setDistrictName(String districtName){
		this.districtName = districtName;
	}

	/**
	 * @param provinceId
	 *            the provinceId to set
	 */
	public void setProvinceId(BigDecimal provinceId){
		this.provinceId = provinceId;
	}

	/**
	 * @param showLevel
	 *            the showLevel to set
	 */
	public void setShowLevel(int showLevel){
		this.showLevel = showLevel;
	}
}
