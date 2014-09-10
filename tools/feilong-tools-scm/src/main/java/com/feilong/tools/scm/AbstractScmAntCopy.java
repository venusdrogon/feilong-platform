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
	private static final Logger	log							= LoggerFactory.getLogger(AbstractScmAntCopy.class);

	/** class 文件扩展名 <code>{@value}</code>. */
	private static final String	EXTENSION_CLASS				= "class";

	/** java 文件扩展名 <code>{@value}</code>. */
	private static final String	EXTENSION_JAVA				= "java";

	/** The Constant maven_src_main_java. */
	private static final String	MAVEN_SRC_MAIN_JAVA			= "src/main/java";

	/** The Constant maven_src_main_webapp. */
	private static final String	MAVEN_SRC_MAIN_WEBAPP		= "src/main/webapp";

	/** The Constant maven_src_main_resources. */
	private static final String	MAVEN_SRC_MAIN_RESOURCES	= "src/main/resources";

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
	public void printlnClipboardContent(String[] excludeFileNames){
		try{
			Reader reader = ClipboardUtil.getClipboardReader();

			ScmAntCopyConfig scmAntCopyConfig = new ScmAntCopyConfig();
			scmAntCopyConfig.setExcludeFileNames(excludeFileNames);

			printlnPathContent(reader, scmAntCopyConfig);
		}catch (UnsupportedFlavorException | IOException e){
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.scm.ScmAntCopy#printlnFileContent(java.lang.String)
	 */
	public void printlnFileContent(String fileName){
		String[] excludeFileNames = null;
		printlnFileContent(fileName, excludeFileNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.scm.ScmAntCopy#printlnFileContent(java.lang.String, java.lang.String[])
	 */
	public void printlnFileContent(String fileName,String[] excludeFileNames){
		ScmAntCopyConfig scmAntCopyConfig = new ScmAntCopyConfig();
		scmAntCopyConfig.setExcludeFileNames(excludeFileNames);

		printlnFileContent(fileName, scmAntCopyConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.scm.ScmAntCopy#printlnFileContent(java.lang.String, com.feilong.tools.scm.ScmAntCopyConfig)
	 */
	public void printlnFileContent(String fileName,ScmAntCopyConfig scmAntCopyConfig){
		try{
			Reader reader = new FileReader(fileName);
			printlnPathContent(reader, scmAntCopyConfig);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

	//***************************************************************************************

	/**
	 * 解析 并log.
	 *
	 * @param reader
	 *            the reader
	 * @param scmAntCopyConfig
	 *            the scm ant copy config
	 */
	private void printlnPathContent(Reader reader,ScmAntCopyConfig scmAntCopyConfig){
		try{
			Map<PatchType, List<String>> patchTypeFilePathMap = getFilePathMapByPatchType(reader, scmAntCopyConfig);

			if (Validator.isNullOrEmpty(patchTypeFilePathMap)){
				throw new IllegalArgumentException("this map is null!!!Maybe clipboard/file content is null or unrelated with patch!");
			}

			StringBuilder builder = new StringBuilder();

			StringBuilderUtil.appendTextWithLn(builder, "<!--project:" + getProjectName() + "-->");

			List<String> addList = patchTypeFilePathMap.get(PatchType.ADD);
			List<String> updateList = patchTypeFilePathMap.get(PatchType.UPDATE);
			List<String> deleteList = patchTypeFilePathMap.get(PatchType.DELETE);

			builder(addList, builder, scmAntCopyConfig, "<!--add-->");
			builder(updateList, builder, scmAntCopyConfig, "<!--update-->");
			builder(deleteList, builder, scmAntCopyConfig, "<!--delete-->");

			log.info(builder.insert(0, SystemUtils.LINE_SEPARATOR).toString());
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * Builder.
	 *
	 * @param patchTypeFilePathList
	 *            the delete list
	 * @param builder
	 *            the builder
	 * @param scmAntCopyConfig
	 *            the scm ant copy config
	 * @param text
	 *            the text
	 */
	private void builder(List<String> patchTypeFilePathList,StringBuilder builder,ScmAntCopyConfig scmAntCopyConfig,String text){
		String[] excludeFileNames = scmAntCopyConfig.getExcludeFileNames();

		// 2012-12-5 18:35 排序
		if (Validator.isNotNullOrEmpty(patchTypeFilePathList)){
			Collections.sort(patchTypeFilePathList);
		}

		if (Validator.isNotNullOrEmpty(patchTypeFilePathList)){
			StringBuilderUtil.appendLn(builder);

			StringBuilderUtil.appendTextWithLn(builder, text);
			for (String fileName : patchTypeFilePathList){
				boolean canPrint = true;
				if (Validator.isNotNullOrEmpty(excludeFileNames)){
					for (String exclude : excludeFileNames){
						if (fileName.endsWith(exclude)){
							canPrint = false;
						}
					}
				}
				if (canPrint && Validator.isNotNullOrEmpty(fileName)){
					//2014-08-11 添加个 /
					String prefix = fileName.startsWith("/") ? "" : "/";
					StringBuilderUtil.appendTextWithLn(builder, "<include name=\"**" + prefix + fileName + "\"/>");
				}
			}
		}
	}

	/**
	 * Gets the file name.
	 *
	 * @param filePath
	 *            filePath
	 * @param scmAntCopyConfig
	 *            the scm ant copy config
	 * @return the file name
	 */
	//TODO
	private String resolveFileName(String filePath,ScmAntCopyConfig scmAntCopyConfig){
		//"src/main/java"
		if (StringUtil.isContain(filePath, MAVEN_SRC_MAIN_JAVA)){
			String fileName = StringUtil.substring(filePath, MAVEN_SRC_MAIN_JAVA, MAVEN_SRC_MAIN_JAVA.length());

			boolean changeJavaFileExtensionNameToClass = scmAntCopyConfig.getChangeJavaFileExtensionNameToClass();
			// 后缀是java
			if (changeJavaFileExtensionNameToClass && FileUtil.getFilePostfixName(fileName).equalsIgnoreCase(EXTENSION_JAVA)){
				fileName = FileUtil.getNewFileName(fileName, EXTENSION_CLASS);
			}

			return fileName;
		}

		//"src/main/webapp"
		else if (StringUtil.isContain(filePath, MAVEN_SRC_MAIN_WEBAPP)){
			return StringUtil.substring(filePath, MAVEN_SRC_MAIN_WEBAPP, MAVEN_SRC_MAIN_WEBAPP.length());
		}

		//"src/main/resources"
		else if (StringUtil.isContain(filePath, MAVEN_SRC_MAIN_RESOURCES)){
			return StringUtil.substring(filePath, MAVEN_SRC_MAIN_RESOURCES, MAVEN_SRC_MAIN_RESOURCES.length());
		}

		else{
			boolean isIgnoreNotRuleFile = scmAntCopyConfig.getIgnoreNotRuleFile();
			if (isIgnoreNotRuleFile){
				log.warn("filePath:[{}],不需要上传到正式环境!", filePath);
				return null;
			}else{
				return filePath;
			}
		}
	}

	/**
	 * 按照 add update delete 排好 list map.
	 *
	 * @param reader
	 *            the reader
	 * @param scmAntCopyConfig
	 *            the scm ant copy config
	 * @return the patch map by patch type
	 * @throws IOException
	 *             the IO exception
	 */
	private Map<PatchType, List<String>> getFilePathMapByPatchType(Reader reader,ScmAntCopyConfig scmAntCopyConfig) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(reader);

		Map<PatchType, List<? extends ScmPatchCommand>> map = toPatchCommandListMap(bufferedReader);

		Map<PatchType, List<String>> filePathMap = new EnumMap<PatchType, List<String>>(PatchType.class);

		if (Validator.isNotNullOrEmpty(map)){
			for (Map.Entry<PatchType, List<? extends ScmPatchCommand>> entry : map.entrySet()){
				//PatchType key = entry.getKey();
				List<? extends ScmPatchCommand> scmPatchCommandList = entry.getValue();

				// add
				putFilePathMap(filePathMap, scmPatchCommandList, scmAntCopyConfig);
			}
		}
		return filePathMap;

	}

	/**
	 * Put file path map.
	 *
	 * @param filePathMap
	 *            the return map
	 * @param scmPatchCommandList
	 *            the scm patch command list
	 * @param scmAntCopyConfig
	 *            the scm ant copy config
	 */
	private void putFilePathMap(
					Map<PatchType, List<String>> filePathMap,
					List<? extends ScmPatchCommand> scmPatchCommandList,
					ScmAntCopyConfig scmAntCopyConfig){

		if (Validator.isNotNullOrEmpty(scmPatchCommandList)){
			List<String> resolveFileNamelist = new ArrayList<String>();

			PatchType patchType = scmPatchCommandList.get(0).getPatchType();

			for (ScmPatchCommand scmPatchCommand : scmPatchCommandList){
				String filePath = scmPatchCommand.getFilePath();
				String fileName = resolveFileName(filePath, scmAntCopyConfig);

				if (Validator.isNotNullOrEmpty(fileName)){
					resolveFileNamelist.add(fileName);
				}
			}
			filePathMap.put(patchType, resolveFileNamelist);
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
	 * @throws IOException
	 *             the IO exception
	 */
	protected abstract Map<PatchType, List<? extends ScmPatchCommand>> toPatchCommandListMap(BufferedReader bufferedReader)
					throws IOException;

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