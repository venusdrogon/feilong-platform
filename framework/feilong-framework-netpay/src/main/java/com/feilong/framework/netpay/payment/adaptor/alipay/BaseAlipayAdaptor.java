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

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.net.HttpMethodType;
import com.feilong.commons.core.net.ParamUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.payment.adaptor.AbstractPaymentAdaptor;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;
import com.feilong.tools.security.oneway.MD5Util;

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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.feilong.framework.netpay.payment.adaptor.AbstractPaymentAdaptor#getCustomizePaymentFormEntity(com.feilong.framework.netpay.payment
     * .command.PayRequest, java.util.Map)
     */
    @Override
    public PaymentFormEntity getCustomizePaymentFormEntity(PayRequest payRequest,Map<String, String> specialSignMap){

        doCommonAlipayValidate(payRequest, specialSignMap);

        // *************************************************************************************************
        Map<String, String> signParamsMap = getSignParamsMapForPaymentFormEntity(payRequest, specialSignMap);

        // *************************************************************************************
        // 待签名字符串
        // 除去sign、sign_type 两个参数外，其他需要使用到的参数皆是要签名的参数
        String toBeSignedString = ParamUtil.toNaturalOrderingString(signParamsMap);

        // 在 MD5 签名时，需要私钥参与签名。
        // 需要把私钥直接拼接到待签名字符串后面，形成新的字符串，利用MD5 的签名函数对这个新的字符串进行签名运算
        String sign = MD5Util.encode(toBeSignedString + key, _input_charset);

        // *************************************************************************************
        //2014-8-11 10:58 为了log看起来整齐 有序, 换成 TreeMap
        Map<String, String> hiddenParamsMap = new TreeMap<String, String>();
        hiddenParamsMap.putAll(signParamsMap);

        hiddenParamsMap.put("sign", sign);

        // #签名方式
        // #DSA,RSA,MD5三值可选
        // #必须大写
        // #宝尊 暂时只支持MD5
        hiddenParamsMap.put("sign_type", "MD5");

        String method = HttpMethodType.GET.getMethod().toLowerCase();
        return getPaymentFormEntity(gateway, method, hiddenParamsMap);
    }

    /**
     * 获得 sign params map.
     *
     * @param payRequest
     *            the pay request
     * @param specialSignMap
     *            the special sign map
     * @return the sign params map
     * @since 1.1.2
     */
    protected abstract Map<String, String> getSignParamsMapForPaymentFormEntity(PayRequest payRequest,Map<String, String> specialSignMap);

    /**
     * 校验 返回的请求 <br>
     * 还有没有必要再次 sign 来确认了? --alipay 伦奇说 都需要.
     * 
     * @param request
     *            the request
     * @return true, if is notify sign ok
     */
    protected boolean isNotifySignOk(HttpServletRequest request){
        // alipay 传过来的参数
        String alipaySign = request.getParameter("sign");
        if (Validator.isNullOrEmpty(alipaySign)){
            throw new IllegalArgumentException("alipaySign can't be null/empty!");
        }

        // alipay 支付接口 里面所有的参数 都是单值的
        @SuppressWarnings("unchecked")
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, String> params = new HashMap<String, String>();
        while (parameterNames.hasMoreElements()){
            // String key = parameterNames.nextElement();

            // 把参数里面的 sign 和 sign_type 去掉
            if (key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")){
                continue;
            }
            String value = request.getParameter(key);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(value);
            params.put(key, stringBuilder.toString());

        }
        String toBeSignedString = ParamUtil.toNaturalOrderingString(params);
        String mysign = MD5Util.encode(toBeSignedString + key, _input_charset);
        boolean isSignOk = mysign.equals(alipaySign);
        return isSignOk;
    }

    /**
     * Do common alipay validate.
     *
     * @param payRequest
     *            the pay request
     * @param specialSignMap
     *            the special sign map
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws NullPointerException
     *             the null pointer exception
     * @since 1.1.2
     */
    private void doCommonAlipayValidate(PayRequest payRequest,Map<String, String> specialSignMap) throws IllegalArgumentException,
                    NullPointerException{
        String return_url = payRequest.getReturnUrl();
        String notify_url = payRequest.getNotifyUrl();

        if (Validator.isNullOrEmpty(return_url)){
            throw new IllegalArgumentException("return_url can't be null/empty!");
        }

        if (Validator.isNullOrEmpty(notify_url)){
            throw new IllegalArgumentException("notify_url can't be null/empty!");
        }

        // *******************************************************************************************
        boolean isPassValidatorSpecialSignMap = validatorSpecialSignMap(specialSignMap);

        if (!isPassValidatorSpecialSignMap){
            throw new IllegalArgumentException("specialSignMap has IllegalArgument key");
        }
    }

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
