/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.tools.middleware.mobile;

/**
 * 手机运营商类型
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-4 下午01:27:15
 */
public enum MobileOperatorType{
    /**
     * 移动
     */
    ChinaMobile("移动"),
    /**
     * 联通
     */
    ChinaUnicom("联通"),
    /**
     * 电信
     */
    ChinaTelecom("电信");

    /**
     * 名称
     */
    private String name;

    //***********************************************************************
    private MobileOperatorType(String name){
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name){
        this.name = name;
    }
}
