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
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.display.httpconcat.HttpConcatUtil;
import com.feilong.taglib.display.httpconcat.command.HttpConcatParam;
import com.feilong.tools.velocity.directive.AbstractDirective;

/**
 * 融合http concat功能的 Directive.<br>
 * 说明:
 * 
 * <pre>
 * {@code
 * 支持以下格式写法:
 * 	#concat(String type,String version)
 * 	#concat(String type,String version,String domain)
 * 	#concat(String type,String version,String domain,String root)
 * 
 * 对于block块内容,每行一个css/js元素
 * 
 * 比如写成:
 * 
 *  #concat("js","20140515","http://www.feilong.com")
 *  
 * 	    ##原来这个文件的script type是text/ecmascript,找不到主人,暂时合并,看看会不会出问题
 * 	    static/marketplace/js/marketplace.js
 * 	
 * 	    static/public/js/cascading/jquery.cascading.data.js
 * 	    static/public/js/cascading/jquery.cascading.js
 * 	
 * 	    static/public/js/jquery.json-2.4.js
 * 	    static/public/js/jquery.form.js
 * 	    static/public/js/jquery.lazyload.min.js
 * 	
 * 	    static/member/messages/messageAddress_$request.getAttribute('locale').js
 * 	    static/trade/messages/messageTrade_$request.getAttribute('locale').js
 * 
 * 
 * 
 *  #end
 * 
 * 
 * 每行一个css/js元素,中间可以有空白行,程序会自动忽视空白行
 * }
 * </pre>
 * 
 * 其中: <blockquote>
 * <table border='1' cellspacing='0' cellpadding='4' summary="Chart showing symbol,  location, localized, and meaning.">
 * <tr bgcolor="#ccccff">
 * <th align=left>字段</th>
 * <th align=left>说明</th>
 * <th align=left>示例</th>
 * </tr>
 * <tr valign='top'>
 * <td>type</td>
 * <td>标识 类型, 可用值 js或者css, 忽视大小写</td>
 * <td>js</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>version</td>
 * <td>版本号 不建议直接使用时间戳,容易被黑客CDN攻击 建议使用md5参数或者随机数</td>
 * <td>16b3adc77c0d3e9df617d3178fe43445</td>
 * </tr>
 * <tr valign=top >
 * <td>domain</td>
 * <td>域名,某些商城有子域名</td>
 * <td>http://image.nikestore.com.cn ,也可以写成 http://image.nikestore.com.cn/ (最后带/)</td>
 * </tr>
 * <tr valign='top' bgcolor="#eeeeff">
 * <td>root</td>
 * <td>目录</td>
 * <td>设置root为'/script' 会拼成http://staging.nikestore.com.cn/script/??jquery/jquery-1.4.2.min.js?16b3adc77c0d3e9df617d3178fe43445</td>
 * </tr>
 * </table>
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月14日 下午6:23:50
 * @since 1.0.7
 */
public class Concat extends AbstractDirective{

	/** The Constant log. */
	private static final Logger	log				= LoggerFactory.getLogger(Concat.class);

	// **************************************************************
	// 由于继承 字段是不会被覆盖的,所以下面的 两个方法 必须每个实现类重写
	// ****************************************************************
	/** 自定义标签名称. */
	private String				DIRECTIVE_NAME	= "concat";

