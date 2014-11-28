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
package com.feilong.tools.jsoup.top100;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用jsoup分析下载巨鲸的mp3.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-28 上午10:31:02
 */
public class Top100Mp3Downloader{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(Top100Mp3Downloader.class);

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args){
		Top100Mp3Downloader m = new Top100Mp3Downloader();
		Map<String, String> map = m.findIds("http://www.top100.cn/artist/info-agr5dcqe.shtml");
		for (Map.Entry<String, String> e : map.entrySet()){
			String name = e.getKey();
			String path = m.findDownPathById(e.getValue());
			m.downByPath("F:\\music\\files\\Michael Jackson", name, path);
			log.info(name + " from " + path + " has down!");
		}
	}

	//***************************************************************************
	/**
	 * 给定歌曲列表页面,返回歌曲名称和加密id的键值对.
	 * 
	 * @param url
	 *            歌曲列表地址,如:http://www.top100.cn/artist/info-agr5dcqe.shtml
	 * @return 键值对
	 */
	private Map<String, String> findIds(String url){
		Map<String, String> map = new HashMap<String, String>();
		try{
			URL u = new URL(url);
			Document doc = Jsoup.parse(u, 1000 * 10);
			Element listDiv = doc.getElementById("songsListDiv");
			Elements uls = listDiv.getElementsByTag("ul");
			for (int i = 0; i < uls.size(); i++){
				Element ul = uls.get(i);
				Element hidden = ul.getElementById("hidValue");
				String id = hidden.val();
				Element li = ul.getElementsByAttributeValue("class", "l3").first();
				Element href = li.getElementsByTag("a").first();
				String name = href.attr("title");
				map.put(name, id);
			}
			return map;
		}catch (MalformedURLException e){
			log.error(e.getClass().getName(), e);
		}catch (IOException e){
			log.error(e.getClass().getName(), e);
		}
		return null;
	}

	/**
	 * 从歌曲的加密id获取歌曲的下载页面,并分析得到下载地址.
	 * 
	 * @param id
	 *            加密id
	 * @return 歌曲下载页面地址
	 */
	private String findDownPathById(String id){
		if (id.startsWith("m")){// 所有id都是m开头  
			id = id.substring(1);
		}
		try{
			URL url = new URL("http://www.top100.cn/download/download.aspx?Productid=" + id);
			Document doc = Jsoup.parse(url, 1000 * 2);
			Elements eles = doc.getElementsByAttributeValue("onclick", "javascript:$(this).css('color','red');");
			String path = null;
			for (int i = 0; i < eles.size(); i++){
				Element e = eles.get(i);
				if (e.tagName().equals("a")){
					path = e.attr("href");
					break;
				}
			}
			return path;
		}catch (MalformedURLException e){
			log.error(e.getClass().getName(), e);
		}catch (IOException e){
			log.error(e.getClass().getName(), e);
		}
		return null;
	}

	/**
	 * 从获取的下载地址获取歌曲内容.
	 * 
	 * @param dir
	 *            保存到目录
	 * @param name
	 *            歌曲名称
	 * @param path
	 *            歌曲下载地址
	 */
	private void downByPath(String dir,String name,String path){
		File parent = new File(dir);
		if (!parent.exists()){
			parent.mkdirs();
		}
		File mp3 = new File(parent, name + ".mp3");
		try{
			URL url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			// 此处必须伪造referer,否则会自动返回首页.分析后,与cookie无关  
			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon;)");
			httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
			httpURLConnection.setRequestProperty("referer", "http://www.top100.cn");
			httpURLConnection.setDoInput(true);
			httpURLConnection.connect();
			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
				InputStream is = httpURLConnection.getInputStream();
				byte[] b = new byte[1024 * 5];
				int length = -1;
				OutputStream os = new FileOutputStream(mp3);
				while ((length = is.read(b)) != -1){
					os.write(b, 0, length);
				}
				os.flush();
				os.close();
				is.close();
			}else{
				log.info("服务器返回:" + httpURLConnection.getResponseCode());
			}
		}catch (MalformedURLException e){
			log.error(e.getClass().getName(), e);
		}catch (IOException e){
			log.error(e.getClass().getName(), e);
		}
	}
}
