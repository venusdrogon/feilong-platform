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

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 颠倒的字体
 * 
 * @author http://www.blogjava.net/chensiyu04/articles/201618.html
 * @version 1.0 2011-3-24 上午10:35:35
 */
public class BackwardsJLabel extends JLabel{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public BackwardsJLabel(){
		super();
	}

	public BackwardsJLabel(Icon image){
		super(image);
	}

	public BackwardsJLabel(Icon image, int align){
		super(image, align);
	}

	public BackwardsJLabel(String text){
		super(text);
	}

	public BackwardsJLabel(String text, Icon icon, int align){
		super(text, icon, align);
	}

	public BackwardsJLabel(String text, int align){
		super(text, align);
	}

	@Override
	public void paint(Graphics g){
		if (g instanceof Graphics2D){
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform flipTrans = new AffineTransform();
			double widthD = getWidth();
			flipTrans.setToTranslation(widthD, 0);
			flipTrans.scale(-1.0, 1);
			g2.transform(flipTrans);
			super.paint(g);
		}else{
			super.paint(g);
		}
	}

	public static void main(String[] args){
		BackwardsJLabel field = new BackwardsJLabel("谁能到懂我说的话?能看懂的赶紧留帖!!");
		field.setFont(new Font("宋体", 0, 12));
		JFrame frame = new JFrame("颠倒的JLabel");
		frame.getContentPane().add(field);
		frame.pack();
		frame.setVisible(true);
	}
}