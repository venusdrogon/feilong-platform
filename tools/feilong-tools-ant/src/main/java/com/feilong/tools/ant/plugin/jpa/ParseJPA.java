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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.DirectoryScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.util.RegexUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.ant.plugin.jpa.command.JpaConstants;
import com.feilong.tools.ant.plugin.jpa.command.MyTable;

/**
 * The Class ParseJPA.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月26日 下午2:02:38
 * @since 1.0.7
 */
public class ParseJPA implements Serializable,JpaConstants{

	/** The Constant serialVersionUID. */
	private static final long			serialVersionUID		= 288232184048495608L;

	/** The Constant log. */
	private static final Logger			log						= LoggerFactory.getLogger(ParseJPA.class);

	/** entity annotation 数量 . */
	private int							entityCount;

	/** table数量. */
	private int							tableCount;

	/** 主键熟练. */
	private int							idCount;

	/** The generated value count. */
	private int							generatedValueCount;

	/** The generated value identity count. */
	private int							generatedValueIdentityCount;

	/** 索引数量. */
	private int							indexCount;

	//****************************************************************************

	/** 总的数据表. */
	private List<String>				totalTable				= new ArrayList<String>();

	/** 总的index. */
	private List<String>				totalIndex				= new ArrayList<String>();

	/** 每个项目的index,key为项目名字. */
	private Map<String, List<String>>	indexGroupByProjectMap	= new HashMap<String, List<String>>();

	/** 每个项目的table,key为项目名字. */
	private Map<String, List<String>>	tableGroupByProjectMap	= new HashMap<String, List<String>>();

	/**
	 * Instantiates a new parses the jpa.
	 * 
	 * @param directoryScanner
	 *            the directory scanner
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ParseJPA(DirectoryScanner directoryScanner) throws IOException{
		int includedFilesCount = directoryScanner.getIncludedFilesCount();
		String[] includedFiles = directoryScanner.getIncludedFiles();

		//*******************************************************************************
		File basedir = directoryScanner.getBasedir();

		List<MyTable> list = new ArrayList<MyTable>();

		for (int i = 0; i < includedFiles.length; i++){
			String child = includedFiles[i];

			String projectName = FileUtil.getFileTopParentName(child);

			File file = new File(basedir, child);
			MyTable myTable = parseFileTpMyTable(file, projectName);

			if (null == myTable){
				continue;
			}else{
				list.add(myTable);
			}
		}
	}

	/**
	 * Parses the file tp my table.
	 * 
	 * @param file
	 *            the file
	 * @param projectName
	 *            the project name
	 * @return the my table
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private MyTable parseFileTpMyTable(File file,String projectName) throws IOException{
		MyTable myTable = new MyTable();
		myTable.setFileAbsolutePath(file.getAbsolutePath());
		myTable.setFileName(file.getName());
		myTable.setProjectName(projectName);

		Reader reader = new FileReader(file);
		LineNumberReader lineNumberReader = new LineNumberReader(reader);
		try{
			String line = null;
			while ((line = lineNumberReader.readLine()) != null){
				int lineNumber = lineNumberReader.getLineNumber();
				//log.debug("the param lineNumber:{}", lineNumber);

				//枚举 跳过
				if (line.contains("public enum")){
					log.warn("enum file:{}", file.getAbsolutePath());
					return null;
				}

				setClassName(myTable, line);

				setEntity(myTable, line);

				setTable(myTable, line);

				setId(myTable, line);

				setStrategy(myTable, line);

				setIndex(myTable, line);
			}
		}catch (Exception e){
			e.printStackTrace();
			throw new IOException(e);
		}finally{
			lineNumberReader.close();
		}

		return myTable;
	}

	/**
	 * Sets the index.
	 * 
	 * @param myTable
	 *            the my table
	 * @param line
	 *            the new index
	 */
	private void setIndex(MyTable myTable,String line){
		//@Index(name = "T_MEM_MEMBER_ADDRESS_MEMBER_ID")
		if (line.contains("@Index")){
			String indexName = RegexUtil.group(REGEX_PATTERN_INDEX, line, 1);
			String lowerCase = indexName.toLowerCase();
			totalIndex.add(lowerCase);

			indexCount++;

			//***************************************************************
			String projectName = myTable.getProjectName();
			List<String> list = indexGroupByProjectMap.get(projectName);
			if (Validator.isNullOrEmpty(list)){
				list = new ArrayList<String>();
			}
			list.add(lowerCase);
			indexGroupByProjectMap.put(projectName, list);
		}
	}

