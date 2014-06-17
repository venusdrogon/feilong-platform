package com.feilong.commons.core.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.bean.BeanUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.test.User;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-15 上午10:45:34
 */
public class BeanUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(BeanUtilTest.class);

	@Test
	public void copyProperty(){
		User a = new User();
		a.setId(5L);
		a.setMoney(new BigDecimal(500000));
		a.setDate(new Date());
		User b = new User();
		// DateConverter converter = new DateConverter(DatePattern.forToString, Locale.US);
		ConvertUtils.register(new DateLocaleConverter(Locale.US, DatePattern.forToString), Date.class);

		String[] strs = { "date", "money" };
		BeanUtil.copyProperties(b, a, strs);
		log.info(b.getDate() + "");
		log.info(b.getMoney() + "");
	}

	@Test
	public void copyProperties(){
		User a = new User();
		a.setId(5L);
		a.setDate(new Date());
		String[] nickName = { "feilong", "飞天奔月", "venusdrogon" };
		a.setNickName(nickName);

		User b = new User();

		String[] aStrings = { "date", "id", "nickName" };
		ConvertUtils.register(new DateLocaleConverter(Locale.US, DatePattern.forToString), Date.class);
		BeanUtil.copyProperties(b, a, aStrings);

		for (String str : b.getNickName()){
			log.info(str);
		}
	}

	@Test
	public void describe(){
		User a = new User();
		a.setId(5L);
		Date now = new Date();
		a.setDate(now);
		Map<String, String> map = BeanUtil.describe(a);

		log.info("map:{}", JsonUtil.format(map));
	}

	@Test
	public void populate(){
		User a = new User();
		a.setId(5L);
		Date now = new Date();
		a.setDate(now);
		// DateConverter converter = new DateConverter("yyyy");
		// ConvertUtils.register(converter, Date.class);
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("id", 8L);
		// properties.put("date", 2010);
		BeanUtil.populate(a, properties);
		log.info(JsonUtil.format(a));
	}
}
