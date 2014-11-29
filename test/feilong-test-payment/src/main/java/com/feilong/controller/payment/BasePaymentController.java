package com.feilong.controller.payment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import com.feilong.commons.core.entity.BackWarnEntity;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.framework.netpay.payment.adaptor.doku.BRIEPayAdaptor;
import com.feilong.framework.netpay.payment.adaptor.doku.MandiriClickPayAdaptor;
import com.feilong.framework.netpay.payment.adaptor.doku.PermataVALITEPayAdaptor;
import com.feilong.servlet.http.RequestUtil;

/**
 * 通用的 PaymentController,包含controller里面一些通用的方法.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
public abstract class BasePaymentController{

	/** The Constant log. */
	private static final Logger		log				= LoggerFactory.getLogger(BasePaymentController.class);

	@Resource
	protected ApplicationContext	applicationContext;

	/** 已经支付. */
	protected static final String	STATUS_HASPAID	= "1";

	/** 没有支付(待支付). */
	private static final String		STATUS_NOPAID	= "0";

	/** The payment channel_permata valite pay. */
	@Value("#{p_DokuPayAdaptor['PAYMENTCHANNEL.permataVALITEPay']}")
	private String					paymentChannel_permataVALITEPay;

	/** The payment channel_mandiri click pay. */
	@Value("#{p_DokuPayAdaptor['PAYMENTCHANNEL.mandiriClickPay']}")
	private String					paymentChannel_mandiriClickPay;

	/** The payment channel_ brie pay. */
	@Value("#{p_DokuPayAdaptor['PAYMENTCHANNEL.BRIEPay']}")
	private String					paymentChannel_BRIEPay;

	/**
	 * 是否是doku的ip地址.
	 * 
	 * @param configIPAddress
	 *            the config ip address
	 * @param clientIp
	 *            the client ip
	 * @return true, if successful
	 */
	protected boolean validateIpAddress(String configIPAddress,String clientIp){
		if (Validator.isNullOrEmpty(configIPAddress)){
			return true;
		}
		return configIPAddress.equals(clientIp);
	}

	/**
	 * Return print string.
	 * 
	 * @param response
	 *            the response
	 * @param responseString
	 *            the response string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void printString(HttpServletResponse response,String responseString) throws IOException{
		if (log.isDebugEnabled()){
			log.debug(getClass().getName() + "\n" + responseString);
		}
		PrintWriter writer = response.getWriter();
		writer.print(responseString);
	}

	/**
	 * Redirect.
	 * 
	 * @param request
	 *            the request
	 * @param description
	 *            the description
	 * @param isSuccess
	 *            the is success
	 * @return the string
	 */
	protected String redirect(HttpServletRequest request,Object description,boolean isSuccess){
		String requestStringForLog = RequestUtil.getRequestStringForLog(request);
		BackWarnEntity backWarnEntity = new BackWarnEntity();
		backWarnEntity.setDescription(description);
		backWarnEntity.setIsSuccess(isSuccess);
		request.setAttribute("backWarnEntity", backWarnEntity);
		request.setAttribute(
				"requestStringForLog",
				requestStringForLog.replace("\n", "<br>").replace("\t", "&nbsp;").replace(" ", "&nbsp;&nbsp;"));
		return "feilong.module.payment.paymentResult";
	}

	/**
	 * 获得doku的 支付适配器.
	 * 
	 * @param request
	 *            the request
	 * @return the do ku payment adaptor
	 */
	protected PaymentAdaptor getDoKuPaymentAdaptor(HttpServletRequest request){
		PaymentAdaptor paymentAdaptor = null;
		// N 2 See payment channel code list
		String PAYMENTCHANNEL = request.getParameter("PAYMENTCHANNEL");
		if (PAYMENTCHANNEL.equals(paymentChannel_mandiriClickPay)){
			paymentAdaptor = applicationContext.getBean(MandiriClickPayAdaptor.class);
		}else if (PAYMENTCHANNEL.equals(paymentChannel_permataVALITEPay)){
			paymentAdaptor = applicationContext.getBean(PermataVALITEPayAdaptor.class);
		}else if (PAYMENTCHANNEL.equals(paymentChannel_BRIEPay)){
			paymentAdaptor = applicationContext.getBean(BRIEPayAdaptor.class);
		}else{
			throw new IllegalArgumentException("PAYMENTCHANNEL not support");
		}
		return paymentAdaptor;
	}

	/**
	 * 是否是atm支付
	 * 
	 * @param request
	 * @return
	 */
	protected boolean isATMPayment(HttpServletRequest request){
		// N 2 See payment channel code list
		String PAYMENTCHANNEL = request.getParameter("PAYMENTCHANNEL");
		// ATM支付的 流程和 其他支付方式不一样
		boolean isATMPayment = paymentChannel_permataVALITEPay.equals(PAYMENTCHANNEL);
		return isATMPayment;
	}

	/**
	 * 获得ids 数组.
	 * 
	 * @param orderIds
	 *            the order ids
	 * @return the order id longs
	 */
	protected long[] getOrderIdLongs(String orderIds){
		// 确认交易时候插入数据库的时候,不应该会出现 空的情况
		String[] orderIdArray = orderIds.split(",");
		int orderLength = orderIdArray.length;
		long[] ids = new long[orderLength];
		for (int i = 0, j = orderLength; i < j; ++i){
			ids[i] = Long.parseLong(orderIdArray[i]);
		}
		return ids;
	}
}
