package com.baidu;
import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;

public class Stu{

	private static final Logger	log	= LoggerFactory.getLogger(Stu.class);

	/**
	 * Testenclosing_type.
	 */
	@Test
	public void testenclosing_type(){

		Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
//		numbers.put("one", null);
//		numbers.put("two", 2);
		numbers.put(null, 3);

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(numbers));
		}
	}

}