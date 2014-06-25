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
package com.feilong.tools.itext.demo;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;

/**
 * The Class PDFParagragh.
 * 
 * @author Administrator
 */
public class PDFParagragh extends Paragraph{

	/**
	 * Instantiates a new PDF paragragh.
	 * 
	 * @param content
	 *            the content
	 * @param alignment
	 *            the alignment
	 * @param fontSize
	 *            the font size
	 */
	public PDFParagragh(String content, int alignment, int fontSize){
		super(content, PDFChineseFont.createChineseFont(fontSize, Font.NORMAL));
		setAlignment(alignment);
	}

	/** The Constant CENTER. */
	public static final int	CENTER	= Element.ALIGN_CENTER;

	/** The Constant LEFT. */
	public static final int	LEFT	= Element.ALIGN_LEFT;

	/** The Constant RIGHT. */
	public static final int	RIGHT	= Element.ALIGN_RIGHT;

	/** The Constant TOP. */
	public static final int	TOP		= Element.ALIGN_TOP;

	/** The Constant MIDDLE. */
	public static final int	MIDDLE	= Element.ALIGN_MIDDLE;

	/** The Constant BOTTOM. */
	public static final int	BOTTOM	= Element.ALIGN_BOTTOM;

}