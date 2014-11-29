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

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

/**
 * The Class JansiTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月31日 下午5:15:54
 * @since 1.0.8
 */
public class JansiTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(JansiTest.class);

	/**
	 * The main method.
	 *
	 * @param args
	 *            the args
	 */
	public static void main(String[] args){
		AnsiConsole.systemInstall();

		Ansi ansi = Ansi.ansi();
		Ansi eraseScreen = ansi.eraseScreen();
		System.out.println(eraseScreen.fg(Color.RED).a("Hello").fg(Color.GREEN).a(" World").reset());
	}

	/**
	 * TestHelloColor.
	 */
	@Test
	public void testHelloColor(){

		System.out.println("Hello \u001b[1;31mred\u001b[0m world!");

		System.setProperty("jansi.passthrough", "true");

		log.info("info");
		log.error("error");
		log.warn("warn");
		AnsiConsole.systemInstall();

		Ansi ansi = Ansi.ansi();
		Ansi eraseScreen = ansi.eraseScreen();

		final Ansi fg = eraseScreen.fg(Color.RED);
		final Ansi a = fg.a("Hello");
		final Ansi fg2 = a.fg(Color.GREEN);
		final Ansi a2 = fg2.a(" World");
		final Ansi reset = a2.reset();
		System.out.println(reset);
	}
}