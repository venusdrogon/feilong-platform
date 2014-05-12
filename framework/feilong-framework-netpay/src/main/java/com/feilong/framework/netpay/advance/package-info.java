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
/**
 * 支付高级文档(此接口 包括 关闭,查询,退款等等功能)<br>
 * 不含HttpServletRequest等信息,可适用于 main方法启动以及不含jsp/servlet功能的启动<br>
 * 同一个支付网关的不同支付类型,可能去支付的参数不一样(比如支付宝 扫码支付,支付宝信用卡支付,支付宝国际卡支付等等等),但是 关闭交易一样
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a> 
 * @version 1.0.6 2014年5月9日 上午1:11:44 
 * @since 1.0.6
 */
package com.feilong.framework.netpay.advance;