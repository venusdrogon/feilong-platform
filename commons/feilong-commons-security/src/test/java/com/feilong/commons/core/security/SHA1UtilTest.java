/**
 * 
 */
package com.feilong.commons.core.security;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.util.StringUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-7 上午01:12:36
 */
public class SHA1UtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(SHA1UtilTest.class);

	/**
	 * {@link com.feilong.commons.core.security.MD5Util#encodeFile(java.lang.String)} 的测试方法。
	 */
	@Test
	public void encodeFile(){
		log.debug(SHA1Util.encodeFile("E:/Data/java/Taglib/qqwrffy/qqwrffy 20100105/QQWry.dat"));
	}

	@Test
	public void encode(){
		// Assert.assertEquals("bbed833d2c7805c4bf039b140bec7e7452125a04efa9e0b296395a9b95c2d44c", SHAUtil.encode("passwordhunter"));
		byte[] bytes = StringUtil.toBytes("你好", CharsetType.UTF8);
		Assert.assertEquals("440ee0853ad1e99f962b63e459ef992d7c211722", SHA1Util.encode(bytes));
	}

	@Test
	public void encode1(){
		log.debug(SHA1Util.encode("521000"));
		log.debug(SHA1Util.encode("2" + "5000" + "200000" + "nikejdistore"));
	}
}
