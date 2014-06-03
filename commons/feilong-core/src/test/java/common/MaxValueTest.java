/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月23日 下午10:54:46
 * @since 1.0.7
 */
public class MaxValueTest{

	private static final Logger	log	= LoggerFactory.getLogger(MaxValueTest.class);

	/**
	 * Max value.
	 */
	@Test
	public void MAX_VALUE(){

		//**************Byte*******************************
		//127
		log.info("Byte.MAX_VALUE:[{}]", Byte.MAX_VALUE);
		//-128
		log.info("Byte.MIN_VALUE:[{}]", Byte.MIN_VALUE);

		//**************Short*******************************
		//32767
		log.info("Short.MAX_VALUE:[{}]", Short.MAX_VALUE);
		//-32768
		log.info("Short.MIN_VALUE:[{}]", Short.MIN_VALUE);

		//**************Integer*******************************
		//2147483647
		log.info("Integer.MAX_VALUE:[{}]", Integer.MAX_VALUE);
		//-2147483648
		log.info("Integer.MIN_VALUE:[{}]", Integer.MIN_VALUE);

		//**************Long*******************************
		//9223372036854775807
		log.info("Long.MAX_VALUE :[{}]", Long.MAX_VALUE);
		//-9223372036854775808
		log.info("Long.MIN_VALUE :[{}]", Long.MIN_VALUE);

		//**************Float*******************************
		//3.4028235E38
		log.info("Float.MAX_VALUE:[{}]", Float.MAX_VALUE);
		//1.4E-45
		log.info("Float.MIN_VALUE:[{}]", Float.MIN_VALUE);

		//**************Double*******************************
		//1.7976931348623157E308
		log.info("Double.MAX_VALUE:[{}]", Double.MAX_VALUE);
		//4.9E-324
		log.info("Double.MIN_VALUE:[{}]", Double.MIN_VALUE);

		log.info("" + (Byte.MAX_VALUE + 5));//132
		log.info("" + (Integer.MAX_VALUE + 5));//-2147483644
		log.info("" + (Integer.MIN_VALUE - 5));//-2147483643

		//怎么叫内部处理啊啊。看来二进制补码显示都还给老师了啊，这可以大一基础课程啊。java整形32位。
		//从32个0，到32个1这是整形的范围，首位0表示正数，1表示复数。所以最大值就是0111 1111 1111 1111 1111 1111 1111 1111，如果加一，就变成了1000 0000 0000 0000 0000 0000 0000 0000 这一下就天堂到地狱，这个是最小值了（这里有负数的补码运算，不是直接二进制加出来的）。
		//-2^31.你继续加那么就是-2^31+1，这是二进制运算的原理，你加过头了就会溢出，如果你变成long，那么就用最高位去补。
		//但是还有一点，复数补码显示是反码加一，-1 二进制是1000 0000 0000 0000 0000 0000 0000 0001，但是补码是1111 1111 1111 1111 1111 1111 1111 1111 ，
		//你可以log.info(Integer.toBinaryString(-1));打印出来就是这样，另外Int 到Long是最高位往前补位的。
		log.info("" + ((Integer.MAX_VALUE + 1) == Integer.MIN_VALUE));//-2147483643
		log.info("" + ((Integer.MIN_VALUE - 1) == Integer.MAX_VALUE));//-2147483643
		log.info("" + (Long.MAX_VALUE < Float.MAX_VALUE));

	}
}
