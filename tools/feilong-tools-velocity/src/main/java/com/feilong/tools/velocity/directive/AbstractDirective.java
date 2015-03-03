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
import java.util.Map;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.directive.Scope;
import org.apache.velocity.runtime.parser.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.tools.velocity.NodeUtil;

/**
 * 所有自定义Directive的抽象类,包含log javadoc功能.<br>
 * Directive 是所有指令的基类，Directive 是一个抽象类，它有三个方法必须实现的，<br>
 * 分别是：
 * <ul>
 * <li>getName：返回指令的名称</li>
 * <li>getType：返回指令的类型，行指令：LINE、块指令：BLOCK</li>
 * <li>render：指令执行的入口</li>
 * </ul>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月15日 上午10:56:47
 * @since 1.0.7
 */
public abstract class AbstractDirective extends Directive{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(AbstractDirective.class);

	// ******************************************************************************

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#init(org.apache.velocity.runtime.RuntimeServices,
	 * org.apache.velocity.context.InternalContextAdapter, org.apache.velocity.runtime.parser.node.Node)
	 */
	@Override
	public void init(RuntimeServices rs,InternalContextAdapter context,Node node) throws TemplateInitException{
		// First difference is that we additionally overrode init() method from Directive class that allows us to get access
		// to RuntimeServices object in order to get logger instance and read default directive parameters from configuration file.

		if (log.isDebugEnabled()){
			log.debug("init......");
		}

		// log = rs.getLog();
		//
		// //read dafault values from config
		// maxLength = rs.getInt("userdirective.truncateBlock.maxLength", 10);
		// suffix = rs.getString("userdirective.truncateBlock.suffix", "...");
		// truncateAtWord = rs.getBoolean("userdirective.truncateBlock.truncateAtWord", false);

		super.init(rs, context, node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#render(org.apache.velocity.context.InternalContextAdapter, java.io.Writer,
	 * org.apache.velocity.runtime.parser.node.Node)
	 */
	@Override
	public boolean render(InternalContextAdapter context,Writer writer,Node node) throws IOException,ResourceNotFoundException,
					ParseErrorException,MethodInvocationException{

		// 其中 render 方法的最后一个参数 node 表示为该指定对应在 Velocity 模板中的节点对象，
		// 通过调用 node 的 jjtGetChild 方法可以获取到传递给该指令的参数以及包含在该指令的脚本内容。

		if (log.isDebugEnabled()){
			Map<String, Object> nodeMapForLog = NodeUtil.getNodeMapForLog(context, node);
			log.debug(JsonUtil.format(nodeMapForLog));
		}
		return doRender(context, writer, node);
	}

	/**
	 * Do render.
	 * 
	 * @param context
	 *            the context
	 * @param writer
	 *            the writer
	 * @param node
	 *            the node
	 * @return true, if do render
	 * @throws IOException
	 *             the IO exception
	 * @throws ResourceNotFoundException
	 *             the resource not found exception
	 * @throws ParseErrorException
	 *             the parse error exception
	 * @throws MethodInvocationException
	 *             the method invocation exception
	 */
	protected abstract boolean doRender(InternalContextAdapter context,Writer writer,Node node) throws IOException,
					ResourceNotFoundException,ParseErrorException,MethodInvocationException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#preRender(org.apache.velocity.context.InternalContextAdapter)
	 */
	@Override
	protected void preRender(InternalContextAdapter context){
		if (log.isDebugEnabled()){
			log.debug("preRender......");
		}
		super.preRender(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#postRender(org.apache.velocity.context.InternalContextAdapter)
	 */
	@Override
	protected void postRender(InternalContextAdapter context){
		if (log.isDebugEnabled()){
			log.debug("postRender......");
		}
		super.postRender(context);
	}

	// ******************************************************************************
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#setLocation(int, int)
	 */
	@Override
	public void setLocation(int line,int column){
		super.setLocation(line, column);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#setLocation(int, int, java.lang.String)
	 */
	@Override
	public void setLocation(int line,int column,String templateName){
		super.setLocation(line, column, templateName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#getLine()
	 */
	@Override
	public int getLine(){
		return super.getLine();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#getColumn()
	 */
	@Override
	public int getColumn(){
		return super.getColumn();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#getTemplateName()
	 */
	@Override
	public String getTemplateName(){
		return super.getTemplateName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#getScopeName()
	 */
	@Override
	public String getScopeName(){
		return super.getScopeName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#isScopeProvided()
	 */
	@Override
	public boolean isScopeProvided(){
		return super.isScopeProvided();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.velocity.runtime.directive.Directive#makeScope(java.lang.Object)
	 */
	@Override
	protected Scope makeScope(Object prev){
		return super.makeScope(prev);
	}

}