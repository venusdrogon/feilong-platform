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

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.ArrayUtil;

/**
 * AbstractAspect
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-13 上午1:14:20
 */
public abstract class AbstractAspect{

	private static final Logger	log	= LoggerFactory.getLogger(AbstractAspect.class);

	/**
	 * 获得运行的annotaion
	 * 
	 * @param joinPoint
	 * @param annotationClass
	 * @return
	 */
	protected Annotation getAnnotation(JoinPoint joinPoint,Class<? extends Annotation> annotationClass){
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		Target _target = annotationClass.getAnnotation(Target.class);
		Annotation annotation = null;
		// 如果当前代理的接口方法有这个annotaion,那么直接返回这个annotation
		if (method.isAnnotationPresent(annotationClass)){
			annotation = method.getAnnotation(annotationClass);
		}else{
			try{
				Object target = joinPoint.getTarget();
				// 运行期间 实现类的 类型
				Class<? extends Object> targetClass = target.getClass();
				String methodName = method.getName();
				Class<?>[] parameterTypes = method.getParameterTypes();
				method = targetClass.getMethod(methodName, parameterTypes);
				ElementType[] elementTypes = _target.value();
				// 是否支持方法级别ElementType.METHOD的annotation
				boolean isMethodAnnotation = ArrayUtil.isContain(elementTypes, ElementType.METHOD);
				// 是否支持 类型级别ElementType.TYPE的annotation
				boolean isTypeAnnotation = ArrayUtil.isContain(elementTypes, ElementType.TYPE);
				if (isMethodAnnotation){
					if (method.isAnnotationPresent(annotationClass)){
						annotation = method.getAnnotation(annotationClass);
					}
				}else if (isTypeAnnotation){
					annotation = targetClass.getAnnotation(annotationClass);
					if (null != annotation){
						//
					}
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return annotation;
	}

	protected Method getMethod(JoinPoint joinPoint,Class<? extends Annotation> clazz){
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		if (method.isAnnotationPresent(clazz)){
			return method;
		}
		Target annotation = clazz.getAnnotation(Target.class);
		ElementType[] value = annotation.value();
		try{
			Object target = joinPoint.getTarget();
			Class<? extends Object> targetClass = target.getClass();
			String methodName = method.getName();
			Class<?>[] parameterTypes = method.getParameterTypes();
			Method m1 = targetClass.getMethod(methodName, parameterTypes);
			if (m1.isAnnotationPresent(clazz)){
				return m1;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		throw new RuntimeException("No Proper annotation found.");
	}

	protected boolean isAnnotationPresent(JoinPoint joinPoint,Class<? extends Annotation> clazz){
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		if (method.isAnnotationPresent(clazz)){
			return true;
		}
		Target annotation = clazz.getAnnotation(Target.class);
		ElementType[] value = annotation.value();
		try{
			Object target = joinPoint.getTarget();
			Class<? extends Object> targetClass = target.getClass();
			String methodName = method.getName();
			Class<?>[] parameterTypes = method.getParameterTypes();
			Method m1 = targetClass.getMethod(methodName, parameterTypes);
			if (m1.isAnnotationPresent(clazz)){
				return true;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	// private boolean hasLogAnnotation(MethodSignature signature){
	// Method method = signature.getMethod();
	// Class<?> declaringClass = method.getDeclaringClass();
	// // log.info(declaringClass.getName());
	// _log = declaringClass.getAnnotation(Log.class);
	// if (null != _log){
	// log.debug("method:{},declaringClass has @Log", method.getName());
	// }else{
	// _log = method.getAnnotation(Log.class);
	// if (null != _log){
	// log.debug("method:{},has @Log", method.getName());
	// }
	// }
	// return null != _log;
	// }
	// @After(value = "pointcut()")
	// public void after(JoinPoint joinPoint){
	//
	// }
}
