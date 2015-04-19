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

package org.springframework.core;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.res.SkuItem;
import org.springframework.core.res.SkuItemRepositoryImpl4;

import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class GenericTypeResolverTest.
 */
public class GenericTypeResolverTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(GenericTypeResolverTest.class);

    /**
     * TestGenericTypeResolverTest.
     */
    @Test
    public void testGenericTypeResolverTest(){
        SkuItemRepositoryImpl4<SkuItem> skuItemRepositoryImpl4 = new SkuItemRepositoryImpl4<>();
        log.info(JsonUtil.format(GenericTypeResolver.getTypeVariableMap(skuItemRepositoryImpl4.getClass())));
    }
}
