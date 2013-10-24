package elsepackage.awt.demo;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

class DrawOval extends Frame{

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
