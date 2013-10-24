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
package com.feilong.spring.aop;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.util.StringUtil;

/**
 * 日志切面 aspect
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-30 上午2:49:12
 */
@Aspect
public class LogAspect extends AbstractAspect{

	// log4j
	private Logger	log	= Logger.getLogger(LogAspect.class);

	private Log		_log;

	@SuppressWarnings("unused")
	// && @annotation(com.feilong.commons.core.aop.Log)
	@Pointcut("execution(* com.feilong.spring.aspects.**.*(..))")
	// @Pointcut("execution(* @annotation(com.feilong.commons.core.aop.Log))")
	private void pointcut(){}

	// com.feilong.spring.aspects.UserManager
	@Around(value = "pointcut()")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable{
		// LoggerFactory.
		// log.
		Method method = getMethod(joinPoint, Log.class);
		String methodName = method.getName();
		Date begin = new Date();
		// 通过反射执行目标对象的连接点处的方法
		joinPoint.proceed();
		// 在来得到方法名吧，就是通知所要织入目标对象中的方法名称
		Date end = new Date();
		Object[] args = joinPoint.getArgs();
		// for (Object arg : args){
		// log.info("arg:{}", arg.toString());
		// }
		// log.info("{},{}", begin, end);

		_log = (Log) getAnnotation(joinPoint, Log.class);
		String level = _log.level();
		// log.debug("level:{}", level);
		// 输出的日志 怪怪的 02:13:10 INFO (NativeMethodAccessorImpl.java:?) [invoke0()] method:addUser([1018, Jummy]),耗时:0
		// ReflectUtil.invokeMethod(log, level, "method:{}({}),耗时:{}", objects);

		String format = "method:%s(%s),耗时:%s";
		Object[] objects = { methodName, args, DateUtil.getIntervalForView(begin, end) };
		Object message = StringUtil.format(format, objects);
		log.log(Level.toLevel(level), message);
	}
}
