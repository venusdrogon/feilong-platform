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
package com.feilong.framework.netpay.payment.adaptor.alipay.pconline;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.netpay.advance.command.TradeRole;
import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.adaptor.AbstractPaymentAdaptor;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.tools.security.oneway.MD5Util;

/**
 * alipay境外支付接口.
 *
 * @author 冯明雷
 * @version 1.0
 * @time 2014-9-28 下午3:00:33
 */
public class AlipayForexPayAdaptor extends AbstractPaymentAdaptor{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(AlipayForexPayAdaptor.class);

	// ************************不属于签名用的 属性/字段**********************************************************************
	/**
	 * MD5 的私钥是以英文字母和数字组成的 32位字符串。<br>
	 * 商户可登录到商户服务中心（https://b.alipay.com），安装数字证书，在“技术服务”栏目中点击“交易安全校验码”，即可查看。<br>
	 * 此处的key 千万不要暴露给别人知道
	 */
	private String				key;

	/**
	 * 表单提交地址 <br>
	 * 支付宝网关.
	 */
	private String				gateway;

	// *************************签名用的必须配置的参数**********************************************************************
	/**
	 * 合作者身份id 不可为空 <br>
	 * 签约的 支付宝账号对应的 支付宝唯一用户名.
	 */
	private String				partner;

	/**
	 * 参数编码字符集 不可为空 <br>
	 * 商户网站使用的 编码格式 如utf-8 ,gbk, gb2312.
	 */
	private String				_input_charset;

	/** 验证通知的 service. */
	private String				service;

	/** 加密方式. */
	private String				sign_type;

	/** 货币符号. */
	private String				currency;

	/** 验证交易接口. */
	private String				service_notify_verify;

	/**
	 * Do get payment form entity.
	 *
	 * @param code
	 *            the code
	 * @param total_fee
	 *            the total_fee
	 * @param return_url
	 *            the return_url
	 * @param notify_url
	 *            the notify_url
	 * @param specialSignMap
	 *            the special sign map
	 * @return the payment form entity
	 */
	protected PaymentFormEntity doGetPaymentFormEntity(
					String code,
					BigDecimal total_fee,
					String return_url,
					String notify_url,
					Map<String, String> specialSignMap){

		boolean isPassValidatorSpecialSignMap = validatorSpecialSignMap(specialSignMap);

		if (isPassValidatorSpecialSignMap){

			// *************************************************************************************************
			// 需要被签名的 参数map
			Map<String, String> signParamsMap = new HashMap<String, String>();

			String body = "NIKESTORE.COM.HK PRODUCTS";
			String subject = "NIKESTORE.COM.HK PRODUCTS";
			signParamsMap.put("body", body);
			signParamsMap.put("currency", currency);
			signParamsMap.put("partner", partner);
			signParamsMap.put("service", service);
			signParamsMap.put("subject", subject);
			signParamsMap.put("out_trade_no", code);
			signParamsMap.put("return_url", return_url);
			signParamsMap.put("notify_url", notify_url);
			signParamsMap.put("timeout_rule", "12h");
			signParamsMap.put("total_fee", total_fee.setScale(2, BigDecimal.ROUND_HALF_UP) + "");

			// *************************************************************************************
			// 待签名字符串
			// 除去sign、sign_type 两个参数外，其他需要使用到的参数皆是要签名的参数
			String toBeSignedString = getToBeSignedString(signParamsMap);

			// 在 MD5 签名时，需要私钥参与签名。
			// 需要把私钥直接拼接到待签名字符串后面，形成新的字符串，利用MD5 的签名函数对这个新的字符串进行签名运算
			String sign = MD5Util.encode(toBeSignedString + key, _input_charset);

			// *************************************************************************************
			Map<String, String> hiddenParamsMap = new LinkedHashMap<String, String>();
			hiddenParamsMap.putAll(signParamsMap);
			hiddenParamsMap.put("sign", sign);
			// #签名方式
			// #DSA,RSA,MD5三值可选
			// #必须大写
			// #宝尊 暂时只支持MD5
			hiddenParamsMap.put("sign_type", sign_type);

			PaymentFormEntity paymentFormEntity = new PaymentFormEntity();
			paymentFormEntity.setAction(gateway);
			paymentFormEntity.setMethod("get");
			paymentFormEntity.setHiddenParamMap(hiddenParamsMap);
			return paymentFormEntity;
		}
		throw new IllegalArgumentException("specialSignMap has IllegalArgument key");
	}

	/**
	 * 验证输入的参数(子类可以按照需要 重写).
	 * 
	 * @param specialSignMap
	 *            指定的签名map
	 * @return true, if successful
	 */
	protected boolean validatorSpecialSignMap(@SuppressWarnings("unused") Map<String, String> specialSignMap){
		return true;
	}

