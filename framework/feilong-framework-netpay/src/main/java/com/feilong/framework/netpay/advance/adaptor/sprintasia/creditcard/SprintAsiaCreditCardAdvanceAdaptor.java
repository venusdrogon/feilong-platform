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
package com.feilong.framework.netpay.advance.adaptor.sprintasia.creditcard;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.advance.AbstractPaymentAdvanceAdaptor;
import com.feilong.framework.netpay.advance.adaptor.sprintasia.creditcard.command.CreditCardQueryResult;
import com.feilong.framework.netpay.advance.adaptor.sprintasia.creditcard.command.TransactionType;
import com.feilong.framework.netpay.advance.adaptor.sprintasia.creditcard.util.CreditCardQueryResultPaser;
import com.feilong.framework.netpay.advance.command.QueryRequest;
import com.feilong.framework.netpay.advance.command.QueryResult;
import com.feilong.framework.netpay.advance.exception.TradeQueryException;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.creditcard.command.TransactionStatus;
import com.feilong.webservice.cxf.JaxWsDynamicClientUtil;

/**
 * The Class SprintAsiaCreditCardAdvanceAdaptor.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月9日 上午1:53:19
 * @since 1.0.6
 */
public class SprintAsiaCreditCardAdvanceAdaptor extends AbstractPaymentAdvanceAdaptor{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(SprintAsiaCreditCardAdvanceAdaptor.class);

	/**
	 * Required<br>
	 * Value: Merchant’s DOacquire ID (will be provided by your Account Manager)<br>
	 * Format: Up to 20 alphanumeric characters.
	 */
	private String				siteID;

	/** 查询网关提交地址. */
	private String				queryGateway;

	/** 查询ws operationName. */
	private String				queryOperationName;

	/** The query service version. */
	private String				queryServiceVersion;

