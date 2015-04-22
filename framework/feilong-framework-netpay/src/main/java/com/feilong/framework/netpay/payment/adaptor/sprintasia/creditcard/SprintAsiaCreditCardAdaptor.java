/*
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
package com.feilong.framework.netpay.payment.adaptor.sprintasia.creditcard;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.util.NumberUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.adaptor.AbstractPaymentAdaptor;
import com.feilong.framework.netpay.payment.adaptor.sprintasia.creditcard.command.TransactionStatus;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;
import com.feilong.servlet.http.RequestUtil;

/**
 * BcaCreditCard.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 2, 2014 9:15:37 PM
 */
public class SprintAsiaCreditCardAdaptor extends AbstractPaymentAdaptor{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(SprintAsiaCreditCardAdaptor.class);

    /** 表单提交地址. */
    private String              gateway;

    /** The method. */
    private String              method;

    /**
     * Required<br>
     * Value: Merchant’s DOacquire ID (will be provided by your Account Manager)<br>
     * Format: Up to 20 alphanumeric characters.
     */
    private String              siteID;

    /** The service version. */
    private String              serviceVersion;

    /**
     * Required<br>
     * Value: Transaction currency<br>
     * Format: 3 characters (ISO 4217 alpha-3 format. Example: USD, MYR, IDR)<br>
     */
    private String              currencyDefault;

