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
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class DrawPolygon extends Frame{

	Polygon	polygon	= new Polygon();

	DrawPolygon(){
		super("drawPolygon Example");
		addMouseListener(new MouseEventHandler());
		addMouseMotionListener(new MouseMotionEventHandler());
		setSize(200, 200);
		show();
	}

	public void paint(Graphics g){
		System.out.println("paint:");
		g.drawPolygon(polygon);
	} // The default update method clears the screen which causes  

	// flicker. This override avoids this. 
	public void update(Graphics g){
		System.out.println("update:");
		paint(g);
	}

	class MouseEventHandler extends MouseAdapter{

		public void mousePressed(MouseEvent evt){
			System.out.println("mousePressed:");
			polygon.addPoint(evt.getX(), evt.getY());
			repaint();
		}
	}

	class MouseMotionEventHandler extends MouseMotionAdapter{

		public void mouseDragged(MouseEvent evt){
			System.out.println("mouseDragged:");
			polygon.addPoint(evt.getX(), evt.getY());
			repaint();
		}
	}

	static public void main(String[] args){
		new DrawPolygon();
	}
}
