/*
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
package com.feilong.framework.netpay.payment.exception;

/**
 * 找不到PaymentAdaptor,如果支付工厂找不到 Adaptor,将会抛出这个异常.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014-5-10 3:30:00
 */
public class PaymentAdaptorNotFoundException extends RuntimeException{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 4485565007832406655L;

	/**
	 * Instantiates a new payment adaptor not found exception.
	 */
	public PaymentAdaptorNotFoundException(){
		super();
	}

	/**
	 * Instantiates a new payment adaptor not found exception.
	 * 
	 * @param message
	 *            the message
	 */
	public PaymentAdaptorNotFoundException(String message){
		super(message);
	}

	/**
	 * Instantiates a new payment adaptor not found exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public PaymentAdaptorNotFoundException(Throwable cause){
		super(cause);
	}

	/**
	 * Instantiates a new payment adaptor not found exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public PaymentAdaptorNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
}
