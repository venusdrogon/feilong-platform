/**
 * 
 */
package com.feilong.commons.core.security;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-7 上午01:12:36
 */
public class MD5UtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(MD5UtilTest.class);

	/**
	 * {@link com.feilong.commons.core.security.MD5Util#encodeFile(java.lang.String)} 的测试方法。
	 */
	@Test
	public void encodeFile(){
		log.debug(MD5Util.encodeFile("E:/Data/java/Taglib/qqwrffy/qqwrffy 20100105/QQWry.dat"));
	}

	@Test
	public void encode(){
		Assert.assertEquals("e10adc3949ba59abbe56e057f20f883e", MD5Util.encode("123456"));
		Assert.assertEquals("e8dc4081b13434b45189a720b77b6818", MD5Util.encode("abcdefgh"));
		Assert.assertEquals("06800efdece5a6dfda22174730f24477", MD5Util.encode("www.e-lining.com"));
		Assert.assertEquals("6f7c5c877d2b4ae3384b4daad366c19b", MD5Util.encode("WWW.RICARD.CN"));
		Assert.assertEquals("efa0699c3f05371d32ec5023d9e55ac0", MD5Util.encode("liushuwen@ricard.cn"));
		Assert.assertEquals("5d4db27d92073e85bfb307cf42738308", MD5Util.encode("sfexpress!@#$"));
	}

	@Test
	public void encode1(){
		log.debug(MD5Util.encode("你好"));
	}
}
