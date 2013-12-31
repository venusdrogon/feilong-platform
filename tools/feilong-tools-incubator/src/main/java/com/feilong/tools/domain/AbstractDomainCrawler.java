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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.PinYinUtil;

/**
 * AbstractDomainCrawler
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-26 下午7:07:37
 */
public abstract class AbstractDomainCrawler implements DomainCrawler{

	private static final Logger	log	= LoggerFactory.getLogger(AbstractDomainCrawler.class);

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.jsoup.DomainCrawler#hasRegister(java.lang.String)
	 */
	@Override
	public abstract boolean hasRegister(String domain);

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.jsoup.DomainCrawler#hasRegister(java.lang.String[])
	 */
	@Override
	public List<String> hasRegister(String[] domains){
		List<String> notRegisterList = new ArrayList<String>();
		for (String domain : domains){
			boolean flag = hasRegister(domain);
			if (!flag){
				notRegisterList.add(domain);
			}
		}
		return notRegisterList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.jsoup.DomainCrawler#hasRegister(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean hasRegister(String domainBody,String domainSuffix){
		String domain = domainBody + "." + domainSuffix;
		return hasRegister(domain);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.jsoup.DomainCrawler#hasRegister(java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public List<String> hasRegister(String domainBodyPrefix,String[] domainBodySuffixes,String domainSuffix){
		String[] domainBodyPrefixes = { domainBodyPrefix };
		return hasRegister(domainBodyPrefixes, domainBodySuffixes, domainSuffix);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.jsoup.DomainCrawler#hasRegister(java.lang.String[], java.lang.String[], java.lang.String)
	 */
	@Override
	public List<String> hasRegister(String[] domainBodyPrefixes,String[] domainBodySuffixes,String domainSuffix){
		String[] domainSuffixes = { domainSuffix };
		return hasRegister(domainBodyPrefixes, domainBodySuffixes, domainSuffixes);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.jsoup.DomainCrawler#hasRegister(java.lang.String[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public List<String> hasRegister(String[] domainBodyPrefixes,String[] domainBodySuffixes,String[] domainSuffixes){
		int length_domainBodyPrefixes = domainBodyPrefixes.length;
		log.debug("the param domainBodyPrefixes.length:{}", length_domainBodyPrefixes);
		// ************************
		int length_domainBodySuffixes = domainBodySuffixes.length;
		log.debug("the param domainBodySuffixes.length:{}", length_domainBodySuffixes);
		// ************************
		int length_domainSuffixes = domainSuffixes.length;
		log.debug("the param domainSuffixes.length:{}", length_domainBodySuffixes);
		// ************************
		int length = length_domainBodySuffixes * length_domainBodyPrefixes * length_domainSuffixes;
		String[] domains = new String[length];
		StringBuilder builder = null;
		int z = 0;
		for (int i = 0; i < length_domainBodyPrefixes; ++i){
			for (int j = 0; j < length_domainBodySuffixes; ++j){
				for (int k = 0; k < length_domainSuffixes; ++k){
					builder = new StringBuilder();
					builder.append(domainBodyPrefixes[i]);
					builder.append(domainBodySuffixes[j]);
					builder.append(".");
					builder.append(domainSuffixes[k]);
					log.debug("the param domains[{}]:{}", z, builder.toString());
					domains[z] = builder.toString();
					++z;
				}
			}
		}
		return hasRegister(domains);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.jsoup.DomainCrawler#hasRegisterChineseConvert(java.lang.String[], java.lang.String)
	 */
	@Override
	public List<String> hasRegisterChineseConvert(String[] chineseDomainBodys,String domainSuffix){
		int length = chineseDomainBodys.length;
		log.debug("the param chineseDomainBodys.length:{}", length);
		String[] domains = new String[length];
		StringBuilder builder = null;
		for (int i = 0; i < length; ++i){
			builder = new StringBuilder();
			String convertChineseToPinYin = PinYinUtil.convertChineseToPinYin(chineseDomainBodys[i]);
			builder.append(convertChineseToPinYin);
			builder.append(".");
			builder.append(domainSuffix);
			domains[i] = builder.toString();
		}
		return hasRegister(domains);
	}
}
