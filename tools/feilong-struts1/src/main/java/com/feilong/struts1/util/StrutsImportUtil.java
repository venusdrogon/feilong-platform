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
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.UncheckedIOException;
import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.struts1.web.form.ImportForm;

/**
 * 导入.
 *
 * @author 金鑫 2010-7-26 上午11:15:54
 */
public class StrutsImportUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(StrutsImportUtil.class);

    /**
     * 获得输入流.
     *
     * @author 金鑫
     * @version 1.0 2010-7-26 上午11:41:02
     * @param importForm
     *            importForm
     * @return 获得输入流
     */
    public static InputStream getInputStream(ImportForm importForm){
        FormFile formFile = importForm.getFormFile();
        try{
            InputStream inputStream = formFile.getInputStream();
            return inputStream;
        }catch (FileNotFoundException e){
            log.error(e.getClass().getName(), e);
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
        return null;
    }

    /**
     * 获取 FormFile.
     *
     * @author 金鑫
     * @version 1.0 2010-7-26 上午11:52:41
     * @param importForm
     *            importForm
     * @return 获取 FormFile
     */
    public static FormFile getFormFile(ImportForm importForm){
        FormFile formFile = importForm.getFormFile();
        return formFile;
    }

    /**
     * 取得上传文件名称.
     *
     * @author 金鑫
     * @version 1.0 2010-7-26 上午11:53:38
     * @param importForm
     *            importForm
     * @return 取得上传文件名称
     */
    public static String getFileName(ImportForm importForm){
        FormFile formFile = getFormFile(importForm);
        return formFile.getFileName();
    }

    /**
     * 获得上传文件大小.
     *
     * @author 金鑫
     * @version 1.0 2010-7-26 上午11:56:38
     * @param importForm
     *            importForm
     * @return 上传文件大小
     */
    public static int getFileSize(ImportForm importForm){
        FormFile formFile = getFormFile(importForm);
        return formFile.getFileSize();
    }

    /**
     * 验证上传文件.
     *
     * @author 金鑫
     * @version 1.0 2010-7-26 上午11:55:51
     * @param importForm
     *            importForm
     * @param request
     *            当前请求
     * @return 空代表验证通过,否则代表不通过说明
     */
    public static String validateFormFile(ImportForm importForm,HttpServletRequest request){
        FormFile formFile = importForm.getFormFile();
        // 最大上传文件
        int maxFileSize = importForm.getMaxFileSize();
        String returnValue = "";
        // 配置了文件的大小（），这样在上传的过程中，如果文件超过了配置的大小，就会终止文件的上传
        Boolean maxLengthExceeded = (Boolean) request.getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
        if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue())){
            returnValue = "文件不能超过" + FileUtil.formatSize(maxFileSize);
        }else{
            int fileSize = formFile.getFileSize();
            if (fileSize > maxFileSize){
                returnValue = "文件不能超过" + FileUtil.formatSize(maxFileSize);
            }else{
                // 允许的文件后缀,用逗号隔开
                String allowFilePostfixs = importForm.getAllowFilePostfixs();
                String fileName = formFile.getFileName();
                if (!ArrayUtil.isContain(allowFilePostfixs.split(";"), FileUtil.getFilePostfixName(fileName))){
                    returnValue = "文件格式不正确,仅支持以下扩展名:" + allowFilePostfixs;
                }
            }
        }
        return returnValue;
    }
}
