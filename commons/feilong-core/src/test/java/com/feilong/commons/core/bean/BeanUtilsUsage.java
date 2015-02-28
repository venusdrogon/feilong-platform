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
package com.feilong.commons.core.bean;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.bean.command.Address;
import com.feilong.commons.core.bean.command.Customer;

/**
 * The Class BeanUtilsUsage.
 */
public class BeanUtilsUsage{

	private static final Logger	log	= LoggerFactory.getLogger(BeanUtilsUsage.class);

	/**
	 * The main method.
	 *
	 * @param args
	 *            the args
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception{
		demoNormalJavaBeans();
		demoDynaBeans();
	}

	/**
	 * Demo normal java beans.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public static void demoNormalJavaBeans() throws Exception{

		log.debug(StringUtils.center(" demoNormalJavaBeans ", 40, "="));

		// data setup  
		Address addr1 = new Address("CA1234", "xxx", "Los Angeles", "USA");
		Address addr2 = new Address("100000", "xxx", "Beijing", "China");
		Address[] addrs = new Address[2];
		addrs[0] = addr1;
		addrs[1] = addr2;
		Customer cust = new Customer(123, "John Smith", addrs);

		// accessing the city of first address  
		String cityPattern = "addresses[0].city";
		String name = (String) PropertyUtils.getSimpleProperty(cust, "name");
		String city = (String) PropertyUtils.getProperty(cust, cityPattern);
		Object[] rawOutput1 = new Object[] { "The city of customer ", name, "'s first address is ", city, "." };
		log.debug(StringUtils.join(rawOutput1));

		// setting the zipcode of customer's second address  
		String zipPattern = "addresses[1].zipCode";
		if (PropertyUtils.isWriteable(cust, zipPattern)){//PropertyUtils  
			log.debug("Setting zipcode ...");
			PropertyUtils.setProperty(cust, zipPattern, "200000");//PropertyUtils  
		}
		String zip = (String) PropertyUtils.getProperty(cust, zipPattern);//PropertyUtils  
		Object[] rawOutput2 = new Object[] { "The zipcode of customer ", name, "'s second address is now ", zip, "." };
		log.debug(StringUtils.join(rawOutput2));
	}

	/**
	 * Demo dyna beans.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public static void demoDynaBeans() throws Exception{

		log.debug(StringUtils.center(" demoDynaBeans ", 40, "="));

		// creating a DynaBean  
		DynaProperty[] dynaBeanProperties = new DynaProperty[] {//DynaProperty  
		new DynaProperty("name", String.class), new DynaProperty("inPrice", Double.class), new DynaProperty("outPrice", Double.class), };
		BasicDynaClass cargoClass = new BasicDynaClass("Cargo", BasicDynaBean.class, dynaBeanProperties); //BasicDynaClass  BasicDynaBean  
		DynaBean cargo = cargoClass.newInstance();//DynaBean  

		// accessing a DynaBean  
		cargo.set("name", "Instant Noodles");
		cargo.set("inPrice", new Double(21.3));
		cargo.set("outPrice", new Double(23.8));
		log.debug("name: " + cargo.get("name"));
		log.debug("inPrice: " + cargo.get("inPrice"));
		log.debug("outPrice: " + cargo.get("outPrice"));
	}
}
