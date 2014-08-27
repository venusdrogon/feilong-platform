/**
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
package com.feilong.controller.payment.type;

/**
 * notify结果 各种验证 类型.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 17, 2014 4:41:03 PM
 */
public enum NotifyResultType{

	/** 成功. */
	Success,

	// ******************************************************

	/** 没有通过NotifyVerify 验证. */
	NotPassNotifyVerify,

	/** 交易不存在. */
	TradeNotExist,

	/** 交易已经被支付了. */
	TradeHasPaid,

	/** 支付金额不匹配. */
	TradeAmountNotEqauls,

	/** 没有通过 自定义的验证. */
	NotPassPreUpdateTradeValidate,

	/** 交易包含的订单不存在. */
	TradeOrdersNotExist,

	/** 交易里面的订单和可以支付的订单数量不匹配. */
	TradeOrdersLengthNotEqauls,

	/** 订单不能支付. */
	TradeOrdersCanNotPaid,

	/** 交易更新异常. */
	TradeUpdateException
}
