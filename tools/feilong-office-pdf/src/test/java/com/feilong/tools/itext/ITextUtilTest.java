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

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

/**
 * The Class ITextUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:27:44
 */
public class ITextUtilTest{

	/**
	 * Name.
	 * 
	 * @throws DocumentException
	 *             the document exception
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void name() throws DocumentException,MalformedURLException,IOException{
		// ①建立com.lowagie.text.Document对象的实例。
		Document document = new Document();
		// ②建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("E://Helloworld.PDF"));
		// HtmlWriter htmlWriter = HtmlWriter.getInstance(document, new FileOutputStream("E://Helloworld.html"));
		// 标题
		document.addTitle("title");
		// 主题
		document.addSubject("subject");
		// 关键字
		document.addKeywords("keywords");
		// 作者
		document.addAuthor("author");
		// 创建者
		document.addCreator("creator");
		// 生产者
		document.addProducer();
		// 创建日期
		document.addCreationDate();
		// 　　其中方法addHeader对于PDF文档无效，addHeader仅对html文档有效，用于添加文档的头信息。
		document.addHeader("aaaaa", "nbbbb");
		// ③打开文档。
		document.open();

		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font font = new Font(bfChinese, 12, Font.NORMAL);
		// ④向文档中添加内容。
		document.add(new Paragraph("曾经的岁月", font));
		// ④向文档中添加内容。
		document.add(new Paragraph("Hello World2"));
		/*********************************************************************************/
		Table table = new Table(3);
		table.setBorderWidth(0);
		// table.setBorderColor(new Color(0, 0, 255));
		table.setPadding(5);
		table.setSpacing(5);
		Cell cell = new Cell("header");
		cell.setHeader(true);
		cell.setColspan(3);
		table.addCell(cell);
		table.endHeaders();
		cell = new Cell("example cell with colspan 1 and rowspan 2");
		cell.setRowspan(2);
		cell.setBorderColor(new Color(255, 0, 0));
		table.addCell(cell);
		table.addCell("1.1");
		table.addCell("2.1");
		table.addCell("1.2");
		table.addCell("2.2");
		table.addCell("cell test1");
		cell = new Cell("big cell");
		cell.setRowspan(2);
		cell.setColspan(2);
		table.addCell(cell);
		table.addCell("cell test2");
		document.add(table);
		/*********************************************************************************/
		Image image = Image.getInstance("E:\\DataOther\\Material\\成套美图\\飞天\\1.jpg");
		image.setAlignment(Image.LEFT);
		image.scalePercent(50);
		image.setRotation((float) (Math.PI / 6));
		document.add(image);
		/*********************************************************************************/
		Phrase paramPhrase = new Phrase("header jinxin");
		// Phrase paramBoolean = new Phrase("header2");
		HeaderFooter header = new HeaderFooter(paramPhrase, false);
		document.setHeader(header);
		// document.resetHeader()
		Phrase phrase_footer = new Phrase("footer");
		HeaderFooter footer = new HeaderFooter(phrase_footer, false);
		document.setFooter(footer);
		/*********************************************************************************/
		// document.resetFooter()
		// ⑤关闭文档。
		document.close();
	}
}
