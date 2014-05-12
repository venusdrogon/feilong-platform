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
package com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.command;

import java.io.Serializable;

/**
 * Payment Confirmation(output).
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 30, 2014 7:05:24 PM
 */
public class OutputPaymentIPAY implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** The klik pay code. */
	private String				klikPayCode;

	/** (Mandatory)DD/mm/YYYY HH:MM:SS. */
	private String				transactionDate;

	/** (Mandatory) The transaction no. */
	private String				transactionNo;

	/** The currency. */
	private String				currency;

	/** The total amount. */
	private String				totalAmount;

	/** The pay type. */
	private String				payType;

	/** The status. */
	private String				status;

	/** The approval code. */
	private ApprovalCode		approvalCode;

	/** The additional data. */
	private String				additionalData;

	/** The reason. */
	private Reason				reason;

	/**
	 * Gets the klik pay code.
	 * 
	 * @return the klik pay code
	 */
	public String getKlikPayCode(){
		return klikPayCode;
	}

	/**
	 * Sets the klik pay code.
	 * 
	 * @param klikPayCode
	 *            the new klik pay code
	 */
	public void setKlikPayCode(String klikPayCode){
		this.klikPayCode = klikPayCode;
	}

	/**
	 * Gets the (Mandatory)DD/mm/YYYY HH:MM:SS.
	 * 
	 * @return the transactionDate
	 */
	public String getTransactionDate(){
		return transactionDate;
	}

	/**
	 * Sets the (Mandatory)DD/mm/YYYY HH:MM:SS.
	 * 
	 * @param transactionDate
	 *            the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate){
		this.transactionDate = transactionDate;
	}

	/**
	 * Gets the (Mandatory) The transaction no.
	 * 
	 * @return the transactionNo
	 */
	public String getTransactionNo(){
		return transactionNo;
	}

	/**
	 * Sets the (Mandatory) The transaction no.
	 * 
	 * @param transactionNo
	 *            the transactionNo to set
	 */
	public void setTransactionNo(String transactionNo){
		this.transactionNo = transactionNo;
	}

	/**
	 * Gets the currency.
	 * 
	 * @return the currency
	 */
	public String getCurrency(){
		return currency;
	}

	/**
	 * Sets the currency.
	 * 
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency){
		this.currency = currency;
	}

	/**
	 * Gets the total amount.
	 * 
	 * @return the totalAmount
	 */
	public String getTotalAmount(){
		return totalAmount;
	}

	/**
	 * Sets the total amount.
	 * 
	 * @param totalAmount
	 *            the totalAmount to set
	 */
	public void setTotalAmount(String totalAmount){
		this.totalAmount = totalAmount;
	}

	/**
	 * Gets the pay type.
	 * 
	 * @return the payType
	 */
	public String getPayType(){
		return payType;
	}

	/**
	 * Sets the pay type.
	 * 
	 * @param payType
	 *            the payType to set
	 */
	public void setPayType(String payType){
		this.payType = payType;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public String getStatus(){
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status){
		this.status = status;
	}

	/**
	 * Gets the approval code.
	 * 
	 * @return the approvalCode
	 */
	public ApprovalCode getApprovalCode(){
		return approvalCode;
	}

	/**
	 * Sets the approval code.
	 * 
	 * @param approvalCode
	 *            the approvalCode to set
	 */
	public void setApprovalCode(ApprovalCode approvalCode){
		this.approvalCode = approvalCode;
	}

	/**
	 * Gets the additional data.
	 * 
	 * @return the additionalData
	 */
	public String getAdditionalData(){
		return additionalData;
	}

	/**
	 * Sets the additional data.
	 * 
	 * @param additionalData
	 *            the additionalData to set
	 */
	public void setAdditionalData(String additionalData){
		this.additionalData = additionalData;
	}

	/**
	 * Gets the reason.
	 * 
	 * @return the reason
	 */
	public Reason getReason(){
		return reason;
	}

	/**
	 * Sets the reason.
	 * 
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(Reason reason){
		this.reason = reason;
	}

}
