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
package com.feilong.office.excel.loxia;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;

/**
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月29日 上午10:59:29
 * @since 1.0.8
 */
public abstract class BaseTrainSignUpTest extends BaseLoxiaExcelReaderTest implements TrainConstants{

	private static final Logger				log			= LoggerFactory.getLogger(BaseTrainSignUpTest.class);

	/** The train sign up entity list. */
	protected List<TrainSignUpEntity>		trainSignUpEntityList;

	/** The comparator. */
	protected Comparator<TrainSignUpEntity>	comparator	= getTrainSignUpEntityComparator();

	/**
	 * Inits the.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Before
	public void init() throws IOException{ //取到list
		trainSignUpEntityList = getList(configuration, trainSignUpSheet, trainSignUpExcel, dataName, sheetNo);
		log.debug(JsonUtil.format(trainSignUpEntityList));
	}

	/**
	 * 获得 train sign up entity comparator.
	 *
	 * @return the train sign up entity comparator
	 */
	private Comparator<TrainSignUpEntity> getTrainSignUpEntityComparator(){
		return new Comparator<TrainSignUpEntity>(){

			public int compare(TrainSignUpEntity o1,TrainSignUpEntity o2){
				String storeCategoryName = o1.getStoreCategoryName();
				String storeCategoryName2 = o2.getStoreCategoryName();

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
}
