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
package com.feilong.tools.om.os;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.ThreadUtil;
import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class OperatingSystemUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-23 上午11:52:07
 */
@SuppressWarnings("all")
public class OperatingSystemUtilTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(OperatingSystemUtilTest.class);

    /**
     * 获得 thread group active count.
     */
    @Test
    public void getThreadGroupActiveCount(){
        log.info(ThreadUtil.getTopThreadGroupActiveCount() + "");
    }

    /**
     * Checks if is windows os.
     */
    @Test
    public void isWindowsOS(){
        log.info(OperatingSystemUtil.isWindowsOS() + "");
    }

    /**
     * 获得 mac map.
     */
    @Test
    public void getMacMap(){
        log.info(JsonUtil.format(OperatingSystemUtil.getMacAddressMap()));
    }

    /**
     * 获得 mac address by host.
     */
    @Test
    public void getMacAddressByHost(){
        log.info(OperatingSystemUtil.getMacAddressByHost("10.8.17.84"));
        log.info(OperatingSystemUtil.getMacAddressByHost("10.8.12.194"));

        // 180.168.119.194
    }

    /**
     * 获得 mac address by name.
     */
    @Test
    public void getMacAddressByName(){
        log.info(OperatingSystemUtil.getMacAddressByName("eth3"));
    }

    /**
     * 获得 monitor info entity.
     */
    @Test
    public void getMonitorInfoEntity(){
        MonitorInfoEntity monitorInfoEntity = OperatingSystemUtil.getMonitorInfoEntity();
        log.info(JsonUtil.format(monitorInfoEntity));
    }

    /**
     * 获得 system properties log.
     */
    @Test
    public final void getSystemPropertiesLog(){
        log.info(OperatingSystemUtil.getSystemPropertiesLog());
    }

    /**
     * 获得 disk info log.
     */
    @Test
    public final void getDiskInfoLog(){
        log.info(OperatingSystemUtil.getDiskInfoLog());
    }

    /**
     * 获取本机IP地址.
     */
    @Test
    public final void getLocalIP(){
        log.debug(OperatingSystemUtil.getLocalHostAddress());
    }

    /**
     * 获取本机,计算机名.
     */
    @Test
    public final void getLocalHostName(){
        log.debug(OperatingSystemUtil.getLocalHostName());
    }
}
