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
package com.feilong.taglib.display.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.entity.Pager;
import com.feilong.commons.core.lang.ObjectUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.html.HtmlTableTag;
import com.feilong.taglib.util.TagUtils;
import com.feilong.tools.html.HtmlUtil;

/**
 * table标签.
 * 
 * @author 金鑫 2010-4-16 下午03:03:32
 */
public class TableTag extends HtmlTableTag{

	private static final Logger	log					= LoggerFactory.getLogger(TableTag.class);

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** *****************begin 声明变量*****************************. */
	/**
	 * 实际处理的iterator
	 */
	protected Iterator			iterator			= null;

	/** 呈现出元素的数目. */
	protected int				lengthCount			= 0;

	/** 标签实例是否开始. */
	protected boolean			started				= false;

	/** 分页参数. */
	private Integer				pageNo;

	/** 每页的大小. */
	private int					maxPageItems;

	/** ********************end**************************. */
	// -------------------------------------------------------------begin Properties
	/**
	 * The collection over which we will be iterating.
	 */
	protected Object			collection			= null;

	/** 每次循环变量名字. */
	protected String			id					= null;

	/**
	 * 定义代表当前被遍历元素序号 <br>
	 * 通过循环返回当前迭代零相对指标. <br>
	 * 此属性是只读的,并给出了嵌套的自定义标签来获取这些信息。 <br>
	 * 因此,它是唯一有效的调用之间这个doStartTag()和doEndTag()
	 */
	protected String			indexId				= null;

	/**
	 * The name of the collection or owning bean. 集合命名变量,默认作用域requst
	 */
	protected String			name				= null;

	/**
	 * The property name containing the collection.
	 */
	protected String			property			= null;

	/**
	 * The scope of the bean specified by the name property, if any.
	 */
	protected String			scope				= null;

	/** 标题集合. */
	private List<String>		titleList			= new ArrayList<String>();

	// -------------------------------------------------------------end
	// [start] 子标签调用
	/**
	 * 添加标题.
	 * 
	 * @author 金鑫
	 * @version 1.0 2010-5-7 下午03:03:03
	 * @param title
	 *            the title
	 */
	public void addTitle(String title){
		titleList.add(title);
	}

	/**
	 * 获得呈现出的长度,即循环次数.
	 * 
	 * @return the lengthCount
	 */
	public int getLengthCount(){
		return lengthCount;
	}

