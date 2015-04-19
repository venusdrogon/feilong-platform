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
package com.feilong.web.esapi;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Validator;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ESAPIValidatorUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-8-16 下午2:25:36
 */
public final class ESAPIValidatorUtil{

    /** The Constant log. */
    private static final Logger    log       = LoggerFactory.getLogger(ESAPIValidatorUtil.class);

    /** The validator. */
    private static final Validator validator = ESAPI.validator();

    /** Don't let anyone instantiate this class. */
    private ESAPIValidatorUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 获得安全的html代码.
     *
     * @param input
     *            the input
     * @return the valid safe html
     * @throws ESAPIException
     *             the ESAPI exception
     */
    public static String getValidSafeHTML(String input) throws ESAPIException{
        try{
            return validator.getValidSafeHTML("html", input, Integer.MAX_VALUE, true);
        }catch (ValidationException e){
            log.error("", e);
            throw new ESAPIException(e);
        }catch (IntrusionException e){
            log.error("", e);
            throw new ESAPIException(e);
        }
    }
}