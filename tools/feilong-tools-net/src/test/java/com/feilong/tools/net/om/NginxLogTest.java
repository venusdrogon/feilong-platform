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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.IOReaderUtil;
import com.feilong.commons.core.io.IOUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-27 上午11:55:46
 * @since 1.0
 */
public class NginxLogTest{

	private static final Logger	log	= LoggerFactory.getLogger(NginxLogTest.class);

	// $remote_addr - $remote_user [$time_local] $status "$request" $body_bytes_sent "$http_referer" "$http_user_agent"
	// $proxy_add_x_forwarded_for "$upstream_addr" "$request_time";
	// 112.64.235.89 - - [19/Dec/2013:16:21:47 +0800] 404 "GET /product/fair/aj11blue1200.htm?cid=dis1221ondspaj01 HTTP/1.1" 29520 "-"
	// "Mozilla/4.0" 112.64.235.89 "192.168.10.27:8407" "0.027"
	@Test
	public void testAj11visitlogall(){
		String fileName = "C:\\Users\\feilong\\Documents\\AJ11\\AJ11\\aj11visitlogall3000000.txt";
		String content = IOReaderUtil.getFileContent(fileName);
		// 将内容以换行符转成数组
		// String[] rowsContents = content.split("\r\n");
		System.out.println(content);
	}

	@Test
	public void nginxLogFormatTest(){

		String line = "222.68.172.190 - - [18/Sep/2013:06:49:57 +0000] \"GET /images/my.jpg HTTP/1.1\" 200 19939 \"http://www.angularjs.cn/A00n\" \"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36\"";
		System.out.println(line);
		NginxLogFormat kpi = new NginxLogFormat();
		String[] arr = line.split(" ");
		kpi.setRemote_addr(arr[0]);
		kpi.setRemote_user(arr[1]);
		kpi.setTime_local(arr[3].substring(1));
		kpi.setRequest(arr[6]);
		kpi.setStatus(arr[8]);
		kpi.setBody_bytes_sent(arr[9]);
		kpi.setHttp_referer(arr[10]);
		kpi.setHttp_user_agent(arr[11] + " " + arr[12]);
		System.out.println(kpi);
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd:HH:mm:ss", Locale.US);
			System.out.println(df.format(kpi.getTime_local_Date()));
			System.out.println(kpi.getTime_local_Date_hour());
			System.out.println(kpi.getHttp_referer_domain());
		}catch (ParseException e){
			e.printStackTrace();
		}
	}
}
