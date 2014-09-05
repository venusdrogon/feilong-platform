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
package com.feilong.project.train;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.bean.BeanUtilException;
import com.feilong.commons.core.bean.PropertyUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.CollectionsUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.project.train.entity.TrainSignUpEmployeeEntity;
import com.feilong.project.train.entity.TrainSignUpEntity;
import com.feilong.tools.office.excel.loxia.LoxiaExcelUtil;

/**
 * The Class BaseTrainTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月29日 上午10:59:29
 * @since 1.0.8
 */
public abstract class BaseTrainTest extends LoxiaExcelUtil implements TrainTestConstants{

	/** The Constant log. */
	private static final Logger					log			= LoggerFactory.getLogger(BaseTrainTest.class);

	protected List<TrainSignUpEntity>			trainSignUpEntityList;

	protected List<TrainSignUpEmployeeEntity>	trainSignUpEmployeeEntityList;

	/** The comparator. */
	protected Comparator<TrainSignUpEntity>		comparator	= getTrainSignUpEntityComparator();

	/**
	 * Inits the.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Before
	public void init() throws IOException{ //取到list
		trainSignUpEmployeeEntityList = getList(
						CONFIGURATION,
						TRAINSIGNUPEMPLOYEE_SHEET,
						DATANAME_TRAINSIGNUPEMPLOYEEENTITYLIST,
						trainSignUpExcel,
						0);
		trainSignUpEntityList = getList(CONFIGURATION, TRAINSIGNUP_SHEET, DATANAME_TRAINSIGNUPLIST, trainSignUpExcel, 1);

		Map<String, List<TrainSignUpEmployeeEntity>> trainSignUpEmployeeEntityMapGroupByName = CollectionsUtil.group(
						trainSignUpEmployeeEntityList,
						"name");

		for (TrainSignUpEntity trainSignUpEntity : trainSignUpEntityList){
			String name = trainSignUpEntity.getName();
			List<TrainSignUpEmployeeEntity> list = trainSignUpEmployeeEntityMapGroupByName.get(name);

			if (Validator.isNullOrEmpty(list)){
				throw new NullPointerException("list can't be null/empty!");
			}
			if (Validator.isNullOrEmpty(list.size() != 1)){
				throw new IllegalArgumentException("when name is :[" + name + "],the trainSignUpEmployeeEntity list size is :"
								+ list.size() + "");
			}
			trainSignUpEntity.setTrainSignUpEmployeeEntity(list.get(0));

		}

		log.debug(JsonUtil.format(trainSignUpEmployeeEntityList));
		log.debug(JsonUtil.format(trainSignUpEntityList));
	}

	/**
	 * 获得 train sign up entity comparator.
	 *
	 * @return the train sign up entity comparator
	 */
	private Comparator<TrainSignUpEntity> getTrainSignUpEntityComparator(){
		return new Comparator<TrainSignUpEntity>(){

			public int compare(TrainSignUpEntity t1,TrainSignUpEntity t2){

				String storeCategoryName = t1.getTrainSignUpEmployeeEntity().getStoreCategoryName();
				String storeCategoryName2 = t2.getTrainSignUpEmployeeEntity().getStoreCategoryName();

				if (storeCategoryName.equals(storeCategoryName2)){
					return 0;
				}else if (storeCategoryName.length() > storeCategoryName2.length()){
					return 1;
				}else if (storeCategoryName.length() == storeCategoryName2.length()){
					return storeCategoryName.compareToIgnoreCase(storeCategoryName2);
				}
				return -1;
			}
		};
	}

	/**
	 * 获得 property value map.
	 *
	 * @param <O>
	 *            the generic type
	 * @param <V>
	 *            the value type
	 * @param objectList
	 *            the object list
	 * @param keyPropertyName
	 *            the key property name
	 * @param storeCategoryNamePropertyName
	 *            the store category name property name
	 * @param valuePropertyName
	 *            the value property name
	 * @return the property value map {@link LinkedHashMap}
	 * @throws NullPointerException
	 *             the null pointer exception
	 */
	protected static <O, V> Map<String, V> getPropertyValueMap(
					Collection<O> objectList,
					String keyPropertyName,
					String storeCategoryNamePropertyName,
					String valuePropertyName) throws NullPointerException{
		if (Validator.isNullOrEmpty(objectList)){
			throw new NullPointerException("objectList is null or empty!");
		}

		if (Validator.isNullOrEmpty(keyPropertyName)){
			throw new NullPointerException("keyPropertyName is null or empty!");
		}

		if (Validator.isNullOrEmpty(valuePropertyName)){
			throw new NullPointerException("valuePropertyName is null or empty!");
		}

		Map<String, V> map = new LinkedHashMap<String, V>();

		try{
			for (O bean : objectList){
				String key = (String) PropertyUtil.getProperty(bean, keyPropertyName);
				String storeCategoryName1 = "" + PropertyUtil.getProperty(bean, storeCategoryNamePropertyName);
				@SuppressWarnings("unchecked")
				V value = (V) PropertyUtil.getProperty(bean, valuePropertyName);

				map.put(key + "(" + storeCategoryName1 + ")", value);
			}
		}catch (BeanUtilException e){
			e.printStackTrace();
		}
		return map;
	}
}
