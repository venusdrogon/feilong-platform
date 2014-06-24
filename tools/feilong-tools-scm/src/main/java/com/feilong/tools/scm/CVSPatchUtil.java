/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.feilong.commons.core.util.StringUtil;

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
 *          <li>1.0 2012-5-23 下午7:57:26</li>
 *          <li>1.1 2012-6-11 11:18<br>
 *          1.如果 addList,updateList,deleteList有空,则相应的 <!--add/update/delete-->注释不显示</li>
 *          <li>1.2 2012-7-9 19:28<br>
 *          1.新建了 AbstractPatchUtil</li>
 *          <li>1.3 2012-7-10 20:06<br>
 *          1.add com.feilong.tools.scm.CVSPatchUtil.getPatchMapByPatchType(BufferedReader)</li>
 *          </ul>
 */
public final class CVSPatchUtil extends AbstractPatchUtil{

	/**
	 * 项目名称{@code}
	 */
	private static String	prefix_projectName	= "#P ";

	/**
	 * Instantiates a new patch util.
	 */
	public CVSPatchUtil(){}

	/**
	 * To patch command list map.
	 * 
	 * @param bufferedReader
	 *            the buffered reader
	 * @return the map
	 */
	@SuppressWarnings("null")
	protected Map<PatchType, List<? extends BasePatchCommand>> toPatchCommandListMap(BufferedReader bufferedReader){
		String line = null;
		CVSPatchCommand patchCommand = null;

		List<CVSPatchCommand> addList = new ArrayList<CVSPatchCommand>();
		List<CVSPatchCommand> updateList = new ArrayList<CVSPatchCommand>();
		List<CVSPatchCommand> deleteList = new ArrayList<CVSPatchCommand>();
		try{
			while ((line = bufferedReader.readLine()) != null){
				// 项目名称
				if (line.startsWith(prefix_projectName)){
					setProjectName(getProjectName(line));
				}
				if (line.startsWith("Index:")){
					// 开始
					patchCommand = new CVSPatchCommand();
					patchCommand.setIndex(line);
					patchCommand.setFilePath(line);
				}else if (line.startsWith("RCS file:")){
					patchCommand.setRcs(line);
				}else if (line.startsWith("diff")){
					patchCommand.setDiff(line);
				}else if (line.startsWith("---")){
					patchCommand.setRemote(line);
					if (line.startsWith("--- /dev/null")){
						patchCommand.setPatchType(PatchType.add);
					}
				}else if (line.startsWith("+++")){
					patchCommand.setLocal(line);
					if (line.startsWith("+++ /dev/null")){
						patchCommand.setPatchType(PatchType.delete);
					}
					switch (patchCommand.getPatchType()) {
						case add:
							patchCommand.setFilePath(patchCommand.getRcs());
							addList.add(patchCommand);
							break;
						case update:
							patchCommand.setFilePath(StringUtil.substringWithoutLast(patchCommand.getRcs(), ",v".length()));
							updateList.add(patchCommand);
							break;
						case delete:
							patchCommand.setFilePath(patchCommand.getRcs());
							deleteList.add(patchCommand);
							break;
						default:
							break;
					}
				}
			}
			return constructPatchTypeSCMCommandMap(addList, updateList, deleteList);
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 显示项目 名称
	 * 
	 * @param line
	 * @return
	 */
	private static String getProjectName(String line){
		return StringUtil.substring(line, prefix_projectName.length());
	}
}
