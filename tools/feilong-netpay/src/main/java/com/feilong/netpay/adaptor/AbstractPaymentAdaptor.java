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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.command.PayRequest;
import com.feilong.netpay.command.PaymentFormEntity;
import com.feilong.tools.json.JsonUtil;

/**
 * 所有 Adaptor 的 基础类, 包括了 通用的验证等方法
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 20, 2013 12:29:49 PM
 */
public abstract class AbstractPaymentAdaptor implements PaymentAdaptor{

	private static final Logger	log				= LoggerFactory.getLogger(AbstractPaymentAdaptor.class);

	// ****************所有Adaptor 都有的属性 ****************************************************
	/** 最小支付金额,比如 0.01. */
	protected BigDecimal		minPriceForPay	= null;

	/** 最大支付金额,比如 999999999. */
	protected BigDecimal		maxPriceForPay	= null;

	// ************************************************************************************
	@PostConstruct
	protected void postConstruct(){
		if (log.isDebugEnabled()){

			Class<?> class1 = this.getClass();
			// Class<?> superclass = class1.getSuperclass();
			Field[] declaredField = class1.getDeclaredFields();

			Map<String, Object> map = new HashMap<String, Object>();

			for (int i = 0; i < declaredField.length; i++){
				Field field = declaredField[i];

				String fieldname = field.getName();

				int modifiers = field.getModifiers();
				// log.info(fieldname + ":" + modifiers);

				// 私有并且静态 一般是log
				boolean isPrivateAndStatic = Modifier.isPrivate(modifiers) && Modifier.isStatic(modifiers);
				if (!isPrivateAndStatic){
					field.setAccessible(true);
					try{
						map.put(fieldname, field.get(this));
					}catch (IllegalArgumentException e){
						e.printStackTrace();
					}catch (IllegalAccessException e){
						e.printStackTrace();
					}
				}
			}
			log.debug("\n" + this.getClass().getCanonicalName() + "\n" + JsonUtil.format(map));
		}
	}

	// **********************************************************************************
	/**
	 * 通用的验证
	 * <ul>
	 * <li>isNullOrEmpty tradeNo</li>
	 * <li>isNullOrEmpty totalFee</li>
	 * <li>totalFee>=minPriceForPay&&totalFee<=maxPriceForPay</li>
	 * <li></li>
	 * </ul>
	 * 
	 * @param payRequest
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
		// 如果配置了 两个价格参数, 那么就会验证 价格属于区间
		if (Validator.isNotNullOrEmpty(minPriceForPay) && Validator.isNotNullOrEmpty(maxPriceForPay)){
			if (totalFee.compareTo(minPriceForPay) == -1 || // 最小支付金额
					totalFee.compareTo(maxPriceForPay) == 1){// 最大支付金额
				throw new IllegalArgumentException("totalFee:[" + totalFee + "] can't < " + minPriceForPay + " or > " + maxPriceForPay);
			}
		}
	}

	/**
	 * 获得PaymentFormEntity
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

		// if (log.isDebugEnabled()){
		// log.debug(JsonUtil.format(paymentFormEntity));
		// }
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
}