package com.feilong.netpay.adaptor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * alipay 信用卡支付<br>
 * 见 快捷支付网银前置文档
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 16, 2013 10:18:24 PM
 */
public class AlipayCreditCardPayAdaptor extends AlipayPayAdaptor{

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.adaptor.AlipayPayAdaptor#setOtherParamsMap()
	 */
	@Override
	protected Map<String, String> setOtherParamsMap(){
		Map<String, String> otherParamsMap = new LinkedHashMap<String, String>();
		// otherParamsMap.put("default_login", "Y");
		// 若要使用快捷支付网银前置，取值必须是 motoPay （快捷支付网银前置）。
		// otherParamsMap.put("paymethod", "motoPay");
		return otherParamsMap;
	}
}
