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
package com.feilong.struts1.web.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.struts1.web.form.ImportForm;

/**
 * 飞龙导入文件基类 DispatchAction
 * 
 * @author 金鑫 时间:2010年4月7日 13:42:48
 * @deprecated
 */
public class FeiLongBaseImportAction extends DispatchAction{

	/**
	 * 获得 FormFile
	 * 
	 * @param form
	 *            ActionForm
	 * @return FormFile
	 * @author 金鑫
	 * @version 1.0 2010-5-21 下午02:31:05
	 */
	protected FormFile getFormFile(ActionForm form){
		ImportForm importForm = (ImportForm) form;
		FormFile formFile = importForm.getFormFile();
		return formFile;
	}

	/**
	 * 获得上传文件大小
	 * 
	 * @param form
	 *            ActionForm
	 * @return 上传文件大小
	 * @author 金鑫
	 * @version 1.0 2010-5-21 下午02:36:06
	 */
	protected int getFileSize(ActionForm form){
		FormFile formFile = getFormFile(form);
		return formFile.getFileSize();
	}

	/**
	 * 获得流
	 * 
	 * @param form
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-4-7 下午01:43:26
	 */
	protected InputStream getInputStream(ActionForm form){
		FormFile formFile = getFormFile(form);
		try{
			return formFile.getInputStream();
		}catch (FileNotFoundException e){
			log.error(e.getClass().getName(), e);
		}catch (IOException e){
			log.error(e.getClass().getName(), e);
		}
		return null;
	}

	/**
	 * 验证上传文件
	 * 
	 * @param form
	 *            ActionForm
	 * @return 空代表验证通过,否则代表不通过说明
	 * @author 金鑫
	 * @version 1.0 2010-5-21 下午03:47:53
	 */
	protected String validateFormFile(ActionForm form,HttpServletRequest request){
		ImportForm importForm = (ImportForm) form;
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
