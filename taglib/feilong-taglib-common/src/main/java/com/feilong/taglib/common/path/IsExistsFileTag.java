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
package com.feilong.taglib.common.path;

import java.io.File;

import com.feilong.commons.core.lang.ClassLoaderUtil;
import com.feilong.taglib.base.AbstractConditionalTag;

/**
 * 判断文件是否存在.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-7-4 下午01:58:31
 * @since 1.0
 */
public class IsExistsFileTag extends AbstractConditionalTag{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * 文件路径,<br>
     * 目前 仅支持 相对于根目录路径,使用/ 开头.
     */
    private String            filePath;

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
     * 设置 文件路径,<br>
     * 目前 仅支持 相对于根目录路径,使用/ 开头.
     *
     * @param filePath
     *            the filePath to set
     */
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
}
