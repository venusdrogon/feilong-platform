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
package com.jumbo.shop.manager.test;

import loxia.support.cache.annotation.CacheEvict;
import loxia.support.cache.annotation.CacheParam;
import loxia.support.cache.annotation.Cacheable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 23, 2013 6:50:49 PM
 */
@Service
public class CreateManagerImpl implements CreateManager{

	private static final Logger	log	= LoggerFactory.getLogger(CreateManagerImpl.class);

	@Cacheable
	public String testCreate(@CacheParam("name") String name){
		log.info(name);
		return name;
	}

	@CacheEvict({ "CreateManagerImpl.testCreate"})
	public void publicSku(String name){
		log.info("publicSku" + name);
	}
}
