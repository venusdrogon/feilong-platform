/*
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
package com.feilong.tools.log4j.appender;

import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年11月9日 上午4:08:18
 * @since 1.0.8
 */
public class ShutdownAbleSMTPAppender extends SMTPAppender{

	private static final Logger	log	= LoggerFactory.getLogger(ShutdownAbleSMTPAppender.class);

	public ShutdownAbleSMTPAppender(){
		//但是另外一个问题也随之产生了，当程序结束时，还在缓冲里面的事件是不会被发送出来的。因为事件数往往没有bufferSize的一半。
		//笔者在构造函数中，添加了一个程序结束时运行的线程，来处理这个问题。
		Runtime.getRuntime().addShutdownHook(new Thread(){

			@Override
			public void run(){
				if (cb.length() > 0){
					sendBuffer();
				}
			}
		});
	}

	//MTPAppender 实现是，每当isTriggeringEvent（）这个方法返回true的时候，它都会发送邮件。这样的话，一个程序执行下来，每个有能力触发的事件都会形成一封邮件。这显然不是我们希望看到的场面。
	// 笔者继承了SMTPAppender类，重装了append方法。          
	public void append(LoggingEvent loggingEvent){

		if (!checkEntryConditions()){
			return;
		}

		loggingEvent.getThreadName();
		loggingEvent.getNDC();
		loggingEvent.getMDCCopy();
		if (this.getLocationInfo()){
			loggingEvent.getLocationInformation();
		}
		cb.add(loggingEvent);
		if (evaluator.isTriggeringEvent(loggingEvent)){
			if (cb.length() > this.getBufferSize() / 2){
				sendBuffer();
			}

		}
	}
}
