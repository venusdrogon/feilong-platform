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
package com.feilong.spring.aop;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ProceedingJoinPointUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.1 2015年4月5日 下午7:58:10
 * @since 1.1.1
 */
public class ProceedingJoinPointUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ProceedingJoinPointUtil.class);

    /**
     * 获得 map for log.
     *
     * @param proceedingJoinPoint
     *            the proceeding join point
     * @return the map for log
     */
    public final static Map<String, Object> getMapForLog(ProceedingJoinPoint proceedingJoinPoint){
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        Method method = methodSignature.getMethod();
        Object[] args = proceedingJoinPoint.getArgs();

        Object target = proceedingJoinPoint.getTarget();

        Class<?> declaringType = methodSignature.getDeclaringType();

        Class<? extends Object> targetClass = target.getClass();
        map.put("target", targetClass.getCanonicalName());
        map.put("method", method.getName());
        map.put("args", args);

        return map;
    }
}
