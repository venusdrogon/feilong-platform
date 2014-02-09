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

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.PaymentAdaptor;
import com.feilong.netpay.command.PaymentFormEntity;

/**
 * 所有 Adaptor 的 基础类, 包括了 通用的验证等方法
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 20, 2013 12:29:49 PM
 */
public abstract class AbstractPaymentAdaptor implements PaymentAdaptor{

	private static final Logger	log	= LoggerFactory.getLogger(AbstractPaymentAdaptor.class);

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#doBeginPayment(java.lang.String, java.math.BigDecimal, java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	public PaymentFormEntity doBeginPayment(
			String code,
			BigDecimal total_fee,
			String return_url,
			String notify_url,
			Map<String, String> specialSignMap){
		if (doValidator(code, total_fee, return_url, notify_url)){
			PaymentFormEntity paymentFormEntity = doGetPaymentFormEntity(code, total_fee, return_url, notify_url, specialSignMap);
			if (log.isDebugEnabled()){
				log.debug(JsonFormatUtil.format(paymentFormEntity));
			}
			return paymentFormEntity;
		}
		return null;
	}

	/**
	 * 每个类 自己实现
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
	 * @return
	 */
	protected abstract PaymentFormEntity doGetPaymentFormEntity(
			String code,
			BigDecimal total_fee,
			String return_url,
			String notify_url,
			Map<String, String> specialSignMap);

	/**
	 * 验证参数
	 * 
	 * @param code
	 * @param total_fee
	 * @param return_url
	 * @param notify_url
	 */
	private boolean doValidator(String code,BigDecimal total_fee,String return_url,String notify_url){
		// ******************************************************************
		// validate
		if (Validator.isNullOrEmpty(code)){
			throw new IllegalArgumentException("code can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(total_fee)){
			throw new IllegalArgumentException("total_fee can't be null/empty!");
		}

		// 交易总额 单位为 RMB-Yuan 取值范围为[0.01， 100000000.00]
		// 精确到小数点 后两位
		BigDecimal minPay = new BigDecimal(0.01f);
		BigDecimal maxPay = new BigDecimal(100000000);
		if (total_fee.compareTo(minPay) == -1 || total_fee.compareTo(maxPay) == 1){
			throw new IllegalArgumentException("total_fee:" + total_fee + " can't < " + minPay + " or > " + maxPay);
		}

		if (Validator.isNullOrEmpty(return_url)){
			throw new IllegalArgumentException("return_url can't be null/empty!");
		}

		if (Validator.isNullOrEmpty(notify_url)){
			throw new IllegalArgumentException("notify_url can't be null/empty!");
		}
		return true;
	}
}
