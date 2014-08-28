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
package temple.awt.swing.tooltip;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("all")public class ToolTipsHack{

	public static void main(String[] args){
		JButton button;
		JFrame frame = new JFrame("Tool Tips Hack");
		BoxLayout layout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.getContentPane().setLayout(layout);
		button = new CustomJButton("啊啊", null);
		button.setText("Open");
		button.setToolTipText("相信牌ToolTip");
		frame.getContentPane().add(button);
		button = new CustomJButton("啊啊啊", null);
		button.setText("Save");
		button.setToolTipText("Save the currently open file");
		frame.getContentPane().add(button);
		frame.getContentPane().add(new JLabel("a label"));
		frame.getContentPane().add(new JLabel("a label"));
		frame.getContentPane().add(new JLabel("a label"));
		frame.pack();
		frame.setVisible(true);//或者直接 frame.show( ); 
	}
}