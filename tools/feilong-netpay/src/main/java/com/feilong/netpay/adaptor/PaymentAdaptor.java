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
package com.feilong.netpay.adaptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.feilong.netpay.command.PayRequest;
import com.feilong.netpay.command.PaymentFormEntity;
import com.feilong.netpay.command.PaymentResult;
import com.feilong.netpay.command.TradeRole;
import com.feilong.tools.net.httpclient.HttpClientUtilException;

/**
 * PaymentAdaptor 接口.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 21, 2013 11:13:51 AM
 */
public interface PaymentAdaptor{

	/**
	 * 生成交易表单相关参数.
	 * 
	 * @param payRequest
	 *            交易订单,包含关键交易信息,以便不同的接口实现不同的业务
	 * @param specialParamMap
	 *            自定义参数map
	 *            <ul>
	 *            <li>可以为null</li>
	 *            <li>也可以是,特殊签名参数设置,会覆盖配置的signMap,比如 某些 网关支持过期时间设置</li>
	 *            </ul>
	 * @return the payment form entity
	 */
	PaymentFormEntity getPaymentFormEntity(PayRequest payRequest,Map<String, String> specialParamMap);

	// ********************************************************************************************

	/**
	 * 拿到订单号返回支付服务器重新验证这笔订单到底有没有支付成功<br>
	 * 验证此次通知信息是否是支付(宝)服务器发来的信息,以帮助校验反馈回来的数据的真假性。.
	 * 
	 * @param request
	 *            the request
	 * @return true, if successful
	 */
	PaymentResult verifyNotify(HttpServletRequest request);

	/**
	 * 处理跳转参数的校验,主要是做核实参数是否正确<br>
	 * 每个adaptor 如果有需要可以自己实现.
	 * 
	 * @param request
	 *            the request
	 * @return true, if successful
	 */
	PaymentResult verifyRedirect(HttpServletRequest request);

	// ********************************************************************************************

	/**
	 * feedback 回来 通过request取到 订单号(交易号).
	 * 
	 * @param request
	 *            request
	 * @return the string
	 */
	String getFeedbackTradeNo(HttpServletRequest request);

	/**
	 * feedback 回来 ,通过request取到得到 交易金额<br>
	 * 某些商城需要显示 用户实际支付的金额,可以从这里取 不用再次计算了.
	 * 
	 * @param request
	 *            request
	 * @return the string
	 */
	String getFeedbackTotalFee(HttpServletRequest request);

	// ************************************************************

	/**
	 * 是否支持关闭接口.
	 * 
	 * @return true, if is support close trade
	 */
	boolean isSupportCloseTrade();

	/**
	 * 关闭交易.
	 * 
	 * @param orderNo
	 *            交易号(订单号) 官方商城唯一订单号
	 * @param tradeRole
	 *            (关闭角色) 一般有 商家 或者 买家 取消交易方：B-买家取消；S-卖家取消
	 * @return 成功返回true
	 * @throws HttpClientUtilException
	 *             the http client util exception
	 */
	boolean closeTrade(String orderNo,TradeRole tradeRole) throws HttpClientUtilException;
}
