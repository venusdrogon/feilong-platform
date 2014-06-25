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
package com.feilong.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rsslibj.elements.Channel;

/**
 * The Class RSSUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-7-26 下午09:06:14
 * @since 1.0
 */
public class RSSUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(RSSUtil.class);

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
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
			log.info("The feed in RDF: " + channel.getFeed("rss"));
		}catch (InstantiationException e){
			e.printStackTrace();
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
