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
import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.struts1.web.form.ImportForm;

/**
 * 导入
 * 
 * @author 金鑫 2010-7-26 上午11:15:54
 */
public class StrutsImportUtil{

    private static final Logger log = LoggerFactory.getLogger(StrutsImportUtil.class);

    /**
     * 获得输入流
     * 
     * @param importForm
     *            importForm
     * @return 获得输入流
     * @author 金鑫
     * @version 1.0 2010-7-26 上午11:41:02
     */
    public static InputStream getInputStream(ImportForm importForm){
        FormFile formFile = importForm.getFormFile();
        try{
            InputStream inputStream = formFile.getInputStream();
            return inputStream;
        }catch (FileNotFoundException e){
            log.error(e.getClass().getName(), e);
        }catch (IOException e){
            log.error(e.getClass().getName(), e);
        }
        return null;
    }

    /**
     * 获取 FormFile
     * 
     * @param importForm
     *            importForm
     * @return 获取 FormFile
     * @author 金鑫
     * @version 1.0 2010-7-26 上午11:52:41
     */
    public static FormFile getFormFile(ImportForm importForm){
        FormFile formFile = importForm.getFormFile();
        return formFile;
    }

    /**
     * 取得上传文件名称
     * 
     * @param importForm
     *            importForm
     * @return 取得上传文件名称
     * @author 金鑫
     * @version 1.0 2010-7-26 上午11:53:38
     */
    public static String getFileName(ImportForm importForm){
        FormFile formFile = getFormFile(importForm);
        return formFile.getFileName();
    }

    /**
     * 获得上传文件大小
     * 
     * @param importForm
     *            importForm
     * @return 上传文件大小
     * @author 金鑫
     * @version 1.0 2010-7-26 上午11:56:38
     */
    public static int getFileSize(ImportForm importForm){
        FormFile formFile = getFormFile(importForm);
        return formFile.getFileSize();
    }

    /**
     * 验证上传文件
     * 
     * @param importForm
     *            importForm
     * @param request
     *            当前请求
     * @return 空代表验证通过,否则代表不通过说明
     * @author 金鑫
     * @version 1.0 2010-7-26 上午11:55:51
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
