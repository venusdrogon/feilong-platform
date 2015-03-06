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
package com.geek.tutorial.itext.bookmarks;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * iText 生成pdf内部链接.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:29:35
 */
public class Anchor{

    /**
     * TestAnchor.
     * 
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    @Test
    public void testAnchor() throws FileNotFoundException,DocumentException{

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E://anchor.pdf"));
        document.open();

        // Code 1
        Font font = new Font();
        font.setColor(Color.BLUE);
        font.setStyle(Font.UNDERLINE);

        document.add(new Chunk("Chapter 1"));

        document.add(new Paragraph(new Chunk("Press here to go chapter 2", font).setLocalGoto("2")));// Code 2

        document.newPage();

        document.add(new Chunk("Chapter 2").setLocalDestination("2"));
        document.add(new Paragraph(new Chunk("http://www.geek-tutorials.com", font).setAnchor("http://www.geek-tutorials.com")));// Code 3

        document.add(new Paragraph(new Chunk("Open outline.pdf chapter 3", font).setRemoteGoto("outline.pdf", "3")));// Code 4

        document.close();
    }
}