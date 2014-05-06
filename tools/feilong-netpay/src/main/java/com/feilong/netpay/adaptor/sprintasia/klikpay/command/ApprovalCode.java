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
package com.feilong.netpay.adaptor.sprintasia.klikpay.command;

import java.io.Serializable;

/**
 * The Class ApprovalCode.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 7, 2014 6:23:37 PM
 */
public class ApprovalCode implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** The full transaction. */
	private String				fullTransaction;

	/** The installment transaction. */
	private String				installmentTransaction;

	/**
	 * Gets the full transaction.
	 * 
	 * @return the fullTransaction
	 */
	public String getFullTransaction(){
		return fullTransaction;
	}

	/**
	 * Sets the full transaction.
	 * 
	 * @param fullTransaction
	 *            the fullTransaction to set
	 */
	public void setFullTransaction(String fullTransaction){
		this.fullTransaction = fullTransaction;
	}

	/**
	 * Gets the installment transaction.
	 * 
	 * @return the installmentTransaction
	 */
	public String getInstallmentTransaction(){
		return installmentTransaction;
	}

	/**
	 * Sets the installment transaction.
	 * 
	 * @param installmentTransaction
	 *            the installmentTransaction to set
	 */
	public void setInstallmentTransaction(String installmentTransaction){
		this.installmentTransaction = installmentTransaction;
	}

}
