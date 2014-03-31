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
package com.feilong.netpay.adaptor.bca.klikBCA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Payment Inquiry (output).
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 30, 2014 7:50:35 PM
 */
public class OutputListTransactionPGW implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long			serialVersionUID		= 288232184048495608L;

	/** (Mandatory)The user id. */
	private String						userID;

	/** Optional. */
	private String						additionalData;

	/** The output detail payments. */
	private List<OutputDetailPayment>	outputDetailPayments	= new ArrayList<OutputDetailPayment>();

	/**
	 * Gets the (Mandatory)The user id.
	 * 
	 * @return the userID
	 */
	public String getUserID(){
		return userID;
	}

	/**
	 * Sets the (Mandatory)The user id.
	 * 
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(String userID){
		this.userID = userID;
	}

	/**
	 * Gets the optional.
	 * 
	 * @return the additionalData
	 */
	public String getAdditionalData(){
		return additionalData;
	}

	/**
	 * Sets the optional.
	 * 
	 * @param additionalData
	 *            the additionalData to set
	 */
	public void setAdditionalData(String additionalData){
		this.additionalData = additionalData;
	}

	/**
	 * Gets the output detail payments.
	 * 
	 * @return the outputDetailPayments
	 */
	public List<OutputDetailPayment> getOutputDetailPayments(){
		return outputDetailPayments;
	}

	/**
	 * Sets the output detail payments.
	 * 
	 * @param outputDetailPayments
	 *            the outputDetailPayments to set
	 */
	public void setOutputDetailPayments(List<OutputDetailPayment> outputDetailPayments){
		this.outputDetailPayments = outputDetailPayments;
	}

}
