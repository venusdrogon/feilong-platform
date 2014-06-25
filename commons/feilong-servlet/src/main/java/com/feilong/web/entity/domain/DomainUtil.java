/**
 * Copyright (c) 2008-2012 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.web.entity.domain;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;

/**
 * 二级域名 工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-8-9 下午4:53:55
 */
public final class DomainUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log				= LoggerFactory.getLogger(DomainUtil.class);

	private static final String	userHome		= System.getProperty("user.home");

	private static   String	customDirectory	= System.getProperty("org.owasp.esapi.resources");

//	/**
//	 * {@inheritDoc}
//	 */
//	public File getResourceFile(String filename) {
//		log.info("Attempting to load " + filename + " as resource file via file I/O.");
//		if (filename == null) {
//			log.info("Failed to load properties via FileIO. Filename is null.");
//			return null; // not found.
//		}
//		File f = null;
//		// first, allow command line overrides. -Dorg.owasp.esapi.resources
//		// directory
//		f = new File(customDirectory, filename);
//		if (customDirectory != null && f.canRead()) {
//			log.info("Found in 'org.owasp.esapi.resources' directory: " + f.getAbsolutePath());
//			return f;
//		} else {
//			log.info("Not found in 'org.owasp.esapi.resources' directory or file not readable: " + f.getAbsolutePath());
//		}
//		// if not found, then try the programmatically set resource directory
//		// (this defaults to SystemResource directory/RESOURCE_FILE
//		URL fileUrl = ClassLoader.getSystemResource(resourceDirectory + "/" + filename);
//        if ( fileUrl == null ) {
//            fileUrl = ClassLoader.getSystemResource("esapi/" + filename);
//        }
//		if (fileUrl != null) {
//			String fileLocation = fileUrl.getFile();
//			f = new File(fileLocation);
//			if (f.exists()) {
//				log.info("Found in SystemResource Directory/resourceDirectory: " + f.getAbsolutePath());
//				return f;
//			} else {
//				log.info("Not found in SystemResource Directory/resourceDirectory (this should never happen): " + f.getAbsolutePath());
//			}
//		} else {
//			log.info("Not found in SystemResource Directory/resourceDirectory: " + resourceDirectory + File.separator + filename);
//		}
//		// If not found, then try immediately under user's home directory first in
//		//        userHome + "/.esapi"        and secondly under
//		//        userHome + "/esapi"
//		// We look in that order because of backward compatibility issues.
//		String homeDir = userHome;
//		if ( homeDir == null ) {
//			homeDir = "";    // Without this,    homeDir + "/.esapi"    would produce
//							// the string        "null/.esapi"        which surely is not intended.
//		}
//		// First look under ".esapi" (for reasons of backward compatibility).
//		f = new File(homeDir + "/.esapi", filename);
//		if ( f.canRead() ) {
//			log.info("[Compatibility] Found in 'user.home' directory: " + f.getAbsolutePath());
//			return f;
//		} else {
//			// Didn't find it under old directory ".esapi" so now look under the "esapi" directory.
//			f = new File(homeDir + "/esapi", filename);
//			if ( f.canRead() ) {
//				log.info("Found in 'user.home' directory: " + f.getAbsolutePath());
//				return f;
//			} else {
//				log.info("Not found in 'user.home' (" + homeDir + ") directory: " + f.getAbsolutePath());
//			}
//		}
//		// return null if not found
//		return null;
//	}

	/**
	 * 获得二级域名 ,要是Validator.isNull( **),返回 contextPath
	 * 
	 * @param request
	 *            the request
	 * @param domainType
	 *            the domain type
	 * @return the domain
	 */
	public static String getDomain(HttpServletRequest request,DomainType domainType){
		ServletContext servletContext = request.getSession().getServletContext();
		return getDomain(servletContext, domainType);
	}

	/**
	 * 获得二级域名 ,要是Validator.isNull( **),返回 contextPath
	 * 
	 * @param servletContext
	 *            the servlet context
	 * @param domainType
	 *            the domain type
	 * @return the domain
	 */
	public static String getDomain(ServletContext servletContext,DomainType domainType){
		String domain = getDomain(domainType);
		if (Validator.isNullOrEmpty(domain)){
			String contextPath = servletContext.getContextPath();
			domain = contextPath;
		}
		return domain;
	}

	/**
	 * 直接读取配置的domainType,用于调度等非 Web环境<br>
	 * .
	 * 
	 * @param domainType
	 *            the domain type
	 * @return the domain
	 */
	public static String getDomain(DomainType domainType){
		String domain = null;

		switch (domainType) {

			case CSS:// css

				domain = DomainConstants.DOMAIN_CSS;
				break;
			case JS:// js
				domain = DomainConstants.DOMAIN_JS;
				break;
			case IMAGE:// 图片
				domain = DomainConstants.DOMAIN_IMAGE;
				break;
			case RESOURCE:// PDP商品图片
				domain = DomainConstants.DOMAIN_RESOURCE;
				break;
			case STORE:// 商城的网址，一般用于不同环境 第三方数据传递 比如微博等
				domain = DomainConstants.DOMAIN_STORE;
				break;
			default:
				throw new IllegalArgumentException("domainType can't support!");
		}
		return domain;
	}
}
