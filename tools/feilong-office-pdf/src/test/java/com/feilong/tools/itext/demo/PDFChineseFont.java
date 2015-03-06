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

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

/**
 * The Class PDFChineseFont.
 * 
 * @author jcoder
 */
public class PDFChineseFont{

    private static final Logger log = LoggerFactory.getLogger(PDFChineseFont.class);

    /** The chinese font. */
    private static Font         chineseFont;

    /**
     * Creates the chinese font.
     * 
     * @param size
     *            the size
     * @param style
     *            the style
     * @return the font
     */
    public static final Font createChineseFont(int size,int style){
        try{
            chineseFont = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED), size, style);
        }catch (DocumentException e){
            log.error(e.getClass().getName(), e);
        }catch (IOException e){
            log.error(e.getClass().getName(), e);
        }
        return chineseFont;
    }
}