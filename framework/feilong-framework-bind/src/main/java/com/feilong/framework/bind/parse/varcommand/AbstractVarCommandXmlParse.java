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
package com.feilong.framework.bind.parse.varcommand;

import java.lang.reflect.Field;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.framework.bind.exception.BuildCommandException;
import com.feilong.framework.bind.parse.AbstractXmlParse;

/**
 * xml格式结果的解析,目的将xml字符串转成 自定义的对象,这个对象必须 实现 {@link VarCommand} 接口.
 * 
 * @param <T>
 *            自定义的对象,这个对象必须 实现 {@link VarCommand} 接口
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月8日 上午10:42:49
 * @since 1.0.6
 */
public abstract class AbstractVarCommandXmlParse<T extends VarCommand> extends AbstractXmlParse<T>{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(AbstractVarCommandXmlParse.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.framework.bind.parse.AbstractXmlParse#buildCommand(java.lang.Class, java.util.Map, java.lang.Object)
     */
    @Override
    protected T buildCommand(Class<T> modelClass,Map<String, String> varNameAndValueMap,T t){
        // 通过反射机制 省却一堆的 set
        // DokuQueryResult dokuQueryResult = new DokuQueryResult();

        Field[] fields = modelClass.getDeclaredFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(VarName.class)){
                VarName varName = field.getAnnotation(VarName.class);
                String varNameName = varName.name();

                String value = varNameAndValueMap.get(varNameName);

                if (log.isDebugEnabled()){
                    String fieldName = field.getName();
                    log.debug("fieldName:[{}],match name:[{}],value:[{}]", fieldName, varNameName, value);
                }
                //TODO 可以在property上做文章,而不是 field
                field.setAccessible(true);

                try{
                    // 将指定对象变量上此 Field 对象表示的字段设置为指定的新值。如果底层字段的类型为基本类型，则对新值进行自动解包
                    field.set(t, value);

                }catch (Exception e){
                    log.error(e.getClass().getName(), e);
                    throw new BuildCommandException(e);
                }
            }
        }
        return t;
    }
}