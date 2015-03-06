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

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.UncheckedIOException;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 20, 2012 7:09:40 PM
 */
public class IKAnalyzerUtilTest{

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
