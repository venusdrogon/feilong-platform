/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.tools.net.om;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.enumeration.FileWriteMode;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.util.JsonFormatUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 7:58:34 PM
 */
public class NginxStubStatusUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(NginxStubStatusUtilTest.class);

	@Test
	public void getNginx_status() throws IOException{
		String uri = "http://www.nikestore.com.cn/nginx_status";

		String userName = "nginx_status";
		String password = "baozun_nikestore_status";

		NginxStubStatusCommand nginxStubStatusCommand = NginxStubStatusUtil.getNginxStubStatusCommand(uri, userName, password);
		String format = JsonFormatUtil.format(nginxStubStatusCommand);
		log.info("\n{}", format);

		String filePath = "F:\\test.txt";
		String content = format + "\n";
		String encode = CharsetType.GBK;
		IOWriteUtil.write(filePath, content, encode, FileWriteMode.APPEND);

	}
}
