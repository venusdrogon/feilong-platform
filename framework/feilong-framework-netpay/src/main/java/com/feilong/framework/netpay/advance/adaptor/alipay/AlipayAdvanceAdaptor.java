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
package com.feilong.framework.netpay.advance.adaptor.alipay;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.commons.core.net.HttpMethodType;
import com.feilong.commons.core.net.ParamUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.bind.parse.XmlParse;
import com.feilong.framework.bind.parse.base.StandardXpathExpressionXmlParse;
import com.feilong.framework.netpay.advance.AbstractPaymentAdvanceAdaptor;
import com.feilong.framework.netpay.advance.command.QueryRequest;
import com.feilong.framework.netpay.advance.command.QueryResult;
import com.feilong.framework.netpay.advance.command.TradeRole;
import com.feilong.framework.netpay.advance.exception.TradeCloseException;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.tools.net.httpclient3.HttpClientConfig;
import com.feilong.tools.net.httpclient3.HttpClientException;
import com.feilong.tools.net.httpclient3.HttpClientUtil;
import com.feilong.tools.security.oneway.MD5Util;

/**
 * 支付宝相关高级adaptor.
 * 
 * <h3>关闭交易接口: {@link #closeTrade(String, TradeRole)}</h3>
 * 
 * <blockquote>
 * 
 * 只能对符合以下三种情况中的任意一种情况的交易，执行关闭交易的操作。
 * <ol>
 * <li>交易类型是担保交易或即时到账交易；</li>
 * <li>交易状态是“等待买家付款”；</li>
 * <li>COD交易中交易状态是“等待卖家发货”。</li>
 * </ol>
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月9日 上午1:32:02
 * @since 1.0.6
 */
public class AlipayAdvanceAdaptor extends AbstractPaymentAdvanceAdaptor{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(AlipayAdvanceAdaptor.class);

    /**
     * MD5 的私钥是以英文字母和数字组成的 32位字符串。<br>
     * 商户可登录到商户服务中心（https://b.alipay.com），安装数字证书，在“技术服务”栏目中点击“交易安全校验码”，即可查看。<br>
     * 此处的key 千万不要暴露给别人知道
     */
    private String              key;

    /**
     * 合作者身份id 不可为空 <br>
     * 签约的 支付宝账号对应的 支付宝唯一用户名.
     */
    private String              partner;

    /**
     * 表单提交地址 <br>
     * 支付宝网关.
     */
    private String              gateway;

    /** 关闭交易. */
    private String              service_close_trade;

    /** single_trade_query单笔查询. */
    private String              service_single_trade_query;

