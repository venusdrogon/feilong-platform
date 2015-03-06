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
package com.feilong.struts1.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.struts.upload.FormFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 飞龙 struts 上传.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-19 上午10:19:12
 */
public class StrutsUpload{

    private static final Logger log = LoggerFactory.getLogger(StrutsUpload.class);

    /**
     * 上传文件.
     * 
     * @author 金鑫
     * @version 1.0 2010-1-6 上午10:51:08
     * @param formFile
     *            FormFile
     * @param directoryName
     *            文件夹名称 最后不带"/"
     * @param fileName
     *            文件名称+后缀
     * @return true, if successful
     */
    public boolean uploadFile(FormFile formFile,String directoryName,String fileName){
        try{
            OutputStream os = new FileOutputStream(directoryName + "/" + fileName);
            os.write(formFile.getFileData());
            os.close();
            return true;
        }catch (FileNotFoundException e){
            log.error(e.getClass().getName(), e);
        }catch (IOException e){
            log.error(e.getClass().getName(), e);
        }
        return false;
    }

    /**
     * formFile 是否含有文件, 即是否上传了文件.
     * 
     * @author 金鑫
     * @version 1.0 2010-9-14 下午05:40:51
     * @param formFile
     *            the form file
     * @return true 表示有
     */
    public static boolean isHasFile(FormFile formFile){
        if (null == formFile || formFile.getFileSize() == 0){
            return false;
        }
        return true;
    }
}
