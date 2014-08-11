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
package com.feilong.tools.scm.svn;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.scm.AbstractScmAntCopy;
import com.feilong.tools.scm.command.PatchType;
import com.feilong.tools.scm.command.ScmPatchCommand;

/**
 * SVN patch.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-7-10 下午5:18:31
 */
public final class SvnPatchAntCopy extends AbstractScmAntCopy{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log					= LoggerFactory.getLogger(SvnPatchAntCopy.class);

	/** 项目名称 <code>{@value}</code>. */
	private static final String	PREFIX_PROJECTNAME	= "#P ";

	/** Index <code>{@value}</code>. */
	private static final String	PREFIX_INDEX		= "Index: ";

	/**
	 * Instantiates a new sVN patch util.
	 */
	public SvnPatchAntCopy(){}

	/**
	 * To patch command list map.
	 * 
	 * @param bufferedReader
	 *            the buffered reader
	 * @return the map
	 * @throws IOException
	 */
	protected Map<PatchType, List<? extends ScmPatchCommand>> toPatchCommandListMap(BufferedReader bufferedReader) throws IOException{
		List<SvnPatchCommand> addList = new ArrayList<SvnPatchCommand>();
		List<SvnPatchCommand> updateList = new ArrayList<SvnPatchCommand>();
		List<SvnPatchCommand> deleteList = new ArrayList<SvnPatchCommand>();

		//*********************************************************************************

		// Index 行寄存器,每个index 先设置这里面 ,如果下面 有--- +++ 等 就从这寄存器里面移除,剩下的都是add 的
		LinkedList<SvnPatchCommand> indexStorageList = new LinkedList<SvnPatchCommand>();

		SvnPatchCommand patchCommand = null;
		String line = null;
		// 每 次循环到 Index 重新赋值
		String filePath = "";
		while ((line = bufferedReader.readLine()) != null){
			// 项目名称
			if (line.startsWith(PREFIX_PROJECTNAME)){
				setProjectName(StringUtil.substring(line, PREFIX_PROJECTNAME.length()));
			}

			if (line.startsWith(PREFIX_INDEX)){
				// 开始
				patchCommand = new SvnPatchCommand();
				patchCommand.setIndex(line);

				// 每 次循环到 Index 重新赋值
				filePath = StringUtil.substring(line, PREFIX_INDEX.length());
				patchCommand.setFilePath(filePath);
				// 先寄存
				indexStorageList.add(patchCommand);

			}else if (line.startsWith("---")){// --- src/main/java/com/jumbo/shop/web/UserDetails.java (revision 47866)
				// 寄存器 删除 最后一个
				indexStorageList.removeLast();
				patchCommand.setRemote(line);

			}else if (line.startsWith("+++")){// +++ src/main/java/com/jumbo/shop/web/UserDetails.java (working copy)
				patchCommand.setLocal(line);

			}else if (line.startsWith("@@ ")){

				// @@ -1,34 +0,0 @@ delete
				// @@ -98,10 +98,18 @@ update
				patchCommand.getTwoAt().add(line);

				//add
				if (StringUtil.isContain(patchCommand.getRemote(), "(revision 0)")){
					// --- src/main/webapp/css/portal11.css (revision 0)
					// +++ src/main/webapp/css/portal11.css (working copy)
					// @@ -0,0 +1,129 @@
					addIgnoreSameFilePath(addList, patchCommand, PatchType.ADD);
				}else{
					//delete
					if (StringUtil.isContain(line, "+0,0")){
						addIgnoreSameFilePath(deleteList, patchCommand, PatchType.DELETE);
					}else{
						// 一个index 下面 可能 修改了 好多行 会产生很多 (选择基于路径)
						// @@ -157,18 +157,18 @@
						// xxxx
						// @@ -478,9 +478,9 @@
						// xxxx

						//update
						addIgnoreSameFilePath(updateList, patchCommand, PatchType.UPDATE);
					}
				}
			}
		}
		if (Validator.isNotNullOrEmpty(indexStorageList)){
			addList = indexStorageList;
		}
		return super.constructPatchTypeSCMCommandMap(addList, updateList, deleteList);
	}
}