/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.commons.core.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-6-1 下午7:19:47
 */
public class ClassUtil{

	private static final Logger	log	= LoggerFactory.getLogger(ClassUtil.class);

	/**
	 * debug
	 * 
	 * @param clz
	 */
	public static void debug(Class<? extends Object> clz){
		log.debug("clz.getComponentType():{}", clz.getComponentType());
		// 用来判断指定的Class类是否为一个基本类型。
		log.debug("clz.isPrimitive():{}", clz.isPrimitive());
		log.debug("clz.isLocalClass():{}", clz.isLocalClass());
		log.debug("clz.isMemberClass():{}", clz.isMemberClass());
		log.debug("clz.isSynthetic():{}", clz.isSynthetic());
		log.debug("clz.isArray():{}", clz.isArray());
		log.debug("clz.isAnnotation():{}", clz.isAnnotation());
		log.debug("clz.isAnonymousClass():{}", clz.isAnonymousClass());
		log.debug("clz.isEnum():{}", clz.isEnum());
	}
}
