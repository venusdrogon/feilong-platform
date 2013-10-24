package elsepackage.temple.awt.swing.tooltip;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ToolTipsHack{

	public static void main(String[] args){
		JButton button;
		JFrame frame = new JFrame("Tool Tips Hack");
		BoxLayout layout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.getContentPane().setLayout(layout);
		button = new CustomJButton("啊啊", null);
		button.setText("Open");
		button.setToolTipText("相信牌ToolTip");
		frame.getContentPane().add(button);
		button = new CustomJButton("啊啊啊", null);
		button.setText("Save");
		button.setToolTipText("Save the currently open file");
		frame.getContentPane().add(button);
		frame.getContentPane().add(new JLabel("a label"));
		frame.getContentPane().add(new JLabel("a label"));
		frame.getContentPane().add(new JLabel("a label"));
		frame.pack();
		frame.setVisible(true);//或者直接 frame.show( ); 
	}
}