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
	private static Desktop	desktop;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws AWTException
	 */
	public static void main(String[] args) throws AWTException{
		if (Desktop.isDesktopSupported()){
			desktop = Desktop.getDesktop();
		}
		if (SystemTray.isSupported()){
			PopupMenu popupMenu = createPopupMenu();

			String filename = "E:\\DataFixed\\Material\\图标\\ICO\\不错的\\16x16\\poolball.ico";
			Image image = Toolkit.getDefaultToolkit().getImage(filename);
			TrayIcon trayIcon = new TrayIcon(image, "托盘例子", popupMenu);
			//*******************************************

			SystemTray systemTray = SystemTray.getSystemTray();
			systemTray.add(trayIcon);
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
			File file = new File("E:\\test\\1.txt");
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
	 *
	 * @return the popup menu
	 */
	public static PopupMenu createPopupMenu(){

		MenuItem openLinkMenuItem = new MenuItem("打开链接");
		openLinkMenuItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				openBrowser("http://blog.csdn.net/xumingming64398966");
			}
		});
		
		MenuItem smMenuItem = new MenuItem("发送邮件");
		smMenuItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				sendMail("mailto:64398966@qq.com");
			}
		});
		
		MenuItem edMenuItem = new MenuItem("编辑");
		edMenuItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				edit();
			}
		});
		
		MenuItem exMenuItem = new MenuItem("退出");
		exMenuItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				System.exit(0);
			}
		});

		PopupMenu popupMenu = new PopupMenu();
		popupMenu.add(openLinkMenuItem);
		popupMenu.add(smMenuItem);
		popupMenu.add(edMenuItem);
		popupMenu.addSeparator();
		popupMenu.add(exMenuItem);

		return popupMenu;
	}
}
