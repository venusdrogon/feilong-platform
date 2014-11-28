

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
	// log.error(e.getClass().getName(), e);
	// } catch (IOException e) {
	// log.error(e.getClass().getName(), e);
	// } finally {
	// if (is != null) {
	// try {
	// is.close();
	// } catch (IOException e) {
	// log.error(e.getClass().getName(), e);
	// }
	// }
	// if (document != null) {
	// try {
	// document.close();
	// } catch (IOException e) {
	// log.error(e.getClass().getName(), e);
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
