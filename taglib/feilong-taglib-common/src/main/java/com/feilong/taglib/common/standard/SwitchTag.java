/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.taglib.common.standard;

import com.feilong.taglib.base.BaseTag;

/**
 * 飞龙switch标签
 * 
 * @author 金鑫 2010-3-19 上午10:48:32
 */
public class SwitchTag extends BaseTag{

    private static final long serialVersionUID = 1L;

    /**
     * 标识case里面是否已经通过,默认没用通过
     */
    private boolean           flag             = false;

    /**
     * swith的值
     */
    private String            value            = "";

    /*
     * 当遇到switch标签时，所有的子标签都不执行。
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    @Override
    public int doStartTag(){
        // 不可少
        flag = false;
        return EVAL_BODY_INCLUDE;
    }

    /**
     * 该方法有子标签调用，表示是否可以执行自身的标签。
     * 
     * @return 该方法有子标签调用，表示是否可以执行自身的标签。
     * @author 金鑫
     * @version 1.0 2010-3-19 上午11:46:29
     */
    public synchronized boolean isExecuteTag(){
        return flag;
    }

    /**
     * 标记执行了
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
    @Override
    public void release(){
        flag = false;
    }

    /**
     * switch的值
     * 
     * @param value
     *            the value to set
     */
    public void setValue(String value){
        this.value = value;
    }

    /**
     * 此处不可少 子标签调用
     * 
     * @return the value
     */
    public String getValue(){
        return value;
    }
}
