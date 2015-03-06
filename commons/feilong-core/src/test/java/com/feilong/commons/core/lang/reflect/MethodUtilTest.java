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
package com.feilong.commons.core.lang.reflect;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.entity.BackWarnEntity;
import com.feilong.commons.core.util.StringUtil;

/**
 * The Class MethodUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年7月15日 下午2:47:29
 * @since 1.0.7
 */
public class MethodUtilTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(MethodUtilTest.class);

    /**
     * Test.
     */
    @Test
    public final void testInvokeMethod(){
        if (log.isInfoEnabled()){
            BackWarnEntity backWarnEntity = new BackWarnEntity();
            String methodName = "getIsSuccess";
            Object[] params = new Object[0];
            log.info("" + MethodUtil.invokeMethod(backWarnEntity, methodName, params));
        }
    }

    /**
     * Test invoke static method.
     */
    @Test(expected = ReflectException.class)
    public final void testInvokeStaticMethod(){
        if (log.isInfoEnabled()){
            log.info("" + MethodUtil.invokeStaticMethod("com.feilong.commons.core.entity.BackWarnEntity", "getIsSuccess", new Object[0]));
        }
    }

    /**
     * Test invoke static method.
     */
    @Test()
    public final void testInvokeStaticMethod1(){
        Assert.assertEquals(
                        "eilong",
                        MethodUtil.invokeStaticMethod("com.feilong.commons.core.util.StringUtil", "substring", "feilong", "ei"));
    }

    /**
     * Test invoke static method.
     *
     * @throws NoSuchMethodException
     *             the no such method exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    @Test()
    public final void testInvokeStaticMethod2() throws NoSuchMethodException,IllegalAccessException,InvocationTargetException{
        //		Assert.assertEquals(
        //				"fjinxinlong",
        //				MethodUtil.invokeStaticMethod("com.feilong.commons.core.util.StringUtil", "replace", "feilong", "ei", "jinxin"));

        Assert.assertEquals("fjinxinlong", MethodUtils.invokeStaticMethod(StringUtil.class, "replace", "feilong", "ei", "jinxin"));
    }
}
