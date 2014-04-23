/**
 *<h2>欢迎来到feilong支付框架</h2>
 * 
 *<ul>
 *<li>高可配置</li>
 *<li>可扩展</li>
 *<li>支持接入多种支付类型</li>
 *<li>支持和Spring集成,聊聊数行代码即可实现去支付的功能</li>
 *</ul>
 *
 *<h5>目前已经集成了15种支付方式(可直接使用,仅需要修改相关配置参数):</h5>
 *<ol>
 *<li>Alipay 即时到账</li>
 *<li>Alipay 信用卡</li>
 *<li>Alipay 网银在线</li>
 *<li>Alipay 国际卡</li>
 *<li>Alipay 即时到账(无线web)</li>
 *<li>Alipay 信用卡(无线web)</li>
 *<li>Alipay 网银在线(无线web)</li>
 *<li>DOKU Mandiri click</li>
 *<li>DOKU BRI</li>
 *<li>DOKU ATM</li>
 *<li>DOKU creditcard</li>
 *<li>BCA creditcard</li>
 *<li>BCA klikpay</li>
 *<li>BCA klikbca</li>
 *<li>汇付天下</li>
 *</ol>
 * 		
 *<h5>每个适配器,需要实现核心的7个方法:</h5>
 * 
 *<ul>
 *<li>PaymentAdaptor#getPaymentFormEntity 核心方法,生成支付请求参数,该对象可以根据情况直接拿到 fullEncodedUrl直接跳转,或者根据请求参数和action method自行构建提交表单</li>
 *<li>PaymentAdaptor#verifyNotify 后台通知验证(这个步骤可能需要和支付网关交互获得真实的支付状态)</li>
 *<li>PaymentAdaptor#verifyRedirect 前端跳转验证(一般这个步骤仅仅验证参数)</li>
 *<li>PaymentAdaptor#getFeedbackTradeNo 解析请求中的交易号</li>
 *<li>PaymentAdaptor#getFeedbackTotalFee 解析请求中的交易金钱</li>
 *<li>PaymentAdaptor#isSupportCloseTrade 是否支持关闭接口</li>
 *<li>PaymentAdaptor#closeTrade 关闭交易接口</li>
 *</ul>
 *
 * 有任何问题或者建议,您可以通过以下方式联系作者:
 *
 *<ul>
 *<li>email:<a href="mailto:venusdrogon@163.com">金鑫</a></li>
 *<li>博客:<a href="http://feitianbenyue.iteye.com/" target="_blank">飞天奔月的java博客</a></li>
 *<li>weibo:<a href="http://weibo.com/venusdrogon" target="_blank">i鑫哥</a></li>
 *</ul>
 * 
 */
package com.feilong.netpay.adaptor;