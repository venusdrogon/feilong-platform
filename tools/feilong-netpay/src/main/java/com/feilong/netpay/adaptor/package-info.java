/**
 * 欢迎来到feilong支付框架
 * 
 * feilong支付框架,是可扩展的,支持接入各种支付类型
 * 
 * 目前已经集成了6种支付方式:
 * <ul>
 *<li>DOKU Mandiri click</li>
 *<li>DOKU BRI</li>
 *<li>DOKU ATM</li>
 *<li>DOKU creditcard</li>
 *<li></li>
 *<li>BCA creditcard</li>
 *<li>BCA klikpay</li>
 *<li>BCA klikbca</li>
 *<li></li>
 *<li>汇付天下</li>
 *<li></li>
 *<li></li>
 *<li></li>
 *<li></li>
 *<li></li>
 *<li></li>
 *</ul>
 * 		
 *每个适配器,需要实现核心的7个方法:
 * 
 *<ul>
 *<li>PaymentAdaptor#verifyNotify 后台通知验证(这个步骤可能需要和支付网关交互获得真实的支付状态)</li>
 *<li>PaymentAdaptor#verifyRedirect 前端跳转验证(一般这个步骤仅仅验证参数)</li>
 *<li>PaymentAdaptor#getFeedbackTradeNo 解析请求中的交易号</li>
 *<li>PaymentAdaptor#getFeedbackTotalFee 解析请求中的交易金钱</li>
 *<li>PaymentAdaptor#isSupportCloseTrade 是否支持关闭接口</li>
 *<li>PaymentAdaptor#closeTrade 关闭交易接口</li>
 *</ul>
 */
package com.feilong.netpay.adaptor;