///**
// * Copyright © 2008-2014 FeiLong, Inc. All Rights Reserved.
// * <p>
// * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
// * 	You shall not disclose such Confidential Information and shall use it 
// *  only in accordance with the terms of the license agreement you entered into with FeiLong.
// * </p>
// * <p>
// * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
// * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
// * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
// * 	THIS SOFTWARE OR ITS DERIVATIVES.
// * </p>
// */
//package com.feilong.common.configure.feilongConfig;
//
//import javax.servlet.ServletContext;
//
//import com.feilong.commons.core.net.URIUtil;
//import com.feilong.commons.core.util.Validator;
//import com.feilong.commons.core.xml.javaxXml.XmlConfigure;
//
///**
// * 模仿 asp.net 读取配置文件 web.cfg.xml
// * 
// * @author 金鑫 2010-6-8 上午05:39:10
// */
//public class ConfigurationManager{
//
//	// 默认的配置文件路径
//	private String				xmlPath	= "WebRoot/WEB-INF/feiLongConfig/feilongWeb-cfg.xml";
//
//	// Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)
//	/**
//	 * xmlConfigure
//	 */
//	private XmlConfigure	xmlConfigure;
//
//	/**
//	 * 默认的构造函数
//	 */
//	public ConfigurationManager(){
//		getXmlConfigure();
//	}
//
//	/**
//	 * 带xml路径的构造函数
//	 * 
//	 * @param xmlPath
//	 */
//	public ConfigurationManager(String xmlPath){
//		this.xmlPath = xmlPath;
//		getXmlConfigure();
//	}
//
//	/**
//	 * web 环境下面读取
//	 * 
//	 * @param servletContext
//	 *            servletContext
//	 */
//	public ConfigurationManager(ServletContext servletContext){
//		this.xmlPath = servletContext.getRealPath("/WEB-INF/feiLongConfig/feilongWeb-cfg.xml");
//		getXmlConfigure();
//	}
//
//	/**
//	 * 获得当前xml 路径地址
//	 * 
//	 * @return 获得当前xml 路径地址
//	 */
//	public String getCurrentXmlPath(){
//		return this.xmlPath;
//	}
//
//	/**
//	 * 实例化配置文件
//	 */
//	private void getXmlConfigure(){
//		xmlConfigure = new XmlConfigure(xmlPath);
//	}
//
//	/**
//	 * 获得,默认的面包屑提供xml数据源名称
//	 * 
//	 * @return 获得,默认的面包屑提供数据源名称
//	 */
//	public String getDefaultSiteMapProviderName(){
//		return xmlConfigure.getAttributeValueByXPath("//siteMap", "defaultProvider");
//	}
//
//	/**
//	 * 获得默认的面包屑提供xml路径
//	 * 
//	 * @return 获得默认的面包屑提供xml路径
//	 */
//	public String getDefaultSiteMapProviderSiteMapFile(){
//		return getSiteMapProviderSiteMapFile(getDefaultSiteMapProviderName());
//	}
//
//	/**
//	 * 根据面包屑数据源xml名称获得面包屑数据源xml SiteMapFile
//	 * 
//	 * @param providerName
//	 *            面包屑数据源xml名称
//	 * @return 面包屑数据源xml路径
//	 */
//	public String getSiteMapProviderSiteMapFile(String providerName){
//		if (Validator.isNullOrEmpty(providerName)){
//			providerName = getDefaultSiteMapProviderName();
//		}
//		return xmlConfigure.getAttributeValueByXPath("//add[@name='" + providerName + "']", "siteMapFile");
//	}
//
//	/**
//	 * 根据sitemap providerName获得 xml路径
//	 * 
//	 * @param providerName
//	 *            providerName
//	 * @return 根据sitemap providerName获得 xml路径
//	 */
//	public String getSiteMapXmlPath(String providerName){
//		String parent = getCurrentXmlPath();
//		String node = getSiteMapProviderSiteMapFile(providerName);
//		return URIUtil.getUnionUrl(parent, node);
//	}
//}
