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
package com.feilong.log.logback;

import java.io.OutputStream;
import org.fusesource.jansi.AnsiConsole;
import ch.qos.logback.core.ConsoleAppender;

/**
 * The Class AnsiConsoleAppender.
 * 
 * <pre>
 * 
 * I'm afraid to say that it is rather a problem working around to get java.io.Console for the jvm for the wrapped jvm. 
 * Also because java.io.Console is final and only has an private default constructor we can't wrap it like System.in/out/err. 
 * So all in all, 
 * I don't think you will be able to use jansi with the wrapper on windows. 
 * linux/unix is fine though as long as you don't try to instrument java.io.Console with System.out ( AnsiConsole.systemInstall() )...
 * 
 * </pre>
 *
 * @param <E>
 *            the element type
 * 
 * @see <a href="http://jira.qos.ch/browse/LOGBACK-762">Failed to instantiate type org.fusesource.jansi.WindowsAnsiOutputStream</a>
 * @see <a href="http://sourceforge.net/p/wrapper/support-requests/290/">#290 jansi with wrapper</a>
 */
public class AnsiConsoleAppender<E> extends ConsoleAppender<E>{

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.qos.logback.core.OutputStreamAppender#setOutputStream(java.io.OutputStream)
	 */
	@Override
	public void setOutputStream(OutputStream outputStream){
		//Failed to create WindowsAnsiOutputStream
		OutputStream wrapOutputStream = AnsiConsole.wrapOutputStream(outputStream);
		super.setOutputStream(wrapOutputStream);
	}
}