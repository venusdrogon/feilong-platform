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

import com.feilong.commons.core.io.UncheckedIOException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 处理PDF文档 (PDF).
 *
 * @author 金鑫 2010-2-27 下午05:14:03
 */
public final class PDFUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(PDFUtil.class);

    /**
     * html文件转换成pdf.
     *
     * @author 金鑫
     * @version 1.0 2010-2-26 下午06:01:45
     * @param htmlPath
     *            需要被转换的html路径及名称
     * @param outputFileName
     *            输出pdf文件路径级名称
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
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 输出pdf 文件.
     *
     * @param outputFileName
     *            地址
     * @param elementList
     *            the element list
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
