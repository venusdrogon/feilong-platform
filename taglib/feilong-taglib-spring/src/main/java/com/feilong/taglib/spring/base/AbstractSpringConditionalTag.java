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
package com.feilong.taglib.spring.base;

/**
 * 条件父类标签,需要条件控制的(需要和业务层打交道)
 * 
 * @author 金鑫 2010-3-31 上午11:21:51
 */
public abstract class AbstractSpringConditionalTag extends BaseSpringTag{

    private static final long serialVersionUID = 6162717594100732596L;

    /**
     * 标签开始
     */
    @Override
    public int doStartTagInternal(){
        if (condition()){
            // 将body的内容输出到存在的输出流中 表示需要处理标签体,但绕过setBodyContent()和doInitBody()方法
            return EVAL_BODY_INCLUDE;
        }
        // 表示不用处理标签体，直接调用doEndTag()方法
        return SKIP_BODY;
    }

    /**
     * 标签结束
     */
    @Override
    public int doEndTag(){
        return EVAL_PAGE;// 处理标签后，继续处理JSP后面的内容
    }

    protected abstract boolean condition();
}
