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
package com.feilong.spring.aspects;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Aspect
// 使用@Aspect之前记得要引入aspectjrt.jar和aspectjweaver.jar
/**
 * The Class MySecurityManagerImpl.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:22:49
 */
public class MySecurityManagerImpl implements MySecurityManager{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(MySecurityManagerImpl.class);

	/**
	 * 定义切入点,切入该方法只是一个标识（而且只作为标识并不是常态下的方法， 并不供人调用所以该方法返回值为void,最好设置为private）,该方法只是一个标识，就象配置文件的id<br>
	 * 切入点的内容是一个表达式,根据这个表达式就可以用来判断切入哪些对象的哪些方法<br>
	 * <br>
	 * 下面对表达式简单说一下: execution(* add*(..))
	 * <ul>
	 * <li>第一个*说匹配是方法的任意返回值类型 待会可以试验一下。</li>
	 * <li>add*就是表示以add开头的方法名都可以匹配想到正则表达式了吗？</li>
	 * <li>(..)这两个就是表示方法里所传递的参数类型也是任意匹配。</li>
	 * </ul>
	 * 具体的请参考spring的开发手册, 这一点Spring讲的比较清楚。
	 * 
	 * @param joinPoint
	 *            the join point
	 */
	// @SuppressWarnings("unused")
	// @Pointcut("execution(* add*(..))")
	// 定义切入点,切入该方法只是一个标识（而且只作为标识并不是常态下的方法， 并不供人调用所以没有返回值最好设置为private）
	// private void addAllMethod(){}
	/**
	 * 定义Advice，标识在哪些切入点<br>
	 * （切入点有ADD这是我们上面通过pointcut定义的。当然你也可以定义别的） 的何处<br>
	 * （何处就是指是切入点的前面呢？后面？还是其他情况）织入通知 <br>
	 * 当然除了before还有其他类型的通知。<br>
	 * 前置通知，在addAllMethod切入点所代表的方法前调用checkSecurity方法
	 */
	// @Before("addAllMethod()")
	@Override
	public void security(JoinPoint joinPoint){
		// 在来得到方法名吧，就是通知所要织入目标对象中的方法名称
		//		String method = joinPoint.getSignature().getName();
		//		log.info("methodName:{}", method);
		//		Object[] args = joinPoint.getArgs();
		//		log.info("begin log args......");
		//		for (Object o : args){
		//			log.info(o.toString());
		//		}
		//		log.info("end log args......");
		//		log.info("@Before -----调用security方法-------");
	}

	/**
	 * 这里也可以添加一些其他的前置方法.
	 */
	// @Before("addAllMethod()")
	public void security2(){
		//log.info("@Before -----调用security方法2-------");
	}
}
