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
package com.feilong.tools.om.os;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.ThreadUtil;
import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.tools.om.os.MonitorInfoEntity;
import com.feilong.tools.om.os.OperatingSystemUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-23 上午11:52:07
 */
public class OperatingSystemUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(OperatingSystemUtilTest.class);

	@Test
	public void getThreadGroupActiveCount(){
		log.info(ThreadUtil.getTopThreadGroupActiveCount() + "");
	}

	@Test
	public void isWindowsOS(){
		log.info(OperatingSystemUtil.isWindowsOS() + "");
	}

	@Test
	public void getMacMap(){
		log.info(JsonFormatUtil.format(OperatingSystemUtil.getMacAddressMap()));
	}

	@Test
	public void getMacAddressByHost(){
		log.info(OperatingSystemUtil.getMacAddressByHost("10.8.17.84"));
		log.info(OperatingSystemUtil.getMacAddressByHost("10.8.12.194"));

		// 180.168.119.194
	}

	@Test
	public void getMacAddressByName(){
		log.info(OperatingSystemUtil.getMacAddressByName("eth3"));
	}

	@Test
	public void getMonitorInfoEntity(){
		MonitorInfoEntity monitorInfoEntity = OperatingSystemUtil.getMonitorInfoEntity();
		log.info(JsonFormatUtil.format(monitorInfoEntity));
	}

	@Test
	public final void getSystemPropertiesLog(){
		log.info(OperatingSystemUtil.getSystemPropertiesLog());
	}

	@Test
	public final void getDiskInfoLog(){
		log.info(OperatingSystemUtil.getDiskInfoLog());
	}

	/**
	 * 获取本机IP地址
	 */
	@Test
	public final void getLocalIP(){
		log.debug(OperatingSystemUtil.getLocalHostAddress());
	}

	/**
	 * 获取本机,计算机名
	 */
	@Test
	public final void getLocalHostName(){
		log.debug(OperatingSystemUtil.getLocalHostName());
	}
}
