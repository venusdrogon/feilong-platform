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

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认值测试.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月23日 下午10:49:31
 * @since 1.0.7
 */
public class DefaultValueTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(DefaultValueTest.class);

	//0
	/** The i. */
	private int					i;

	//0
	/** The l. */
	private long				l;

	//0.0
	/** The f. */
	private float				f;

	//0.0
	/** The d. */
	private double				d;

	//0
	/** The sh. */
	private short				sh;

	//0
	/** The by. */
	private byte				by;

	//false
	/** The b. */
	private boolean				b;

	//null
	/** The bb. */
	private Boolean				bb;

	//null
	/** The s. */
	private String				s;

	/**
	 * Name.
	 */
	@Test
	public void name(){
		//java.lang.NullPointerException 
		if (bb){
			log.debug("1");
		}else{
			log.debug("2");

		}
	}

	/**
	 * Default value.
	 */
	@Test
	public void defaultValue(){
		Assert.assertEquals(0, i);
		Assert.assertEquals(0, by);
		Assert.assertEquals(0, sh);
		if (log.isDebugEnabled()){
			log.debug("the param f:{}", f);
			log.debug("the param d:{}", d);
		}
		//		Assert.assertEquals(0.0, f);
		//		Assert.assertEquals(0.0, d);
		Assert.assertEquals(false, b);
		Assert.assertEquals(null, s);
		Assert.assertEquals(0, l);
		Assert.assertEquals(null, bb);
	}
}
