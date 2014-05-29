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
package com.feilong.commons.core.tools.json;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.test.User;
import com.feilong.test.UserAddress;
import com.feilong.test.UserInfo;

public class JsonlibTest{

	private static final Logger	log	= LoggerFactory.getLogger(JsonlibTest.class);

	@Test
	public void name() throws IllegalAccessException,InvocationTargetException,NoSuchMethodException{
		String json = "{name=\"json\",bool:true,int:1,double:2.2,func:function(a){ return a; },array:[1,2]}";
		JSONObject jsonObject = JSONObject.fromObject(json);
		Object bean = JSONObject.toBean(jsonObject);

		Assert.assertEquals(jsonObject.get("name"), PropertyUtils.getProperty(bean, "name"));
		Assert.assertEquals(jsonObject.get("bool"), PropertyUtils.getProperty(bean, "bool"));
		Assert.assertEquals(jsonObject.get("int"), PropertyUtils.getProperty(bean, "int"));
		Assert.assertEquals(jsonObject.get("double"), PropertyUtils.getProperty(bean, "double"));
		Assert.assertEquals(jsonObject.get("func"), PropertyUtils.getProperty(bean, "func"));
		List expected = JSONArray.toList(jsonObject.getJSONArray("array"));
		Assert.assertEquals(expected, (List) PropertyUtils.getProperty(bean, "array"));
	}

	@Test
	public void name1(){
		String json = getJsonString();

		User user = JsonUtil.toBean(json, User.class);
		user.setId(10L);
		json = JsonUtil.format(user);
		log.info(json);
	}

	@Test
	public void toBean(){
		String json = getJsonString();

		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("userAddresseList", UserAddress.class);

		User user = JsonUtil.toBean(json, User.class, classMap);
		log.info(JsonUtil.format(user));
	}

	/**
	 * @return
	 */
	@Test
	public void testJsonString(){
		getJsonString();
	}

	/**
	 * @return
	 */
	private String getJsonString(){
		User user = new User();

		user.setId(8L);
		user.setName("feilong");

		String[] loves = { "桔子", "香蕉" };
		user.setLoves(loves);

		UserInfo userInfo = new UserInfo();

		userInfo.setAge(10);
		user.setUserInfo(userInfo);

		UserAddress userAddress1 = new UserAddress();
		userAddress1.setAddress("上海市闸北区万荣路1188号H座109-118室");

		UserAddress userAddress2 = new UserAddress();
		userAddress2.setAddress("上海市闸北区阳城路280弄25号802室(阳城贵都)");

		UserAddress[] userAddresses = { userAddress1, userAddress2 };
		user.setUserAddresses(userAddresses);

		List<UserAddress> userAddresseList = new ArrayList<UserAddress>();
		userAddresseList.add(userAddress1);
		userAddresseList.add(userAddress2);
		user.setUserAddresseList(userAddresseList);

		String json;
		// json= JsonUtil.format(user);

		json = JsonUtil.toJSON(user).toString(4, 4);
		log.info(json);
		return json;
	}
}
