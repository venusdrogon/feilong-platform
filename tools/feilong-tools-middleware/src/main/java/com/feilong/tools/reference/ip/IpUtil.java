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
package com.feilong.tools.reference.ip;

import com.feilong.commons.core.util.Validator;

/**
 * ip工具包.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-31 下午06:24:40
 */
public class IpUtil{

    /**
     * ip地址转换成地区.
     *
     * @param ip
     *            ip地址
     * @param showContent
     *            显示内容<br>
     *            region 地区 <br>
     *            business:运营商 <br>
     *            all 地区+运营商 默认地区
     * @return ip地址转换成地区
     */
    public static String ipToAddress(String ip,Object...showContent){
        IPSeeker ipSeeker = IPSeeker.getInstance();
        if (Validator.isNotNullOrEmpty(showContent)){
            if ("region".equals(showContent[0])){
                return ipSeeker.getCountry(ip);
            }else if ("business".equals(showContent[0])){
                return ipSeeker.getArea(ip);
            }else if ("all".equals(showContent[0])){
                return ipSeeker.getAddress(ip);
            }
        }
        return ipSeeker.getCountry(ip);
    }
}
