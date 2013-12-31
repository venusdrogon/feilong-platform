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
package com.feilong.commons.core.io;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.entity.JoinStringEntity;
import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.commons.core.util.StringUtil;

/**
 * 飞龙 运行命令相关操作
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-12-9 下午11:04:05
 */
public final class Command{

	private static final Logger	log	= LoggerFactory.getLogger(Command.class);

	public static String callCmd(String[] cmdarray){
		Process process = Command.exec(cmdarray);
		return IOUtil.inputStream2String(process.getInputStream());
	}

	public static String callCmd(String command){
		Process process = Command.exec(command);
		return IOUtil.inputStream2String(process.getInputStream());
	}

	public static String callCmd(String[] cmdarray,String[] anothercmdarray){
		Process process = null;
		try{
			Runtime runtime = Runtime.getRuntime();

			log.debug("[cmdarray]: " + ArrayUtil.toString(cmdarray, new JoinStringEntity(" ")));
			process = runtime.exec(cmdarray);

			// 已经执行完第一个命令，准备执行第二个命令
			// 导致当前线程等待，如有必要，一直要等到由该 Process 对象表示的进程已经终止。如果已终止该子进程，此方法立即返回。如果没有终止该子进程，调用的线程将被阻塞，直到退出子进程。
			process.waitFor();

			log.debug("[another]: " + ArrayUtil.toString(anothercmdarray, new JoinStringEntity(" ")));
			process = runtime.exec(anothercmdarray);
		}catch (IOException e){
			e.printStackTrace();
		}catch (InterruptedException e){
			e.printStackTrace();
		}

		return IOUtil.inputStream2String(process.getInputStream());

	}

	/**
	 * 在单独的进程中执行指定的字符串命令<br>
	 * 某些命令win7 需要管理员权限运行
	 * 
	 * @param command
	 *            一条指定的系统命令
	 * @return 一个新的 Process 对象，用于管理子进程
	 */
	public static Process exec(String command){
		Runtime runtime = Runtime.getRuntime();
		try{
			log.debug("[command]:{}", command);
			Process process = runtime.exec(command);
			return process;
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 在单独的进程中执行指定命令和变量<br>
	 * 某些命令win7 需要管理员权限运行
	 * 
	 * @param cmdarray
	 *            包含所调用命令及其参数的数组。
	 * @return 一个新的 Process 对象，用于管理子进程
	 */
	public static Process exec(String[] cmdarray){
		Runtime runtime = Runtime.getRuntime();
		try{
			log.debug("[cmdarray]: " + ArrayUtil.toString(cmdarray, new JoinStringEntity(" ")));
			Process process = runtime.exec(cmdarray);
			return process;
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用window资源管理器,打开文件或者文件夹
	 * 
	 * @param fileNameOrDirectory
	 *            文件或者文件夹,路径请使用\\的形式 不要用/,如E:\\Workspaces,不要用 E:/Workspaces
	 * @return Process
	 */
	public static Process execFileOrDirectoryOpen(String fileNameOrDirectory){
		return exec("explorer.exe " + fileNameOrDirectory);
	}

	/**
	 * 使用window资源管理器,让文件或者文件夹获得焦点,即选中
	 * 
	 * @param fileNameOrDirectory
	 *            文件或者文件夹,路径请使用\\的形式 不要用/,如E:\\Workspaces,不要用 E:/Workspaces
	 * @return Process
	 */
	public static Process execFileOrDirectoryFocus(String fileNameOrDirectory){
		return exec("explorer.exe /select," + fileNameOrDirectory);
	}

	/**
	 * 指定时间关闭计算机,win7 需要管理员权限运行
	 * 
	 * @param time
	 *            指定时间
	 * @return Process
	 */
	public static Process execShutdownAt(String time){
		String command = "at " + time + " shutdown -s";
		return exec(command);
	}

	/**
	 * 倒计时关机
	 * 
	 * @param time
	 *            毫秒
	 * @return Process
	 */
	public static Process execShutdown(int haomiao){
		String command = "shutdown -s -t " + haomiao;
		return exec(command);
	}

	/**
	 * 停止倒计时关机计算机,仅对execShutdown计划关机有效,对execShutdownAt无效
	 * 
	 * @return
	 */
	public static Process execShutdownStop(){
		String command = "shutdown -a";
		return exec(command);
	}

	/**
	 * 修改文件扩展名关联,win7 需要管理员权限运行<br>
	 * 
	 * @param ext
	 *            指定文件扩展名,比如 .txt (.不能缺少)
	 * @param fileType
	 *            指定要与指定的文件扩展名相关联的文件类型
	 * @return
	 */
	public static Process assoc(String ext,String fileType){
		// 如果在没有参数的情况下使用，则 assoc 命令将显示所有当前文件扩展名关联的列表。
		String command = StringUtil.format("assoc %s=%s", ext, fileType);
		return exec(command);
	}

}
