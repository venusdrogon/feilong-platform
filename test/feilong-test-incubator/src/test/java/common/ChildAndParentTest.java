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

import com.feilong.childandparent.Child;
import com.feilong.childandparent.Parent;
import com.feilong.commons.core.bean.BeanUtil;
import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class ChildAndParentTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-9-16 上午11:13:41
 * @since 1.0
 */
public class ChildAndParentTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ChildAndParentTest.class);

	/**
	 * Name.
	 */
	@Test
	public void name(){

		Parent parent = new Parent();
		//In the code, by inherting from parent, you're saying "Child is a kind of Parent". 
		//Okay, sounds weird due to the names, but perfectly acceptable. 
		//
		//Then with the "child c = (child)p" cast, you are, in essence, telling the compiler that "Parent is a kind of Child". 
		//It's not. The inference is backwards.
		//
		//As previously stated, I would re-work the class diagram if you need something like this to work.
		// It sounds like some sort of mistake was made in the inheritance architecture somewhere.
		//If all you need is for both child and parent to implement common methods so that when either of the objects is passed to some function it can operate on some common methods on the passed-in object (holy polymorphism, Batman!), 
		//then just have the two of them implement the same interface, or type the function parameter as the base class and make sure the methods exist on the base class. 
		//parent = new Child();
		parent.setName("飞天奔月");

		Child child = new Child();
		BeanUtil.copyProperties(child, parent);

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(child));
		}
	}

	/**
	 * Testenclosing_type.
	 */
	@Test
	public void testenclosing_type(){
		Child child = new Child();
		//assertEquals(expected, actual);
	}
}
