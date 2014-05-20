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
package temple.awt;

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
