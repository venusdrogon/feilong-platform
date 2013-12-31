/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */

package com.feilong.netpay.adaptor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.AbstractPaymentAdaptor;
import com.feilong.netpay.PaymentFormEntity;
import com.feilong.netpay.util.Md5Encrypt;

/**
 * alipay 纯网关接口<br>
 * <ul>
 * <li>此接口只支持 https 请求</li>
 * <li>参数 body（商品描述）、subject（商品名称）、extra_common_param（公用 回传参数）不能包含特殊字符（如：#、%、&、+）、敏感词汇，<br>
 * 也不能使用外 国文字（旺旺不支持的外文，如：韩文、泰语、藏文、蒙古文、阿拉伯语）；</li>
 * <li>此接口支持重复调用，前提是交易基本信息（买家、卖家、交易金额、超时时间等）在多次调用中保持一致，且交易尚未完成支付。</li>
 * </ul>
 * .
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 15, 2013 8:41:39 PM
 */
public class AlipayPayAdaptor extends AbstractPaymentAdaptor{

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

	/** 通知验证地址. */
	private String				notify_verify_url;

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

	// **********************************************************************************************

	/** 其他需要被签名的 Map. */
	private Map<String, String>	signMap;

	// // ***************************************************************************************
	// HttpSession session = request.getSession();
	// // 支付宝联合登陆
	// String strToken = (String) session.getAttribute("aliPayToken");
	// boolean hasToken = strToken != null && strToken.length() > 0;
	// if (hasToken){
	// params.put("token", strToken);
	// }
	// /**
	// * Begin payment1.
	// *
	// * @param so
	// * the so
	// * @param request
	// * the request
	// * @return the payment form entity
	// */
	// public PaymentFormEntity beginPayment1(SalesOrder so,HttpServletRequest request){
	// String code = so.getCode();
	// String paymentType = so.getPaymentType();
	// BigDecimal total_fee = so.getTotalforOrder();
	// String return_url = getReturnUrl(code, request);
	// String notify_url = getNotifyUrl(paymentType, request);
	//
	// return doBeginPayment(code, total_fee, return_url, notify_url);
	// }

	// model.addAttribute("postItem", paymentData);
	// model.addAttribute("postUrl", POST_URL);
	// model.addAttribute("postMethod", "get");

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.PaymentAdaptor#doBeginPayment(java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.String, java.util.Map)
	 */
	public PaymentFormEntity doBeginPayment(String code,BigDecimal total_fee,String return_url,String notify_url,Map<String, String> specialSignMap){
		if (doValidator(code, total_fee, return_url, notify_url)){

			// *************************************************************************************************
			// 需要被签名的 参数map
			Map<String, String> signParamsMap = new HashMap<String, String>();

			// 注入 或者设置的
			if (Validator.isNotNullOrEmpty(signMap)){
				signParamsMap.putAll(signMap);
			}

			// 子类可以实现 比如 alipay 信用卡, alipay网银
			Map<String, String> otherParamsMap = setOtherParamsMap();
			if (Validator.isNotNullOrEmpty(otherParamsMap)){
				signParamsMap.putAll(otherParamsMap);
			}

			// 特殊 传入
			if (Validator.isNotNullOrEmpty(specialSignMap)){
				signParamsMap.putAll(specialSignMap);
			}

			// 放在 所有设置的 最下面,保证 核心参数不会被 子类修改
			setCommonAlipayParams(code, total_fee, return_url, notify_url, signParamsMap);

			// *************************************************************************************
			// 待签名字符串
			// 除去sign、sign_type 两个参数外，其他需要使用到的参数皆是要签名的参数
			String toBeSignedString = getToBeSignedString(signParamsMap);

			// 在 MD5 签名时，需要私钥参与签名。
			// 需要把私钥直接拼接到待签名字符串后面，形成新的字符串，利用MD5 的签名函数对这个新的字符串进行签名运算
			String sign = Md5Encrypt.md5(toBeSignedString + key, _input_charset);

			// *************************************************************************************
			Map<String, String> hiddenParamsMap = new LinkedHashMap<String, String>();
			hiddenParamsMap.putAll(signParamsMap);

			hiddenParamsMap.put("sign", sign);

			// #签名方式
			// #DSA,RSA,MD5三值可选
			// #必须大写
			// #宝尊 暂时只支持MD5
			hiddenParamsMap.put("sign_type", "MD5");

			PaymentFormEntity paymentFormEntity = new PaymentFormEntity();
			paymentFormEntity.setAction(gateway);
			paymentFormEntity.setMethod("get");
			paymentFormEntity.setHiddenParamMap(hiddenParamsMap);
			return paymentFormEntity;
		}
		return null;
	}

