/* 
 * Created on 2005-7-5 
 * 
 * TODO To change the template for this generated file go to 
 * Window - Preferences - Java - Code Style - Code Templates 
 */
package com.feilong.tools.itext.demo;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;

/**
 * @author Administrator TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class PDFParagragh extends Paragraph{

	public PDFParagragh(String content, int alignment, int fontSize){
		super(content, PDFChineseFont.createChineseFont(fontSize, Font.NORMAL));
		setAlignment(alignment);
	}

	public static final int	CENTER	= Element.ALIGN_CENTER;

	public static final int	LEFT	= Element.ALIGN_LEFT;

	public static final int	RIGHT	= Element.ALIGN_RIGHT;

	public static final int	TOP		= Element.ALIGN_TOP;

	public static final int	MIDDLE	= Element.ALIGN_MIDDLE;

	public static final int	BOTTOM	= Element.ALIGN_BOTTOM;

}