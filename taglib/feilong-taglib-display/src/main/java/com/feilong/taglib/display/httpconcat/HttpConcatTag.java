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
package com.feilong.taglib.display.httpconcat;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.AbstractCommonTag;
import com.feilong.taglib.display.httpconcat.command.HttpConcatParam;

/**
 * 根据 TENGINE_SUPPORT判断 将参数动态生成tengine插件的形式或者普通js/css的形式<br>
 * <br>
 * 作用:遵循Yahoo!前端优化准则第一条：减少HTTP请求发送次数<br>
 * 这一功能可以组合Javascript 以及 Css文件<br>
 * 使用方法:<br>
 * a)以两个问号(??)激活combo<br>
 * b)多文件之间用半角逗号(,)分开<br>
 * c)用一个?来便是时间戳<br>
 * .
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014年5月4日 下午11:45:20
 */
public class HttpConcatTag extends AbstractCommonTag{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -3447592871482978718L;

	/** 类型css/js. */
	private String				type;

	/** 版本号. */
	private String				version;

	/**
	 * 根目录<br>
	 * 如果设置root为'/script' 会拼成http://staging.nikestore.com.cn/script/??jquery/jquery-1.4.2.min.js?2013022801
	 */
	private String				root;

	/** The domain. */
	private String				domain;

	// ***********************************************************************************
	/*
	 * (non-Javadoc)
	 * @see com.feilong.taglib.base.AbstractCommonTag#writeContent()
	 */
	@Override
	protected Object writeContent(){
		String bodyContentSrc = bodyContent.getString();
		bodyContentSrc = bodyContentSrc.replaceAll("[\r\n\\s]", "");

		if (Validator.isNullOrEmpty(bodyContentSrc) || Validator.isNullOrEmpty(type)){
			return "";
		}

		List<String> itemSrcList = getItemSrcList(bodyContentSrc, new ArrayList<String>());

		HttpConcatParam httpConcatParam = new HttpConcatParam();
		httpConcatParam.setDomain(domain);
		httpConcatParam.setRoot(root);
		httpConcatParam.setType(type);
		httpConcatParam.setVersion(version);
		httpConcatParam.setItemSrcList(itemSrcList);

		return HttpConcatUtil.getWriteContent(httpConcatParam);
	}

	/**
	 * 递归获取bodyContent中的TengineFile.
	 * 
	 * @param bodyContentSrc
	 *            the body content
	 * @param fileList
	 *            the file list
	 * @return the file list
	 */
	private List<String> getItemSrcList(String bodyContentSrc,List<String> fileList){
		int begin = bodyContentSrc.indexOf(HttpConcatItemTag.BEGIN_TAG);
		int end = bodyContentSrc.indexOf(HttpConcatItemTag.END_TAG);
		if (begin < 0 || end < 0){
			return fileList;
		}
		fileList.add(bodyContentSrc.substring(begin + HttpConcatItemTag.BEGIN_TAG.length(), end));
		return getItemSrcList(bodyContentSrc.substring(end + HttpConcatItemTag.END_TAG.length()), fileList);
	}

	// ******************************************************************************

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException{
		print(this.writeContent());
		return EVAL_PAGE;
	}

	// **************************************************************************
	/*
	 * (non-Javadoc)
	 * @see com.feilong.taglib.base.AbstractCommonTag#doStartTag()
	 */
	@Override
	public int doStartTag(){
		return EVAL_BODY_BUFFERED;
	}

	/**
	 * Sets the 类型css/js.
	 * 
	 * @param type
	 *            the new 类型css/js
	 */
	public void setType(String type){
		this.type = type;
	}

	/**
	 * Sets the 版本号.
	 * 
	 * @param version
	 *            the new 版本号
	 */
	public void setVersion(String version){
		this.version = version;
	}

	/**
	 * Sets the 根目录<br>
	 * 如果设置root为'/script' 会拼成http://staging.
	 * 
	 * @param root
	 *            the new 根目录<br>
	 *            如果设置root为'/script' 会拼成http://staging
	 */
	public void setRoot(String root){
		this.root = root;
	}

	/**
	 * @param domain
	 *            the domain to set
	 */
	public void setDomain(String domain){
		this.domain = domain;
	}

}
