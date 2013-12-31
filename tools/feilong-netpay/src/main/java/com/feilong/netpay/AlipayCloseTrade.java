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

package com.feilong.netpay;
//package com.feilong.commons.netpay;
//
//
//import java.io.IOException;
//import java.io.StringReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.ResourceBundle;
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.xml.sax.InputSource;
//
//import com.jumbo.util.Md5Encrypt;
///**
// * 支付宝关闭交易接口；当支付宝返回交易已经关闭，或者交易不存在的时候返回 true 表示关闭交易成功;
// * 注意：只有支付方式为支付宝的订单才需要调用该接口推荐先取消商城方自己的订单，然后再调用关闭交易的接口，交易关闭失败抛出异常，让事物进行回滚
// * @time 2012-11-26  下午6:22:11
// */
//public class AlipayCloseTrade{
//	
//	private static final Logger     logger              = LoggerFactory.getLogger(AlipayCloseTrade.class);	
//	
//	private static final String		POST_URL			= "https://mapi.alipay.com/gateway.do?";
//
//	private static final String		POST_PARTNER		= "partner";
//
//	private static final String		POST_SERVICE		= "service";
//
//	private static final String		POST_SIGN_TYPE		= "sign_type";
//	
//	private static final String 	POST_OUT_ORDER_NO	= "out_order_no";
//	
//	private static final String 	POST_TRADE_ROLE		= "trade_role";
//	
//	private static final String 	POST_CHARSET		= "_input_charset";
//	
//	private static final String		POST_SIGN			= "sign";
//	
//	private static final String 	SERVICE				= "close_trade";
//	
//	private static final String		INPUT_CHARSET		= "utf-8";
//	
//	private static final String 	SIGN_TYPE 			= "MD5";	
//	
//	/**买家取消*/
//	public static final String 	TRADE_BUYER			= "B";
//	
//	/**卖家取消*/
//	public static final String 	TRADER_SELLER		= "S";
//	
//	/**取消訂單成功*/
//	private static final String 	IS_SUCCESS			= "T";
//	
//	private static final String 	RESPONSE_IS_SUCCESS	= "is_success";
//	
//	private static final String 	RESPONSE_ERROR		= "error";
//	
//	/**交易不存在*/
//	private static final String 	TRADE_NOT_EXIST		= "TRADE_NOT_EXIST";
//	
//	
//	/**
//	 * 
//	 *@author xialong 
//	 *
//	 *@param orderNo
//	 *			官方商城唯一订单号
//	 *@param tradeRole
//	 *			取消交易方：B-买家取消；S-卖家取消
//	 *@return
//	 */
//	public static boolean closeTrade(String orderNo,String tradeRole,int type){
//		ResourceBundle interfaceConfig	=null;
//		String	partent	= "";
//		String	key	= "";
//		if(type==1){
//			//配置文件在config文件夹下
//			interfaceConfig= ResourceBundle.getBundle("config/interface-config");
//		}else if(type==2){
//			//配置文件直接在resource下
//			interfaceConfig= ResourceBundle.getBundle("interface-config");
//		}
//		partent = interfaceConfig.getString("alipay.partner");
//		key	= interfaceConfig.getString("alipay.key");
//		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put(POST_SERVICE, SERVICE);
//		map.put(POST_PARTNER, partent);
//		map.put(POST_CHARSET, INPUT_CHARSET);
//		map.put(POST_OUT_ORDER_NO, orderNo);
//		map.put(POST_TRADE_ROLE, tradeRole);
//		
//		String signStr = getSignStr(map, key);
//		
//		logger.debug("signStr : {}",signStr);
//		
//		String sign = Md5Encrypt.md5(signStr,INPUT_CHARSET);
//		
//		map.put(POST_SIGN, sign);
//		map.put(POST_SIGN_TYPE, SIGN_TYPE);
//		
//		String postUrl = getPostUrl(POST_URL,map);
//		
//		String returnXML = URLGetByHttpClient(postUrl);
//		try {
//			Map<String,String> resultMap = splitXML(returnXML);
//			if(IS_SUCCESS.equals(resultMap.get(RESPONSE_IS_SUCCESS))
//					|| TRADE_NOT_EXIST.equals(resultMap.get(RESPONSE_ERROR))){
//				return true;
//			}else{
//				logger.error("close trade error : code-{},info-{}",new String[]{orderNo,resultMap.get(RESPONSE_ERROR)});
//			}
//			
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//	
//	/**
//	 * 生成加密字符串
//	 *@author xialong 
//	 *
//	 *@param params 需要加密的参数 
//	 *@param privateKey
//	 *@return
//	 */
//	private static String getSignStr(Map<String, String> params,String privateKey){
//		List<String> keys = new ArrayList<String>(params.keySet());
//		Collections.sort(keys);
//		String prestr = "";
//		for (int i = 0; i < keys.size(); i++){
//			String key = (String) keys.get(i);
//			String value = params.get(key).toString();
//			if (i == keys.size() - 1){
//				prestr = prestr + key + "=" + value;
//			}else{
//				prestr = prestr + key + "=" + value + "&";
//			}
//		}
//		return prestr + privateKey;
//	}
//	
//	/**
//	 *  生成请求连接
//	 *@author xialong 
//	 *
//	 *@param url 
//	 *@param params
//	 *@return
//	 */
//	private static String getPostUrl(String url,Map<String,String> params){
//		List<String> keys = new ArrayList<String>(params.keySet());
//		String prestr = "";
//		for (int i = 0; i < keys.size(); i++){
//			String key = (String) keys.get(i);
//			String value = params.get(key).toString();
//			if (i == keys.size() - 1){
//				prestr = prestr + key + "=" + value;
//			}else{
//				prestr = prestr + key + "=" + value + "&";
//			}
//		}
//		return url.concat(prestr);
//	}
//	
//	private static String URLGetByHttpClient(String url) {
//		HttpClient client = new HttpClient();
//		GetMethod get = new GetMethod(url);
//		String resultMsg = "";//返回值
//		try {
//			client.executeMethod(get);
//			if ((resultMsg=get.getResponseBodyAsString()).trim().length() > 0) {
//				return resultMsg;
//			}
//		} catch (HttpException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	/**
//	 * 解析支付宝返回的xml信息
//	 *@author xialong 
//	 *
//	 *@param xml
//	 *@return
//	 *@throws DocumentException
//	 */
//	private static Map<String,String> splitXML(String xml) throws DocumentException{
//		logger.info("xml : {}",xml);
//		
//		Map<String,String> map = new HashMap<String, String>();
//		SAXReader reader = new SAXReader();
//		Document doc = reader.read(new InputSource(new StringReader(xml)));
//		Element root = doc.getRootElement();
//		logger.info("is_success : {}",root.elementText("is_success"));
//		logger.debug("error : {}",root.elementText("error"));
//		
//		map.put("is_success", root.elementText("is_success"));
//		map.put("error", root.elementText("error"));
//		return map;
//	}
//}