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
package com.feilong.framework.netpay.advance.adaptor.sprintasia.creditcard.command;

import java.io.Serializable;

import com.feilong.framework.bind.parse.varcommand.VarName;
import com.feilong.framework.netpay.advance.command.QueryResultCommand;

/**
 * The Class QueryResult.
 * 
 * <pre>
 *  {@code
 *  	
 * 	<wddxPacket version="1.0">
 * 	<header/>
 * 	<data>
 * 		<struct>
 * 			<var name="TRANSACTIONID">
 * 				<string>868BBC35-A5D1-FCBF-0453F134C99B5553</string>
 * 			</var>
 * 			<var name="ACQUIRERRESPONSECODE">
 * 				<string>000</string>
 * 			</var>
 * 			<var name="SCRUBMESSAGE">
 * 				<string/>
 * 			</var>
 * 			<var name="AMOUNT">
 * 				<number>9011999.0</number>
 * 			</var>
 * 			<var name="SERVICEVERSION">
 * 				<string>2.0</string>
 * 			</var>
 * 			<var name="TRANSACTIONSCRUBCODE">
 * 				<string/>
 * 			</var>
 * 			<var name="MERCHANTTRANSACTIONID">
 * 				<string>010003170001</string>
 * 			</var>
 * 			<var name="CURRENCY">
 * 				<string>IDR</string>
 * 			</var>
 * 			<var name="TRANSACTIONSTATUS">
 * 				<string>APPROVED</string>
 * 			</var>
 * 			<var name="SITEID">
 * 				<string>Blanja2</string>
 * 			</var>
 * 			<var name="TRANSACTIONDATE">
 * 				<string>2014-04-23 15:19:27</string>
 * 			</var>
 * 			<var name="ACQUIRERCODE">
 * 				<string>AUTH20140423152019PM</string>
 * 			</var>
 * 			<var name="SCRUBCODE">
 * 				<string/>
 * 			</var>
 * 			<var name="TRANSACTIONSCRUBMESSAGE">
 * 				<string/>
 * 			</var>
 * 			<var name="ACQUIRERAPPROVALCODE">
 * 				<string>298883</string>
 * 			</var>
 * 			<var name="TRANSACTIONTYPE">
 * 				<string>AUTHORIZATION</string>
 * 			</var>
 * 		</struct>
 * 	</data>
 * </wddxPacket>
 *  }
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014年5月5日 下午6:47:34
 */
public final class CreditCardQueryResult implements Serializable,QueryResultCommand{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/**
	 * 868BBC35-A5D1-FCBF-0453F134C99B5553 <br>
	 * Value: DOacquire transaction ID <br>
	 * Format: Up to 50 alphanumeric characters.
	 */
	@VarName(name = "TRANSACTIONID",sampleValue = "868BBC35-A5D1-FCBF-0453F134C99B5553")
	private String				transactionID;

	//
	/**
	 * 000<br>
	 * Value: Approval code given by the partner bank/acquirer <br>
	 * Format: Up to 10 alphanumeric characters.
	 */
	@VarName(name = "ACQUIRERRESPONSECODE",sampleValue = "000")
	private String				acquirerResponseCode;

	// <string/>
	/**
	 * Value: System generated message by DOacquire when transaction status is SCRUB, ERROR or CANCEL <br>
	 * Format: Up to 250 alphanumeric characters .
	 */
	@VarName(name = "SCRUBMESSAGE")
	private String				scrubMessage;

	/**
	 * 9011999.0 <br>
	 * Value: Transaction amount <br>
	 * Format: Up to 10 numeric characters .
	 */
	@VarName(name = "AMOUNT",sampleValue = "9011999.0")
	private String				amount;

	/** Value: 2.0. */
	@VarName(name = "SERVICEVERSION",sampleValue = "2.0")
	private String				serviceVersion;

	/**
	 * 010003170001 <br>
	 * Value: Merchant’s unique Transaction ID <br>
	 * Format: Up to 50 alphanumeric characters .
	 */
	@VarName(name = "MERCHANTTRANSACTIONID",sampleValue = "010003170001")
	private String				merchantTransactionID;

	/**
	 * IDR <br>
	 * Value: Transaction currency <br>
	 * Format: 3 characters (ISO 4217 alpha-3 format. Example: USD, MYR, IDR).
	 */
	@VarName(name = "CURRENCY",sampleValue = "IDR")
	private String				currency;

