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
import java.util.Map;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * @deprecated 暂未启用
 */
@Deprecated
public class ConcatItem extends Directive{

	/** The Constant log. */
	private static final Logger	log				= LoggerFactory.getLogger(ConcatItem.class);

	/** The DIRECTIV e_ name. */
	private String				DIRECTIVE_NAME	= "concatItem";

	private int					DIRECTIVE_TYPE	= LINE;

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#render(org.apache.velocity.context.InternalContextAdapter, java.io.Writer,
	 * org.apache.velocity.runtime.parser.node.Node)
	 */
	public boolean render(InternalContextAdapter context,Writer writer,Node node) throws IOException,ResourceNotFoundException,
			ParseErrorException,MethodInvocationException{

		// 其中 render 方法的最后一个参数 node 表示为该指定对应在 Velocity 模板中的节点对象，
		// 通过调用 node 的 jjtGetChild 方法可以获取到传递给该指令的参数以及包含在该指令的脚本内容。

		Map<String, Object> nodeMapForLog = NodeUtil.getNodeMapForLog(context, node);

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(nodeMapForLog));
		}

		SimpleNode typeNode = (SimpleNode) node.jjtGetChild(0);
		SimpleNode versionNode = (SimpleNode) node.jjtGetChild(1);

		Node endNode = node.jjtGetChild(2);

		Object value = typeNode.value(context);
		log.debug("typeNode:{}", JsonUtil.format(typeNode));
		log.debug("versionNode:{}", JsonUtil.format(versionNode));

		// 检查内容是否有变化
		StringWriter stringWriter = new StringWriter();
		endNode.render(context, stringWriter);
		writer.write("sssss");
		return true;
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
}
