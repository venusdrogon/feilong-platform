package com.feilong.tools.jsoup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.io.CSVUtil;
import com.feilong.commons.core.io.IOReaderUtil;
import com.feilong.commons.core.io.IOUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 进包网
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-10-22 下午06:45:18
 */
public class FeiLongJinBaoWangCrawler{

	private final static Logger	log			= LoggerFactory.getLogger(FeiLongJinBaoWangCrawler.class);

	private static String		searchPage	= "http://www.jinbaowang.cn/gallery--n,%s-grid.html";

	/**
	 * 将map 转成文件
	 * 
	 * @param skuCodeAndImagesMap
	 * @param directoryName
	 */
	public static void convertSkuCodeImagesToFile(Map<String, List<String>> skuCodeAndImagesMap,String directoryName){
		String columnTitles[] = { "code", "image" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		for (Map.Entry<String, List<String>> entry : skuCodeAndImagesMap.entrySet()){
			String code = entry.getKey();
			List<String> images = entry.getValue();
			if (Validator.isNotNullOrEmpty(images)){
				for (String image : images){
					Object[] objects = { code, image };
					dataList.add(objects);
				}
			}
		}
		Date now = new Date();
		String timestamp = DateUtil.date2String(now, DatePattern.timestamp);
		CSVUtil.write(directoryName + "/" + timestamp + ".csv", columnTitles, dataList);
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(directoryName + "/" + timestamp + ".obj"));
			out.writeObject(skuCodeAndImagesMap);
			out.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param objPath
	 * @return
	 */
	public static Map<String, List<String>> convertObjectToMap(String objPath){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(objPath));
			Map<String, List<String>> skuCodeAndImagesMap = (Map<String, List<String>>) in.readObject();
			in.close();
			return skuCodeAndImagesMap;
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析商家编码文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static List<String> getCodeList(String filePath){
		String content = IOReaderUtil.getFileContent(filePath);
		// 将内容以换行符转成数组
		String[] codeRows = content.split("\r\n");
		if (Validator.isNotNullOrEmpty(codeRows)){
			List<String> codeList = new ArrayList<String>();
			for (String codeRow : codeRows){
				if (!codeRow.equals("商家编码") && Validator.isNotNullOrEmpty(codeRow)){
					codeList.add(codeRow);
				}
			}
			return codeList;
		}
		return null;
	}

	/**
	 * 根据codes 获得 skuCodeAndImages
	 * 
	 * @param codes
	 *            codes
	 * @return skuCodeAndImages
	 */
	public static Map<String, List<String>> getSkuCodeAndImagesMap(List<String> codes){
		if (Validator.isNullOrEmpty(codes)){
			throw new IllegalArgumentException("codes cannot be null");
		}
		Map<String, List<String>> skuCodeAndImages = new HashMap<String, List<String>>();
		List<String> list = null;
		String skuDetailsRealUrl = null;
		for (String code : codes){
			skuDetailsRealUrl = getSkuDetailsRealUrl(code);
			if (Validator.isNotNullOrEmpty(skuDetailsRealUrl)){
				list = getSkuDetailsImages(skuDetailsRealUrl);
			}
			skuCodeAndImages.put(code, list);
		}
		return skuCodeAndImages;
	}

	/**
	 * 通过code 商家编码 ,获得 在进包网上面的 真实的 商品详细页面url
	 * 
	 * @param code
	 *            商家编码
	 * @return 在进包网上面的 真实的 商品详细页面url
	 */
	public static String getSkuDetailsRealUrl(String code){
		String urlString = StringUtil.format(searchPage, code);
		Document document = JsoupUtil.getDocument(urlString);
		if (null != document){
			String query = ".items-gallery .goodinfo h6 a";
			Elements elements = document.select(query);
			for (Element element : elements){
				String title = element.attr("title");
				if (title.equals(code)){
					return element.attr("href");
				}
			}
		}
		log.error(code + " cannot be find!");
		return null;
	}

	/**
	 * 下载sku 的 内容介绍图片<br>
	 * 
	 * @param skuDetailsRealUrl
	 *            sku 真实路径
	 * @param skuCode
	 *            code
	 * @param directoryName
	 *            目标文件夹
	 */
	public static List<String> getSkuDetailsImages(String skuDetailsRealUrl){
		Document document = JsoupUtil.getDocument(skuDetailsRealUrl);
		if (null != document){
			Elements elements = document.select("#goods-intro img");
			List<String> list = new ArrayList<String>();
			for (Element element : elements){
				String url = element.attr("src");
				list.add(url);
			}
			return list;
		}
		log.error(skuDetailsRealUrl + " cannot be find!");
		return null;
	}

	/**
	 * 自动抓取该详细页面所有的图片 下载到 directoryName/skuCode 目录下面
	 * 
	 * @param skuCodeAndImages
	 * @param directoryName
	 */
	public static void downSkuImages(Map<String, List<String>> skuCodeAndImagesMap,String directoryName){
		// 目标文件夹
		String destination = null;
		List<String> images = null;
		String skuCode = null;
		log.info("begin down...");
		for (Entry<String, List<String>> skuCodeAndImages : skuCodeAndImagesMap.entrySet()){
			skuCode = skuCodeAndImages.getKey();
			images = skuCodeAndImages.getValue();
			if (Validator.isNotNullOrEmpty(images)){
				destination = directoryName + "/" + skuCode;
				log.info("begin down sku:" + skuCode);
				for (String image : images){
					IOUtil.down(image, destination);
				}
				log.info(skuCode + " down over");
			}
		}
		log.info("down over~~耶");
	}
}
