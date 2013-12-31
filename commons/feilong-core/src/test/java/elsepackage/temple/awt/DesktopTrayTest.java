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
package elsepackage.temple.awt;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DesktopTrayTest{

	private static Desktop		desktop;

	private static SystemTray	systemTray;

	private static PopupMenu	popupMenu;

	public static void main(String[] args){
		if (Desktop.isDesktopSupported()){
			desktop = Desktop.getDesktop();
		}
		if (SystemTray.isSupported()){
			systemTray = SystemTray.getSystemTray();
			createPopupMenu();
			Image image = Toolkit.getDefaultToolkit().getImage("http://nikestore.com.cn/images/favicon.ico");
			TrayIcon trayIcon = new TrayIcon(image, "托盘例子", popupMenu);
			//*******************************************
			try{
				systemTray.add(trayIcon);
			}catch (AWTException awte){
				awte.printStackTrace();
			}
		}
	}

	public static void sendMail(String mail){
		if (desktop != null && desktop.isSupported(Desktop.Action.MAIL)){
			try{
				desktop.mail(new URI(mail));
			}catch (IOException e){
				e.printStackTrace();
			}catch (URISyntaxException e){
				e.printStackTrace();
			}
		}
	}

	public static void openBrowser(String url){
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
			try{
				desktop.browse(new URI(url));
			}catch (IOException e){
				e.printStackTrace();
			}catch (URISyntaxException e){
				e.printStackTrace();
			}
		}
	}

	public static void edit(){
		if (desktop != null && desktop.isSupported(Desktop.Action.EDIT)){
			File file = new File("test.txt");
			try{
				if (file.exists() == false){
					//	file.create();
				}
				desktop.edit(file);
			}catch (IOException ioe){
				ioe.printStackTrace();
			}
		}
	}

	public static void createPopupMenu(){
		popupMenu = new PopupMenu();
		MenuItem ob = new MenuItem("打开链接");
		ob.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				openBrowser("http://blog.csdn.net/xumingming64398966");
			}
		});
		MenuItem sm = new MenuItem("发送邮件");
		sm.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				sendMail("64398966@qq.com");
			}
		});
		MenuItem ed = new MenuItem("编辑");
		ed.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				edit();
			}
		});
		MenuItem ex = new MenuItem("退出");
		ex.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				System.exit(0);
			}
		});
		popupMenu.add(ob);
		popupMenu.add(sm);
		popupMenu.add(ed);
		popupMenu.addSeparator();
		popupMenu.add(ex);
	}
}