	/**
	 * 验证参数
	 * 
	 * @param code
	 * @param total_fee
	 * @param return_url
	 * @param notify_url
	 */
	public boolean doValidator(String code,BigDecimal total_fee,String return_url,String notify_url){
		// ******************************************************************
		// validate
		if (Validator.isNullOrEmpty(code)){
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

	/**
	 * 设置 共用的 alipay 参数信息
	 * 
	 * @param code
	 * @param total_fee
	 * @param return_url
	 * @param notify_url
	 * @param signParamsMap
	 */
	private void setCommonAlipayParams(String code,BigDecimal total_fee,String return_url,String notify_url,Map<String, String> signParamsMap){
		// 支付宝合作商户网站,唯一订单号 （确保在商户系统中唯一） String(64)
		signParamsMap.put("out_trade_no", code);

		// 交易金额 该笔订单的资金总额，单位为 RMB-Yuan。
		// 取值范围为[0.01， 100000000.00]，
		// 精确到小数点后 两位。
		signParamsMap.put("total_fee", total_fee.setScale(2, BigDecimal.ROUND_HALF_UP) + "");

		// 页面跳转同步通知页面路径String(200)
		// 支付宝处理完请求后，当前页面自 动跳转到商户网站里指定页面的 http 路径。
		signParamsMap.put("return_url", return_url);

		// 服务器异步通知页面路径 String(190)
		// 支付宝服务器主动通知商户网站 里指定的页面 http 路径。
		signParamsMap.put("notify_url", notify_url);

		signParamsMap.put("_input_charset", _input_charset);
		signParamsMap.put("partner", partner);

		// signParamsMap.put("it_b_pay", "1m");
	}

	/**
	 * 子类可以实现 比如 alipay 信用卡, alipay网银
	 * 
	 * @return
	 */
	protected Map<String, String> setOtherParamsMap(){
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#notifyVerify(java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean doNotifyVerify(HttpServletRequest request){
		// 获取支付宝返回数据之一的通知校验ID（notify_id），按照支付宝要求的格式拼接成要请求的链接

		// 通过访问这个请求链接，利用编程方法来模拟http请求与支付宝服务器进行交互，
		// 获得支付宝服务器上处理的结果。 如果获得的信息是true，则校验成功；如果获得的信息是其他，则校验失败。
		Map<String, String[]> paramData = request.getParameterMap();

		// TODO 还有没有必要再次 sign 来确认了
		Map<String, String> params = new HashMap<String, String>();
		for (Iterator<String> iter = paramData.keySet().iterator(); iter.hasNext();){
			String name = iter.next();
			String[] values = paramData.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++){
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			if (name.equals("body") || name.equals("subject")){
				valueStr = "Nike官方商城商品";
			}
			params.put(name, valueStr);
		}
		String mysign = sign(params, key);
		boolean isSignOk = mysign.equals(paramData.get("sign")[0]);

		if (isSignOk){
			// 示例https://mapi.alipay.com/gateway.do?service=notify_verify&partner=2088002396712354&notify_id=RqPnCoPT3K9%252Fvwbh3I%252BFioE227%252BPfNMl8jwyZqMIiXQWxhOCmQ5MQO%252FWd93rvCB%252BaiGg
			String alipayNotifyURL = notify_verify_url + "partner=" + partner + "&notify_id=" + paramData.get("notify_id")[0];

			boolean isNotifyVerifySuccess = isNotifyVerifySuccess(alipayNotifyURL);

			if (isNotifyVerifySuccess){
				// 付款成功
				return true;
			}else{
				return false;
			}
		}else{
			return false;
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
	 * 使用 HttpURLConnection 去alipay 上面 验证 是否确实 校验成功
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
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Sign.
	 * 
	 * @param params
	 *            the params
	 * @param privateKey
	 *            the private key
	 * @return the string
	 */
	private String sign(Map<String, String> params,String privateKey){
		Properties properties = new Properties();
		for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext();){
			String name = iter.next();
			Object value = params.get(name);
			if (name == null || name.equalsIgnoreCase("sign") || name.equalsIgnoreCase("sign_type")){
				continue;
			}
			properties.setProperty(name, value.toString());
		}
		String content = getSignatureContent(properties);

		if (privateKey == null){
			return null;
		}
		String signBefore = content + privateKey;
		return Md5Encrypt.md5(signBefore, _input_charset);
	}

	/**
	 * Gets the signature content.
	 * 
	 * @param properties
	 *            the properties
	 * @return the signature content
	 */
	private static String getSignatureContent(Properties properties){
		StringBuffer content = new StringBuffer();
		List keys = new ArrayList(properties.keySet());
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++){
			String key = (String) keys.get(i);
			String value = properties.getProperty(key);
			content.append((i == 0 ? "" : "&") + key + "=" + value);
		}
		return content.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#getFeedbackSoCode(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String doGetFeedbackSoCode(HttpServletRequest request){
		return request.getParameter("out_trade_no");
	}

	/**
	 * Sets the mD5 的私钥是以英文字母和数字组成的 32位字符串。<br>
	 * 商户可登录到商户服务中心（https://b.
	 * 
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key){
		this.key = key;
	}

	/**
	 * Sets the 合作者身份id 不可为空 <br>
	 * 签约的 支付宝账号对应的 支付宝唯一用户名.
	 * 
	 * @param partner
	 *            the partner to set
	 */
	public void setPartner(String partner){
		this.partner = partner;
	}

	/**
	 * Sets the 表单提交地址 <br>
	 * 支付宝网关.
	 * 
	 * @param gateway
	 *            the gateway to set
	 */
	public void setGateway(String gateway){
		this.gateway = gateway;
	}

	/**
	 * Sets the 通知验证地址.
	 * 
	 * @param notify_verify_url
	 *            the notify_verify_url to set
	 */
	public void setNotify_verify_url(String notify_verify_url){
		this.notify_verify_url = notify_verify_url;
	}

	/**
	 * @param _input_charset
	 *            the _input_charset to set
	 */
	public void set_input_charset(String _input_charset){
		this._input_charset = _input_charset;
	}

	/**
	 * @param signMap
	 *            the signMap to set
	 */
	public void setSignMap(Map signMap){
		this.signMap = signMap;
	}

}
