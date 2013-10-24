package com.feilong.commons.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.feilong.commons.core.lang.ClassLoaderUtil;

public class NamedQuerys implements Serializable{

	private final static Logger	log					= LoggerFactory.getLogger(NamedQuerys.class);

	private static final long	serialVersionUID	= -3041827711013056715L;

	/**
	 * classpath 下面 对应的xsd 资源名称
	 */
	public static final String	xsd_file_path		= "namedquerys-1.0.xsd";

	/**
	 * sql 文件夹在classpath 下面的路径
	 */
	public static final String	sql_directory_path	= "namedsql";

	private static String		xpathExpression		= "//query";

	private static String		element_name		= "name";

	private static String		element_ql			= "ql";

	private Map<String, String>	querys				= new HashMap<String, String>();

	// ******************************************************************************************************
	public NamedQuerys(){
		init();
	}

	public String getQueryByName(String name){
		String result = querys.get(name);
		if (null == result){
			throw new RuntimeException("Named Query not found with name:" + name);
		}
		return result;
	}

	public void init(){
		// init inner query
		SAXReader saxReader = new SAXReader();
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		URL schemaUrl = ClassLoaderUtil.getResource(xsd_file_path, this.getClass());
		try{
			Schema schema = schemaFactory.newSchema(schemaUrl);
			Validator validator = schema.newValidator();
			Enumeration<URL> urls = ClassLoaderUtil.getResources(sql_directory_path, this.getClass());
			while (urls.hasMoreElements()){
				URL url = urls.nextElement();
				log.debug("url:" + url.toString());
				File dir = new File(url.toURI());
				if (dir.isDirectory()){
					convertFileToMap(dir, saxReader, validator);
				}else{
					log.error("Wrong path defintion for named querys: " + sql_directory_path);
				}
			}
		}catch (SAXException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}catch (URISyntaxException e){
			e.printStackTrace();
		}
	}

	/*********************************************************************************************/
	private void convertFileToMap(File file,SAXReader saxReader,Validator validator){
		log.debug("begin convert file:" + file.getAbsolutePath());
		if (file.isDirectory()){
			for (File file_child : file.listFiles()){
				convertFileToMap(file_child, saxReader, validator);
			}
		}else{
			try{
				validator.validate(new StreamSource(new FileInputStream(file)));
				Document document = saxReader.read(new FileInputStream(file));
				convertDocumentToMap(document);
			}catch (FileNotFoundException e){
				log.error(file.getName() + " seems is not find");
			}catch (SAXException e){
				log.error(file.getName() + " validate saxexception");
			}catch (IOException e){
				log.error(file.getName() + " io exception");
			}catch (DocumentException e){
				log.error(file.getName() + " seems is not one valid file:" + e.getMessage());
			}
		}
	}

	/**
	 * 解析document
	 * 
	 * @param document
	 */
	@SuppressWarnings("unchecked")
	private void convertDocumentToMap(Document document){
		List<Node> nodes = document.selectNodes(xpathExpression);
		for (Node node : nodes){
			Element element = (Element) node;
			String element_name_text = element.elementTextTrim(element_name);
			String element_ql_text = element.elementTextTrim(element_ql);
			log.debug("name:" + element_name_text);
			log.debug("ql:" + element_ql_text);
			querys.put(element_name_text, element_ql_text);
		}
	}
}
