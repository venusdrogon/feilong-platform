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
package com.feilong.tools.mail.entity;

import java.util.LinkedList;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 24, 2011 1:07:09 AM
 */
public class AttachMailEntity extends BaseMailEntity{

	/**
	 * 邮件附件的文件名
	 */
	private String[]			attachFileNames;

	/**
	 * 获取图片的二进制
	 */
	private LinkedList<byte[]>	attachList;

	/**
	 * 邮件附件的文件名
	 * 
	 * @return attachFileNames
	 */
	public String[] getAttachFileNames(){
		return attachFileNames;
	}

	/**
	 * 邮件附件的文件名
	 * 
	 * @param fileNames
	 */
	public void setAttachFileNames(String[] fileNames){
		this.attachFileNames = fileNames;
	}

	/**
	 * 获取图片的二进制
	 * 
	 * @return
	 */
	public LinkedList<byte[]> getAttachList(){
		return attachList;
	}

	/**
	 * 获取图片的二进制
	 * 
	 * @param attachList
	 */
	public void setAttachList(LinkedList<byte[]> attachList){
		this.attachList = attachList;
	}
}
