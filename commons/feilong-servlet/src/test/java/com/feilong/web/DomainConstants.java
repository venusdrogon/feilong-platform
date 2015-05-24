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

import java.util.ResourceBundle;

import com.feilong.core.configure.ResourceBundleUtil;

/**
 * 相关二级域名 配置.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-8-9 下午4:37:46
 * @deprecated
 */
@Deprecated
public final class DomainConstants{

    /** 接口配置. */
    private static final ResourceBundle domain          = ResourceBundle.getBundle("config/domain");

    /** 当前项目配置的 css的域名. */
    public static final String          DOMAIN_CSS      = ResourceBundleUtil.getValue(domain, "domain.css");

    /** 当前项目配置的 js的域名. */
    public static final String          DOMAIN_JS       = ResourceBundleUtil.getValue(domain, "domain.js");

    /** 当前项目配置的 image 的域名. */
    public static final String          DOMAIN_IMAGE    = ResourceBundleUtil.getValue(domain, "domain.image");

    /** 当前项目配置的 image 的域名(PDP). */
    public static final String          DOMAIN_RESOURCE = ResourceBundleUtil.getValue(domain, "domain.resource");

    /** 商城的网址，一般用于不同环境 第三方数据传递 比如微博等. */
    public static final String          DOMAIN_STORE    = ResourceBundleUtil.getValue(domain, "domain.store");

    /** Don't let anyone instantiate this class. */
    private DomainConstants(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }
}