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
package com.feilong.commons.core.io;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-27 上午11:55:46
 * @since 1.0
 */
public class IOUtilTest{

	private static final Logger	log			= LoggerFactory.getLogger(IOUtilTest.class);

	private String				fileName1	= "F:/pie2.png";

	private String				fileName	= "E:\\Data\\Java\\Taglib\\Apache Commons 非常有用的工具包\\commons-net\\ftp";

	private String				fString		= "/home/webuser/nike_int/johnData/${date}/nikeid_pix_${typeName}.csv";

	@Test
	public void down(){
		String url = "http://www.kenwen.com/egbk/31/31186/4395342.txt";
		String directoryName = SpecialFolder.getDesktop();
		IOUtil.down(url, directoryName);
	}

	@Test
	public void getContentLength(){
		try{
			URL url = new URL("http://www.jinbaowang.cn/images//20110722/096718c3d1c9b4a1.jpg");
			URLConnection urlConnection = url.openConnection();
			int contentLength = urlConnection.getContentLength();
			System.out.println(FileUtil.formatFileSize(contentLength));
		}catch (IOException e){
			e.printStackTrace();
		}
		try{
			URL url = new URL("http://localhost:8080/TestHttpURLConnectionPro/index.jsp");
			URLConnection rulConnection = url.openConnection();

			HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
		}catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testGetFullName(){
		Class class1 = IOUtil.class;
		System.out.println(class1.getName());
	}

	@Test
	// @Ignore
	public void testGetP(){
		File file = new File(fString);
		log.info(file.getAbsolutePath());
		System.out.println(file.getParent());
	}

}
