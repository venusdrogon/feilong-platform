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
package com.feilong.commons.core.net;

/**
 * HttpURLConnectionParam.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 27, 2013 4:54:08 PM
 * @version 1.0.9 2015年3月5日 下午5:49:15 add proxyAddress&&proxyPort
 * @since 1.0.2
 */
public final class HttpURLConnectionParam extends URLConnectionParam{

    /** The proxy address. */
    private String  proxyAddress;

    /**
     * 代理端口 <br>
     * A valid port value is between 0 ~ 65535. <br>
     * A port number of zero will let the system pick up an ephemeral port in a bind operation.
     */
    private Integer proxyPort;

    /**
     * 获得 the proxy address.
     *
     * @return the proxyAddress
     */
    public String getProxyAddress(){
        return proxyAddress;
    }

    /**
     * 设置 the proxy address.
     *
     * @param proxyAddress
     *            the proxyAddress to set
     */
    public void setProxyAddress(String proxyAddress){
        this.proxyAddress = proxyAddress;
    }

    /**
     * 获得 代理端口 <br>
     * A valid port value is between 0 ~ 65535.
     *
     * @return the proxyPort
     */
    public Integer getProxyPort(){
        return proxyPort;
    }

    /**
     * 设置 代理端口 <br>
     * A valid port value is between 0 ~ 65535.
     *
     * @param proxyPort
     *            the proxyPort to set
     */
    public void setProxyPort(Integer proxyPort){
        this.proxyPort = proxyPort;
    }
}
