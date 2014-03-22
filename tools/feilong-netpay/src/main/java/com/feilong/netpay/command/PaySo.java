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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 支付订单信息(独立的entity,不受官方商城版本影响).
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 13, 2014 2:02:28 PM
 */
public class PaySo implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 交易号码,必须唯一,可以是 订单code,也可以是自定义的交易code. */
	private String				tradeNo;

	/** 买家的姓名,一般的支付网关不需要这个参数,但是个别的支付网关是需要的. */
	private String				buyerName;

	/** 买家的邮箱,一般的支付网关不需要这个参数,但是个别的支付网关是需要的. */
	private String				buyerEmail;

	/** 需要在线支付的总金额(含运费),订单行总价加起来总和+运费transferFee 必须＝totalFee. */
	private BigDecimal			totalFee			= new BigDecimal(0);

	/** 需要在线支付的运费,一般的支付网关不需要这个参数,但是个别的支付网关是需要的. */
	private BigDecimal			transferFee			= new BigDecimal(0);

	/** 支付的订单明细行, 一般的支付网关不需要这个参数,但是个别的支付网关是需要的. */
	private List<PaySoLine>		paySoLineList		= new ArrayList<PaySoLine>();

	/**
	 * Gets the 交易号码,必须唯一,可以是 订单code,也可以是自定义的交易code.
	 * 
	 * @return the tradeNo
	 */
	public String getTradeNo(){
		return tradeNo;
	}

	/**
	 * Sets the 交易号码,必须唯一,可以是 订单code,也可以是自定义的交易code.
	 * 
	 * @param tradeNo
	 *            the tradeNo to set
	 */
	public void setTradeNo(String tradeNo){
		this.tradeNo = tradeNo;
	}

	/**
	 * Gets the 买家的姓名,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
	 * 
	 * @return the buyerName
	 */
	public String getBuyerName(){
		return buyerName;
	}

	/**
	 * Sets the 买家的姓名,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
	 * 
	 * @param buyerName
	 *            the buyerName to set
	 */
	public void setBuyerName(String buyerName){
		this.buyerName = buyerName;
	}

	/**
	 * Gets the 买家的邮箱,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
	 * 
	 * @return the buyerEmail
	 */
	public String getBuyerEmail(){
		return buyerEmail;
	}

	/**
	 * Sets the 买家的邮箱,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
	 * 
	 * @param buyerEmail
	 *            the buyerEmail to set
	 */
	public void setBuyerEmail(String buyerEmail){
		this.buyerEmail = buyerEmail;
	}

	/**
	 * Gets the 需要在线支付的总金额(含运费),订单行总价加起来总和+运费transferFee 必须＝totalFee.
	 * 
	 * @return the totalFee
	 */
	public BigDecimal getTotalFee(){
		return totalFee;
	}

	/**
	 * Sets the 需要在线支付的总金额(含运费),订单行总价加起来总和+运费transferFee 必须＝totalFee.
	 * 
	 * @param totalFee
	 *            the totalFee to set
	 */
	public void setTotalFee(BigDecimal totalFee){
		this.totalFee = totalFee;
	}

	/**
	 * Gets the 需要在线支付的运费,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
	 * 
	 * @return the transferFee
	 */
	public BigDecimal getTransferFee(){
		return transferFee;
	}

	/**
	 * Sets the 需要在线支付的运费,一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
	 * 
	 * @param transferFee
	 *            the transferFee to set
	 */
	public void setTransferFee(BigDecimal transferFee){
		this.transferFee = transferFee;
	}

	/**
	 * Gets the 支付的订单明细行, 一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
	 * 
	 * @return the paySoLineList
	 */
	public List<PaySoLine> getPaySoLineList(){
		return paySoLineList;
	}

	/**
	 * Sets the 支付的订单明细行, 一般的支付网关不需要这个参数,但是个别的支付网关是需要的.
	 * 
	 * @param paySoLineList
	 *            the paySoLineList to set
	 */
	public void setPaySoLineList(List<PaySoLine> paySoLineList){
		this.paySoLineList = paySoLineList;
	}

}
