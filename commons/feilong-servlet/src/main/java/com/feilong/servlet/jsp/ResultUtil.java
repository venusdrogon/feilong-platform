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
package com.feilong.servlet.jsp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import com.feilong.core.bean.BeanUtil;
import com.feilong.core.bean.PropertyUtil;
import com.feilong.core.lang.ObjectUtil;
import com.feilong.core.lang.reflect.ConstructorUtil;
import com.feilong.core.lang.reflect.FieldUtil;
import com.feilong.core.util.StringUtil;
import com.feilong.core.util.Validator;

/**
 * jstl {@link javax.servlet.jsp.jstl.sql.Result}的工具类.
 * 
 * <p>
 * {@code need jstl}
 * </p>
 * 
 * @author 金鑫 2010-7-9 上午11:55:22
 * @since 1.0
 * 
 * @see javax.servlet.jsp.jstl.sql.Result
 * @see javax.servlet.jsp.jstl.sql.ResultSupport
 * 
 * @deprecated 这个类使用场景不大 如有有需要, 可以使用 spring jdbcUtil等工具类
 */
@Deprecated
public final class ResultUtil{

    /** Don't let anyone instantiate this class. */
    private ResultUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 将result 转成任意的单个的 java bean.
     * 
     * <pre>
     * result 数据必须只有一个.
     * 可变参数不填,则全部填充
     * </pre>
     * 
     * @param result
     *            result
     * @param clz
     *            clz
     * @param fieldNames
     *            字段参数
     * @return java bean
     * @throws SecurityException
     *             the security exception
     * @throws NoSuchFieldException
     *             the no such field exception
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws ClassNotFoundException
     *             the class not found exception
     * @throws NoSuchMethodException
     *             the no such method exception
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    public static Object convertResultToObjectOneBean(Result result,Class<?> clz,String...fieldNames) throws SecurityException,
                    NoSuchFieldException,IllegalArgumentException,ClassNotFoundException,NoSuchMethodException,InstantiationException,
                    IllegalAccessException,InvocationTargetException{
        if (isEmpty(result)){
            return null;
        }
        int rowCount = result.getRowCount();
        // 多条数据不能实用这个方法
        if (rowCount > 1){
            throw new IllegalArgumentException("result 数据" + rowCount + "条");
        }

        @SuppressWarnings("unchecked")
        SortedMap<Object, Object>[] sortedMaps = result.getRows();
        SortedMap<Object, Object> sortedMap = sortedMaps[0];

        String className = clz.getName();
        // 实例化
        Object bean = ConstructorUtil.newInstance(className);

        // 是否取字段
        boolean isGetFileds = Validator.isNotNullOrEmpty(fieldNames);
        if (!isGetFileds){
            fieldNames = FieldUtil.getDeclaredFieldNames(clz);
        }
        // ***********************************************************************************
        Field field = null;
        // 数据库中字段值
        Object sqlValue = null;
        for (String fieldName : fieldNames){
            field = FieldUtil.getDeclaredField(clz, fieldName);
            if (null != field){
                String name = "";
                Object value = "";
                sqlValue = sortedMap.get(fieldName);
                // 是否是date类型
                if (Date.class.isAssignableFrom(field.getType())){
                    // bean 里面的date类型字段需要先实例化
                    name = fieldName + ".time";
                    value = ((Date) sqlValue).getTime();
                    PropertyUtil.setProperty(bean, name, value);
                }else{
                    name = fieldName;
                    value = sqlValue;
                    BeanUtil.setProperty(bean, name, value);
                }
            }
        }
        return bean;
    }

    /**
     * 将Result转成list.
     * 
     * @param result
     *            the result
     * @param clz
     *            the clz
     * @return the list
     * @throws SecurityException
     *             the security exception
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws ClassNotFoundException
     *             the class not found exception
     * @throws NoSuchMethodException
     *             the no such method exception
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    public static List<?> convertResultToList(Result result,Class<?> clz) throws SecurityException,IllegalArgumentException,
                    ClassNotFoundException,NoSuchMethodException,InstantiationException,IllegalAccessException,InvocationTargetException{
        if (isEmpty(result)){
            return null;
        }
        return convertResultToList(result, clz, result.getColumnNames());
    }

    /**
     * 将Result转成list.
     *
     * @param <T>
     *            the generic type
     * @param result
     *            结果集
     * @param clz
     *            类名
     * @param fileds
     *            字段可变参数
     * @return 将Result转成list
     * @throws SecurityException
     *             the security exception
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws ClassNotFoundException
     *             the class not found exception
     * @throws NoSuchMethodException
     *             the no such method exception
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    public static <T> List<T> convertResultToList(Result result,Class<T> clz,String...fileds) throws SecurityException,
                    IllegalArgumentException,ClassNotFoundException,NoSuchMethodException,InstantiationException,IllegalAccessException,
                    InvocationTargetException{
        if (isEmpty(result)){
            return null;
        }
        List<T> list = new ArrayList<T>();
        SortedMap<?, ?>[] sortedMaps = result.getRows();
        // 是否取字段
        boolean isGetFileds = Validator.isNotNullOrEmpty(fileds);
        T bean;
        String className = clz.getName();
        for (SortedMap<?, ?> sortedMap : sortedMaps){
            // 实例化
            bean = ConstructorUtil.newInstance(className);
            if (isGetFileds){
                for (String filed : fileds){
                    BeanUtil.setProperty(bean, filed, sortedMap.get(filed));
                }
            }
            list.add(bean);
        }
        return list;
    }

    /**
     * 将result 单个sortedMap 值取出赋予bean(fileds字段区分大小写,必须和bean 里面的字段一一对应).
     * 
     * <pre>
     * 自动转换类型
     * </pre>
     * 
     * @param sortedMap
     *            sortedMap
     * @param bean
     *            bean
     * @param fileds
     *            字段
     */
    public static void getSortedMapValueToBean(SortedMap<?, ?> sortedMap,Object bean,String...fileds){
        if (Validator.isNotNullOrEmpty(sortedMap) && Validator.isNotNullOrEmpty(bean) && Validator.isNotNullOrEmpty(fileds)){
            for (String filed : fileds){
                String value = getSortedMapValueByKeyWithTrim(sortedMap, filed);
                BeanUtil.setProperty(bean, filed, value);
            }
        }
    }

