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
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.toolkit.ClipboardUtil;
import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.util.StringBuilderUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.scm.command.PatchType;
import com.feilong.tools.scm.command.ScmPatchCommand;

/**
 * AbstractPatchUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-7-9 下午5:02:38
 */
public abstract class AbstractScmAntCopy implements ScmAntCopy{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(AbstractScmAntCopy.class);

	/** 项目名称. */
	protected String			projectName;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.scm.ScmAntCopy#printlnClipboardContent()
	 */
	public void printlnClipboardContent(){
		printlnClipboardContent(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.scm.ScmAntCopy#printlnClipboardContent(java.lang.String[])
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.scm.ScmAntCopy#printlnFileContent(java.lang.String)
	 */
	public void printlnFileContent(String fileName){
		printlnFileContent(fileName, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.scm.ScmAntCopy#printlnFileContent(java.lang.String, java.lang.String[])
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
	 * 解析 并 system out.
	 * 
	 * @param reader
	 *            the reader
	 * @param excludeFileNames
	 *            the exclude file names
	 */
	private void printlnPathContent(Reader reader,String[] excludeFileNames){
		BufferedReader bufferedReader = new BufferedReader(reader);

		StringBuilder builder = new StringBuilder();
		Map<PatchType, List<String>> map = getPatchMapByPatchType(bufferedReader);

		StringBuilderUtil.appendTextWithLn(builder, "<!--project:" + getProjectName() + "-->");

		if (Validator.isNotNullOrEmpty(map)){
			List<String> addList = map.get(PatchType.ADD);
			List<String> updateList = map.get(PatchType.UPDATE);
			List<String> deleteList = map.get(PatchType.DELETE);

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

			log.info(builder.insert(0, SystemUtils.LINE_SEPARATOR).toString());
		}else{
			log.error("this map is null!!!Maybe clipboard/file content is null or unrelated with patch!");
		}
	}

	/**
	 * Println path content.
	 *
	 * @param builder
	 *            the builder
	 * @param fileName
	 *            the file name
	 * @param excludeFileNames
	 *            the exclude file names
	 */
	private void appendPatchContent(StringBuilder builder,String fileName,String[] excludeFileNames){
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
	private String resolveFileName(String filePath){
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
			log.warn("filePath:[{}],不需要上传到正式环境!", filePath);
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
	private Map<PatchType, List<String>> getPatchMapByPatchType(BufferedReader bufferedReader){
		Map<PatchType, List<? extends ScmPatchCommand>> map = toPatchCommandListMap(bufferedReader);

		Map<PatchType, List<String>> returnMap = new EnumMap<PatchType, List<String>>(PatchType.class);

		if (Validator.isNotNullOrEmpty(map)){
			for (Map.Entry<PatchType, List<? extends ScmPatchCommand>> entry : map.entrySet()){
				//PatchType key = entry.getKey();
				List<? extends ScmPatchCommand> value = entry.getValue();

				// add
				put(returnMap, value);
			}
		}
		return returnMap;

	}

	/**
	 * Put.
	 *
	 * @param returnMap
	 *            the return map
	 * @param scmPatchCommandList
	 *            the scm patch command list
	 */
	private void put(Map<PatchType, List<String>> returnMap,List<? extends ScmPatchCommand> scmPatchCommandList){
		if (Validator.isNotNullOrEmpty(scmPatchCommandList)){
			List<String> resolveFileNamelist = new ArrayList<String>();

			PatchType patchType = scmPatchCommandList.get(0).getPatchType();

			for (ScmPatchCommand scmPatchCommand : scmPatchCommandList){
				String index = scmPatchCommand.getFilePath();
				String fileName = resolveFileName(index);
				resolveFileNamelist.add(fileName);
			}
			returnMap.put(patchType, resolveFileNamelist);
		}
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
	protected Map<PatchType, List<? extends ScmPatchCommand>> constructPatchTypeSCMCommandMap(
			List<? extends ScmPatchCommand> addList,
			List<? extends ScmPatchCommand> updateList,
			List<? extends ScmPatchCommand> deleteList){
		Map<PatchType, List<? extends ScmPatchCommand>> map = new EnumMap<PatchType, List<? extends ScmPatchCommand>>(PatchType.class);

		if (Validator.isNotNullOrEmpty(addList)){
			map.put(PatchType.ADD, addList);
		}

		if (Validator.isNotNullOrEmpty(updateList)){
			map.put(PatchType.UPDATE, updateList);
		}

		if (Validator.isNotNullOrEmpty(deleteList)){
			map.put(PatchType.DELETE, deleteList);
		}

		return map;
	}

	/**
	 * 添加是忽略相同文件路径的svnPatchCommand,出现的可能在于 eclipse创建 patch的时候,选择的patch root是workspace<br>
	 * 可以选择 selection或者单个项目 就不会有这样的问题.
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @param svnPatchCommand
	 *            the svn patch command
	 * @param patchType
	 *            the patch type
	 */
	protected <T extends ScmPatchCommand> void addIgnoreSameFilePath(List<T> list,T svnPatchCommand,PatchType patchType){
		String filePath = svnPatchCommand.getFilePath();

		boolean isContainSameFilePath = false;

		// 判断集合中是否已经有 同样的filePath存在,相同的filePath不能加入到list 否则print 的时候 会好几行相同.
		for (ScmPatchCommand scmPatchCommand : list){
			String _filePath = scmPatchCommand.getFilePath();
			if (_filePath.equals(filePath)){
				isContainSameFilePath = true;// filePath equals 则 return null
				break;
			}
		}
		if (!isContainSameFilePath){// 防止 出现重复行
			svnPatchCommand.setPatchType(patchType);
			list.add(svnPatchCommand);
		}else{
			log.warn("ContainSameFilePath:[{}]", filePath);
		}
	}

	/**
	 * To patch command list map.
	 * 
	 * @param bufferedReader
	 *            the buffered reader
	 * @return the map< patch type, list<? extends base scm command>>
	 */
	protected abstract Map<PatchType, List<? extends ScmPatchCommand>> toPatchCommandListMap(BufferedReader bufferedReader);

	/**
	 * Gets the 项目名称.
	 * 
	 * @return the projectName
	 */
	protected String getProjectName(){
		return projectName;
	}

	/**
	 * Sets the 项目名称.
	 * 
	 * @param projectName
	 *            the projectName to set
	 */
	protected void setProjectName(String projectName){
		this.projectName = projectName;
	}
}
