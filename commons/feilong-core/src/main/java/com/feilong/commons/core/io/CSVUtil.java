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
package com.feilong.commons.core.io;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.Constants;
import com.feilong.commons.core.util.ObjectUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 飞龙cvs工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-26 上午01:26:47
 * @since 1.0
 */
public final class CSVUtil{

	/** The Constant log. */
	private final static Logger	log						= LoggerFactory.getLogger(CSVUtil.class);

	/** 转义引号用的字符 ". */
	private static final char	ESCAPE_CHARACTER		= '"';

	/** 默认的引号字符 "引号. */
	private static final char	DEFAULT_QUOTE_CHARACTER	= '"';

	/**
	 * \\u转义字符的意思是“\\u后面的1-4位16进制数表示的Unicode码对应的汉字”,而Unicode 0000 代表的字符是 NUL，也就是空的意思，<br>
	 * 如果把这个字符输出到控制台，显示为空格.
	 */
	private static final char	NO_QUOTE_CHARACTER		= '\u0000';

	/**
	 * 写cvs文件(默认使用GBK编码).
	 * 
	 * @param fileName
	 *            文件名称,全路径,自动生成不存在的父文件夹
	 * @param columnTitles
	 *            列标题,可以为空
	 * @param dataList
	 *            数据数组,可以带列名
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @since 1.0
	 */
	public final static void write(String fileName,String[] columnTitles,List<Object[]> dataList) throws IOException{
		write(fileName, columnTitles, dataList, new CSVParams());
	}

	/**
	 * 写cvs文件.
	 * 
	 * @param fileName
	 *            文件名称,全路径,自动生成不存在的父文件夹
	 * @param columnTitles
	 *            列标题,可以为空
	 * @param dataList
	 *            数据数组,可以带列名
	 * @param csvParams
	 *            the csv params
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public final static void write(String fileName,String[] columnTitles,List<Object[]> dataList,CSVParams csvParams) throws IOException{
		// 标题和内容都是空,没有任何意义,不创建文件
		if (Validator.isNullOrEmpty(columnTitles) && Validator.isNullOrEmpty(dataList)){
			log.error("columnTitles and linkedList all null!");
		}else{
			if (Validator.isNullOrEmpty(dataList)){
				dataList = new LinkedList<Object[]>();
			}
			if (Validator.isNotNullOrEmpty(columnTitles)){
				dataList.add(0, columnTitles);
			}
			log.info("begin write file:" + fileName);
			IOWriteUtil.write(fileName, getWriteContent(dataList, csvParams), csvParams.getEncode());
		}
	}

	// *******************************************************************************
	/**
	 * Writes the entire list to a CSV file. The list is assumed to be a String[]
	 * 
	 * @param allLines
	 *            a List of String[], with each String[] representing a line of the file.
	 * @param csvParams
	 *            the csv params
	 * @return the write content
	 */
	private final static String getWriteContent(List<Object[]> allLines,CSVParams csvParams){
		if (Validator.isNotNullOrEmpty(allLines)){
			StringBuffer sb = new StringBuffer();
			for (Object[] nextLine : allLines){
				sb.append(getWriteContentLine(nextLine, csvParams));
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * Writes the next line to the file.
	 * 
	 * @param line
	 *            the line
	 * @param csvParams
	 *            the csv params
	 * @return the write content line
	 */
	private final static String getWriteContentLine(Object[] line,CSVParams csvParams){
		// *******************用于扩展**********************************
		char separator = csvParams.getSeparator();
		char quotechar = DEFAULT_QUOTE_CHARACTER;
		String lineEnd = Constants.LINE_SEPARATOR;
		// *************************************************************
		StringBuffer sb = new StringBuffer();
		int length_line = line.length;
		for (int i = 0; i < length_line; ++i){
			// 分隔符，列为空也要表达其存在。
			if (i != 0){
				sb.append(separator);
			}
			String currentElement = ObjectUtil.toString(line[i]);
			// *****************************************************
			if (currentElement != null){
				// *****************************************************
				if (quotechar != NO_QUOTE_CHARACTER){
					sb.append(quotechar);
				}
				// *****************************************************
				int length = currentElement.length();
				for (int j = 0; j < length; ++j){
					char currentChar = currentElement.charAt(j);
					if (currentChar == quotechar || currentChar == ESCAPE_CHARACTER){
						sb.append(ESCAPE_CHARACTER).append(currentChar);
					}else{
						sb.append(currentChar);
					}
				}
				// ******************************************************
				if (quotechar != NO_QUOTE_CHARACTER){
					sb.append(quotechar);
				}
				// *****************************************************
			}
		}
		sb.append(lineEnd);
		return sb.toString();
	}
}