	/**
	 * APPROVED <br>
	 * Value: DOacquire transaction status Format: <br>
	 *  APPROVE (transaction has been authorized by the partner bank/acquirer) <br>
	 *  DECLINE (transaction has been rejected by the partner bank/acquirer) <br>
	 *  SCRUB (transaction has been rejected based on account risk policy, Please refer the details in response parameter ‘scrubCode’ and
	 * ‘scrubMessage’) <br>
	 *  ERROR (please refer the details in response parameter ‘scrubCode’ and ‘scrubMessage’) <br>
	 *  CANCEL (transaction is not completed by cardholder. Please refer the details in response parameter ‘scrubCode’ and ‘scrubMessage’)
	 * .
	 */
	@VarName(name = "TRANSACTIONSTATUS",sampleValue = "APPROVED")
	private String				transactionStatus;

	/**
	 * Blanja2<br>
	 * Value: Merchant’s DOacquire ID <br>
	 * Format: Up to 20 alphanumeric characters .
	 */
	@VarName(name = "SITEID",sampleValue = "Blanja2")
	private String				siteID;

	/**
	 * AUTH20140423152019PM<br>
	 * Value: Partner bank/acquirer transaction ID <br>
	 * Format: Up to 10 alphanumeric characters.
	 */
	@VarName(name = "ACQUIRERCODE",sampleValue = "AUTH20140423152019PM")
	private String				acquirerCode;

	/**
	 * Value: System generated code by DOacquire when transaction status is SCRUB, ERROR or CANCEL <br>
	 * Format: Up to 10 alphanumeric characters .
	 */
	@VarName(name = "SCRUBCODE")
	private String				scrubCode;

	/**
	 * 298883<br>
	 * Value: Response code received from the partner bank/acquirer <br>
	 * Format: Up to 10 alphanumeric characters .
	 */
	@VarName(name = "ACQUIRERAPPROVALCODE",sampleValue = "298883")
	private String				acquirerApprovalCode;

	/**
	 * AUTHORIZATION <br>
	 * Value: <br>
	 *  AUTHORIZATION <br>
	 *  CAPTURE <br>
	 *  VOID CAPTURE <br>
	 *  SALES <br>
	 *  VOID <br>
	 *  REFUND <br>
	 *  FORCE.
	 */
	@VarName(name = "TRANSACTIONTYPE",sampleValue = "AUTHORIZATION")
	private String				transactionType;

	// *************************** 文档没有******************************************

	/** 2014-04-23 15:19:27 文档没有. */
	@VarName(name = "TRANSACTIONDATE")
	private String				transactionDate;

	/** 文档没有. */
	@VarName(name = "TRANSACTIONSCRUBMESSAGE")
	private String				transactionScrubMessage;

	// <string/>
	/** The TRANSACTIONSCRUBCODE. */
	@VarName(name = "TRANSACTIONSCRUBCODE")
	private String				transactionScrubcode;

	/**
	 * 获得 868BBC35-A5D1-FCBF-0453F134C99B5553 <br>
	 * Value: DOacquire transaction ID <br>
	 * Format: Up to 50 alphanumeric characters.
	 * 
	 * @return the transactionID
	 */
	public String getTransactionID(){
		return transactionID;
	}

	/**
	 * 设置 868BBC35-A5D1-FCBF-0453F134C99B5553 <br>
	 * Value: DOacquire transaction ID <br>
	 * Format: Up to 50 alphanumeric characters.
	 * 
	 * @param transactionID
	 *            the transactionID to set
	 */
	public void setTransactionID(String transactionID){
		this.transactionID = transactionID;
	}

	/**
	 * 获得 000<br>
	 * Value: Approval code given by the partner bank/acquirer <br>
	 * Format: Up to 10 alphanumeric characters.
	 * 
	 * @return the acquirerResponseCode
	 */
	public String getAcquirerResponseCode(){
		return acquirerResponseCode;
	}

	/**
	 * 设置 000<br>
	 * Value: Approval code given by the partner bank/acquirer <br>
	 * Format: Up to 10 alphanumeric characters.
	 * 
	 * @param acquirerResponseCode
	 *            the acquirerResponseCode to set
	 */
	public void setAcquirerResponseCode(String acquirerResponseCode){
		this.acquirerResponseCode = acquirerResponseCode;
	}

