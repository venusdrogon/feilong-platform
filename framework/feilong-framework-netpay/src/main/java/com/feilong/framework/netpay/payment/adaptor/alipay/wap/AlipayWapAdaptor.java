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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.net.ParamUtil;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.adaptor.alipay.BaseAlipayAdaptor;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.tools.dom4j.Dom4jUtil;
import com.feilong.tools.security.oneway.MD5Util;

/**
 * 手机版alipay支付.
 * 
 * @author 冯明雷
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.0 2013-6-4 下午2:05:50
 * @version 1.0.5 2014-5-6 20:38 change name
 * 
 * @deprecated 待重构
 */
@Deprecated
public class AlipayWapAdaptor extends BaseAlipayAdaptor{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(AlipayWapAdaptor.class);

    /** 商品名称. */
    private String              subject;

    /** 商城支付宝账户. */
    private String              seller;

    /** 创建交易接口名称. */
    private String              service_create;

    /** 授权接口名称. */
    private String              service_auth;

    /** 算法名称，商城只支持MD5. */
    private String              sec_id;

    /** 请求参数格式. */
    private String              format;

    /** 接口版本号. */
    private String              v;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.feilong.framework.netpay.payment.adaptor.alipay.BaseAlipayAdaptor#getSignParamsMap(com.feilong.framework.netpay.payment.command
     * .PayRequest, java.util.Map)
     */
    @Override
    protected Map<String, String> getSignParamsMapForPaymentFormEntity(PayRequest payRequest,Map<String, String> specialSignMap){
        try{
            String requestToken = getRequestToken(payRequest, specialSignMap);
            if (Validator.isNotNullOrEmpty(requestToken)){

                //准备alipay.wap.auth.authAndExecute服务的参数
                Map<String, String> requestParams = new HashMap<String, String>();
                StringBuilder reqData = new StringBuilder();
                requestParams.put("service", service_auth);
                reqData.append("<auth_and_execute_req><request_token>" + requestToken + "</request_token></auth_and_execute_req>");
                requestParams.put("req_data", reqData.toString());
                requestParams.put("sec_id", sec_id);
                requestParams.put("partner", partner);
                requestParams.put("format", format);
                requestParams.put("v", v);
                return requestParams;
            }
        }catch (Exception e){
            log.error(e.getClass().getName(), e);
        }

        //TODO
        return null;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#verifyNotify(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public PaymentResult verifyNotify(HttpServletRequest request){

        log.info("getQueryStringLog:{}" + RequestUtil.getQueryStringLog(request));

        boolean isNotifySignOk;

        String notify_data = request.getParameter("notify_data");
        if (Validator.isNotNullOrEmpty(notify_data)){
            isNotifySignOk = isNotifySignOkForNotifyUrl(request);
        }else{
            //解析随浏览器跳转发回的消息参数.
            isNotifySignOk = isNotifySignOk(request);
        }

        if (isNotifySignOk){
            boolean isPaymentSuccess = isPaymentSuccess(request);
            // 付款成功
            return isPaymentSuccess ? PaymentResult.PAID : PaymentResult.FAIL;
        }
        return PaymentResult.FAIL;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#getFeedbackTradeNo(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getFeedbackTradeNo(HttpServletRequest request){
        log.info("getQueryStringLog:{}" + RequestUtil.getQueryStringLog(request));
        String soCode = null;
        soCode = request.getParameter("out_trade_no");
        if (Validator.isNotNullOrEmpty(soCode)){
            return soCode;
        }
        String notify_data = request.getParameter("notify_data");
        log.info("notify--------" + notify_data);
        if (Validator.isNotNullOrEmpty(notify_data)){
            soCode = getValueByKeyForXML(notify_data, "out_trade_no");

        }
        return soCode;
    }

    /**
     * 生成创建交易请求的url，并发送请求获得返回结果.
     *
     * @author 冯明雷
     * @param payRequest
     *            the pay request
     * @param specialSignMap
     *            the special sign map
     * @return String
     * @throws Exception
     *             the exception
     * @time 2013-6-7上午11:17:51
     */
    private String getRequestToken(PayRequest payRequest,Map<String, String> specialSignMap) throws Exception{
        String tradeNo = payRequest.getTradeNo();
        BigDecimal totalFee = payRequest.getTotalFee();
        String return_url = payRequest.getReturnUrl();
        String notify_url = payRequest.getNotifyUrl();

        // 准备alipay.wap.trade.create.direct服务的参数
        StringBuilder sb = new StringBuilder();
        sb.append("<direct_trade_create_req>");
        sb.append("<subject>" + subject + "</subject>");
        sb.append("<out_trade_no>" + tradeNo + "</out_trade_no>");
        sb.append("<total_fee>" + totalFee + "</total_fee>");
        sb.append("<seller_account_name>" + seller + "</seller_account_name>");
        sb.append("<call_back_url>" + return_url + "</call_back_url>");
        sb.append("<notify_url>" + notify_url + "</notify_url>");
        String bankCode = specialSignMap.get(PARAM_DEFAULT_BANK);
        if (Validator.isNotNullOrEmpty(bankCode)){
            sb.append("<cashier_code>" + bankCode + "</cashier_code>");
        }
        // regData.append("<pay_expire>" + pay_expire + "</pay_expire>");
        sb.append("</direct_trade_create_req>");

        Map<String, String> hiddenParamMap = new HashMap<String, String>();
        hiddenParamMap.put("req_data", sb.toString());
        hiddenParamMap.put("service", service_create);
        hiddenParamMap.put("req_id", "" + System.currentTimeMillis());

        hiddenParamMap.put("sec_id", sec_id);
        hiddenParamMap.put("partner", partner);
        hiddenParamMap.put("format", format);
        hiddenParamMap.put("v", v);

        String toBeSignedString = ParamUtil.getToBeSignedString(hiddenParamMap);
        String sign = MD5Util.encode(toBeSignedString + key, _input_charset);
        hiddenParamMap.put("sign", sign);
        String url = URIUtil.getEncodedUrlByValueMap(gateway, hiddenParamMap, _input_charset);

        String invokeUrl = URIUtil.getBeforePath(url) + "?";
        URL serverUrl = new URL(invokeUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) serverUrl.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();

        String params = url.replace(invokeUrl, "");

        httpURLConnection.getOutputStream().write(params.getBytes());
        InputStream is = httpURLConnection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder buffer = new StringBuilder();
        String line = "";
        while ((line = in.readLine()) != null){
            buffer.append(line);
        }

        //URLConnectionUtil.getResponseBodyAsString(urlString);
        String createResult = URLDecoder.decode(buffer.toString(), _input_charset);
        httpURLConnection.disconnect();

        HashMap<String, String> resMap = new HashMap<String, String>();

        Map<String, String> returnParamMap = URIUtil.parseQueryToValueMap(createResult, null);

        String returnV = returnParamMap.get("v");
        String returnService = returnParamMap.get("service");
        String returnPartner = returnParamMap.get("partner");
        String reutrnSign = returnParamMap.get("sign");
        String returnReqId = returnParamMap.get("req_id");
        String returnSecId = returnParamMap.get("sec_id");
        resMap.put("v", returnV);
        resMap.put("service", returnService);
        resMap.put("partner", returnPartner);
        resMap.put("sec_id", returnSecId);
        resMap.put("req_id", returnReqId);
        String businessResult = "";
        if (createResult.contains("<err>")){
            String error = returnParamMap.get("res_error");
            log.error("创建支付宝交易失败：" + error);
            throw new Exception("创建支付宝交易失败：" + error);
        }
        String res_data = returnParamMap.get("res_data");
        resMap.put("res_data", res_data);
        // 验证签名数据
        String verifyData = ParamUtil.getToBeSignedString(resMap);
        String sign1 = MD5Util.encode(verifyData + key, _input_charset);
        if (sign1.equals(reutrnSign)){
            businessResult = getValueByKeyForXML(res_data, "request_token");
        }else{
            log.error("MD5验证签名失败");
            throw new Exception("MD5验证签名失败");
        }
        return businessResult;
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
        boolean isSuccess = false;
        String result = request.getParameter("result");
        if (Validator.isNotNullOrEmpty(result)){
            isSuccess = "success".equals(result);
        }else{
            String notify_data = request.getParameter("notify_data");
            if (Validator.isNotNullOrEmpty(notify_data)){
                String status = getValueByKeyForXML(notify_data, "trade_status");
                isSuccess = "TRADE_FINISHED".equals(status) || "TRADE_SUCCESS".equals(status);
            }
        }
        return isSuccess;
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

        String service = request.getParameter("service");
        String _v = request.getParameter("v");
        String _sec_id = request.getParameter("sec_id");
        String notify_data = request.getParameter("notify_data");

        String verifyData = "service=" + service + "&v=" + _v + "&sec_id=" + _sec_id + "&notify_data=" + notify_data;
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
     * @throws DocumentException
     *             the document exception
     * @time 2013-9-16上午10:16:39
     */
    private String getValueByKeyForXML(String xmlData,String name){
        Document document = Dom4jUtil.string2Document(xmlData);
        Element root = document.getRootElement();
        String value = root.elementText(name);
        return value;
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

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#getFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getFeedbackTotalFee(HttpServletRequest request){
        // TODO
        return null;
    }

    /**
     * 设置 商品名称.
     * 
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject){
        this.subject = subject;
    }

    /**
     * 设置 商城支付宝账户.
     * 
     * @param seller
     *            the seller to set
     */
    public void setSeller(String seller){
        this.seller = seller;
    }

    /**
     * 设置 创建交易接口名称.
     * 
     * @param service_create
     *            the service_create to set
     */
    public void setService_create(String service_create){
        this.service_create = service_create;
    }

    /**
     * 设置 授权接口名称.
     * 
     * @param service_auth
     *            the service_auth to set
     */
    public void setService_auth(String service_auth){
        this.service_auth = service_auth;
    }

    /**
     * 设置 算法名称，商城只支持MD5.
     * 
     * @param sec_id
     *            the sec_id to set
     */
    public void setSec_id(String sec_id){
        this.sec_id = sec_id;
    }

    /**
     * 设置 请求参数格式.
     * 
     * @param format
     *            the format to set
     */
    public void setFormat(String format){
        this.format = format;
    }

    /**
     * 设置 接口版本号.
     * 
     * @param v
     *            the v to set
     */
    public void setV(String v){
        this.v = v;
    }

}
