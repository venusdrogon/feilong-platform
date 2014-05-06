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
package com.feilong.netpay.command;

/**
 * 支付结果.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014年5月5日 下午11:01:24
 */
public enum PaymentResult{

	/** 已支付. */
	PAID,

	/** 未支付. */
	NO_PAID,

	/** 支付失败. */
	FAIL,

	/**
	 * PENDING transaction still in process<br>
	 * (极少数的支付方式会有这样的状态),目前只有 bca credit card有这个状态
	 */
	PENDING

}
