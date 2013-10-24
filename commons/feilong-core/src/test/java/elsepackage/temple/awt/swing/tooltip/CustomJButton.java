package elsepackage.temple.awt.swing.tooltip;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolTip;

//继承自JToolTip
class CustomToolTip extends JToolTip{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5896756583376027995L;

	@Override
	public void paintComponent(Graphics g){
		//形状 自己看着办..自己觉的什么漂亮就用什么
		Shape round = new RoundRectangle2D.Float(4, 4, this.getWidth() - 1 - 8, this.getHeight() - 1 - 8, 15, 15);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.white);
		g2.fill(round);
		g2.setColor(Color.gray);
		g2.setStroke(new BasicStroke(5));
		g2.draw(round);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
		String text = this.getComponent().getToolTipText();
		if (text != null){
			FontMetrics fm = g2.getFontMetrics();
			int h = fm.getAscent();
			g2.setColor(Color.black);
			g2.drawString(text, 10, (this.getHeight() + h) / 2);
		}
	}

	@Override
	public Dimension getPreferredSize(){
		Dimension dim = super.getPreferredSize();
		return new Dimension((int) dim.getWidth() + 20, (int) dim.getHeight() + 20);
	}

	public CustomToolTip(){
		super();
		//不用介绍了,
		this.setOpaque(false);
	}
}

//用到时 就用这里 JButton jb = new CustomJButton ();
//可以自己更改 比如你不想要JButton 你想要JLabel 或者其他的 就 extends JLabel
// 然后 JLabel jl = new CustomJButton(); 当然 名字随便你换不换..(换了比较规范)
public class CustomJButton extends JButton{

	/**
  * 
  */
	private static final long	serialVersionUID	= 1L;

	JToolTip					_tooltip;

	public CustomJButton(String string, ImageIcon icon){
		//传入的JButton 字符串和JButton的Icon 如果不需要.可以直接删掉
		//或者自己在加一个无参数的方法..
		super(string, icon);
		_tooltip = new CustomToolTip();
		_tooltip.setComponent(this);
	}

	@Override
	public JToolTip createToolTip(){
		return _tooltip;
	}
}
