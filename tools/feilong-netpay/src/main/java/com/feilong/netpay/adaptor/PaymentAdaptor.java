/*
 * Copyright (c) 2010 Jumbomart All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Jumbomart.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jumbo.
 *
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.feilong.netpay.adaptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.feilong.netpay.command.PaySo;
import com.feilong.netpay.command.PaymentFormEntity;
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
	 * @param paySo
	 *            交易订单,包含关键交易信息,以便不同的接口实现不同的业务
	 * @param return_url
	 *            页面跳转同步通知页面路径String(200) <br>
	 *            支付宝处理完请求后，当前页面自 动跳转到商户网站里指定页面的 http 路径。
	 * @param notify_url
	 *            服务器异步通知页面路径 String(190) <br>
	 *            支付宝服务器主动通知商户网站 里指定的页面 http 路径。
	 * @param specialSignMap
	 *            特殊签名参数设置 ,可以为null <br>
	 *            会覆盖配置的signMap ，比如 某些 网关支持过期时间设置
	 * @return the payment form entity
	 * @deprecated 请将return_url以及 notify_url 参数封装,调用 {@link #doBeginPayment(PaySo, Map)}
	 */
	PaymentFormEntity doBeginPayment(PaySo paySo,String return_url,String notify_url,Map<String, String> specialSignMap);

	/**
	 * 生成交易表单相关参数.
	 * 
	 * @param request
	 *            the request
	 * @return the payment form entity
	 */
	// PaymentFormEntity doBeginPayment(PaySo paySo,Map<String, String> specialSignMap);

	/**
	 * 拿到订单号返回支付服务器重新验证这笔订单到底有没有支付成功<br>
	 * 验证此次通知信息是否是支付(宝)服务器发来的信息,以帮助校验反馈回来的数据的真假性。
	 * 
	 * @param request
	 * @return
	 */
	boolean doNotifyVerify(HttpServletRequest request);

	/**
	 * 处理跳转参数的校验,主要是做核实参数是否正确<br>
	 * 每个adaptor 如果有需要可以自己实现.
	 * 
	 * @param request
	 *            the request
	 * @return true, if successful
	 */
	boolean doRedirectVerify(HttpServletRequest request);

	// ********************************************************************************************

	/**
	 * feedback 回来 通过request取到 订单号(交易号).
	 * 
	 * @param request
	 *            request
	 * @return the string
	 */
	String doGetFeedbackTradeNo(HttpServletRequest request);

	/**
	 * feedback 回来 ,通过request取到得到 交易金额<br>
	 * 某些商城需要显示 用户实际支付的金额,可以从这里取 不用再次计算了.
	 * 
	 * @param request
	 *            request
	 * @return the string
	 */
	String doGetFeedbackTotalFee(HttpServletRequest request);

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
	boolean doCloseTrade(String orderNo,TradeRole tradeRole) throws HttpClientUtilException;

	/**
	 * 是否支持关闭接口.
	 * 
	 * @return true, if is support close trade
	 */
	boolean isSupportCloseTrade();
}
