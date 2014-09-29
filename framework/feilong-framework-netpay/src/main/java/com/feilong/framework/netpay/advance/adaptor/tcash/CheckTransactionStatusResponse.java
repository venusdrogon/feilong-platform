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
package com.feilong.framework.netpay.advance.adaptor.tcash;

import java.io.Serializable;

import com.feilong.framework.netpay.advance.command.QueryResultCommand;

/**
 * Below table shows T-Cash Check Transaction Status response.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年7月21日 下午4:03:39
 * @since 1.0.8
 */
public final class CheckTransactionStatusResponse implements QueryResultCommand,Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/**
	 * T-Cash reference number that generated when merchant generate public token and used by customer when confirm transaction.<br>
	 * Example 1: 1709131628081576
	 */
	private String				refNum;

	/**
	 * Customer transaction amount <br>
	 * Example 1: 1250.
	 */
	private String				amount;

	/**
	 * Transaction Date <br>
	 * Example 1: 17/09/2013 16:49:50.<br>
	 * Confirmed the format is dd/MM/yyyy HH:mm:ss
	 */
	private String				transactionDate;

	/**
	 * Status Customer Transaction <br>
	 * Example 1: SUCCESS_COMPLETED.
	 */
	private String				Status;

	/**
	 * 获得 t-Cash reference number that generated when merchant generate public token and used by customer when confirm transaction.
	 *
	 * @return the refNum
	 */
	public String getRefNum(){
		return refNum;
	}

	/**
	 * 设置 t-Cash reference number that generated when merchant generate public token and used by customer when confirm transaction.
	 *
	 * @param refNum
	 *            the refNum to set
	 */
	public void setRefNum(String refNum){
		this.refNum = refNum;
	}

	/**
	 * 获得 customer transaction amount <br>
	 * Example 1: 1250.
	 *
	 * @return the amount
	 */
	public String getAmount(){
		return amount;
	}

	/**
	 * 设置 customer transaction amount <br>
	 * Example 1: 1250.
	 *
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount){
		this.amount = amount;
	}

	/**
	 * 获得 transaction Date <br>
	 * Example 1: 17/09/2013 16:49:50.
	 *
	 * @return the transactionDate
	 */
	public String getTransactionDate(){
		return transactionDate;
	}

	/**
	 * 设置 transaction Date <br>
	 * Example 1: 17/09/2013 16:49:50.
	 *
	 * @param transactionDate
	 *            the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate){
		this.transactionDate = transactionDate;
	}

	/**
	 * 获得 status.
	 *
	 * @return the status
	 */
	public String getStatus(){
		return Status;
	}

	/**
	 * 设置 status.
	 *
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status){
		Status = status;
	}

}
