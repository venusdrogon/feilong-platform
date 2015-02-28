package com.feilong.commons.core.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.bean.command.Member;
import com.feilong.commons.core.bean.command.MemberAddress;
import com.feilong.commons.core.bean.command.SalesOrder;
import com.feilong.commons.core.bean.command.SalesOrderDto;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.DatePattern;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.test.User;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-15 上午10:45:34
 */
@SuppressWarnings("all")
public class BeanUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(BeanUtilTest.class);

	private SalesOrder			salesOrder;

	@Before
	public void init(){
		salesOrder = new SalesOrder();
		salesOrder.setCreateTime(new Date());
		salesOrder.setCode("258415-002");
		salesOrder.setId(5L);
		salesOrder.setPrice(new BigDecimal(566));

		Member member = new Member();
		member.setCode("222222");
		long memberId = 888L;
		member.setId(memberId);
		member.setLoginName("feilong");

		Map<String, String> loveMap = new HashMap<>();
		loveMap.put("蜀国", "赵子龙");
		loveMap.put("魏国", "张文远");
		loveMap.put("吴国", "甘兴霸");
		member.setLoveMap(loveMap);

		MemberAddress memberAddress1 = new MemberAddress();
		memberAddress1.setAddress("上海市宝山区真大路333弄22号1503室");
		memberAddress1.setAddTime(DateUtil.string2Date("20140615", DatePattern.yyyyMMdd));
		memberAddress1.setId(1L);
		memberAddress1.setMemberId(memberId);

		MemberAddress memberAddress2 = new MemberAddress();
		memberAddress2.setAddress("上海市闸北区阳城路280弄25号802室(阳城贵都)");
		memberAddress2.setAddTime(DateUtil.string2Date("20101001", DatePattern.yyyyMMdd));
		memberAddress2.setId(1L);
		memberAddress2.setMemberId(memberId);

		MemberAddress[] memberAddresses = { memberAddress1, memberAddress2 };
		member.setMemberAddresses(memberAddresses);

		salesOrder.setMember(member);
	}

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

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(b));
		}

	}

	@Test
	public void copyProperties1(){
		SalesOrderDto salesOrderDto = new SalesOrderDto();

		//ConvertUtils.register(new DateLocaleConverter(Locale.US, DatePattern.forToString), Date.class);
		BeanUtil.copyProperties(salesOrderDto, salesOrder);

		if (log.isDebugEnabled()){
			log.debug("salesOrderDto:{}", JsonUtil.format(salesOrderDto));
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

	@Test
	public void cloneBean(){
		SalesOrder salesOrder1 = BeanUtil.cloneBean(salesOrder);

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(salesOrder1));
		}
	}
}
