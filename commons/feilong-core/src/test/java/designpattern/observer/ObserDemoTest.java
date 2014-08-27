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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ObserDemoTest.
 */
public class ObserDemoTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ObserDemoTest.class);

	/**
	 * TestObserDemoTest.
	 */
	@Test
	public void testObserDemoTest(){

		House house = new House(1000000);

		HousePriceObserver housePriceObserver1 = new HousePriceObserver("购房者A");
		HousePriceObserver housePriceObserver2 = new HousePriceObserver("购房者B");
		HousePriceObserver housePriceObserver3 = new HousePriceObserver("购房者C");

		house.addObserver(housePriceObserver1); // 加入观察者  
		house.addObserver(housePriceObserver2); // 加入观察者  
		house.addObserver(housePriceObserver3); // 加入观察者  

		log.debug("countObservers:" + house.countObservers());
		log.debug("hasChanged:" + house.hasChanged());
		log.debug(house.toString());// 输出房子价格

		house.setPrice(666666); // 修改房子价格  
		log.debug("hasChanged:" + house.hasChanged());
		log.debug(house.toString());// 输出房子价格
	}
}