	/**
	 * 获得 value: System generated message by DOacquire when transaction status is SCRUB, ERROR or CANCEL <br>
	 * Format: Up to 250 alphanumeric characters .
	 * 
	 * @return the scrubMessage
	 */
	public String getScrubMessage(){
		return scrubMessage;
	}

	/**
	 * 设置 value: System generated message by DOacquire when transaction status is SCRUB, ERROR or CANCEL <br>
	 * Format: Up to 250 alphanumeric characters .
	 * 
	 * @param scrubMessage
	 *            the scrubMessage to set
	 */
	public void setScrubMessage(String scrubMessage){
		this.scrubMessage = scrubMessage;
	}

	/**
	 * 获得 9011999.
	 * 
	 * @return the amount
	 */
	public String getAmount(){
		return amount;
	}

	/**
	 * 设置 9011999.
	 * 
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount){
		this.amount = amount;
	}

	/**
	 * 获得 value: 2.
	 * 
	 * @return the serviceVersion
	 */
	public String getServiceVersion(){
		return serviceVersion;
	}

	/**
	 * 设置 value: 2.
	 * 
	 * @param serviceVersion
	 *            the serviceVersion to set
	 */
	public void setServiceVersion(String serviceVersion){
		this.serviceVersion = serviceVersion;
	}

	/**
	 * 获得 010003170001 <br>
	 * Value: Merchant’s unique Transaction ID <br>
	 * Format: Up to 50 alphanumeric characters .
	 * 
	 * @return the merchantTransactionID
	 */
	public String getMerchantTransactionID(){
		return merchantTransactionID;
	}

	/**
	 * 设置 010003170001 <br>
	 * Value: Merchant’s unique Transaction ID <br>
	 * Format: Up to 50 alphanumeric characters .
	 * 
	 * @param merchantTransactionID
	 *            the merchantTransactionID to set
	 */
	public void setMerchantTransactionID(String merchantTransactionID){
		this.merchantTransactionID = merchantTransactionID;
	}

	/**
	 * 获得 iDR <br>
	 * Value: Transaction currency <br>
	 * Format: 3 characters (ISO 4217 alpha-3 format.
	 * 
	 * @return the currency
	 */
	public String getCurrency(){
		return currency;
	}

	/**
	 * 设置 iDR <br>
	 * Value: Transaction currency <br>
	 * Format: 3 characters (ISO 4217 alpha-3 format.
	 * 
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency){
		this.currency = currency;
	}

	/**
	 * 获得 aPPROVED <br>
	 * Value: DOacquire transaction status Format: <br>
	 *  APPROVE (transaction has been authorized by the partner bank/acquirer) <br>
	 *  DECLINE (transaction has been rejected by the partner bank/acquirer) <br>
	 *  SCRUB (transaction has been rejected based on account risk policy, Please refer the details in response parameter ‘scrubCode’ and
	 * ‘scrubMessage’) <br>
	 *  ERROR (please refer the details in response parameter ‘scrubCode’ and ‘scrubMessage’) <br>
	 *  CANCEL (transaction is not completed by cardholder.
	 * 
	 * @return the transactionStatus
	 */
	public String getTransactionStatus(){
		return transactionStatus;
	}

	/**
	 * 设置 aPPROVED <br>
	 * Value: DOacquire transaction status Format: <br>
	 *  APPROVE (transaction has been authorized by the partner bank/acquirer) <br>
	 *  DECLINE (transaction has been rejected by the partner bank/acquirer) <br>
	 *  SCRUB (transaction has been rejected based on account risk policy, Please refer the details in response parameter ‘scrubCode’ and
	 * ‘scrubMessage’) <br>
	 *  ERROR (please refer the details in response parameter ‘scrubCode’ and ‘scrubMessage’) <br>
	 *  CANCEL (transaction is not completed by cardholder.
	 * 
	 * @param transactionStatus
	 *            the transactionStatus to set
	 */
	public void setTransactionStatus(String transactionStatus){
		this.transactionStatus = transactionStatus;
	}

	/**
	 * 获得 blanja2<br>
	 * Value: Merchant’s DOacquire ID <br>
	 * Format: Up to 20 alphanumeric characters .
	 * 
	 * @return the siteID
	 */
	public String getSiteID(){
		return siteID;
	}

	/**
	 * 设置 blanja2<br>
	 * Value: Merchant’s DOacquire ID <br>
	 * Format: Up to 20 alphanumeric characters .
	 * 
	 * @param siteID
	 *            the siteID to set
	 */
	public void setSiteID(String siteID){
		this.siteID = siteID;
	}

