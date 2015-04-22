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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.feilong.framework.netpay.command.PaymentResult;
import com.feilong.framework.netpay.payment.command.PayRequest;
import com.feilong.framework.netpay.payment.command.PaymentFormEntity;

/**
 * PaymentAdaptor 接口.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 21, 2013 11:13:51 AM
 * @see com.feilong.framework.netpay.payment.adaptor.AbstractPaymentAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.sprintasia.creditcard.SprintAsiaCreditCardAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.sprintasia.klikbca.SprintAsiaKlikBCAAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.SprintAsiaKlikPayAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.doku.AbstractDokuPayAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.doku.CreditCardPayAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.doku.MandiriClickPayAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.doku.BRIEPayAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.doku.PermataVALITEPayAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.chinapnr.ChinapnrAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.alipay.BaseAlipayAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineCreditCardAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineInternationalCardAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineNetpayAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineScanCodeAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.alipay.wap.AlipayWapAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.alipay.wap.AlipayWapCreditCardAdaptor
 * @see com.feilong.framework.netpay.payment.adaptor.alipay.wap.AlipayWapNetpayAdaptor
 */
public interface PaymentAdaptor{

    /**
     * 生成交易表单相关参数.
     * 
     * @param payRequest
     *            交易订单,包含关键交易信息,以便不同的接口实现不同的业务
     * @param specialParamMap
     *            自定义参数map
     *            <ul>
     *            <li>可以为null</li>
     *            <li>也可以是,特殊签名参数设置,会覆盖配置的signMap,比如 某些 网关支持过期时间设置</li>
     *            <li>理论上来说,这个map里面的key都是 银行需要的参数字段, 如果有不是银行需要的字段,但是需要做逻辑控制的,请想办法在 {@link PayRequest}中新增</li>
     *            <li>如果 {@code specialParamMap}有key和 <code>signMap</code>相同, {@code specialParamMap}会覆盖 <code>signMap</code></li>
     *            </ul>
     * @return the payment form entity
     */
    PaymentFormEntity getPaymentFormEntity(PayRequest payRequest,Map<String, String> specialParamMap);

    // ********************************************************************************************

    /**
     * 拿到订单号返回支付服务器重新验证这笔订单到底有没有支付成功<br>
     * 验证此次通知信息是否是支付(宝)服务器发来的信息,以帮助校验反馈回来的数据的真假性。.
     * 
     * @param request
     *            the request
     * @return true, if successful
     * @throws IllegalArgumentException
     *             参数验证不通过
     */
    PaymentResult verifyNotify(HttpServletRequest request) throws IllegalArgumentException;

    /**
     * 处理跳转参数的校验,主要是做核实参数是否正确<br>
     * 每个adaptor 如果有需要可以自己实现.
     * 
     * @param request
     *            the request
     * @return true, if successful
     */
    PaymentResult verifyRedirect(HttpServletRequest request);

    // ********************************************************************************************

    /**
     * feedback 回来 通过request取到 订单号(交易号).
     * 
     * @param request
     *            request
     * @return the string
     */
    String getFeedbackTradeNo(HttpServletRequest request);

    /**
     * feedback 回来 ,通过request取到得到 交易金额<br>
     * 某些商城需要显示 用户实际支付的金额,可以从这里取 不用再次计算了.
     * 
     * @param request
     *            request
     * @return the string
     */
    String getFeedbackTotalFee(HttpServletRequest request);
}
