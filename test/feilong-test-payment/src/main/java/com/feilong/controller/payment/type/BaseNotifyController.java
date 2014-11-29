package com.feilong.controller.payment.type;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.controller.payment.BasePaymentController;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.servlet.http.RequestUtil;

/**
 * 通用的 PaymentController,包含controller里面一些通用的方法.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
public abstract class BaseNotifyController extends BasePaymentController{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(BaseNotifyController.class);

	/**
	 * 处理通用校验,以及确认交易,更新数据库<br>
	 * .
	 * 
	 * @param paymentAdaptor
	 *            the payment adaptor
	 * @param request
	 *            the request
	 * @return BackWarnEntity 验证通过并更新了数据库返回 isSuccess=true,否则失败 会返回isSuccess=false
	 */
	protected NotifyResultType validateAndUpdateTrade(PaymentAdaptor paymentAdaptor,HttpServletRequest request){

		String requestAllURL = RequestUtil.getRequestFullURL(request, CharsetType.UTF8);

		// ********************② 验证notify参数合法性****************************************************************
		PaymentResult passNotifyVerify = paymentAdaptor.verifyNotify(request);
		if (PaymentResult.PAID != passNotifyVerify){
			log.error("not passNotifyVerify,full request url:{}", requestAllURL);
			return NotifyResultType.NotPassNotifyVerify;
		}

		String paramTradeNo = paymentAdaptor.getFeedbackTradeNo(request);
		String paramAmount = paymentAdaptor.getFeedbackTotalFee(request);

		// // ********************③ 验证交易****************************************************************
		// TradeOnlinePaymentDto tradeOnlinePaymentDto = paymentService.findTradeOnlinePaymentDtoByTradeNo(paramTradeNo);
		//
		// // ********************③.1交易存不存在****************************************************************
		// if (null == tradeOnlinePaymentDto){
		// log.error("tradeOnlinePayment not exist,tradeNo:{},requestAllURL:{}", paramTradeNo, requestAllURL);
		// return NotifyResultType.TradeNotExist;
		// }
		//
		// // *******************③.2是否已经支付************************************************************
		// String status = tradeOnlinePaymentDto.getStatus();
		// boolean transactionStatus_hasPaid = STATUS_HASPAID.equals(status);
		// if (transactionStatus_hasPaid){
		// log.error("tradeOnlinePayment has be paid,tradeNo:{},requestAllURL:{}", paramTradeNo, requestAllURL);
		// return NotifyResultType.TradeHasPaid;
		// }
		//
		// // ********************③.3 返回的金额 和 我们数据库中的金额 不等************************
		// // N 12.2 Total amount. Eg: 10000.00
		// BigDecimal amountDB = tradeOnlinePaymentDto.getAmount();
		// if (amountDB.compareTo(new BigDecimal(paramAmount)) != 0){
		// log.error(
		// " AMOUNT:{} not equals with our amount:{} in database ,tradeNo:{},requestAllURL:{}",
		// paramAmount,
		// amountDB,
		// paramTradeNo,
		// requestAllURL);
		// return NotifyResultType.TradeAmountNotEqauls;
		// }
		//
		// // *******************************preUpdateTradeValidate*****************************************************************
		// boolean preUpdateTradeValidate = preUpdateTradeValidate(tradeOnlinePaymentDto, request);
		// if (!preUpdateTradeValidate){
		// log.error("not pass preUpdateTradeValidate,tradeNo:{}", paramTradeNo);
		// return NotifyResultType.NotPassPreUpdateTradeValidate;
		// }
		//
		// // ********************③.4 验证交易是否可以去支付****************************************************************
		// String orderIds = tradeOnlinePaymentDto.getOrderIds();
		// long[] ids = getOrderIdLongs(orderIds);
		//
		// List<OrderDto> orderDtoList = tradeService.getOrder(ids);
		// // ********************③.5 不存在订单****************************************************************
		// if (Validator.isNullOrEmpty(orderDtoList)){
		// log.error("orderDtoList isNullOrEmpty:{}", ids);
		// return NotifyResultType.TradeOrdersNotExist;
		// }
		//
		// // ********************③.5 订单数和交易里面的订单数不一致****************************************************************
		// int orderDtoListSize = orderDtoList.size();
		// int idsLength = ids.length;
		// if (orderDtoListSize != idsLength){
		// log.error("orderDtoList.size():{} != ids.length:{},ids:{}", orderDtoListSize, idsLength, ids);
		// return NotifyResultType.TradeOrdersLengthNotEqauls;
		// }
		//
		// // ********************③.6 是否有不可去支付的交易****************************************************************
		// for (OrderDto orderDto : orderDtoList){
		// // ***********************************************************************
		// // 判断是否可以去支付
		// boolean canGoToPay = true;
		// canGoToPay = orderDto.supportBuyer(OrderEvent.BUYER_PAY);
		//
		// if (!canGoToPay){
		// // 有一笔不能去支付 直接跳出
		// log.error(
		// "tradeOnlinePayment can not paid,tradeOnlinePayment tradeNo:{},paid order id:{},status:{}",
		// paramTradeNo,
		// orderDto.getId(),
		// orderDto.getStatus());
		// return NotifyResultType.TradeOrdersCanNotPaid;
		// }
		// }

		return NotifyResultType.Success;
		// TODO 判断超时
		// return updateTrade(tradeOnlinePaymentDto, paramTradeNo);
	}

	/**
	 * 更新交易前的自定义验证,比如 klikpay 可以校验 返回的 transactionDate是否和数据库中相匹配<br>
	 * 此流程在 金额验证之后, 订单验证之前,因此,拿到的tradeOnlinePaymentDto 是有效的数据,非空
	 * 
	 * @param tradeOnlinePaymentDto
	 *            the trade online payment dto
	 * @param request
	 *            可以拿到这里的请求参数做判断
	 * @return true, if successful,如果不需要验证可以直接返回true
	 */
	// protected abstract boolean preUpdateTradeValidate(TradeOnlinePaymentDto tradeOnlinePaymentDto,HttpServletRequest request);

	/**
	 * Update sales order.
	 * 
	 * @param tradeOnlinePaymentDto
	 *            the trade online payment dto
	 * @param paramTradeNo
	 *            the param trade no
	 * @return the notify result type
	 */
	// private NotifyResultType updateTrade(TradeOnlinePaymentDto tradeOnlinePaymentDto,String paramTradeNo){
	// // ********************④ 更新订单状态****************************************************************
	// // 更新订单状态 陈翻
	// long tradeOnlinePaymentId = tradeOnlinePaymentDto.getId();
	//
	// boolean isUpdateSuccess = false;
	// try{
	// // 传入tradeOnlinePaymentId， 找到相关的所有的订单，更新订单及订单行状态。
	// // 有任何一张订单不能支付（即前置状态不匹配），则抛出PaymentException，
	// // 在更新订单状态时如果不成功，则抛出PaymentException.
	// // 在更新订单行状态时如果不成功，则抛出PaymentException.
	// // 上述异常都可获取抛出异常处的order id, order number 以及发生异常时的订单状态.
	// isUpdateSuccess = tradeService.buyerPay(tradeOnlinePaymentId);
	// }catch (Exception e){
	// log.error(e.getClass().getName(), e);
	// }
	// if (isUpdateSuccess){
	// log.info("TRANSIDMERCHANT:[{}],AMOUT:[{}],return success", paramTradeNo, tradeOnlinePaymentDto.getAmount());
	// return NotifyResultType.Success;
	// }else{
	// log.error("tradeService.buyerPay:[{}] fail", tradeOnlinePaymentId);
	// return NotifyResultType.TradeUpdateException;
	// }

	// return NotifyResultType.Success;
	// }
}
