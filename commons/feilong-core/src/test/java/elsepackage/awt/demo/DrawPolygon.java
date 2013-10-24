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
