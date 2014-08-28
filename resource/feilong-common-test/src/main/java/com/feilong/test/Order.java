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
package com.feilong.test;


/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 26, 2013 2:42:00 PM
 */
public class Order{

	private String	code;

	private String	memberID;

	private String	merchant_order_code;

	/**
	 * @return the code
	 */
	public String getCode(){
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * @return the memberID
	 */
	public String getMemberID(){
		return memberID;
	}

	/**
	 * @param memberID
	 *            the memberID to set
	 */
	public void setMemberID(String memberID){
		this.memberID = memberID;
	}

	/**
	 * @return the merchant_order_code1
	 */
	public String getMerchant_order_code(){
		return merchant_order_code;
	}

	/**
	 * @param merchant_order_code
	 *            the merchant_order_code1 to set
	 */
	public void setMerchant_order_code(String merchant_order_code){
		this.merchant_order_code = merchant_order_code;
	}

}
