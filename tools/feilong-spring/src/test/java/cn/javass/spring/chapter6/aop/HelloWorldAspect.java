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
package cn.javass.spring.chapter6.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DateExtensionUtil;

/**
 * The Class HelloWorldAspect.
 */
@SuppressWarnings("all")
public class HelloWorldAspect{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(HelloWorldAspect.class);

    /** The begin. */
    private Date                begin;

    /** The end. */
    private Date                end;

    // 前置通知
    /**
     * Before advice.
     */
    public void beforeAdvice(){
        begin = new Date();
        log.info("1.......before advice,{}", begin);
    }

    // 后置最终通知
    /**
     * After finally advice.
     */
    public void afterFinallyAdvice(){
        end = new Date();
        log.info("2.......after finally advice,{},耗时:{}", begin, DateExtensionUtil.getIntervalForView(begin, end));
    }

    /**
     * Around.
     *
     * @param proceedingJoinPoint
     *            the proceeding join point
     * @throws Throwable
     *             the throwable
     */
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object[] args = { "a" };
        proceedingJoinPoint.proceed();
        log.info("around");
    }
}
