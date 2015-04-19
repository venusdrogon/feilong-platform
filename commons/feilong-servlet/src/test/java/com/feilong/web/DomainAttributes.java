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
package com.feilong.web;

/**
 * The Interface DomainAttributes.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 27, 2014 1:09:51 AM
 * @deprecated
 */
@Deprecated
public final class DomainAttributes{

    /** 样式表 域名的变量. */
    public static final String ATTRIBUTE_DOMAIN_CSS      = "domain_css";

    /** js 域名的变量. */
    public static final String ATTRIBUTE_DOMAIN_JS       = "domain_js";

    /** image 域名的变量. */
    public static final String ATTRIBUTE_DOMAIN_IMAGE    = "domain_image";

    /** 商品图片 resource 域名的变量. */
    public static final String ATTRIBUTE_DOMAIN_RESOURCE = "domain_resource";

    /** store 域名的变量,商城的网址，一般用于不同环境 第三方数据传递 比如微博等. */
    public static final String ATTRIBUTE_DOMAIN_STORE    = "domain_store";

    /** Don't let anyone instantiate this class. */
    private DomainAttributes(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }
}
