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
package com.feilong.framework.netpay.payment.alipay.pconline;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.framework.netpay.payment.BasePaymentTest;
import com.feilong.framework.netpay.payment.PaymentAdaptor;

/**
 * The Class AlipayOnlineAdaptorTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 16, 2013 8:27:39 PM
 */
public class AlipayOnlineAdaptorTest extends BasePaymentTest{

    /** The payment adaptor. */
    @Autowired
    @Qualifier("alipayOnlineAdaptor")
    private PaymentAdaptor paymentAdaptor;

    // @Autowired
    // @Qualifier("paymentAdaptorMap")
    // @Value("#{paymentAdaptorMap}")
    // private Map<String, PaymentAdaptor> paymentAdaptorMap;

    /**
     * Creates the payment form.
     */
    @Test
    public void createPaymentForm(){
        // Map<String, PaymentAdaptor> paymentAdaptorMap1 = (Map<String, PaymentAdaptor>) applicationContext.getBean("paymentAdaptorMap");
        // PaymentAdaptor paymentAdaptor = paymentAdaptorMap.get("6");

        Map<String, String> specialSignMap = new HashMap<String, String>();
        //specialSignMap.put("token", "20130120e28ebb6933ba483fad4bc190d72b8689");

        // 设置未付款交易的超时时间，一旦时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟， h-小时， d-天， 1c-当天（无论交易何时创建，都在 0 点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        // 该功能需要联系技术支持来配置关闭时间。
        specialSignMap.put("it_b_pay", "20m");
        String url = "http://www.underarmour.cn/s.htm?keyword=鞋&pageNo=2";
        specialSignMap.put("show_url", URIUtil.encodeUrl(url, CharsetType.UTF8));

        //specialSignMap.put("enable_paymethod", "directPay^bankPay^cartoon^cash");
        specialSignMap.put("enable_paymethod", "bankPay");
        //        specialSignMap.put("exter_invoke_ip", "101.231.83.150");

        //公用回传  如果用户请求时传递了该参数，则 返回给商户时会回传该参数。  可空 
        //        specialSignMap.put("extra_common_param", "今天天气不错");
        //        specialSignMap.put(
        //                        "item_orders_info",
        //                        "out_iids=id1&-&id2&+&nums=1&-&2&+&prices=2.50&-&10.01&+&skus=颜色:黑色;尺码:400&-&颜色:白色;尺码:40&+&titles=商品标题1&-&商品标题2&+&detail_urls=http://taobao.item.aa.html&-&http://taobao.item.bb.html&+&pic_urls=http://www.taowaidian.com/img/bg-taxis-cur.png&-&http://www.taowaidian.com/img/list_menu_sch_btn.gif&+&logistics_type=post&+&post_fee=10.00&+&promotion_desc=1.全场满500送50 2.满300减50&+&receiver_name=张三即时到账交易接口&+&receiver_address_prov=浙江省&+&receiver_address_city=杭州市&+&receiver_address_area=西湖区&+&receiver_address=华星路99号创业大厦&+&receiver_mobile=13812345678&+&receiver_phone=0571-88155188");

        createPaymentForm(paymentAdaptor, specialSignMap);
    }
}