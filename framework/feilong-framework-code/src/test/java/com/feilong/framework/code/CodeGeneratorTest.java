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
package com.feilong.framework.code;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

/**
 * The Class CodeGeneratorTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 3, 2013 5:14:45 PM
 */
public class CodeGeneratorTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(CodeGeneratorTest.class);

    /**
     * Creates the order code.
     */
    @Test
    public void createOrderCode(){
        // log.info(CodeGenerator.createOrderCode(111121L));
        // log.info(CodeGenerator.createOrderCode(1L));
        // log.info(CodeGenerator.createOrderCode(10L));
        // log.info(CodeGenerator.createOrderCode(101L));
        // log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2013-01-01 00:08:02", DatePattern.commonWithTime), 1L));
        // log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2020-11-11 12:31:23", DatePattern.commonWithTime), 111141L));
        // log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2099-12-31 23:59:45", DatePattern.commonWithTime), 1161L));
        // log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2035-04-22 08:31:35", DatePattern.commonWithTime), 203881L));
        // log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2044-12-31 10:21:46", DatePattern.commonWithTime), 35191L));
        log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2044-12-31 10:21:46", DatePattern.COMMON_DATE_AND_TIME), 35191L, 555L));
        log.info(CodeGenerator.createOrderCode(
                        DateUtil.string2Date("2099-12-31 23:59:45", DatePattern.COMMON_DATE_AND_TIME),
                        1161L,
                        5555555L));
    }

    /**
     * Creates the order code1.
     */
    @Test
    public void createOrderCode1(){
        for (int i = 0, j = 100; i < j; ++i){
            log.info(CodeGenerator.createOrderCode(
                            DateUtil.string2Date("2044-12-31 10:21:46", DatePattern.COMMON_DATE_AND_TIME),
                            35191L,
                            555L));
        }
    }

    /**
     * Creates the return order code.
     */
    @Test
    public void createReturnOrderCode(){
        log.info(CodeGenerator.createReturnOrderCode(111121L, 5555555L));
    }

    /**
     * Creates the trade no.
     */
    @Test
    public void createTradeNo(){
        log.info(CodeGenerator.createTradeNo(5545L, 88));
        log.info(CodeGenerator.createTradeNo(5545L, 1));
    }
}
