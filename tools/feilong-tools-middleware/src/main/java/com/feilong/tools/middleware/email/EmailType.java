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
package com.feilong.tools.middleware.email;

/**
 * 邮箱 枚举
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-3-22 上午11:54:50
 * @since 1.0
 */
public enum EmailType{
	// -----------------------------------网易--------------------------------------------
	/**
	 * "网易163邮箱","http://mail.163.com/","163.com"
	 */
	email163("网易163邮箱","http://mail.163.com/","163.com"),
	/**
	 * "网易126邮箱","http://www.126.com/","126.com"
	 */
	email126("网易126邮箱","http://www.126.com/","126.com"),
	/**
	 * "网易vip邮箱","http://vip.163.com/","vip.163.com"
	 */
	email163Vip("网易vip邮箱","http://vip.163.com/","vip.163.com"),
	/**
	 * "网易yeah邮箱","http://www.yeah.net/","yeah.net
	 */
	emailYeah("网易yeah邮箱","http://www.yeah.net/","yeah.net"),
	/**
	 * "188财富邮","http://www.188.com/","188.com"
	 */
	email188("188财富邮","http://www.188.com/","188.com"),
	// ---------------------------------yahoo----------------------------------------
	/**
	 * "雅虎邮箱","http://mail.cn.yahoo.com/","yahoo.cn"
	 */
	emailYahoo("雅虎邮箱","http://mail.cn.yahoo.com/","yahoo.cn"),
	/**
	 * "雅虎邮箱","http://mail.cn.yahoo.com/","yahoo.com.cn"
	 */
	emailYahooComcn("雅虎邮箱","http://mail.cn.yahoo.com/","yahoo.com.cn"),
	// -----------------------------sina----------------------------------------
	/**
	 * "新浪免费邮箱","http://mail.sina.com.cn/","sina.com"
	 */
	emailSina("新浪免费邮箱","http://mail.sina.com.cn/","sina.com"),
	/**
	 * "新浪vip邮箱","http://mail.sina.com.cn/","vip.sina.com"
	 */
	emailSinaVip("新浪vip邮箱","http://mail.sina.com.cn/","vip.sina.com"),
	// -----------------------------sohu----------------------------------------
	/**
	 * "搜狐免费邮箱","http://mail.sohu.com/","sohu.com"
	 */
	emailSohu("搜狐免费邮箱","http://mail.sohu.com/","sohu.com"),
	/**
	 * "搜狐vip邮箱","http://mail.sohu.com/","vip.sohu.com"
	 */
	emailSohuVip("搜狐vip邮箱","http://mail.sohu.com/","vip.sohu.com"),
	// ---------------------------------QQ---------------------------------
	/**
	 * "QQ邮箱","http://mail.qq.com/","qq.com"
	 */
	emailQQ("QQ邮箱","http://mail.qq.com/","qq.com"),
	/**
	 * "QQ vip 邮箱","http://mail.qq.com/","vip.qq.com"
	 */
	emailQQVip("QQ vip 邮箱","http://mail.qq.com/","vip.qq.com"),
	// ---------------------------------Tom---------------------------------
	/**
	 * "TOM免费邮箱","http://mail.tom.com/","tom.com"
	 */
	emailTom("TOM免费邮箱","http://mail.tom.com/","tom.com"),
	/**
	 * "Tom vip邮箱","http://vip.tom.com/","163.net"
	 */
	email163Net("Tom vip邮箱","http://vip.tom.com/","163.net"),
	// ---------------------------------else----------------------------------
	/**
	 * "电信189邮箱","http://www.189.cn/webmail/","189.com"
	 */
	email189("电信189邮箱","http://www.189.cn/webmail/","189.com"),
	/**
	 * "谷歌邮箱","http://mail.google.com","gmail.com"
	 */
	emailGamil("谷歌邮箱","http://mail.google.com","gmail.com"),
	/**
	 * "hotmail 邮箱","http://www.hotmail.com","hotmail.com"
	 */
	emailHotmail("hotmail 邮箱","http://www.hotmail.com","hotmail.com"),
	/**
	 * "21cn免费邮箱","http://mail.21cn.com/","21cn.com"
	 */
	email21cn("21cn免费邮箱","http://mail.21cn.com/","21cn.com"),
	/**
	 * "foxmail邮箱","http://mail.qq.com/","foxmail.com"
	 */
	emailFoxmail("foxmail邮箱","http://mail.qq.com/","foxmail.com");

	// -------------------------------------------------------------------------
	/**
	 * 通过 postfix 获得EmailType
	 * 
	 * @param postfix
	 * @return EmailType
	 */
	public static EmailType getEmailTypeByPostfix(String postfix){
		if (null != postfix){
			for (EmailType _emailType : EmailType.values()){
				if (postfix.equals(_emailType.getPostfix())){
					return _emailType;
				}
			}
		}
		return null;
	}

	// ***********************************************************************
	// 邮箱中文名
	private String	chineseName;

	// 网址
	private String	website;

	// 后缀
	private String	postfix;

	private EmailType(String chineseName, String website, String postfix){
		this.chineseName = chineseName;
		this.website = website;
		this.postfix = postfix;
	}

	/**
	 * 邮箱中文名
	 * 
	 * @return the chineseName
	 */
	public String getChineseName(){
		return chineseName;
	}

	/**
	 * 邮箱中文名
	 * 
	 * @param chineseName
	 *            the chineseName to set
	 */
	public void setChineseName(String chineseName){
		this.chineseName = chineseName;
	}

	/**
	 * 网址
	 * 
	 * @return the website
	 */
	public String getWebsite(){
		return website;
	}

	/**
	 * 网址
	 * 
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website){
		this.website = website;
	}

	/**
	 * 后缀
	 * 
	 * @return the postfix
	 */
	public String getPostfix(){
		return postfix;
	}

	/**
	 * 后缀
	 * 
	 * @param postfix
	 *            the postfix to set
	 */
	public void setPostfix(String postfix){
		this.postfix = postfix;
	}
}
