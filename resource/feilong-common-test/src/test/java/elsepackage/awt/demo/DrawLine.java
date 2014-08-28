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
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

@SuppressWarnings("all")
class DrawLine extends Frame{

	@SuppressWarnings("rawtypes")
	Vector	points			= new Vector();

	int		lastDrawnPoint	= 0;

	DrawLine(){
		super("drawLine Example");
		setSize(500, 500);
		addMouseListener(new MouseEventHandler());
		addMouseMotionListener(new MouseMotionEventHandler());
		show();
	}

	public void paint(Graphics g){
		Point curPt = null;
		for (int i = 0; i < points.size(); i++){
			Point pt = (Point) points.elementAt(i);
			if (curPt != null){
				g.drawLine(curPt.x, curPt.y, pt.x, pt.y);
			}
			curPt = pt;
		}
		lastDrawnPoint = points.size();
	}

	public void update(Graphics g){
		Point curPt = null;
		lastDrawnPoint = Math.max(0, lastDrawnPoint - 1);
		for (int i = lastDrawnPoint; i < points.size(); i++){
			Point pt = (Point) points.elementAt(i);
			if (curPt != null){
				g.drawLine(curPt.x, curPt.y, pt.x, pt.y);
			}
			curPt = pt;
		}
		lastDrawnPoint = points.size();
	}

	class MouseEventHandler extends MouseAdapter{

		public void mousePressed(MouseEvent evt){
			points.addElement(evt.getPoint());
			repaint();
		}
	}
	@SuppressWarnings("all")
	class MouseMotionEventHandler extends MouseMotionAdapter{

		public void mouseDragged(MouseEvent evt){
			points.addElement(evt.getPoint());
			repaint();
		}
	}

	static public void main(String[] args){
		new DrawLine();
	}
}
