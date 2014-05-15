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
package com.feilong.taglib.display.httpconcat.directive;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
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
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.taglib.display.httpconcat.HttpConcatUtil;
import com.feilong.taglib.display.httpconcat.command.HttpConcatParam;
import com.feilong.tools.json.JsonUtil;
import com.feilong.tools.velocity.NodeUtil;

/**
 * 融合http concat功能的 Directive<br>
 * Directive 是所有指令的基类，Directive 是一个抽象类，它有三个方法必须实现的，<br>
 * 分别是：
 * <ul>
 * <li>getName：返回指令的名称</li>
 * <li>getType：返回指令的类型，行指令：LINE、块指令：BLOCK</li>
 * <li>render：指令执行的入口</li>
 * </ul>
 * .
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月14日 下午6:23:50
 * @since 1.0.7
 */
public class Concat extends Directive{

	/** The Constant log. */
	private static final Logger	log				= LoggerFactory.getLogger(Concat.class);

	/** The DIRECTIV e_ name. */
	private String				DIRECTIVE_NAME	= "concat";

	private int					DIRECTIVE_TYPE	= BLOCK;

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#init(org.apache.velocity.runtime.RuntimeServices,
	 * org.apache.velocity.context.InternalContextAdapter, org.apache.velocity.runtime.parser.node.Node)
	 */
	public void init(RuntimeServices rs,InternalContextAdapter context,Node node) throws TemplateInitException{
		// First difference is that we additionally overrode init() method from Directive class that allows us to get access
		// to RuntimeServices object in order to get logger instance and read default directive parameters from configuration file.

		super.init(rs, context, node);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#render(org.apache.velocity.context.InternalContextAdapter, java.io.Writer,
	 * org.apache.velocity.runtime.parser.node.Node)
	 */
	public boolean render(InternalContextAdapter internalContextAdapter,Writer writer,Node node) throws IOException,
			ResourceNotFoundException,ParseErrorException,MethodInvocationException{

		// 其中 render 方法的最后一个参数 node 表示为该指定对应在 Velocity 模板中的节点对象，
		// 通过调用 node 的 jjtGetChild 方法可以获取到传递给该指令的参数以及包含在该指令的脚本内容。

		Map<String, Object> nodeMapForLog = NodeUtil.getNodeMapForLog(internalContextAdapter, node);

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(nodeMapForLog));
		}

		SimpleNode typeNode = (SimpleNode) node.jjtGetChild(0);
		String type = "" + typeNode.value(internalContextAdapter);

		SimpleNode versionNode = (SimpleNode) node.jjtGetChild(1);
		String version = "" + versionNode.value(internalContextAdapter);

		Node endNode = node.jjtGetChild(2);

		Object value = typeNode.value(internalContextAdapter);

		StringWriter stringWriter = new StringWriter();
		endNode.render(internalContextAdapter, stringWriter);

		String value2 = stringWriter.toString();
		String[] items = value2.trim().split("\n\t");

		for (int i = 0; i < items.length; ++i){
			String item = items[i];
			if (log.isInfoEnabled()){
				log.info(item.trim());
			}
		}
		String writeContent = getContent(type, version, items);
		writer.write(writeContent);
		return true;
	}

	/**
	 * @param type
	 * @param version
	 * @param items
	 * @return
	 */
	private String getContent(String type,String version,String[] items){
		List<String> itemSrcList = ArrayUtil.toList(items);

		HttpConcatParam httpConcatParam = new HttpConcatParam();
		httpConcatParam.setDomain("");
		httpConcatParam.setItemSrcList(itemSrcList);
		httpConcatParam.setRoot("");
		httpConcatParam.setType(type);
		httpConcatParam.setVersion(version);

		String writeContent = HttpConcatUtil.getWriteContent(httpConcatParam);
		return writeContent;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getName()
	 */
	public String getName(){
		return DIRECTIVE_NAME;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getType()
	 */
	public int getType(){
		return DIRECTIVE_TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#setLocation(int, int)
	 */
	public void setLocation(int line,int column){
		// TODO Auto-generated method stub
		super.setLocation(line, column);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#setLocation(int, int, java.lang.String)
	 */
	public void setLocation(int line,int column,String templateName){
		// TODO Auto-generated method stub
		super.setLocation(line, column, templateName);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getLine()
	 */
	public int getLine(){
		// TODO Auto-generated method stub
		return super.getLine();
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getColumn()
	 */
	public int getColumn(){
		// TODO Auto-generated method stub
		return super.getColumn();
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getTemplateName()
	 */
	public String getTemplateName(){
		// TODO Auto-generated method stub
		return super.getTemplateName();
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getScopeName()
	 */
	public String getScopeName(){
		// TODO Auto-generated method stub
		return super.getScopeName();
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#isScopeProvided()
	 */
	public boolean isScopeProvided(){
		// TODO Auto-generated method stub
		return super.isScopeProvided();
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#preRender(org.apache.velocity.context.InternalContextAdapter)
	 */
	protected void preRender(InternalContextAdapter context){
		// TODO Auto-generated method stub
		super.preRender(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#makeScope(java.lang.Object)
	 */
	protected Scope makeScope(Object prev){
		// TODO Auto-generated method stub
		return super.makeScope(prev);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#postRender(org.apache.velocity.context.InternalContextAdapter)
	 */
	protected void postRender(InternalContextAdapter context){
		// TODO Auto-generated method stub
		super.postRender(context);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	protected Object clone() throws CloneNotSupportedException{
		// TODO Auto-generated method stub
		return super.clone();
	}
}
