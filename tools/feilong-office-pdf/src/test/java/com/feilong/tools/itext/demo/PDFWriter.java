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

import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 

import com.lowagie.text.Cell; 
import com.lowagie.text.Document; 
import com.lowagie.text.DocumentException; 
import com.lowagie.text.Paragraph; 
import com.lowagie.text.Rectangle; 
import com.lowagie.text.Table; 
import com.lowagie.text.pdf.PdfWriter; 

/** 
 * @author jcoder 
 
 */ 
abstract public class PDFWriter { 
    
    /** The document. */
    protected Document document = null; 
    
    /** The out. */
    protected FileOutputStream out = null; 
    
    /** The page size. */
    protected Rectangle pageSize = null; 
    
    /** The file path. */
    protected String filePath = null; 
    
    /** The cell. */
    protected Cell cell = null; 
    
    /** The header. */
    protected Paragraph header = null; 
    
    /** The prg. */
    protected Paragraph prg = null; 
    
    /** The table. */
    protected Table table = null; 

    /**
	 * Instantiates a new PDF writer.
	 * 
	 * @param filePath
	 *            the file path
	 */
    public PDFWriter(String filePath) { 
        try { 
            this.filePath = filePath; 
            document = new Document(); 
            out = new FileOutputStream(filePath); 
            PdfWriter.getInstance(document, out); 
            document.open(); 
        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } catch (DocumentException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        } 

    } 

    /**
	 * Close.
	 */
    public void close() { 
        try { 
            document.close(); 
            out.close(); 
        } catch (IOException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        } 
    } 

} 
