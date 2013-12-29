/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.tools.office.word;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/* 
 * 
 * <p>我的word转pdf用到了一个虚拟的打印机，安装一个Adobe Acrobat 7.0 Professional就可以了</p><p>
 * 2。配置虚拟打印机，开始--打印机和传真--添加打印机---一步一步的配置就行了。</p><p>
 * 3。点击配置的打印机右键首选项----设置----把不要发送字体到打印机取消勾选</p><p>
 * 4。下载Jacob.jar包。把jacob.jar所对应的Jacob.dll放在windows/sys32下或者jre/bin下</p><p>
 * 5。代码部分</p><p>代码</p>  
 * 注意word转pdf要安装虚拟打印机，且要配置 
 * 使用jacob框架，把dll文件放到jre/bin目录下 
 */
public class WordToPdf{

	private ActiveXComponent	wordCom	= null;

	private Object				wordDoc	= null;

	private final Variant		False	= new Variant(false);

	private final Variant		True	= new Variant(true);

	/**
	 * 打开word文档
	 * 
	 * @param filePath
	 *            word文档
	 * @return 返回word文档对象
	 */
	public boolean openWord(String filePath){
		//建立ActiveX部件  
		wordCom = new ActiveXComponent("Word.Application");
		try{
			//返回wrdCom.Documents的Dispatch  
			Dispatch wrdDocs = wordCom.getProperty("Documents").toDispatch();
			//调用wrdCom.Documents.Open方法打开指定的word文档，返回wordDoc  
			wordDoc = Dispatch.invoke(wrdDocs, "Open", Dispatch.Method, new Object[] { filePath }, new int[1]).toDispatch();
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 关闭word文档
	 */
	public void closeWord(boolean saveOnExit){
		if (wordCom != null){
			//关闭word文件  
			//Dispatch.call(wordDoc, "Close", new Variant(saveOnExit));  
			wordCom.invoke("Quit", new Variant[] {});
			//wordCom.invoke("Quit",new Variant[0]);  
			wordCom = null;
			//释放在程序线程中引用的其它com，比如Adobe PDFDistiller  
			ComThread.Release();
		}
	}

	/**
	 * 将word文档打印为PS文件后，使用Distiller将PS文件转换为PDF文件
	 * 
	 * @param sourceFilePath
	 *            源文件路径
	 * @param destinPSFilePath
	 *            首先生成的PS文件路径
	 * @param destinPDFFilePath
	 *            生成PDF文件路径
	 */
	public void docToPDF(String sourceFilePath,String destinPSFilePath,String destinPDFFilePath){
		if (!openWord(sourceFilePath)){
			closeWord(true);
			return;
		}
		//建立Adobe Distiller的com对象  
		ActiveXComponent distiller = new ActiveXComponent("PDFDistiller.PDFDistiller.1");
		try{
			//设置当前使用的打印机，我的Adobe Distiller打印机名字为 "Adobe PDF"  
			wordCom.setProperty("ActivePrinter", new Variant("Adobe PDF"));
			//设置printout的参数，将word文档打印为postscript文档。现在只使用了前5个参数，假如要使用更多的话可以参考MSDN的office开发相关api  
			//是否在后台运行  
			Variant Background = False;
			//是否追加打印  
			Variant Append = False;
			//打印所有文档  
			int wdPrintAllDocument = 0;
			Variant Range = new Variant(wdPrintAllDocument);
			//输出的postscript文件的路径  
			Variant OutputFileName = new Variant(destinPSFilePath);
			Dispatch.callN((Dispatch) wordDoc, "PrintOut", new Variant[] { Background, Append, Range, OutputFileName });
			System.out.println("由word文档打印为ps文档成功！");
			//调用Distiller对象的FileToPDF方法所用的参数，具体内容参考Distiller Api手册  
			//作为输入的ps文档路径  
			Variant inputPostScriptFilePath = new Variant(destinPSFilePath);
			//作为输出的pdf文档的路径  
			Variant outputPDFFilePath = new Variant(destinPDFFilePath);
			//定义FileToPDF方法要使用adobe pdf设置文件的路径，在这里没有赋值表示并不使用pdf配置文件  
			Variant PDFOption = new Variant("");
			//调用FileToPDF方法将ps文档转换为pdf文档  
			Dispatch.callN(distiller, "FileToPDF", new Variant[] { inputPostScriptFilePath, outputPDFFilePath, PDFOption });
			System.out.println("由ps文档转换为pdf文档成功！");
		}catch (Exception ex){
			ex.printStackTrace();
		}finally{
			closeWord(true);
		}
	}

	public static void main(String[] argv){
		WordToPdf d2p = new WordToPdf();
		d2p.docToPDF("D:\\test.doc", "D:\\test.ps", "D:\\test.pdf");
		boolean success = (new File("D:\\test.ps")).delete();
		if (success){
			System.out.println("删除打印机文件成功");
		}
	}
}
