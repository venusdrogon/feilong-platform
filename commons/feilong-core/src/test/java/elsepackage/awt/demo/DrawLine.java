package elsepackage.awt.demo;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

class DrawLine extends Frame{

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
