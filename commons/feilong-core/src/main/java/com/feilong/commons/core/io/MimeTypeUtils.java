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
package com.feilong.commons.core.io;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import com.feilong.commons.core.util.Validator;

/**
 * 获取文件Mime-Type.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年11月19日 上午1:12:50
 * @see org.apache.catalina.startup.Tomcat#DEFAULT_MIME_MAPPINGS
 * @see <a href="http://stackoverflow.com/questions/4348810/java-library-to-find-the-mime-type-from-file-content/10140531#10140531">java
 *      library to find the mime type from file content</a>
 * @see <a href="http://stackoverflow.com/questions/51438/getting-a-files-mime-type-in-java">Getting
 *      A File's Mime Type In Java</a>
 * @see <a href="http://tika.apache.org/">也可以使用Apache Tika</a>
 * @since 1.0.8
 */
public final class MimeTypeUtils{

	/** The Constant fileExtensionMap. */
	private static final Map<String, String>	fileExtensionMap;

	/** Don't let anyone instantiate this class. */
	private MimeTypeUtils(){}

	static{
		fileExtensionMap = new HashMap<String, String>();
		// MS Office
		fileExtensionMap.put("doc", "application/msword");
		fileExtensionMap.put("dot", "application/msword");
		fileExtensionMap.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		fileExtensionMap.put("dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template");
		fileExtensionMap.put("docm", "application/vnd.ms-word.document.macroEnabled.12");
		fileExtensionMap.put("dotm", "application/vnd.ms-word.template.macroEnabled.12");
		fileExtensionMap.put("xls", "application/vnd.ms-excel");
		fileExtensionMap.put("xlt", "application/vnd.ms-excel");
		fileExtensionMap.put("xla", "application/vnd.ms-excel");
		fileExtensionMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		fileExtensionMap.put("xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template");
		fileExtensionMap.put("xlsm", "application/vnd.ms-excel.sheet.macroEnabled.12");
		fileExtensionMap.put("xltm", "application/vnd.ms-excel.template.macroEnabled.12");
		fileExtensionMap.put("xlam", "application/vnd.ms-excel.addin.macroEnabled.12");
		fileExtensionMap.put("xlsb", "application/vnd.ms-excel.sheet.binary.macroEnabled.12");
		fileExtensionMap.put("ppt", "application/vnd.ms-powerpoint");
		fileExtensionMap.put("pot", "application/vnd.ms-powerpoint");
		fileExtensionMap.put("pps", "application/vnd.ms-powerpoint");
		fileExtensionMap.put("ppa", "application/vnd.ms-powerpoint");
		fileExtensionMap.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
		fileExtensionMap.put("potx", "application/vnd.openxmlformats-officedocument.presentationml.template");
		fileExtensionMap.put("ppsx", "application/vnd.openxmlformats-officedocument.presentationml.slideshow");
		fileExtensionMap.put("ppam", "application/vnd.ms-powerpoint.addin.macroEnabled.12");
		fileExtensionMap.put("pptm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12");
		fileExtensionMap.put("potm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12");
		fileExtensionMap.put("ppsm", "application/vnd.ms-powerpoint.slideshow.macroEnabled.12");

		// Open Office
		fileExtensionMap.put("odt", "application/vnd.oasis.opendocument.text");
		fileExtensionMap.put("ott", "application/vnd.oasis.opendocument.text-template");
		fileExtensionMap.put("oth", "application/vnd.oasis.opendocument.text-web");
		fileExtensionMap.put("odm", "application/vnd.oasis.opendocument.text-master");
		fileExtensionMap.put("odg", "application/vnd.oasis.opendocument.graphics");
		fileExtensionMap.put("otg", "application/vnd.oasis.opendocument.graphics-template");
		fileExtensionMap.put("odp", "application/vnd.oasis.opendocument.presentation");
		fileExtensionMap.put("otp", "application/vnd.oasis.opendocument.presentation-template");
		fileExtensionMap.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
		fileExtensionMap.put("ots", "application/vnd.oasis.opendocument.spreadsheet-template");
		fileExtensionMap.put("odc", "application/vnd.oasis.opendocument.chart");
		fileExtensionMap.put("odf", "application/vnd.oasis.opendocument.formula");
		fileExtensionMap.put("odb", "application/vnd.oasis.opendocument.database");
		fileExtensionMap.put("odi", "application/vnd.oasis.opendocument.image");
		fileExtensionMap.put("oxt", "application/vnd.openofficeorg.extension");
	}

	/**
	 * 获得 content type by file name.<br>
	 * 
	 * //TODO
	 * <b>
	 * Very incomplete function. As of Java 7, html, pdf and jpeg extensions return the correct mime-type but js and css return null! </b> <br>
	 * 
	 * <p>
	 * I tried Apache Tika but it is huge with tons of dependencies, <br>
	 * URLConnection doesn't use the bytes of the files, <br>
	 * MimetypesFileTypeMap also just looks at files names,<br>
	 * and I couldn't move to Java 7.
	 * </p>
	 * 
	 * @param fileName
	 *            the file name
	 * @return the content type by file name
	 * @see java.net.URLConnection#getFileNameMap()
	 * @see java.net.FileNameMap#getContentTypeFor(String)
	 * @see org.apache.commons.io.FilenameUtils#getExtension(String)
	 * @see java.net.URLConnection#guessContentTypeFromName(String)
	 * @see java.net.URLConnection#guessContentTypeFromStream(java.io.InputStream)
	 * @see Files#probeContentType(java.nio.file.Path)
	 */
	public static String getContentTypeByFileName(String fileName){
		// 1. first use java's build-in utils
		//从数据文件加载文件名映射（一个 mimetable）。它首先尝试加载由 "content.types.user.table" 属性定义的特定于用户的表。如果加载失败，它会尝试加载位于 java 主目录下的 lib/content-types.properties 中的默认内置表。
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentType = fileNameMap.getContentTypeFor(fileName);

		// 2. nothing found -> lookup our in extension map to find types like ".doc" or ".docx"
		if (Validator.isNullOrEmpty(contentType)){
			String extension = FilenameUtils.getExtension(fileName);
			contentType = fileExtensionMap.get(extension);
		}

		return contentType;
	}
}