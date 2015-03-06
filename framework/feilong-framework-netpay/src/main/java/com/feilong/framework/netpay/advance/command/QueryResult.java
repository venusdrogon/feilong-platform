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
package com.feilong.framework.netpay.advance.command;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.feilong.framework.netpay.command.PaymentResult;

/**
 * 查询结果.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.6 2014年5月8日 下午2:09:38
 * @since 1.0.6
 */
public class QueryResult implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long  serialVersionUID = 288232184048495608L;

    /** 支付结果. */
    private PaymentResult      paymentResult;

    /** 转换成的对象. */
    private QueryResultCommand queryResultCommand;

    /** 我们的交易号码,可以是 订单code,也可以是自定义的交易code,每个商城规则可能不一样,the same as PayRequest tradeNo. */
    private String             tradeNo;

    /** (optional) 支付网关交易号,某些支付网关不会返回这个值,比如 DOKU. */
    private String             gatewayTradeNo;

    /** 支付网关返回的原生结果. */
    private String             gatewayResult;

    /** 支付网关返回的支付金额. */
    private BigDecimal         gatewayAmount;

    /** (optional)支付网关支付的时间,支付网关可能取到的是字符串格式 转换成date. */
    private Date               gatewayPaymentTime;

    /**
     * 获得 支付结果.
     * 
     * @return the paymentResult
     */
    public PaymentResult getPaymentResult(){
        return paymentResult;
    }

    /**
     * 设置 支付结果.
     * 
     * @param paymentResult
     *            the paymentResult to set
     */
    public void setPaymentResult(PaymentResult paymentResult){
        this.paymentResult = paymentResult;
    }

    /**
     * 获得 转换成的对象.
     * 
     * @return the queryResultCommand
     */
    public QueryResultCommand getQueryResultCommand(){
        return queryResultCommand;
    }

    /**
     * 设置 转换成的对象.
     * 
     * @param queryResultCommand
     *            the queryResultCommand to set
     */
    public void setQueryResultCommand(QueryResultCommand queryResultCommand){
        this.queryResultCommand = queryResultCommand;
    }

    /**
     * 获得 我们的交易号码,可以是 订单code,也可以是自定义的交易code,每个商城规则可能不一样,the same as PayRequest tradeNo.
     * 
     * @return the tradeNo
     */
    public String getTradeNo(){
        return tradeNo;
    }

    /**
     * 设置 我们的交易号码,可以是 订单code,也可以是自定义的交易code,每个商城规则可能不一样,the same as PayRequest tradeNo.
     * 
     * @param tradeNo
     *            the tradeNo to set
     */
    public void setTradeNo(String tradeNo){
        this.tradeNo = tradeNo;
    }

    /**
     * 获得 (optional) 支付网关交易号,某些支付网关不会返回这个值,比如 DOKU.
     * 
     * @return the gatewayTradeNo
     */
    public String getGatewayTradeNo(){
        return gatewayTradeNo;
    }

    /**
     * 设置 (optional) 支付网关交易号,某些支付网关不会返回这个值,比如 DOKU.
     * 
     * @param gatewayTradeNo
     *            the gatewayTradeNo to set
     */
    public void setGatewayTradeNo(String gatewayTradeNo){
        this.gatewayTradeNo = gatewayTradeNo;
    }

    /**
     * 获得 支付网关返回的原生结果.
     * 
     * @return the gatewayResult
     */
    public String getGatewayResult(){
        return gatewayResult;
    }

    /**
     * 设置 支付网关返回的原生结果.
     * 
     * @param gatewayResult
     *            the gatewayResult to set
     */
    public void setGatewayResult(String gatewayResult){
        this.gatewayResult = gatewayResult;
    }

    /**
     * 获得 (optional)支付网关支付的时间,支付网关可能取到的是字符串格式 转换成date.
     * 
     * @return the gatewayPaymentTime
     */
    public Date getGatewayPaymentTime(){
        return gatewayPaymentTime;
    }

    /**
     * 设置 (optional)支付网关支付的时间,支付网关可能取到的是字符串格式 转换成date.
     * 
     * @param gatewayPaymentTime
     *            the gatewayPaymentTime to set
     */
    public void setGatewayPaymentTime(Date gatewayPaymentTime){
        this.gatewayPaymentTime = gatewayPaymentTime;
    }

    /**
     * 获得 支付网关返回的支付金额.
     * 
     * @return the gatewayAmount
     */
    public BigDecimal getGatewayAmount(){
        return gatewayAmount;
    }

    /**
     * 设置 支付网关返回的支付金额.
     * 
     * @param gatewayAmount
     *            the gatewayAmount to set
     */
    public void setGatewayAmount(BigDecimal gatewayAmount){
        this.gatewayAmount = gatewayAmount;
    }

}