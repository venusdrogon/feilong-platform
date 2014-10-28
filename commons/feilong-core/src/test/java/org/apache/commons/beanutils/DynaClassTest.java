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
package org.apache.commons.beanutils;

import java.util.HashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.entity.BackWarnEntity;
import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class DynaClassTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年10月21日 下午12:17:09
 * @since 1.0.8
 */
public class DynaClassTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(DynaClassTest.class);

	/**
	 * Testenclosing_type.
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 */
	@Test
	public void testBasicDynaClass() throws IllegalAccessException,InstantiationException{
		DynaProperty[] props = new DynaProperty[] {
				new DynaProperty("address", java.util.Map.class),
				new DynaProperty("firstName", String.class),
				new DynaProperty("lastName", String.class) };
		BasicDynaClass dynaClass = new BasicDynaClass("employee", BasicDynaBean.class, props);

		DynaBean employee = dynaClass.newInstance();
		employee.set("address", new HashMap());
		employee.set("firstName", "Fred");
		employee.set("lastName", "Flintstone");

		log.info(JsonUtil.format(employee));
	}

	/**
	 * Test wrap dyna bean.
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 */
	@Test
	public void testWrapDynaBean() throws IllegalAccessException,InstantiationException{

		BackWarnEntity backWarnEntity = new BackWarnEntity();
		WrapDynaBean employee = new WrapDynaBean(backWarnEntity);

		employee.set("description", "lalalalal");

		log.info(JsonUtil.format(employee));
	}
}