    /** The price pattern. */
    private String              pricePattern;

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#getPaymentFormEntity(com.feilong.netpay.command.PayRequest, java.util.Map)
     */
    @Override
    public PaymentFormEntity getCustomizePaymentFormEntity(PayRequest payRequest,Map<String, String> specialSignMap){

        String transactionNo = payRequest.getTradeNo();
        String totalAmount = NumberUtil.toString(payRequest.getTotalFee(), pricePattern);

        // ******************************************************************************
        Map<String, String> map = new HashMap<String, String>();

        // Required
        // Value: Merchant’s DOacquire ID (will be provided by your Account Manager)
        // Format: Up to 20 alphanumeric characters
        map.put("siteID", siteID);

        // Required
        // Value: 1.0
        map.put("serviceVersion", serviceVersion);

        // Required
        // Value: Merchant’s unique Transaction ID
        // Format: Up to 50 alphanumeric characters
        map.put("merchantTransactionID", transactionNo);

        // Required
        // Value: Transaction currency
        // Format: 3 characters (ISO 4217 alpha-3 format. Example: USD, MYR, IDR)
        map.put("currency", currencyDefault);

        // Required
        // Value: Transaction amount
        // Format: Up to 10 numeric characters
        map.put("amount", totalAmount);
        // ******************************Optional*********************************************************************

        // Optional
        // Value: Merchant’s invoice data which will be display to Cardholder
        // Format: Up to 250 alphanumeric characters
        // map.put("merchantTransactionNote", merchantTransactionNote);

        // Optional
        // Value: Additional information which may be required by partner bank/acquirer
        // Format: Up to 250 alphanumeric characters
        // map.put("userDefineValue", userDefineValue);

        // Optional
        // Value: Cardholder billing name (as printed on card) Format: Up to 50 alphanumeric characters
        // map.put("billingName", billingName);

        // Optional
        // Value: Cardholder billing address
        // Format: Up to 250 alphanumeric characters
        // map.put("billingAddress", billingAddress);

        // Optional
        // Value: Cardholder billing city
        // Format: Up to 50 alphabetical characters
        // map.put("billingCity", billingCity);

        // Optional
        // Value: Cardholder billing state
        // Format: Up to 50 alphabetical characters
        // map.put("billingState", billingState);

        // Optional
        // Value: Cardholder billing postal code/zip Format: Up to 10 alphanumeric characters
        // map.put("billingPostalCode", billingPostalCode);

        // Optional
        // Value: Cardholder billing country
        // Format: 2 characters (ISO 3166-1-alpha-2 format. Example: US, MY,ID)
        // map.put("billingCountry", billingCountry);

        // Optional
        // Value: Cardholder contact phone number Format: Up to 20 alphanumeric characters
        // map.put("billingPhone", billingPhone);

        // Optional
        // Value: Cardholder contact email address Format: Up to 50 alphanumeric characters
        // map.put("billingEmail", billingEmail);

        // Optional
        // Value: Goods recipient name
        // Format: Up to 50 alphanumeric characters
        // map.put("deliveryName", deliveryName);

        // Optional
        // Value: Goods recipient address
        // Format: Up to 250 alphanumeric characters
        // map.put("deliveryAddress", deliveryAddress);

        // Optional
        // Value: Goods recipient city
        // Format: Up to 50 alphanumeric characters
        // map.put("deliveryCity", deliveryCity);

        // Optional
        // Value: Goods recipient state
        // Format: Up to 50 alphanumeric characters
        // map.put("deliveryState", deliveryState);

        // Optional
        // Value: Goods recipient postal code/zip
        // Format: Up to 10 alphanumeric characters
        // map.put("deliveryPostalCode", deliveryPostalCode);

        // Optional
        // Value: Goods recipient country
        // Format: 2 characters (ISO 3166-1-alpha-2 format. Example: US, MY,ID)
        // map.put("deliveryCountry", deliveryCountry);

        // *************************************************************************************************
        // 需要被签名的 参数map

        // 特殊 传入
        if (Validator.isNotNullOrEmpty(specialSignMap)){
            map.putAll(specialSignMap);
        }

        return getPaymentFormEntity(gateway, method, map);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#doRedirectVerify(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public PaymentResult verifyRedirect(HttpServletRequest request){
        // the same as Notify
        return verifyNotify(request);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.PaymentAdaptor#doNotifyVerify(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public PaymentResult verifyNotify(HttpServletRequest request){
        // Value: Merchant’s DOacquire ID
        // Format: Up to 20 alphanumeric characters
        @SuppressWarnings({ "unused", "hiding" })
        String siteID = request.getParameter("siteID");

        // Value: 1.0
        @SuppressWarnings({ "unused", "hiding" })
        String serviceVersion = request.getParameter("serviceVersion");

        // Value: Merchant’s unique Transaction ID
        // Format: Up to 50 alphanumeric characters
        @SuppressWarnings("unused")
        String merchantTransactionID = request.getParameter("merchantTransactionID");

        // Value: Transaction currency
        // Format: 3 characters (ISO 4217 alpha-3 format. Example: USD, MYR,
        // IDR)
        @SuppressWarnings("unused")
        String currency = request.getParameter("currency");

        // Value: Transaction amount
        // Format: Up to 10 numeric characters
        String amount = request.getParameter("amount");
        if (Validator.isNullOrEmpty(amount)){
            throw new IllegalArgumentException("amount can't be null/empty!");
        }

        // Value: DOacquire transaction ID
        // Format: Up to 50 alphanumeric characters
        String transactionID = request.getParameter("transactionID");
        if (Validator.isNullOrEmpty(transactionID)){
            throw new IllegalArgumentException("transactionID can't be null/empty!");
        }

        // Value: AUTHORIZATION
        @SuppressWarnings("unused")
        String transactionType = request.getParameter("transactionType");

        // Value: DOacquire transaction status
        // Format:
        //  PENDING (transaction still in process)
        //  APPROVE (transaction has been authorized by the partner bank/acquirer)
        //  DECLINE (transaction has been rejected by the partner bank/acquirer)
        //  SCRUB (transaction has been rejected based on account risk policy)
        //  ERROR (network connectivity error with the partner bank/acquirer)
        //  CANCEL (cardholder did not complete the transaction)

        // Jim, list of revised statuses should be as follows:
        // - APPROVED instead of APPROVE
        // - DECLINED instead of DECLINE
        // - SCRUBBED instead of SCRUBB
        String transactionStatus = request.getParameter("transactionStatus");

        // Value: Card number
        // Format: First 6 and Last 4 of Cardholder Card Number (411111**1111)
        @SuppressWarnings("unused")
        String cardNo = request.getParameter("cardNo");

        // Value: Partner bank/acquirer selected to authorize this transaction
        // Format: Up to 10 alphanumeric characters
        @SuppressWarnings("unused")
        String acquirerCode = request.getParameter("acquirerCode");

        // Value: Merchant account provided by partner bank/acquirer
        // Format: Up to 50 alphanumeric characters
        @SuppressWarnings("unused")
        String acquirerMerchantAccount = request.getParameter("acquirerMerchantAccount");

        // Value: Response code received from the partner bank/acquirer
        // Format: Up to 10 alphanumeric characters
        @SuppressWarnings("unused")
        String acquirerResponseCode = request.getParameter("acquirerResponseCode");

        // Value: Approval code given by the partner bank/acquirer
        // Format: Up to 10 alphanumeric characters
        @SuppressWarnings("unused")
        String acquirerApprovalCode = request.getParameter("acquirerApprovalCode");

        // Value: System generated code by DOacquire when transaction status is
        // SCRUB
        // Format: Up to 10 alphanumeric characters
        @SuppressWarnings("unused")
        String scrubCode = request.getParameter("scrubCode");

        // Value: System generated message by DOacquire when transaction
        // status is SCRUB
        // Format: Up to 250 alphanumeric characters
        @SuppressWarnings("unused")
        String scrubMessage = request.getParameter("scrubMessage");

        // PENDING 表示BCA网关拿不到银行支付情况, 他们会再次主动发送请求去询问, 然后邮件形式通知
        // 我们使用查询接口,去查询 也会触发 BCA 去查询去查询

        // PENDING 大约有1%的可能
        // 他们文档里面写的是 APPROVE,但是传递是参数值是 APPROVED

        if (TransactionStatus.APPROVED.equals(transactionStatus)){
            return PaymentResult.PAID;
        }else if (TransactionStatus.PENDING.equals(transactionStatus)){
            return PaymentResult.PENDING;
        }else{
            Object[] logArgs = { transactionStatus, RequestUtil.getRequestFullURL(request, CharsetType.UTF8) };
            log.error("transactionStatus is:[{}], full request url is :{}", logArgs);
            return PaymentResult.FAIL;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackSoCode(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getFeedbackTradeNo(HttpServletRequest request){
        return request.getParameter("merchantTransactionID");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.PaymentAdaptor#doGetFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getFeedbackTotalFee(HttpServletRequest request){
        return request.getParameter("amount");
    }

    /**
     * Sets the 表单提交地址.
     * 
     * @param gateway
     *            the gateway to set
     */
    public void setGateway(String gateway){
        this.gateway = gateway;
    }

    /**
     * Sets the method.
     * 
     * @param method
     *            the method to set
     */
    public void setMethod(String method){
        this.method = method;
    }

    /**
     * Sets the required<br>
     * Value: Transaction currency<br>
     * Format: 3 characters (ISO 4217 alpha-3 format.
     * 
     * @param currencyDefault
     *            the currencyDefault to set
     */
    public void setCurrencyDefault(String currencyDefault){
        this.currencyDefault = currencyDefault;
    }

    /**
     * Sets the required<br>
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
     * Sets the service version.
     * 
     * @param serviceVersion
     *            the serviceVersion to set
     */
    public void setServiceVersion(String serviceVersion){
        this.serviceVersion = serviceVersion;
    }

    /**
     * 设置 the price pattern.
     * 
     * @param pricePattern
     *            the pricePattern to set
     */
    public void setPricePattern(String pricePattern){
        this.pricePattern = pricePattern;
    }

}
