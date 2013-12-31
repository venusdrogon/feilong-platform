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
package com.feilong.netpay.taglib;

import java.util.Date;

import com.feilong.netpay.adaptor.YiChengPay;
import com.feilong.netpay.entity.FeiLongYiChengPayEntity;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 生成新华一城 支付form表单
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-10-27 上午11:57:49
 */
public class FeiLongYiChengPayTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	// ************************************************************************************************
	/**
	 * 提交地址 不填则根据当前环境 采用FeiLongYiChengPay.post_url_test 或者FeiLongYiChengPay.post_url_formal
	 */
	private String				action;

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
	// private String serialNo;
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
	 * 终端号 一城卡提供,不填,则采用默认的
	 */
	private String				terminalNo			= "75003003";

	/**
	 * 一城网给商业客户密钥,用于拼接md5参数
	 */
	private String				key					= "AFuIFd1wxySyDDqq";

	/**
	 * 创建时间,用于生成流水号
	 */
	private Date				createTime;

	// *****************************************************************
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.commons.taglib.base.FeiLongBaseTag#writeContent()
	 */
	@Override
	protected Object writeContent(){
		FeiLongYiChengPayEntity feiLongYiChengPayEntity = new FeiLongYiChengPayEntity(
				merchantNo,
				orderId,
				amount,
				callBackUrl,
				isFull,
				terminalNo,
				key,
				createTime);
		return YiChengPay.getWriteContent(action, feiLongYiChengPayEntity);
	}

	// ********************************************************
	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action){
		this.action = action;
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
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(String orderId){
		this.orderId = orderId;
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
	 * @param callBackUrl
	 *            the callBackUrl to set
	 */
	public void setCallBackUrl(String callBackUrl){
		this.callBackUrl = callBackUrl;
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
	 * @param terminalNo
	 *            the terminalNo to set
	 */
	public void setTerminalNo(String terminalNo){
		this.terminalNo = terminalNo;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key){
		this.key = key;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
}
