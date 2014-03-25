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
package com.feilong.netpay.adaptor.alipay.mobile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.security.oneway.MD5Util;
import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.adaptor.AbstractPaymentAdaptor;
import com.feilong.netpay.command.PayRequest;
import com.feilong.netpay.command.PaymentFormEntity;
import com.feilong.netpay.command.TradeRole;
import com.feilong.servlet.http.ParamUtil;

/**
 * 手机版alipay支付
 * 
 * @author 冯明雷
 * @time 2013-6-4 下午2:05:50
 */
public class AlipayPayMobileAdaptor extends AbstractPaymentAdaptor{

	private static final Logger	log	= LoggerFactory.getLogger(AlipayPayMobileAdaptor.class);

	/** 请求地址 */
	private String				gateway;

	/** 商品名称 */
	private String				subject;

	/** 商城支付宝账户 */
	private String				seller;

	/** 支付宝合作伙伴id */
	private String				partner;

	/** 创建交易接口名称 */
	private String				service_create;

	/** 授权接口名称 */
	private String				service_auth;

	/** 算法名称，商城只支持MD5 */
	private String				sec_id;

	/** 签名 */
	private String				key;

	/** 请求参数格式 */
	private String				format;

	/** 接口版本号 */
	private String				v;

	/**
	 * 参数编码字符集 不可为空 <br>
	 * 商户网站使用的 编码格式 如utf-8 ,gbk, gb2312.
	 */
	private String				_input_charset;

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#getPaymentFormEntity(com.feilong.netpay.command.PayRequest, java.util.Map)
	 */
	public PaymentFormEntity getPaymentFormEntity(PayRequest payRequest,Map<String, String> specialSignMap){
		String tradeNo = payRequest.getTradeNo();
		BigDecimal total_fee = payRequest.getTotalFee();
		String return_url = payRequest.getReturnUrl();
		String notify_url = payRequest.getNotifyUrl();
		if (doValidator(tradeNo, total_fee, return_url, notify_url)){
			PaymentFormEntity paymentFormEntity = doGetPaymentFormEntity(payRequest, return_url, notify_url, specialSignMap);
			return paymentFormEntity;
		}
		return null;
	}

