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
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.commons.core.net.HttpMethodType;
import com.feilong.commons.core.net.ParamUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.AlipayRequestParamConstants;
import com.feilong.framework.netpay.AlipaySignException;
import com.feilong.framework.netpay.payment.adaptor.AbstractPaymentAdaptor;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;
import com.feilong.framework.netpay.payment.exception.ConstructPaymentRequestParametersException;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.tools.security.oneway.MD5Util;

/**
 * 所有支付宝支付的父类.
 * 
 * <h3>公共属性:</h3>
 * 
 * <blockquote>
 * <ul>
 * <li>{@link #partner} 商户签约的支付宝账号对应的支付宝唯一用户号</li>
 * <li>{@link #key} MD5 的私钥是以英文字母和数字组成的 32位字符串</li>
 * <li>{@link #gateway} 支付宝网关</li>
 * <li>{@link #_input_charset} 参数编码字符集</li>
 * </ul>
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014-5-6 20:20:08
 */
public abstract class AbstractAlipayAdaptor extends AbstractPaymentAdaptor{

    /** The Constant log. */
    private static final Logger   log = LoggerFactory.getLogger(AbstractAlipayAdaptor.class);

    /**
     * MD5 的私钥是以英文字母和数字组成的 32位字符串.<br>
     * 商户可登录到商户服务中心（https://b.alipay.com），安装数字证书，在“技术服务”栏目中点击“交易安全校验码”，即可查看。<br>
     * 此处的key 千万不要暴露给别人知道
     */
    protected String              key;

    /**
     * 合作者身份id 不可为空. <br>
     * 商户签约的支付宝账号对应的支付宝唯一用户号,以2088开头的16位纯数字组成。 比如 2088101000137799
     */
    protected String              partner;

    /**
     * 表单提交地址,支付宝网关.
     */
    protected String              gateway;

    /**
     * 参数编码字符集 不可为空. <br>
     * 商户网站使用的 编码格式 如utf-8 ,gbk, gb2312.
     */
    protected String              _input_charset;

    //*************************************************************************

    /** 其他需要被签名的 Map. */
    protected Map<String, String> needSignParamMap;

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
        Map<String, String> signParamsMap = constructSignParamsMap(payRequest, specialSignMap);
        String sign = getSignString(signParamsMap);

        // *************************************************************************************
        //2014-8-11 10:58 为了log看起来整齐 有序, 换成 TreeMap
        Map<String, String> hiddenParamsMap = new TreeMap<String, String>();
        hiddenParamsMap.putAll(signParamsMap);

        hiddenParamsMap.put(AlipayRequestParamConstants.SIGN, sign);

        // #签名方式
        // #DSA,RSA,MD5三值可选
        // #必须大写
        // #宝尊 暂时只支持MD5
        //TODO alipay WAP不需要这个参数
        hiddenParamsMap.put(AlipayRequestParamConstants.SIGN_TYPE, "MD5");

