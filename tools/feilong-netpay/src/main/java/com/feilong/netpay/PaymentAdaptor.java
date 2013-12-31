/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */

package com.feilong.netpay;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * PaymentAdaptor 接口
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 21, 2013 11:13:51 AM
 */
public interface PaymentAdaptor{

	/**
	 * 生成交易表单 相关参数.
	 * 
	 * @param code
	 *            订单号/交易号
	 * @param total_fee
	 *            交易总额 单位为 RMB-Yuan 取值范围为[0.01， 100000000.00]
	 * @param return_url
	 *            页面跳转同步通知页面路径String(200) <br>
	 *            支付宝处理完请求后，当前页面自 动跳转到商户网站里指定页面的 http 路径。
	 * @param notify_url
	 *            服务器异步通知页面路径 String(190) <br>
	 *            支付宝服务器主动通知商户网站 里指定的页面 http 路径。
	 * @param specialSignMap
	 *            特殊签名参数设置 ,可以为null 会覆盖配置的signMap ，不如 某些 网关支持过期时间设置
	 * @return the payment form entity
	 */
	PaymentFormEntity doBeginPayment(String code,BigDecimal total_fee,String return_url,String notify_url,Map<String, String> specialSignMap);

	/**
	 * 拿到订单号 返回支付服务器重新验证 这笔订单到底有没有支付成功<br>
	 * 验证此次通知信息是否是支付(宝)服务器发来的信息，以帮助校验反馈回来的数据的 真假性。
	 * 
	 * @param request
	 * @return
	 */
	boolean doNotifyVerify(HttpServletRequest request);

	/**
	 * feedback 回来 通过request取到得到订单号
	 * 
	 * @param request
	 *            request
	 * @return
	 */
	String doGetFeedbackSoCode(HttpServletRequest request);
}
