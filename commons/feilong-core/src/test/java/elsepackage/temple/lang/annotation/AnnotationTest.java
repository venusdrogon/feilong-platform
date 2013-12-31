/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package elsepackage.temple.lang.annotation;

import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;


@MyAnnotation(name = "feilong",sex = 0,loveStrings = { "胡伟立", "三国" })
public class AnnotationTest{

	@MyAnnotation(name = "feilong",sex = 0,loveStrings = { "王菲" })
	public void limei(){}

	@MyAnnotation(name = "金鑫",sex = 1,loveStrings = { "胡伟立", "三国" })
	public void jinxin(){}

	public static void main(String[] args){
		System.out.println(AnnotationTest.class.isAnnotationPresent(MyAnnotation.class));
		MyAnnotation myAnnotation = AnnotationTest.class.getAnnotation(MyAnnotation.class);
		System.out.println(myAnnotation.name());
		// *************************************************************
		Method[] methods = AnnotationTest.class.getDeclaredMethods();
		for (Method method : methods){
			if (method.isAnnotationPresent(MyAnnotation.class)){
				System.out.println("[Test." + method.getName() + "].annotation:");
				MyAnnotation fieldAnnotation = method.getAnnotation(MyAnnotation.class);
				System.out.println(ArrayUtils.toString(fieldAnnotation.loveStrings()));
			}
		}
	}
}
