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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.feilong.commons.core.date.TimeInterval;
import com.feilong.commons.core.lang.ThreadUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
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
public class MultiThreadMemberManagerTest //extends AbstractJUnit4SpringContextTests
{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(MultiThreadMemberManagerTest.class);

    /** The member manager. */
    @Autowired
    private MemberManager       memberManager;

    /** The sales order manager. */
    @Autowired
    private SalesOrderManager   salesOrderManager;

    /**
     * Test add user2.
     * 
     * @throws InterruptedException
     */
    @Test
    public final void testAddUser2() throws InterruptedException{
        for (int i = 0; i < 2; ++i){
            Thread thread = new Thread(new TestRunnable());
            thread.start();
        }
        Thread.sleep((long) (TimeInterval.SECONDS_PER_MINUTE * 1000 * 0.2));
    }

    /**
     * The Class TestRunnable.
     */
    class TestRunnable implements Runnable{

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run(){
            if (log.isInfoEnabled()){
                log.info("run thread,thread info:{}", JsonUtil.format(ThreadUtil.getCurrentThreadMapForLog()));
            }
            memberManager.addUser("feilong");
            salesOrderManager.addUser("feilong");
        }
    }
}
