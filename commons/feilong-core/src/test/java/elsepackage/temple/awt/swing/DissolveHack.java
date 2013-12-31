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
package elsepackage.temple.awt.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *旋转式关闭
 * 
 * @author http://www.blogjava.net/chensiyu04/articles/202420.html
 * @version 1.0 2011-3-24 上午10:41:38
 */
public class DissolveHack{

	public static void main(String[] args){
		final JFrame frame = new JFrame("Dissolve Hack");
		frame.setLayout(new BorderLayout());
		final JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){
				quit.setVisible(false);
			}
		});
		frame.getContentPane().add(quit, BorderLayout.NORTH);
		frame.getContentPane().add(new JButton("我转~"), BorderLayout.CENTER);
		frame.getContentPane().add(new JButton("我转~"), BorderLayout.SOUTH);
		frame.getContentPane().add(new JButton("我转~"), BorderLayout.EAST);
		frame.getContentPane().add(new JButton("我转~"), BorderLayout.WEST);
		frame.addWindowListener(new WindowListener(){

			public void windowActivated(WindowEvent arg0){}

			public void windowClosed(WindowEvent arg0){}

			public void windowClosing(WindowEvent arg0){
				new SpinDissolver().dissolveExit(frame);
			}

			public void windowDeactivated(WindowEvent arg0){}

			public void windowDeiconified(WindowEvent arg0){}

			public void windowIconified(WindowEvent arg0){}

			public void windowOpened(WindowEvent arg0){}
		});
		frame.pack();
		frame.setLocation(300, 300);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}
