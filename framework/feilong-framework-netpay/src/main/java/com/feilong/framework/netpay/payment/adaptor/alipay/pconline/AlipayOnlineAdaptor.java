/**
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.net.ParamUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.adaptor.alipay.BaseAlipayAdaptor;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;
import com.feilong.tools.security.oneway.MD5Util;

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
public class AlipayOnlineAdaptor extends BaseAlipayAdaptor{

    /** The Constant log. */
    private static final Logger log                = LoggerFactory.getLogger(AlipayOnlineAdaptor.class);

    /** 其他需要被签名的 Map. */
    private Map<String, String> signMap;

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
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#getPaymentFormEntity(com.feilong.netpay.command.PayRequest, java.util.Map)
     */
    @Override
    public PaymentFormEntity getPaymentFormEntity(PayRequest payRequest,Map<String, String> specialSignMap){

        doCommonValidate(payRequest);

        String tradeNo = payRequest.getTradeNo();
        BigDecimal totalFee = payRequest.getTotalFee();
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

        if (isPassValidatorSpecialSignMap){

            // *************************************************************************************************
            // 需要被签名的 参数map
            Map<String, String> signParamsMap = new HashMap<String, String>();

            // 注入 或者设置的
            if (Validator.isNotNullOrEmpty(signMap)){
                signParamsMap.putAll(signMap);
            }

            // 特殊 传入
            if (Validator.isNotNullOrEmpty(specialSignMap)){
                signParamsMap.putAll(specialSignMap);
            }

            // 设置防钓鱼参数
            setAntiPhishingParams(signParamsMap);

            // 放在 所有设置的 最下面,保证 核心参数不会被 子类修改
            setCommonAlipayParams(tradeNo, totalFee, return_url, notify_url, signParamsMap);

            // *************************************************************************************
            // 待签名字符串
            // 除去sign、sign_type 两个参数外，其他需要使用到的参数皆是要签名的参数
            String toBeSignedString = ParamUtil.getToBeSignedString(signParamsMap);

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

            String method = "get";
            return getPaymentFormEntity(gateway, method, hiddenParamsMap);
        }
        throw new IllegalArgumentException("specialSignMap has IllegalArgument key");
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
    private void setCommonAlipayParams(
                    String code,
                    BigDecimal total_fee,
                    String return_url,
                    String notify_url,
                    Map<String, String> signParamsMap){
        // 支付宝合作商户网站,唯一订单号 （确保在商户系统中唯一） String(64)
        signParamsMap.put("out_trade_no", code);

        // 交易金额 该笔订单的资金总额，单位为 RMB-Yuan。
        // 取值范围为[0.01， 100000000.00]，
        // 精确到小数点后 两位。
        signParamsMap.put("total_fee", total_fee.setScale(2, BigDecimal.ROUND_HALF_UP) + "");

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

        boolean isNotifySignOk = isNotifySignOk(request);

        if (isNotifySignOk){
            // 获取支付宝返回数据之一的通知校验ID（notify_id），按照支付宝要求的格式拼接成要请求的链接
            // 示例https://mapi.alipay.com/gateway.do?service=notify_verify&partner=2088002396712354&notify_id=RqPnCoPT3K9%252Fvwbh3I%252BFioE227%252BPfNMl8jwyZqMIiXQWxhOCmQ5MQO%252FWd93rvCB%252BaiGg

            // 通过访问这个请求链接，利用编程方法来模拟http请求与支付宝服务器进行交互，
            // 获得支付宝服务器上处理的结果。 如果获得的信息是true，则校验成功；如果获得的信息是其他，则校验失败。
            String notify_id = request.getParameter("notify_id");

            StringBuilder sb = new StringBuilder();
            sb.append(gateway);
            sb.append("?");
            sb.append("service=" + service_notify_verify);
            sb.append("&partner=" + partner);
            sb.append("&notify_id=" + notify_id);
            String alipayNotifyURL = sb.toString();

            boolean isNotifyVerifySuccess = isNotifyVerifySuccess(alipayNotifyURL);

            // 付款成功
            return isNotifyVerifySuccess ? PaymentResult.PAID : PaymentResult.FAIL;
        }
        log.error("isNotifySignOk error");
        return PaymentResult.FAIL;
    }

    /**
     * 校验 返回的请求 <br>
     * 还有没有必要再次 sign 来确认了? --alipay 伦奇说 都需要.
     * 
     * @param request
     *            the request
     * @return true, if is notify sign ok
     */
    private boolean isNotifySignOk(HttpServletRequest request){
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

            // if (key.equals("body") || key.equals("subject")){
            // valueStr = "Nike官方商城商品";
            // }
            params.put(key, stringBuilder.toString());

        }
        String toBeSignedString = ParamUtil.getToBeSignedString(params);
        String mysign = MD5Util.encode(toBeSignedString + key, _input_charset);
        boolean isSignOk = mysign.equals(alipaySign);
        return isSignOk;
    }

    /**
     * 使用 HttpURLConnection 去alipay 上面 验证 是否确实 校验成功.
     * 
     * @param notifyVerifyUrl
     *            通知验证的url
     * @return 如果获得的信息是true，则校验成功；如果获得的信息是其他，则校验失败。
     */
    private boolean isNotifyVerifySuccess(String notifyVerifyUrl){
        try{
            URL url = new URL(notifyVerifyUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String notifyVerifyResult = bufferedReader.readLine();
            // 如果获得的信息是true，则校验成功；如果获得的信息是其他，则校验失败。
            return "true".equals(notifyVerifyResult);
        }catch (Exception e){
            log.error(e.getClass().getName(), e);
        }
        return false;
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
     */
    private final String getAnti_phishing_key(){

        // 构造访问query_timestamp接口的URL串
        StringBuilder sb = new StringBuilder();
        sb.append(gateway);
        sb.append("?");
        sb.append("service=" + service_query_timestamp);
        sb.append("&");
        sb.append("partner=" + partner);

        InputStream inputStream = null;
        try{
            URL url = new URL(sb.toString());
            inputStream = url.openStream();

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);

            if (log.isDebugEnabled()){
                log.debug("document:{}", document.toString());

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

            StringBuffer result = new StringBuffer();
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
        }catch (MalformedURLException e){
            log.error(e.getClass().getName(), e);
        }catch (IOException e){
            log.error(e.getClass().getName(), e);
        }catch (DocumentException e){
            log.error(e.getClass().getName(), e);
        }finally{
            try{
                if (null != inputStream){
                    inputStream.close();
                }
            }catch (IOException e){
                log.error(e.getClass().getName(), e);
            }
        }
        // 内部 不控制,不能影响订单的创建
        return "";
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
     * 设置 其他需要被签名的 Map.
     * 
     * @param signMap
     *            the signMap to set
     */
    public void setSignMap(Map<String, String> signMap){
        this.signMap = signMap;
    }

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
