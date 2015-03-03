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
package com.feilong.tools.velocity.directive;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.tools.view.ViewToolContext;

/**
 * The Class AbstractDirective.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-5-14 23:53:42
 */
public abstract class AbstractDirective1 extends Directive{

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#render(org.apache.velocity.context.InternalContextAdapter, java.io.Writer,
	 * org.apache.velocity.runtime.parser.node.Node)
	 */
	@Override
	public boolean render(InternalContextAdapter internalContext,Writer writer,Node node) throws IOException,ResourceNotFoundException,
					ParseErrorException,MethodInvocationException{

		// 问题我自己解决，解决方式如下：
		// 通过查看代码，发现指令的render方法中有个context参数，有一个方法：getInternalUserContext可以获取一个ViewToolContext对象，而这个对象在velocity的servlet中被初始化了，将request对象和response对象放在这个对象里。也就是说，获取这个ViewToolContext，就可以获取我想要的东西。
		// 我写了个虚拟类，继承Directive类，获取ViewToolContext，这样就可以搞定了。
		// http://www.oschina.net/question/109826_58614

		// ToolContext implementation specific to the servlet environment.
		//
		// It provides the following special features:
		//
		// puts the request, response, session, and servlet context objects into the Velocity context for direct access, and keeps them
		// read-only
		// supports a read-only toolbox of view tools
		// auto-searches servlet request attributes, session attributes and servlet context attribues for objects
		// The get(String key) method implements the following search order for objects:
		//
		// tool in a request scoped toolbox
		// tool in a session scoped toolbox
		// tool in a application scoped toolbox
		// request, response, session, or servlet context
		// object in the local map of objects (traditional use)
		// request attributes, session attributes, servlet context attributes
		// The purpose of this class is to make it easy for web designer to work with Java servlet based web applications. They do not need
		// to be concerned with the concepts of request, session or application attributes and the lifetime of objects in these scopes.
		//
		// Note that the put() method always puts objects into the local map and does not allow tools or servlet classes to be overridden.
		//

		ViewToolContext context = (ViewToolContext) internalContext.getInternalUserContext();
		return doRender(internalContext, context, writer, node);
	}

	/**
	 * Do render.
	 * 
	 * @param internalContext
	 *            the internal context
	 * @param context
	 *            the context
	 * @param writer
	 *            the writer
	 * @param node
	 *            the node
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ResourceNotFoundException
	 *             the resource not found exception
	 * @throws ParseErrorException
	 *             the parse error exception
	 * @throws MethodInvocationException
	 *             the method invocation exception
	 */
	protected abstract boolean doRender(InternalContextAdapter internalContext,ViewToolContext context,Writer writer,Node node)
					throws IOException,ResourceNotFoundException,ParseErrorException,MethodInvocationException;
}