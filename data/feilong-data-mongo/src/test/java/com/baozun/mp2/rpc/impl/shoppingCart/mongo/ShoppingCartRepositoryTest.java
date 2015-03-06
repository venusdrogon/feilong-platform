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
package com.baozun.mp2.rpc.impl.shoppingCart.mongo;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.baozun.mp2.rpc.impl.shoppingCart.mongo.documents.ShoppingCart;
import com.baozun.mp2.rpc.impl.shoppingCart.mongo.repositories.ShoppingCartRepository;
import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class ShoppingCartRepositoryTest.
 */
@ContextConfiguration(locations = { "classpath:spring-mongo.xml" })
public class ShoppingCartRepositoryTest extends AbstractJUnit4SpringContextTests{

    /** The Constant log. */
    private static final Logger    log = LoggerFactory.getLogger(ShoppingCartRepositoryTest.class);

    /** The shopping cart repository. */
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    /**
     * TestShoppingCartServiceImplTest.
     */
    @Test
    public void testShoppingCartServiceImplTest(){

        ShoppingCart cartSku = shoppingCartRepository.findById("543cfedd07dd1c8dad126879");

        if (log.isDebugEnabled()){
            log.debug(JsonUtil.format(cartSku));
        }

    }

    /**
     * Test save.
     */
    @Test
    public void testSave(){

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setId("5");

        shoppingCart.setMemberId(5L);
        shoppingCart.setQuantity(5);
        shoppingCart.setShopId(5L);
        shoppingCart.setSkuId(5L);

        final ShoppingCart save = shoppingCartRepository.save(shoppingCart);

        log.debug(JsonUtil.format(save));
    }

    /**
     * Test save.
     */
    @Test
    public void testCount(){
        final Long count = shoppingCartRepository.count();
        log.debug("" + count);
    }

    /**
     * Test find all.
     */
    @Test
    public void testFindAll(){
        final List<ShoppingCart> findAll = shoppingCartRepository.findAll();
        log.debug(JsonUtil.format(findAll));
    }

    /**
     * findOne.
     */
    @Test
    public void testFindOne(){
        ShoppingCart shoppingCart = shoppingCartRepository.findOne("5");
        log.debug(JsonUtil.format(shoppingCart));
    }

}
