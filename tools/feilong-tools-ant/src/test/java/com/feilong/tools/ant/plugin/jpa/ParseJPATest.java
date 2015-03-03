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
package com.feilong.tools.ant.plugin.jpa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.apache.tools.ant.DirectoryScanner;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.StringBuilderUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.ant.DirectoryScannerUtil;
import com.feilong.tools.ant.plugin.jpa.command.Column;
import com.feilong.tools.ant.plugin.jpa.command.JpaConstants;

/**
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月26日 下午2:06:28
 * @since 1.0.7
 */
public class ParseJPATest{

	private static final Logger	log	= LoggerFactory.getLogger(ParseJPATest.class);

	/**
	 * Test execute target1.
	 * 
	 * @throws IOException
	 */
	@Test
	public final void getMyTableList() throws IOException{
		//扫描log目录
		String scanLogFloder = SystemUtils.USER_HOME + "/feilong/dbscan/";
		//		FileResource fileResource=new FileResource();
		//		fileResource.setDirectory(directory);
		//		FileScanner fileScanner=new  DependScanner();

		String inputName = "product";
		inputName = "trade";
		inputName = "member";
		inputName = "platform";

		String longTextFile = scanLogFloder + "longtext " + inputName + ".txt";
		String ouputfilePath = scanLogFloder + "longtext " + inputName + "-output.txt";

		List<Column> longTextColumnlist = aa(longTextFile);

		//**************************************************************************
		List<String> list = new ArrayList<String>();

		//		list.add("mp2-dataCenter");
		//list.add("mp2-member");
		list.add("mp2-platform");
		//		list.add("mp2-product");
		//list.add("mp2-trade");

		for (String projectName : list){

			String basedir = "E:/Workspaces/baozun-else/mp2-new/mp2-modules/" + projectName;

			String[] includes = { "**/repo/*.java" };

			String[] excludes = { //
			"**/package-info.java",//
					"**/BaseModel.java" };

			DirectoryScanner directoryScanner = new DirectoryScanner();

			directoryScanner.setBasedir(basedir);
			directoryScanner.setIncludes(includes);
			directoryScanner.setExcludes(excludes);

			directoryScanner.scan();

			Map<String, Object> map = DirectoryScannerUtil.getDirectoryScannerMapForLog(directoryScanner);

			if (log.isDebugEnabled()){
				log.debug(JsonUtil.format(map));
			}

			//******************************************************************
			ParseJPA parseJPA = new ParseJPA(directoryScanner);

			String format = JsonUtil.format(parseJPA);
			if (log.isDebugEnabled()){
				log.debug(format);
			}

			IOWriteUtil.write(scanLogFloder + projectName + ".txt", format);

			//******************************************************************

			outputLongtextAlter(longTextColumnlist, parseJPA, ouputfilePath);
		}
	}

	/**
	 * 将longtext 类型的输出 需要alter 或者其他
	 * 
	 * @param longTextColumnlist
	 * @param parseJPA
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	private void outputLongtextAlter(List<Column> longTextColumnlist,ParseJPA parseJPA,String ouputfilePath)
					throws IllegalArgumentException,IOException{
		StringBuilder sb = new StringBuilder();

		List<Column> totalColumnList = parseJPA.getColumnList();
		log.debug(JsonUtil.format(longTextColumnlist));
		for (Column column : longTextColumnlist){

			//查到了
			boolean find = false;
			for (Column _column : totalColumnList){

				//表相同
				String lowerCaseTable = column.getTableName().toLowerCase();
				String lowerCaseColumn = column.getColumnName().toLowerCase();
				if (lowerCaseTable.equals(_column.getTableName().toLowerCase()) //
								&& lowerCaseColumn.equals(_column.getColumnName().toLowerCase())//
				){
					String length = _column.getLength();

					if (Validator.isNullOrEmpty(length)){
						String format = "{}, {} ,java code length is null \n";
						String warn = Slf4jUtil.formatMessage(format, lowerCaseTable, lowerCaseColumn);
						StringBuilderUtil.appendTextWithLn(sb, warn);
					}else{

						if (Integer.parseInt(length) > JpaConstants.MYSQL5_MAXLENGTH_VARCHAR_UTF8){
							String format = "\n\nin java code,find [{}] [{}], but length is [{}]>[{}] \n\n\n";
							String warn = Slf4jUtil.formatMessage(
											format,
											lowerCaseTable,
											lowerCaseColumn,
											length,
											JpaConstants.MYSQL5_MAXLENGTH_VARCHAR_UTF8);
							StringBuilderUtil.appendTextWithLn(sb, warn);
						}else{
							String alterSql = Slf4jUtil.formatMessage(
											JpaConstants.TEMPLATE_MODIFY_COLUMN,
											column.getTableName(),
											column.getColumnName(),
											"varchar",
											length);
							log.debug("{}", alterSql);
							StringBuilderUtil.appendTextWithLn(sb, alterSql);
						}
					}
					find = true;
					break;
				}
			}

			//如果找不到
			if (!find){
				String format = JsonUtil.format(column);
				if (log.isDebugEnabled()){
					log.debug("not find column:{}", format);
				}
				StringBuilderUtil.appendTextWithLn(sb, format);
			}
		}

		IOWriteUtil.write(ouputfilePath, sb.toString());
	}

	/**
	 * @param longTextFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private List<Column> aa(String longTextFile) throws FileNotFoundException,IOException{
		Reader reader = new FileReader(longTextFile);
		LineNumberReader lineNumberReader = new LineNumberReader(reader);

		String line = null;

		List<Column> columnlist = new ArrayList<Column>();

		while ((line = lineNumberReader.readLine()) != null){
			int lineNumber = lineNumberReader.getLineNumber();
			//			if (log.isDebugEnabled()){
			//				log.debug("the param lineNumber:{}", lineNumber);
			//			}

			String[] split = line.split("\t");

			Column column = new Column();

			column.setTableName(split[0]);
			column.setColumnName(split[1]);
			//column.setLength(columnName);
			column.setType(split[2]);
			columnlist.add(column);
		}

		lineNumberReader.close();

		return columnlist;

	}

}