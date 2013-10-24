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
package com.feilong.tools.scm;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.Constants;
import com.feilong.commons.core.awt.toolkit.ClipboardUtil;
import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.util.StringBuilderUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * AbstractPatchUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-7-9 下午5:02:38
 */
public abstract class AbstractPatchUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(AbstractPatchUtil.class);

	/** 项目名称. */
	protected String			projectName;

	/**
	 * 读取剪切板的信息.
	 */
	public void printlnClipboardContent(){
		printlnClipboardContent(null);
	}

	/**
	 * 读取剪切板的信息.
	 * 
	 * @param excludes
	 *            the excludes
	 */
	public void printlnClipboardContent(String[] excludes){
		Clipboard clipboard = ClipboardUtil.getSystemClipboard();
		Transferable transferable = clipboard.getContents(clipboard);
		DataFlavor dataFlavor = DataFlavor.stringFlavor;
		try{
			Reader reader = dataFlavor.getReaderForText(transferable);
			printlnPathContent(reader, excludes);
		}catch (UnsupportedFlavorException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 解析patch 文件.
	 * 
	 * @param fileName
	 *            the file name
	 */
	public void printlnFileContent(String fileName){
		printlnFileContent(fileName, null);
	}

	/**
	 * 解析 并 system out.
	 * 
	 * @param reader
	 *            the reader
	 * @param excludeFileNames
	 *            the exclude file names
	 */
	protected void printlnPathContent(Reader reader,String[] excludeFileNames){
		BufferedReader bufferedReader = new BufferedReader(reader);

		StringBuilder builder = new StringBuilder();
		Map<PatchType, List<String>> map = getPatchMapByPatchType(bufferedReader);

		StringBuilderUtil.appendTextWithLn(builder, "<!--project:" + getProjectName() + "-->");

		if (Validator.isNotNullOrEmpty(map)){
			List<String> addList = map.get(PatchType.add);
			List<String> updateList = map.get(PatchType.update);
			List<String> deleteList = map.get(PatchType.delete);

			// 2012-12-5 18:35 排序
			if (Validator.isNotNullOrEmpty(addList)){
				Collections.sort(addList);
			}
			if (Validator.isNotNullOrEmpty(updateList)){
				Collections.sort(updateList);
			}
			if (Validator.isNotNullOrEmpty(deleteList)){
				Collections.sort(deleteList);
			}
			// add
			if (Validator.isNotNullOrEmpty(addList)){
				StringBuilderUtil.appendTextWithLn(builder, "<!--add-->");

				for (String fileName : addList){
					appendPatchContent(builder, fileName, excludeFileNames);
				}
			}
			// update
			if (Validator.isNotNullOrEmpty(updateList)){
				StringBuilderUtil.appendLn(builder);
				StringBuilderUtil.appendTextWithLn(builder, "<!--update-->");

				for (String fileName : updateList){
					appendPatchContent(builder, fileName, excludeFileNames);
				}
			}
			// delete
			if (Validator.isNotNullOrEmpty(deleteList)){
				StringBuilderUtil.appendLn(builder);
				StringBuilderUtil.appendTextWithLn(builder, "<!--delete-->");
				for (String fileName : deleteList){
					appendPatchContent(builder, fileName, excludeFileNames);
				}
			}

			log.info(builder.insert(0, Constants.lineSeparator).toString());
		}else{
			log.error("this map is null!!!Maybe clipboard/file content is null or unrelated with patch!");
		}
	}

	/**
	 * 解析patch 文件.
	 * 
	 * @param fileName
	 *            the file name
	 * @param excludeFileNames
	 *            the exclude file names
	 */
	public void printlnFileContent(String fileName,String[] excludeFileNames){
		try{
			Reader reader = new FileReader(fileName);
			printlnPathContent(reader, excludeFileNames);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

	/**
	 * Println path content.
	 * 
	 * @param builder
	 * @param fileName
	 *            the file name
	 * @param excludeFileNames
	 *            the exclude file names
	 */
	protected void appendPatchContent(StringBuilder builder,String fileName,String[] excludeFileNames){
		boolean canPrint = true;
		if (Validator.isNotNullOrEmpty(excludeFileNames)){
			for (String exclude : excludeFileNames){
				if (fileName.endsWith(exclude)){
					canPrint = false;
				}
			}
		}
		if (canPrint){
			if (Validator.isNotNullOrEmpty(fileName)){
				StringBuilderUtil.appendTextWithLn(builder, "<include name=\"**" + fileName + "\"/>");
			}
		}
	}

	/**
	 * Gets the file name.
	 * 
	 * @param filePath
	 *            filePath
	 * @return the file name
	 */
	protected String resolveFileName(String filePath){
		String src_main_java = "src/main/java";
		String src_main_webapp = "src/main/webapp";
		String src_main_resources = "src/main/resources";
		String fileName = "";
		if (StringUtil.isContain(filePath, src_main_java)){
			fileName = StringUtil.substring(filePath, src_main_java, src_main_java.length());
			// 后缀是java
			if (FileUtil.getFilePostfixName(fileName).equalsIgnoreCase("java")){
				fileName = FileUtil.getNewFileName(fileName, "class");
			}
		}else if (StringUtil.isContain(filePath, src_main_webapp)){
			fileName = StringUtil.substring(filePath, src_main_webapp, src_main_webapp.length());
		}else if (StringUtil.isContain(filePath, src_main_resources)){
			fileName = StringUtil.substring(filePath, src_main_resources, src_main_resources.length());
		}else{
			log.warn("the file filePath:{},不需要上传到正式环境", filePath);
		}
		return fileName;
	}

	/**
	 * 按照 add update delete 排好 list map.
	 * 
	 * @param bufferedReader
	 *            the buffered reader
	 * @return the patch map by patch type
	 */
	protected Map<PatchType, List<String>> getPatchMapByPatchType(BufferedReader bufferedReader){
		Map<PatchType, List<? extends BasePatchCommand>> map = toPatchCommandListMap(bufferedReader);
		Map<PatchType, List<String>> returnMap = new HashMap<PatchType, List<String>>();
		if (Validator.isNotNullOrEmpty(map)){
			List<? extends BasePatchCommand> addList = map.get(PatchType.add);
			List<? extends BasePatchCommand> updateList = map.get(PatchType.update);
			List<? extends BasePatchCommand> deleteList = map.get(PatchType.delete);
			// add
			if (Validator.isNotNullOrEmpty(addList)){
				List<String> addStringlist = new ArrayList<String>();
				for (BasePatchCommand patchCommand : addList){
					String index = patchCommand.getFilePath();
					String fileName = resolveFileName(index);
					addStringlist.add(fileName);
				}
				returnMap.put(PatchType.add, addStringlist);
			}
			// update
			if (Validator.isNotNullOrEmpty(updateList)){
				List<String> updateStringlist = new ArrayList<String>();
				for (BasePatchCommand patchCommand : updateList){
					String index = patchCommand.getFilePath();
					String fileName = resolveFileName(index);
					updateStringlist.add(fileName);
				}
				returnMap.put(PatchType.update, updateStringlist);
			}
			// delete
			if (Validator.isNotNullOrEmpty(deleteList)){
				List<String> deleteStringlist = new ArrayList<String>();
				for (BasePatchCommand patchCommand : deleteList){
					String index = patchCommand.getFilePath();
					String fileName = resolveFileName(index);
					deleteStringlist.add(fileName);
				}
				returnMap.put(PatchType.delete, deleteStringlist);
			}
		}
		return returnMap;

	}

	/**
	 * construct map.
	 * 
	 * @param addList
	 *            the add list
	 * @param updateList
	 *            the update list
	 * @param deleteList
	 *            the delete list
	 * @return the map< patch type, list<? extends base scm command>>
	 */
	protected Map<PatchType, List<? extends BasePatchCommand>> constructPatchTypeSCMCommandMap(
			List<? extends BasePatchCommand> addList,
			List<? extends BasePatchCommand> updateList,
			List<? extends BasePatchCommand> deleteList){
		Map<PatchType, List<? extends BasePatchCommand>> map = new HashMap<PatchType, List<? extends BasePatchCommand>>();

		if (Validator.isNotNullOrEmpty(addList)){
			map.put(PatchType.add, addList);
		}

		if (Validator.isNotNullOrEmpty(updateList)){
			map.put(PatchType.update, updateList);
		}

		if (Validator.isNotNullOrEmpty(deleteList)){
			map.put(PatchType.delete, deleteList);
		}

		return map;
	}

	/**
	 * To patch command list map.
	 * 
	 * @param bufferedReader
	 *            the buffered reader
	 * @return the map< patch type, list<? extends base scm command>>
	 */
	protected abstract Map<PatchType, List<? extends BasePatchCommand>> toPatchCommandListMap(BufferedReader bufferedReader);

	/**
	 * Gets the 项目名称.
	 * 
	 * @return the projectName
	 */
	public String getProjectName(){
		return projectName;
	}

	/**
	 * Sets the 项目名称.
	 * 
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}
}