	/**
	 * Gets the query result.<br>
	 * 他们还在使用 RPC/encoded is a vestige from before SOAP objects were defined with XML Schema.<br>
	 * JAX-WS doesn't support rpc/enc Web Services
	 *
	 * @param queryRequest
	 *            the query request
	 * @return the query result
	 * @throws TradeQueryException
	 *             the query exception
	 */
	public QueryResult getQueryResult(QueryRequest queryRequest) throws TradeQueryException{

		// @formatter:off

//
//		// Please make sure parameters are submitted in following order
//		Map<String, String> object = new HashMap<String, String>();
//
//		// Conditional (must present if transactionID is not)
//		// Value: Merchant’s unique Transaction ID
//		// Format: Up to 50 alphanumeric characters
//		object.put("merchantTransactionID", queryRequest.getTradeNo());
//
//		// Required
//		object.put("serviceVersion", queryServiceVersion);
//
//		// Required
//		// Value: Merchant’s DOacquire ID (same siteID you use for initial transaction)
//		// Format: Up to 20 alphanumeric characters
//		object.put("siteID", siteID);
//
//		// Conditional (must present if merchantTransactionID is not)
//		// Value: DOacquire’s transaction ID
//		// Format: Up to 50 alphanumeric characters
//		// object.put("transactionID", "");
//
//		// Required
//		// Value:
//		//  AUTHORIZATION
//		//  CAPTURE
//		//  VOID CAPTURE
//		//  SALES
//		//  VOID
//		//  REFUND
//		//  FORCE
//		object.put("transactionType", TransactionType.AUTHORIZATION);
//
//		NameValuePair[] nameValuePairs = {
//				new NameValuePair("merchantTransactionID", queryRequest.getTradeNo()),
//				new NameValuePair("serviceVersion", queryServiceVersion),
//				new NameValuePair("siteID", siteID),
//				new NameValuePair("transactionType", TransactionType.AUTHORIZATION), };

		// @formatter:on

		// @formatter:off

//		<wddxPacket version="1.0">
//		<header/>
//		<data>
//			<struct>
//				<var name="TRANSACTIONID">
//					<string/>
//				</var>
//				<var name="ACQUIRERRESPONSECODE">
//					<string/>
//				</var>
//				<var name="SCRUBMESSAGE">
//					<string>Transaction not found</string>
//				</var>
//				<var name="AMOUNT">
//					<string/>
//				</var>
//				<var name="SERVICEVERSION">
//					<string>2.0</string>
//				</var>
//				<var name="TRANSACTIONSCRUBCODE">
//					<string/>
//				</var>
//				<var name="MERCHANTTRANSACTIONID">
//					<string>010003420004</string>
//				</var>
//				<var name="CURRENCY">
//					<string/>
//				</var>
//				<var name="TRANSACTIONSTATUS">
//					<string/>
//				</var>
//				<var name="SITEID">
//					<string>Blanja2</string>
//				</var>
//				<var name="TRANSACTIONDATE">
//					<string/>
//				</var>
//				<var name="ACQUIRERCODE">
//					<string/>
//				</var>
//				<var name="SCRUBCODE">
//					<string>50008</string>
//				</var>
//				<var name="TRANSACTIONSCRUBMESSAGE">
//					<string/>
//				</var>
//				<var name="ACQUIRERAPPROVALCODE">
//					<string/>
//				</var>
//				<var name="TRANSACTIONTYPE">
//					<string>AUTHORIZATION</string>
//				</var>
//			</struct>
//		</data>
//	</wddxPacket>

	// @formatter:on

		// *************************************************************
		String merchantTransactionID = queryRequest.getTradeNo();
		String transactionID = "";

		try{

			String wddxPacketXML = JaxWsDynamicClientUtil.call(
							queryGateway,
							queryOperationName,
							merchantTransactionID,
							queryServiceVersion,
							siteID,
							transactionID,
							TransactionType.AUTHORIZATION);
			// ******************************************************************
			CreditCardQueryResultPaser creditCardQueryResultPaser = new CreditCardQueryResultPaser();
			CreditCardQueryResult creditCardQueryResult = creditCardQueryResultPaser.parseXML(wddxPacketXML);

			String transactionStatus = creditCardQueryResult.getTransactionStatus();

			// SCRUBMESSAGE Transaction not found
			if (Validator.isNullOrEmpty(transactionStatus)){
				String messagePattern = "creditCard transactionStatus is isNullOrEmpty,creditCardQueryResult:{},wddxPacketXML is:{}";
				String formatMessage = Slf4jUtil.formatMessage(messagePattern, JsonUtil.format(creditCardQueryResult), wddxPacketXML);
				throw new RuntimeException(formatMessage);
			}

			QueryResult queryResult = new QueryResult();
			PaymentResult paymentResult = toPaymentResult(transactionStatus);
			queryResult.setGatewayAmount(new BigDecimal(creditCardQueryResult.getAmount()));
			queryResult.setGatewayPaymentTime(DateUtil.string2Date(creditCardQueryResult.getTransactionDate(), DatePattern.commonWithTime));
			queryResult.setGatewayResult(wddxPacketXML);
			queryResult.setGatewayTradeNo(creditCardQueryResult.getTransactionID());
			queryResult.setPaymentResult(paymentResult);
			queryResult.setQueryResultCommand(creditCardQueryResult);
			queryResult.setTradeNo(merchantTransactionID);
			return queryResult;
		}catch (Exception e){
			log.error("queryRequest:" + JsonUtil.format(queryRequest), e);
			throw new TradeQueryException(e);
		}
	}

	/**
	 * To payment result.
	 * 
	 * @param transactionStatus
	 *            the transaction status
	 * @return the payment result
	 */
	private PaymentResult toPaymentResult(String transactionStatus){
		PaymentResult paymentResult;
		if (TransactionStatus.APPROVED.equals(transactionStatus)){
			paymentResult = PaymentResult.PAID;
		}else if (TransactionStatus.PENDING.equals(transactionStatus)){
			paymentResult = PaymentResult.PENDING;
		}else{
			// 其余视为 失败,可以重新支付
			paymentResult = PaymentResult.FAIL;
		}
		return paymentResult;
	}

	/**
	 * 设置 required<br>
	 * Value: Merchant’s DOacquire ID (will be provided by your Account Manager)<br>
	 * Format: Up to 20 alphanumeric characters.
	 * 
	 * @param siteID
	 *            the siteID to set
	 */
	public void setSiteID(String siteID){
		this.siteID = siteID;
	}

	/**
	 * 设置 the query service version.
	 * 
	 * @param queryServiceVersion
	 *            the queryServiceVersion to set
	 */
	public void setQueryServiceVersion(String queryServiceVersion){
		this.queryServiceVersion = queryServiceVersion;
	}

	/**
	 * 设置 查询网关提交地址.
	 * 
	 * @param queryGateway
	 *            the queryGateway to set
	 */
	public void setQueryGateway(String queryGateway){
		this.queryGateway = queryGateway;
	}

	/**
	 * 设置 查询ws operationName.
	 * 
	 * @param queryOperationName
	 *            the queryOperationName to set
	 */
	public void setQueryOperationName(String queryOperationName){
		this.queryOperationName = queryOperationName;
	}

}
