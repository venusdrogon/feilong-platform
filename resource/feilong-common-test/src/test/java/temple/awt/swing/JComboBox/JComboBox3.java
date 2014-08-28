package temple.awt.swing.JComboBox;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

@SuppressWarnings("all")public class JComboBox3{

	String[]	s	= { "美国", "日本", "大陆", "英国", "法国", "意大利", "澳洲", "韩国" };

	public JComboBox3(){
		JFrame f = new JFrame("JComboBox3");
		Container contentPane = f.getContentPane();
		ComboBoxModel mode = new AModel();
		JComboBox combo = new JComboBox(mode);
		combo.setBorder(BorderFactory.createTitledBorder("您最喜欢到哪个国家玩呢？"));
		contentPane.add(combo);
		f.pack();
		f.show();
		f.addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

	public static void main(String[] args){
		new JComboBox3();
	}

	class AModel extends DefaultComboBoxModel{

		AModel(){
			for (int i = 0; i < s.length; i++)
				addElement(s[i]);
		}
	}
}
