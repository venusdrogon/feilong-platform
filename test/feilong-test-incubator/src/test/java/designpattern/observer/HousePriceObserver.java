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
package designpattern.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class HousePriceObserver.
 */
public class HousePriceObserver implements Observer{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(HousePriceObserver.class);

	/** The name. */
	private String				name;

	/**
	 * The Constructor.
	 *
	 * @param name
	 *            the name
	 */
	public HousePriceObserver(String name){
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable obj,Object arg){

		if (log.isDebugEnabled()){
			log.debug("update:" + obj.toString());
		}
		if (arg instanceof Float){ // 判断参数类型  
			log.debug(this.name + "观察到价格更改为：" + arg);
		}
	}
}