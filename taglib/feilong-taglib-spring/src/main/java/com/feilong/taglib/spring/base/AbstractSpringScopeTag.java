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
package com.feilong.taglib.spring.base;

/**
 * 需要和spring控制的业务层交互,且仅仅设置作用域,请使用这个基类.
 * 
 * <pre>
 * 只需要重写doExecute()方法即可
 * </pre>
 *
 * @author <a href="venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-8-27 下午01:33:05
 */
public abstract class AbstractSpringScopeTag extends BaseSpringTag{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -9131054886965798341L;

    /** 设置作用域名称 类似于bean:define标签. */
    @SuppressWarnings("hiding")
    protected String          id;

    /**
     * 标签开始.
     *
     * @return the int
     */
    @Override
    public int doStartTagInternal(){
        // 重写该方法
        doExecute();
        return SKIP_BODY; // 表示不用处理标签体，直接调用doEndTag()方法
    }

    /**
     * 执行方法,重写该方法.
     *
     * @author 金鑫
     * @version 1.0 2010-8-27 下午01:37:40
     */
    protected abstract void doExecute();

    /**
     * 设置 设置作用域名称 类似于bean:define标签.
     *
     * @param id
     *            the id to set
     */
    @Override
    public void setId(String id){
        this.id = id;
    }
}