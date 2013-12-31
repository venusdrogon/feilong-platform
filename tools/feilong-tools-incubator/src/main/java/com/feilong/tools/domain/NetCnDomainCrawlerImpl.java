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
package com.feilong.tools.domain;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.jsoup.JsoupUtil;

/**
 * www.net.cn 万网抓取
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-26 上午12:36:58
 */
public class NetCnDomainCrawlerImpl extends AbstractDomainCrawler{

	/** The Constant log. */
	private static final Logger	log			= LoggerFactory.getLogger(NetCnDomainCrawlerImpl.class);

	/** The url_format. */
	private String				url_format	= "http://pandavip.www.net.cn/check/check_ac1.cgi/has_client/buy/buy/buy1.asp?packageid=2a&chaxunyuming=1&domain=%s";

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.jsoup.AbstractDomainCrawler#hasRegister(java.lang.String)
	 */
	public boolean hasRegister(String domain){
		String fullRequestUrl = StringUtil.format(url_format, domain);
		log.debug("the param fullRequestUrl value is :{}", fullRequestUrl);
		Document document = JsoupUtil.getDocument(fullRequestUrl);
		if (null != document){
			String requestResult = document.text();
			// log.debug("the request result is :{}", requestResult);
			// 去除前面的括号
			String substring = StringUtil.substring(requestResult, 2);
			// log.info(substring);
			// 去除后面的
			String substringWithoutLast = StringUtil.substringWithoutLast(substring, 3);
			log.info("substringWithoutLast:{}", substringWithoutLast);
			String[] split = substringWithoutLast.trim().split("\\|");
			if (Validator.isNullOrEmpty(split) || split.length < 3){
				log.error("split:{}", split);
			}else{
				try{
					String resultValue = split[3];
					log.info("{}:{}", split[1], resultValue);
					String ok = "Domain name is available";
					if (resultValue.equals(ok)){
						return false;
					}else{// Domain name is not available
					}
				}catch (Exception e){
					log.error("{} is error,{}", fullRequestUrl, e.getMessage());
				}
			}
		}else{
			log.error("{} is error", fullRequestUrl);
		}
		return true;
	}
}