	/**
	 * 验证参数
	 * 
	 * @param tradeNo
	 * @param total_fee
	 * @param return_url
	 * @param notify_url
	 */
	private boolean doValidator(String tradeNo,BigDecimal total_fee,String return_url,String notify_url){
		// ******************************************************************
		// validate
		if (Validator.isNullOrEmpty(tradeNo)){
			throw new IllegalArgumentException("code can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(total_fee)){
			throw new IllegalArgumentException("total_fee can't be null/empty!");
		}

		// 交易总额 单位为 RMB-Yuan 取值范围为[0.01， 100000000.00]
		// 精确到小数点 后两位
		BigDecimal minPay = new BigDecimal(0.01f);
		BigDecimal maxPay = new BigDecimal(100000000);
		if (total_fee.compareTo(minPay) == -1 || total_fee.compareTo(maxPay) == 1){
			throw new IllegalArgumentException("total_fee:" + total_fee + " can't < " + minPay + " or > " + maxPay);
		}

		if (Validator.isNullOrEmpty(return_url)){
			throw new IllegalArgumentException("return_url can't be null/empty!");
		}

		if (Validator.isNullOrEmpty(notify_url)){
			throw new IllegalArgumentException("notify_url can't be null/empty!");
		}
		return true;
	}

	protected PaymentFormEntity doGetPaymentFormEntity(
			PayRequest payRequest,
			String return_url,
			String notify_url,
			Map<String, String> specialSignMap){
		String tradeNo = payRequest.getTradeNo();
		BigDecimal total_fee = payRequest.getTotalFee();

		// 验证传入的参数(支付宝支付直接返回true，网银、信用卡支付主要验证银行code是否支持)
		boolean isPassValidatorSpecialSignMap = validatorSpecialSignMap(specialSignMap);
		if (isPassValidatorSpecialSignMap){
			String requestToken = "";
			try{
				requestToken = getRequestToken(tradeNo, total_fee, return_url, notify_url, specialSignMap);
			}catch (Exception e){
				e.printStackTrace();
			}
			if (Validator.isNotNullOrEmpty(requestToken)){
				Map<String, String> map = prepareAuthParamsMap(requestToken);
				String toBeSignedString = ParamUtil.getToBeSignedString(map);
				String authSign = MD5Util.encode(toBeSignedString + key, _input_charset);
				map.put("sign", authSign);

				String method = "get";
				return getPaymentFormEntity(gateway, method, map);
			}
		}
		return null;
	}

	@Override
	public boolean doNotifyVerify(HttpServletRequest request){
		if (Validator.isNullOrEmpty(key)){
			throw new NullPointerException("the key is null or empty!");
		}
		boolean isNotifySignOk = isNotifySignOk(request);
		log.info("------------notify isNotifySignOk:" + isNotifySignOk);
		if (isNotifySignOk){
			boolean isPaymentSuccess = isPaymentSuccess(request);
			// 付款成功
			return isPaymentSuccess;
		}else{
			log.error("isNotifySignOk error");
			return false;
		}
	}

	@Override
	public String doGetFeedbackTradeNo(HttpServletRequest request){
		String soCode = null;
		soCode = request.getParameter("out_trade_no");
		if (Validator.isNotNullOrEmpty(soCode)){
			return soCode;
		}else{
			String notify_data = request.getParameter("notify_data");
			log.info("notify--------" + notify_data);
			if (Validator.isNotNullOrEmpty(notify_data)){
				try{
					soCode = getValueByKeyForXML(notify_data, "out_trade_no");
				}catch (DocumentException e){
					e.printStackTrace();
				}
			}
			return soCode;
		}
	}

	@Override
	public String doGetFeedbackTotalFee(HttpServletRequest request){
		return null;
	}

	@Override
	public boolean doCloseTrade(String orderNo,TradeRole tradeRole){
		return false;
	}

	@Override
	public boolean isSupportCloseTrade(){
		return false;
	}

	/**
	 * 验证输入的参数(子类可以按照需要 重写).
	 * 
	 * @param specialSignMap
	 *            指定的签名map
	 * @return true, if successful
	 */
	protected boolean validatorSpecialSignMap(Map<String, String> specialSignMap){
		return true;
	}

	/**
	 * 生成创建交易请求的url，并发送请求获得返回结果
	 * 
	 * @param code
	 * @param total_fee
	 * @param return_url
	 * @param notify_url
	 * @throws Exception
	 * @return String
	 * @author 冯明雷
	 * @time 2013-6-7上午11:17:51
	 */
	private String getRequestToken(String code,BigDecimal total_fee,String return_url,String notify_url,Map<String, String> specialSignMap)
			throws Exception{
		Map<String, String> hiddenParamMap = prepareTradeRequestParamsMap(code, total_fee, return_url, notify_url, specialSignMap);
		String toBeSignedString = ParamUtil.getToBeSignedString(hiddenParamMap);
		String sign = MD5Util.encode(toBeSignedString + key, _input_charset);
		hiddenParamMap.put("sign", sign);
		String url = URIUtil.getEncodedUrl(gateway, hiddenParamMap, _input_charset);
		String createResult = getCreateTradeResult(url);
		String requestToken = parseAlipayResult(createResult);
		return requestToken;
	}

	/**
	 * 准备alipay.wap.trade.create.direct服务的参数
	 * 
	 * @param request
	 * @return 请求参数map
	 */
	private Map<String, String> prepareTradeRequestParamsMap(
			String code,
			BigDecimal total_fee,
			String return_url,
			String notify_url,
			Map<String, String> specialSignMap){
		Map<String, String> hiddenParamMap = new HashMap<String, String>();
		StringBuffer regData = new StringBuffer();
		regData.append("<direct_trade_create_req>");
		regData.append("<subject>" + subject + "</subject>");
		regData.append("<out_trade_no>" + code + "</out_trade_no>");
		regData.append("<total_fee>" + total_fee + "</total_fee>");
		regData.append("<seller_account_name>" + seller + "</seller_account_name>");
		regData.append("<call_back_url>" + return_url + "</call_back_url>");
		regData.append("<notify_url>" + notify_url + "</notify_url>");
		String bankCode = specialSignMap.get("defaultbank");
		if (Validator.isNotNullOrEmpty(bankCode)){
			regData.append("<cashier_code>" + bankCode + "</cashier_code>");
		}
		// regData.append("<pay_expire>" + pay_expire + "</pay_expire>");
		regData.append("</direct_trade_create_req>");
		hiddenParamMap.put("req_data", regData.toString());
		hiddenParamMap.put("service", service_create);
		String req_id = System.currentTimeMillis() + "";
		hiddenParamMap.put("req_id", req_id);
		hiddenParamMap.putAll(prepareCommonParams());
		return hiddenParamMap;
	}

	/**
	 * 准备alipay.wap.auth.authAndExecute服务的参数
	 * 
	 * @param request
	 * @param requestToken
	 * @return 返回授权接口参数map
	 */
	private Map<String, String> prepareAuthParamsMap(String requestToken){
		Map<String, String> requestParams = new HashMap<String, String>();
		StringBuilder reqData = new StringBuilder();
		requestParams.put("service", service_auth);
		reqData.append("<auth_and_execute_req><request_token>" + requestToken + "</request_token></auth_and_execute_req>");
		requestParams.put("req_data", reqData.toString());
		requestParams.putAll(prepareCommonParams());
		return requestParams;
	}

	/**
	 * 准备通用参数
	 * 
	 * @return 通用参数map
	 */
	private Map<String, String> prepareCommonParams(){
		Map<String, String> commonParams = new HashMap<String, String>();
		commonParams.put("sec_id", sec_id);
		commonParams.put("partner", partner);
		commonParams.put("format", format);
		commonParams.put("v", v);
		return commonParams;
	}

	/**
	 * 获得发送创建交易的返回结果
	 * 
	 * @param reqUrl
	 * @param params
	 * @throws Exception
	 * @return String
	 * @author 冯明雷
	 * @time 2013-6-6下午5:12:38
	 */
	private String getCreateTradeResult(String reqUrl) throws Exception{
		String result = "";
		String invokeUrl = URIUtil.getBeforePath(reqUrl) + "?";
		URL serverUrl = new URL(invokeUrl);
		HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.connect();
		String params = reqUrl.replace(invokeUrl, "");
		conn.getOutputStream().write(params.getBytes());
		InputStream is = conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null){
			buffer.append(line);
		}
		result = URLDecoder.decode(buffer.toString(), _input_charset);
		conn.disconnect();
		return result;
	}

