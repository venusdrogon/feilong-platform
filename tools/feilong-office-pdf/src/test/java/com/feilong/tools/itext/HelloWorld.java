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
package com.feilong.tools.itext;

import java.util.ArrayList;
import java.util.List;

import com.feilong.tools.office.pdf.PDFUtil;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;

/**
 * The Class HelloWorld.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:25:30
 */
public class HelloWorld{

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args){

		// 定义输出位置并把文档对象装入输出对象中
		String outputFileName = "E:/hello.pdf";

		List<Element> arrayList = new ArrayList<Element>();

		arrayList.add(new Paragraph("HelloWorld"));
		PDFUtil.write(outputFileName, arrayList);
	}
}
