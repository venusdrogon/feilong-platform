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
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

import com.feilong.commons.core.util.Validator;

/**
 * IKAnalyzer 工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 20, 2012 7:07:26 PM
 */
public final class IKAnalyzerUtil{

	/**
	 * 分词情况.
	 * 
	 * @param text
	 *            需要分词的 文本
	 * @param isMaxWordLength
	 *            是否最大分词,如果 text=中华人民共和国
	 *            <ul>
	 *            <li>false:(默认),分词器进行最细粒度切分 中华人民共和国 中华人民 中华 华人 人民共和国 人民 共和国 共和</li>
	 *            <li>true:分词器进行最大词长切 中华人民共和国</li>
	 *            </ul>
	 * @return the lexeme texts
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
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
