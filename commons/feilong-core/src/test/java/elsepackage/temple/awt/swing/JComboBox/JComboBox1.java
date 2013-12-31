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
package elsepackage.temple.awt.swing.JComboBox;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class JComboBox1{

	public static void main(String[] args){
		JFrame jFrame = new JFrame("JComboBox1");
		Container contentPane = jFrame.getContentPane();
		contentPane.setLayout(new GridLayout(1, 2));
		String[] s = { "美国", "日本", "大陆", "英国", "法国", "意大利", "澳洲", "韩国" };
		JComboBox jComboBox = new JComboBox(s);
		// 利用JComboBox类所提供的addItem()方法，加入一个项目到此JComboBox中。
		jComboBox.addItem("中国");
		jComboBox.setBorder(BorderFactory.createTitledBorder("你最喜欢到哪个国家玩呢?"));
		//***************************************************
		Vector vector = new Vector();
		vector.addElement("Nokia 8850");
		vector.addElement("Nokia 8250");
		vector.addElement("Motorola v8088");
		vector.addElement("Motorola v3850");
		vector.addElement("Panasonic 8850");
		vector.addElement("其它");
		JComboBox jComboBox1 = new JComboBox(vector);
		jComboBox1.setBorder(BorderFactory.createTitledBorder("你最喜欢哪一种手机呢？"));
		//***************************************************
		contentPane.add(jComboBox);
		contentPane.add(jComboBox1);
		jFrame.pack();
		jFrame.show();
		jFrame.addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}
