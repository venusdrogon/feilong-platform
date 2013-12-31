/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.date;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.entity.SelectEntity;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-6 下午3:11:10
 */
@Deprecated
public class SelectHelper{

	private static final Logger	log	= LoggerFactory.getLogger(SelectHelper.class);

	/** ************************************************************************************ */
	/**
	 * 获得年份集合
	 * 
	 * <pre>
	 * 
	 * 例如 要获得1949-2020年份集合
	 * 
	 * 则getYearList(1949,2020,true);
	 * 
	 * </pre>
	 * 
	 * @param beginYear
	 *            开始年份
	 * @param endYear
	 *            结束年份
	 * @param desc
	 *            是否倒序,年份大的排在上面
	 * @return 获得年份集合
	 * @since 1.0
	 */
	public static List<SelectEntity> getYearList(int beginYear,int endYear,boolean desc){
		List<SelectEntity> yearList = new ArrayList<SelectEntity>();
		SelectEntity selectEntity = null;
		// 倒序
		if (desc){
			for (int i = endYear; i >= beginYear; --i){
				selectEntity = new SelectEntity(i, i);
				yearList.add(selectEntity);
			}
		}
		// 顺序
		else{
			for (int i = beginYear; i <= endYear; ++i){
				selectEntity = new SelectEntity(i, i);
				yearList.add(selectEntity);
			}
		}
		return yearList;
	}

	/**
	 * 获得月份列表
	 * 
	 * @param desc
	 *            是否倒序,年份大的排在上面
	 * @return 获得月份列表
	 * @since 1.0
	 */
	public static List<SelectEntity> getMonthList(boolean desc){
		List<SelectEntity> monthList = new ArrayList<SelectEntity>();
		SelectEntity selectEntity = null;
		log.info("the param desc is:{}", desc);
		// 倒序
		if (desc){
			for (int i = 12; i >= 1; --i){
				selectEntity = new SelectEntity(i, i);
				monthList.add(selectEntity);
			}
		}
		// 顺序
		else{
			for (int i = 1; i <= 12; ++i){
				selectEntity = new SelectEntity(i, i);
				monthList.add(selectEntity);
			}
		}
		return monthList;
	}
}
