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
package org.springframework.expression;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.feilong.test.User;

/**
 * The Class ExpressionParserTest.
 */
@SuppressWarnings("all")
public class ExpressionParserTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ExpressionParserTest.class);

    /**
     * Test.
     */
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
