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
package com.feilong.framework.netpay.payment.adaptor.alipay.pconline;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.UncheckedIOException;
import com.feilong.commons.core.net.URLConnectionUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.adaptor.alipay.AbstractAlipayAdaptor;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.tools.dom4j.Dom4jException;
import com.feilong.tools.dom4j.Dom4jUtil;

/**
 * alipay 纯网关接口.
 * <ul>
 * <li>此接口只支持 https 请求</li>
 * <li>参数 body（商品描述）、subject（商品名称）、extra_common_param（公用 回传参数）不能包含特殊字符（如：#、%、&、+）、敏感词汇，<br>
 * 也不能使用外 国文字（旺旺不支持的外文，如：韩文、泰语、藏文、蒙古文、阿拉伯语）；</li>
 * <li>此接口支持重复调用，前提是交易基本信息（买家、卖家、交易金额、超时时间等）在多次调用中保持一致，且交易尚未完成支付。</li>
 * </ul>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 15, 2013 8:41:39 PM
 * @version 1.0.5 2014-5-6 20:38 change name
 */
public class AlipayOnlineAdaptor extends AbstractAlipayAdaptor{

    /** The Constant log. */
    private static final Logger log                = LoggerFactory.getLogger(AlipayOnlineAdaptor.class);

    // **********************************************************************************************
    /** 验证通知的 service. */
    private String              service_notify_verify;

    /** 时间戳查询接口,用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数. */
    private String              service_query_timestamp;

