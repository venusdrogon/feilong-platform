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
package com.feilong.spring.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.feilong.spring.manager.java.MemberManager;
import com.feilong.spring.manager.java.SalesOrderManager;

/**
 * The Class MemberManagerTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.1 2015年4月6日 上午5:40:44
 * @since 1.1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class MemberManagerTest //extends AbstractJUnit4SpringContextTests
{

    /** The member manager. */
    @Autowired
    private MemberManager     memberManager;

    /** The sales order manager. */
    @Autowired
    private SalesOrderManager salesOrderManager;

    /**
     * Test method for {@link com.feilong.spring.manager.java.MemberManager#getUser(java.lang.String)}.
     */
    @Test
    public final void testGetUser(){
        memberManager.getUser("feilong");
    }

    /**
     * Test get user1.
     */
    @Test
    public final void testGetUser1(){
        memberManager.getUser("feilong");
        salesOrderManager.getUser("feilong");
    }

    /**
     * Test add user.
     */
    @Test
    public final void testAddUser(){
        memberManager.addUser("feilong");
    }

    /**
     * Test add user1.
     */
    @Test
    public final void testAddUser1(){
        memberManager.addUser("feilong");
        salesOrderManager.addUser("feilong");
    }

    /**
     * Test get user read only.
     */
    @Test
    public final void testGetUserReadOnly(){
        memberManager.getUserReadOnly("feilong");
    }
}