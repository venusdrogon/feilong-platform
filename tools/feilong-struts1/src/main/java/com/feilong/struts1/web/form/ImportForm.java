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
package com.feilong.struts1.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * 导入文件form.
 *
 * @author 金鑫 2010-4-7 下午01:32:24
 */
public class ImportForm extends ActionForm{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID  = 1L;

    /** The form file. */
    private FormFile          formFile;

    /** 最大文件大小,默认大小1024*2 2M. */
    private int               maxFileSize       = 2 * 1024 * 1024;

    /** 允许的文件后缀,用逗号隔开. */
    private String            allowFilePostfixs = "xls";

    /* (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validate(ActionMapping mapping,HttpServletRequest request){
        return null;
    }

    /* (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping mapping,HttpServletRequest request){
    }

    /**
     * 获得 form file.
     *
     * @return the formFile
     */
    public FormFile getFormFile(){
        return formFile;
    }

    /**
     * 设置 form file.
     *
     * @param formFile
     *            the formFile to set
     */
    public void setFormFile(FormFile formFile){
        this.formFile = formFile;
    }

    /**
     * 最大文件大小.
     *
     * @return the maxFileSize
     */
    public int getMaxFileSize(){
        return maxFileSize;
    }

    /**
     * 最大文件大小.
     *
     * @param maxFileSize
     *            the maxFileSize to set
     */
    public void setMaxFileSize(int maxFileSize){
        this.maxFileSize = maxFileSize;
    }

    /**
     * 允许的文件后缀,用逗号隔开.
     *
     * @return the allowFilePostfixs
     */
    public String getAllowFilePostfixs(){
        return allowFilePostfixs;
    }

    /**
     * 允许的文件后缀,用逗号隔开.
     *
     * @param allowFilePostfixs
     *            the allowFilePostfixs to set
     */
    public void setAllowFilePostfixs(String allowFilePostfixs){
        this.allowFilePostfixs = allowFilePostfixs;
    }
}