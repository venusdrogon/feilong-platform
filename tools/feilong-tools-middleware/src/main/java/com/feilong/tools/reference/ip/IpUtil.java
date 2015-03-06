/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.tools.reference.ip;

import com.feilong.commons.core.util.Validator;

/**
 * ip工具包
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-31 下午06:24:40
 */
public class IpUtil{

    /**
     * ip地址转换成地区
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
