package temple.awt.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JWindow;

@SuppressWarnings("all")public class Dissolver extends JComponent implements Runnable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7121426356345480873L;

	JFrame						frame;

	JWindow						fullscreen;

	int							count;

	BufferedImage				frame_buffer;

	BufferedImage				screen_buffer;

	public Dissolver(){}

	public void run(){
		try{
			count = 0;
			Thread.currentThread();
			Thread.sleep(100);
			for (int i = 0; i < 10; i++){
				count = i;
				fullscreen.repaint();
				Thread.currentThread();
				Thread.sleep(100);
			}
		}catch (InterruptedException ex){}
		System.exit(1);
	}

	public void dissolveExit(JFrame frame){
		try{
			this.frame = frame;
			Robot robot = new Robot();
			Rectangle frame_rect = frame.getBounds();
			frame_buffer = robot.createScreenCapture(frame_rect);
			frame.setVisible(false);
			Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screen_rect = new Rectangle(0, 0, screensize.width, screensize.height);
			screen_buffer = robot.createScreenCapture(screen_rect);
			fullscreen = new JWindow(new JFrame());
			fullscreen.setSize(screensize);
			fullscreen.add(this);
			this.setSize(screensize);
			fullscreen.setVisible(true);
			fullscreen.repaint();
			new Thread(this).start();
		}catch (Exception ex){
			System.out.println(ex);
			ex.printStackTrace();
		}
	}
}

class SpinDissolver extends Dissolver{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 186324961809484002L;

	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(screen_buffer, -fullscreen.getX(), fullscreen.getY(), null);
		AffineTransform old_trans = g2.getTransform();
		// 移动到框架的上面-左手边角落
		g2.translate(frame.getX(), frame.getY());
		// 向左边将框架移出
		g2.translate(0, +(count + 1) * (frame.getX() + frame.getWidth()) / 20);
		// 收缩 frame
		float scale = 1f / ((float) count + 1);
		g2.scale(scale, scale);
		// 中心的周围
		g2.rotate((count) / 3.14 / 1.3, frame.getWidth() / 2, frame.getHeight() / 2);
		g2.drawImage(frame_buffer, 0, 0, null);
		g2.setTransform(old_trans);
	}
}