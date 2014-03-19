/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.netpay.command;

import java.io.Serializable;
import java.util.Date;

import com.feilong.netpay.adaptor.else1.YiChengPay;

/**
 * 新华一城网配置参数 实体类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 26, 2010 4:48:36 PM
 */
public class YiChengPayEntity implements Serializable{

	private static final long	serialVersionUID	= 1L;

	// ******************form提交参数*******************************
	/**
	 * 商户号(一城卡提供)
	 */
	private String				merchantNo			= "888888880175003";

	/**
	 * 订单号 商户生成
	 */
	private String				orderId;

	/**
	 * 支付流水号（可以用当前时间） hhmmss
	 */
	private String				serialNo;

	/**
	 * 订单未付金额（必须为两位小数） 单位为元 必需，最低金额为0.1元
	 */
	private String				amount;

	/**
	 * 回调地址，支付成功将向该地址发送支付结果等信息
	 */
	private String				callBackUrl;

	/**
	 * 是否全额支付（0为部分支付，1为全额支付）
	 */
	private String				isFull				= "1";

	/**
	 * 终端号 一城卡提供
	 */
	private String				terminalNo			= "75003003";

	/**
	 * 密文 MD5序列
	 */
	private String				merchantDecodedData;

	// **********************************************************
	/**
	 * 一城网给商业客户密钥,用于拼接md5参数
	 */
	private String				key					= "AFuIFd1wxySyDDqq";

	// ******************回调参数*******************************
	// private static String merchantNo;
	// private static String terminalNo;
	// private static String orderId;
	// private static String serialNo;
	// private static String amount;
	// private static String isFull;
	/**
	 * 支付状态。1-成功，2-失败，3-已付款
	 */
	private String				payStatus;

	/**
	 * 密文 密文 数字签名 MD5序列
	 */
	private String				resultDecodedData;

	// ***********************************************************
	public YiChengPayEntity(){
		super();
	}

	/**
	 * 带参数实例化(用于提交)
	 * 
	 * @param merchantNo
	 *            商户号(一城卡提供)
	 * @param orderId
	 *            订单号 商户生成
	 * @param amount
	 *            订单未付金额（必须为两位小数） 单位为元 必需，最低金额为0.1元
	 * @param callBackUrl
	 *            回调地址，支付成功将向该地址发送支付结果等信息
	 * @param isFull
	 *            是否全额支付（0为部分支付，1为全额支付）
	 * @param terminalNo
	 *            终端号 一城卡提供
	 * @param key
	 *            一城网给商业客户密钥,用于拼接md5参数
	 * @param createTime
	 *            important 必须和数据库订单创建时间一致
	 */
	public YiChengPayEntity(String merchantNo, String orderId, String amount, String callBackUrl, String isFull, String terminalNo, String key,
			Date createTime){
		this.merchantNo = merchantNo;
		this.orderId = orderId;
		this.serialNo = YiChengPay.getSerialNo(createTime);
		this.amount = YiChengPay.convertAmountTo2XiaoShu(amount);
		this.callBackUrl = callBackUrl;
		this.isFull = isFull;
		this.terminalNo = terminalNo;
		this.key = key;
	}

	// **********************************************************
	/**
	 * 商户号(一城卡提供)
	 * 
	 * @return the merchantNo
	 */
	public String getMerchantNo(){
		return merchantNo;
	}

	/**
	 * 商户号(一城卡提供)
	 * 
	 * @param merchantNo
	 *            the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo){
		this.merchantNo = merchantNo;
	}

	/**
	 * 订单号 商户生成
	 * 
	 * @return the orderId
	 */
	public String getOrderId(){
		return orderId;
	}

	/**
	 * 订单号 商户生成
	 * 
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	/**
	 * 支付流水号（可以用当前时间） hhmmss
	 * 
	 * @return the serialNo
	 */
	public String getSerialNo(){
		return serialNo;
	}

	/**
	 * 支付流水号（可以用当前时间） hhmmss
	 * 
	 * @param serialNo
	 *            the serialNo to set
	 */
	public void setSerialNo(String serialNo){
		this.serialNo = serialNo;
	}

	/**
	 * 订单未付金额（必须为两位小数） 单位为元 必需，最低金额为0.1元
	 * 
	 * @return the amount
	 */
	public String getAmount(){
		return amount;
	}

	/**
	 * 订单未付金额（必须为两位小数） 单位为元 必需，最低金额为0.1元
	 * 
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount){
		this.amount = amount;
	}

	/**
	 * 回调地址，支付成功将向该地址发送支付结果等信息
	 * 
	 * @return the callBackUrl
	 */
	public String getCallBackUrl(){
		return callBackUrl;
	}

	/**
	 * 回调地址，支付成功将向该地址发送支付结果等信息
	 * 
	 * @param callBackUrl
	 *            the callBackUrl to set
	 */
	public void setCallBackUrl(String callBackUrl){
		this.callBackUrl = callBackUrl;
	}

	/**
	 * 是否全额支付（0为部分支付，1为全额支付）
	 * 
	 * @return the isFull
	 */
	public String getIsFull(){
		return isFull;
	}

	/**
	 * 是否全额支付（0为部分支付，1为全额支付）
	 * 
	 * @param isFull
	 *            the isFull to set
	 */
	public void setIsFull(String isFull){
		this.isFull = isFull;
	}

	/**
	 * 终端号 一城卡提供
	 * 
	 * @return the terminalNo
	 */
	public String getTerminalNo(){
		return terminalNo;
	}

	/**
	 * 终端号 一城卡提供
	 * 
	 * @param terminalNo
	 *            the terminalNo to set
	 */
	public void setTerminalNo(String terminalNo){
		this.terminalNo = terminalNo;
	}

	/**
	 * 密文 MD5序列
	 * 
	 * @return the merchantDecodedData
	 */
	public String getMerchantDecodedData(){
		return merchantDecodedData;
	}

	/**
	 * 密文 MD5序列
	 * 
	 * @param merchantDecodedData
	 *            the merchantDecodedData to set
	 */
	public void setMerchantDecodedData(String merchantDecodedData){
		this.merchantDecodedData = merchantDecodedData;
	}

	/**
	 * 支付状态。1-成功，2-失败，3-已付款
	 * 
	 * @return the payStatus
	 */
	public String getPayStatus(){
		return payStatus;
	}

	/**
	 * 支付状态。1-成功，2-失败，3-已付款
	 * 
	 * @param payStatus
	 *            the payStatus to set
	 */
	public void setPayStatus(String payStatus){
		this.payStatus = payStatus;
	}

	/**
	 * 密文 密文 数字签名 MD5序列
	 * 
	 * @return the resultDecodedData
	 */
	public String getResultDecodedData(){
		return resultDecodedData;
	}

	/**
	 * 密文 密文 数字签名 MD5序列
	 * 
	 * @param resultDecodedData
	 *            the resultDecodedData to set
	 */
	public void setResultDecodedData(String resultDecodedData){
		this.resultDecodedData = resultDecodedData;
	}

	/**
	 * 一城网给商业客户密钥,用于拼接md5参数
	 * 
	 * @return the key
	 */
	public String getKey(){
		return key;
	}

	/**
	 * 一城网给商业客户密钥,用于拼接md5参数
	 * 
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key){
		this.key = key;
	}
}
