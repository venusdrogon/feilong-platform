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
package com.feilong.commons.core.lang;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.JsonFormatUtil;

/**
 * 线程解析
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 17, 2011 2:00:35 AM
 */
public final class ThreadUtil{

	private static final Logger	log	= LoggerFactory.getLogger(ThreadUtil.class);

	/**
	 * 获得线程总数
	 * 
	 * @return
	 */
	public static int getTopThreadGroupActiveCount(){

		// 线程组表示一个线程的集合。此外，线程组也可以包含其他线程组。线程组构成一棵树，在树中，除了初始线程组外，每个线程组都有一个父线程组。
		// 允许线程访问有关自己的线程组的信息，但是不允许它访问有关其线程组的父线程组或其他任何线程组的信息。
		Thread currentThread = Thread.currentThread();

		Map<String, Object> threadObjectLog = getThreadObjectLog(currentThread);
		log.debug(JsonFormatUtil.format(threadObjectLog));

		ThreadGroup currentThreadGroup = currentThread.getThreadGroup();
		ThreadGroup topThreadGroup = getTopThreadGroup(currentThreadGroup);

		log.debug(JsonFormatUtil.format(getThreadGroupObjectLog(topThreadGroup)));
		// 返回此线程组中活动线程的估计数。
		int topThreadGroupActiveCount = topThreadGroup.activeCount();

		return topThreadGroupActiveCount;
	}

	/**
	 * 获得最顶层的 ThreadGroup
	 * 
	 * @param threadGroup
	 * @return
	 */
	private static ThreadGroup getTopThreadGroup(ThreadGroup threadGroup){

		log.debug(JsonFormatUtil.format(getThreadGroupObjectLog(threadGroup)));

		ThreadGroup parentThreadGroup = threadGroup.getParent();
		if (parentThreadGroup == null){
			return threadGroup;
		}else{
			// 递归写法
			return getTopThreadGroup(parentThreadGroup);
		}

		// 循环写法
		// for (; threadGroup.getParent() != null; threadGroup = threadGroup.getParent()){

		// }
		// return threadGroup;
	}

	/**
	 * 获得 ThreadGroup 对象log
	 * 
	 * @param threadGroup
	 * @return
	 */
	public static Map<String, Object> getThreadGroupObjectLog(ThreadGroup threadGroup){

		if (null != threadGroup){
			Map<String, Object> map = new HashMap<String, Object>();

			// 返回此线程组中活动线程的估计数。
			map.put("threadGroup.activeCount()", threadGroup.activeCount());

			// 返回此线程组中活动线程组的估计数。
			map.put("threadGroup.activeGroupCount()", threadGroup.activeGroupCount());

			// 返回此线程组的最高优先级。
			map.put("threadGroup.getMaxPriority()", threadGroup.getMaxPriority());
			map.put("threadGroup.getName()", threadGroup.getName());

			// 测试此线程组是否为一个后台程序线程组。
			map.put("threadGroup.isDaemon()", threadGroup.isDaemon());
			map.put("threadGroup.toString()", threadGroup.toString());

			return map;
		}
		return null;
	}

	/**
	 * 获得 thread 对象log
	 * 
	 * @param thread
	 *            线程 是程序中的执行线程。Java 虚拟机允许应用程序并发地运行多个执行线程。<br>
	 *            每个线程都有一个优先级，高优先级线程的执行优先于低优先级线程。<br>
	 *            每个线程都可以或不可以标记为一个守护程序。<br>
	 *            当某个线程中运行的代码创建一个新 Thread 对象时，该新线程的初始优先级被设定为创建线程的优先级，并且当且仅当创建线程是守护线程时，新线程才是守护程序。
	 * @return
	 */
	public static Map<String, Object> getThreadObjectLog(Thread thread){

		if (null != thread){
			Map<String, Object> map = new HashMap<String, Object>();

			// 返回当前线程的线程组中活动线程的数目
			map.put("Thread.activeCount()", Thread.activeCount());

			map.put("thread.getContextClassLoader()", thread.getContextClassLoader());
			map.put("thread.isAlive()", thread.isAlive());
			map.put("thread.isDaemon()", thread.isDaemon());
			map.put("thread.isInterrupted()", thread.isInterrupted());
			// map.put("thread.toString()", thread.toString());
			map.put("Thread.MIN_PRIORITY", Thread.MIN_PRIORITY);
			map.put("Thread.MAX_PRIORITY", Thread.MAX_PRIORITY);
			map.put("Thread.NORM_PRIORITY", Thread.NORM_PRIORITY);

			map.put("thread.getId()", thread.getId());
			map.put("thread.getName()", thread.getName());

			map.put("thread.getPriority()", thread.getPriority());
			StackTraceElement[] stackTraceElement = thread.getStackTrace();
			map.put("thread.getStackTrace()", stackTraceElement);
			map.put("thread.getState()", thread.getState());
			map.put("thread.getThreadGroup()", thread.getThreadGroup());
			map.put("thread.getUncaughtExceptionHandler()", thread.getUncaughtExceptionHandler());
			// map.put("Thread.getAllStackTraces()", Thread.getAllStackTraces());
			map.put("Thread.getDefaultUncaughtExceptionHandler()", Thread.getDefaultUncaughtExceptionHandler());

			return map;
		}
		return null;
	}

	/**
	 * 传入currentThread 解析其当前方法的名称
	 * 
	 * @param currentThread
	 *            Thread thread = Thread.currentThread();
	 * @return
	 */
	public static String getCurrentMethodName(Thread currentThread){
		return getMethodName(currentThread, 3);
	}

	/**
	 * 解析其调用者方法的名称
	 * 
	 * @param currentThread
	 *            Thread thread = Thread.currentThread();
	 * @return
	 */
	public static String getCallerMethodName(Thread currentThread){
		return getMethodName(currentThread, 1);
	}

	/**
	 * 传入currentThread 解析调用方法的名称
	 * 
	 * @param currentThread
	 *            currentThread
	 * @param index
	 *            index 索引
	 * @return
	 */
	private static String getMethodName(Thread currentThread,int index){
		StackTraceElement[] stackTraceElements = currentThread.getStackTrace();

		if (log.isDebugEnabled()){
			for (StackTraceElement stackTraceElement : stackTraceElements){
				log.debug("methodName:{}", stackTraceElement.getMethodName());
			}
		}
		StackTraceElement stackTraceElement = stackTraceElements[index];
		String methodName = stackTraceElement.getMethodName();
		return methodName;
	}
}
