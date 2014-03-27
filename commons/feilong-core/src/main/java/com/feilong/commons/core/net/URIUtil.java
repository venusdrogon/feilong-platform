/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.net;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 处理url uri 等.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-6-11 上午02:06:43
 * @since 1.0
 */
public final class URIUtil{

	/** The Constant log. */
	private static final Logger	log				= LoggerFactory.getLogger(URIUtil.class);

	/** 查询片段 <code>{@value}</code>. */
	public static final String	fragment		= "#";

	/** ? The question mark is used as a separator and is not part of the query string. */
	public static final String	questionMark	= "?";

	/**
	 * URI uri = new URI(path);<br>
	 * 如果String对象的URI违反了RFC 2396的语法规则，将会产生一个java.net.URISyntaxException。
	 * 
	 * @param path
	 *            the path
	 * @return the URI
	 */
	public static URI getURI(String path){
		try{
			// 如果String对象的URI违反了RFC 2396的语法规则，将会产生一个java.net.URISyntaxException。
			URI uri = new URI(path);
			return uri;
		}catch (URISyntaxException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证path是不是绝对路径.
	 * 
	 * @param path
	 *            待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isAbsolutePath(String path){
		URI uri = getURI(path);

		if (null == uri){
			return false;
		}else{
			return uri.isAbsolute();
		}
	}

	/**
	 * URI.create(url)<br>
	 * 如果知道URI是有效的，不会产生URISyntaxException，可以使用静态的create(String uri)方法
	 * 
	 * @param url
	 *            the url
	 * @param charsetType
	 *            decode/encode 编码
	 * @return 如果异常 返回null
	 * @see {@link http://stackoverflow.com/questions/15004593/java-request-getquerystring-value-different-between-chrome-and-ie-browser}
	 */
	public static URI create(String url,String charsetType){
		if (log.isDebugEnabled()){
			log.debug("[in url]:{}", url);
		}

		if (Validator.isNullOrEmpty(url)){
			return null;
		}else{
			try{
				// 暂不处理 这种路径报错的情况
				// cmens/t-b-f-a-c-s-f-p400-600,0-200,200-400,600-up-gCold Gear-eBase Layer-i1-o.htm
				if (StringUtil.isContain(url, questionMark)){
					// 问号前面的部分
					String before = getBeforePath(url);

					String query = StringUtil.substring(url, questionMark, 1);

					// 浏览器传递queryString()参数差别
					// chrome 会将query 进行 encoded 再发送请求
					// 而ie 原封不动的发送

					// 由于暂时不能辨别是否encoded过,所以 先强制decode 再 encode
					// 此处不能先转 ,参数就是想传 =是转义符
					// query = decode(query, charsetType);

					Map<String, String> map = parseQueryToMap(query);
					url = getEncodedUrl(before, map, charsetType);

					if (log.isDebugEnabled()){
						log.debug("after url:{}", url);
					}
				}else{
					// 不带参数 一般不需要处理
				}
				// 如果知道URI是有效的，不会产生URISyntaxException，可以使用静态的create(String uri)方法
				// 调用的 new URI(str) 方法
				URI uri = URI.create(url);
				return uri;
			}catch (Exception e){
				log.error("url:{}", url);
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * 拼接url(如果charsetType 是null,则原样拼接,如果不是空,则返回安全的url).
	 * 
	 * @param beforeUrl
	 *            支持带?的形式, 内部自动解析
	 * @param paramMap
	 *            参数map value将会被 toString
	 * @param charsetType
	 *            编码,如果为空 ,name 和value 不进行编码
	 * @return the encoded url
	 */
	public static String getEncodedUrl(String beforeUrl,Map<String, ?> paramMap,String charsetType){
		// map 不是空 表示 有参数
		if (Validator.isNotNullOrEmpty(paramMap)){

			Map<String, Object> appendMap = new HashMap<String, Object>();
			appendMap.putAll(paramMap);

			// 注意 action before 可能带参数
			// "action": "https://202.6.215.230:8081/purchasing/purchase.do?action=loginRequest",
			// "fullEncodedUrl":
			// "https://202.6.215.230:8081/purchasing/purchase.do?action=loginRequest?miscFee=0&descp=&klikPayCode=03BELAV220&callback=%2Fpatment1url&totalAmount=60000.00&payType=01&transactionNo=20140323024019&signature=1278794012&transactionDate=23%2F03%2F2014+02%3A40%3A19&currency=IDR",

			// *******************************************
			String beforePath = beforeUrl;

			// 如果包含?
			if (StringUtil.isContain(beforeUrl, questionMark)){
				// 问号前面的部分
				beforePath = getBeforePath(beforeUrl);

				String query = StringUtil.substring(beforeUrl, questionMark, 1);

				// 浏览器传递queryString()参数差别
				// chrome 会将query 进行 encoded 再发送请求
				// 而ie 原封不动的发送

				// 由于暂时不能辨别是否encoded过,所以 先强制decode 再 encode
				// 此处不能先转 ,参数就是想传 =是转义符
				// query = decode(query, charsetType);

				Map<String, String> map = parseQueryToMap(query);
				appendMap.putAll(map);
			}

			StringBuilder builder = new StringBuilder("");
			builder.append(beforePath);
			builder.append(questionMark);

			// *******************************************

			int i = 0;
			int size = appendMap.size();
			for (Map.Entry<String, ?> entry : appendMap.entrySet()){
				String key = entry.getKey();
				// 兼容特殊情况
				Object value = entry.getValue();
				if (null == value){
					value = "";
					log.warn("the param key:[{}] value is null", key);
				}

				if (Validator.isNotNullOrEmpty(charsetType)){
					// 统统先强制 decode 再 encode
					// 浏览器兼容问题
					key = encode(decode(key, charsetType), charsetType);
					if (!"".equals(value)){
						value = encode(decode(value.toString(), charsetType), charsetType);
					}
				}

				builder.append(key);
				builder.append("=");
				builder.append(value);

				// 最后一个& 不拼接
				if (i != size - 1){
					builder.append("&");
				}
				++i;
			}

			return builder.toString();
		}
		return beforeUrl;
	}

	/**
	 * 将a=1&b=2这样格式的数据转换成map,这个方法不会处理 特殊符号 中文等不兼容情况.
	 * 
	 * @param query
	 *            a=1&b=2类型的数据
	 * @return map value的处理,原始的key和value
	 * @see #parseQueryToMap(String, String)
	 */
	public static Map<String, String> parseQueryToMap(String query){
		return parseQueryToMap(query, null);
	}

	/**
	 * 将a=1&b=2这样格式的数据转换成map (如果charsetType 不是null或者empty 返回安全的 key和value).
	 * 
	 * @param query
	 *            a=1&b=2类型的数据
	 * @param charsetType
	 *            何种编码，如果是null或者 empty,那么不参数部分原样返回,自己去处理兼容性问题<br>
	 *            否则会先解码,再加码,因为ie浏览器和chrome 浏览器 url中访问路径 ,带有中文情况下 不一致
	 * @return map value的处理
	 *         <ul>
	 *         <li>没有Validator.isNullOrEmpty(bianma) 那么就原样返回</li>
	 *         <li>如果有编码,统统先强制 decode 再 encode</li>
	 *         </ul>
	 */
	public static Map<String, String> parseQueryToMap(String query,String charsetType){
		if (Validator.isNotNullOrEmpty(query)){
			String[] nameAndValueArray = query.split("&");
			if (Validator.isNotNullOrEmpty(nameAndValueArray)){
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int i = 0, j = nameAndValueArray.length; i < j; ++i){

					String nameAndValue = nameAndValueArray[i];
					String[] tempArray = nameAndValue.split("=", 2);

					if (tempArray != null && tempArray.length == 2){
						String key = tempArray[0];
						String value = tempArray[1];

						if (Validator.isNullOrEmpty(charsetType)){
							// 没有编码 原样返回
							map.put(key, value);
						}else{
							// 统统先强制 decode 再 encode
							// 浏览器兼容问题
							key = encode(decode(key, charsetType), charsetType);
							value = encode(decode(value, charsetType), charsetType);
							map.put(key, value);
						}
					}
				}
				return map;
			}
		}
		return null;
	}

	/**
	 * 获取链接?前面的连接(不包含?).
	 * 
	 * @param url
	 *            the url
	 * @return 如果 url 为空 返回 ""
	 */
	public static String getBeforePath(String url){
		if (Validator.isNullOrEmpty(url)){
			return "";
		}else{
			String before = "";
			// 判断url中是否含有?
			int index = url.indexOf(questionMark);
			if (index == -1){
				before = url;
			}else{
				before = url.substring(0, index);
			}
			return before;
		}
	}

	/**
	 * 获取联合url,通过在指定的上下文中对给定的 spec 进行解析创建 URL。 新的 URL 从给定的上下文 URL 和 spec 参数创建<br>
	 * 网站地址拼接,请使用{@link #getUnionUrl(URL, String)}
	 * <p>
	 * 示例: URIUtil.getUnionUrl("E:\\test", "sanguo")------------->file:/E:/test/sanguo
	 * 
	 * @param context
	 *            要解析规范的上下文
	 * @param spec
	 *            the <code>String</code> to parse as a URL.
	 * @return 获取联合url
	 */
	public static String getUnionUrl(String context,String spec){
		URL url_parent = getURL(context);
		return getUnionUrl(url_parent, spec);
	}

	/**
	 * 获取联合url,通过在指定的上下文中对给定的 spec 进行解析创建 URL。 新的 URL 从给定的上下文 URL 和 spec 参数创建<br>
	 * 网站地址拼接,请使用这个method
	 * <p>
	 * 示例: URIUtil.getUnionUrl("E:\\test", "sanguo")------------->file:/E:/test/sanguo<br>
	 * URL url = new URL("http://www.exiaoshuo.com/jinyiyexing/");<br>
	 * result = URIUtil.getUnionUrl(url, "/jinyiyexing/1173348/");<br>
	 * http://www.exiaoshuo.com/jinyiyexing/1173348/
	 * 
	 * @param context
	 *            要解析规范的上下文
	 * @param spec
	 *            the <code>String</code> to parse as a URL.
	 * @return 获取联合url
	 */
	public static String getUnionUrl(URL context,String spec){
		try{
			URL url_union = new URL(context, spec);
			return url_union.toString();
		}catch (MalformedURLException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串路径转成url.
	 * 
	 * @param pathName
	 *            字符串路径
	 * @return url
	 */
	public static URL getURL(String pathName){
		File file = new File(pathName);
		try{
			// file.toURL() 已经过时,它不会自动转义 URL 中的非法字符
			return file.toURI().toURL();
		}catch (MalformedURLException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * iso-8859的方式去除乱码.
	 * 
	 * @param str
	 *            字符串
	 * @param bianma
	 *            使用的编码
	 * @return 原来的字符串
	 * @deprecated
	 */
	public static String decodeLuanMa_ISO8859(String str,String bianma){
		if (Validator.isNotNullOrEmpty(str)){
			try{
				return new String(str.trim().getBytes(CharsetType.ISO_8859_1), bianma);
			}catch (UnsupportedEncodingException e){
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * 加码,对参数值进行编码 <br>
	 * 使用以下规则：
	 * <ul>
	 * <li>字母数字字符 "a" 到 "z"、"A" 到 "Z" 和 "0" 到 "9" 保持不变。</li>
	 * <li>特殊字符 "."、"-"、"*" 和 "_" 保持不变。</li>
	 * <li>空格字符 " " 转换为一个加号 "+"。</li>
	 * <li>所有其他字符都是不安全的，因此首先使用一些编码机制将它们转换为一个或多个字节。<br>
	 * 然后每个字节用一个包含 3 个字符的字符串 "%xy" 表示，其中 xy 为该字节的两位十六进制表示形式。<br>
	 * 推荐的编码机制是 UTF-8。<br>
	 * 但是，出于兼容性考虑，如果未指定一种编码，则使用相应平台的默认编码。</li>
	 * </ul>
	 * 
	 * @param value
	 *            the value
	 * @param charsetType
	 *            charsetType {@link CharsetType}
	 * @return 加码之后的值<br>
	 *         如果 charsetType 是空 原样返回 value
	 * @see CharsetType
	 */
	public static String encode(String value,String charsetType){
		if (Validator.isNullOrEmpty(charsetType)){
			return value;
		}

		try{
			return URLEncoder.encode(value, charsetType);
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解码,对参数值进行解码 <br>
	 * Decodes a <code>application/x-www-form-urlencoded</code> string using a specific encoding scheme. The supplied encoding is used to
	 * determine what characters are represented by any consecutive sequences of the form "<code>%<i>xy</i></code>".
	 * <p>
	 * <em><strong>Note:</strong> The <a href=
	 * "http://www.w3.org/TR/html40/appendix/notes.html#non-ascii-chars">
	 * World Wide Web Consortium Recommendation</a> states that
	 * UTF-8 should be used. Not doing so may introduce
	 * incompatibilites.</em>
	 * 
	 * @param value
	 *            需要被解码的值
	 * @param charsetType
	 *            charsetType {@link CharsetType}
	 * @return the newly decoded <code>String</code> 解码之后的值<br>
	 *         如果 charsetType 是空 原样返回 value
	 * @see URLEncoder#encode(java.lang.String, java.lang.String)
	 * @see CharsetType
	 */
	public static String decode(String value,String charsetType){
		if (Validator.isNullOrEmpty(charsetType)){
			return value;
		}
		try{
			return URLDecoder.decode(value, charsetType);
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * url中的特殊字符转为16进制代码,用于url传递.
	 * 
	 * @param specialCharacter
	 *            特殊字符
	 * @return 特殊字符url编码
	 */
	@Deprecated
	public static String specialCharToHexString(String specialCharacter){

		Map<String, String> specialCharacterMap = new HashMap<String, String>();

		specialCharacterMap.put("+", "%2B");// URL 中+号表示空格
		specialCharacterMap.put(" ", "%20");// URL中的空格可以用+号或者编码
		specialCharacterMap.put("/", "%2F");// 分隔目录和子目录
		specialCharacterMap.put(questionMark, "%3F");// 分隔实际的 URL 和参数
		specialCharacterMap.put("%", "%25");// 指定特殊字符
		specialCharacterMap.put("#", "%23");// 表示书签
		specialCharacterMap.put("&", "%26");// URL 中指定的参数间的分隔符
		specialCharacterMap.put("=", "%3D");// URL 中指定参数的值

		if (specialCharacterMap.containsKey(specialCharacter)){
			return specialCharacterMap.get(specialCharacter);
		}
		// 不是 url 特殊字符 原样输出
		return specialCharacter;
	}

}
