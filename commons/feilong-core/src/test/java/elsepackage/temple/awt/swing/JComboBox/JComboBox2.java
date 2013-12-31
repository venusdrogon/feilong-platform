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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class JComboBox2{

	String[]	s	= { "美国", "日本", "大陆", "英国", "法国", "意大利", "澳洲", "韩国" };

	public JComboBox2(){
		JFrame f = new JFrame("JComboBox2");
		Container contentPane = f.getContentPane();
		ComboBoxModel mode = new UserDefineComboBoxModel();
		JComboBox combo = new JComboBox(mode);
		combo.setBorder(BorderFactory.createTitledBorder("你最喜欢到哪个国家去玩?"));
		contentPane.add(combo);
		f.pack();
		f.show();
		f.addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

	public static void main(String[] args){
		new JComboBox2();
	}

	class UserDefineComboBoxModel extends AbstractListModel implements ComboBoxModel{

		String	item	= null;

		public Object getElementAt(int index){
			return s[index++];
		}

		// 由于继承AbstractListModel抽象类。因此我们分别在程序中实作了getElementAt()与getSize()方法。
		public int getSize(){
			return s.length;
		}

		// 由于我们实现了ComboBoxModel
		// interface.因此我们必须在程序中实作setSelectedItem()与getSelectedItem()方法.
		public void setSelectedItem(Object anItem){
			item = (String) anItem;
		}

		public Object getSelectedItem(){
			return item;
		}
	}
}
