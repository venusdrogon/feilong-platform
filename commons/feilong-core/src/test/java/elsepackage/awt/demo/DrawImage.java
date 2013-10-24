package elsepackage.awt.demo;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

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
