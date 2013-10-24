package com.feilong.taglib.spring.base;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * 自定义标签的父类,需要和spring控制的业务层交互的请使用这个基类
 * 
 * @author 金鑫 时间:2009年10月28日 10:50:06
 */
public class BaseSpringTag extends RequestContextAwareTag{

	private static final long	serialVersionUID	= 5289127954140428690L;

	protected StringBuilder		stringBuilder;

	/**
	 * 标签开始
	 */
	@Override
	public int doStartTagInternal(){
		JspWriter jspWriter = pageContext.getOut();// 重要
		try{
			jspWriter.println(this.writeContent());
		}catch (IOException e){
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag(){
		return EVAL_PAGE;// 处理标签后，继续处理JSP后面的内容
	}

	/**
	 * 获得spring管理的实体bean
	 * 
	 * @param beanName
	 *            实体bean名称
	 * @return
	 * @author 金鑫
	 * @version 1.0 时间:2009-10-28上午11:04:17
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getBean(String beanName){
		WebApplicationContext webApplicationContext = this.getRequestContext().getWebApplicationContext();
		return (T) webApplicationContext.getBean(beanName);
	}

	/**
	 * 显示
	 * 
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-3-31 上午11:13:45
	 */
	protected Object writeContent(){
		return "";
	}

	// [start] 公用方法
	/**
	 * 获得HttpServletRequest
	 * 
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-2-3 下午01:59:09
	 */
	protected HttpServletRequest getHttpServletRequest(){
		return (HttpServletRequest) getServletRequest();
	}

	/**
	 * 获得ServletRequest
	 * 
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-2-3 下午01:58:55
	 */
	protected ServletRequest getServletRequest(){
		return this.pageContext.getRequest();
	}

	/**
	 * 获得 HttpSession
	 * 
	 * @return HttpSession
	 * @author 金鑫
	 * @version 1.0 2010-3-18 上午11:04:27
	 */
	protected HttpSession getHttpSession(){
		return this.pageContext.getSession();
	}

	/**
	 * 获得HttpServletResponse
	 * 
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-3-15 下午06:25:18
	 */
	protected HttpServletResponse getHttpServletResponse(){
		return (HttpServletResponse) pageContext.getResponse();
	}
	// [end]
}