	/**
	 * 获得 aUTH20140423152019PM<br>
	 * Value: Partner bank/acquirer transaction ID <br>
	 * Format: Up to 10 alphanumeric characters.
	 * 
	 * @return the acquirerCode
	 */
	public String getAcquirerCode(){
		return acquirerCode;
	}

	/**
	 * 设置 aUTH20140423152019PM<br>
	 * Value: Partner bank/acquirer transaction ID <br>
	 * Format: Up to 10 alphanumeric characters.
	 * 
	 * @param acquirerCode
	 *            the acquirerCode to set
	 */
	public void setAcquirerCode(String acquirerCode){
		this.acquirerCode = acquirerCode;
	}

	/**
	 * 获得 value: System generated code by DOacquire when transaction status is SCRUB, ERROR or CANCEL <br>
	 * Format: Up to 10 alphanumeric characters .
	 * 
	 * @return the scrubCode
	 */
	public String getScrubCode(){
		return scrubCode;
	}

	/**
	 * 设置 value: System generated code by DOacquire when transaction status is SCRUB, ERROR or CANCEL <br>
	 * Format: Up to 10 alphanumeric characters .
	 * 
	 * @param scrubCode
	 *            the scrubCode to set
	 */
	public void setScrubCode(String scrubCode){
		this.scrubCode = scrubCode;
	}

	/**
	 * 获得 298883<br>
	 * Value: Response code received from the partner bank/acquirer <br>
	 * Format: Up to 10 alphanumeric characters .
	 * 
	 * @return the acquirerApprovalCode
	 */
	public String getAcquirerApprovalCode(){
		return acquirerApprovalCode;
	}

	/**
	 * 设置 298883<br>
	 * Value: Response code received from the partner bank/acquirer <br>
	 * Format: Up to 10 alphanumeric characters .
	 * 
	 * @param acquirerApprovalCode
	 *            the acquirerApprovalCode to set
	 */
	public void setAcquirerApprovalCode(String acquirerApprovalCode){
		this.acquirerApprovalCode = acquirerApprovalCode;
	}

	/**
	 * 获得 aUTHORIZATION <br>
	 * Value: <br>
	 *  AUTHORIZATION <br>
	 *  CAPTURE <br>
	 *  VOID CAPTURE <br>
	 *  SALES <br>
	 *  VOID <br>
	 *  REFUND <br>
	 *  FORCE.
	 * 
	 * @return the transactionType
	 */
	public String getTransactionType(){
		return transactionType;
	}

	/**
	 * 设置 aUTHORIZATION <br>
	 * Value: <br>
	 *  AUTHORIZATION <br>
	 *  CAPTURE <br>
	 *  VOID CAPTURE <br>
	 *  SALES <br>
	 *  VOID <br>
	 *  REFUND <br>
	 *  FORCE.
	 * 
	 * @param transactionType
	 *            the transactionType to set
	 */
	public void setTransactionType(String transactionType){
		this.transactionType = transactionType;
	}

	/**
	 * 获得 2014-04-23 15:19:27 文档没有.
	 * 
	 * @return the transactionDate
	 */
	public String getTransactionDate(){
		return transactionDate;
	}

	/**
	 * 设置 2014-04-23 15:19:27 文档没有.
	 * 
	 * @param transactionDate
	 *            the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate){
		this.transactionDate = transactionDate;
	}

	/**
	 * 获得 文档没有.
	 * 
	 * @return the transactionScrubMessage
	 */
	public String getTransactionScrubMessage(){
		return transactionScrubMessage;
	}

	/**
	 * 设置 文档没有.
	 * 
	 * @param transactionScrubMessage
	 *            the transactionScrubMessage to set
	 */
	public void setTransactionScrubMessage(String transactionScrubMessage){
		this.transactionScrubMessage = transactionScrubMessage;
	}

	/**
	 * 获得 the TRANSACTIONSCRUBCODE.
	 * 
	 * @return the transactionScrubcode
	 */
	public String getTransactionScrubcode(){
		return transactionScrubcode;
	}

	/**
	 * 设置 the TRANSACTIONSCRUBCODE.
	 * 
	 * @param transactionScrubcode
	 *            the transactionScrubcode to set
	 */
	public void setTransactionScrubcode(String transactionScrubcode){
		this.transactionScrubcode = transactionScrubcode;
	}
}
