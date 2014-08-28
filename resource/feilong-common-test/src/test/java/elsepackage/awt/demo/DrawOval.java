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
import java.awt.Insets;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
@SuppressWarnings("all")
class DrawOval extends Frame{

	@SuppressWarnings("rawtypes")
	Vector	points	= new Vector();

	DrawOval(){
		super("drawOval Example");
		setSize(200, 200);
		show();
	}

	void addPoint(Point p){
		points.addElement(p);
		repaint();
	}

	public void paint(Graphics g){
		Insets insets = getInsets();
		int x = insets.left, y = insets.top;
		for (int i = 0; i < points.size(); i++){
			Point p = (Point) points.elementAt(i);
			g.drawOval(x + p.x - 5, y + p.y - 5, 10, 10);
		}
	}

	static public void main(String[] args){
		DrawOval m = new DrawOval();
		BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
		while (true){
			try{
				m.addPoint(new Point(Integer.parseInt(dis.readLine()), Integer.parseInt(dis.readLine())));
			}catch (Exception e){
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
