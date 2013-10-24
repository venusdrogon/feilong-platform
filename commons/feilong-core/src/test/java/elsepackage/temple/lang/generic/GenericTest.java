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
package elsepackage.temple.lang.generic;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/**
 *泛型
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-6-22 下午03:45:36
 * @since 1.0
 */
public class GenericTest{

	public static <T> T getValue(String a,Class clz){
		System.out.println(clz == String.class);
		T aT = null;
		try{
			Method method = GenericTest.class.getMethod("getValue", String.class, Class.class);
			TypeVariable typeVariable = (TypeVariable) method.getGenericReturnType();
			System.out.println(typeVariable.toString());
			System.out.println(typeVariable.getName());
			System.out.println(typeVariable.getBounds()[0]);
			System.out.println(typeVariable.getGenericDeclaration().toString());
			System.out.println(method.toGenericString());
			System.out.println(method.toString());
		}catch (SecurityException e){
			e.printStackTrace();
		}catch (NoSuchMethodException e){
			e.printStackTrace();
		}
		return aT;
	}

	public static void main(String[] args){
		String aString = getValue("jinxin", String.class);
		Integer b = getValue("jinxin", Integer.class);
	}
}
