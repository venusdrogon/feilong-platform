/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.feilong.core.util.Validator;

/**
 * 二级域名 工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-8-9 下午4:53:55
 * @deprecated
 */
@Deprecated
public final class DomainUtil{

    /** The Constant userHome. */
    private static final String userHome        = System.getProperty("user.home");

    /** The custom directory. */
    private static String       customDirectory = System.getProperty("org.owasp.esapi.resources");

    /** Don't let anyone instantiate this class. */
    private DomainUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    //TODO
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
