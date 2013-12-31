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
package com.feilong.tools.middleware;

import java.util.LinkedList;

import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 处理短信
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-4-19 下午04:22:54
 * @since 1.0
 */
public class SMSUtil{

	// 运营商添加的字符
	private static int		smsAddLength	= 0;

	// 最长70个字符
	private static int		oneSMSMaxLength	= 70 - smsAddLength;

	/**
	 * 后缀的标记 eg.[飞龙]
	 */
	private static String[]	tagsSuffix		= { "[", "]" };

	/**
	 * 前缀的标记 eg.[1/2]
	 */
	private static String[]	tagsPrefix		= { "[", "]" };

	/**
	 * 发送短信获得返回的信息
	 * 
	 * @param phonenumber
	 *            手机号码
	 * @param smsMsg
	 *            短信内容
	 * @return 返回的信息
	 */
	public static String getSMSReturnInfo(String phonenumber,String smsMsg){
		//String url = "http://sms.mobsms.net/send/g70send.aspx?name=hykj&pwd=654321&dst=" + phonenumber + "&msg=" + smsMsg + "";
		//return FeiLongHttpClientUtil.getHttpMethodResponseBodyAsString(url, HttpMethodType.POST);
		return null;
		// num=2&success=18916782320,15221520717,&faile=&err=发送成功&errid=0
		// num=1&success=13817710742,&faile=&err=发送成功&errid=0
	}

	/**
	 * 将短信内容转换成 LinkedList
	 * 
	 * @param servletContext
	 *            servletContext
	 * @param smsContent
	 *            短信内容
	 * @param suffix
	 *            后缀,可以指定,如果为""或者null,则读取feilong.user.properties文件 projectChineseName键
	 * @return
	 */
	//	public static LinkedList<String> convertSmsContentToLinkedList(ServletContext servletContext,String smsContent,String suffix){
	//		// 后缀
	//		String tag = "";
	//		// 含有标识
	//		if (Validator.isNotNull(suffix)){
	//			tag = suffix;
	//		}else{
	//			tag = FeiLongPropertiesConfigure.getPropertiesFeiLongValueWithServletContext(servletContext, "projectChineseName");
	//		}
	//		return convertSmsContentToLinkedList(smsContent, tag);
	//	}
	/**
	 * 将短信内容转换成 LinkedList
	 * 
	 * @param clz
	 *            调用class,以便读取默认后缀标记
	 * @param smsContent
	 *            短信内容
	 * @param suffix
	 *            后缀,可以指定,如果为""或者null,则读取feilong.user.properties文件 projectChineseName键
	 * @return LinkedList
	 */
	//	public static LinkedList<String> convertSmsContentToLinkedList(Class clz,String smsContent,String suffix){
	//		// 后缀
	//		String tag = "";
	//		// 含有标识
	//		if (Validator.isNotNull(suffix)){
	//			tag = suffix;
	//		}else{
	//			tag = FeiLongPropertiesConfigure.getPropertiesFeiLongValueWithClassLoader(clz, "projectChineseName");
	//		}
	//		return convertSmsContentToLinkedList(smsContent, tag);
	//	}
	/**
	 * 将短信内容转换成 LinkedList
	 * 
	 * @param smsContent
	 *            短信内容
	 * @param tag
	 *            后缀,可以指定,如果为""或者null,则读取feilong.user.properties文件 projectChineseName键
	 * @return LinkedList
	 */
	public static LinkedList<String> convertSmsContentToLinkedList(String smsContent,String tag){
		LinkedList<String> linkedList = new LinkedList<String>();
		if (Validator.isNotNullOrEmpty(smsContent)){
			// 前面短信内容已经含有Params.projectChineseName 2是符号的长度
			int contentLength = oneSMSMaxLength - tag.length() - 2;
			// 循环次数
			int i = 1;
			// 总条数
			int j = 1;
			// 总字数
			int allLength = smsContent.length();
			// 计算总条数(分几条发)
			if (allLength > contentLength){
				// 每次发送最大长度 -2-1-2 符号/[]ij
				contentLength = contentLength - 2 - 1 - 2;
				j = allLength / contentLength + (allLength % contentLength == 0 ? 0 : 1);
			}
			// [1/2]
			// 不是空
			// 发送一条信息的内容
			String oneSendContent = "";
			while (i <= j){
				// 每条短信的内容
				oneSendContent = "";
				// 多于1条 增加序号
				if (j > 1){
					oneSendContent += tagsPrefix[0] + i + "/" + j + tagsPrefix[1];
				}
				oneSendContent += StringUtil.substring(smsContent, (i - 1) * contentLength, contentLength);
				oneSendContent += tagsSuffix[0] + tag + tagsSuffix[1];
				linkedList.add(oneSendContent);
				i++;
			}
		}else{
			linkedList.add("");
		}
		return linkedList;
	}
}
