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
package com.feilong.netpay.adaptor.sprintasia.creditcard.command;

/**
 * The Interface TransactionType.
 */
public interface TransactionType{

	// Required
	// Value:
	//  AUTHORIZATION
	//  CAPTURE
	//  VOID CAPTURE
	//  SALES
	//  VOID
	//  REFUND
	//  FORCE

	/** The AUTHORIZATION. */
	String	AUTHORIZATION	= "AUTHORIZATION";

	/** The CAPTURE. */
	String	CAPTURE			= "CAPTURE";

	/** The VOIDCAPTURE. */
	String	VOIDCAPTURE		= "VOID CAPTURE";

	/** The SALES. */
	String	SALES			= "SALES";

	/** The VOID. */
	String	VOID			= "VOID";

	/** The REFUND. */
	String	REFUND			= "REFUND";

	/** The FORCE. */
	String	FORCE			= "FORCE";
}
