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
package com.feilong.application.taobao.entity;

import java.io.Serializable;

/**
 * 淘宝退出登录(基于淘宝开放平台技术标准登录验证授权)
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-10-17 14:24
 */
public class TaoBaoStandardLoginOutEntity implements Serializable{

    private static final long serialVersionUID = 738005325596563656L;

    /**
     * 访问请求, 请求url,如http://container.api.taobao.com/container/logoff
     */
    private String            requestUrl;

    /**
     * Y TOP分配给应用的AppKey例如：12000024
     */
    private String            app_key;

    /**
     * 淘宝网开发平台官方正式授权的应用协议签名密钥，与App Secret同时被授予的还包括App Key <br>
     * 申请获取App Key,在合作伙伴后台申请成为淘宝合作伙伴后并创建应用，即可发送一份至您的邮箱以及淘宝站内信
     */
    private String            app_secret;

    /**
     * Y 签名方法：md5
     */
    private String            sign_method;

    /**
     * Y 签名：QWERTYYUIO123456UIUIOUYTREWQ1D234
     */
    private String            sign;

    /**
     * Y 时间戳,校验允许误差5min 2010-09-11+23:24:55(yyyy mm-dd HH:MM:SS,空格会被urlencode编码成+)
     */
    private String            timestamp;

    /**
     * Y TOP分配给应用的AppKey例如：12000024
     * 
     * @return the app_key
     */
    public String getApp_key(){
        return app_key;
    }

    /**
     * Y TOP分配给应用的AppKey例如：12000024
     * 
     * @param appKey
     *            the app_key to set
     */
    public void setApp_key(String appKey){
        app_key = appKey;
    }

    /**
     * Y 签名方法：md5
     * 
     * @return the sign_method
     */
    public String getSign_method(){
        return sign_method;
    }

    /**
     * Y 签名方法：md5
     * 
     * @param signMethod
     *            the sign_method to set
     */
    public void setSign_method(String signMethod){
        sign_method = signMethod;
    }

    /**
     * Y 签名：QWERTYYUIO123456UIUIOUYTREWQ1D234
     * 
     * @return the sign
     */
    public String getSign(){
        return sign;
    }

    /**
     * Y 签名：QWERTYYUIO123456UIUIOUYTREWQ1D234
     * 
     * @param sign
     *            the sign to set
     */
    public void setSign(String sign){
        this.sign = sign;
    }

    /**
     * Y 时间戳,校验允许误差5min 2010-09-11 23:24:55(yyyy-MM-dd HH:mm:ss)
     * 
     * @return the timestamp
     */
    public String getTimestamp(){
        return timestamp;
    }

    /**
     * Y 时间戳,校验允许误差5min 2010-09-11 23:24:55(yyyy-MM-dd HH:mm:ss)
     * 
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    /**
     * 访问请求, 请求url,如http://container.api.taobao.com/container/logoff
     * 
     * @return the requestUrl
     */
    public String getRequestUrl(){
        return requestUrl;
    }

    /**
     * 访问请求, 请求url,如http://container.api.taobao.com/container/logoff
     * 
     * @param requestUrl
     *            the requestUrl to set
     */
    public void setRequestUrl(String requestUrl){
        this.requestUrl = requestUrl;
    }

    /**
     * 淘宝网开发平台官方正式授权的应用协议签名密钥，与App Secret同时被授予的还包括App Key <br>
     * 申请获取App Key,在合作伙伴后台申请成为淘宝合作伙伴后并创建应用，即可发送一份至您的邮箱以及淘宝站内信
     * 
     * @return the app_secret
     */
    public String getApp_secret(){
        return app_secret;
    }

    /**
     * 淘宝网开发平台官方正式授权的应用协议签名密钥，与App Secret同时被授予的还包括App Key <br>
     * 申请获取App Key,在合作伙伴后台申请成为淘宝合作伙伴后并创建应用，即可发送一份至您的邮箱以及淘宝站内信
     * 
     * @param appSecret
     *            the app_secret to set
     */
    public void setApp_secret(String appSecret){
        app_secret = appSecret;
    }
}
