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
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.ObjectUtil;

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
	 * @see Iterator#hasNext()
	 * @see Iterator#next()
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
	 * 去重(如果原collection是有序的,那么会保留原collection元素顺序).
	 * 
	 * @param <T>
	 *            the generic type
	 * @param collection
	 *            the item src list
	 * @return if Validator.isNullOrEmpty(collection) 返回null<br>
	 *         else 返回的是 {@link ArrayList}
	 * @see ArrayList#ArrayList(java.util.Collection)
	 * @see LinkedHashSet#LinkedHashSet(Collection)
	 * @see <a
	 *      href="http://www.oschina.net/code/snippet_117714_2991?p=2#comments">http://www.oschina.net/code/snippet_117714_2991?p=2#comments</a>
	 */
	public static <T> List<T> removeDuplicate(Collection<T> collection){
		if (Validator.isNullOrEmpty(collection)){
			return null;
		}
		// 效率问题？contains的本质就是遍历。
		// 在100W的list当中执行0.546秒，而contains，我则没耐心去等了。顺便贴一下在10W下2段代码的运行时间。
		// [foo1] 100000 -> 50487 : 48610 ms.
		// [foo2] 100000 -> 50487 : 47 ms.
		return new ArrayList<T>(new LinkedHashSet<T>(collection));
	}

	/**
	 * 用于 自定义标签/ 自定义el.
	 * 
	 * @param collection
	 *            一个集合,将会被转成Iterator,可以为逗号隔开的字符串,会被分隔成Iterator.
	 * @param value
	 *            任意类型的值,最终toString 判断比较.
	 * @return true, if successful
	 * @see ObjectUtil#toIterator(Object)
	 * @see #isContain(Iterator, Object)
	 */
	public static boolean _isContainTag(Object collection,Object value){
		@SuppressWarnings("rawtypes")
		Iterator iterator = ObjectUtil.toIterator(collection);
		return isContain(iterator, value);
	}

	/**
	 * 集合转成数组<br>
	 * note:由于没有办法自动获得T 泛型的类型, 所以会取第一个值的类型做数组的类型,故需要确保第一个元素不是null.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            list
	 * @return 数组,if Validator.isNullOrEmpty(list),return null
	 * @throws IllegalArgumentException
	 *             如果list中第一个元素 isNullOrEmpty
	 * @see java.lang.reflect.Array#newInstance(Class, int)
	 * @see java.lang.reflect.Array#newInstance(Class, int...)
	 * @see java.util.Collection#toArray()
	 * @see java.util.Collection#toArray(Object[])
	 * @see java.util.List#toArray()
	 * @see java.util.List#toArray(Object[])
	 * @see java.util.Vector#toArray()
	 * @see java.util.Vector#toArray(Object[])
	 * @see java.util.LinkedList#toArray()
	 * @see java.util.LinkedList#toArray(Object[])
	 * @see java.util.ArrayList#toArray()
	 * @see java.util.ArrayList#toArray(Object[])
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	public static <T> T[] toArray(List<T> list) throws IllegalArgumentException{
		if (Validator.isNullOrEmpty(list)){
			return null;
		}

		//**********************************************************************
		T firstT = list.get(0);
		if (Validator.isNullOrEmpty(firstT)){
			throw new IllegalArgumentException("list's first item can't be null/empty!");
		}
		//**********************************************************************
		Class<?> compontType = firstT.getClass();

		int size = list.size();
		T[] a = (T[]) Array.newInstance(compontType, size);

		// 如果采用大家常用的把a的length设为0,就需要反射API来创建一个大小为size的数组,而这对性能有一定的影响.
		// 所以最好的方式就是直接把a的length设为Collection的size从而避免调用反射API来达到一定的性能优化.

		//注意，toArray(new Object[0]) 和 toArray() 在功能上是相同的。 
		return (T[]) list.toArray(a);
	}

	/**
	 * 获得 list第一个元素.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            list
	 * @return 第一个元素<br>
	 *         if (Validator.isNotNullOrEmpty(list),return null
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
	 * toStringReplaceBrackets(testList)-----{@code >}(xinge, feilong)
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
	 * {@code
	 * 
	 * List&lt;String&gt; testList = new ArrayList&lt;String&gt;();
	 * testList.add(&quot;xinge&quot;);
	 * testList.add(&quot;feilong&quot;);
	 * 
	 * toStringRemoveBrackets(testList)----->xinge,feilong
	 * }
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
	 * {@code
	 * 
	 * List&lt;String&gt; testList = new ArrayList&lt;String&gt;();
	 * testList.add(&quot;xinge&quot;);
	 * testList.add(&quot;feilong&quot;);
	 * 
	 * toString(testList,true)----->'xinge','feilong'
	 * toString(testList,false)----->xinge,feilong
	 * }
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
	 * 解析对象集合,使用 {@link org.apache.commons.beanutils.PropertyUtils#getProperty(Object, String)}取到对象特殊属性,拼成List(ArrayList). <br>
	 * 支持属性级联获取,支付获取数组,集合,map,自定义bean等属性
	 * 
	 * <h3>使用示例:</h3>
	 * 
	 * <blockquote>
	 * 
	 * <pre>
	 * List&lt;User&gt; testList = new ArrayList&lt;User&gt;();
	 * 
	 * User user;
	 * UserInfo userInfo;
	 * 
	 * //*******************************************************
	 * List&lt;UserAddress&gt; userAddresseList = new ArrayList&lt;UserAddress&gt;();
	 * UserAddress userAddress = new UserAddress();
	 * userAddress.setAddress(&quot;中南海&quot;);
	 * userAddresseList.add(userAddress);
	 * 
	 * //*******************************************************
	 * Map&lt;String, String&gt; attrMap = new HashMap&lt;String, String&gt;();
	 * attrMap.put(&quot;蜀国&quot;, &quot;赵子龙&quot;);
	 * attrMap.put(&quot;魏国&quot;, &quot;张文远&quot;);
	 * attrMap.put(&quot;吴国&quot;, &quot;甘兴霸&quot;);
	 * 
	 * //*******************************************************
	 * String[] lovesStrings1 = { &quot;sanguo1&quot;, &quot;xiaoshuo1&quot; };
	 * userInfo = new UserInfo();
	 * userInfo.setAge(28);
	 * 
	 * user = new User(2L);
	 * user.setLoves(lovesStrings1);
	 * user.setUserInfo(userInfo);
	 * user.setUserAddresseList(userAddresseList);
	 * 
	 * user.setAttrMap(attrMap);
	 * testList.add(user);
	 * 
	 * //*****************************************************
	 * String[] lovesStrings2 = { &quot;sanguo2&quot;, &quot;xiaoshuo2&quot; };
	 * userInfo = new UserInfo();
	 * userInfo.setAge(30);
	 * 
	 * user = new User(3L);
	 * user.setLoves(lovesStrings2);
	 * user.setUserInfo(userInfo);
	 * user.setUserAddresseList(userAddresseList);
	 * user.setAttrMap(attrMap);
	 * testList.add(user);
	 * 
	 * //数组
	 * List&lt;String&gt; fieldValueList1 = ListUtil.getFieldValueList(testList, &quot;loves[1]&quot;);
	 * log.info(JsonUtil.format(fieldValueList1));
	 * 
	 * //级联对象
	 * List&lt;Integer&gt; fieldValueList2 = ListUtil.getFieldValueList(testList, &quot;userInfo.age&quot;);
	 * log.info(JsonUtil.format(fieldValueList2));
	 * 
	 * //Map
	 * List&lt;Integer&gt; attrList = ListUtil.getFieldValueList(testList, &quot;attrMap(蜀国)&quot;);
	 * log.info(JsonUtil.format(attrList));
	 * 
	 * //集合
	 * List&lt;String&gt; addressList = ListUtil.getFieldValueList(testList, &quot;userAddresseList[0]&quot;);
	 * log.info(JsonUtil.format(addressList));
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param <T>
	 *            返回集合类型 generic type
	 * @param <O>
	 *            可迭代对象类型 generic type
	 * @param objectList
	 *            任何可以迭代的对象
	 * @param propertyName
	 *            迭代泛型对象的属性名称,Possibly indexed and/or nested name of the property to be extracted
	 * @return 解析迭代集合,取到对象特殊属性,拼成List(ArrayList)
	 * @throws NullPointerException
	 *             if Validator.isNullOrEmpty(objectList) or Validator.isNullOrEmpty(propertyName)
	 * @see com.feilong.commons.core.bean.BeanUtil#getProperty(Object, String)
	 * @see org.apache.commons.beanutils.PropertyUtils#getProperty(Object, String)
	 * @since jdk1.5
	 */
	public static <T, O> List<T> getFieldValueList(Iterable<O> objectList,String propertyName) throws NullPointerException{
		if (Validator.isNullOrEmpty(objectList)){
			throw new NullPointerException("objectList is null or empty!");
		}

		if (Validator.isNullOrEmpty(propertyName)){
			throw new NullPointerException("propertyName is null or empty!");
		}

		List<T> list = new ArrayList<T>();
		try{
			for (O bean : objectList){
				@SuppressWarnings("unchecked")
				T property = (T) PropertyUtils.getProperty(bean, propertyName);
				list.add(property);
			}
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}catch (NoSuchMethodException e){
			e.printStackTrace();
		}
		return list;
	}
}