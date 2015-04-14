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
package com.feilong.framework.bind.parse.base;

import java.util.Map;

import com.feilong.commons.core.bean.BeanUtil;
import com.feilong.framework.bind.parse.AbstractXmlParse;

/**
 * 基本转换,使用反射 {@link BeanUtil}将属性一一设置,不需要配置varCommand.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年7月18日 下午5:23:08
 * @param <T>
 *            任意的标准javaBean
 * @since 1.0.8
 */
public abstract class AbstractBaseXmlParse<T> extends AbstractXmlParse<T>{

    /**
     * Builds the command.
     *
     * @param modelClass
     *            the model class
     * @param varNameAndValueMap
     *            the var name and value map
     * @param t
     *            the t
     * @return the t
     * @since 1.1.1
     */
    @Override
    protected T buildCommand(Class<T> modelClass,Map<String, String> varNameAndValueMap,T t){
        BeanUtil.populate(t, varNameAndValueMap);
        return t;
    }
}
