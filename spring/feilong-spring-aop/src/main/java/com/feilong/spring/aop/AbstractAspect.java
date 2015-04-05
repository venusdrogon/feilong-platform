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
package com.feilong.spring.aop;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.commons.core.util.Validator;

/**
 * {@link org.aspectj.lang.annotation.Aspect}相关父类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-13 上午1:14:20
 * @version 1.1.1 add implements Ordered
 * @see org.springframework.core.Ordered
 */
public abstract class AbstractAspect implements Ordered{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(AbstractAspect.class);

    /**
     * 获得运行的annotaion.
     *
     * @param <T>
     *            the generic type
     * @param joinPoint
     *            the join point
     * @param annotationClass
     *            the annotation class
     * @return the annotation
     */
    protected <T extends Annotation> T getAnnotation(JoinPoint joinPoint,Class<T> annotationClass){
        if (Validator.isNullOrEmpty(joinPoint)){
            throw new NullPointerException("joinPoint can't be null/empty!");
        }
        if (Validator.isNullOrEmpty(annotationClass)){
            throw new NullPointerException("annotationClass can't be null/empty!");
        }

        T annotation = null;

        Target annotationTarget = annotationClass.getAnnotation(Target.class);
        ElementType[] elementTypes = annotationTarget.value();

        // ElementType.METHOD
        // 是否支持方法级别ElementType.METHOD的annotation
        boolean isMethodAnnotation = ArrayUtil.isContain(elementTypes, ElementType.METHOD);
        if (isMethodAnnotation){

            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();

            // 如果当前代理的接口方法有这个annotaion,那么直接返回这个annotation
            if (method.isAnnotationPresent(annotationClass)){
                annotation = method.getAnnotation(annotationClass);
            }
        }

        // ElementType.TYPE
        if (null == annotation){
            // 是否支持 类型级别ElementType.TYPE的annotation
            boolean isTypeAnnotation = ArrayUtil.isContain(elementTypes, ElementType.TYPE);
            if (isTypeAnnotation){
                Object target = joinPoint.getTarget();
                // 运行期间 实现类的 类型
                Class<?> targetClass = target.getClass();
                annotation = targetClass.getAnnotation(annotationClass);
            }
        }

        // String methodName = method.getName();
        // Class<?>[] parameterTypes = method.getParameterTypes();
        // method = targetClass.getMethod(methodName, parameterTypes);

        return annotation;
    }

    /**
     * 获得 method.
     * 
     * @param joinPoint
     *            the join point
     * @param klass
     *            the clazz
     * @return the method
     * @deprecated 目前作用不大,将来会重构
     */
    @Deprecated
    protected Method getMethod(JoinPoint joinPoint,Class<? extends Annotation> klass){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(klass)){
            return method;
        }
        Target annotation = klass.getAnnotation(Target.class);
        ElementType[] value = annotation.value();
        try{
            Object target = joinPoint.getTarget();
            Class<? extends Object> targetClass = target.getClass();
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            Method m1 = targetClass.getMethod(methodName, parameterTypes);
            if (m1.isAnnotationPresent(klass)){
                return m1;
            }
        }catch (Exception e){
            log.error(e.getClass().getName(), e);
        }
        throw new RuntimeException("No Proper annotation found.");
    }

    /**
     * Checks if is annotation present.
     * 
     * @param joinPoint
     *            the join point
     * @param clazz
     *            the clazz
     * @return true, if checks if is annotation present
     * @deprecated 目前作用不大,将来会重构
     */
    @Deprecated
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
            log.error(e.getClass().getName(), e);
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

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.core.Ordered#getOrder()
     */
    @Override
    public int getOrder(){
        return 0;
    }
}
