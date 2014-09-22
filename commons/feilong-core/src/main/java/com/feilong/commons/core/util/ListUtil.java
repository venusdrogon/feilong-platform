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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

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
		// 效率问题？contains的本质就是遍历.
		// 在100W的list当中执行0.546秒，而contains，我则没耐心去等了.顺便贴一下在10W下2段代码的运行时间.
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
}