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
package com.feilong.commons.core.io;

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
	private final static Logger	log	= LoggerFactory.getLogger(CSVUtil.class);

	/**
	 * 写cvs文件(默认使用GBK编码).
	 * 
	 * @param fileName
	 *            文件名称,全路径,自动生成不存在的父文件夹
	 * @param columnTitles
	 *            列标题,可以为空
	 * @param dataList
	 *            数据数组,可以带列名
	 * @since 1.0
	 */
	public final static void write(String fileName,String[] columnTitles,List<Object[]> dataList){
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
	 */
	public final static void write(String fileName,String[] columnTitles,List<Object[]> dataList,CSVParams csvParams){
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
		char quotechar = CSVParams.DEFAULT_QUOTE_CHARACTER;
		String lineEnd = Constants.lineSeparator;
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
				if (quotechar != CSVParams.NO_QUOTE_CHARACTER){
					sb.append(quotechar);
				}
				// *****************************************************
				int length = currentElement.length();
				for (int j = 0; j < length; ++j){
					char currentChar = currentElement.charAt(j);
					if (currentChar == quotechar || currentChar == CSVParams.ESCAPE_CHARACTER){
						sb.append(CSVParams.ESCAPE_CHARACTER).append(currentChar);
					}else{
						sb.append(currentChar);
					}
				}
				// ******************************************************
				if (quotechar != CSVParams.NO_QUOTE_CHARACTER){
					sb.append(quotechar);
				}
				// *****************************************************
			}
		}
		sb.append(lineEnd);
		return sb.toString();
	}
}
