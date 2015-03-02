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
package com.feilong.framework.netpay.payment.adaptor.doku;

/**
 * Doku支付方式.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 11, 2014 5:05:30 PM
 */
public class PermataVALITEPayAdaptor extends AbstractDokuPayAdaptor{

	/** 5511 Not an error, payment code has not been paid by Customer. */
	private String	atmRedirectSuccessStatusCode;

	/**
	 * Validate status.
	 * 
	 * @param STATUSCODE
	 *            the sTATUSCODE
	 * @return true, if successful
	 */
	@Override
	protected boolean validateRedirectStatusParam(String STATUSCODE){
		boolean statusSuccess = atmRedirectSuccessStatusCode.equals(STATUSCODE);
		return statusSuccess;
	}

	/**
	 * Sets the 5511 Not an error, payment code has not been paid by Customer.
	 * 
	 * @param atmRedirectSuccessStatusCode
	 *            the atmRedirectSuccessStatusCode to set
	 */
	public void setAtmRedirectSuccessStatusCode(String atmRedirectSuccessStatusCode){
		this.atmRedirectSuccessStatusCode = atmRedirectSuccessStatusCode;
	}
}
