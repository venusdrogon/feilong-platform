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
package com.feilong.framework.netpay.payment.adaptor;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.ReflectUtil;
import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.framework.netpay.payment.adaptor.alipay.BaseAlipayAdaptor;
import com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineAdaptor;
import com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineCreditCardAdaptor;
import com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineInternationalCardAdaptor;
import com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineNetpayAdaptor;
import com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineScanCodeAdaptor;
import com.feilong.framework.netpay.payment.adaptor.alipay.wap.AlipayWapAdaptor;
import com.feilong.framework.netpay.payment.adaptor.alipay.wap.AlipayWapCreditCardAdaptor;
import com.feilong.framework.netpay.payment.adaptor.alipay.wap.AlipayWapNetpayAdaptor;
import com.feilong.framework.netpay.payment.adaptor.chinapnr.ChinapnrAdaptor;
import com.feilong.framework.netpay.payment.adaptor.doku.AbstractDokuPayAdaptor;
import com.feilong.framework.netpay.payment.adaptor.doku.BRIEPayAdaptor;
import com.feilong.framework.netpay.payment.adaptor.doku.CreditCardPayAdaptor;
import com.feilong.framework.netpay.payment.adaptor.doku.MandiriClickPayAdaptor;
import com.feilong.framework.netpay.payment.adaptor.doku.PermataVALITEPayAdaptor;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.creditcard.SprintAsiaCreditCardAdaptor;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikbca.SprintAsiaKlikBCAAdaptor;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.SprintAsiaKlikPayAdaptor;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;

/**
 * 所有 {@link PaymentAdaptor} 的 基础类,包括了 公共属性/通用的验证等方法.
 * 
 * <h4>4个公共属性</h4>
 * 
 * <blockquote>
 * <p>
 * <ul>
 * <li>{@link #validateMinPrice} 默认false,不验证</li>
 * <li>{@link #validateMaxPrice} 默认false,不验证</li>
 * <li>{@link #minPriceForPay} 支持支付的最小金额</li>
 * <li>{@link #maxPriceForPay} 支持支付的最大金额</li>
 * </ul>
 * <p>
 * </blockquote>
 * 
 * 
 * <h4>金额验证规则</h4>
 * 
 * <blockquote>
 * <p>
 * <ol>
 * <li>如果 ,开启了{@link #validateMinPrice},并且 isNotNullOrEmpty({@link #minPriceForPay}),并且 支付金额 <{@link #minPriceForPay},则抛出
 * IllegalArgumentException</li>
 * <li>如果 ,开启了{@link #validateMaxPrice},并且 isNotNullOrEmpty({@link #maxPriceForPay}),并且 支付金额 <{@link #maxPriceForPay},则抛出
 * IllegalArgumentException</li>
 * </ol>
 * <p>
 * </blockquote>
 * 
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 20, 2013 12:29:49 PM
 * @version 1.0.5 2014-5-6 16:47
 * @see SprintAsiaCreditCardAdaptor
 * @see SprintAsiaKlikBCAAdaptor
 * @see SprintAsiaKlikPayAdaptor
 * @see AbstractDokuPayAdaptor
 * @see CreditCardPayAdaptor
 * @see MandiriClickPayAdaptor
 * @see BRIEPayAdaptor
 * @see PermataVALITEPayAdaptor
 * @see ChinapnrAdaptor
 * @see BaseAlipayAdaptor
 * @see AlipayOnlineAdaptor
 * @see AlipayOnlineCreditCardAdaptor
 * @see AlipayOnlineInternationalCardAdaptor
 * @see AlipayOnlineNetpayAdaptor
 * @see AlipayOnlineScanCodeAdaptor
 * @see AlipayWapAdaptor
 * @see AlipayWapCreditCardAdaptor
 * @see AlipayWapNetpayAdaptor
 */
public abstract class AbstractPaymentAdaptor implements PaymentAdaptor{

	/** The Constant log. */
	private static final Logger	log					= LoggerFactory.getLogger(AbstractPaymentAdaptor.class);

	// ****************所有Adaptor 都有的属性 ****************************************************
	/** 最小支付金额,比如 0.01. */
	protected BigDecimal		minPriceForPay		= null;

	/** 最大支付金额,比如 999999999. */
	protected BigDecimal		maxPriceForPay		= null;

	/** 是否验证最大金额. */
	protected Boolean			validateMaxPrice	= false;

	/** 是否验证最小金额. */
	protected Boolean			validateMinPrice	= false;