        String method = HttpMethodType.GET.getMethod().toLowerCase();
        return getPaymentFormEntity(gateway, method, hiddenParamsMap);
    }

    /**
     * 签名.
     * 
     * <h3>代码流程:</h3> <blockquote>
     * <ol>
     * <li>调用 {@link ParamUtil#toNaturalOrderingString(Map) toNaturalOrderingString}(signParamsMap),转成待签名字符串</li>
     * <li>调用 {@link MD5Util#encode(String, String) MD5Util.encode}(toBeSignedString+key,_input_charset)签名</li>
     * </ol>
     * </blockquote>
     *
     * @param signParamsMap
     *            the sign params map
     * @return the sign string
     * @since 1.1.2
     */
    protected String getSignString(Map<String, String> signParamsMap){
        if (Validator.isNullOrEmpty(signParamsMap)){
            throw new NullPointerException("signParamsMap can't be null/empty!");
        }

        // 把参数里面的 sign 和 sign_type 去掉
        if (signParamsMap.containsKey(AlipayRequestParamConstants.SIGN)){
            signParamsMap.remove(AlipayRequestParamConstants.SIGN);
        }
        if (signParamsMap.containsKey(AlipayRequestParamConstants.SIGN_TYPE)){
            signParamsMap.remove(AlipayRequestParamConstants.SIGN_TYPE);
        }

        // *************************************************************************************
        // 待签名字符串
        // 除去sign、sign_type 两个参数外，其他需要使用到的参数皆是要签名的参数
        String toBeSignedString = ParamUtil.toNaturalOrderingString(signParamsMap);

        // 在 MD5 签名时，需要私钥参与签名。
        // 需要把私钥直接拼接到待签名字符串后面，形成新的字符串，利用MD5 的签名函数对这个新的字符串进行签名运算
        String sign = MD5Util.encode(toBeSignedString + key, _input_charset);
        return sign;
    }

    /**
     * 构造待签名的参数map，注意 <span style="color:red">不要设置 sign和sign_type 参数</span>.
     *
     * @param payRequest
     *            the pay request
     * @param specialSignMap
     *            the special sign map
     * @return the sign params map
     * @throws ConstructPaymentRequestParametersException
     *             the construct payment request parameters exception
     * @since 1.1.2
     */
    protected abstract Map<String, String> constructSignParamsMap(PayRequest payRequest,Map<String, String> specialSignMap)
                    throws ConstructPaymentRequestParametersException;

    /**
     * 断言 alipay签名正确合法.<br>
     * <span style="color:red">如果不匹配,将抛出 {@link com.feilong.framework.netpay.AlipaySignException}</span>.<br>
     * 注:支付宝如要提示签名错误,一般显示的是 "sign illegal",illegal的反义词是legal.<br>
     *
     * @param alipaySign
     *            the alipay sign
     * @param parameterMap
     *            the parameter map
     * @throws NullPointerException
     *             the null pointer exception
     * @throws AlipaySignException
     *             if 支付宝的sign值和我们生成出来的不相等
     * @since 1.1.2
     */
    protected void assertAlipaySignLegal(String alipaySign,Map<String, String> parameterMap) throws NullPointerException,
                    AlipaySignException{
        if (Validator.isNullOrEmpty(alipaySign)){
            throw new NullPointerException("alipaySign can't be null/empty!");
        }

        if (Validator.isNullOrEmpty(parameterMap)){
            throw new NullPointerException("parameterMap can't be null/empty!");
        }

        String ourSign = getSignString(parameterMap);
        boolean isSignOk = alipaySign.equals(ourSign);

        //如果失败log 并且异常
        if (!isSignOk){
            String logMessage = Slf4jUtil.formatMessage(
                            "sign not match,alipay sign is:[{}],our sign is:[{}],parameterMap info:[{}]",
                            alipaySign,
                            ourSign,
                            JsonUtil.format(parameterMap));
            log.error(logMessage);
            throw new AlipaySignException(logMessage);
        }
    }

    /**
     * 断言 alipay签名正确合法 ,illegal 的反义词是----->legal.<br>
     * 还有没有必要再次 sign 来确认了? --alipay 伦奇说 都需要.
     *
     * @param alipayRequest
     *            the alipay request
     * @throws NullPointerException
     *             the null pointer exception
     * @since 1.1.2
     */
    protected void assertAlipaySignLegal(HttpServletRequest alipayRequest) throws NullPointerException{
        // alipay 传过来的参数
        String alipaySign = alipayRequest.getParameter(AlipayRequestParamConstants.SIGN);

        if (Validator.isNullOrEmpty(alipaySign)){
            throw new IllegalArgumentException("alipaySign can't be null/empty!");
        }
        // alipay 支付接口 里面所有的参数 都是单值的
        Map<String, String> parameterMap = RequestUtil.getParameterSingleValueMap(alipayRequest);

        assertAlipaySignLegal(alipaySign, parameterMap);
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
     * 设置 mD5 的私钥是以英文字母和数字组成的 32位字符串.
     *
     * @param key
     *            the key to set
     */
    public void setKey(String key){
        this.key = key;
    }

    /**
     * 设置 合作者身份id 不可为空.
     *
     * @param partner
     *            the partner to set
     */
    public void setPartner(String partner){
        this.partner = partner;
    }

    /**
     * 设置 表单提交地址,支付宝网关.
     *
     * @param gateway
     *            the gateway to set
     */
    public void setGateway(String gateway){
        this.gateway = gateway;
    }

    /**
     * Set_input_charset.
     *
     * @param _input_charset
     *            the _input_charset to set
     */
    public void set_input_charset(String _input_charset){
        this._input_charset = _input_charset;
    }

    /**
     * 设置 其他需要被签名的 Map.
     *
     * @param needSignParamMap
     *            the needSignParamMap to set
     */
    public void setNeedSignParamMap(Map<String, String> needSignParamMap){
        this.needSignParamMap = needSignParamMap;
    }
}