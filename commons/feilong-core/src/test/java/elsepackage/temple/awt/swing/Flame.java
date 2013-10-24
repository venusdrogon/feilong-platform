package elsepackage.temple.awt.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Flame extends JLabel implements Runnable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -3852863790018228121L;

	boolean						first				= true;

	//
	int							ROWS				= 50;

	int							COLS				= 64;

	int							HIDDEN				= 4;

	int							ROWS_SEED			= 4;

	int							ROWS_RESEED			= 48;

	int							MAX_SEED			= 8;

	int							PALETTE_SIZE		= 64;

	int							COOLING_LIMIT		= 32;

	int							COOLING_ROWS		= 42;

	int							COOLING_FACTOR		= 2;

	java.awt.Color				palette[]			= new java.awt.Color[PALETTE_SIZE];

	byte						Buffer[], Buffer2[];

	String						NAME;

	String						message, textfont;

	int							textsize, textX, textY;

	Color						textcolor;

	Image						offScrImage			= null;

	Graphics					offScrGC;

	Dimension					offScrSize;

	Thread						kicker				= null;

	public Flame(String name){
		this.NAME = name;
		Dimension d = new Dimension(200, 72);
		setPreferredSize(d);
		setSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		init();
		start();
	}

	public void init(){
		int r, i;
		COLS = getSize().width;
		ROWS = getSize().height + HIDDEN;
		ROWS_RESEED = (int) (getSize().height * 0.99);
		Buffer = new byte[COLS * ROWS];
		Buffer2 = new byte[COLS * ROWS];
		for (i = 0; i < 16; ++i){
			palette[i] = new Color(255, 240, 0);
		}
		for (i = 0; i < 16; ++i){
			palette[16 + i] = new Color(255, 16 * i, 0);
		}
		for (r = COLS * (ROWS - ROWS_SEED); r < (ROWS * COLS); ++r){
			Buffer[r] = (byte) (PALETTE_SIZE - 20);
		}
	}

	public void MainLoop(){
		int r, a, i;
		for (r = COLS + 1; r < (COLS * (ROWS - 1)) - 1; ++r){
			a = Buffer[r - COLS - 1] + Buffer[r - COLS] + Buffer[r - COLS + 1] + Buffer[r - 1] + Buffer[r + 1] + Buffer[r + COLS - 1] + Buffer[r + COLS]
					+ Buffer[r + COLS + 1];
			a = (a >> 3);
			Buffer2[r] = (byte) (a);
		}
		for (r = COLS * (ROWS_RESEED); r < COLS * (ROWS); ++r){
			a = Buffer2[r];
			Buffer2[r] = (byte) ((a - (Math.random() * MAX_SEED)) % (PALETTE_SIZE * 1.1));
		}
		for (i = 0; i < COLS * (ROWS - 1); ++i){
			Buffer[i] = Buffer2[i + COLS];
		}
	}

	@Override
	public final synchronized void update(Graphics g){
		Dimension d = getSize();
		if ((offScrImage == null) || (d.width != offScrSize.width) || (d.height != offScrSize.height)){
			offScrImage = createImage(d.width, d.height);
			offScrSize = d;
			offScrGC = offScrImage.getGraphics();
		}
		if (offScrGC != null){
			offScrGC.fillRect(0, 0, d.width, d.height);
			paint(offScrGC);
			g.drawImage(offScrImage, 0, 0, null);
		}
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		if (null == Buffer){
			return;
		}
		int a;
		Color c;
		MainLoop();
		for (int y = 0; y < (ROWS - HIDDEN); ++y){
			for (int x = 0; x < COLS; ++x){
				a = Buffer[y * COLS + x];
				a = a < 0 ? -a : a; //   Patch   nasty   bug     
				a = a < (PALETTE_SIZE - 1) ? (a) : (PALETTE_SIZE - 1);
				c = palette[a];
				try{
					g.setColor(c);
					g.drawLine(x, y, x + 1, y);
				}catch (Exception e){}
			}
		}
		g.setFont(new Font("宋体", 0, 18));
		g.setColor(Color.black);
		g.drawLine(0, getHeight() / 2 + 6, this.getWidth(), getHeight() / 2 + 6);
		g.drawString(NAME, 10, getHeight() / 2 + 10);
		try{}catch (Exception e){}
	}

	public void start(){
		if (kicker == null){
			kicker = new Thread(this);
			kicker.start();
		}
	}

	public void stop(){
		kicker = null;
	}

	public void run(){
		while (kicker != null){
			if (null != this.getGraphics()){
				update(this.getGraphics());
			}
			try{
				Thread.sleep(30);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args){
		JFrame f = new JFrame("Flame");
		f.setLayout(new BorderLayout());
		Flame flame = new Flame("  JLabel很生气!!!!");
		flame.setFont(new Font("宋体", 1, 18));
		f.getContentPane().add(flame, BorderLayout.SOUTH);
		f.getContentPane().add(new JButton(), BorderLayout.CENTER);
		f.getContentPane().add(new JButton("我顶"), BorderLayout.NORTH);
		f.getContentPane().add(new JButton(), BorderLayout.WEST);
		f.getContentPane().add(new JButton(), BorderLayout.EAST);
		f.setSize(200, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}