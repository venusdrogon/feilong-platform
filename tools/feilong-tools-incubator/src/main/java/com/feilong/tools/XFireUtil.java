/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.tools;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

/**
 * xfire web service类
 * 
 * @author <a href="venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-9-21 下午02:05:21
 */
public class XFireUtil{

	/**
	 * 获得web服务接口
	 * 
	 * @param clz
	 *            接口类
	 * @param serviceUrl
	 *            定义远程WebService服务器地址 比如:http://localhost:8080/xfireweb/services/BookService
	 * @return 获得web服务接口
	 * @author 金鑫
	 * @version 1.0 2010-9-21 下午02:08:15
	 */
	public Object getInterface(Class clz,String serviceUrl){
		// 实例化对象工厂
		ObjectServiceFactory objectServiceFactory = new ObjectServiceFactory();
		// 创建服务的元数据,通过服务工厂创建服务类型，只包含外观(接口),创建IBankingService模型 就是服务的元数据
		Service service = objectServiceFactory.create(clz);
		// 创建XFireProxyFactory代理工厂
		XFireProxyFactory xFireProxyFactory = new XFireProxyFactory();
		try{
			// 通过代理工厂创建代理对象,代理对象具有Service的外观，对url地址上的ws进行代理,使用得到的实例，调用相关的方法得到服务的实例
			return xFireProxyFactory.create(service, serviceUrl);
		}catch (MalformedURLException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得调用结果
	 * 
	 * @param wsdl
	 *            wsdl文件路径,比如"http://127.0.0.1:8080/employee/services/test?wsdl"
	 * @param methodName
	 *            执行方法,比如say
	 * @param params
	 *            参数,比如new Object[] { "jinxin", "sss" }
	 * @return 获得调用结果
	 * @author 金鑫
	 * @version 1.0 2010-9-21 下午02:33:18
	 */
	public Object[] getResults(String wsdl,String methodName,Object[] params){
		Object[] results = null;
		try{
			Client client = new Client(new URL(wsdl));
			// 调用特定的Web Service方法
			results = client.invoke(methodName, params);
		}catch (MalformedURLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return results;
	}
}
