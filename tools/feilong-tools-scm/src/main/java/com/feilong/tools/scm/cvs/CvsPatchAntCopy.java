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
package com.feilong.tools.scm.cvs;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.feilong.commons.core.util.StringUtil;
import com.feilong.tools.scm.AbstractScmAntCopy;
import com.feilong.tools.scm.command.PatchType;
import com.feilong.tools.scm.command.ScmPatchCommand;

/**
 * 此工具类是结合ant打包 使用的，用于：创建修改了哪些文件。<br>
 * <b> 操作步骤： </b>
 * 
 * <pre>
 * 同步到:Synchronize窗口，选中你需要上传的文件-->右击鼠标，选中create patch-->finish;
 * 
 * 这样你修改了哪些文件执行下面的main，就在console里面输出出<include> 的更改的文件。
 * 再将这些include发送给维护的同事就可以了
 * 
 * 1.支持 add update delete
 * 2.显示完整的路径 如<code><include name="**\com\jumbo\interceptor\ProductInterceptor.class"/></code>
 * </pre>
 * 
 * @author xialong
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version <ul>
 *          <li>1.0.0 2012-5-23 下午7:57:26</li>
 *          <li>1.0.1 2012-6-11 11:18<br>
 *          1.如果 addList,updateList,deleteList有空,则相应的 <!--add/update/delete-->注释不显示</li>
 *          <li>1.0.2 2012-7-9 19:28<br>
 *          1.新建了 AbstractPatchUtil</li>
 *          <li>1.0.3 2012-7-10 20:06<br>
 *          1.add com.feilong.tools.scm.CVSPatchUtil.getPatchMapByPatchType(BufferedReader)</li>
 *          </ul>
 */
public final class CvsPatchAntCopy extends AbstractScmAntCopy{

	/** 项目名称 <code>{@value}</code>. */
	private static final String	PREFIX_PROJECTNAME	= "#P ";

	/**
	 * Instantiates a new patch util.
	 */
	public CvsPatchAntCopy(){
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.feilong.tools.scm.AbstractScmAntCopy#toPatchCommandListMap(java.io.BufferedReader)
	 */
	@Override
	@SuppressWarnings("null")
	protected Map<PatchType, List<? extends ScmPatchCommand>> toPatchCommandListMap(BufferedReader bufferedReader) throws IOException{
		String line = null;
		CvsPatchCommand patchCommand = null;

		List<CvsPatchCommand> addList = new ArrayList<CvsPatchCommand>();
		List<CvsPatchCommand> updateList = new ArrayList<CvsPatchCommand>();
		List<CvsPatchCommand> deleteList = new ArrayList<CvsPatchCommand>();
		while ((line = bufferedReader.readLine()) != null){
			// 项目名称
			if (line.startsWith(PREFIX_PROJECTNAME)){
				setProjectName(parseProjectName(line));
			}
			if (line.startsWith("Index:")){
				// 开始
				patchCommand = new CvsPatchCommand();
				patchCommand.setIndex(line);
				patchCommand.setFilePath(line);
			}else if (line.startsWith("RCS file:")){
				patchCommand.setRcs(line);
			}else if (line.startsWith("diff")){
				patchCommand.setDiff(line);
			}else if (line.startsWith("---")){
				patchCommand.setRemote(line);
				if (line.startsWith("--- /dev/null")){
					patchCommand.setPatchType(PatchType.ADD);
				}
			}else if (line.startsWith("+++")){
				patchCommand.setLocal(line);
				if (line.startsWith("+++ /dev/null")){
					patchCommand.setPatchType(PatchType.DELETE);
				}
				switch (patchCommand.getPatchType()) {
					case ADD:
						patchCommand.setFilePath(patchCommand.getRcs());
						addList.add(patchCommand);
						break;
					case UPDATE:
						patchCommand.setFilePath(StringUtil.substringWithoutLast(patchCommand.getRcs(), ",v".length()));
						updateList.add(patchCommand);
						break;
					case DELETE:
						patchCommand.setFilePath(patchCommand.getRcs());
						deleteList.add(patchCommand);
						break;
					default:
						break;
				}
			}
		}
		return super.constructPatchTypeSCMCommandMap(addList, updateList, deleteList);
	}

	/**
	 * 获得项目名称.
	 *
	 * @param line
	 *            the line
	 * @return the project name
	 */
	private static String parseProjectName(String line){
		return StringUtil.substring(line, PREFIX_PROJECTNAME.length());
	}
}
