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
package com.feilong.spring.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.enumeration.MimeType;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.servlet.http.ResponseUtil;

/**
 * The Class JsonView.
 */
public class JsonView extends AbstractView{

	/** The Constant log. */
	private static final Logger	log						= LoggerFactory.getLogger(JsonView.class);

	/**
	 * Default content type. Overridable as bean property.
	 */
	public static final String	JSON_FILTER_STR			= "JSON_FILTER_STR";

	/** The Constant DEFAULT_CONTENT_TYPE. */
	public static final String	DEFAULT_CONTENT_TYPE	= MimeType.JSON.getMime();

	/** The Constant DEFAULT_ENCODING. */
	public static final String	DEFAULT_ENCODING		= CharsetType.UTF8;

	/** The prefix json. */
	private boolean				prefixJson				= false;

	/** The encoding. */
	private String				encoding;

	/**
	 * Instantiates a new json view.
	 */
	public JsonView(){
		setContentType(DEFAULT_CONTENT_TYPE);
		setEncoding(DEFAULT_ENCODING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.view.AbstractView#prepareResponse(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void prepareResponse(HttpServletRequest request,HttpServletResponse response){
		response.setContentType(getContentType());
		response.setCharacterEncoding(encoding);

		ResponseUtil.setNoCacheHeader(response);
		// response.setHeader("Pragma", "No-cache");
		// response.setHeader("Cache-Control", "no-cache");
		// response.setDateHeader("Expires", 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request,HttpServletResponse response)
					throws Exception{

		String filterString = (String) model.get(JSON_FILTER_STR);
		model.remove(JSON_FILTER_STR);

		// 这个key 有问题
		// see com.jumbo.brandstore.web.view.CommonTilesView.renderMergedOutputModel(Map<String, Object>, HttpServletRequest,
		// HttpServletResponse)
		model.remove(Config.FMT_LOCALIZATION_CONTEXT + ".request");

		String writeStr = "";
		if (prefixJson){
			writeStr = "{} && ";
			response.getWriter().write(writeStr);

			if (log.isDebugEnabled()){
				log.debug(writeStr);
			}
		}
		if (model.size() > 0){
			if (filterString == null){
				filterString = "**";
			}

			// writeStr = JsonUtil.format(model, filterString);
			writeStr = JsonUtil.format(model);
		}else{
			writeStr = new JSONObject().toString(4);
		}

		response.getWriter().write(writeStr);

		if (log.isDebugEnabled()){
			Object[] objects = { RequestUtil.getRequestFullURL(request, CharsetType.UTF8), filterString, writeStr };
			log.debug("{} \t filterString:[{}] \n{}", objects);
		}
	}

	/**
	 * Indicates whether the JSON output by this view should be prefixed with "{@code &&}". Default is false.
	 * <p>
	 * Prefixing the JSON string in this manner is used to help prevent JSON Hijacking. The prefix renders the string syntactically invalid
	 * as a script so that it cannot be hijacked. This prefix does not affect the evaluation of JSON, but if JSON validation is performed on
	 * the string, the prefix would need to be ignored.
	 * 
	 * @param prefixJson
	 *            the new prefix json
	 */
	public void setPrefixJson(boolean prefixJson){
		this.prefixJson = prefixJson;
	}

	/**
	 * Sets the encoding.
	 * 
	 * @param encoding
	 *            the new encoding
	 */
	public void setEncoding(String encoding){
		this.encoding = encoding;
	}
}