    /**
     * 参数编码字符集 不可为空 <br>
     * 商户网站使用的 编码格式 如utf-8 ,gbk, gb2312.
     */
    private String              _input_charset;

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.framework.netpay.advance.AbstractPaymentAdvanceAdaptor#closeTrade(java.lang.String,
     * com.feilong.framework.netpay.advance.command.TradeRole)
     */
    @Override
    public boolean closeTrade(String orderNo,TradeRole tradeRole) throws TradeCloseException,IllegalArgumentException{
        //构造关闭请求参数map
        Map<String, String> closeTradeRequestMap = constructCloseTradeRequestMap(orderNo, tradeRole);
        return doCloseTrade(gateway, closeTradeRequestMap);
    }

    /**
     * 构造关闭交易请求map.
     *
     * @param orderNo
     *            the order no
     * @param tradeRole
     *            the trade role
     * @return the map< string, string>
     * @since 1.1.2
     */
    private Map<String, String> constructCloseTradeRequestMap(String orderNo,TradeRole tradeRole){
        Map<String, String> closeTradeRequestMap = new HashMap<String, String>();

        //基本参数
        closeTradeRequestMap.put("service", service_close_trade);
        closeTradeRequestMap.put("partner", partner);
        closeTradeRequestMap.put("_input_charset", _input_charset);

        //业务参数
        closeTradeRequestMap.put("out_order_no", orderNo);//XXX 建议以后 使用trade_no去关闭, 使用订单号关闭交易速度会比较慢  see 交易关闭接口(close_trade)接入与使用规则.pdf
        closeTradeRequestMap.put("trade_role", toTrade_roleParamValue(tradeRole));

        //ip  ip地址  String交易操作者卖家或是买家的客户端ip地址。 可空  10.5.41.76 

        //trade_no 支付宝交易号 String(64) 支付宝根据商户请求，创建订 单生成的支付宝交易号。        最短16位，最长64位。 
        //trade_no和out_order_no不可同时为空。         可空  2011031700597827 

        //TODO 设置成统一方法
        String toBeSignedString = ParamUtil.toNaturalOrderingString(closeTradeRequestMap);
        String sign = MD5Util.encode(toBeSignedString + key, _input_charset);

        closeTradeRequestMap.put("sign", sign);
        closeTradeRequestMap.put("sign_type", "MD5");
        return closeTradeRequestMap;
    }

    /**
     * To trade_role param.
     *
     * @param tradeRole
     *            the trade role
     * @return the string
     * @since 1.1.2
     */
    private String toTrade_roleParamValue(TradeRole tradeRole){

        //如果 trade_role 参数值为 D，且商户网站唯一订单号对应的交易为 COD交易，  则说明是因为 COD交易超时由合作伙伴调用本接口来关闭交易。 

        switch (tradeRole) {
            case BUYER:// 买家取消
                return "B";

            case SELLER:// 卖家取消
                return "S";

            default:
                throw new UnsupportedOperationException("tradeRole: [{" + tradeRole + "}]  not support!");
        }
    }

    /**
     * 关闭交易.
     *
     * @param gateway
     *            the gateway
     * @param closeTradeRequestMap
     *            参数
     * @return true, if successful
     * @throws TradeCloseException
     *             the close trade exception
     * @throws HttpClientException
     *             the http client util exception
     */
    //TODO 返回值转成对象, 一个boolean 不能解决后续问题
    private boolean doCloseTrade(String gateway,Map<String, String> closeTradeRequestMap) throws TradeCloseException,HttpClientException{

        HttpClientConfig httpClientConfig = new HttpClientConfig();

        httpClientConfig.setUri(gateway);
        httpClientConfig.setParams(new TreeMap<String, String>(closeTradeRequestMap));
        httpClientConfig.setHttpMethodType(HttpMethodType.GET);

        String returnXML = HttpClientUtil.getResponseBodyAsString(httpClientConfig);

        if (Validator.isNullOrEmpty(returnXML)){
            throw new TradeCloseException("returnXML can't be null/empty!");
        }

        try{
            AlipayCloseTradeResult alipayCloseTradeResult = convertAlipayResultXMLToAlipayCloseTradeResult(returnXML);
            String errorMessage = alipayCloseTradeResult.getError();
            String is_success = alipayCloseTradeResult.getIs_success();

            //***********************log********************************************

            String orderNo = closeTradeRequestMap.get("out_order_no");
            String message = Slf4jUtil.formatMessage(
                            "close trade : out_order_no:[{}],alipay return:[{}],closeTrade request info:[{}]",
                            orderNo,
                            JsonUtil.format(alipayCloseTradeResult),
                            JsonUtil.format(httpClientConfig));

            //*******************************************************************
            if (AlipayCloseTradeResult.SUCCESS.equals(is_success)){ // 关闭支付宝交易成功
                log.info(message);
                return true;
            }else{
                AlipayErrorCode alipayErrorCode = AlipayErrorCode.getByCodeValue(errorMessage);
                log.warn("alipayErrorCode:[{}]", alipayErrorCode);

                //TODO 这里的逻辑需要理清
                switch (alipayErrorCode) {
                    case TRADE_NOT_EXIST: // 交易不存在
                        log.info(message);
                        return true;
                    default:
                        log.error(message);
                        throw new TradeCloseException(message);
                }
            }
        }catch (DocumentException e){
            log.error(e.getClass().getName(), e);
            throw new TradeCloseException(e);
        }
    }

    /**
     * 解析支付宝返回的xml信息.
     *
     * @author xialong
     * @param alipayResult
     *            the alipay result
     * @return the map
     * @throws DocumentException
     *             the document exception
     */
    private static AlipayCloseTradeResult convertAlipayResultXMLToAlipayCloseTradeResult(String alipayResult) throws DocumentException{
        log.info("alipayResult :\n {}", alipayResult);

        String xpathExpression = "/alipay/*";
        XmlParse<AlipayCloseTradeResult> queryResultXmlParse = new StandardXpathExpressionXmlParse<AlipayCloseTradeResult>(
                        AlipayCloseTradeResult.class,
                        xpathExpression);
        AlipayCloseTradeResult alipayCloseTradeResult = queryResultXmlParse.parseXML(alipayResult);
        alipayCloseTradeResult.setOriginalResult(alipayResult);

        return alipayCloseTradeResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jumbo.brandstore.payment.PaymentAdaptor#isSupportCloseTrade()
     */
    @Override
    public boolean isSupportCloseTrade(){
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.framework.netpay.advance.AbstractPaymentAdvanceAdaptor#getQueryResult(com.feilong.framework.netpay.advance.command.
     * QueryRequest)
     */
    @Override
    public QueryResult getQueryResult(QueryRequest queryRequest){
        String orderNo = queryRequest.getTradeNo();
        Map<String, String> constructSingleTradeQueryRequestMap = constructSingleTradeQueryRequestMap(orderNo);

        HttpClientConfig httpClientConfig = new HttpClientConfig();

        httpClientConfig.setUri(gateway);
        httpClientConfig.setParams(new TreeMap<String, String>(constructSingleTradeQueryRequestMap));
        httpClientConfig.setHttpMethodType(HttpMethodType.GET);

        String returnXML = HttpClientUtil.getResponseBodyAsString(httpClientConfig);

        QueryResult queryResult = new QueryResult();

        //FIXME
        //        queryResult.setGatewayAmount(gatewayAmount);
        //        queryResult.setGatewayPaymentTime(gatewayAmount);
        queryResult.setGatewayResult(returnXML);
        //        queryResult.setGatewayTradeNo(gatewayAmount);
        queryResult.setPaymentResult(PaymentResult.PENDING);
        //        queryResult.setQueryResultCommand(gatewayAmount);
        //        queryResult.setTradeNo(gatewayAmount);

        if (log.isDebugEnabled()){
            log.debug(JsonUtil.format(queryResult));
        }
        return queryResult;
    }

    /**
     * 构造单笔查询接口需要的参数.
     * 
     * <p>
     * 和 调用关闭接口相比,少了 trade_role 参数
     * </p>
     *
     * @param orderNo
     *            the order no
     * @return the map< string, string>
     * @since 1.1.2
     */
    private Map<String, String> constructSingleTradeQueryRequestMap(String orderNo){
        Map<String, String> singleTradeQueryRequestMap = new HashMap<String, String>();

        //基本参数
        //TODO
        singleTradeQueryRequestMap.put("service", service_single_trade_query);
        singleTradeQueryRequestMap.put("partner", partner);
        singleTradeQueryRequestMap.put("_input_charset", _input_charset);

        //业务参数
        singleTradeQueryRequestMap.put("out_order_no", orderNo);//XXX 建议以后 使用trade_no去查询, 建议使用支付宝交易号进行 查询，用商户网站唯一订单号 查询的效率比较低  see 单笔交易查询接口(single_trade_query) v1.2.pdf

        //和 调用关闭接口相比, 少了 trade_role 参数

        //trade_no 支付宝交易号 String(64) 支付宝根据商户请求，创建订 单生成的支付宝交易号。        最短16位，最长64位。 
        //trade_no和out_order_no不可同时为空。         可空  2011031700597827 

        //TODO 设置成统一方法
        String toBeSignedString = ParamUtil.toNaturalOrderingString(singleTradeQueryRequestMap);
        String sign = MD5Util.encode(toBeSignedString + key, _input_charset);

        singleTradeQueryRequestMap.put("sign", sign);
        singleTradeQueryRequestMap.put("sign_type", "MD5");
        return singleTradeQueryRequestMap;
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
     * 设置 关闭交易.
     * 
     * @param service_close_trade
     *            the service_close_trade to set
     */
    public void setService_close_trade(String service_close_trade){
        this.service_close_trade = service_close_trade;
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

    /**
     * 设置 single_trade_query单笔查询.
     *
     * @param service_single_trade_query
     *            the service_single_trade_query to set
     */
    public void setService_single_trade_query(String service_single_trade_query){
        this.service_single_trade_query = service_single_trade_query;
    }
}
