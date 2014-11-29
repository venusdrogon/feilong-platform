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

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.feilong.commons.core.enumeration.CharsetType;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;

/**
 * The Class RssFeedView.
 */
@Component("rssView")
public class RssFeedView extends AbstractRssFeedView{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.feed.AbstractFeedView#buildFeedMetadata(java.util.Map, com.sun.syndication.feed.WireFeed, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void buildFeedMetadata(Map<String, Object> model,Channel channel,HttpServletRequest request){
		// Channel channel2 = (Channel) model.get("channel");
		// BeanUtil.copyProperties(channel, channel2);
		// Channel channel = new Channel();
		channel.setTitle("飞龙官方商城");
		channel.setDescription("飞龙官方商城");
		channel.setCopyright("feilong");
		channel.setEncoding(CharsetType.GBK);
		channel.setPubDate(new Date());
		channel.setFeedType("rss_2.0");
		channel.setLink("http://www.feilong.com/");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.feed.AbstractRssFeedView#buildFeedItems(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String key = "itemList";
		List<Item> rssItems = (List<Item>) model.get(key);
		return rssItems;
	}
}
