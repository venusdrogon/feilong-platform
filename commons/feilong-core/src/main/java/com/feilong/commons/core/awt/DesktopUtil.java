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
package com.feilong.commons.core.awt;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.net.URIUtil;

/**
 * Desktop 类允许 Java 应用程序启动已在本机桌面上注册的关联应用程序，以处理 URI 或文件。
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-5 下午05:07:45
 * @since 1.0
 * @since jdk 1.6
 */
public final class DesktopUtil{

	private final static Logger	log	= LoggerFactory.getLogger(DesktopUtil.class);

	/**
	 * 使用系统默认浏览器,打开url
	 * 
	 * @param url
	 *            url地址
	 */
	public static void browse(String url){
		// 判断当前系统是否支持Java AWT Desktop扩展
		if (Desktop.isDesktopSupported()){
			// 创建一个URI实例
			URI uri = URIUtil.create(url);
			// 获取当前系统桌面扩展
			Desktop desktop = Desktop.getDesktop();
			// 判断系统桌面是否支持要执行的功能
			if (desktop.isSupported(Action.BROWSE)){
				// 获取系统默认浏览器打开链接
				try{
					desktop.browse(uri);
				}catch (IOException e){
					log.debug(e.getMessage());
				}
			}
		}else{
			log.error("don'nt Support Desktop");
		}
	}

	/**
	 * 启动关联应用程序来打开文件。
	 * 
	 * @param url
	 *            url地址
	 */
	public static void open(String url){
		// 判断当前系统是否支持Java AWT Desktop扩展
		if (Desktop.isDesktopSupported()){

			// 获取当前系统桌面扩展
			Desktop desktop = Desktop.getDesktop();

			// 判断系统桌面是否支持要执行的功能
			if (desktop.isSupported(Action.OPEN)){

				// 启动关联应用程序来打开文件
				File file = new File(url);
				try{
					desktop.open(file);
				}catch (IOException e){
					e.printStackTrace();
				}

			}
		}else{
			log.error("don'nt Support Desktop");
		}
	}
}
