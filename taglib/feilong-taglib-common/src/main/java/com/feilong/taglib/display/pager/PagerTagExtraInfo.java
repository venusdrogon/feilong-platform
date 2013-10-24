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
package com.feilong.taglib.display.pager;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.ValidationMessage;
import javax.servlet.jsp.tagext.VariableInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.taglib.base.BaseTagTEL;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-13 上午12:50:50
 */
public class PagerTagExtraInfo extends BaseTagTEL{

	private static final Logger	log	= LoggerFactory.getLogger(PagerTagExtraInfo.class);

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagExtraInfo#validate(javax.servlet.jsp.tagext.TagData)
	 */
	@Override
	public ValidationMessage[] validate(TagData tagData){
//		Object count = tagData.getAttribute("maxElements");
//		if (TagData.REQUEST_TIME_VALUE == count){
//			// String id = tagData.getId();
//			// return new ValidationMessage[] { new ValidationMessage(id, "asdasdasdad") };
//		}
//		if (TagData.REQUEST_TIME_VALUE == count){
//			showAttributes(tagData);
//			Object allPageNo = tagData.getAttribute("allPageNo");
//			Object pageParamName = tagData.getAttribute("pageParamName");
//			// if (maxElements < 0){
//			// log.debug("the param maxElements:{},must >=0", maxElements);
//			// return false;
//			// }
////			Object maxIndexPages = tagData.getAttribute("maxIndexPages");
////			Object skin = tagData.getAttribute("skin");
//		}
		return super.validate(tagData);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagExtraInfo#getVariableInfo(javax.servlet.jsp.tagext.TagData)
	 */
	@Override
	public VariableInfo[] getVariableInfo(TagData tagData){
		// VariableInfo[] variableInfos = new VariableInfo[1];
		// variableInfos[0] = new VariableInfo(tagData.getAttributeString("id"), "java.lang.String[]", true, VariableInfo.NESTED);
		// return (variableInfos);
		return super.getVariableInfo(tagData);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagExtraInfo#isValid(javax.servlet.jsp.tagext.TagData)
	 */
	@Override
	public boolean isValid(TagData tagData){
		return super.isValid(tagData);
	}
}
