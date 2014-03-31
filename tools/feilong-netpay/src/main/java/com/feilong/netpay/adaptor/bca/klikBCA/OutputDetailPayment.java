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

/**
 * The Class OutputDetailPayment.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 30, 2014 7:51:39 PM
 */
public class OutputDetailPayment implements Serializable{

	private static final long	serialVersionUID	= 4985013088026800559L;

	/** (Mandatory) The transaction no. */
	private String				transactionNo;

	/** (Mandatory)DD/mm/YYYY HH:MM:SS. */
	private String				transactionDate;

	/** amount fields consist of 3 digits of currency and 11 digits of numeric format. */
	private String				amount;

	/** The description. */
	private String				description;

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
	 * Gets the amount fields consist of 3 digits of currency and 11 digits of numeric format.
	 * 
	 * @return the amount
	 */
	public String getAmount(){
		return amount;
	}

	/**
	 * Sets the amount fields consist of 3 digits of currency and 11 digits of numeric format.
	 * 
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount){
		this.amount = amount;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description){
		this.description = description;
	}

}
