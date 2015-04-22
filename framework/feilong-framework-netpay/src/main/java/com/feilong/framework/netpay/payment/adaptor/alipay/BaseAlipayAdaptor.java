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
package com.feilong.framework.netpay.payment.adaptor.alipay;

import java.util.Map;

import com.feilong.framework.netpay.payment.adaptor.AbstractPaymentAdaptor;

/**
 * 所有支付宝支付的父类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014-5-6 20:20:08
 */
public abstract class BaseAlipayAdaptor extends AbstractPaymentAdaptor{

    /** The Constant PARAM_DEFAULT_BANK. */
    protected static final String PARAM_DEFAULT_BANK = "defaultbank";

    /**
     * MD5 的私钥是以英文字母和数字组成的 32位字符串。<br>
     * 商户可登录到商户服务中心（https://b.alipay.com），安装数字证书，在“技术服务”栏目中点击“交易安全校验码”，即可查看。<br>
     * 此处的key 千万不要暴露给别人知道
     */
    protected String              key;

    /**
     * 合作者身份id 不可为空 <br>
     * 签约的 支付宝账号对应的 支付宝唯一用户名.
     */
    protected String              partner;

    /**
     * 表单提交地址 <br>
     * 支付宝网关.
     */
    protected String              gateway;

    /**
     * 参数编码字符集 不可为空 <br>
     * 商户网站使用的 编码格式 如utf-8 ,gbk, gb2312.
     */
    protected String              _input_charset;

    /**
     * 验证输入的参数(子类可以按照需要 重写).
     * 
     * @param specialSignMap
     *            指定的签名map
     * @return true, if successful
     */
    protected boolean validatorSpecialSignMap(@SuppressWarnings("unused") Map<String, String> specialSignMap){
        return true;
    }

    /**
     * 设置 mD5 的私钥是以英文字母和数字组成的 32位字符串。<br>
     * 商户可登录到商户服务中心（https://b.
     * 
     * @param key
     *            the key to set
     */
    public void setKey(String key){
        this.key = key;
    }

    /**
     * 设置 合作者身份id 不可为空 <br>
     * 签约的 支付宝账号对应的 支付宝唯一用户名.
     * 
     * @param partner
     *            the partner to set
     */
    public void setPartner(String partner){
        this.partner = partner;
    }

    /**
     * 设置 表单提交地址 <br>
     * 支付宝网关.
     * 
     * @param gateway
     *            the gateway to set
     */
    public void setGateway(String gateway){
        this.gateway = gateway;
    }

    /**
     * Sets the _input_charset.
     * 
     * @param _input_charset
     *            the _input_charset to set
     */
    public void set_input_charset(String _input_charset){
        this._input_charset = _input_charset;
    }

}
