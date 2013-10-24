/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package elsepackage.temple.awt;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-20 下午07:55:14
 * @since 1.0
 */
public class 系统级热键{
	// 83.注册全局热键
	// //http://hi.baidu.com/ekou/blog/item/81cd52087a305bd463d986e4.html
	// //http://www.blogjava.net/Files/walsece/jintellitype.rar
	// //http://commons.apache.org/logging/
	// commons-loggins.jar
	//
	//
	// 所谓系统级热键就是指一组快捷键，不论当前系统焦点在哪个程序中，只要按下该键，程序就能够捕捉该事件并进行相关处理。该功能在应用程序中是非常有用的，比如系统自带的
	// “win+L”自动锁屏，QQ中默认的“ctrl+alt+Z”自动打开当前的消息窗口等等。
	// Java中的事件监听机制虽然功能强大，但是当系统焦点脱离该程序时也无能为力。要实现该功能必须调用系统的钩子函数，因此在java中也必须通过jni调用来实现，但是对于不熟悉系统函数或者其他编成语言的朋友来说却是个难题。
	// 以前实现类似的功能都是自己通过c调用系统的钩子函数然后再通过jni调用，自己写的东西只要能满足简单的需求即可，因此功能和程序结构也比较简单。后来在国外的一个网站上发现了一个组件“jintellitype”帮我们封装了绝大部分的功能，而且结构上也采用java中的事件监听机制，我们只要在程序中通过注册即可实现，下面是一个简单的例子:
	//
	// import com.melloware.jintellitype.HotkeyListener;
	// import com.melloware.jintellitype.JIntellitype;
	//
	// public class HotKey implements HotkeyListener {
	// static final int KEY_1 = 88;
	// static final int KEY_2 = 89;
	// static final int KEY_3 = 90;
	//
	// /**
	// * 该方法负责监听注册的系统热键事件
	// *
	// * @param key:触发的热键标识
	// */
	// public void onHotKey(int key) {
	// switch (key) {
	// case KEY_1:
	// System.out.println("ctrl+alt+I 按下.........");
	// break;
	// case KEY_2:
	// System.out.println("ctrl+alt+O 按下.........");
	// break;
	// case KEY_3:
	// System.out.println("系统退出..........");
	// destroy();
	// }
	//
	// }
	//
	// /**
	// * 解除注册并退出
	// */
	// void destroy() {
	// JIntellitype.getInstance().unregisterHotKey(KEY_1);
	// JIntellitype.getInstance().unregisterHotKey(KEY_2);
	// JIntellitype.getInstance().unregisterHotKey(KEY_3);
	// System.exit(0);
	// }
	//
	// /**
	// * 初始化热键并注册监听事件
	// */
	// void initHotkey() {
	// // 参数KEY_1表示改组热键组合的标识，第二个参数表示组合键，如果没有则为0，该热键对应ctrl+alt+I
	// JIntellitype.getInstance().registerHotKey(KEY_1,
	// JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, (int) 'I');
	// JIntellitype.getInstance().registerHotKey(KEY_2,
	// JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, (int) 'O');
	// JIntellitype.getInstance().registerHotKey(KEY_3,
	// JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, (int) 'X');
	//
	// JIntellitype.getInstance().addHotKeyListener(this);
	// }
	//
	// public static void main(String[] args) {
	// HotKey key = new HotKey();
	// key.initHotkey();
	//
	// // 下面模拟长时间执行的任务
	// while (true) {
	// try {
	// Thread.sleep(10000);
	// } catch (Exception ex) {
	// break;
	// }
	// }
	// }
	// }
	//
	// 偶尔，我们可以给用户添加一些快捷键，不管现在焦点在哪里。
	//
	// 有个做法就是，任何组建上注册你的监听器，但显然，这不是一个好做法
	//
	// java的toolkit可以直接添加一个监听器，
	//
	// 一下就是示例
	//
	// Toolkit toolkit = Toolkit.getDefaultToolkit();
	//
	// toolkit.addAWTEventListener(capListener, AWTEvent.KEY_EVENT_MASK |
	// AWTEvent.MOUSE_EVENT_MASK| AWTEvent.WINDOW_EVENT_MASK);
	//
	// 实现一个监听器：
	//
	// class CapListener implements AWTEventListener {
	//
	// public void eventDispatched(AWTEvent event) {
	//
	// }
	//
	// }
	// 这就可以了
	//
	//
	// /* 内部线程类 */
	// class Son extends Thread
	// {
	// private ICallBack event;
	// public Son(ICallBack callback)
	// {
	// event=callback;
	// }
	// public void run()
	// {
	// try
	// {
	// java.text.SimpleDateFormat fmt=new
	// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// while(true)
	// {
	// Thread.currentThread().sleep(3000);
	// event.output(fmt.format(new java.util.Date()));
	// Thread.currentThread().sleep(3000);
	// }
	// }
	// catch (Exception e)
	// {
	// }
	// }
	// }
	//
	// /* 回调接口 */
	// interface ICallBack
	// {
	// public void output();
	// }
	//
	//
	// /* 内部线程类 */
	// class Son extends Thread
	// {
	// private ICallBack event;
	// public Son(ICallBack callback)
	// {
	// event=callback;
	// }
	// public void run()
	// {
	// try
	// {
	// java.text.SimpleDateFormat fmt=new
	// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// while(true)
	// {
	// Thread.currentThread().sleep(3000);
	// event.output(fmt.format(new java.util.Date()));
	// Thread.currentThread().sleep(3000);
	// }
	// }
	// catch (Exception e)
	// {
	// }
	// }
	// }
	//
	// /* 回调接口 */
	// interface ICallBack
	// {
	// public void output();
	// }
	//
	//
	// /* 内部线程类 */
	// class Son extends Thread
	// {
	// private ICallBack event;
	// public Son(ICallBack callback)
	// {
	// event=callback;
	// }
	// public void run()
	// {
	// try
	// {
	// java.text.SimpleDateFormat fmt=new
	// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// while(true)
	// {
	// Thread.currentThread().sleep(3000);
	// event.output(fmt.format(new java.util.Date()));
	// Thread.currentThread().sleep(3000);
	// }
	// }
	// catch (Exception e)
	// {
	// }
	// }
	// }
	//
	// /* 回调接口 */
	// interface ICallBack
	// {
	// public void output();
	// }
}
