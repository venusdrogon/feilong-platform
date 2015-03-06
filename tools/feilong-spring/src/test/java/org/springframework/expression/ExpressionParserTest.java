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
package org.springframework.expression;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.feilong.test.User;

@SuppressWarnings("all")
public class ExpressionParserTest{

    private static final Logger log = LoggerFactory.getLogger(ExpressionParserTest.class);

    @Test
    public void test(){

        //  The constructor arguments are name, age.
        User user2 = new User("jinxin", 29);
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("name.equals('jinxin') and (4+3)==(7-0)");
        Object name = null;
        //		EvaluationContext context = new StandardEvaluationContext(user);
        //		  name = exp.getValue(context);
        //log.info(name);
        //exp = parser.parseExpression("name");
        name = expression.getValue(user2, Boolean.class);
        log.info("" + name);
    }
}
