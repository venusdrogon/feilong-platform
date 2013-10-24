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
package org.springframework.context;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-29 下午4:14:43
 */
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class ApplicationContextTest extends AbstractJUnit4SpringContextTests{

	private static final Logger	log	= LoggerFactory.getLogger(ApplicationContextTest.class);

	@Test
	public void test(){
		log.debug("applicationContext.getBeanDefinitionCount():{}", applicationContext.getBeanDefinitionCount());

		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		log.debug("applicationContext.getBeanDefinitionNames():{}", beanDefinitionNames);
		for (String beanDefinitionName : beanDefinitionNames){
			Object bean = applicationContext.getBean(beanDefinitionName);

			String scope = applicationContext.isPrototype(beanDefinitionName) ? "Prototype" : (applicationContext.isSingleton(beanDefinitionName) ? "Singleton"
					: "");
			//applicationContext.FACTORY_BEAN_PREFIX;
			Object[] objects = { beanDefinitionName, bean.getClass().getName(), scope };
			log.debug("[beanDefinitionName]:{},[bean]:{},scope:[{}]", objects);
		}

		log.debug("applicationContext.getDisplayName():{}", applicationContext.getDisplayName());

		log.debug("applicationContext.getId():{}", applicationContext.getId());
		//3.1.0
		//log.debug("applicationContext.getEnvironment():{}", applicationContext.getEnvironment());
		log.debug("applicationContext.getStartupDate():{}", applicationContext.getStartupDate());

	}
}
