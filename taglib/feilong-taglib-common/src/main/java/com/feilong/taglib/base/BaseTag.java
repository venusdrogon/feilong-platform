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

package com.feilong.taglib.base;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 飞龙自定义标签的父类,所有飞龙自定义标签的基类,包含通用的方法 <br>
 * <ul>
 * <li>EVAL_BODY_AGAIN 对标签体循环处理</li>
 * <li>EVAL_PAGE (6)：处理标签后，继续处理JSP后面的内容</li>
 * <li>SKIP_PAGE(5)： 忽略标签后面的JSP内容</li>
 * <li>EVAL_BODY_BUFFERED(2) 表示需要处理标签体</li>
 * <li>EVAL_BODY_INCLUDE(1)将body的内容输出到存在的输出流中 表示需要处理标签体,但绕过setBodyContent()和doInitBody()方法</li>
 * <li>SKIP_BODY (0) ： 表示不用处理标签体，直接调用doEndTag()方法 跳过了开始和结束标签之间的代码。</li>
 * </ul>
 * TagSupport与BodyTagSupport的区别主要是标签处理类是否需要与标签体交互， <br>
 * 如果不需要交互的就用TagSupport，否则如果不需要交互就用BodyTagSupport。 <br>
 * 交互就是标签处理类是否要读取标签体的内容和改变标签体返回的内容。<br>
 * <br>
 * 用TagSupport实现的标签，都可以用BodyTagSupport来实现，因为BodyTagSupport继承了TagSupport.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-13 01:46
 */
public abstract class BaseTag extends BodyTagSupport{

	private static final Logger	log					= LoggerFactory.getLogger(BaseTag.class);

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -5494214419937813707L;

	/**
	 * 将文字输出到页面.
	 * 
	 * @param object
	 *            the object
	 * @author 金鑫
	 * @version 1.0 2010-5-5 下午03:27:25
	 */
	protected void print(Object object){
		JspWriter jspWriter = pageContext.getOut();
		try{
			jspWriter.print(object);
		}catch (IOException e){
			log.error(e.getClass().getName(), e);
		}
	}

	/**
	 * 将文字输出到页面.
	 * 
	 * @param object
	 *            the object
	 * @author 金鑫
	 * @version 1.0 2010-5-5 下午03:53:12
	 */
	protected void println(Object object){
		JspWriter jspWriter = pageContext.getOut();
		try{
			jspWriter.println(object.toString());
		}catch (IOException e){
			log.error(e.getClass().getName(), e);
		}
	}

	// /**
	// * 标签结束 javax.servlet.jsp.tagext.TagSupport.doEndTag() 默认 EVAL_PAGE
	// */
	// @Override
	// public int doEndTag(){
	// return EVAL_PAGE;// 处理标签后，继续处理JSP后面的内容
	// }

	// [start] 公用方法
	/**
	 * 获得HttpServletRequest.
	 * 
	 * @return the http servlet request
	 * @author 金鑫
	 * @version 1.0 2010-2-3 下午01:59:09
	 */
	protected final HttpServletRequest getHttpServletRequest(){
		return (HttpServletRequest) getServletRequest();
	}

	/**
	 * 获得ServletRequest.
	 * 
	 * @return the servlet request
	 * @version 1.0 2010-2-3 下午01:58:55
	 */
	protected final ServletRequest getServletRequest(){
		return this.pageContext.getRequest();
	}

	/**
	 * 获得 HttpSession.
	 * 
	 * @return HttpSession
	 * @author 金鑫
	 * @version 1.0 2010-3-18 上午11:04:27
	 */
	protected final HttpSession getHttpSession(){
		return this.pageContext.getSession();
	}

	/**
	 * 获得HttpServletResponse.
	 * 
	 * @return the http servlet response
	 * @author 金鑫
	 * @version 1.0 2010-3-15 下午06:25:18
	 */
	protected final HttpServletResponse getHttpServletResponse(){
		return (HttpServletResponse) pageContext.getResponse();
	}
	// [end]
}