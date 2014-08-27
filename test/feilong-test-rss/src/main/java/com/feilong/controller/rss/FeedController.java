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
package com.feilong.controller.rss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Item;

/**
 * The Class FeedController.
 */
@Controller
public class FeedController{

	/**
	 * Rss.
	 *
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @return the string
	 */
	@RequestMapping("/rss")
	public String rss(Model model,HttpServletRequest request){
		List<Item> itemList = new ArrayList<Item>();
		Item item = new Item();
		item.setTitle("鑫哥好帅");
		Description description = new Description();
		description.setType("text/html");
		// Image image=new Image();
		//
		// image.se
		String root = "http://" + request.getServerName()
						+ (request.getServerPort() == 80 ? "" : ":" + request.getServerPort() + request.getContextPath());
		description.setValue("辟谣:<span style='font:18px;color:red'>鑫哥真的喜欢女的</span><br><embed src='" + root
						+ "/resources/qixiufang.wma'></embed><img style='width:500px' src='" + root + "/resources/image/xinge.jpg'/>");
		item.setDescription(description);
		item.setLink("http://www.feilong.com/");
		item.setAuthor("金鑫");
		item.setPubDate(new Date());
		itemList.add(item);
		// 加入一个Demo的list数据
		model.addAttribute("itemList", itemList);
		// 频道的相关信息
		// model.addAttribute("channel", channel);
		return "rssView";
	}
}
