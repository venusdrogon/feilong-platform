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
package com.feilong.taglib.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 提供jsp 标签帮助menthods
 * 
 * <pre>
 * 此类 copy from org.apache.struts.taglib TagUtils
 * 
 * </pre>
 * 
 * @version $Rev: 471754 $
 */
public class TagUtils{

	/**
	 * The Singleton instance.
	 * 
	 * @since 1.3.5 Changed to non-final so it may be overridden, use at your own risk (you've been warned!!)
	 */
	private static TagUtils		instance	= new TagUtils();

	/**
	 * Maps lowercase JSP scope names to their PageContext integer constant values.
	 */
	private static final Map	scopes		= new HashMap();
	/**
	 * Initialize the scope names map and the encode variable with the Java 1.4 method if available.
	 */
	static{
		scopes.put("page", new Integer(PageContext.PAGE_SCOPE));
		scopes.put("request", new Integer(PageContext.REQUEST_SCOPE));
		scopes.put("session", new Integer(PageContext.SESSION_SCOPE));
		scopes.put("application", new Integer(PageContext.APPLICATION_SCOPE));
	}

	/**
	 * Constructor for TagUtils.
	 */
	protected TagUtils(){
		super();
	}

	/**
	 * Returns the Singleton instance of TagUtils.
	 * 
	 * @return the Singleton instance
	 */
	public static TagUtils getInstance(){
		return instance;
	}

	/**
	 * Set the instance. This blatently violates the Singleton pattern, but then some say Singletons are an anti-pattern.
	 * 
	 * @param instance
	 *            The instance to set.
	 * @since 1.3.5 Changed to non-final and added setInstance() so TagUtils may be overridden, use at your own risk (you've been warned!!)
	 */
	public static void setInstance(TagUtils instance){
		TagUtils.instance = instance;
	}

	/**
	 * Return the form action converted into an action mapping path. The value of the <code>action</code> property is manipulated as follows
	 * in computing the
	 * name of the requested mapping:
	 * <ul>
	 * <li>Any filename extension is removed (on the theory that extension mapping is being used to select the controller servlet).</li>
	 * <li>If the resulting value does not start with a slash, then a slash is prepended.</li>
	 * </ul>
	 * 
	 * @param action
	 *            the action
	 * @return the action mapping name
	 */
	public String getActionMappingName(String action){
		String value = action;
		int question = action.indexOf("?");
		if (question >= 0){
			value = value.substring(0, question);
		}
		int pound = value.indexOf("#");
		if (pound >= 0){
			value = value.substring(0, pound);
		}
		int slash = value.lastIndexOf("/");
		int period = value.lastIndexOf(".");
		if ((period >= 0) && (period > slash)){
			value = value.substring(0, period);
		}
		return value.startsWith("/") ? value : ("/" + value);
	}

	/**
	 * Converts the scope name into its corresponding PageContext constant value.
	 * 
	 * @param scopeName
	 *            Can be "page", "request", "session", or "application" in any case.
	 * @return The constant representing the scope (ie. PageContext.REQUEST_SCOPE).
	 * @throws JspException
	 *             if the scopeName is not a valid name.
	 */
	public int getScope(String scopeName) throws JspException{
		Integer scope = (Integer) scopes.get(scopeName.toLowerCase());
		if (scope == null){}
		return scope.intValue();
	}

	/**
	 * Locate and return the specified bean, from an optionally specified scope, in the specified page context. If no such bean is found,
	 * return <code>null</code> instead. If an exception is thrown, it will have already been saved via a call to
	 * <code>saveException()</code>.
	 * 
	 * @param pageContext
	 *            Page context to be searched
	 * @param name
	 *            Name of the bean to be retrieved
	 * @param scopeName
	 *            Scope to be searched (page, request, session, application) or <code>null</code> to use <code>findAttribute()</code>
	 *            instead
	 * @return JavaBean in the specified page context
	 * @throws JspException
	 *             if an invalid scope name is requested
	 */
	public Object lookup(PageContext pageContext,String name,String scopeName) throws JspException{
		if (scopeName == null){
			return pageContext.findAttribute(name);
		}
		try{
			return pageContext.getAttribute(name, instance.getScope(scopeName));
		}catch (JspException e){
			throw e;
		}
	}

	/**
	 * Locate and return the specified property of the specified bean, from an optionally specified scope, in the specified page context. If
	 * an exception is
	 * thrown, it will have already been saved via a call to <code>saveException()</code>.
	 * 
	 * @param pageContext
	 *            Page context to be searched
	 * @param name
	 *            Name of the bean to be retrieved
	 * @param property
	 *            Name of the property to be retrieved, or <code>null</code> to retrieve the bean itself
	 * @param scope
	 *            Scope to be searched (page, request, session, application) or <code>null</code> to use <code>findAttribute()</code>
	 *            instead
	 * @return property of specified JavaBean
	 * @throws JspException
	 *             if accessing this property causes an IllegalAccessException, IllegalArgumentException, InvocationTargetException, or
	 *             NoSuchMethodException
	 */
	public Object lookup(PageContext pageContext,String name,String property,String scope) throws JspException{
		// Look up the requested bean, and return if requested
		Object bean = lookup(pageContext, name, scope);
		if (property == null){
			return bean;
		}
		// Locate and return the specified property
		try{
			return PropertyUtils.getProperty(bean, property);
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}catch (NoSuchMethodException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Write the specified text as the response to the writer associated with this page. <strong>WARNING</strong> - If you are writing body
	 * content from the <code>doAfterBody()</code> method of a custom tag class that implements <code>BodyTag</code>, you should be calling
	 * <code>writePrevious()</code> instead.
	 * 
	 * @param pageContext
	 *            The PageContext object for this page
	 * @param text
	 *            The text to be written
	 * @throws JspException
	 *             if an input/output error occurs (already saved)
	 */
	public void write(PageContext pageContext,String text) throws JspException{
		JspWriter writer = pageContext.getOut();
		try{
			writer.print(text);
		}catch (IOException e){}
	}

	/**
	 * Write the specified text as the response to the writer associated with the body content for the tag within which we are currently
	 * nested.
	 * 
	 * @param pageContext
	 *            The PageContext object for this page
	 * @param text
	 *            The text to be written
	 */
	public void writePrevious(PageContext pageContext,String text){
		JspWriter writer = pageContext.getOut();
		if (writer instanceof BodyContent){
			writer = ((BodyContent) writer).getEnclosingWriter();
		}
		try{
			writer.print(text);
		}catch (IOException e){}
	}
}
