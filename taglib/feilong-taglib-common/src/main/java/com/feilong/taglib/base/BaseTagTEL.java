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
package com.feilong.taglib.base;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class BaseTagTEL.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-13 上午1:35:28
 */
public abstract class BaseTagTEL extends TagExtraInfo{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(BaseTagTEL.class);

	/**
	 * 显示 tagData 里面的信息 一般用于 debug.
	 * 
	 * @param tagData
	 *            the tag data
	 */
	protected Map<String, Object> toMap(TagData tagData){
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> attributes = tagData.getAttributes();
		while (attributes.hasMoreElements()){
			String key = attributes.nextElement();
			Object value = tagData.getAttribute(key);
			map.put(key, value);
		}
		return map;
	}
}
