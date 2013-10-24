/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.servlet.http;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.ObjectUtil;

/**
 * SessionUtil 操作session
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-7-6 下午02:10:33
 * @version 1.1 Jan 15, 2013 2:31:32 PM 进行精简
 */
public final class SessionUtil{

	private final static Logger	log	= LoggerFactory.getLogger(SessionUtil.class);

	private SessionUtil(){}

	/**
	 * 遍历显示session的attribute,将 name /attributeValue 存入到map
	 * 
	 * @param session
	 */
	public static Map<String, Object> getAttributeMap(HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()){
			String name = attributeNames.nextElement();
			Object attributeValue = session.getAttribute(name);
			map.put(name, attributeValue);
		}
		return map;
	}

	// *********************************************************************************************************

	/**
	 * 获取属性值Integer类型
	 * 
	 * @param attributeName
	 *            session里面的 属性名称
	 * @param object
	 *            request or session
	 * @return 获取属性值Integer类型
	 */
	public static Integer getAttributeToInteger(String attributeName,Object object){
		Object value = getAttribute(attributeName, object);
		return ObjectUtil.toInteger(value);
	}

	/**
	 * 获取属性值BigDecimal类型
	 * 
	 * @param attributeName
	 *            session里面的 属性名称
	 * @param object
	 *            request or session
	 * @return 获取属性值BigDecimal类型
	 */
	public static BigDecimal getAttributeToBigDecimal(String attributeName,Object object){
		Object value = getAttribute(attributeName, object);
		return ObjectUtil.toBigDecimal(value);
	}

	/**
	 * 将属性值转换成字符串
	 * 
	 * @param attributeName
	 *            属性名称
	 * @param object
	 *            request or session
	 * @return 将属性值转换成字符串
	 */
	public static String getAttributeToString(String attributeName,Object object){
		return ObjectUtil.toString(getAttribute(attributeName, object));
	}

	/**
	 * 直接获取属性值
	 * 
	 * @param attributeName
	 *            属性名称
	 * @param object
	 *            request or session
	 * @return 直接获取属性值
	 */
	public static Object getAttribute(String attributeName,Object object){
		HttpSession session = null;
		if (object instanceof HttpServletRequest){
			session = ((HttpServletRequest) object).getSession();
		}else if (object instanceof HttpSession){
			session = (HttpSession) object;
		}else{
			throw new IllegalArgumentException("object must instanceof HttpServletRequest/HttpSession");
		}
		return session.getAttribute(attributeName);
	}

	/**
	 * 替换session,防止利用JSESSIONID 伪造url进行session hack
	 * 
	 * @param request
	 *            request
	 */
	public static HttpSession replaceSession(HttpServletRequest request){
		// 当session存在时返回该session，否则不会新建session，返回null
		HttpSession session = request.getSession(false);
		if (null != session){
			// getSession()/getSession(true)：当session存在时返回该session，否则新建一个session并返回该对象
			session = request.getSession();
			log.debug("old session: {}", session.getId());
			Map<String, Object> map = new HashMap<String, Object>();
			Enumeration enumeration = session.getAttributeNames();
			while (enumeration.hasMoreElements()){
				String key = (String) enumeration.nextElement();
				map.put(key, session.getAttribute(key));
			}
			session.invalidate();
			session = request.getSession();
			log.debug("new session: {}", session.getId());
			for (String key : map.keySet()){
				session.setAttribute(key, map.get(key));
			}
		}else{
			// 是null 新建一个
			session = request.getSession();
		}
		return session;
	}
	// ********************下面4个方法不建议在这里使用 **********************************************************************
	// /**
	// * ajax验证是否通过验证码
	// *
	// * @param session
	// * @return 通过返回true
	// */
	// public boolean isPassValidateCode_ajax(String code,HttpSession session){
	// return isPassValidateCode(code, session);
	// }

	// /**
	// * 是否通过验证码
	// *
	// * <pre>
	// * 验证码的文本框 name 为 code
	// * 验证码作用域 属性名为FeiLongConstants.session.validateCode
	// * </pre>
	// *
	// * @param request
	// * 当前请求
	// * @return 通过返回true
	// */
	// public static boolean isPassValidateCode(HttpServletRequest request){
	// String code = request.getParameter("code");
	// return isPassValidateCode(code, request.getSession());
	// }

	// /**
	// * 验证是否通过验证码
	// *
	// * @param code
	// * 验证码
	// * @param session
	// * session
	// * @return 通过返回true
	// */
	// public static boolean isPassValidateCode(String code,HttpSession session){
	// String validateCode = SessionUtil.getAttributeObjectToString(Constants.Session.validateCode, session);
	// return ObjectUtil.equalsNotNull(code, validateCode);
	// }

	// /**
	// * 获得验证码,一般用于dwr调用
	// *
	// * @param session
	// * session
	// * @return 获得验证码
	// */
	// public String getValidateCode_ajax(HttpSession session){
	// return getAttributeObjectToString(Constants.Session.validateCode, session);
	// }

	// ********************这两个方法不准 以后需要的话 重新写 **********************************************************************
	// /**
	// * 是否不存在属性名
	// *
	// * @param attributeName
	// * 属性名
	// * @param object
	// * request or session
	// * @return 不存在返回true
	// */
	// public static boolean isNotExistAttributeName(String attributeName,Object object){
	// return !isExistAttributeName(attributeName, object);
	// }

	// /**
	// * 是否存在属性名
	// *
	// * @param attributeName
	// * 属性名
	// * @param object
	// * request or session
	// * @return 存在返回true
	// */
	// public static boolean isExistAttributeName(String attributeName,Object object){
	// return Validator.isNotNullOrEmpty(getAttributeObject(attributeName, object));
	// }
}
