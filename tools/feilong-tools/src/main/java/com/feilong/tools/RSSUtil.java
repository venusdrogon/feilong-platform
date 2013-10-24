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
package com.feilong.tools;

import com.rsslibj.elements.Channel;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-7-26 下午09:06:14
 * @since 1.0
 */
public class RSSUtil{

	public static void main(String[] args){
		Channel channel = new Channel();
		channel.setTitle("金鑫");
		channel.setLink("http://localhost/");
		channel.setDescription("This is my sample channel.");
		channel.setImage("http://localhost/", "The Channel Image", "http://localhost/foo.jpg");
		channel.setTextInput("http://localhost/search", "Search The Channel Image", "The Channel Image", "s");
		channel.addItem("http://localhost/item1", "The First 金鑫>", "The First Item").setDcContributor("Joseph B. Ottinger");
		channel.addItem("http://localhost/item2", "The Second 金鑫", "The Second Item").setDcCreator("Jason Bell");
		try{
			System.out.println("The feed in RDF: " + channel.getFeed("rss"));
		}catch (InstantiationException e){
			e.printStackTrace();
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
