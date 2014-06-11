///**
// * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
//import org.w3c.dom.Node;
//
//import com.feilong.commons.core.xml.javaxXml.XmlConfigure;
//
///**
// * 面包屑配置
// * 
// * @author 金鑫 2010-6-17 上午09:45:51
// */
//@Deprecated
//public class SiteMapConfigure{
//
//	private String				xmlPath;
//
//	private XmlConfigure	xmlConfigure;
//
//	/**
//	 * 带xml路径的构造函数
//	 * 
//	 * @param xmlPath
//	 */
//	public SiteMapConfigure(String xmlPath){
//		this.xmlPath = xmlPath;
//		xmlConfigure = new XmlConfigure(xmlPath);
//	}
//
//	/**
//	 * 根据当前的url 获得sitemap 里面的节点
//	 * 
//	 * @param currentUrl
//	 *            当前的url
//	 * @return 根据当前的url 获得sitemap 里面的节点
//	 */
//	public Node getCurrentNode(String currentUrl){
//		return xmlConfigure.getNodeByXPath("//siteMapNode[@url='" + currentUrl + "']");
//	}
//
//	/**
//	 * 生成siteMap内容
//	 * 
//	 * @return 生成siteMap内容
//	 */
//	public String getSiteMapContent(String currentUrl){
//		Node node = getCurrentNode(currentUrl);
//		if (null != node){
////			StringBuilder stringBuilder = new StringBuilder("");
////			HtmlSpanEntity htmlSpanEntity = new HtmlSpanEntity();
////			htmlSpanEntity.setContent(xmlConfigure.getAttributeValue(node, "title"));
////			htmlSpanEntity.setTitle(xmlConfigure.getAttributeValue(node, "description"));
////			stringBuilder.append(HTMLSpan.createSpan(htmlSpanEntity));
////			return stringBuilder.toString();
//		}
//		return "";
//	}
//}
