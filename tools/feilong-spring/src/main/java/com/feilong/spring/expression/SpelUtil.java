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
package com.feilong.spring.expression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * The Class SpelUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 25, 2011 4:20:09 PM
 */
public class SpelUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger		log					= LoggerFactory.getLogger(SpelUtil.class);

	/** The expression parser. */
	private static ExpressionParser	expressionParser	= new SpelExpressionParser();

	/**
	 * Gets the value.
	 * 
	 * @param expressionString
	 *            the expression string
	 * @return the value
	 */
	public static Object getValue(String expressionString){
		Expression expression = expressionParser.parseExpression(expressionString);
		//		String message = expression.getValue(String.class);
		//		log.info(message);
		return expression.getValue();
	}
}
