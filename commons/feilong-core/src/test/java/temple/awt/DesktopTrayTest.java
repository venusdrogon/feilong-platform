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
package temple.awt;

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

/**
 * The Class DesktopTrayTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 15:38:43
 */
public class DesktopTrayTest{

	/** The desktop. */
	private static Desktop		desktop;

	/** The system tray. */
	private static SystemTray	systemTray;

	/** The popup menu. */
	private static PopupMenu	popupMenu;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
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

	/**
	 * Send mail.
	 * 
	 * @param mail
	 *            the mail
	 */
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

	/**
	 * Open browser.
	 * 
	 * @param url
	 *            the url
	 */
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

	/**
	 * Edits the.
	 */
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

	/**
	 * Creates the popup menu.
	 */
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