    /**
     * 将result 单个sortedMap 值取出赋予bean(fileds字段不区分大小写,自动转换成规范化的字段).
     * 
     * <pre>
     * 自动转换类型
     * </pre>
     * 
     * @param sortedMap
     *            sortedMap
     * @param bean
     *            bean
     * @param fileds
     *            字段
     */
    public static void getSortedMapValueToBeanAutoChangeRegulateFiled(SortedMap<?, ?> sortedMap,Object bean,String...fileds){
        if (Validator.isNotNullOrEmpty(fileds)){
            for (int i = 0, j = fileds.length; i < j; ++i){
                fileds[i] = convertNameToPropertyName(fileds[i]);
            }
        }
        getSortedMapValueToBean(sortedMap, bean, fileds);
    }

    /**
     * 将普通名称转成属性名称 首字母小写,第二个字母大写.
     * 
     * <pre>
     * jinxin----jinxin
     * Jinxin----jinxin
     * jin_xin----jin_Xin
     * jin_xin_xin----jin_Xin_Xin
     * </pre>
     * 
     * @param name
     *            普通名称
     * @return 将普通名称转成属性名称
     */
    private static String convertNameToPropertyName(String name){
        // 如果名称里面包含_ 则分割,首字母小写,第二个字母大写
        if (StringUtil.isContain(name, "_")){
            String[] strings = name.split("_");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(strings[0].toLowerCase());
            for (int i = 1; i < strings.length; ++i){
                stringBuilder.append("_");
                stringBuilder.append(StringUtil.firstCharToUpperCase(strings[i]));
            }
            return stringBuilder.toString();
        }
        return name.toLowerCase();
    }

    /**
     * 判断Result不是空的.
     * 
     * @param result
     *            result
     * @return 不是空返回true
     */
    public static boolean isNotEmpty(Result result){
        return !isEmpty(result);
    }

    /**
     * 判断Result是否是空的.
     * 
     * @param result
     *            result
     * @return 空返回true
     */
    public static boolean isEmpty(Result result){
        if (null == result){
            return true;
        }else if (result.getRowCount() == 0){
            return true;
        }
        return false;
    }

    /**
     * 获得result结果唯一值.
     * 
     * @param result
     *            the result
     * @return 获得result结果唯一值
     */
    public static Object getUniqueResult(Result result){
        if (ResultUtil.isEmpty(result)){
            return null;
        }
        SortedMap<?, ?> sortedMap = result.getRows()[0];
        Object object = sortedMap.get(result.getColumnNames()[0]);
        return object;
    }

    // **********************************
    /**
     * 获得result row的特定字段的值(key,不区分大小写).
     * 
     * @param sortedMap
     *            SortedMap
     * @param key
     *            字段名称
     * @return 获得result row的特定字段的值
     */
    public static Object getSortedMapValueByKey(SortedMap<?, ?> sortedMap,Object key){
        return sortedMap.get(key);
    }

    /**
     * 获得result row的特定字段的值,并去除空格(key,不区分大小写).
     * 
     * @param sortedMap
     *            SortedMap
     * @param key
     *            filedName
     * @return 获得result row的特定字段的值,并去除空格
     */
    public static String getSortedMapValueByKeyWithTrim(SortedMap<?, ?> sortedMap,Object key){
        return ObjectUtil.trim(getSortedMapValueByKey(sortedMap, key));
    }

    /**
     * 获得result row的特定字段的值,并转成Integer类型(key,不区分大小写).
     * 
     * @param sortedMap
     *            SortedMap
     * @param key
     *            filedName
     * @return 获得result row的特定字段的值,并转成Integer类型
     */
    public static Integer getSortedMapValueByKeyAndToInteger(SortedMap<?, ?> sortedMap,Object key){
        Object value = getSortedMapValueByKey(sortedMap, key);
        return ObjectUtil.toInteger(value);
    }
}