	/** 自定义标签类型. */
	private int					DIRECTIVE_TYPE	= BLOCK;

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.velocity.directive.AbstractDirective#doRender(org.apache.velocity.context.InternalContextAdapter,
	 * java.io.Writer, org.apache.velocity.runtime.parser.node.Node)
	 */
	public boolean doRender(InternalContextAdapter internalContextAdapter,Writer writer,Node node) throws IOException,
			ResourceNotFoundException,ParseErrorException,MethodInvocationException{

		HttpConcatParam httpConcatParam = getHttpConcatParam(internalContextAdapter, node);
		if (null == httpConcatParam){
			log.warn("httpConcatParam is isNullOrEmpty,will not concat,just return");
			return true;
		}

		String writeContent = HttpConcatUtil.getWriteContent(httpConcatParam);
		writer.write(writeContent);
		return true;
	}

	/**
	 * 获得 http concat content.
	 * 
	 * @param internalContextAdapter
	 *            the internal context adapter
	 * @param node
	 *            the node
	 * @return the http concat content
	 * @throws IOException
	 *             the IO exception
	 */
	private HttpConcatParam getHttpConcatParam(InternalContextAdapter internalContextAdapter,Node node) throws IOException{
		// block content
		String blockContent = getBlockContent(internalContextAdapter, node);

		// 如果文字 isNullOrEmpty 那么仅仅 log warn
		if (Validator.isNullOrEmpty(blockContent)){
			log.warn("blockContent is isNullOrEmpty...,you should write source list between #{} and #end", DIRECTIVE_NAME);
			return null;
		}
		// **********************parse params******************************************************

		String type = "";
		String version = "";
		String domain = "";
		String root = "";
		Boolean httpConcatSupport = null;

		// loop through all "params"
		for (int i = 0, j = node.jjtGetNumChildren(); i < j; i++){
			Node jjtGetChild = node.jjtGetChild(i);
			if (null != jjtGetChild){
				if (!(jjtGetChild instanceof ASTBlock)){
					// reading and casting inline parameters
					Object value = jjtGetChild.value(internalContextAdapter);
					if (i == 0){
						type = "" + value;
					}else if (i == 1){
						version = "" + value;
					}else if (i == 2){
						domain = "" + value;
					}else if (i == 3){
						root = "" + value;
					}else if (i == 4){
						httpConcatSupport = Boolean.parseBoolean("" + value);
					}else{
						break;
					}
				}

				// else{
				// // reading block content and rendering it
				// StringWriter stringWriter = new StringWriter();
				// jjtGetChild.render(internalContextAdapter, stringWriter);
				//
				// blockContent = blockContent.toString();
				// break;
				// }
			}
		}

		// *******************************************************************************************
		// 转成item src list
		List<String> itemSrcList = toItemSrcList(blockContent);

		HttpConcatParam httpConcatParam = new HttpConcatParam();

		httpConcatParam.setDomain(domain);
		httpConcatParam.setRoot(root);
		httpConcatParam.setType(type);
		httpConcatParam.setVersion(version);
		httpConcatParam.setItemSrcList(itemSrcList);
		httpConcatParam.setHttpConcatSupport(httpConcatSupport);

		return httpConcatParam;
	}

	/**
	 * 获得 block content.
	 * 
	 * @param internalContextAdapter
	 *            the internal context adapter
	 * @param node
	 *            the node
	 * @return the block content
	 * @throws IOException
	 *             the IO exception
	 */
	private String getBlockContent(InternalContextAdapter internalContextAdapter,Node node) throws IOException{
		int jjtGetNumChildren = node.jjtGetNumChildren();
		Node endNode = node.jjtGetChild(jjtGetNumChildren - 1);

		StringWriter stringWriter = new StringWriter();
		endNode.render(internalContextAdapter, stringWriter);

		String blockContent = stringWriter.toString();
		return blockContent;
	}

	// ***************************************************************

	/**
	 * 获得 items array.
	 * 
	 * @param blockContent
	 *            内容,目前 以 \n 分隔
	 * @return the items array
	 */
	private List<String> toItemSrcList(String blockContent){
		String regex = "\n";
		String[] items = blockContent.trim().split(regex);
		int length = items.length;

		List<String> list = new ArrayList<String>(length);
		for (int i = 0; i < length; ++i){
			String item = items[i];
			// 忽视空行
			if (Validator.isNotNullOrEmpty(item)){
				// 去除空格
				list.add(item.trim());
			}
		}
		return list;
	}

	// ***************************************************************
	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getName()
	 */
	public String getName(){
		if (log.isDebugEnabled()){
			log.debug("DIRECTIVE_NAME:{}", DIRECTIVE_NAME);
		}
		return DIRECTIVE_NAME;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getType()
	 */
	public int getType(){
		if (log.isDebugEnabled()){
			log.debug("DIRECTIVE_TYPE:{}", DIRECTIVE_TYPE);
		}
		return DIRECTIVE_TYPE;
	}
}