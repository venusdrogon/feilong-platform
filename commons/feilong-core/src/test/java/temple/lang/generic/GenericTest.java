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
package temple.lang.generic;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/**
 * 泛型.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-6-22 下午03:45:36
 * @since 1.0
 */
public class GenericTest{

	/**
	 * Gets the value.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param a
	 *            the a
	 * @param clz
	 *            the clz
	 * @return the value
	 */
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

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args){
		String aString = getValue("jinxin", String.class);
		Integer b = getValue("jinxin", Integer.class);
	}
}
