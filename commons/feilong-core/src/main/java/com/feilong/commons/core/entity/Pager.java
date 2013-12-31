/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页 bean实体.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2009-9-2 下午02:24:44
 * @version 2012-3-16 01:01 将查询参数 删除,保留分页相关参数
 * @since 1.0
 */
public class Pager implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -903770720729924696L;

	/** 当前页码. */
	private int					currentPageNo;

	/** 每页显示数量,默认10,传入该参数 可以计算分页数量. */
	private Integer				pageSize			= 10;

	/** 总共数据数,不同的数据库返回的类型不一样. */
	private Integer				count;

	/** 存放的数据集合. */
	private List<?>				itemList;

	/**
	 * The Constructor.
	 */
	public Pager(){}

	/**
	 * The Constructor.
	 * 
	 * @param currentPageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示数目
	 * @param count
	 *            总数
	 */
	public Pager(int currentPageNo, Integer pageSize, Integer count){
		this.currentPageNo = currentPageNo;
		this.pageSize = pageSize;
		this.count = count;
	}

	/**
	 * 存放的数据集合
	 * 
	 * @return the itemList
	 */
	public List<?> getItemList(){
		return itemList;
	}

	/**
	 * 存放的数据集合
	 * 
	 * @param itemList
	 *            the itemList to set
	 */
	public void setItemList(List<?> itemList){
		this.itemList = itemList;
	}

	/**
	 * 总共数据数
	 * 
	 * @return the count
	 */
	public Number getCount(){
		return count;
	}

	/**
	 * 总共数据数
	 * 
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count){
		this.count = count;
	}

	/**
	 * 当前页码
	 * 
	 * @return the currentPageNo
	 */
	public int getCurrentPageNo(){
		return currentPageNo;
	}

	/**
	 * 当前页码
	 * 
	 * @param currentPageNo
	 *            the currentPageNo to set
	 */
	public void setCurrentPageNo(int currentPageNo){
		this.currentPageNo = currentPageNo;
	}

	/**
	 * 总页数.
	 * 
	 * @return the allPageNo
	 */
	public int getAllPageNo(){
		if (0 == count){
			return 0;
		}else if (count < pageSize){
			return 1;
		}
		// 除后的页数
		int i = count / pageSize;
		if (count % pageSize == 0){
			return i;
		}
		return i + 1;
	}

	/**
	 * 上一页页码.
	 * 
	 * @return the prePageNo
	 */
	public int getPrePageNo(){
		if (currentPageNo - 1 <= 1){
			return 1;
		}
		return currentPageNo - 1;
	}

	/**
	 * 下一页页码.
	 * 
	 * @return the nextPageNo
	 */
	public int getNextPageNo(){
		/** 总页数. */
		int allPageNo = getAllPageNo();
		if (currentPageNo + 1 >= allPageNo){
			return allPageNo;
		}
		return currentPageNo + 1;
	}
}
