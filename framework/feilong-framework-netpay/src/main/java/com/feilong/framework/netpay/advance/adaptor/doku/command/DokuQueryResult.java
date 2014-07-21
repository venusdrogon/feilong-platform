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
package com.feilong.framework.netpay.advance.adaptor.doku.command;

import java.io.Serializable;

import com.feilong.framework.bind.parse.varcommand.VarName;
import com.feilong.framework.netpay.advance.command.QueryResultCommand;

/**
 * The Class QueryResult.
 * 
 * <pre>
 * 原始结果:
 * {@code
 * <?xml version="1.0"?>
 * <PAYMENT_STATUS>
 * 	<AMOUNT>7790000.00</AMOUNT>
 * 	<TRANSIDMERCHANT>010003660001</TRANSIDMERCHANT>
 * 	<WORDS>e9e6ed65c872f1646644001f1b67fc8bc5de8df6</WORDS>
 * 	<RESPONSECODE>0000</RESPONSECODE>
 * 	<APPROVALCODE>RB1234567890</APPROVALCODE>
 * 	<RESULTMSG>SUCCESS</RESULTMSG>
 * 	<PAYMENTCHANNEL>06</PAYMENTCHANNEL>
 * 	<PAYMENTCODE></PAYMENTCODE>
 * 	<SESSIONID>20140508105926</SESSIONID>
 * 	<BANK>BRI</BANK>
 * 	<MCN></MCN>
 * 	<PAYMENTDATETIME>20140508095526</PAYMENTDATETIME>
 * 	<VERIFYID></VERIFYID>
 * 	<VERIFYSCORE>-1</VERIFYSCORE>
 * 	<VERIFYSTATUS>NA</VERIFYSTATUS>
 * </PAYMENT_STATUS>
 * }
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月8日 上午10:35:51
 * @since 1.0.6
 */
public final class DokuQueryResult implements Serializable,QueryResultCommand{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** The amount. */
	@VarName(name = "AMOUNT",sampleValue = "7790000.00")
	private String				amount;

	/** The transid merchant. */
	@VarName(name = "TRANSIDMERCHANT",sampleValue = "010003660001")
	private String				transidMerchant;

	/** The words. */
	@VarName(name = "WORDS",sampleValue = "e9e6ed65c872f1646644001f1b67fc8bc5de8df6")
	private String				words;

	/** The response code. */
	@VarName(name = "RESPONSECODE",sampleValue = "0000")
	private String				responseCode;

	/** The approval code. */
	@VarName(name = "APPROVALCODE",sampleValue = "RB1234567890")
	private String				approvalCode;

	/** The resultmsg. */
	@VarName(name = "RESULTMSG",sampleValue = "SUCCESS")
	private String				resultmsg;

	/** The payment channel. */
	@VarName(name = "PAYMENTCHANNEL",sampleValue = "06")
	private String				paymentChannel;

	/** The payment code. */
	@VarName(name = "PAYMENTCODE")
	private String				paymentCode;

	/** The session id. */
	@VarName(name = "SESSIONID",sampleValue = "20140508105926")
	private String				sessionId;

	/** The bank. */
	@VarName(name = "BANK",sampleValue = "BRI")
	private String				bank;

	/** The mcn. */
	@VarName(name = "MCN")
	private String				mcn;

	/** 20140508095526 The payment date time. */
	@VarName(name = "PAYMENTDATETIME",sampleValue = "20140508095526")
	private String				paymentDateTime;

	/** The verify id. */
	@VarName(name = "VERIFYID")
	private String				verifyId;

	/** The verify score. */
	@VarName(name = "VERIFYSCORE",sampleValue = "-1")
	private String				verifyScore;

	/** The verify status. */
	@VarName(name = "VERIFYSTATUS",sampleValue = "NA")
	private String				verifyStatus;

	/**
	 * 获得 the amount.
	 * 
	 * @return the amount
	 */
	public String getAmount(){
		return amount;
	}

	/**
	 * 设置 the amount.
	 * 
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount){
		this.amount = amount;
	}

	/**
	 * 获得 the transid merchant.
	 * 
	 * @return the transidMerchant
	 */
	public String getTransidMerchant(){
		return transidMerchant;
	}

	/**
	 * 设置 the transid merchant.
	 * 
	 * @param transidMerchant
	 *            the transidMerchant to set
	 */
	public void setTransidMerchant(String transidMerchant){
		this.transidMerchant = transidMerchant;
	}

