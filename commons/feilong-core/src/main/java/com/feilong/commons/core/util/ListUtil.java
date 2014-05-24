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
package com.feilong.commons.core.util;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ListUtil {@link List}工具类.
 * 
 * @author 金鑫 2010-3-2 下午03:20:12
 * @since 1.0.0
 */
public final class ListUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ListUtil.class);

	/** Don't let anyone instantiate this class. */
	private ListUtil(){}

	/**
	 * iterator是否包含某个值.
	 * 
	 * @param iterator
	 *            iterator
	 * @param value
	 *            value
	 * @return iterator是否包含某个值,如果iterator为null/empty,则返回false
	 */
	public static boolean isContain(Iterator<?> iterator,Object value){
		boolean flag = false;
		if (Validator.isNotNullOrEmpty(iterator)){
			Object object = null;
			while (iterator.hasNext()){
				object = iterator.next();
				if (object.toString().equals(value.toString())){
					flag = true;
					break;
				}
			}
		}else{
			log.debug("iterator is null/empty");
		}
		return flag;
	}

	/**
	 * 去重(保留原list元素顺序).
	 * 
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the item src list
	 * @return 如果入参 isNullOrEmpty 返回null<br>
	 *         返回的是 ArrayList
	 * @see <a
	 *      href="http://www.oschina.net/code/snippet_117714_2991?p=2#comments">http://www.oschina.net/code/snippet_117714_2991?p=2#comments</a>
	 */
	// TODO 按照原List类型(ArrayList or linkedList)返回
	public static <T> List<T> removeDuplicate(List<T> list){

		if (Validator.isNullOrEmpty(list)){
			return null;
		}
		// 效率问题？contains的本质就是遍历。你可以自己写段demo测试一下。new ArrayList<T>(new LinkedHashSet<T>(list));
		// 在100W的list当中执行0.546秒，而contains，我则没耐心去等了。顺便贴一下在10W下2段代码的运行时间。
		// [foo1] 100000 -> 50487 : 48610 ms.
		// [foo2] 100000 -> 50487 : 47 ms.
		return new ArrayList<T>(new LinkedHashSet<T>(list));
	}

	/**
	 * 用于 自定义标签/ 自定义el.
	 * 
	 * @param collection
	 *            一个集合,将会被转成Iterator,可以为逗号隔开的字符串,会被分隔成Iterator.
	 * @param value
	 *            任意类型的值,最终toString 判断比较.
	 * @return true, if successful
	 */
	public static boolean _isContainTag(Object collection,Object value){
		@SuppressWarnings("rawtypes")
		Iterator iterator = ObjectUtil.toIterator(collection);
		return ListUtil.isContain(iterator, value);
	}

	/**
	 * 集合转成数组.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            list
	 * @return 数组
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public static <T> T[] toArray(List<T> list){
		if (Validator.isNullOrEmpty(list)){
			return null;
		}
		//FIXME 有可能第一个元素是 null
		Class<? extends Object> firstClass = list.get(0).getClass();
		Class<? extends Object> compontType = firstClass;
		// // 所有类型是不是同一个
		// boolean isAllSameClass = true;
		// for (T t : list){
		// if (t.getClass() != firstClass){
		// isAllSameClass = false;
		// break;
		// }
		// }
		//
		// // 不是
		// if (!isAllSameClass){
		// compontType = Object.class;
		// }

		T[] array = (T[]) Array.newInstance(compontType, list.size());
		return (T[]) list.toArray(array);
	}

	/**
	 * 获得 list第一个元素.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            list
	 * @return 第一个元素,<br>
	 *         空list返回null
	 */
	public static <T> T getFirstItem(List<T> list){
		if (Validator.isNotNullOrEmpty(list)){
			return list.get(0);
		}
		return null;
	}

	// ***********************************************************************************
	/**
	 * list集合转换成字符串,仅将[]中括号符号 换成()小括号,其余不动<br>
	 * 如:
	 * 
	 * <pre>
	 * List&lt;String&gt; testList = new ArrayList&lt;String&gt;();
	 * testList.add(&quot;xinge&quot;);
	 * testList.add(&quot;feilong&quot;);
	 * 
	 * toStringReplaceBrackets(testList)----->(xinge, feilong)
	 * </pre>
	 * 
	 * @param list
	 *            list集合
	 * @return list集合转换成字符串,仅将[]中括号符号 换成()小括号,其余不动
	 */
	public static String toStringReplaceBrackets(List<String> list){
		return list.toString().replace('[', '(').replace(']', ')');
	}

	/**
	 * list集合转换成字符串,移除[]中括号符号,并去除空格 <br>
	 * 如:
	 * 
	 * <pre>
	 * List&lt;String&gt; testList = new ArrayList&lt;String&gt;();
	 * testList.add(&quot;xinge&quot;);
	 * testList.add(&quot;feilong&quot;);
	 * 
	 * toStringRemoveBrackets(testList)----->xinge,feilong
	 * </pre>
	 * 
	 * @param list
	 *            list集合
	 * @return list集合转换成字符串,移除[]中括号符号,并去除空格
	 */
	public static String toStringRemoveBrackets(List<String> list){
		String s = list.toString();
		return s.substring(1, s.length() - 1).replaceAll(" ", "");
	}

	/**
	 * list集合转换成字符串 如:
	 * 
	 * <pre>
	 * List&lt;String&gt; testList = new ArrayList&lt;String&gt;();
	 * testList.add(&quot;xinge&quot;);
	 * testList.add(&quot;feilong&quot;);
	 * 
	 * toString(testList,true)----->'xinge','feilong'
	 * toString(testList,false)----->xinge,feilong
	 * </pre>
	 * 
	 * @param list
	 *            list集合
	 * @param isAddQuotation
	 *            是否增加单引号
	 * @return list集合转换成字符串
	 */
	public static String toString(List<String> list,boolean isAddQuotation){
		String returnValue = toStringRemoveBrackets(list);
		if (isAddQuotation){
			returnValue = "'" + returnValue.replaceAll(",", "','") + "'";
		}
		return returnValue;
	}

	/**
	 * 解析对象集合,取到对象特殊属性,拼成List(ArrayList).
	 * 
	 * @param <T>
	 *            the generic type
	 * @param <O>
	 *            the generic type
	 * @param objectList
	 *            对象集合
	 * @param propertyName
	 *            属性名称
	 * @return <ul>
	 *         <li>isNotNullOrEmpty(objectList) 返回null</li>
	 *         <li>解析对象集合,取到对象特殊属性,拼成List(ArrayList)</li>
	 *         <li></li>
	 *         <li></li>
	 *         </ul>
	 * @throws NullPointerException
	 *             if isNotNullOrEmpty(objectList) or isNullOrEmpty(propertyName)
	 */
	public static <T, O> List<T> getFieldValueList(Iterable<O> objectList,String propertyName) throws NullPointerException{

		if (Validator.isNullOrEmpty(objectList)){
			throw new NullPointerException("objectList is null or empty!");
		}

		if (Validator.isNullOrEmpty(propertyName)){
			throw new NullPointerException("propertyName is null or empty!");
		}

		List<T> list = new ArrayList<T>();
		for (O object : objectList){
			try{
				@SuppressWarnings("unchecked")
				T property = (T) PropertyUtils.getProperty(object, propertyName);
				list.add(property);
			}catch (IllegalAccessException e){
				e.printStackTrace();
			}catch (InvocationTargetException e){
				e.printStackTrace();
			}catch (NoSuchMethodException e){
				e.printStackTrace();
			}
		}
		return list;

	}
}