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
package com.feilong.tools.net.httpclient3;

import org.apache.commons.httpclient.UsernamePasswordCredentials;

import com.feilong.commons.core.net.HttpRequest;

/**
 * The Class HttpClientConfig.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月8日 上午1:08:10
 * @since 1.0.6
 */
public class HttpClientConfig extends HttpRequest{

    private static final long           serialVersionUID = 5760119862768822039L;

    /** 一组包含安全规则和明文密码的凭据。这个实现对由HTTP标准规范中定义的标准认证模式是足够的. */
    private UsernamePasswordCredentials usernamePasswordCredentials;

    /** 代理地址. */
    private String                      proxyAddress;

    /** 代理port. */
    private int                         proxyPort;

    /**
     * 获得 一组包含安全规则和明文密码的凭据。这个实现对由HTTP标准规范中定义的标准认证模式是足够的.
     *
     * @return the usernamePasswordCredentials
     */
    public UsernamePasswordCredentials getUsernamePasswordCredentials(){
        return usernamePasswordCredentials;
    }

    /**
     * 设置 一组包含安全规则和明文密码的凭据。这个实现对由HTTP标准规范中定义的标准认证模式是足够的.
     *
     * @param usernamePasswordCredentials
     *            the usernamePasswordCredentials to set
     */
    public void setUsernamePasswordCredentials(UsernamePasswordCredentials usernamePasswordCredentials){
        this.usernamePasswordCredentials = usernamePasswordCredentials;
    }

    /**
     * 获得 代理地址.
     *
     * @return the proxyAddress
     */
    public String getProxyAddress(){
        return proxyAddress;
    }

    /**
     * 设置 代理地址.
     *
     * @param proxyAddress
     *            the proxyAddress to set
     */
    public void setProxyAddress(String proxyAddress){
        this.proxyAddress = proxyAddress;
    }

    /**
     * 获得 代理port.
     *
     * @return the proxyPort
     */
    public int getProxyPort(){
        return proxyPort;
    }

    /**
     * 设置 代理port.
     *
     * @param proxyPort
     *            the proxyPort to set
     */
    public void setProxyPort(int proxyPort){
        this.proxyPort = proxyPort;
    }
}