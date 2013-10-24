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
package com.feilong.tools;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

import com.feilong.commons.core.util.Validator;

/**
 * IKAnalyzer 工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 20, 2012 7:07:26 PM
 */
public final class IKAnalyzerUtil{

	private static final Logger	log	= LoggerFactory.getLogger(IKAnalyzerUtil.class);

	/**
	 * 分词情况
	 * 
	 * @param text
	 *            需要分词的 文本
	 * @param isMaxWordLength
	 *            是否最大分词,如果 text=中华人民共和国
	 *            <ul>
	 *            <li>false:(默认),分词器进行最细粒度切分 中华人民共和国 中华人民 中华 华人 人民共和国 人民 共和国 共和</li>
	 *            <li>true:分词器进行最大词长切 中华人民共和国</li>
	 *            </ul>
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 *             如果text isNullOrEmpty
	 * @since IK V3.1.1
	 */
	public static String[] getLexemeTexts(String text,boolean isMaxWordLength) throws IOException{
		if (Validator.isNullOrEmpty(text)){
			throw new IllegalArgumentException("text can't be null/empty!");
		}

		StringReader stringReader = new StringReader(text);
		// 分割
		IKSegmentation segmentation = new IKSegmentation(stringReader, isMaxWordLength);

		List<String> list = new ArrayList<String>();
		for (Lexeme lexeme = segmentation.next(); null != lexeme; lexeme = segmentation.next()){// lexeme 为IK分词器的语义单元对象，相当于Lucene中的Token词元对象
			// 每个语义单元放到list
			list.add(lexeme.getLexemeText());
		}

		return list.toArray(new String[list.size()]);
	}
}
