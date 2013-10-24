package com.feilong.tools.middleware;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.middleware.MoneyUtil;

public class MoneyTest{

	private static final Logger	log	= LoggerFactory.getLogger(MoneyTest.class);

	/**
	 * 人民币转成大写测试 Debug: hangeToBig(100203.04)=壹拾零贰佰零叁圆零角肆分
	 */
	@Test
	public void testtoRMB(){
		double value = 100203.04;
		log.info(MoneyUtil.convertMoneyToChineseMoney(value));
	}
}