	/**
	 * 获得 the words.
	 * 
	 * @return the words
	 */
	public String getWords(){
		return words;
	}

	/**
	 * 设置 the words.
	 * 
	 * @param words
	 *            the words to set
	 */
	public void setWords(String words){
		this.words = words;
	}

	/**
	 * 获得 the response code.
	 * 
	 * @return the responseCode
	 */
	public String getResponseCode(){
		return responseCode;
	}

	/**
	 * 设置 the response code.
	 * 
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(String responseCode){
		this.responseCode = responseCode;
	}

	/**
	 * 获得 the approval code.
	 * 
	 * @return the approvalCode
	 */
	public String getApprovalCode(){
		return approvalCode;
	}

	/**
	 * 设置 the approval code.
	 * 
	 * @param approvalCode
	 *            the approvalCode to set
	 */
	public void setApprovalCode(String approvalCode){
		this.approvalCode = approvalCode;
	}

	/**
	 * 获得 the resultmsg.
	 * 
	 * @return the resultmsg
	 */
	public String getResultmsg(){
		return resultmsg;
	}

	/**
	 * 设置 the resultmsg.
	 * 
	 * @param resultmsg
	 *            the resultmsg to set
	 */
	public void setResultmsg(String resultmsg){
		this.resultmsg = resultmsg;
	}

	/**
	 * 获得 the payment channel.
	 * 
	 * @return the paymentChannel
	 */
	public String getPaymentChannel(){
		return paymentChannel;
	}

	/**
	 * 设置 the payment channel.
	 * 
	 * @param paymentChannel
	 *            the paymentChannel to set
	 */
	public void setPaymentChannel(String paymentChannel){
		this.paymentChannel = paymentChannel;
	}

	/**
	 * 获得 the payment code.
	 * 
	 * @return the paymentCode
	 */
	public String getPaymentCode(){
		return paymentCode;
	}

	/**
	 * 设置 the payment code.
	 * 
	 * @param paymentCode
	 *            the paymentCode to set
	 */
	public void setPaymentCode(String paymentCode){
		this.paymentCode = paymentCode;
	}

	/**
	 * 获得 the session id.
	 * 
	 * @return the sessionId
	 */
	public String getSessionId(){
		return sessionId;
	}

	/**
	 * 设置 the session id.
	 * 
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(String sessionId){
		this.sessionId = sessionId;
	}

	/**
	 * 获得 the bank.
	 * 
	 * @return the bank
	 */
	public String getBank(){
		return bank;
	}

	/**
	 * 设置 the bank.
	 * 
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(String bank){
		this.bank = bank;
	}

	/**
	 * 获得 the mcn.
	 * 
	 * @return the mcn
	 */
	public String getMcn(){
		return mcn;
	}

	/**
	 * 设置 the mcn.
	 * 
	 * @param mcn
	 *            the mcn to set
	 */
	public void setMcn(String mcn){
		this.mcn = mcn;
	}

	/**
	 * 获得 the payment date time.
	 * 
	 * @return the paymentDateTime
	 */
	public String getPaymentDateTime(){
		return paymentDateTime;
	}

	/**
	 * 设置 the payment date time.
	 * 
	 * @param paymentDateTime
	 *            the paymentDateTime to set
	 */
	public void setPaymentDateTime(String paymentDateTime){
		this.paymentDateTime = paymentDateTime;
	}

	/**
	 * 获得 the verify id.
	 * 
	 * @return the verifyId
	 */
	public String getVerifyId(){
		return verifyId;
	}

	/**
	 * 设置 the verify id.
	 * 
	 * @param verifyId
	 *            the verifyId to set
	 */
	public void setVerifyId(String verifyId){
		this.verifyId = verifyId;
	}

	/**
	 * 获得 the verify score.
	 * 
	 * @return the verifyScore
	 */
	public String getVerifyScore(){
		return verifyScore;
	}

	/**
	 * 设置 the verify score.
	 * 
	 * @param verifyScore
	 *            the verifyScore to set
	 */
	public void setVerifyScore(String verifyScore){
		this.verifyScore = verifyScore;
	}

	/**
	 * 获得 the verify status.
	 * 
	 * @return the verifyStatus
	 */
	public String getVerifyStatus(){
		return verifyStatus;
	}

	/**
	 * 设置 the verify status.
	 * 
	 * @param verifyStatus
	 *            the verifyStatus to set
	 */
	public void setVerifyStatus(String verifyStatus){
		this.verifyStatus = verifyStatus;
	}

}
