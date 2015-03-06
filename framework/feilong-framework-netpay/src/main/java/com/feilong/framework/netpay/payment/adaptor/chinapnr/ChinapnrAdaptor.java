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
package com.feilong.framework.netpay.payment.adaptor.chinapnr;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chinapnr.SecureLink;

import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.adaptor.AbstractPaymentAdaptor;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;

/**
 * 汇付天下支付方式 .
 */
public class ChinapnrAdaptor extends AbstractPaymentAdaptor{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ChinapnrAdaptor.class);

    /** The gateway. */
    private String              gateway;

    /** 汇付天下商户号. */
    private String              merId;

    /** 商户密钥文件，包含路径和文件名，由汇付分配 . */
    private String              merKeyFile;

    /**
     * 钱管家公钥文件，包含路径和文件名，由汇付提供<br>
     * 验证数据签名，钱管家将交易应答返回给商户网站后，商户需要使用钱 管家的公钥验证签名，以验证该数据是否被篡改过。.
     */
    private String              pgKeyFile;

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#getPaymentFormEntity(com.feilong.netpay.command.PayRequest, java.util.Map)
     */
    @Override
    public PaymentFormEntity getPaymentFormEntity(PayRequest payRequest,Map<String, String> specialSignMap){

        doCommonValidate(payRequest);

        String tradeNo = payRequest.getTradeNo();
        BigDecimal totalFee = payRequest.getTotalFee();
        String return_url = payRequest.getReturnUrl();
        String notify_url = payRequest.getNotifyUrl();

        if (Validator.isNullOrEmpty(return_url)){
            throw new IllegalArgumentException("return_url can't be null/empty!");
        }

        if (Validator.isNullOrEmpty(notify_url)){
            throw new IllegalArgumentException("notify_url can't be null/empty!");
        }

        // ********************************************************************
        // sing里的参数
        String Version = "10";
        String CmdId = "Buy";
        String MerId = merId;
        String OrdId = tradeNo;
        String OrdAmt = totalFee.setScale(2, BigDecimal.ROUND_HALF_UP) + "";
        String CurCode = "RMB";
        String Pid = "";
        String RetUrl = return_url;
        String BgRetUrl = notify_url;
        String MerPriv = "";
        String GateId = "";
        String UsrMp = "";
        String DivDetails = "";
        String PayUsrId = "";
        String MerData = Version + CmdId + MerId + OrdId + OrdAmt + CurCode + Pid + RetUrl + MerPriv + GateId + UsrMp + DivDetails
                        + PayUsrId + BgRetUrl;

        SecureLink secureLink = new SecureLink();
        int ret = secureLink.SignMsg(MerId, merKeyFile, MerData);

        boolean isSignOk = (ret == 0);
        if (!isSignOk){
            throw new IllegalArgumentException("[Chinapnr]签名错误 ret=" + ret);
        }
        String ChkValue = secureLink.getChkValue();

        // 传送至汇付天下的参数
        Map<String, String> hiddenParamsMap = new HashMap<String, String>();
        hiddenParamsMap.put("Version", Version);
        hiddenParamsMap.put("MerId", MerId);
        hiddenParamsMap.put("CmdId", CmdId);
        hiddenParamsMap.put("CurCode", CurCode);
        hiddenParamsMap.put("OrdId", OrdId);
        hiddenParamsMap.put("OrdAmt", OrdAmt);
        hiddenParamsMap.put("Pid", Pid);
        hiddenParamsMap.put("RetUrl", RetUrl);
        hiddenParamsMap.put("BgRetUrl", BgRetUrl);
        hiddenParamsMap.put("MerPriv", MerPriv);
        hiddenParamsMap.put("GateId", GateId);
        hiddenParamsMap.put("UsrMp", UsrMp);
        hiddenParamsMap.put("DivDetails", DivDetails);
        hiddenParamsMap.put("PayUsrId", PayUsrId);
        hiddenParamsMap.put("ChkValue", ChkValue);

        return getPaymentFormEntity(gateway, "get", hiddenParamsMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jumbo.brandstore.payment.PaymentAdaptor#getFeedbackSoCode(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getFeedbackTradeNo(HttpServletRequest request){
        return request.getParameter("OrdId");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jumbo.brandstore.payment.PaymentAdaptor#doGetFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getFeedbackTotalFee(HttpServletRequest request){
        return request.getParameter("OrdAmt");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.PaymentAdaptor#doRedirectVerify(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public PaymentResult verifyRedirect(HttpServletRequest request){
        return PaymentResult.PAID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jumbo.brandstore.payment.PaymentAdaptor#doNotifyVerify(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public PaymentResult verifyNotify(HttpServletRequest request){
        // 消息类型
        String CmdId = request.getParameter("CmdId");
        // 商户号
        String MerId = request.getParameter("MerId");
        // 应答返回码
        String RespCode = request.getParameter("RespCode");
        // 钱管家交易唯一标识
        String TrxId = request.getParameter("TrxId");
        // 金额
        String OrdAmt = request.getParameter("OrdAmt");
        // 币种
        String CurCode = request.getParameter("CurCode");
        // 商品编号
        String Pid = request.getParameter("Pid");
        // 订单号
        String OrdId = request.getParameter("OrdId");
        // 商户私有域
        String MerPriv = request.getParameter("MerPriv");
        // 返回类型
        String RetType = request.getParameter("RetType");
        // 分账明细
        String DivDetails = request.getParameter("DivDetails");
        // 银行ID
        String GateId = request.getParameter("GateId");

        // 签名信息 需要验证的签名
        String ChkValue = request.getParameter("ChkValue");

        // 验签
        try{
            // 参数顺序不能错
            String msgData = CmdId + MerId + RespCode + TrxId + OrdAmt + CurCode + Pid + OrdId + MerPriv + RetType + DivDetails + GateId;
            SecureLink secureLink = new SecureLink();
            int ret = secureLink.VeriSignMsg(pgKeyFile, msgData, ChkValue);
            if (ret != 0){
                log.error("[Chinapnr]同步步签名验证失败[" + msgData + "]");
            }else{
                if (RespCode.equals("000000")){
                    return PaymentResult.PAID;
                }
                log.error("[Chinapnr]同步交易失败");
                return PaymentResult.FAIL;
            }
        }catch (Exception e){
            log.error("[Chinapnr]同步签名验证异常");
        }
        return PaymentResult.FAIL;
    }

    /**
     * Sets the gateway.
     * 
     * @param gateway
     *            the gateway to set
     */
    public void setGateway(String gateway){
        this.gateway = gateway;
    }

    /**
     * Sets the 汇付天下商户号.
     * 
     * @param merId
     *            the merId to set
     */
    public void setMerId(String merId){
        this.merId = merId;
    }

    /**
     * Sets the 钱管家公钥文件，包含路径和文件名，由汇付提供<br>
     * 验证数据签名，钱管家将交易应答返回给商户网站后，商户需要使用钱 管家的公钥验证签名，以验证该数据是否被篡改过。.
     * 
     * @param pgKeyFile
     *            the pgKeyFile to set
     */
    public void setPgKeyFile(String pgKeyFile){
        this.pgKeyFile = pgKeyFile;
    }

    /**
     * Sets the 商户密钥文件，包含路径和文件名，由汇付分配 .
     * 
     * @param merKeyFile
     *            the merKeyFile to set
     */
    public void setMerKeyFile(String merKeyFile){
        this.merKeyFile = merKeyFile;
    }

}
