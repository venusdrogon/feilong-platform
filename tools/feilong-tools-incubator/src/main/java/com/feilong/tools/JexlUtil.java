/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.tools;

import java.util.Map;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.ObjectUtil;
import com.feilong.commons.core.util.Validator;

/**
 * common-jexl 工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 25, 2011 2:29:59 PM
 */
public class JexlUtil{

	private static final Logger	log			= LoggerFactory.getLogger(JexlUtil.class);

	private static JexlEngine	jexlEngine	= new JexlEngine();

	/**
	 * 解析表达式
	 * 
	 * @param expressionString
	 *            需要解析的表达式
	 * @param mapContext
	 *            参数
	 * @return boolean
	 */
	public static boolean evaluateBoolean(String expressionString,Map<String, Object> mapContext){
		Object object = evaluate(expressionString, mapContext);
		return ObjectUtil.toBoolean(object);
	}

	/**
	 * 解析表达式
	 * 
	 * @param expressionString
	 *            需要解析的表达式
	 * @param mapContext
	 *            参数
	 * @return Object
	 */
	public static Object evaluate(String expressionString,Map<String, Object> mapContext){
		if (Validator.isNullOrEmpty(expressionString)){
			throw new IllegalArgumentException("expression can't be null!");
		}
		/**
		 * 初始化一个JexlContext对象，它代表一个执行JEXL表达式的上下文环境
		 */
		JexlContext jexlContext = null;
		if (Validator.isNullOrEmpty(mapContext)){
			log.info("input param \"mapContext\" is null,the expression value is \"{}\"", expressionString);
		}else{
			jexlContext = new MapContext();
			for (Map.Entry<String, Object> entry : mapContext.entrySet()){
				jexlContext.set(entry.getKey(), entry.getValue());
			}
		}
		// JexlInfo jexlInfo=new JexlInfo(){
		//			
		// @Override
		// public String debugString(){
		//				 
		// return null;
		// }
		//			
		// @Override
		// public DebugInfo debugInfo(){
		//			 
		// return null;
		// }
		// };
		/**
		 * 用ExpressionFactory类的静态方法createExpression创建一个Expression对象
		 */
		Expression expression = jexlEngine.createExpression(expressionString);
		/**
		 * 对这个Expression对象求值，传入执行JEXL表达式的上下文环境对象
		 */
		Object object = expression.evaluate(jexlContext);
		log.info(expression.dump());
		return object;
	}
}
