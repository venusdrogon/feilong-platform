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
package com.feilong.framework.netpay.payment.adaptor.alipay.wap;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.NotImplementedException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.commons.core.net.HttpMethodType;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.AlipayRequestParamConstants;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.adaptor.alipay.AbstractAlipayAdaptor;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.exception.ConstructPaymentRequestParametersException;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.tools.dom4j.Dom4jUtil;
import com.feilong.tools.net.httpclient3.HttpClientConfig;
import com.feilong.tools.net.httpclient3.HttpClientUtil;
import com.feilong.tools.security.oneway.MD5Util;

/**
 * 手机版alipay支付.
 * 
 * <h3>手机网页即时到账授权接口(alipay.wap.trade.create.direct)”和“手机网页即时到账交易接口(alipay.wap.auth.authAndExecute)”</h3>
 * 
 * <blockquote>
 * <ul>
 * <li>两者配套使用，共同完成即时到账交易。</li>
 * <li>授权接口,用于存储商户创建交易所需信息，并返回一个授权令牌（request_token）；</li>
 * <li>交易接口,使用授权接口返回的授权令牌（request_token）值，将页面重定向到支付宝支付页面，使得付款者可以直接汇款给另一个拥有支付宝账号的收款者。</li>
 * <li>授权接口,并未实际创建即时到账交易，创建即时到账交易并且完成支付的过程由交易接口完成。</li>
 * </ul>
 * </blockquote>
 * 
 * @author 冯明雷
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.0 2013-6-4 下午2:05:50
 * @version 1.0.5 2014-5-6 20:38 change name
 * 
 * @deprecated 待重构
 */
@Deprecated
public class AlipayWapAdaptor extends AbstractAlipayAdaptor{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(AlipayWapAdaptor.class);

    /** 用来获得授权令牌 token的 Map. */
    private Map<String, String> tokenSignParamMap;

