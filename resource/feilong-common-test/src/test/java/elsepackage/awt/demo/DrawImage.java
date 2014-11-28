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
package elsepackage.awt.demo;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

@SuppressWarnings("all")
class DrawImage extends Frame{

	Image	image;

	DrawImage(String filename){
		super("drawImage Example");
		try{
			image = getToolkit().getImage(filename);
			setIconImage(image);
		}catch (Exception e){
			e.printStackTrace();
		}
		setSize(400, 200);
		show();
	}

	public void paint(Graphics g){
		Insets insets = getInsets();
		int x = insets.left, y = insets.top;
		int w = image.getWidth(this);
		int h = image.getHeight(this); // original 
		g.drawImage(image, x, y, this); // shrinken
		g.drawRect(x, y, w / 4 + 1, h / 4 + 1);
		g.drawImage(image, x + 1, y + 1, w / 4, h / 4, this); // horizontally flipped
		g.drawImage(image, x + w, y, x + 2 * w, y + h, w, 0, 0, h, this); // vertically flipped 
		g.drawImage(image, x + 2 * w, y, x + 3 * w, y + h, 0, h, w, 0, this); // enlarged; use -1 to indicate proportional height 
		g.drawImage(image, x + 3 * w, y, 2 * w, -1, this);
	}

	static public void main(String[] args){
		new DrawImage("E:\\Data\\Material\\背景\\清明上河图\\幻灯片1.PNG");
		//		if (args.length == 1){
		//			new Main(args[0]);
		//		}else{
		//			System.err.println("usage: java Main <image file>");
		//		}
	}
}
