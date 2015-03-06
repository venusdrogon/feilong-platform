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
package com.feilong.tools;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.UncheckedIOException;

/**
 * The Class IKAnalyzerUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 20, 2012 7:09:40 PM
 */
public class IKAnalyzerUtilTest{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(IKAnalyzerUtilTest.class);

    /**
     * Test method for {@link com.feilong.tools.IKAnalyzerUtil#getLexemeTexts(java.lang.String, boolean)}.
     */
    @Test
    public final void testGetLexemeTexts(){

        try{
            String text = "Chuck Taylor All Star Classic Boot 经典工装靴款";
            //			 String text = "Air force";
            //			 String text = "中华人民共和国";
            //			String text = "556426-600";

            String[] analizedText = IKAnalyzerUtil.getLexemeTexts(text, true);

            for (String string : analizedText){
                log.info(string);
            }

        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }
}