	// ************************************************************************************
	/**
	 * Post construct.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@PostConstruct
	protected void postConstruct() throws IllegalArgumentException,IllegalAccessException{
		if (log.isInfoEnabled()){
			// FieldCallback fc;
			// ReflectionUtils.doWithFields(getClass(), fc);
			// ReflectUtils.
			//XXX
			Map<String, Object> map = ReflectUtil.getFieldValueMap(this);
			Class<? extends AbstractPaymentAdaptor> clz = getClass();
			log.info("\n{}\n{}", clz.getCanonicalName(), JsonUtil.format(map));
		}
	}

	// **********************************************************************************
	/**
	 * 通用的验证.
	 * <ul>
	 * <li>isNullOrEmpty tradeNo</li>
	 * <li>isNullOrEmpty totalFee</li>
	 * <li>totalFee是否<=0</li>
	 * <li>totalFee>=minPriceForPay</li>
	 * <li>totalFee<=maxPriceForPay</li>
	 * </ul>
	 * 
	 * @param payRequest
	 *            支付请求 {@link PayRequest}
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>{@code totalFee <=0}</li>
	 *             <li>{@code validateMinPrice && isNotNullOrEmpty(minPriceForPay) && totalFee < minPriceForPay}</li>
	 *             <li>{@code validateMaxPrice && isNotNullOrEmpty(maxPriceForPay) && totalFee > maxPriceForPay}</li>
	 *             </ul>
	 * @throws NullPointerException
	 *             <ul>
	 *             <li>isNullOrEmpty(payRequest)</li>
	 *             <li>isNullOrEmpty(tradeNo)</li>
	 *             <li>isNullOrEmpty(totalFee)</li>
	 *             </ul>
	 */
	protected void doCommonValidate(PayRequest payRequest) throws IllegalArgumentException,NullPointerException{
		if (Validator.isNullOrEmpty(payRequest)){
			throw new NullPointerException("payRequest can't be null/empty!");
		}

		String tradeNo = payRequest.getTradeNo();
		BigDecimal totalFee = payRequest.getTotalFee();

		// ******************************************************************
		if (Validator.isNullOrEmpty(tradeNo)){
			throw new NullPointerException("tradeNo can't be null/empty!");
		}

		// **********************验证金额************************************
		if (Validator.isNullOrEmpty(totalFee)){
			throw new NullPointerException(Slf4jUtil.formatMessage("totalFee can't be null/empty!,tradeNo:{}", tradeNo));
		}

		// 金额<=0
		boolean isLEZero = totalFee.compareTo(BigDecimal.ZERO) == -1 || totalFee.compareTo(BigDecimal.ZERO) == 0;
		if (isLEZero){
			throw new IllegalArgumentException(Slf4jUtil.formatMessage("totalFee:[{}] can't < 0", totalFee));
		}

		// 如果配置了 两个价格参数, 那么就会验证价格属于区间
		if (validateMinPrice){
			// 最小支付金额
			if (Validator.isNotNullOrEmpty(minPriceForPay)){
				if (totalFee.compareTo(minPriceForPay) == -1){
					throw new IllegalArgumentException(Slf4jUtil.formatMessage(
							"totalFee:[{}] can't < minPriceForPay:[{}]",
							totalFee,
							minPriceForPay));
				}
			}
		}

		// 最大支付金额
		// 20140411 经过mp2办公室讨论, 刘总意思是 我们这个不验证最大值, 让用户直接跳转到支付网关, 至于支付是否超限 不是我们关心的事情
		// 可能支付网关会修改价格区间范围,所以我们这里不验证,直接跳过去,如果支付失败,让用户自己取消订单或者切换支付方式
		// 以后要改回来, 将这个参数改成 true
		if (validateMaxPrice){
			if (Validator.isNotNullOrEmpty(maxPriceForPay)){
				if (totalFee.compareTo(maxPriceForPay) == 1){
					throw new IllegalArgumentException(Slf4jUtil.formatMessage(
							"totalFee:[{}] can't > maxPriceForPay:[{}]",
							totalFee,
							maxPriceForPay));
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
	 * @return paymentFormEntity
	 * @see PaymentFormEntity
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
	 * 设置 最小支付金额,比如 0.1
	 * 
	 * @param minPriceForPay
	 *            the minPriceForPay to set
	 */
	public void setMinPriceForPay(BigDecimal minPriceForPay){
		this.minPriceForPay = minPriceForPay;
	}

	/**
	 * 设置 最大支付金额,比如 999999999.00
	 * 
	 * @param maxPriceForPay
	 *            the maxPriceForPay to set
	 */
	public void setMaxPriceForPay(BigDecimal maxPriceForPay){
		this.maxPriceForPay = maxPriceForPay;
	}

	/**
	 * 设置 是否验证最大金额.
	 * 
	 * @param validateMaxPrice
	 *            the validateMaxPrice to set
	 */
	public void setValidateMaxPrice(Boolean validateMaxPrice){
		this.validateMaxPrice = validateMaxPrice;
	}

	/**
	 * 设置 是否验证最小金额.
	 * 
	 * @param validateMinPrice
	 *            the validateMinPrice to set
	 */
	public void setValidateMinPrice(Boolean validateMinPrice){
		this.validateMinPrice = validateMinPrice;
	}
}