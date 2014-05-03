package com.feilong.servlet.listener;

import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.StringUtil;
import com.feilong.servlet.ServletContextUtil;
import com.feilong.tools.json.JsonUtil;

/**
 * 初始化配置 监听器.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Aug 19, 2013 10:28:05 AM
 */
public class ServletInfoListener implements ServletContextListener{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ServletInfoListener.class);

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent){
		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.log("Initialize InitConfigurationListener for application...");
		servletContext.log("Charset.defaultCharset().name():" + Charset.defaultCharset().name());

		Map<String, String> initParameterMap = ServletContextUtil.getInitParameterMap(servletContext);

		if (log.isInfoEnabled()){
			log.info("initParameterMap:{}", JsonUtil.format(initParameterMap));
		}

		initDebugServlet(servletContext);
	}

	/**
	 * 显示 servelt 信息.
	 * 
	 * @param servletContext
	 *            the servlet context
	 */
	private void initDebugServlet(ServletContext servletContext){
		// 返回servlet运行的servlet 容器的版本和名称。
		servletContext.log("servletContext.getServerInfo():" + servletContext.getServerInfo());
		// 返回这个servlet容器支持的Java Servlet API的主要版本。所有符合2.5版本的实现，必须有这个方法返回的整数2。
		// 返回这个servlet容器支持的Servlet API的次要版本。所有符合2.5版本的实现，必须有这个方法返回整数5。
		String servletVersion = StringUtil.format(
				"servlet version:%s.%s",
				servletContext.getMajorVersion(),
				servletContext.getMinorVersion());
		servletContext.log(servletVersion);
		String contextPath = servletContext.getContextPath();
		servletContext.log("[servletContext.getContextPath():]" + contextPath);
		servletContext.log("[servletContext.getServletContextName():]" + servletContext.getServletContextName());
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce){
		// currently do nothing
	}
}
