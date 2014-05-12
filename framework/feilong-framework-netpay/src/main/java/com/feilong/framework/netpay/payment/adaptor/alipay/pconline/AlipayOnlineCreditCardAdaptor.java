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
package com.feilong.framework.netpay.payment.adaptor.alipay.pconline;

/**
 * alipay 信用卡支付<br>
 * 见 快捷支付网银前置文档
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 16, 2013 10:18:24 PM
 * @version 1.0.5 2014-5-6 20:38 change name
 */
public class AlipayOnlineCreditCardAdaptor extends AlipayOnlineAdaptor{

	// 目前方法 完全和 alipay 支付一样
	// 只是 注入的 sign参数 增加了 default_login
	// paymethod 值不同
}
