/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.struts1.util;

import java.util.Enumeration;
import java.util.Iterator;

import org.apache.struts.util.IteratorAdapter;

import com.feilong.commons.core.util.ObjectUtil;

/**
 *FeiLongStrutsTaglibUtil
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-19 下午01:14:39
 */
public class StrutsTaglibUtil{

	/**
	 * 数组/map/Collection/Iterator/Enumeration转成Iterator
	 * 
	 * @param currentCollection
	 *            Object
	 * @return Iterator
	 * @author 金鑫
	 * @version 1.0 2010-5-12 下午05:07:41
	 */
	public static Iterator objectToIterator(Object currentCollection){
		Iterator iterator = ObjectUtil.toIterator(currentCollection);
		if (null == iterator){
			// currentCollection 不是空
			if (null != currentCollection){
				// 构建此集合的迭代器
				if (currentCollection instanceof Enumeration){
					iterator = new IteratorAdapter((Enumeration) currentCollection);
				}
			}
		}
		return iterator;
	}
}
