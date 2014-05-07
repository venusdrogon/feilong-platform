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
package com.feilong.tools.net.httpclient3;

/**
 * The Class HttpClientUtilException.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 30, 2013 1:58:26 AM
 * @since 1.0.6
 */
public class HttpClientUtilException extends Exception{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 4485565007832406655L;

	/**
	 * Instantiates a new http client util exception.
	 */
	public HttpClientUtilException(){
		super();
	}

	/**
	 * Instantiates a new http client util exception.
	 * 
	 * @param message
	 *            the message
	 */
	public HttpClientUtilException(String message){
		super(message);
	}

	/**
	 * Instantiates a new http client util exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public HttpClientUtilException(Throwable cause){
		super(cause);
	}

	/**
	 * Instantiates a new http client util exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public HttpClientUtilException(String message, Throwable cause){
		super(message, cause);
	}
}
