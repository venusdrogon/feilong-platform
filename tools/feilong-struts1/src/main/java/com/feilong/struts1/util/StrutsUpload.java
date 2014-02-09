/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.struts1.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.struts.upload.FormFile;

/**
 *飞龙 struts 上传
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-19 上午10:19:12
 */
public class StrutsUpload{

	/**
	 * 上传文件
	 * 
	 * @param formFile
	 *            FormFile
	 * @param directoryName
	 *            文件夹名称 最后不带"/"
	 * @param fileName
	 *            文件名称+后缀
	 * @author 金鑫
	 * @version 1.0 2010-1-6 上午10:51:08
	 */
	public boolean uploadFile(FormFile formFile,String directoryName,String fileName){
		try{
			OutputStream os = new FileOutputStream(directoryName + "/" + fileName);
			os.write(formFile.getFileData());
			os.close();
			return true;
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * formFile 是否含有文件, 即是否上传了文件
	 * 
	 * @param formFile
	 * @return true 表示有
	 * @author 金鑫
	 * @version 1.0 2010-9-14 下午05:40:51
	 */
	public static boolean isHasFile(FormFile formFile){
		if (null == formFile || formFile.getFileSize() == 0){
			return false;
		}
		return true;
	}
}
