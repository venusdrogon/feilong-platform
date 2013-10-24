package elsepackage.awt.demo;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class DrawString extends Frame implements ItemListener{

	MainCanvas	cv		= new MainCanvas();

	Choice		choice	= new Choice();

	DrawString(){
		super("drawString Example");
		for (int i = 4; i < 60; i += 4){
			choice.addItem("" + i);
		}
		choice.select(0);
		choice.addItemListener(this);
		cv.setFontSize(4);
		cv.setSize(300, 100);
		add(cv, BorderLayout.CENTER);
		add(choice, BorderLayout.SOUTH);
		pack();
		show();
	}

	public void itemStateChanged(ItemEvent evt){
		String what = (String) (evt.getItem());
		cv.setFontSize(Integer.parseInt(what));
	}

	static public void main(String[] args){
		new DrawString();
	}
}

class MainCanvas extends Canvas{

	void setFontSize(int size){
		Font f = getFont();
		if (f == null){
			f = new Font("Serif", Font.PLAIN, size);
		}else{
			f = new Font(getFont().getName(), getFont().getStyle(), size);
		}
		setFont(f);
		repaint();
	}

	public void paint(Graphics g){
		String s = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
		FontMetrics fontM = g.getFontMetrics();
		g.setColor(Color.white);
		g.fillRect(0, 0, fontM.stringWidth(s), fontM.getHeight());
		g.setColor(Color.black);
		g.drawString(s, 0, fontM.getAscent());
	}
}
