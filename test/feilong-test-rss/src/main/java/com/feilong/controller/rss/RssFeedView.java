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

@Component("rssView")
public class RssFeedView extends AbstractRssFeedView{

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

	@SuppressWarnings("unchecked")
	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String key = "itemList";
		List<Item> rssItems = (List<Item>) model.get(key);
		return rssItems;
	}
}