	/**
	 * 解析支付宝返回的结果
	 * 
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private String parseAlipayResult(String return_result) throws Exception{
		HashMap<String, String> resMap = new HashMap<String, String>();

		Map<String, String> returnParamMap = URIUtil.parseQueryToMap(return_result);
		String returnV = returnParamMap.get("v");
		String returnService = returnParamMap.get("service");
		String returnPartner = returnParamMap.get("partner");
		String reutrnSign = returnParamMap.get("sign");
		String returnReqId = returnParamMap.get("req_id");
		String returnSecId = returnParamMap.get("sec_id");
		resMap.put("v", returnV);
		resMap.put("service", returnService);
		resMap.put("partner", returnPartner);
		resMap.put("sec_id", returnSecId);
		resMap.put("req_id", returnReqId);
		String businessResult = "";
		if (return_result.contains("<err>")){
			String error = returnParamMap.get("res_error");
			log.error("创建支付宝交易失败：" + error);
			throw new Exception("创建支付宝交易失败：" + error);
		}else{
			String res_data = returnParamMap.get("res_data");
			resMap.put("res_data", res_data);
			// 验证签名数据
			String verifyData = ParamUtil.getToBeSignedString(resMap);
			String sign = MD5Util.encode(verifyData + key, _input_charset);
			if (sign.equals(reutrnSign)){
				try{
					businessResult = getValueByKeyForXML(res_data, "request_token");
				}catch (DocumentException e){
					e.printStackTrace();
				}
			}else{
				log.error("MD5验证签名失败");
				throw new Exception("MD5验证签名失败");
			}
		}
		return businessResult;
	}

	/**
	 * 校验 返回的请求 <br>
	 * 
	 * @param request
	 *            the request
	 * @return true, if is notify sign ok
	 */
	private boolean isNotifySignOk(HttpServletRequest request){
		boolean isSignOk = false;

		String notify_data = request.getParameter("notify_data");
		if (Validator.isNotNullOrEmpty(notify_data)){
			isSignOk = isNotifySignOkForNotifyUrl(request);
		}else{
			isSignOk = isNotifySignOkForCallBack(request);
		}
		return isSignOk;
	}

	/**
	 * 判断是否交易成功
	 * 
	 * @param request
	 * @return boolean
	 * @author 冯明雷
	 * @time 2013-6-10下午2:22:42
	 */
	private boolean isPaymentSuccess(HttpServletRequest request){
		boolean isSuccess = false;
		String result = request.getParameter("result");
		log.info("------------notify result:" + result);
		if (Validator.isNotNullOrEmpty(result)){
			isSuccess = "success".equals(result);
			log.info("------------return url result:" + result);
		}else{
			String notify_data = request.getParameter("notify_data");
			log.info("------------notify notify_data:" + notify_data);
			if (Validator.isNotNullOrEmpty(notify_data)){
				try{
					String status = getValueByKeyForXML(notify_data, "trade_status");
					log.info("------------notify status:" + status);
					isSuccess = "TRADE_FINISHED".equals(status) || "TRADE_SUCCESS".equals(status);
				}catch (DocumentException e){
					e.printStackTrace();
				}
			}
		}
		return isSuccess;
	}

