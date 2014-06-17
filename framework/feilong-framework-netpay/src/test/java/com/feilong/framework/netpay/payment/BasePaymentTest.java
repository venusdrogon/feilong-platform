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
package com.feilong.framework.netpay.payment;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.ReflectUtil;
import com.feilong.framework.netpay.payment.PaymentAdaptor;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaySoLine;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * The Class BasePaymentTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 22, 2014 4:31:38 PM
 */
@ContextConfiguration(locations = { "classpath*:spring/payment/payment/spring-payment-adaptor.xml" })
public class BasePaymentTest extends AbstractJUnit4SpringContextTests{

	/** The Constant log. */
	protected static final Logger	log					= LoggerFactory.getLogger(BasePaymentTest.class);

	/** The template in class path. */
	private String					templateInClassPath	= "paymentChannel.vm";

	/** The encode. */
	private String					encode				= CharsetType.UTF8;

	private boolean					openFile			= true;

	private String					code				= DateUtil.date2String(new Date(), DatePattern.timestamp);

	// private String code = "44";

	@SuppressWarnings("hiding")
	@Autowired
	protected ApplicationContext	applicationContext;

	/**
	 * 通用的测试方法(自动取到paymentAdaptor 的 Qualifier value).
	 * 
	 * @param paymentAdaptor
	 *            the payment adaptor
	 * @param specialSignMap
	 *            the special sign map
	 * @throws IOException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	protected void createPaymentForm(PaymentAdaptor paymentAdaptor,Map<String, String> specialSignMap){

		BigDecimal total_fee = new BigDecimal(60000.00f);

		PayRequest payRequest = new PayRequest();
		//code="feilong1111";
		payRequest.setTradeNo(code);
		payRequest.setTotalFee(total_fee);
		payRequest.setBuyerEmail("venusdrogon@163.com");
		payRequest.setBuyerName("jinxin");
		payRequest.setBuyer(888);

		payRequest.setTransferFee(new BigDecimal(10000.00f));

		List<PaySoLine> paySoLineList = payRequest.getPaySoLineList();

		PaySoLine paySoLine1 = new PaySoLine();
		paySoLine1.setItemName("nike ;s free 5.0");
		paySoLine1.setUnitPrice(new BigDecimal(20000));
		paySoLine1.setQuantity(1);
		paySoLine1.setSubTotalPrice(new BigDecimal(20000));
		paySoLineList.add(paySoLine1);

		PaySoLine paySoLine2 = new PaySoLine();
		paySoLine2.setItemName("nike free 4.0");
		paySoLine2.setUnitPrice(new BigDecimal(15000));
		paySoLine2.setQuantity(2);
		paySoLine2.setSubTotalPrice(new BigDecimal(30000));
		paySoLineList.add(paySoLine2);

		payRequest.setCreateDate(new Date());

		// ******************************************************************
		String return_url = "http://www.esprit.cn/payment/redirect/klikPay";
		return_url = "http://203.128.73.211/p/klikpayback/010002770003?s=cca0ca41b07759089b8a0c35a2b98a361d3016d8";
		String notify_url = "/patment2url";

		payRequest.setReturnUrl(return_url);
		payRequest.setNotifyUrl(notify_url);

		PaymentFormEntity paymentFormEntity = paymentAdaptor.getPaymentFormEntity(payRequest, specialSignMap);

		if (openFile){
			log.info(JsonUtil.format(paymentFormEntity));

			String fullEncodedUrl = paymentFormEntity.getFullEncodedUrl();
			log.info(fullEncodedUrl);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paymentFormEntity", paymentFormEntity);

			@SuppressWarnings("unused")
			String method = paymentFormEntity.getMethod();

			// if (method.toLowerCase().equals("get")){
			// DesktopUtil.browse(fullEncodedUrl);
			// }else{

			try{
				Field declaredField = ReflectUtil.getDeclaredField(this.getClass(), "paymentAdaptor");
				Qualifier qualifier = declaredField.getAnnotation(Qualifier.class);
				String fileName = qualifier.value() + DateUtil.date2String(new Date(), DatePattern.timestamp);

				String filePath = "F:/payment/" + fileName + ".html";

				String html = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, map);
				log.info(html);

				IOWriteUtil.write(filePath, html, encode);
				DesktopUtil.browse(filePath);
			}catch (SecurityException e){
				e.printStackTrace();
			}catch (NoSuchFieldException e){
				e.printStackTrace();
			}catch (IOException e){
				e.printStackTrace();
			}
			// }
		}

	}
}