	/**
	 * Do notify verify.
	 *
	 * @param request
	 *            the request
	 * @return true, if do notify verify
	 */
	public boolean doNotifyVerify(HttpServletRequest request){
		if (Validator.isNullOrEmpty(key)){
			throw new NullPointerException("the key is null or empty!");
		}
		Map<String, String[]> parameterMap = RequestUtil.getParameterMap(request);
		log.info(JsonUtil.format(parameterMap));

		boolean isNotifySignOk = isNotifySignOk(request);

		log.info("alipay doNotifyVerify return:[{}]" + isNotifySignOk);

		if (isNotifySignOk){
			// 获取支付宝返回数据之一的通知校验ID（notify_id），按照支付宝要求的格式拼接成要请求的链接
			// 示例https://mapi.alipay.com/gateway.do?service=notify_verify&partner=2088002396712354&notify_id=RqPnCoPT3K9%252Fvwbh3I%252BFioE227%252BPfNMl8jwyZqMIiXQWxhOCmQ5MQO%252FWd93rvCB%252BaiGg

			// 通过访问这个请求链接，利用编程方法来模拟http请求与支付宝服务器进行交互，
			// 获得支付宝服务器上处理的结果。 如果获得的信息是true，则校验成功；如果获得的信息是其他，则校验失败。
			String notify_id = request.getParameter("notify_id");

			StringBuilder sb = new StringBuilder();
			sb.append(gateway);
			sb.append("?");
			sb.append("service=" + service_notify_verify);
			sb.append("&partner=" + partner);
			sb.append("&notify_id=" + notify_id);
			String alipayNotifyURL = sb.toString();

			boolean isNotifyVerifySuccess = isNotifyVerifySuccess(alipayNotifyURL);
			log.info("alipay isNotifyVerifySuccess return:[{}]" + isNotifyVerifySuccess);

			// 付款成功
			return isNotifyVerifySuccess;
		}else{
			log.error("isNotifySignOk error");
			return false;
		}
	}

