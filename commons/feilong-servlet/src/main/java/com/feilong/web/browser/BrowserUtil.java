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
package com.feilong.web.browser;

import com.feilong.core.util.StringUtil;

/**
 * 浏览器.<br>
 * 
 * <pre>
 *  userAgent 属性是一个只读的字符串，声明了浏览器用于 HTTP 请求的用户代理头的值。
 *  一般来讲，它是在 navigator.appCodeName 的值之后加上斜线和 navigator.appVersion 的值构成的。
 *  例如：Mozilla/4.0 (compatible; MSIE6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)。
 *  注：用户代理头：user-agent header。
 * 
 *  User Agent中文名为用户代理，简称 UA，它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等。
 * 
 *  标准格式为： 
 *  浏览器标识 (操作系统标识; 加密等级标识; 浏览器语言) 渲染引擎标识 版本信息
 *  
 *  //Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.57 Safari/537.17
 *  
 *  iphone :Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7
 * 
 * </pre>
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 25, 2013 3:38:09 PM
 * @see "c# 的 HttpCapabilitiesBase"
 */
public final class BrowserUtil{

    /** Don't let anyone instantiate this class. */
    private BrowserUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 判断 userAgent 是否是移动设备.
     *
     * @param userAgent
     *            the user agent
     * @return the checks if is mobile device
     */
    public static boolean getIsMobileDevice(String userAgent){
        //if ( $http_user_agent ~ "(MIDP)|(WAP)|(UP.Browser)|(Smartphone)|(Obigo)|(Mobile)|(AU.Browser)|(wxd.Mms)|(WxdB.Browser)|(CLDC)|(UP.Link)|(KM.Browser)|(UCWEB)|(SEMC\-Browser)|(Mini)|(Symbian)|(Palm)|(Nokia)|(Panasonic)|(MOT\-)|(SonyEricsson)|(NEC\-)|(Alcatel)|(Ericsson)|(BENQ)|(BenQ)|(Amoisonic)|(Amoi\-)|(Capitel)|(PHILIPS)|(SAMSUNG)|(Lenovo)|(Mitsu)|(Motorola)|(SHARP)|(WAPPER)|(LG\-)|(LG/)|(EG900)|(CECT)|(Compal)|(kejian)|(Bird)|(BIRD)|(G900/V1.0)|(Arima)|(CTL)|(TDG)|(Daxian)|(DAXIAN)|(DBTEL)|(Eastcom)|(EASTCOM)|(PANTECH)|(Dopod)|(Haier)|(HAIER)|(KONKA)|(KEJIAN)|(LENOVO)|(Soutec)|(SOUTEC)|(SAGEM)|(SEC\-)|(SED\-)|(EMOL\-)|(INNO55)|(ZTE)|(iPhone)|(Android)|(Windows CE)" )
        if (//命中率高的 放前面
        StringUtil.isContainIgnoreCase(userAgent, "iPhone")
                        || StringUtil.isContainIgnoreCase(userAgent, "Mobile")
                        || StringUtil.isContainIgnoreCase(userAgent, "Window CE")
                        ||
                        //StringUtil.isContainIgnoreCase(userAgent, "WAPPER")||
                        StringUtil.isContainIgnoreCase(userAgent, "WAP") || StringUtil.isContainIgnoreCase(userAgent, "Android") ||

                        StringUtil.isContainIgnoreCase(userAgent, "Alcatel") || StringUtil.isContainIgnoreCase(userAgent, "Amoisonic")
                        || StringUtil.isContainIgnoreCase(userAgent, "Amoi-") ||

                        StringUtil.isContainIgnoreCase(userAgent, "AU.Browser") || StringUtil.isContainIgnoreCase(userAgent, "Arima") ||

                        StringUtil.isContainIgnoreCase(userAgent, "Bird") || StringUtil.isContainIgnoreCase(userAgent, "BenQ") ||

                        StringUtil.isContainIgnoreCase(userAgent, "CTL") || StringUtil.isContainIgnoreCase(userAgent, "CECT")
                        || StringUtil.isContainIgnoreCase(userAgent, "Compal") || StringUtil.isContainIgnoreCase(userAgent, "CLDC")
                        || StringUtil.isContainIgnoreCase(userAgent, "Capitel") ||

                        StringUtil.isContainIgnoreCase(userAgent, "Daxian") || StringUtil.isContainIgnoreCase(userAgent, "DBTEL")
                        || StringUtil.isContainIgnoreCase(userAgent, "Dopod") ||

                        StringUtil.isContainIgnoreCase(userAgent, "Eastcom") || StringUtil.isContainIgnoreCase(userAgent, "EMOL-")
                        || StringUtil.isContainIgnoreCase(userAgent, "Ericsson") || StringUtil.isContainIgnoreCase(userAgent, "EG900") ||

                        StringUtil.isContainIgnoreCase(userAgent, "G900/V1.0") ||

                        StringUtil.isContainIgnoreCase(userAgent, "Haier") ||

                        StringUtil.isContainIgnoreCase(userAgent, "INNO55") ||

                        StringUtil.isContainIgnoreCase(userAgent, "kejian") || StringUtil.isContainIgnoreCase(userAgent, "KONKA")
                        || StringUtil.isContainIgnoreCase(userAgent, "KM.Browser") ||

                        StringUtil.isContainIgnoreCase(userAgent, "Lenovo") || StringUtil.isContainIgnoreCase(userAgent, "LG-") ||

                        StringUtil.isContainIgnoreCase(userAgent, "Mitsu") || StringUtil.isContainIgnoreCase(userAgent, "Motorola")
                        || StringUtil.isContainIgnoreCase(userAgent, "Mini") || StringUtil.isContainIgnoreCase(userAgent, "MOT-")
                        || StringUtil.isContainIgnoreCase(userAgent, "MIDP") ||

                        StringUtil.isContainIgnoreCase(userAgent, "Nokia") || StringUtil.isContainIgnoreCase(userAgent, "NEC-") ||

                        StringUtil.isContainIgnoreCase(userAgent, "Obigo") ||

                        StringUtil.isContainIgnoreCase(userAgent, "PANTECH") || StringUtil.isContainIgnoreCase(userAgent, "PHILIPS")
                        || StringUtil.isContainIgnoreCase(userAgent, "Palm") || StringUtil.isContainIgnoreCase(userAgent, "Panasonic") ||

                        StringUtil.isContainIgnoreCase(userAgent, "Soutec") || StringUtil.isContainIgnoreCase(userAgent, "SOUTEC")
                        || StringUtil.isContainIgnoreCase(userAgent, "SAGEM") || StringUtil.isContainIgnoreCase(userAgent, "SEC-")
                        || StringUtil.isContainIgnoreCase(userAgent, "SEMC-Browser")
                        || StringUtil.isContainIgnoreCase(userAgent, "Symbian") || StringUtil.isContainIgnoreCase(userAgent, "Smartphone")
                        || StringUtil.isContainIgnoreCase(userAgent, "SHARP") || StringUtil.isContainIgnoreCase(userAgent, "SonyEricsson")
                        || StringUtil.isContainIgnoreCase(userAgent, "SAMSUNG") ||

                        StringUtil.isContainIgnoreCase(userAgent, "TDG") ||

                        StringUtil.isContainIgnoreCase(userAgent, "UCWEB") || StringUtil.isContainIgnoreCase(userAgent, "UP.Browser")
                        || StringUtil.isContainIgnoreCase(userAgent, "UP.Link") ||

                        StringUtil.isContainIgnoreCase(userAgent, "wxd.Mms") || StringUtil.isContainIgnoreCase(userAgent, "WxdB.Browser") ||

                        StringUtil.isContainIgnoreCase(userAgent, "ZTE")

        ){
            return true;
        }
        return false;
    }
}
