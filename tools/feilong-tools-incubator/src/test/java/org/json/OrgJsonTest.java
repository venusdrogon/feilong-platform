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
package org.json;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrgJsonTest{

	private static final Logger	log	= LoggerFactory.getLogger(OrgJsonTest.class);

	@Test
	public void test(){
		String a = "{user:{name:'张三'},user2:{name:'张三'}}";
		try{
			JSONObject jsonObject = new JSONObject(a);
			JSONObject jSONArray_user = (JSONObject) jsonObject.getJSONObject("user");

			log.debug("jSONArray_user.length():{}", jSONArray_user.length());
			log.debug("jsonObject:{}", jsonObject);
			log.debug("jSONArray_user:{}", jSONArray_user);

		}catch (JSONException e){
			e.printStackTrace();
		}
	}
}
