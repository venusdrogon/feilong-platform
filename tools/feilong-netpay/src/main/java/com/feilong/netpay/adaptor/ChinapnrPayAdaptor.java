package com.feilong.netpay.adaptor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chinapnr.SecureLink;

import com.feilong.netpay.command.PaymentFormEntity;
import com.feilong.netpay.command.TradeRole;

/**
 * 汇付天下支付方式 .
 */
public class ChinapnrPayAdaptor extends AbstractPaymentAdaptor{

	private static final Logger	log	= LoggerFactory.getLogger(ChinapnrPayAdaptor.class);

	/** The gateway. */
	private String				gateway;

	/** 汇付天下商户号. */
	private String				merId;

	/** 商户密钥文件，包含路径和文件名，由汇付分配 . */
	private String				merKeyFile;

	/**
	 * 钱管家公钥文件，包含路径和文件名，由汇付提供<br>
	 * 验证数据签名，钱管家将交易应答返回给商户网站后，商户需要使用钱 管家的公钥验证签名，以验证该数据是否被篡改过。.
	 */
	private String				pgKeyFile;

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.AbstractPaymentAdaptor#doGetPaymentFormEntity(java.lang.String, java.math.BigDecimal,
	 * java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public PaymentFormEntity doGetPaymentFormEntity(
			String code,
			BigDecimal total_fee,
			String return_url,
			String notify_url,
			Map<String, String> specialSignMap){
		// sing里的参数
		String Version = "10";
		String CmdId = "Buy";
		String MerId = merId;
		String OrdId = code;
		String OrdAmt = total_fee.setScale(2, BigDecimal.ROUND_HALF_UP) + "";
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
		}else{
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

			PaymentFormEntity paymentFormEntity = new PaymentFormEntity();
			paymentFormEntity.setAction(gateway);
			paymentFormEntity.setMethod("get");
			paymentFormEntity.setHiddenParamMap(hiddenParamsMap);

			return paymentFormEntity;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#getFeedbackSoCode(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String doGetFeedbackSoCode(HttpServletRequest request){
		return request.getParameter("OrdId");
	}

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#doGetFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
	 */
	public String doGetFeedbackTotalFee(HttpServletRequest request){
		return request.getParameter("OrdAmt");
	}

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#doNotifyVerify(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean doNotifyVerify(HttpServletRequest request){
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
					return true;
				}else{
					log.error("[Chinapnr]同步交易失败");
					return false;
				}
			}
		}catch (Exception e){
			log.error("[Chinapnr]同步签名验证异常");
		}
		return false;
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

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#closeTrade(java.lang.String, com.jumbo.brandstore.payment.TradeRole)
	 */
	public boolean doCloseTrade(String orderNo,TradeRole tradeRole){
		throw new UnsupportedOperationException("汇付天下暂时不支持关闭交易");
	}

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#isSupportCloseTrade()
	 */
	public boolean isSupportCloseTrade(){
		return false;
	}
}
