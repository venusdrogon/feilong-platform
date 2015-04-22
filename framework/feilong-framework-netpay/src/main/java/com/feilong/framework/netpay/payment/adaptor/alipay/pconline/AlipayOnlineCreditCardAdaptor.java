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
package com.feilong.framework.netpay.payment.adaptor.alipay.pconline;

import com.feilong.commons.core.util.ArrayUtil;

/**
 * alipay 信用卡支付<br>
 * 见 快捷支付网银前置文档<br>
 * 
 * <pre>
 * 目前方法 完全和 alipay 支付一样
 * 只是 注入的 sign参数 增加了 default_login
 * paymethod 值不同
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 16, 2013 10:18:24 PM
 * @version 1.0.5 2014-5-6 20:38 change name
 */
public class AlipayOnlineCreditCardAdaptor extends AlipayOnlineAdaptor{

    //TODO 待再次封装
    /**
     * 判断传入的 银行code 是否 支持.
     * 
     * @param defaultbank
     *            the defaultbank
     * @return true, if is support bank
     */
    private boolean isSupportBank(String defaultbank){
        return ArrayUtil.isContain(SUPPORT_BANKS, defaultbank);
    }

    /** 支持的银行code. */
    private static final String[] SUPPORT_BANKS = {
                                                //银行简码  银行名称 
            "CCB-MOTO-CREDIT",//中国建设银行
            "ICBC-MOTO-CREDIT",//中国工商银行
            "BOC-MOTO-CREDIT",//中国银行
            "SPABANK-MOTO-CREDIT",//平安银行
            "HXBANK-EXPRESS-CREDIT",//华夏银行
            "CMBC-EXPRESS-CREDIT",//中国民生银行
            "CIB-EXPRESS-CREDIT",//兴业银行
            "BJBANK-EXPRESS-CREDIT",//北京银行
            "SHRCB-EXPRESS-CREDIT",//上海农商银行
            "GDB-EXPRESS-CREDIT",//广发银行
            "CEB-EXPRESS-CREDIT",//中国光大银行
            "NXBANK-EXPRESS-CREDIT",//宁夏银行
            "DLB-EXPRESS-CREDIT",//大连银行
            "BOD-EXPRESS-CREDIT",//东莞银行
            "NBBANK-EXPRESS-CREDIT",//宁波银行
            "TCCB-EXPRESS-CREDIT",//天津银行
            "HZCB-EXPRESS-CREDIT",//杭州银行
            "BHB-EXPRESS-CREDIT",//河北银行
            "JSBANK-EXPRESS-CREDIT",//江苏银行
            "NJCB-EXPRESS-CREDIT",//南京银行
            "HSBANK-EXPRESS-CREDIT",//徽商银行
            "ZJTLCB-EXPRESS-CREDIT",//浙江泰隆商业银行
            "WJRCB-EXPRESS-CREDIT",//吴江农商银行
            "ABC-EXPRESS-CREDIT",//中国农业银行
            "CMB-EXPRESS-CREDIT",//招商银行
            "JHBANK-EXPRESS-CREDIT",//金华银行
            "SDEB-EXPRESS-CREDIT",//顺德农村信用联社
            "NBYZ-EXPRESS-CREDIT",//鄞州银行
            "LZYH-EXPRESS-CREDIT",//兰州银行
            "HKB-EXPRESS-CREDIT",//汉口银行
            "DYCCB-EXPRESS-CREDIT",//东营市商业银行
            "H3CB-EXPRESS-CREDIT",//呼和浩特市商业银行
            "BOQH-EXPRESS-CREDIT",//青海银行
            "BOJZ-EXPRESS-CREDIT",//锦州银行
            "JRCB-EXPRESS-CREDIT",//江苏江阴农村商业银行
            "BSB-EXPRESS-CREDIT",//包商银行
            "CSCB-EXPRESS-CREDIT",//长沙市商业银行
            "CDRCB-EXPRESS-CREDIT",//成都农商银行
            "GRCB-EXPRESS-CREDIT",//广州农村商业银行
            "SRBANK-EXPRESS-CREDIT",//上饶银行
            "GZB-EXPRESS-CREDIT",//赣州银行
            "CZCB-EXPRESS-CREDIT",//稠州商业银行
            "CRCBANK-EXPRESS-CREDIT",//重庆农村商业银行
            "CITIC-EXPRESS-CREDIT",//中信银行

                                                };
}
