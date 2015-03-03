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
package com.feilong.taglib.common.path;

import java.io.File;

import com.feilong.commons.core.lang.ClassLoaderUtil;
import com.feilong.taglib.base.AbstractConditionalTag;

/**
 * 判断文件是否存在
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-7-4 下午01:58:31
 * @since 1.0
 */
public class IsExistsFileTag extends AbstractConditionalTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 文件路径,<br>
	 * 目前 仅支持 相对于根目录路径,使用/ 开头
	 */
	private String				filePath;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.taglib.base.FeiLongBaseConditionalTag#condition()
	 */
	@Override
	protected boolean condition(){
		//web classes 目录
		String classPath = ClassLoaderUtil.getClassPath().getPath();
		//web 根目录
		File rootPath = new File(classPath).getParentFile().getParentFile();
		File file = new File(rootPath.getAbsolutePath() + filePath);
		return file.exists();
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
}
