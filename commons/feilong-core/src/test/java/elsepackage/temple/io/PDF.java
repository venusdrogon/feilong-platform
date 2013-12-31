/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package elsepackage.temple.io;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-20 下午07:55:46
 * @since 1.0
 */
public class PDF{
	// 90.提取PDF文件中的文本
	// //http://incubator.apache.org/pdfbox/
	// /*
	// import java.io.*;
	// import org.pdfbox.pdfparser.*;
	// import org.pdfbox.pdmodel.*;
	// import org.pdfbox.util.*;
	// */
	// public class SimplePDFReader {
	// /**
	// * simply reader all the text from a pdf file.
	// * You have to deal with the format of the output text by yourself.
	// * 2008-2-25
	// * @param pdfFilePath file path
	// * @return all text in the pdf file
	// */
	// public static String getTextFromPDF(String pdfFilePath) {
	// String result = null;
	// FileInputStream is = null;
	// PDDocument document = null;
	// try {
	// is = new FileInputStream(pdfFilePath);
	// PDFParser parser = new PDFParser(is);
	// parser.parse();
	// document = parser.getPDDocument();
	// PDFTextStripper stripper = new PDFTextStripper();
	// result = stripper.getText(document);
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// if (is != null) {
	// try {
	// is.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// if (document != null) {
	// try {
	// document.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// return result;
	// }
	// }
	// 得到PDF的文本内容之后，自己根据文件的格式，取得想要的文本（这里我找的就是文章的标题，在文本中恰巧都是文件的第一行的内容），然后通过java的File相关api，对文件进行更名操作。
	// import java.io.File;
	// import java.io.FilenameFilter;
	//
	// public class PaperNameMender {
	// public static void changePaperName(String filePath) {
	// //使用SimplePDFReader得到pdf文本
	// String ts = SimplePDFReader.getTextFromPDF(filePath);
	// //取得一行内容
	// String result = ts.substring(0, ts.indexOf('\n'));
	// //得到源文件名中的最后一个逗点.的位置
	// int index = filePath.indexOf('.');
	// int nextIndex = filePath.indexOf('.', index + 1);
	// while(nextIndex != -1) {
	// index = nextIndex;
	// nextIndex = filePath.indexOf('.', index + 1);
	// }
	// //合成新文件名
	// String newFilename = filePath.substring(0, index) + " " +
	// result.trim() + ".pdf";
	// File originalFile = new File(filePath);
	// //修改文件名
	// originalFile.renameTo(new File(newFilename));
	// }
	// }
	//
}
