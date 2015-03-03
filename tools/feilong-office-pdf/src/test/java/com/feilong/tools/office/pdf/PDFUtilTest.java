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
package com.feilong.tools.office.pdf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.office.pdf.PDFUtil;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Section;

/**
 * The Class PDFUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 10, 2013 4:41:07 PM
 */
public class PDFUtilTest{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(PDFUtilTest.class);

	/**
	 * Test.
	 */
	@Test
	public final void test(){
		String outputFileName = "e://html.pdf";
		String htmlPath = "e://1.htm";
		PDFUtil.htmlToPDF(htmlPath, outputFileName);
	}

	/**
	 * Write.
	 */
	@Test
	public void write(){
		// 定义输出位置并把文档对象装入输出对象中
		String outputFileName = "E:/hello.pdf";

		List<Element> arrayList = new ArrayList<Element>();
		arrayList.add(new Chapter("HelloWorld Chapter1", 1));
		Phrase e = new Phrase("HelloWorld Phrase");
		e.add("aaa");
		arrayList.add(e);
		arrayList.add(new Paragraph("HelloWorld Paragraph"));
		arrayList.add(new Chunk("HelloWorld Chunk"));

		Chapter chapter = new Chapter("HelloWorld Chapter2", 2);
		chapter.setBookmarkOpen(true);
		chapter.setBookmarkTitle("aaaa");
		chapter.setTitle(new Paragraph("HelloWorld Paragraph"));

		arrayList.add(chapter);
		PDFUtil.write(outputFileName, arrayList);
	}

	/**
	 * Write1.
	 */
	@Test
	public void write1(){
		Paragraph title2 = new Paragraph("This is Chapter 2", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new Color(
						0,
						0,
						255)));

		Chapter chapter2 = new Chapter(title2, 2);
		chapter2.setNumberDepth(0);

		Paragraph someText = new Paragraph("This is some text");
		chapter2.add(someText);

		Paragraph title21 = new Paragraph("This is Section 1 in Chapter 2", FontFactory.getFont(
						FontFactory.HELVETICA,
						16,
						Font.BOLD,
						new Color(255, 0, 0)));
		Section section1 = chapter2.addSection(title21);
		Paragraph someSectionText = new Paragraph(
						"This is some silly paragraph in a chapter and/or section. It contains some text to test the functionality of Chapters and Section.");
		section1.add(someSectionText);

		// 定义输出位置并把文档对象装入输出对象中
		String outputFileName = "E:/hello1.pdf";

		List<Element> arrayList = new ArrayList<Element>();

		arrayList.add(chapter2);
		PDFUtil.write(outputFileName, arrayList);
	}
}
