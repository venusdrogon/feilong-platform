/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package elsepackage.temple.awt;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-23 下午02:32:29
 * @since 1.0
 */
public class 剪贴扳{

	// 81.剪贴扳转换成打印字符
	public static void main(String[] args){
		// 取得系统剪贴板里可传输的数据构造的Java对象
		Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		try{
			if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)){
				// 因为原系的剪贴板里有多种信息, 如文字, 图片, 文件等
				// 先判断开始取得的可传输的数据是不是文字, 如果是, 取得这些文字
				String s = (String) transferable.getTransferData(DataFlavor.stringFlavor);
				String[] arr = s.split("\n");
				StringBuilder sb = new StringBuilder(1024);
				for (String ss : arr){
					if (!ss.trim().equals("")){
						sb.append("w.WriteLine(\"ECHO "
								+ ss.replace("^", "^^").replace("&", "^&").replace(":", "^:").replace(">", "^>").replace("<", "^<").replace("|", "^|").replace(
										"\"",
										"^\"").replace("\\", "\\\\").replace("\"", "\\\"") + "\");");
						sb.append("\r\n");
					}
				}
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable tText = new StringSelection(sb.toString());
				clipboard.setContents(tText, null);
			}
		}catch (UnsupportedFlavorException ex){
			ex.printStackTrace();
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