	/**
	 * 设置 strategy.
	 * 
	 * @param myTable
	 *            the my table
	 * @param line
	 *            the line
	 */
	private void setStrategy(MyTable myTable,String line){
		//@GeneratedValue(strategy = GenerationType.IDENTITY)
		if (line.contains("@GeneratedValue")){
			myTable.setStrategy(line);

			generatedValueCount++;

			if (line.contains("GenerationType.IDENTITY")){
				myTable.setIdentity(true);

				generatedValueIdentityCount++;
			}
		}
	}

	/**
	 * 设置 id.
	 * 
	 * @param myTable
	 *            the my table
	 * @param line
	 *            the line
	 */
	private void setId(MyTable myTable,String line){
		//@Id
		if (line.contains("@Id")){
			myTable.setId("yes");
			myTable.setIdCount(myTable.getIdCount() + 1);

			idCount++;

		}
	}

	/**
	 * 设置 table.
	 * 
	 * @param myTable
	 *            the my table
	 * @param line
	 *            the line
	 */
	private void setTable(MyTable myTable,String line){
		//@Table(name = "T_MEM_MEMBER_ADDRESS")
		if (line.contains("@Table")){

			String tableName = RegexUtil.group(REGEX_PATTERN_TABLE, line, 1);
			myTable.setTableName(tableName);

			String lowerCase = tableName.toLowerCase();
			totalTable.add(lowerCase);
			tableCount++;

			//***************************************************************
			String projectName = myTable.getProjectName();
			List<String> list = tableGroupByProjectMap.get(projectName);
			if (Validator.isNullOrEmpty(list)){
				list = new ArrayList<String>();
			}
			list.add(lowerCase);
			tableGroupByProjectMap.put(projectName, list);

		}
	}

	/**
	 * 设置 entity.
	 * 
	 * @param myTable
	 *            the my table
	 * @param line
	 *            the line
	 */
	private void setEntity(MyTable myTable,String line){
		//@Entity
		if (line.contains("@Entity") && !line.contains("//@Entity") && !line.contains("// @Entity")){
			entityCount++;
			myTable.setEntity(true);
		}
	}

	/**
	 * 设置 class name.
	 * 
	 * @param myTable
	 *            the my table
	 * @param line
	 *            the line
	 */
	private void setClassName(MyTable myTable,String line){
		//****************************************************************
		//class
		if (line.contains("public class")){
			myTable.setClassName(line);
		}
	}

	/**
	 * Gets the entity count.
	 * 
	 * @return the entityCount
	 */
	public int getEntityCount(){
		return entityCount;
	}

	/**
	 * 获得 table数量.
	 * 
	 * @return the tableCount
	 */
	public int getTableCount(){
		return tableCount;
	}

	/**
	 * 获得 主键熟练.
	 * 
	 * @return the idCount
	 */
	public int getIdCount(){
		return idCount;
	}

	/**
	 * 获得 索引数量.
	 * 
	 * @return the indexCount
	 */
	public int getIndexCount(){
		return indexCount;
	}

	/**
	 * 获得 the Constant log.
	 * 
	 * @return the log
	 */
	public static Logger getLog(){
		return log;
	}

	/**
	 * Gets the generated value count.
	 * 
	 * @return the generatedValueCount
	 */
	public int getGeneratedValueCount(){
		return generatedValueCount;
	}

	/**
	 * Gets the generated value identity count.
	 * 
	 * @return the generatedValueIdentityCount
	 */
	public int getGeneratedValueIdentityCount(){
		return generatedValueIdentityCount;
	}

	/**
	 * 获得 总的数据表.
	 * 
	 * @return the totalTable
	 */
	public List<String> getTotalTable(){
		return totalTable;
	}

	/**
	 * Gets the serialversionuid.
	 * 
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid(){
		return serialVersionUID;
	}

	/**
	 * 获得 总的index.
	 * 
	 * @return the totalIndex
	 */
	public List<String> getTotalIndex(){
		return totalIndex;
	}

	/**
	 * 获得 每个项目的index,key为项目名字.
	 * 
	 * @return the indexGroupByProjectMap
	 */
	public Map<String, List<String>> getIndexGroupByProjectMap(){
		return indexGroupByProjectMap;
	}

	/**
	 * 获得 每个项目的table,key为项目名字.
	 * 
	 * @return the tableGroupByProjectMap
	 */
	public Map<String, List<String>> getTableGroupByProjectMap(){
		return tableGroupByProjectMap;
	}

}