    /**
     * 是否开通开通 防钓鱼认证,默认否<br>
     * 如果开通了 设置为true,那么 发送到支付网关 会自动设置 这个参数值.
     */
    private Boolean             isOpenAntiPhishing = false;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.feilong.framework.netpay.payment.adaptor.alipay.BaseAlipayAdaptor#getSignParamsMapForPaymentFormEntity(com.feilong.framework.
     * netpay.payment.command.PayRequest, java.util.Map)
     */
    @Override
    protected Map<String, String> constructSignParamsMap(PayRequest payRequest,Map<String, String> specialSignMap){
        // 需要被签名的 参数map
        Map<String, String> signParamsMap = new HashMap<String, String>();

        //step1: 设置spring 注入
        if (Validator.isNotNullOrEmpty(needSignParamMap)){
            signParamsMap.putAll(needSignParamMap);
        }

        //step2: 设置传递过来的参数
        if (Validator.isNotNullOrEmpty(specialSignMap)){
            signParamsMap.putAll(specialSignMap);
        }

        //step3:设置特殊的参数
        setSpecialParams(signParamsMap);

        //step4:设置公共的
        //放在 所有设置的 最下面,保证 核心参数不会被 子类修改
        //TODO 可以可以加上注入的参数 校验，比如 不能在spring 注入 out_trade_no,total_fee等参数
        setCommonAlipayParams(payRequest, signParamsMap);

        return signParamsMap;
    }

    /**
     * 设置特殊的参数.
     *
     * @param signParamsMap
     *            the special params
     * @since 1.1.2
     */
    protected void setSpecialParams(Map<String, String> signParamsMap){
        // 设置防钓鱼参数
        setAntiPhishingParams(signParamsMap);
    }

    /**
     * 设置 共用的 alipay 参数信息.
     * 
     * @param code
     *            订单code
     * @param total_fee
     *            总支付金额
     * @param return_url
     *            浏览器返回地址
     * @param notify_url
     *            通知地址
     * @param signParamsMap
     *            签名参数map
     */
    private void setCommonAlipayParams(PayRequest payRequest,Map<String, String> signParamsMap){
        String tradeNo = payRequest.getTradeNo();
        BigDecimal totalFee = payRequest.getTotalFee();
        String return_url = payRequest.getReturnUrl();
        String notify_url = payRequest.getNotifyUrl();

        // 支付宝合作商户网站,唯一订单号 （确保在商户系统中唯一） String(64)
        signParamsMap.put("out_trade_no", tradeNo);

        // 交易金额 该笔订单的资金总额，单位为 RMB-Yuan。
        // 取值范围为[0.01， 100000000.00]，
        // 精确到小数点后 两位。
        signParamsMap.put("total_fee", "" + totalFee.setScale(2, BigDecimal.ROUND_HALF_UP));

        // 页面跳转同步通知页面路径String(200)
        // 支付宝处理完请求后，当前页面自 动跳转到商户网站里指定页面的 http 路径。
        signParamsMap.put("return_url", return_url);

        // 服务器异步通知页面路径 String(190)
        // 支付宝服务器主动通知商户网站 里指定的页面 http 路径。
        signParamsMap.put("notify_url", notify_url);

        signParamsMap.put("_input_charset", _input_charset);
        signParamsMap.put("partner", partner);
    }

    /**
     * 设置防钓鱼参数.
     * 
     * @param signParamsMap
     *            the sign params map
     */
    private void setAntiPhishingParams(Map<String, String> signParamsMap){
        if (isOpenAntiPhishing){
            String anti_phishing_key = getAnti_phishing_key();
            signParamsMap.put("anti_phishing_key", anti_phishing_key);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jumbo.brandstore.payment.PaymentAdaptor#notifyVerify(java.lang.String, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public PaymentResult verifyNotify(HttpServletRequest request){
        if (Validator.isNullOrEmpty(key)){
            throw new NullPointerException("the key is null or empty!");
        }

        assertAlipaySignLegal(request);

        // 获取支付宝返回数据之一的通知校验ID（notify_id），按照支付宝要求的格式拼接成要请求的链接
        // 示例https://mapi.alipay.com/gateway.do?service=notify_verify&partner=2088002396712354&notify_id=RqPnCoPT3K9%252Fvwbh3I%252BFioE227%252BPfNMl8jwyZqMIiXQWxhOCmQ5MQO%252FWd93rvCB%252BaiGg

        //验证此次通知信息是否是支付宝服务器发来的信息，以帮助校验反馈回来的数据的真假性
        // 通过访问这个请求链接，利用编程方法来模拟http请求与支付宝服务器进行交互，获得支付宝服务器上处理的结果。 如果获得的信息是true，则校验成功；如果获得的信息是其他，则校验失败。
        String notify_id = request.getParameter("notify_id");

        StringBuilder sb = new StringBuilder();
        sb.append(gateway);
        sb.append("?");
        sb.append("service=" + service_notify_verify);
        sb.append("&partner=" + partner);
        sb.append("&notify_id=" + notify_id);
        String alipayNotifyURL = sb.toString();

        String notifyVerifyResult = URLConnectionUtil.readLine(alipayNotifyURL);
        // 如果获得的信息是true，则校验成功；如果获得的信息是其他，则校验失败。
        boolean isNotifyVerifySuccess = "true".equals(notifyVerifyResult);

        // 付款成功
        return isNotifyVerifySuccess ? PaymentResult.PAID : PaymentResult.FAIL;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jumbo.brandstore.payment.PaymentAdaptor#getFeedbackSoCode(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getFeedbackTradeNo(HttpServletRequest request){
        return request.getParameter("out_trade_no");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jumbo.brandstore.payment.PaymentAdaptor#doGetFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getFeedbackTotalFee(HttpServletRequest request){
        return request.getParameter("total_fee");
    }

    // **********************************************************************************************************

    /**
     * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数 <br>
     * 注意：远程解析XML出错，与服务器是否支持SSL等配置有关.
     *
     * @return 时间戳字符串
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @throws Dom4jException
     *             the dom4j exception
     */
    private final String getAnti_phishing_key() throws UncheckedIOException,Dom4jException{
        // 构造访问query_timestamp接口的URL串
        StringBuilder sb = new StringBuilder();
        sb.append(gateway);
        sb.append("?");
        sb.append("service=" + service_query_timestamp);
        sb.append("&");
        sb.append("partner=" + partner);
        String url = sb.toString();

        String responseBodyAsString = URLConnectionUtil.getResponseBodyAsString(url);

        Document document = Dom4jUtil.string2Document(responseBodyAsString);
        return getAnti_phishing_key(document);
    }

    /**
     * 获得 anti_phishing_key.
     *
     * @param document
     *            the document
     * @return the anti_phishing_key
     * @since 1.1.2
     */
    private String getAnti_phishing_key(Document document){

        if (log.isInfoEnabled()){
            log.info("document:{}", document.toString());

            // <alipay>
            // <is_success>T</is_success>
            // <request>
            // <param name="service">query_timestamp</param>
            // <param name="partner">2088201564862550</param>
            // </request>
            // <response>
            // <timestamp>
            // <encrypt_key>KPr8DuZp5xc031OVxw==</encrypt_key>
            // </timestamp>
            // </response>
            // <sign>1fc434a9045f5681736cd47ee2faa41a</sign>
            // <sign_type>MD5</sign_type>
            // </alipay>
        }
        StringBuilder result = new StringBuilder();
        @SuppressWarnings("unchecked")
        List<Node> nodeList = document.selectNodes("//alipay/*");
        for (Node node : nodeList){
            // 截取部分不需要解析的信息
            String name = node.getName();
            String text = node.getText();
            if (name.equals("is_success") && text.equals("T")){
                // 判断是否有成功标示
                @SuppressWarnings("unchecked")
                List<Node> nodeList1 = document.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1){
                    result.append(node1.getText());
                }
            }
        }

        String anti_phishing_key = result.toString();

        if (log.isDebugEnabled()){
            log.debug("anti_phishing_key value:[{}]", anti_phishing_key);
        }
        return anti_phishing_key;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#doRedirectVerify(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public PaymentResult verifyRedirect(HttpServletRequest request){
        // TODO
        return PaymentResult.PAID;
    }

    // ****************************************************************************************************************************

    /**
     * 设置 验证通知的 service.
     * 
     * @param service_notify_verify
     *            the service_notify_verify to set
     */
    public void setService_notify_verify(String service_notify_verify){
        this.service_notify_verify = service_notify_verify;
    }

    /**
     * 设置 时间戳查询接口,用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数.
     * 
     * @param service_query_timestamp
     *            the service_query_timestamp to set
     */
    public void setService_query_timestamp(String service_query_timestamp){
        this.service_query_timestamp = service_query_timestamp;
    }

    /**
     * 设置 是否开通开通 防钓鱼认证,默认否<br>
     * 如果开通了 设置为true,那么 发送到支付网关 会自动设置 这个参数值.
     * 
     * @param isOpenAntiPhishing
     *            the isOpenAntiPhishing to set
     */
    public void setIsOpenAntiPhishing(Boolean isOpenAntiPhishing){
        this.isOpenAntiPhishing = isOpenAntiPhishing;
    }

}
