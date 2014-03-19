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

	/** 订单code. */
	private String				soCode;

	/** 用户的姓名. */
	private String				memberName;

	/** 用户的邮箱. */
	private String				memberEmail;

	/** 需要在线支付的总金额(不含运费). */
	private BigDecimal			totalActual			= new BigDecimal(0);

	/** 需要在线支付的运费. */
	private BigDecimal			transferFee			= new BigDecimal(0);

	/** 支付的订单明细行. */
	private List<PaySoLine>		paySoLineList		= new ArrayList<PaySoLine>();

	/**
	 * Gets the 订单code.
	 * 
	 * @return the soCode
	 */
	public String getSoCode(){
		return soCode;
	}

	/**
	 * Sets the 订单code.
	 * 
	 * @param soCode
	 *            the soCode to set
	 */
	public void setSoCode(String soCode){
		this.soCode = soCode;
	}

	/**
	 * Gets the 支付的订单明细行.
	 * 
	 * @return the paySoLineList
	 */
	public List<PaySoLine> getPaySoLineList(){
		return paySoLineList;
	}

	/**
	 * Sets the 支付的订单明细行.
	 * 
	 * @param paySoLineList
	 *            the paySoLineList to set
	 */
	public void setPaySoLineList(List<PaySoLine> paySoLineList){
		this.paySoLineList = paySoLineList;
	}

	/**
	 * Gets the 需要支付的总金额(不含运费).
	 * 
	 * @return the totalActual
	 */
	public BigDecimal getTotalActual(){
		return totalActual;
	}

	/**
	 * Sets the 需要支付的总金额(不含运费).
	 * 
	 * @param totalActual
	 *            the totalActual to set
	 */
	public void setTotalActual(BigDecimal totalActual){
		this.totalActual = totalActual;
	}

	/**
	 * Gets the 需要支付的运费.
	 * 
	 * @return the transferFee
	 */
	public BigDecimal getTransferFee(){
		return transferFee;
	}

	/**
	 * Sets the 需要支付的运费.
	 * 
	 * @param transferFee
	 *            the transferFee to set
	 */
	public void setTransferFee(BigDecimal transferFee){
		this.transferFee = transferFee;
	}

	/**
	 * Gets the 用户的姓名.
	 * 
	 * @return the memberName
	 */
	public String getMemberName(){
		return memberName;
	}

	/**
	 * Sets the 用户的姓名.
	 * 
	 * @param memberName
	 *            the memberName to set
	 */
	public void setMemberName(String memberName){
		this.memberName = memberName;
	}

	/**
	 * Gets the 用户的邮箱.
	 * 
	 * @return the memberEmail
	 */
	public String getMemberEmail(){
		return memberEmail;
	}

	/**
	 * Sets the 用户的邮箱.
	 * 
	 * @param memberEmail
	 *            the memberEmail to set
	 */
	public void setMemberEmail(String memberEmail){
		this.memberEmail = memberEmail;
	}
}
