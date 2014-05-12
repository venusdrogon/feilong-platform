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
package com.feilong.framework.netpay.payment.adaptor.sprintasia.creditcard.command;

/**
 * gateway 返回的状态<br>
 * PENDING和APPROVED是我们需要关注的
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 25, 2014 4:28:17 PM
 */
public interface TransactionStatus{

	// Value: DOacquire transaction status
	// Format:
	//  PENDING (transaction still in process)
	//  APPROVE (transaction has been authorized by the partner bank/acquirer)
	//  DECLINE (transaction has been rejected by the partner bank/acquirer)
	//  SCRUB (transaction has been rejected based on account risk policy)
	//  ERROR (network connectivity error with the partner bank/acquirer)
	//  CANCEL (cardholder did not complete the transaction)

	// Jim, list of revised statuses should be as follows:
	// - APPROVED instead of APPROVE
	// - DECLINED instead of DECLINE
	// - SCRUBBED instead of SCRUBB

	/** The PENDING,transaction still in process. */
	String	PENDING		= "PENDING";

	/** The APPROVED,transaction has been authorized by the partner bank/acquirer. */
	String	APPROVED	= "APPROVED";	// - APPROVED instead of APPROVE

	/** The DECLINED,transaction has been rejected by the partner bank/acquirer. */
	String	DECLINED	= "DECLINED";	// - DECLINED instead of DECLINE

	/** The SCRUBBED,transaction has been rejected based on account risk policy. */
	String	SCRUBBED	= "SCRUBBED";	// - SCRUBBED instead of SCRUBB

	/** The ERROR,network connectivity error with the partner bank/acquirer. */
	String	ERROR		= "ERROR";

	/** The CANCEL,cardholder did not complete the transaction. */
	String	CANCEL		= "CANCEL";
}
