/* 
 * Created on 2005-7-1 
 * 
 * TODO To change the template for this generated file go to 
 * Window - Preferences - Java - Code Style - Code Templates 
 */
package com.feilong.tools.itext.demo;

import java.io.IOException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

/**
 * @author jcoder TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class PDFChineseFont{

	private static Font	chineseFont;

	public final static Font createChineseFont(int size,int style){
		try{
			chineseFont = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED), size, style);
		}catch (DocumentException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chineseFont;
	}
}