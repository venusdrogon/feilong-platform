/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package elsepackage.temp;

import org.junit.Test;

import com.feilong.commons.core.util.BeanUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-9-16 上午11:13:41
 * @since 1.0
 */
public class MyTest{

	public static void main(String[] args){
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
		System.out.println(child.getName());
	}

	@Test
	public void name(){
		System.out.println(SizeOfAgent.fullSizeOf(1));
	}
}
