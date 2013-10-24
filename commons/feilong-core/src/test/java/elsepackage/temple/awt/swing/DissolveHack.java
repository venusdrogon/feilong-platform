package elsepackage.temple.awt.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *旋转式关闭
 * 
 * @author http://www.blogjava.net/chensiyu04/articles/202420.html
 * @version 1.0 2011-3-24 上午10:41:38
 */
public class DissolveHack{

	public static void main(String[] args){
		final JFrame frame = new JFrame("Dissolve Hack");
		frame.setLayout(new BorderLayout());
		final JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){
				quit.setVisible(false);
			}
		});
		frame.getContentPane().add(quit, BorderLayout.NORTH);
		frame.getContentPane().add(new JButton("我转~"), BorderLayout.CENTER);
		frame.getContentPane().add(new JButton("我转~"), BorderLayout.SOUTH);
		frame.getContentPane().add(new JButton("我转~"), BorderLayout.EAST);
		frame.getContentPane().add(new JButton("我转~"), BorderLayout.WEST);
		frame.addWindowListener(new WindowListener(){

			public void windowActivated(WindowEvent arg0){}

			public void windowClosed(WindowEvent arg0){}

			public void windowClosing(WindowEvent arg0){
				new SpinDissolver().dissolveExit(frame);
			}

			public void windowDeactivated(WindowEvent arg0){}

			public void windowDeiconified(WindowEvent arg0){}

			public void windowIconified(WindowEvent arg0){}

			public void windowOpened(WindowEvent arg0){}
		});
		frame.pack();
		frame.setLocation(300, 300);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}
