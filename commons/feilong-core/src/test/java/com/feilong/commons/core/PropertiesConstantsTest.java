package com.feilong.commons.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.test.User;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-6-1 上午11:50:45
 * @since 1.0
 */
public class PropertiesConstantsTest{

	private static final Logger	log	= LoggerFactory.getLogger(PropertiesConstantsTest.class);

	@Test
	public void test(){
		log.debug(PropertiesConstants.CONFIG_DATE_MINUTE);
	}

	@Test
	public void name(){
		List<User> list = new ArrayList<>();

		User user = new User();
		user.setName("张三");
		list.add(user);

		user = new User();
		user.setName("李四");
		list.add(user);

		user = new User();
		user.setName("李某某");
		list.add(user);

		user = new User();
		user.setName("王五");
		list.add(user);

		Iterator iterator = list.iterator();
		while (iterator.hasNext()){
			User member = (User) iterator.next();
			if ("李某某".equals(member.getName())){
				iterator.remove();
			}
		}

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(list));
		}
	}
}