	/**
	 * 解析随浏览器跳转发回的消息参数
	 * 
	 * @param request
	 * @return boolean
	 * @author 冯明雷
	 * @time 2013-9-16上午10:22:17
	 */
	private boolean isNotifySignOkForCallBack(HttpServletRequest request){
		// alipay 传过来的参数
		String alipaySign = request.getParameter("sign");
		if (Validator.isNullOrEmpty(alipaySign)){
			throw new IllegalArgumentException("alipaySign can't be null/empty!");
		}else{
			// alipay 支付接口 里面所有的参数 都是单值的
			@SuppressWarnings("unchecked")
			Enumeration<String> parameterNames = request.getParameterNames();
			Map<String, String> params = new HashMap<String, String>();
			while (parameterNames.hasMoreElements()){
				String key = parameterNames.nextElement();
				// 把参数里面的 sign 和 sign_type 去掉
				if (key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")){
					continue;
				}else{
					String value = request.getParameter(key);
					params.put(key, value);
				}
			}
			String toBeSignedString = ParamUtil.getToBeSignedString(params);

			String mysign = MD5Util.encode(toBeSignedString + key, _input_charset);
			boolean isSignOk = mysign.equals(alipaySign);
			return isSignOk;
		}
	}

	/**
	 * 解析alipay推送的消息参数
	 * 
	 * @param request
	 * @return boolean
	 * @author 冯明雷
	 * @time 2013-9-16上午10:23:19
	 */
	private boolean isNotifySignOkForNotifyUrl(HttpServletRequest request){
		// alipay 传过来的参数
		String alipaySign = request.getParameter("sign");
		log.info("------------notify alipaySign:" + alipaySign);
		String service = request.getParameter("service");
		log.info("------------notify service:" + service);
		String v = request.getParameter("v");
		log.info("------------notify v:" + v);
		String sec_id = request.getParameter("sec_id");
		log.info("------------notify sec_id:" + sec_id);
		String notify_data = request.getParameter("notify_data");
		log.info("------------notify notify_data:" + notify_data);
		String verifyData = "service=" + service + "&v=" + v + "&sec_id=" + sec_id + "&notify_data=" + notify_data;
		log.info("------------notify verifyData:" + verifyData);
		String mysign = MD5Util.encode(verifyData + key, _input_charset);
		boolean isSignOk = mysign.equals(alipaySign);
		return isSignOk;

	}

	/**
	 * 根据key获得xml类型字符串中对应的节点的值
	 * 
	 * @param xmlData
	 * @param key
	 * @throws DocumentException
	 * @return String
	 * @author 冯明雷
	 * @time 2013-9-16上午10:16:39
	 */
	private String getValueByKeyForXML(String xmlData,String key) throws DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new InputSource(new StringReader(xmlData)));
		Element root = document.getRootElement();
		String value = root.elementText(key);
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.adaptor.PaymentAdaptor#doRedirectVerify(javax.servlet.http.HttpServletRequest)
	 */
	public boolean doRedirectVerify(HttpServletRequest request){
		return true;
	}

	public String getGateway(){
		return gateway;
	}

	public void setGateway(String gateway){
		this.gateway = gateway;
	}

	public String getSubject(){
		return subject;
	}

	public void setSubject(String subject){
		this.subject = subject;
	}

	public String getSeller(){
		return seller;
	}

	public void setSeller(String seller){
		this.seller = seller;
	}

	public String getPartner(){
		return partner;
	}

	public void setPartner(String partner){
		this.partner = partner;
	}

	public String getService_create(){
		return service_create;
	}

	public void setService_create(String service_create){
		this.service_create = service_create;
	}

	public String getService_auth(){
		return service_auth;
	}

	public void setService_auth(String service_auth){
		this.service_auth = service_auth;
	}

	public String getSec_id(){
		return sec_id;
	}

	public void setSec_id(String sec_id){
		this.sec_id = sec_id;
	}

	public String getKey(){
		return key;
	}

	public void setKey(String key){
		this.key = key;
	}

	public String getFormat(){
		return format;
	}

	public void setFormat(String format){
		this.format = format;
	}

	public String getV(){
		return v;
	}

	public void setV(String v){
		this.v = v;
	}

	public String get_input_charset(){
		return _input_charset;
	}

	public void set_input_charset(String _input_charset){
		this._input_charset = _input_charset;
	}

}
