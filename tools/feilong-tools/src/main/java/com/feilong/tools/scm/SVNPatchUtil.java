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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * SVN patch.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-7-10 下午5:18:31
 */
public class SVNPatchUtil extends AbstractPatchUtil{

	/** 项目名称{@code}. */
	private static String	prefix_projectName	= "#P ";

	/**
	 * Instantiates a new sVN patch util.
	 */
	public SVNPatchUtil(){}

	/**
	 * To patch command list map.
	 * 
	 * @param bufferedReader
	 *            the buffered reader
	 * @return the map
	 */
	protected Map<PatchType, List<? extends BasePatchCommand>> toPatchCommandListMap(BufferedReader bufferedReader){
		String line = null;
		SVNPatchCommand patchCommand = null;

		List<SVNPatchCommand> addList = new ArrayList<SVNPatchCommand>();
		List<SVNPatchCommand> updateList = new ArrayList<SVNPatchCommand>();
		List<SVNPatchCommand> deleteList = new ArrayList<SVNPatchCommand>();

		// Index 行寄存器,每个index 先设置这里面 ,如果下面 有--- +++ 等 就从这寄存器里面移除,剩下的都是add 的
		LinkedList<SVNPatchCommand> indexStorageList = new LinkedList<SVNPatchCommand>();

		// 每 次循环到 Index 重新赋值
		String filePath = "";
		try{
			while ((line = bufferedReader.readLine()) != null){
				// 项目名称
				if (line.startsWith(prefix_projectName)){
					setProjectName(StringUtil.substring(line, prefix_projectName.length()));
				}
				String prefix_index = "Index: ";

				if (line.startsWith(prefix_index)){
					// 开始
					patchCommand = new SVNPatchCommand();
					patchCommand.setIndex(line);

					// 每 次循环到 Index 重新赋值
					filePath = StringUtil.substring(line, prefix_index.length());
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

					if (StringUtil.isContain(patchCommand.getRemote(), "(revision 0)")){
						// --- src/main/webapp/css/portal11.css (revision 0)
						// +++ src/main/webapp/css/portal11.css (working copy)
						// @@ -0,0 +1,129 @@
						patchCommand.setPatchType(PatchType.add);
						addList.add(patchCommand);
					}else{
						if (StringUtil.isContain(line, "+0,0")){
							patchCommand.setPatchType(PatchType.delete);
							deleteList.add(patchCommand);
						}else{
							// 一个index 下面 可能 修改了 好多行 会产生很多
							// @@ -157,18 +157,18 @@
							// xxxx
							// @@ -478,9 +478,9 @@
							// xxxx
							if (!isContainSameFilePath(updateList, filePath)){// 防止 出现重复行
								patchCommand.setPatchType(PatchType.update);
								updateList.add(patchCommand);
							}
						}
					}
				}
			}
			if (Validator.isNotNullOrEmpty(indexStorageList)){
				addList = indexStorageList;
			}
			return constructPatchTypeSCMCommandMap(addList, updateList, deleteList);
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断集合中是否已经有 同样的filePath存在,相同的filePath不能加入到list 否则print 的时候 会好几行相同
	 * 
	 * @param list
	 * @param filePath
	 * @return filePath equals 则 return null
	 */
	private boolean isContainSameFilePath(List<SVNPatchCommand> list,String filePath){
		for (SVNPatchCommand svnPatchCommand : list){
			String _filePath = svnPatchCommand.getFilePath();
			if (_filePath.equals(filePath)){
				return true;// filePath equals 则 return null
			}
		}
		return false;
	}
}