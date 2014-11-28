/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.tools.office.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 处理PDF文档 (PDF)
 * 
 * @author 金鑫 2010-2-27 下午05:14:03
 */
public final class PDFUtil{

	private static final Logger	log	= LoggerFactory.getLogger(PDFUtil.class);

	/**
	 * html文件转换成pdf
	 * 
	 * @param htmlPath
	 *            需要被转换的html路径及名称
	 * @param outputFileName
	 *            输出pdf文件路径级名称
	 * @author 金鑫
	 * @version 1.0 2010-2-26 下午06:01:45
	 */
	public static void htmlToPDF(String htmlPath,String outputFileName){
		try{
			Reader reader = new FileReader(htmlPath);

			StyleSheet styleSheet = new StyleSheet();
			styleSheet.loadTagStyle("body", "leading", "16,0");

			@SuppressWarnings("unchecked")
			ArrayList<Element> arrayList = HTMLWorker.parseToList(reader, styleSheet);
			write(outputFileName, arrayList);
		}catch (FileNotFoundException e){
			log.error(e.getClass().getName(), e);
		}catch (IOException e){
			log.error(e.getClass().getName(), e);
		}
	}

	/**
	 * 输出pdf 文件
	 * 
	 * @param outputFileName
	 *            地址
	 * @param elementList
	 */
	public static void write(String outputFileName,List<Element> elementList){
		// 创建一个文档对象
		Document document = new Document();
		try{
			OutputStream outputStream = new FileOutputStream(outputFileName);
			PdfWriter.getInstance(document, outputStream);
			// 打开文档对象
			document.open();

			for (Element element : elementList){
				document.add(element);
			}

			// 关闭文档对象，释放资源
			document.close();
		}catch (FileNotFoundException e){
			log.error(e.getClass().getName(), e);
		}catch (DocumentException e){
			log.error(e.getClass().getName(), e);
		}
	}
}
