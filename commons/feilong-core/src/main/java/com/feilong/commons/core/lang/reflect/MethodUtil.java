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
package com.feilong.commons.core.lang.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.ClassUtil;
import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 使用反射的方式请求bean中的方法.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年7月15日 下午1:08:15
 * @see org.apache.commons.lang3.reflect.MethodUtils
 * @see org.apache.commons.lang3.ClassUtils#getPublicMethod(Class, String, Class...)
 * @since 1.0.7
 */
public final class MethodUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(MethodUtil.class);

    /** Don't let anyone instantiate this class. */
    private MethodUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    // [start]

    /**
     * 执行某对象的某个方法.
     * 
     * @param <T>
     *            the generic type
     * @param obj
     *            对象
     * @param methodName
     *            方法名
     * @param params
     *            参数
     * @return 方法执行之后的结果值
     * @throws ReflectException
     *             如果在执行的过程中出现了异常
     * @see #getMethod(Class, String, Object...)
     * @see java.lang.reflect.Method#invoke(Object, Object...)
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(Object obj,String methodName,Object...params) throws ReflectException{
        try{
            Class<?> ownerClass = obj.getClass();
            Method method = getMethod(ownerClass, methodName, params);

            Object invoke = method.invoke(obj, params);
            return (T) invoke;
        }catch (Exception e){
            throw new ReflectException(e);
        }
    }

    /**
     * 执行静态方法.
     * 
     * @param <T>
     *            the generic type
     * @param className
     *            类名
     * @param methodName
     *            方法名
     * @param params
     *            动态参数
     * @return 方法执行之后的结果值
     * @throws ReflectException
     *             the reflect exception
     * @see #getMethod(Class, String, Object...)
     * @see java.lang.reflect.Method#invoke(Object, Object...)
     * @see org.apache.commons.lang3.reflect.MethodUtils#invokeStaticMethod(Class, String, Object...)
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeStaticMethod(String className,String methodName,Object...params) throws ReflectException{
        try{
            Class<?> ownerClass = ClassUtil.loadClass(className);
            Method method = getMethod(ownerClass, methodName, params);

            int modifiers = method.getModifiers();
            boolean isStatic = Modifier.isStatic(modifiers);

            if (!isStatic){
                throw new IllegalArgumentException(
                                Slf4jUtil.formatMessage(
                                                "className:[{}],methodName:[{}],params:[{}],modifiers:[{}],this method not a static method, you need use 'invokeMethod' instead of 'invokeStaticMethod'",
                                                className,
                                                methodName,
                                                params,
                                                modifiers));
            }else{
                //如果底层方法是静态的，那么可以忽略指定的 obj 参数.
                //该参数可以为 null. 从中调用底层方法的对象
                Object object = null;

                // 如果底层方法所需的形参数为 0，
                //则所提供的 args 数组长度可以为 0 或 null 用于方法调用的参数
                Object invoke = method.invoke(object, params);
                return (T) invoke;
            }
        }catch (Exception e){
            log.error(e.getClass().getName(), e);
            throw new ReflectException(e);
        }
    }

    /**
     * 获得方法.
     * 
     * @param ownerClass
     *            类
     * @param methodName
     *            方法名
     * @param paramValues
     *            动态参数值,程序会基于参数值 转成参数类型
     * @return 该方法
     * @throws IllegalArgumentException
     *             if Validator.isNullOrEmpty(ownerClass) or Validator.isNullOrEmpty(methodName)
     * @throws ReflectException
     *             the reflect exception
     * @see ClassUtil#toParameterTypes(Object...)
     * @see java.lang.Class#getMethod(String, Class...)
     */
    private static Method getMethod(Class<?> ownerClass,String methodName,Object...paramValues) throws IllegalArgumentException,
                    ReflectException{
        if (Validator.isNullOrEmpty(ownerClass)){
            throw new IllegalArgumentException("ownerClass can't be null/empty!");
        }
        if (Validator.isNullOrEmpty(methodName)){
            throw new IllegalArgumentException("methodName can't be null/empty!");
        }

        if (log.isDebugEnabled()){
            log.debug("ownerClass:[{}],methodName:[{}],paramValues:[{}]", ownerClass, methodName, paramValues);
        }

        Class<?>[] parameterTypes = ClassUtil.toParameterTypes(paramValues);
        return getMethod(ownerClass, methodName, parameterTypes);
    }

    /**
     * 获得 method.
     *
     * @param ownerClass
     *            the owner class
     * @param methodName
     *            the method name
     * @param parameterTypes
     *            the parameter types
     * @return the method
     * @see java.lang.Class#getMethod(String, Class...)
     * @see org.apache.commons.lang3.ClassUtils#getPublicMethod(Class, String, Class...)
     * @since 1.1.1
     */
    private static Method getMethod(Class<?> ownerClass,String methodName,Class<?>...parameterTypes){

        if (Validator.isNullOrEmpty(ownerClass)){
            throw new IllegalArgumentException("ownerClass can't be null/empty!");
        }
        if (Validator.isNullOrEmpty(methodName)){
            throw new IllegalArgumentException("methodName can't be null/empty!");
        }

        if (log.isDebugEnabled()){
            log.debug("ownerClass:[{}],methodName:[{}],parameterTypes:[{}]", ownerClass, methodName, parameterTypes);
        }

        try{
            // 它反映此 Class 对象所表示的类或接口的指定公共成员方法.<br>
            //name 参数是一个 String，用于指定所需方法的简称.<br>
            //parameterTypes 参数是按声明顺序标识该方法形参类型的 Class 对象的一个数组.如果 parameterTypes 为 null，则按空数组处理.
            Method method = ownerClass.getMethod(methodName, parameterTypes);
            return method;
        }catch (Exception e){
            log.error(e.getClass().getName(), e);
            throw new ReflectException(e);
        }
    }

    // [end]
}
