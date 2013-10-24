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
package jdk.java.util;

import java.util.ListResourceBundle;

import com.feilong.test.User;


/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 18, 2012 11:45:42 PM
 */
public class SanguoListResourceBundle extends ListResourceBundle{

	/*
	 * (non-Javadoc)
	 * @see java.util.ListResourceBundle#getContents()
	 */
	@Override
	protected Object[][] getContents(){

		return new Object[][] {
				{ "关羽", "云长" },
				{ "张飞", "翼德" },
				{ "赵云", "子龙" },
				{ "马超", "孟起" },
				{ "黄忠", "汉升" },
				{ "刘备", "玄德" },
				{ "诸葛亮", "孔明" },
				{ "姜维", "伯约" },
				{ "魏延", "文长" },
				{ "庞统", "士元" },
				{ "法正", new User() } };
	}
}
