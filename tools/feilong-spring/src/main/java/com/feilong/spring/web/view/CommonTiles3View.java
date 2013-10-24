package com.feilong.spring.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.servlet.ServletContextUtil;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.servlet.http.SessionUtil;

public class CommonTiles3View extends TilesView{

	private static final Logger	log	= LoggerFactory.getLogger(CommonTiles3View.class);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.view.tiles2.TilesView#renderMergedOutputModel(java.util.Map,
	 * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request,HttpServletResponse response)
			throws Exception{
		super.renderMergedOutputModel(model, request, response);

		if (log.isDebugEnabled()){
			Map<String, Object> attributeMap = RequestUtil.getAttributeMap(request);
			// 这个key format 有问题
			attributeMap.remove(Config.FMT_LOCALIZATION_CONTEXT + ".request");
			// javax.servlet.jsp.jstl.fmt.localizationContext.request
			// java.lang.IllegalAccessException:
			// Class loxia.support.json.JSONObject can not access a member of class
			// org.springframework.web.servlet.support.JstlUtils$SpringLocalizationContext
			// with modifiers "public"
			// model 已经 exposeModelAsRequestAttributes
			Object[] argsObjects = { RequestUtil.getRequestAllURL(request),
					// , JsonFormatUtil.defaultPropFilterStr + ",-" + Config.FMT_LOCALIZATION_CONTEXT + ".request"
					JsonFormatUtil.format(attributeMap),
					JsonFormatUtil.format(SessionUtil.getAttributeMap(request.getSession())),
					JsonFormatUtil.format(ServletContextUtil.getAttributeMap(request.getSession().getServletContext())) };

			log.debug(
					"requestAllURL:{},\n request attributeMap:{},\n session attributeMap:{} , \n servletContext attributeMap:{} ",
					argsObjects);
		}
	}
}
