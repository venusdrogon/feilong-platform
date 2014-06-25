package code;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 3, 2013 5:14:45 PM
 */
@SuppressWarnings("all")public class CodeGeneratorTest{

	private static final Logger	log	= LoggerFactory.getLogger(CodeGeneratorTest.class);

	/**
	 * Test method for {@link code.CodeGenerator.shop.utils.SalesOrderCodeGenerator#createOrderCode(java.lang.Long)}.
	 */
	@Test
	public void createOrderCode(){
		// log.info(CodeGenerator.createOrderCode(111121L));
		// log.info(CodeGenerator.createOrderCode(1L));
		// log.info(CodeGenerator.createOrderCode(10L));
		// log.info(CodeGenerator.createOrderCode(101L));
		// log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2013-01-01 00:08:02", DatePattern.commonWithTime), 1L));
		// log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2020-11-11 12:31:23", DatePattern.commonWithTime), 111141L));
		// log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2099-12-31 23:59:45", DatePattern.commonWithTime), 1161L));
		// log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2035-04-22 08:31:35", DatePattern.commonWithTime), 203881L));
		// log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2044-12-31 10:21:46", DatePattern.commonWithTime), 35191L));
		log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2044-12-31 10:21:46", DatePattern.commonWithTime), 35191L, 555L));
		log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2099-12-31 23:59:45", DatePattern.commonWithTime), 1161L, 5555555L));
	}

	@Test
	public void createOrderCode1(){
		for (int i = 0, j = 100; i < j; ++i){
			log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2044-12-31 10:21:46", DatePattern.commonWithTime), 35191L, 555L));
		}
	}

	@Test
	public void createReturnOrderCode(){
		log.info(CodeGenerator.createReturnOrderCode(111121L, 5555555L));
	}

	@Test
	public void createTradeNo(){
		log.info(CodeGenerator.createTradeNo(5545L, 88));
		log.info(CodeGenerator.createTradeNo(5545L, 1));
	}
}
