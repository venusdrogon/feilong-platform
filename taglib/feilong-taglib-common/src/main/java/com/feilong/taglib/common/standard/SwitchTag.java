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
package com.feilong.taglib.common.standard;

import com.feilong.taglib.base.BaseTag;

/**
 * 飞龙switch标签.
 *
 * @author 金鑫 2010-3-19 上午10:48:32
 */
public class SwitchTag extends BaseTag{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** 标识case里面是否已经通过,默认没用通过. */
    private boolean           flag             = false;

    /** swith的值. */
    private String            value            = "";

    /*
     * 当遇到switch标签时，所有的子标签都不执行。
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
     */
    @Override
    public int doStartTag(){
        // 不可少
        flag = false;
        return EVAL_BODY_INCLUDE;
    }

    /**
     * 该方法有子标签调用，表示是否可以执行自身的标签。.
     *
     * @author 金鑫
     * @version 1.0 2010-3-19 上午11:46:29
     * @return 该方法有子标签调用，表示是否可以执行自身的标签。
     */
    public synchronized boolean isExecuteTag(){
        return flag;
    }

    /**
     * 标记执行了.
     *
     * @author 金鑫
     * @version 1.0 2010-3-19 上午11:46:43
     */
    public synchronized void setExecuteTag(){
        flag = true;
    }

    /*
     * 销毁到该方法。
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#release()
     */
    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.BodyTagSupport#release()
     */
    @Override
    public void release(){
        flag = false;
    }

    /**
     * switch的值.
     *
     * @param value
     *            the value to set
     */
    public void setValue(String value){
        this.value = value;
    }

    /**
     * 此处不可少 子标签调用.
     *
     * @return the value
     */
    public String getValue(){
        return value;
    }
}
