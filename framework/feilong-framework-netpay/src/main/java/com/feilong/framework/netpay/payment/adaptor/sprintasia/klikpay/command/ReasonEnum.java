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

/**
 * 失败的原因
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 7, 2014 9:00:13 PM
 */
public enum ReasonEnum{

	/** The SUCCESS. */
	SUCCESS("Success","Sukses"),

	/** The CANCELED. */
	CANCELED("Transaction has been canceled.","Transaksi Anda telah dibatalkan."),

	/** The PAID. */
	PAID("Transaction has been paid.","Transaksi Anda telah dibayar."),

	/**
	 * The transaction has expired or exceeded Time limit given..
	 */
	EXPIRED("Transaction has expired.","Transaksi Anda telah kedaluwarsa."),

	/** The CANNOTBEPROCESSED,Technical issues . */
	CANNOTBEPROCESSED("Transaction cannot be processed.","Transaksi Anda telah dapat diproses.");

	// ***********************************************************************

	/** The english. */
	private String	english;

	/** The indonesian. */
	private String	indonesian;

	/**
	 * Instantiates a new reason enum.
	 * 
	 * @param english
	 *            the english
	 * @param indonesian
	 *            the indonesian
	 */
	private ReasonEnum(String english, String indonesian){
		this.english = english;
		this.indonesian = indonesian;
	}

	/**
	 * Gets the indonesian.
	 * 
	 * @return the indonesian
	 */
	public String getIndonesian(){
		return indonesian;
	}

	/**
	 * Sets the indonesian.
	 * 
	 * @param indonesian
	 *            the indonesian to set
	 */
	public void setIndonesian(String indonesian){
		this.indonesian = indonesian;
	}

	/**
	 * Gets the english.
	 * 
	 * @return the english
	 */
	public String getEnglish(){
		return english;
	}

	/**
	 * Sets the english.
	 * 
	 * @param english
	 *            the english to set
	 */
	public void setEnglish(String english){
		this.english = english;
	}
}
