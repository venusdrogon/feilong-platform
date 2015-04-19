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
package com.feilong.tools.xstream;

import java.util.Map;

import com.feilong.commons.core.util.Validator;
import com.thoughtworks.xstream.XStream;

/**
 * {@link com.thoughtworks.xstream.XStream} 工具类.<br>
 * 将object转成xml字符串.
 * 
 * <h3>使用示例:</h3>
 * 
 * <blockquote>
 * <p>
 * 对于将下面的对象，转成XML<br>
 * User user = new User(1L);
 * </p>
 * <p>
 * 1.不带toXmlConfig参数 <br>
 * 使用 {@code com.feilong.tools.xstream.XStreamUtil.toXML(user, null)},则返回
 * 
 * <pre>
 * {@code
 *  <com.feilong.test.User>
 *   <name>feilong</name>
 *   <id>1</id>
 *   <userInfo/>
 *   <userAddresseList/>
 * </com.feilong.test.User>
 * }
 * </pre>
 * 
 * </p>
 * 
 * 
 * <p>
 * 2.设置 Alias 参数 <br>
 * 可以看到上面的结果中，XML Root元素名字是 com.feilong.test.User，如果只是显示成 {@code <user>}怎么做呢？<br>
 * 使用
 * 
 * <pre>
 * 
 * 
 * {
 *     &#064;code
 *     User user = new User(1L);
 *     ToXmlConfig toXmlConfig = new ToXmlConfig();
 *     toXmlConfig.getAliasMap().put(&quot;user&quot;, User.class);
 *     log.info(XStreamUtil.toXML(user, toXmlConfig));
 * }
 * </pre>
 * 
 * ,则返回
 * 
 * <pre>
 * {@code
 * <user>
 *   <name>feilong</name>
 *   <id>1</id>
 *   <userInfo/>
 *   <userAddresseList/>
 * </user>
 * }
 * </pre>
 * 
 * </p>
 * <p>
 * 3.设置 ImplicitCollection 参数 <br>
 * 如果我在结果不想出现 {@code <userAddresseList/>}怎么做呢？<br>
 * 使用
 * 
 * <pre>
 * 
 * 
 * {
 *     &#064;code
 *     User user = new User(1L);
 *     ToXmlConfig toXmlConfig = new ToXmlConfig();
 *     toXmlConfig.getAliasMap().put(&quot;user&quot;, User.class);
 *     toXmlConfig.getImplicitCollectionMap().put(&quot;userAddresseList&quot;, User.class);
 *     log.info(XStreamUtil.toXML(user, toXmlConfig));
 * }
 * </pre>
 * 
 * ,则返回
 * 
 * <pre>
 * {@code
 *  <user>
 *   <name>feilong</name>
 *   <id>1</id>
 *   <userInfo/>
 * </user>
 * }
 * </pre>
 * 
 * </p>
 * </blockquote>
 * 
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 31, 2014 9:58:34 AM
 */
public class XStreamUtil{

    /**
     * 将object转成xml字符串.
     * 
     * <h3>使用示例:</h3>
     * 
     * <blockquote>
     * <p>
     * 对于将下面的对象，转成XML<br>
     * User user = new User(1L);
     * </p>
     * <p>
     * 1.不带toXmlConfig参数 <br>
     * 使用 {@code com.feilong.tools.xstream.XStreamUtil.toXML(user, null)},则返回
     * 
     * <pre>
     * {@code
     *  <com.feilong.test.User>
     *   <name>feilong</name>
     *   <id>1</id>
     *   <userInfo/>
     *   <userAddresseList/>
     * </com.feilong.test.User>
     * }
     * </pre>
     * 
     * </p>
     * 
     * 
     * <p>
     * 2.设置 Alias 参数 <br>
     * 可以看到上面的结果中，XML Root元素名字是 com.feilong.test.User，如果只是显示成 {@code <user>}怎么做呢？<br>
     * 使用
     * 
     * <pre>
     * 
     * 
     * {
     *     &#064;code
     *     User user = new User(1L);
     *     ToXmlConfig toXmlConfig = new ToXmlConfig();
     *     toXmlConfig.getAliasMap().put(&quot;user&quot;, User.class);
     *     log.info(XStreamUtil.toXML(user, toXmlConfig));
     * }
     * </pre>
     * 
     * ,则返回
     * 
     * <pre>
     * {@code
     * <user>
     *   <name>feilong</name>
     *   <id>1</id>
     *   <userInfo/>
     *   <userAddresseList/>
     * </user>
     * }
     * </pre>
     * 
     * </p>
     * <p>
     * 3.设置 ImplicitCollection 参数 <br>
     * 如果我在结果不想出现 {@code <userAddresseList/>}怎么做呢？<br>
     * 使用
     * 
     * <pre>
     * 
     * 
     * {
     *     &#064;code
     *     User user = new User(1L);
     *     ToXmlConfig toXmlConfig = new ToXmlConfig();
     *     toXmlConfig.getAliasMap().put(&quot;user&quot;, User.class);
     *     toXmlConfig.getImplicitCollectionMap().put(&quot;userAddresseList&quot;, User.class);
     *     log.info(XStreamUtil.toXML(user, toXmlConfig));
     * }
     * </pre>
     * 
     * ,则返回
     * 
     * <pre>
     * {@code
     *  <user>
     *   <name>feilong</name>
     *   <id>1</id>
     *   <userInfo/>
     * </user>
     * }
     * </pre>
     * 
     * </p>
     * </blockquote>
     * 
     * @param obj
     *            the obj
     * @param toXmlConfig
     *            the to xml config
     * @return the string
     * @see com.thoughtworks.xstream.XStream#toXML(Object)
     * @see com.thoughtworks.xstream.XStream#alias(String, Class)
     * @see com.thoughtworks.xstream.XStream#addImplicitCollection(Class, String)
     */
    public static String toXML(Object obj,ToXmlConfig toXmlConfig){
        XStream xstream = new XStream();

        if (Validator.isNotNullOrEmpty(toXmlConfig)){

            // *******************alias********************************************
            Map<String, Class<?>> aliasMap = toXmlConfig.getAliasMap();
            if (Validator.isNotNullOrEmpty(aliasMap)){
                for (Map.Entry<String, Class<?>> entry : aliasMap.entrySet()){
                    String key = entry.getKey();
                    Class<?> value = entry.getValue();
                    // 类重命名
                    xstream.alias(key, value);
                }
            }
            // *******************implicitCollectionMap********************************************
            Map<String, Class<?>> implicitCollectionMap = toXmlConfig.getImplicitCollectionMap();
            if (Validator.isNotNullOrEmpty(implicitCollectionMap)){
                for (Map.Entry<String, Class<?>> entry : implicitCollectionMap.entrySet()){
                    String key = entry.getKey();
                    Class<?> value = entry.getValue();
                    xstream.addImplicitCollection(value, key);
                }
            }
        }

        // 属性重命名
        // xstream.aliasField(alias, definedIn, fieldName);
        // 包重命名
        // xstream.aliasPackage(name, pkgName);
        String xml = xstream.toXML(obj);
        return xml;
    }
}