    /** 构造token req_data参数，程序会自动转成xml字符串. */
    private Map<String, String> reqDataForTokenMap;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.feilong.framework.netpay.payment.adaptor.alipay.BaseAlipayAdaptor#getSignParamsMap(com.feilong.framework.netpay.payment.command
     * .PayRequest, java.util.Map)
     */
    @Override
    protected Map<String, String> constructSignParamsMap(PayRequest payRequest,Map<String, String> specialSignMap)
                    throws ConstructPaymentRequestParametersException{
        //准备alipay.wap.auth.authAndExecute服务的参数
        Map<String, String> signParamsMap = new HashMap<String, String>();

        //step1: 设置spring 注入
        if (Validator.isNotNullOrEmpty(needSignParamMap)){
            signParamsMap.putAll(needSignParamMap);
        }

        //TODO 有点冲突
        //step2: 设置传递过来的参数
        //        if (Validator.isNotNullOrEmpty(specialSignMap)){
        //            signParamsMap.putAll(specialSignMap);
        //        }

        //step3:设置特殊的参数  
        //注意 这一步放 上面两步的下面， 可以在这里拿到上面融合的参数
        setSpecialParams(payRequest, specialSignMap, signParamsMap);

        //不需要 sign type参数

        return signParamsMap;
    }

    /**
     * 设置特殊的参数.
     *
     * @param payRequest
     *            the pay request
     * @param specialSignMap
     *            the special sign map
     * @param signParamsMap
     *            the sign params map
     * @throws ConstructPaymentRequestParametersException
     *             the construct payment request parameters exception
     * @since 1.1.2
     */
    protected void setSpecialParams(PayRequest payRequest,Map<String, String> specialSignMap,Map<String, String> signParamsMap)
                    throws ConstructPaymentRequestParametersException{
        setReqDataParams(payRequest, specialSignMap, signParamsMap);
    }

    /**
     * 设置 req data params.
     *
     * @param payRequest
     *            the pay request
     * @param specialSignMap
     *            the special sign map
     * @param signParamsMap
     *            the sign params map
     * @throws ConstructPaymentRequestParametersException
     *             the construct payment request parameters exception
     * @since 1.1.2
     */
    private void setReqDataParams(PayRequest payRequest,Map<String, String> specialSignMap,Map<String, String> signParamsMap)
                    throws ConstructPaymentRequestParametersException{
        String requestToken = getRequestToken(payRequest, specialSignMap);
        signParamsMap.put("req_data", "<auth_and_execute_req><request_token>" + requestToken + "</request_token></auth_and_execute_req>");
    }

    /**
     * (授权)拿到 授权令牌.
     *
     * @author 冯明雷
     * @param payRequest
     *            the pay request
     * @param specialSignMap
     *            the special sign map
     * @return String
     * @throws ConstructPaymentRequestParametersException
     *             the construct payment request parameters exception
     * @time 2013-6-7上午11:17:51
     */
    private String getRequestToken(PayRequest payRequest,Map<String, String> specialSignMap)
                    throws ConstructPaymentRequestParametersException{

        Map<String, String> tokenRequestMap = constructTokenRequestMap(payRequest, specialSignMap);

        String sign = getSignString(tokenRequestMap);
        tokenRequestMap.put(AlipayRequestParamConstants.SIGN, sign);

        HttpClientConfig httpClientConfig = new HttpClientConfig();
        httpClientConfig.setHttpMethodType(HttpMethodType.POST);
        httpClientConfig.setParams(tokenRequestMap);

        String alipayTokenResponseResult = HttpClientUtil.getResponseBodyAsString(httpClientConfig);
        //TODO 要编码吗？--->Demo里面有 (明雷)
        //String createResult = URLDecoder.decode(result, _input_charset);

        return extractRequestTokenValue(alipayTokenResponseResult);
    }

    /**
     * 提取结果中的token值.
     * 
     * <h3>返回情况示例:</h3>
     * 
     * <blockquote>
     * 
     * <ul>
     * <li>z 正常输出： <br>
     * {@code partner=2088101000137799&req_id=1282889689836&res_data=<?xml version="1.0" encoding="utf-8"?><direct_trade_create_res><request_token>20100830e8085e3e0868a466b822350ede5886e8</request_token></direct_trade_create_res>&sec_id=MD5&service=alipay.wap.trade.create.direct&v=2.0&sign=72a64fb63f0b54f96b10cefb69319e8a}
     * </li>
     * <li>z 发生错误时输出：<br>
     * {@code partner=208810100013779&req_id=1282889689836&res_error=<?xml version="1.0" encoding="utf-8"?><err><code>0005</code>
     * <sub_code>0005</sub_code><msg>partner illegal</msg><detail>合作伙伴没有开通接口访问权限</detail></err>&sec_id=0001&service=alipay.wap.trade.create.direct&v=2.0}
     * </li>
     * </ul>
     * 
     * </blockquote>
     *
     * @param alipayTokenResponseResult
     *            the create result
     * @return the string
     * @throws ConstructPaymentRequestParametersException
     *             the construct payment request parameters exception
     * @since 1.1.2
     */
    private String extractRequestTokenValue(String alipayTokenResponseResult) throws ConstructPaymentRequestParametersException{
        Map<String, String> returnParamMap = URIUtil.parseQueryToValueMap(alipayTokenResponseResult, null);

        //请求失败后才会返回该值
        String res_error = returnParamMap.get("res_error");
        if (Validator.isNotNullOrEmpty(res_error)){
            String logMessage = Slf4jUtil.formatMessage("alipay request_token get error,return res_error's value is:[{}]", res_error);
            log.error(logMessage);
            throw new ConstructPaymentRequestParametersException(logMessage);
        }

        //对请求或响应中参数签名后的 值。      
        //请求失败的请况下，不签名，  无此参数。 
        String reutrnSign = returnParamMap.get("sign");

        //XXX 这里是否还有验证签名的必要， 发起请求是自己发起的，接收方也是支付宝
        assertAlipaySignLegal(reutrnSign, returnParamMap);

        //授权令牌，请求成功后才会返回该值
        String res_data = returnParamMap.get("res_data");
        return getValueByKeyForXML(res_data, "request_token");
    }

    /**
     * Construct token request map.
     *
     * @param payRequest
     *            the pay request
     * @param specialSignMap
     *            the special sign map
     * @return the map< string, string>
     * @since 1.1.2
     */
    private Map<String, String> constructTokenRequestMap(PayRequest payRequest,Map<String, String> specialSignMap){

        Map<String, String> tokenRequestMap = new HashMap<String, String>();
        tokenRequestMap.putAll(tokenSignParamMap);

        //req_data 参数的值不能包含半角或全角的“&”符号；
        tokenRequestMap.put("req_data", constructReqDataParam(payRequest, specialSignMap));

        //用于关联请求与响应，防止 请求重播。      支付宝限制来自同一个partner的请求号必须唯 一。 样例:1282889689836
        //TODO 这时嘛东东--->忘记了(明)
        tokenRequestMap.put("req_id", "" + System.currentTimeMillis());

        return tokenRequestMap;
    }

    /**
     * Construct req data param.
     * 
     * <p>
     * <span style="color:red">注意:req_data 参数的值不能包含半角或全角的“&”符号；</span>
     * </p>
     * 
     * 
     * <h3>元素说明:</h3>
     * 
     * <pre>
     * {@code
     * <direct_trade_create_req>：根节点 
     * z  <subject>：商品名称 
     * z  <out_trade_no>：商户网站唯一订单号 
     * z  <total_fee>：交易金额
     * z  <seller_account_name>：卖家支付宝账号 
     * z  <call_back_url>：支付成功跳转页面路径 
     * z  <notify_url>：服务器异步通知页面路径 
     * z  <out_user>：商户系统用户唯一标识   买家在商户系统的唯一标识。 当该买家支付成功一次后，再次支付金额在30元内时，不需要再次输入密码,比如 123456789
     * z  <merchant_url>：操作中断返回地址  用户付款中途退出返回商户的地址,如 http://www.yoursite.com
     * z  <pay_expire>：交易自动关闭时间  交易自动关闭时间，单位为分钟。 默认值21600（即15天）。 如 3600
     * z  <agent_id>： 代理人ID，用于需要给代理分佣的情况下传入。  如 11397568a1
     * }
     * </pre>
     * 
     * </blockquote>
     * 
     *
     * @param payRequest
     *            the pay request
     * @param specialSignMap
     *            the special sign map
     * @return the string
     * @since 1.1.2
     */
    private String constructReqDataParam(PayRequest payRequest,Map<String, String> specialSignMap){
        String tradeNo = payRequest.getTradeNo();
        BigDecimal totalFee = payRequest.getTotalFee();
        String return_url = payRequest.getReturnUrl();
        String notify_url = payRequest.getNotifyUrl();

        Map<String, String> map = new HashMap<String, String>();

        //        String cashier_code = specialSignMap.get("cashier_code");
        //        //String cashier_code = specialSignMap.get(AlipayRequestParamConstants.DEFAULT_BANK);
        // if (Validator.isNotNullOrEmpty(cashier_code)){
        ////FIXME 咦 文档好像没有这个参数  ----> 信用卡文档中
        //            map.put("cashier_code", cashier_code);
        //        }

        map.putAll(reqDataForTokenMap);
        map.putAll(specialSignMap);

        //***************放下面 确保 不会被配置或者注入 影响
        map.put("out_trade_no", tradeNo);
        map.put("total_fee", "" + totalFee.setScale(2, BigDecimal.ROUND_HALF_UP));

        //call_back_url=alipay 的return_url
        //支付成功后的跳转页面链 接。      支付成功才会跳转。
        map.put("call_back_url", return_url);

        //支付宝服务器主动通知商 户网站里指定的页面http路径。
        map.put("notify_url", notify_url);

        //只有支付成功时才会跳转到 call_back_url，支付失败时跳转到 merchant_url （不返回任何参数），跳转地址都在“手机网页即时到账授权接口  (alipay.wap.trade.create.direct)”中设置

        //***************************************

        // 准备alipay.wap.trade.create.direct服务的参数
        //XXX 做成对象转
        StringBuilder sb = new StringBuilder();
        sb.append("<direct_trade_create_req>");
        sb.append(mapToXML(map));
        sb.append("</direct_trade_create_req>");

        String req_data = sb.toString();
        return req_data;
    }

    /**
     * map装成xml字符串，key将是 xml的 element,value是 element的text.
     *
     * @param map
     *            the map
     * @return the string
     * @since 1.1.2
     */
    //TODO will test ,and may 提取成公共的方法
    private String mapToXML(Map<String, String> map){
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
        }
        return sb.toString();
    }

    //*********************************************************************************************

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#verifyNotify(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public PaymentResult verifyNotify(HttpServletRequest request){

        log.info("getQueryStringLog:{}" + RequestUtil.getQueryStringLog(request));

        boolean isNotifySignOk = false;

        String notify_data = request.getParameter("notify_data");

        if (Validator.isNotNullOrEmpty(notify_data)){
            isNotifySignOk = isNotifySignOkForNotifyUrl(request);
        }
        if (isNotifySignOk){
            boolean isPaymentSuccess = isPaymentSuccess(request);
            // 付款成功
            return isPaymentSuccess ? PaymentResult.PAID : PaymentResult.FAIL;
        }
        //TODO
        return PaymentResult.FAIL;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#getFeedbackTradeNo(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getFeedbackTradeNo(HttpServletRequest request){

        String soCode = request.getParameter("out_trade_no");
        if (Validator.isNotNullOrEmpty(soCode)){
            return soCode;
        }
        String notify_data = request.getParameter("notify_data");
        if (Validator.isNotNullOrEmpty(notify_data)){
            soCode = getValueByKeyForXML(notify_data, "out_trade_no");

        }
        return soCode;
    }

    /**
     * 判断是否交易成功.
     *
     * @author 冯明雷
     * @param request
     *            the request
     * @return boolean
     * @time 2013-6-10下午2:22:42
     */
    private boolean isPaymentSuccess(HttpServletRequest request){

        String result = request.getParameter("result");

        //TODO result会是空吗
        if (Validator.isNotNullOrEmpty(result)){
            return "success".equals(result);
        }else{
            //TODO 是否还要判断下面的了？ 
            String notify_data = request.getParameter("notify_data");
            if (Validator.isNotNullOrEmpty(notify_data)){
                String status = getValueByKeyForXML(notify_data, "trade_status");
                return "TRADE_FINISHED".equals(status) || "TRADE_SUCCESS".equals(status);
            }
        }
        return false;
    }

    /**
     * 解析alipay推送的消息参数.
     *
     * @author 冯明雷
     * @param request
     *            the request
     * @return boolean
     * @time 2013-9-16上午10:23:19
     */
    private boolean isNotifySignOkForNotifyUrl(HttpServletRequest request){
        // alipay 传过来的参数
        String alipaySign = request.getParameter("sign");

        //注意: 这里不要保证顺序？

        //notify_url 接受到的参数验签不要排序，只需要按照支付宝返回的参数顺序，组装字符串，然后验签，
        //如支付宝异步通知 POST 方式返回的参数顺序如下：
        //service=alipay.wap.trade.create.direct&v=1.0&sec_id=0001&notify_data=<notify>…</notify> 

        //没有值的参数无需传递，也无需包含到待签名数据中

        StringBuilder sb = new StringBuilder();
        sb.append("service=").append(request.getParameter("service"));
        sb.append("&v=").append(request.getParameter("v"));
        sb.append("&sec_id=").append(request.getParameter("sec_id"));
        sb.append("&notify_data=").append(request.getParameter("notify_data"));

        String verifyData = sb.toString();

        String mysign = MD5Util.encode(verifyData + key, _input_charset);
        boolean isSignOk = mysign.equals(alipaySign);
        return isSignOk;

    }

    /**
     * 根据key获得xml类型字符串中对应的节点的值.
     *
     * @author 冯明雷
     * @param xmlData
     *            the xml data
     * @param name
     *            the name
     * @return String
     * @time 2013-9-16上午10:16:39
     */
    //TODO 统一调用 Dom4jUtil
    private String getValueByKeyForXML(String xmlData,String name){
        Document document = Dom4jUtil.string2Document(xmlData);
        Element root = document.getRootElement();
        String value = root.elementText(name);
        return value;
    }

    //**********************************************************************************

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#doRedirectVerify(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public PaymentResult verifyRedirect(HttpServletRequest request){
        //解析随浏览器跳转发回的消息参数.
        //isNotifySignOk = isNotifySignOk(request);
        assertAlipaySignLegal(request);

        //TODO
        throw new NotImplementedException("verifyRedirect is not implemented!");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#getFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getFeedbackTotalFee(HttpServletRequest request){
        //TODO
        throw new NotImplementedException("getFeedbackTotalFee is not implemented!");
    }

    /**
     * 设置 用来获得授权令牌 token的 Map.
     *
     * @param tokenSignParamMap
     *            the tokenSignParamMap to set
     */
    public void setTokenSignParamMap(Map<String, String> tokenSignParamMap){
        this.tokenSignParamMap = tokenSignParamMap;
    }

    /**
     * 设置 构造token req_data参数，程序会自动转成xml字符串.
     *
     * @param reqDataForTokenMap
     *            the reqDataForTokenMap to set
     */
    public void setReqDataForTokenMap(Map<String, String> reqDataForTokenMap){
        this.reqDataForTokenMap = reqDataForTokenMap;
    }

}