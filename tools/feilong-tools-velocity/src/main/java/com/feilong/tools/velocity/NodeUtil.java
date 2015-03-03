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
package com.feilong.tools.velocity;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class NodeUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月14日 下午10:23:28
 * @since 1.0.7
 */
public class NodeUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(NodeUtil.class);

	/**
	 * 获得 node map for log.
	 * 
	 * @param internalContextAdapter
	 *            the internal context adapter
	 * @param node
	 *            the node
	 * @return the node map for log
	 */
	public static final Map<String, Object> getNodeMapForLog(InternalContextAdapter internalContextAdapter,Node node){

		if (log.isDebugEnabled()){
			//log.debug("node:{}", JsonUtil.format(node));
		}

		Map<String, Object> object = new LinkedHashMap<String, Object>();

		int jjtGetNumChildren = node.jjtGetNumChildren();

		object.put("node.jjtGetNumChildren()", jjtGetNumChildren);
		object.put("node.getType()", node.getType());
		object.put("node.getColumn()", node.getColumn());
		object.put("node.getInfo()", node.getInfo());
		object.put("node.getLine()", node.getLine());
		object.put("node.getTemplateName()", node.getTemplateName());
		object.put("node.getType()", node.getType());
		object.put("node.isInvalid()", node.isInvalid());
		object.put("node.value(context)", node.value(internalContextAdapter));
		object.put("node.getFirstToken()", node.getFirstToken());
		object.put("node.getLastToken()", node.getLastToken());

		for (int i = 0; i < jjtGetNumChildren; ++i){
			Node jjtGetChild = node.jjtGetChild(i);
			Object value = jjtGetChild.value(internalContextAdapter);
			object.put("node.jjtGetChild(" + i + ").value", value);
		}

		return object;
	}
}
