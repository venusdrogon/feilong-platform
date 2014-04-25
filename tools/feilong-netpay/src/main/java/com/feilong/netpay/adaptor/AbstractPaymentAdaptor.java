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

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.ReflectUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.command.PayRequest;
import com.feilong.netpay.command.PaymentFormEntity;
import com.feilong.tools.json.JsonUtil;

/**
 * 所有 Adaptor 的 基础类, 包括了 通用的验证等方法.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 20, 2013 12:29:49 PM
 */
public abstract class AbstractPaymentAdaptor implements PaymentAdaptor{

	/** The Constant log. */
	private static final Logger	log					= LoggerFactory.getLogger(AbstractPaymentAdaptor.class);

	// ****************所有Adaptor 都有的属性 ****************************************************
	/** 最小支付金额,比如 0.01. */
	protected BigDecimal		minPriceForPay		= null;

	/** 最大支付金额,比如 999999999. */
	protected BigDecimal		maxPriceForPay		= null;

	/** The is valiedate max price. */
	protected Boolean			validateMaxPrice	= false;

	// ************************************************************************************
	/**
	 * Post construct.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 */
	@PostConstruct
	protected void postConstruct() throws IllegalArgumentException,IllegalAccessException{
		if (log.isDebugEnabled()){
//			FieldCallback fc;
//			ReflectionUtils.doWithFields(getClass(), fc);
//			ReflectUtils.
			Map<String, Object> map = ReflectUtil.getFieldValueMap(this);
			Class<? extends AbstractPaymentAdaptor> clz = getClass();
			log.debug("\n{}\n{}", clz.getCanonicalName(), JsonUtil.format(map));
		}
	}

	// **********************************************************************************
	/**
	 * 通用的验证
	 * <ul>
	 * <li>isNullOrEmpty tradeNo</li>
	 * <li>isNullOrEmpty totalFee</li>
	 * <li>totalFee>=minPriceForPay</li>
	 * <li>or totalFee<=maxPriceForPay</li>
	 * </ul>
	 * .
	 * 
	 * @param payRequest
	 *            the pay request
	 */
	protected void doCommonValidate(PayRequest payRequest){

		if (Validator.isNullOrEmpty(payRequest)){
			throw new IllegalArgumentException("payRequest can't be null/empty!");
		}

		String tradeNo = payRequest.getTradeNo();
		BigDecimal totalFee = payRequest.getTotalFee();

		// ******************************************************************
		if (Validator.isNullOrEmpty(tradeNo)){
			throw new IllegalArgumentException("code can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(totalFee)){
			throw new IllegalArgumentException("totalFee can't be null/empty!");
		}

		// **********************************************************
		// 如果配置了 两个价格参数, 那么就会验证价格属于区间
		// 最小支付金额
		if (Validator.isNotNullOrEmpty(minPriceForPay)){
			if (totalFee.compareTo(minPriceForPay) == -1){
				throw new IllegalArgumentException("totalFee:[" + totalFee + "] can't < " + minPriceForPay);
			}
		}

		// 最大支付金额
		// 20140411 经过mp2办公室讨论, 刘总意思是 我们这个不验证最大值, 让用户直接跳转到支付网关, 至于支付是否超限 不是我们关心的事情
		// XXX 可能支付网关会修改价格区间范围,所以我们这里不验证,直接跳过去,如果支付失败,让用户自己取消订单或者切换支付方式

		// 以后要改回来, 将这个参数改成 true
		if (validateMaxPrice){
			if (Validator.isNotNullOrEmpty(maxPriceForPay)){
				if (totalFee.compareTo(maxPriceForPay) == 1){
					throw new IllegalArgumentException("totalFee:[" + totalFee + "] can't > " + maxPriceForPay);
				}
			}
		}
	}

	/**
	 * 获得PaymentFormEntity.
	 * 
	 * @param actionGateway
	 *            提交网关地址
	 * @param method
	 *            method 是get 还是post
	 * @param hiddenParamMap
	 *            参数
	 * @return PaymentFormEntity
	 */
	protected PaymentFormEntity getPaymentFormEntity(String actionGateway,String method,Map<String, String> hiddenParamMap){
		PaymentFormEntity paymentFormEntity = new PaymentFormEntity();
		paymentFormEntity.setAction(actionGateway);
		paymentFormEntity.setMethod(method);
		paymentFormEntity.setHiddenParamMap(hiddenParamMap);
		return paymentFormEntity;
	}

	// *********************************************************************************************
	/**
	 * Sets the 最小支付金额.
	 * 
	 * @param minPriceForPay
	 *            the minPriceForPay to set
	 */
	public void setMinPriceForPay(BigDecimal minPriceForPay){
		this.minPriceForPay = minPriceForPay;
	}

	/**
	 * Sets the 最大支付金额.
	 * 
	 * @param maxPriceForPay
	 *            the maxPriceForPay to set
	 */
	public void setMaxPriceForPay(BigDecimal maxPriceForPay){
		this.maxPriceForPay = maxPriceForPay;
	}

	/**
	 * Sets the is valiedate max price.
	 * 
	 * @param validateMaxPrice
	 *            the validateMaxPrice to set
	 */
	public void setValidateMaxPrice(boolean validateMaxPrice){
		this.validateMaxPrice = validateMaxPrice;
	}

}