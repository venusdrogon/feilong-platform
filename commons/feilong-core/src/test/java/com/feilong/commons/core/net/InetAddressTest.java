/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.commons.core.net;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-23 下午01:22:29
 */
public class InetAddressTest{

	private static final Logger	log	= LoggerFactory.getLogger(InetAddressTest.class);

	/**
	 * {@link com.feilong.commons.core.net.InetAddressUtil#domainName2IpAddress(java.lang.String)} 的测试方法。
	 */
	@Test
	public final void domainName2IpAddress(){
		String domainName = "www.e-lining.com";
		domainName = "www.baidu.com";
		domainName = "www.nikestore.com.cn";
		log.info("ip:" + InetAddressUtil.domainName2IpAddress(domainName));
		log.info("ip:" + InetAddressUtil.domainName2IpAddress("127.0.0.1"));
	}
}
