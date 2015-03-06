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
package com.feilong.struts1.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * 导入文件form
 * 
 * @author 金鑫 2010-4-7 下午01:32:24
 */
public class ImportForm extends ActionForm{

    private static final long serialVersionUID  = 1L;

    private FormFile          formFile;

    /**
     * 最大文件大小,默认大小1024*2 2M
     */
    private int               maxFileSize       = 2 * 1024 * 1024;

    /**
     * 允许的文件后缀,用逗号隔开
     */
    private String            allowFilePostfixs = "xls";

    @Override
    public ActionErrors validate(ActionMapping mapping,HttpServletRequest request){
        return null;
    }

    @Override
    public void reset(ActionMapping mapping,HttpServletRequest request){
    }

    /**
     * @return the formFile
     */
    public FormFile getFormFile(){
        return formFile;
    }

    /**
     * @param formFile
     *            the formFile to set
     */
    public void setFormFile(FormFile formFile){
        this.formFile = formFile;
    }

    /**
     * 最大文件大小
     * 
     * @return the maxFileSize
     */
    public int getMaxFileSize(){
        return maxFileSize;
    }

    /**
     * 最大文件大小
     * 
     * @param maxFileSize
     *            the maxFileSize to set
     */
    public void setMaxFileSize(int maxFileSize){
        this.maxFileSize = maxFileSize;
    }

    /**
     * 允许的文件后缀,用逗号隔开
     * 
     * @return the allowFilePostfixs
     */
    public String getAllowFilePostfixs(){
        return allowFilePostfixs;
    }

    /**
     * 允许的文件后缀,用逗号隔开
     * 
     * @param allowFilePostfixs
     *            the allowFilePostfixs to set
     */
    public void setAllowFilePostfixs(String allowFilePostfixs){
        this.allowFilePostfixs = allowFilePostfixs;
    }
}