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
package com.feilong.taglib.base;

/**
 * 飞龙自定义标签用于输出的父类 <br>
 * 
 * @author 金鑫 时间:2009-5-2下午05:20:22
 * @version 2012-3-13 上午1:59:22
 */
public abstract class AbstractCommonTag extends BaseTag{

    private static final long serialVersionUID = -3092134818447347878L;

    /**
     * 标签开始,
     */
    @Override
    public int doStartTag(){
        // 开始执行的部分
        print(this.writeContent());
        // 开始:跳过了开始和结束标签之间的代码。
        return SKIP_BODY;
    }

    // *******************************************************************
    /**
     * 标签体内容
     * 
     * @return
     * @author 金鑫
     * @version 1.0 2010-5-27 下午11:50:40
     */
    protected abstract Object writeContent();
}