	/**
	 * 校验 返回的请求 <br>
	 * 还有没有必要再次 sign 来确认了.
	 *
	 * @param request
	 *            the request
	 * @return true, if is notify sign ok
	 */
	private boolean isNotifySignOk(HttpServletRequest request){
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
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(value);

					params.put(key, stringBuilder.toString());
				}
			}
			String toBeSignedString = getToBeSignedString(params);
			String mysign = MD5Util.encode(toBeSignedString + key, _input_charset);
			boolean isSignOk = mysign.equals(alipaySign);
			return isSignOk;
		}
	}

	/**
	 * 生成待签名的字符串 <br>
	 * 对数组里的每一个值从 a 到 z 的顺序排序，若遇到相同首字母，则看第二个字母， 以此类推。 排序完成之后，再把所有数组值以“&”字符连接起来 <br>
	 * 没有值的参数无需传递，也无需包含到待签名数据中.
	 * 
	 * @param params
	 *            用于凭借签名的参数
	 * @return the to be signed string
	 */
	private static String getToBeSignedString(Map<String, String> params){
		List<String> keys = new ArrayList<String>(params.keySet());

		// key 排序
		Collections.sort(keys);

		StringBuilder builder = new StringBuilder();
		int size = keys.size();
		for (int i = 0; i < size; ++i){
			String key = keys.get(i);
			String value = params.get(key);

			builder.append(key + "=" + value);
			// 最后一个不要拼接 &
			if (i != size - 1){
				builder.append("&");
			}
		}
		return builder.toString();
	}

	/**
	 * 使用 HttpURLConnection 去alipay 上面 验证 是否确实 校验成功.
	 * 
	 * @param notifyVerifyUrl
	 *            通知验证的url
	 * @return 如果获得的信息是true，则校验成功；如果获得的信息是其他，则校验失败。
	 */
	private boolean isNotifyVerifySuccess(String notifyVerifyUrl){
		try{
			URL url = new URL(notifyVerifyUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

			String notifyVerifyResult = bufferedReader.readLine();
			// 如果获得的信息是true，则校验成功；如果获得的信息是其他，则校验失败。
			return "true".equals(notifyVerifyResult);
		}catch (Exception e){
			log.error(e.getClass().getName(), e);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#getFeedbackSoCode(javax.servlet.http.HttpServletRequest)
	 */
	/**
	 * Do get feedback so code.
	 *
	 * @param request
	 *            the request
	 * @return the string
	 */
	public String doGetFeedbackSoCode(HttpServletRequest request){
		return request.getParameter("out_trade_no");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#doGetFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
	 */
	/**
	 * Do get feedback total fee.
	 *
	 * @param request
	 *            the request
	 * @return the string
	 */
	public String doGetFeedbackTotalFee(HttpServletRequest request){
		return request.getParameter("total_fee");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#closeTrade(java.lang.String, com.jumbo.brandstore.payment.TradeRole)
	 */
	/**
	 * Do close trade.
	 *
	 * @param orderNo
	 *            the order no
	 * @param tradeRole
	 *            the trade role
	 * @return true, if do close trade
	 */
	public boolean doCloseTrade(String orderNo,TradeRole tradeRole){
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#isSupportCloseTrade()
	 */
	/**
	 * Checks if is support close trade.
	 *
	 * @return true, if checks if is support close trade
	 */
	public boolean isSupportCloseTrade(){
		return true;
	}

	// **********************************************************************************************************

	/**
	 * 解析支付宝返回的xml信息.
	 *
	 * @author xialong
	 * @param alipayResult
	 *            the alipay result
	 * @return the map
	 * @throws DocumentException
	 *             the document exception
	 */
	private static Map<String, String> convertResultToMap(String alipayResult) throws DocumentException{
		log.info("alipayResult :\n {}", alipayResult);

		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(new InputSource(new StringReader(alipayResult)));
		Element root = document.getRootElement();

		String is_success = root.elementText("is_success");
		String error = root.elementText("error");
		log.info("is_success : {}", is_success);
		log.info("error : {}", error);

		map.put("is_success", is_success);
		map.put("error", error);

		return map;
	}

	// ****************************************************************************************************************************

	/**
	 * Do get payment form entity for my nike.
	 *
	 * @param code
	 *            the code
	 * @param total_fee
	 *            the total_fee
	 * @param return_url
	 *            the return_url
	 * @param notify_url
	 *            the notify_url
	 * @param specialSignMap
	 *            the special sign map
	 * @return the payment form entity
	 */
	protected PaymentFormEntity doGetPaymentFormEntityForMyNike(
					String code,
					BigDecimal total_fee,
					String return_url,
					String notify_url,
					Map<String, String> specialSignMap){
		return doGetPaymentFormEntity(code, total_fee, return_url, notify_url, specialSignMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.feilong.framework.netpay.payment.PaymentAdaptor#getPaymentFormEntity(com.feilong.framework.netpay.payment.command.PayRequest,
	 * java.util.Map)
	 */
	public PaymentFormEntity getPaymentFormEntity(PayRequest payRequest,Map<String, String> specialParamMap){
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.framework.netpay.payment.PaymentAdaptor#verifyNotify(javax.servlet.http.HttpServletRequest)
	 */
	public PaymentResult verifyNotify(HttpServletRequest request) throws IllegalArgumentException{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.framework.netpay.payment.PaymentAdaptor#verifyRedirect(javax.servlet.http.HttpServletRequest)
	 */
	public PaymentResult verifyRedirect(HttpServletRequest request){
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.framework.netpay.payment.PaymentAdaptor#getFeedbackTradeNo(javax.servlet.http.HttpServletRequest)
	 */
	public String getFeedbackTradeNo(HttpServletRequest request){
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.framework.netpay.payment.PaymentAdaptor#getFeedbackTotalFee(javax.servlet.http.HttpServletRequest)
	 */
	public String getFeedbackTotalFee(HttpServletRequest request){
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 设置 mD5 的私钥是以英文字母和数字组成的 32位字符串。<br>
	 * 商户可登录到商户服务中心（https://b.
	 *
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key){
		this.key = key;
	}

	/**
	 * 设置 表单提交地址 <br>
	 * 支付宝网关.
	 *
	 * @param gateway
	 *            the gateway to set
	 */
	public void setGateway(String gateway){
		this.gateway = gateway;
	}

	/**
	 * 设置 合作者身份id 不可为空 <br>
	 * 签约的 支付宝账号对应的 支付宝唯一用户名.
	 *
	 * @param partner
	 *            the partner to set
	 */
	public void setPartner(String partner){
		this.partner = partner;
	}

	/**
	 * Set_input_charset.
	 *
	 * @param _input_charset
	 *            the _input_charset to set
	 */
	public void set_input_charset(String _input_charset){
		this._input_charset = _input_charset;
	}

	/**
	 * 设置 验证通知的 service.
	 *
	 * @param service
	 *            the service to set
	 */
	public void setService(String service){
		this.service = service;
	}

	/**
	 * 设置 加密方式.
	 *
	 * @param sign_type
	 *            the sign_type to set
	 */
	public void setSign_type(String sign_type){
		this.sign_type = sign_type;
	}

	/**
	 * 设置 货币符号.
	 *
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency){
		this.currency = currency;
	}

	/**
	 * 设置 验证交易接口.
	 *
	 * @param service_notify_verify
	 *            the service_notify_verify to set
	 */
	public void setService_notify_verify(String service_notify_verify){
		this.service_notify_verify = service_notify_verify;
	}

}
