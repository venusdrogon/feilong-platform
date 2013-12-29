package com.feilong.tools.domain;

import java.util.List;

/**
 * 域名抓取器,用来验证
 * <ul>
 * <li>单个域名:如feilong.com</li>
 * <li>基于中文单词/子自动解析成英文域名,如:飞龙,自动解析成feilong去验证</li>
 * <li>一组域名是否被注册,如:["feilong.com","feitian.com","feitianbenyue.com"]</li>
 * <li>一个域名前缀+一组域后缀,如: <code>fei+["tian","ben","yue"]</code>,自动循环拼接成feitian,feiben,feiyue,进行验证</li>
 * </ul>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.0 2012-2-23 01:14
 */
public interface DomainCrawler{

	/**
	 * 判断一个域名是否被注册.
	 * 
	 * @param domain
	 *            如:feilong.com
	 * @return 如果被注册,返回true;反之返回false
	 */
	boolean hasRegister(String domain);

	/**
	 * domainBody+domainSuffix 是否被注册.
	 * 
	 * @param domainBody
	 *            如:feilong
	 * @param domainSuffix
	 *            如:com
	 * @return 如果被注册,返回true;反之返回false
	 */
	boolean hasRegister(String domainBody,String domainSuffix);

	/**
	 * 判断一组域名是否可以被注册.
	 * 
	 * @param domains
	 *            比如["feitian.com","feitian.cn"]
	 * @return 可以注册的域名 以List 方式返回
	 */
	List<String> hasRegister(String[] domains);

	/**
	 * 判断某个domainSuffix域名后缀,下面以domainBodyPrefix开头的,一组domainBodySuffixes域名主体 是否被注册.
	 * 
	 * @param domainBodyPrefix
	 *            域名主体开头,比如:fei
	 * @param domainBodySuffixes
	 *            一组域名主体后缀,比如["tian","long"]<br>
	 *            用来自动和domainBodyPrefix 拼接成 feitian,feilong
	 * @param domainSuffix
	 *            域名后缀 比如:com
	 * @return 可以注册的域名 以List 方式返回
	 */
	List<String> hasRegister(String domainBodyPrefix,String[] domainBodySuffixes,String domainSuffix);

	/**
	 * 判断某组domainBodyPrefixes域名后缀,下面以domainBodyPrefix开头的,多维度domainBodySuffixes域名主体 是否被注册.
	 * 
	 * @param domainBodyPrefixes
	 *            the domain body prefixes 比如["i","yi"]
	 * @param domainBodySuffixes
	 *            一组域名主体后缀,比如["tian","long"]<br>
	 *            用来自动和domainBodyPrefixes 拼接成 itian,ilong yitian,yilong
	 * @param domainSuffix
	 *            域名后缀 比如:com
	 * @return 可以注册的域名 以List 方式返回
	 */
	List<String> hasRegister(String[] domainBodyPrefixes,String[] domainBodySuffixes,String domainSuffix);

	/**
	 * 判断某组domainBodyPrefixes域名后缀,下面以domainBodyPrefixes开头的,多维度domainBodySuffixes域名主体 ,多维度domainSuffixes域名主体是否被注册.
	 * 
	 * @param domainBodyPrefixes
	 *            the domain body prefixes 比如["i","yi"]
	 * @param domainBodySuffixes
	 *            一组域名主体后缀,比如["tian","long"]<br>
	 *            用来自动和domainBodyPrefixes 拼接成 itian,ilong yitian,yilong
	 * @param domainSuffixes
	 *            the domain suffixes 域名后缀 比如:["com","cn"]
	 * @return 可以注册的域名 以List 方式返回
	 */
	List<String> hasRegister(String[] domainBodyPrefixes,String[] domainBodySuffixes,String[] domainSuffixes);

	/**
	 * 将一组中文词组,转成汉语拼音,来判断是否被注册<br>
	 * 适用于要求拼音化域名.
	 * 
	 * @param chineseDomainBodys
	 *            比如:["飞龙","飞天"],自动转成 feilong,feitian
	 * @param domainSuffix
	 *            域名后缀 比如:com
	 * @return 可以注册的域名 以List 方式返回
	 */
	List<String> hasRegisterChineseConvert(String[] chineseDomainBodys,String domainSuffix);
}
