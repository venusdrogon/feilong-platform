/*
 * Created on 2004-1-3，创建第一个Hello World程序
 */
package com.feilong.tools.itext;

import java.util.ArrayList;
import java.util.List;

import com.feilong.commons.office.pdf.PDFUtil;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;

public class HelloWorld{

	public static void main(String[] args){

		// 定义输出位置并把文档对象装入输出对象中
		String outputFileName = "E:/hello.pdf";

		List<Element> arrayList = new ArrayList<Element>();

		arrayList.add(new Paragraph("HelloWorld"));
		PDFUtil.write(outputFileName, arrayList);
	}
}
