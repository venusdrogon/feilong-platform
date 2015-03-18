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
package com.jumbo.shop.manager.test;

import loxia.support.cache.annotation.CacheEvict;
import loxia.support.cache.annotation.CacheParam;
import loxia.support.cache.annotation.Cacheable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The Class CreateManagerImpl.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 23, 2013 6:50:49 PM
 */
@Service
public class CreateManagerImpl implements CreateManager{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(CreateManagerImpl.class);

    /* (non-Javadoc)
     * @see com.jumbo.shop.manager.test.CreateManager#testCreate(java.lang.String)
     */
    @Override
    @Cacheable
    public String testCreate(@CacheParam("name") String name){
        log.info(name);
        return name;
    }

    /* (non-Javadoc)
     * @see com.jumbo.shop.manager.test.CreateManager#publicSku(java.lang.String)
     */
    @Override
    @CacheEvict({ "CreateManagerImpl.testCreate" })
    public void publicSku(String name){
        log.info("publicSku" + name);
    }
}
