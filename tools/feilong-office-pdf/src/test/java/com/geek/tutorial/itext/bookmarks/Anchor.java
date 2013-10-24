package com.geek.tutorial.itext.bookmarks;

import java.awt.Color;
import java.io.FileOutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * iText 生成pdf内部链接
 */
public class Anchor{

	public Anchor() throws Exception{

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

	public static void main(String[] args){

		try{
			Anchor anchor = new Anchor();
		}catch (Exception e){
			System.out.println(e);
		}
	}
}