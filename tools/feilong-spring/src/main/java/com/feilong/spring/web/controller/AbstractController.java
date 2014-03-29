package com.feilong.spring.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.RequestUtil;

/**
 * 通用的 父类 AbstractController
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 23, 2013 10:54:51 PM
 */
public abstract class AbstractController{

	protected static final Logger	log							= LoggerFactory.getLogger(AbstractController.class);

	/** 包装的单价 */
	public static final Double		PACK_PRICE					= 20.0;

	public static final String		header_with_ajax_springmvc	= RequestUtil.header_XRequestedWith + "="
																		+ RequestUtil.header_XRequestedWith_value_ajax;

	@Resource
	protected ApplicationContext	context;

	// @Autowired
	// protected ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));

		// can be override to add new binding
		initBinderInternal(webDataBinder);
	}

	/**
	 * can be override to add new binding
	 * 
	 * @param webDataBinder
	 *            webDataBinder
	 */
	protected void initBinderInternal(WebDataBinder webDataBinder){}

	protected String getServerRootWithContextPath(HttpServletRequest request){
		return "http://".concat(request.getServerName())
				.concat((request.getServerPort() == 80 ? "" : ":".concat(String.valueOf(request.getServerPort()))))
				.concat(request.getContextPath());
	}

	/**
	 * 生成spring 的跳转路径<br>
	 * e.g. getSpringRedirectPath("/shoppingcart") <br>
	 * 注:不需要你手工的 拼接request.getContextPath()
	 * 
	 * @param targetUrl
	 *            如果是相对根目录路径 只需要传递 如"/shoppingcart" spring会自动添加request.getContextPath() <br>
	 *            也可以传入绝对路径 如:http://www.baidu.com
	 * @return
	 */
	protected String getSpringRedirectPath(String targetUrl){
		return UrlBasedViewResolver.REDIRECT_URL_PREFIX + targetUrl;
	}

	/**
	 * 生成 spring Forward 路径
	 * 
	 * @param forwardUrl
	 * @return
	 */
	protected String getSpringForwardPath(String forwardUrl){
		return UrlBasedViewResolver.FORWARD_URL_PREFIX + forwardUrl;
	}

	// **********************************************************************************************

	/**
	 * 获得消息信息
	 * 
	 * @param code
	 *            code
	 * @param args
	 *            args
	 * @return
	 */
	protected String getMessage(Integer errorCode){
		Object[] args = null;
		return getMessage(errorCode, args);
	}

	/**
	 * 获得消息信息
	 * 
	 * @param code
	 *            code
	 * @param args
	 *            args
	 * @return
	 */
	protected String getMessage(Integer errorCode,Object...args){
		if (Validator.isNotNullOrEmpty(errorCode)){
			return getMessage("ExceptionResolver.BUSINESS_EXCEPTION_PREFIX" + errorCode, args);
		}
		return null;
	}

	/**
	 * 获得消息信息
	 * 
	 * @param code
	 *            code
	 * @param args
	 *            args
	 * @return
	 */
	protected String getMessage(String code,Object...args){
		if (Validator.isNotNullOrEmpty(code)){
			return context.getMessage(code, args, LocaleContextHolder.getLocale());
		}
		return null;
	}

	/**
	 * 获得消息信息
	 * 
	 * @param messageSourceResolvable
	 *            适用于 ObjectError 以及 FieldError
	 * @return
	 */
	protected String getMessage(MessageSourceResolvable messageSourceResolvable){
		return context.getMessage(messageSourceResolvable, LocaleContextHolder.getLocale());
	}
}