	// -------------------------------------------------------------end
	// [end]
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.taglib.base.AbstractCommonTag#doStartTag()
	 */
	@Override
	public int doStartTag(){
		iterator = getIterator();
		// 判断有没有数据
		boolean isHasData = Validator.isNotNullOrEmpty(iterator);
		titleList.clear();
		HttpServletRequest request = getHttpServletRequest();
		// 输出表单
		if (isHasData){
			Pager pageModel = (Pager) request.getAttribute("pageModel");
			if (null != pageModel){

				/**
				 * 最大的页码显示数量.
				 * 
				 * @deprecated
				 */
				Integer MAX_PAGE_ITEMS = 10;
				maxPageItems = MAX_PAGE_ITEMS;// PagerConstants.MAX_PAGE_ITEMS;
				// 获得参数pageNo
				pageNo = pageModel.getCurrentPageNo();
			}
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("<table");
			innerCommonAttribute(stringBuilder);
			stringBuilder.append(">");
			stringBuilder.append(SystemUtils.LINE_SEPARATOR);
			print(stringBuilder);
		}
		// 是否为空
		request.setAttribute("FeiLongDisplayIsHasData", isHasData);
		lengthCount = 0;
		// Store the first value and evaluate, or skip the body if none
		Object element = null;
		if (isHasData){
			element = iterator.next();
			if (element == null){
				pageContext.removeAttribute(id);
			}else{
				pageContext.setAttribute(id, element);
			}
			lengthCount++;
			started = true;// 开始执行了
			if (indexId != null){
				pageContext.setAttribute(indexId, new Integer(getIndex()));
			}
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}

	/**
	 * Make the next collection element available and loop, or finish the iterations if there are no more elements. <br>
	 * 充分利用现有和未来集合元素循环，或完成迭代如果没有更多的元素
	 * 
	 * @return the int
	 * @throws JspException
	 *             the jsp exception
	 */
	@Override
	public int doAfterBody() throws JspException{
		StringBuilder stringBuilder = new StringBuilder();
		// 首次
		if (lengthCount == 1){
			stringBuilder.append("<tr>");
			stringBuilder.append(SystemUtils.LINE_SEPARATOR);
			for (int i = 0, j = titleList.size(); i < j; i++){
				stringBuilder.append("<th>");
				stringBuilder.append(titleList.get(i));
				stringBuilder.append("</th>");
			}
			stringBuilder.append(SystemUtils.LINE_SEPARATOR);
			stringBuilder.append("</tr>");
			stringBuilder.append(SystemUtils.LINE_SEPARATOR);
		}
		// 从这个迭代渲染输出到输出流
		if (bodyContent != null){
			stringBuilder.append("<tr");
			if (lengthCount % 2 == 0){
				HtmlUtil.addAttribute(stringBuilder, "class", "feilongEvenTrClass");
			}
			stringBuilder.append(">");
			stringBuilder.append(SystemUtils.LINE_SEPARATOR);
			stringBuilder.append(bodyContent.getString());
			stringBuilder.append("</tr>");
			stringBuilder.append(SystemUtils.LINE_SEPARATOR);
			TagUtils.getInstance().writePrevious(pageContext, stringBuilder.toString());
			bodyContent.clearBody();
		}
		if (iterator.hasNext()){
			// 设置别名属于当前循环范围
			Object element = iterator.next();
			if (element == null){
				pageContext.removeAttribute(id);
			}else{
				pageContext.setAttribute(id, element);
			}
			lengthCount++;
			if (indexId != null){
				pageContext.setAttribute(indexId, new Integer(getIndex()));
			}
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}

	/**
	 * Clean up after processing this enumeration.
	 * 
	 * @return the int
	 */
	@Override
	public int doEndTag(){
		// 输出表单
		if (lengthCount > 0){
			println("</table>");
		}
		started = false;
		iterator = null;
		return EVAL_PAGE;
	}

	/**
	 * 产生Iterator迭代器.
	 * 
	 * @author 金鑫
	 * @version 1.0 2010-5-6 下午04:04:05
	 * @return the *****************begin 声明变量*****************************
	 */
	private Iterator getIterator(){
		// 获取集合我们要遍历
		Object currentCollection = this.collection;
		if (currentCollection == null){
			try{
				currentCollection = TagUtils.getInstance().lookup(pageContext, name, property, scope);
			}catch (JspException e){
				log.error(e.getClass().getName(), e);
			}
		}
		return ObjectUtil.toIterator(currentCollection);
	}

	/**
	 * 设置 ********************end**************************.
	 * 
	 * @param collection
	 *            the new ********************end**************************
	 */
	public void setCollection(Object collection){
		this.collection = collection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#setId(java.lang.String)
	 */
	@Override
	public void setId(String id){
		this.id = id;
	}

	/**
	 * 设置 the name of the collection or owning bean.
	 * 
	 * @param name
	 *            the new name of the collection or owning bean
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 设置 the property name containing the collection.
	 * 
	 * @param property
	 *            the new property name containing the collection
	 */
	public void setProperty(String property){
		this.property = property;
	}

	/**
	 * 设置 the scope of the bean specified by the name property, if any.
	 * 
	 * @param scope
	 *            the new scope of the bean specified by the name property, if any
	 */
	public void setScope(String scope){
		this.scope = scope;
	}

	/**
	 * 取得循环编号.
	 * 
	 * @author 金鑫
	 * @version 1.0 2010-6-7 上午10:03:02
	 * @return 循环编号
	 */
	public int getIndex(){
		if (started){
			if (null != pageNo){
				return maxPageItems * (pageNo - 1) + (lengthCount - 1);
			}
			return lengthCount - 1;
		}
		return 0;
	}

	/**
	 * 设置 定义代表当前被遍历元素序号 <br>
	 * 通过循环返回当前迭代零相对指标.
	 * 
	 * @param indexId
	 *            the new 定义代表当前被遍历元素序号 <br>
	 *            通过循环返回当前迭代零相对指标
	 */
	public void setIndexId(String indexId){
		this.indexId = indexId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#release()
	 */
	@Override
	public void release(){
		super.release();
		iterator = null;
		lengthCount = 0;
		id = null;
		collection = null;
		name = null;
		property = null;
		scope = null;
		started = false;
	}
}
