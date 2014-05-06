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
package com.feilong.netpay.command;

import java.io.Serializable;

/**
 * 查询结果.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014-5-5 22:56:51
 */
public class QueryResult implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	// **********************required******************************************************
	/** 我们的交易号码,可以是 订单code,也可以是自定义的交易code,每个商城规则可能不一样,the same as PayRequest tradeNo. */
	private String				tradeNo;

	/** 支付网关返回的原生结果. */
	private String				paymentGatewayResult;

	/** 支付网关交易号. */
	private String				paymentGatewayTradeNo;

	/** 支付结果. */
	private PaymentResult		paymentResult;

	/**
	 * Gets the 我们的交易号码,可以是 订单code,也可以是自定义的交易code,每个商城规则可能不一样,the same as PayRequest tradeNo.
	 * 
	 * @return the tradeNo
	 */
	public String getTradeNo(){
		return tradeNo;
	}

	/**
	 * Sets the 我们的交易号码,可以是 订单code,也可以是自定义的交易code,每个商城规则可能不一样,the same as PayRequest tradeNo.
	 * 
	 * @param tradeNo
	 *            the tradeNo to set
	 */
	public void setTradeNo(String tradeNo){
		this.tradeNo = tradeNo;
	}

	/**
	 * 获得 支付网关返回的原生结果.
	 * 
	 * @return the paymentGatewayResult
	 */
	public String getPaymentGatewayResult(){
		return paymentGatewayResult;
	}

	/**
	 * 设置 支付网关返回的原生结果.
	 * 
	 * @param paymentGatewayResult
	 *            the paymentGatewayResult to set
	 */
	public void setPaymentGatewayResult(String paymentGatewayResult){
		this.paymentGatewayResult = paymentGatewayResult;
	}

	/**
	 * 获得 支付网关交易号.
	 * 
	 * @return the paymentGatewayTradeNo
	 */
	public String getPaymentGatewayTradeNo(){
		return paymentGatewayTradeNo;
	}

	/**
	 * 设置 支付网关交易号.
	 * 
	 * @param paymentGatewayTradeNo
	 *            the paymentGatewayTradeNo to set
	 */
	public void setPaymentGatewayTradeNo(String paymentGatewayTradeNo){
		this.paymentGatewayTradeNo = paymentGatewayTradeNo;
	}

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

}