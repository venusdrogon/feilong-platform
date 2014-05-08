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
package com.feilong.netpay.adaptor.alipay;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.feilong.commons.core.enumeration.HttpMethodType;
import com.feilong.commons.core.security.oneway.MD5Util;
import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.adaptor.AbstractPaymentAdaptor;
import com.feilong.netpay.command.TradeRole;
import com.feilong.servlet.http.ParamUtil;
import com.feilong.tools.net.httpclient3.HttpClientConfig;
import com.feilong.tools.net.httpclient3.HttpClientUtil;
import com.feilong.tools.net.httpclient3.HttpClientException;

/**
 * 所有支付宝支付的父类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014-5-6 20:20:08
 */
public abstract class BaseAlipayAdaptor extends AbstractPaymentAdaptor{

	/** The Constant log. */
	private static final Logger		log					= LoggerFactory.getLogger(BaseAlipayAdaptor.class);

	/** The Constant PARAM_DEFAULT_BANK. */
	protected static final String	PARAM_DEFAULT_BANK	= "defaultbank";

	/**
	 * MD5 的私钥是以英文字母和数字组成的 32位字符串。<br>
	 * 商户可登录到商户服务中心（https://b.alipay.com），安装数字证书，在“技术服务”栏目中点击“交易安全校验码”，即可查看。<br>
	 * 此处的key 千万不要暴露给别人知道
	 */
	protected String				key;

	/**
	 * 合作者身份id 不可为空 <br>
	 * 签约的 支付宝账号对应的 支付宝唯一用户名.
	 */
	protected String				partner;

	/**
	 * 表单提交地址 <br>
	 * 支付宝网关.
	 */
	protected String				gateway;

	/** 关闭交易. */
	protected String				service_close_trade;

	/**
	 * 参数编码字符集 不可为空 <br>
	 * 商户网站使用的 编码格式 如utf-8 ,gbk, gb2312.
	 */
	protected String				_input_charset;

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

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#closeTrade(java.lang.String, com.jumbo.brandstore.payment.TradeRole)
	 */
	public boolean closeTrade(String orderNo,TradeRole tradeRole) throws HttpClientException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("service", service_close_trade);
		params.put("partner", partner);
		params.put("_input_charset", _input_charset);
		params.put("out_order_no", orderNo);

		String trade_role = "";
		switch (tradeRole) {
			case BUYER:
				// 买家取消
				trade_role = "B";
				/** 买家取消 */
				break;
			case SELLER:
				// 卖家取消
				trade_role = "S";
				break;
			default:
				throw new IllegalArgumentException("trade_role can't be null/empty!");
		}

		params.put("trade_role", trade_role);

		String toBeSignedString = ParamUtil.getToBeSignedString(params);
		String sign = MD5Util.encode(toBeSignedString + key, _input_charset);

		params.put("sign", sign);
		params.put("sign_type", "MD5");

		return _closeTrade(params);

	}

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.PaymentAdaptor#isSupportCloseTrade()
	 */
	public boolean isSupportCloseTrade(){
		return true;
	}

	/**
	 * 关闭交易.
	 * 
	 * @param params
	 *            参数
	 * @return true, if successful
	 * @throws HttpClientException
	 *             the http client util exception
	 */
	private boolean _closeTrade(Map<String, String> params) throws HttpClientException{
		String closeTradeUrl = getCloseTradeUrl(params);

		HttpClientConfig httpClientConfig = new HttpClientConfig();

		httpClientConfig.setUri(closeTradeUrl);
		httpClientConfig.setHttpMethodType(HttpMethodType.GET);

		String returnXML = HttpClientUtil.getResponseBodyAsString(httpClientConfig);

		if (Validator.isNotNullOrEmpty(returnXML)){
			try{
				Map<String, String> resultMap = convertResultToMap(returnXML);
				String errorMessage = resultMap.get("error");
				if ("T".equals(resultMap.get("is_success"))// 取消訂單成功
						|| "TRADE_NOT_EXIST".equals(errorMessage)// 交易不存在
				){
					return true;
				}
				String orderNo = params.get("out_order_no");
				Object[] args = { orderNo, errorMessage, closeTradeUrl };
				log.error("close trade error : out_order_no:[{}],info:[{}],closeTradeUrl:{}", args);
			}catch (DocumentException e){
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 生成请求连接.
	 * 
	 * @param params
	 *            the params
	 * @return the close trade url
	 * @author xialong
	 */
	// TODO
	private String getCloseTradeUrl(Map<String, String> params){
		List<String> keys = new ArrayList<String>(params.keySet());
		String prestr = "";
		for (int i = 0; i < keys.size(); i++){
			// String key = keys.get(i);
			String value = params.get(key).toString();
			if (i == keys.size() - 1){
				prestr = prestr + key + "=" + value;
			}else{
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return gateway.concat("?").concat(prestr);
	}

	/**
	 * 解析支付宝返回的xml信息.
	 * 
	 * @param alipayResult
	 *            the alipay result
	 * @return the map
	 * @throws DocumentException
	 *             the document exception
	 * @author xialong
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
	 * 设置 关闭交易.
	 * 
	 * @param service_close_trade
	 *            the service_close_trade to set
	 */
	public void setService_close_trade(String service_close_trade){
		this.service_close_trade = service_close_trade;
	}

	/**
	 * Sets the _input_charset.
	 * 
	 * @param _input_charset
	 *            the _input_charset to set
	 */
	public void set_input_charset(String _input_charset){
		this._input_charset = _input_charset;
	}

}
