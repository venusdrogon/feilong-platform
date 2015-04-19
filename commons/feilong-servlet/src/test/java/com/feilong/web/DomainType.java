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
 * 二级域名 类型.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-13 下午5:49:47
 * @deprecated
 */
@Deprecated
public enum DomainType{

    /** 样式表. */
    CSS(DomainConstants.DOMAIN_CSS),

    /** js. */
    JS(DomainConstants.DOMAIN_JS),

    /** 图片. */
    IMAGE(DomainConstants.DOMAIN_IMAGE),

    /** 资源图片,如PDP商品图片,在测试环境 可能 商品图片使用外部 而Image图片使用内部. */
    RESOURCE(DomainConstants.DOMAIN_RESOURCE),

    /** 商城的网址，一般用于不同环境 第三方数据传递 比如微博等. */
    STORE(DomainConstants.DOMAIN_STORE);

    /** The path. */
    private String path;

    /**
     * The Constructor.
     *
     * @param path
     *            the path
     */
    private DomainType(String path){
        this.path = path;
    }

    /**
     * 获得 path.
     *
     * @return the path
     */
    public String getPath(){
        return path;
    }

    /**
     * 设置 path.
     *
     * @param path
     *            the path to set
     */
    public void setPath(String path){
        this.path = path;
    }
}