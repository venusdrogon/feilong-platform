/**
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package elsepackage.temple.awt.swing;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 不停震动的Dialog)
 * 
 * @author http://www.blogjava.net/chensiyu04/articles/201620.html
 * @version 1.0 2011-3-24 上午10:36:26
 */
public class DialogEarthquakeCenter extends Object implements Runnable{

	// 在这里我把常量说一下..下面计算时你们自己看..我就不一个一个说了
	//Shake Distance 是摇动距离的意思
	//Shake Cycle 是摇动周期的意思
	//Shake Duration  摇动期间
	//***** Upadate 这个看不懂的话 下面的你就别看了
	public static final int		SHAKE_DISTANCE	= 10;

	public static final double	SHAKE_CYCLE		= 50;

	public static final int		SHAKE_DURATION	= 1000;

	public static final int		SHAKE_UPDATE	= 2;

	private JDialog				dialog;

	private Point				naturalLocation;

	private long				startTime;

	private Timer				shakeTimer;

	private final double		TWO_PI			= Math.PI * 2.0;

	public DialogEarthquakeCenter(JDialog d){
		dialog = d;
	}

	public void startShake(){
		naturalLocation = dialog.getLocation();
		startTime = System.currentTimeMillis();
		shakeTimer = new Timer(SHAKE_UPDATE, new ActionListener(){

			public void actionPerformed(ActionEvent e){
				//将当前系统的系统时间 以毫秒的形式付给elapsed
				long elapsed = System.currentTimeMillis();
				double waveOffset = (elapsed % SHAKE_CYCLE) / SHAKE_CYCLE;
				double angle = waveOffset * TWO_PI;
				int shakenX = (int) ((Math.sin(angle) * SHAKE_DISTANCE) + naturalLocation.x);
				dialog.setLocation(shakenX, naturalLocation.y);
				dialog.repaint();
				if (elapsed >= SHAKE_DURATION){
					stopShake();
				}
			}
		});
		shakeTimer.start();
		//如果不开下面这个线程的话 窗口只震动一次
		//如果有这个线程 就是Thread.sleep(100)运行一次Timer
		//每运行Timer一次 窗口就会震动一次 我让线程while(true)就是死循环 让他不停的震动
		Thread t = new Thread(this);
		t.start();
	}

	public void stopShake(){
		shakeTimer.stop();
		dialog.setLocation(naturalLocation);
		dialog.repaint();
	}

	public static void main(String[] args){
		JOptionPane pane = new JOptionPane(
				"这几天.心情很郁闷..汶川的事.让我沉静了很久..这到底是谁的错?" + '\n' + "难道老天没有感情吗??.震吧..震吧." + '\n' + "连我的Swing程序也一起震了吧",
				JOptionPane.ERROR_MESSAGE,
				JOptionPane.OK_OPTION);
		JDialog d = pane.createDialog(null, "痛心的汶川");
		DialogEarthquakeCenter dec = new DialogEarthquakeCenter(d);
		d.pack();
		d.setModal(false);
		d.setVisible(true);
		dec.startShake();
		// wait (forever) for a non-null click and then quit
		while (pane.getValue() == JOptionPane.UNINITIALIZED_VALUE){
			try{
				Thread.sleep(100);
			}catch (InterruptedException ie){}
		}
		System.exit(0);
	}

	public void run(){
		while (true){
			try{
				Thread.sleep(100);
			}catch (Exception e){}
			shakeTimer.start();
		}
	}
}