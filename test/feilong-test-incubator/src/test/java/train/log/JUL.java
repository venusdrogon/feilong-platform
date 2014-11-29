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
package train.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

/**
 * The Class JUL.
 */
public class JUL{

	/** The log. */
	private static Logger	log	= Logger.getLogger(JUL.class.getName());

	/**
	 * Test jul.
	 */
	@Test
	public void testJUL(){
		log.info("begin");
		try{
			throw new NullPointerException("");
		}catch (NullPointerException ex){
			// Log the error
			log.log(Level.WARNING, "trouble sneezing", ex);
		}
		log.info("end");
	}

	/** The logger. */
	private static Logger	logger	= Logger.getLogger("com.wombat.nose");

	/**
	 * The main method.
	 *
	 * @param argv
	 *            the argv
	 * @throws SecurityException
	 *             the security exception
	 * @throws IOException
	 *             the IO exception
	 */
	public static void main(String argv[]) throws SecurityException,IOException{
		FileHandler fh = new FileHandler("mylog.txt");
		// Send logger output to our FileHandler.
		logger.addHandler(fh);
		// Request that every detail gets logged.
		logger.setLevel(Level.ALL);
		// Log a simple INFO message.
		logger.info("doing stuff");
		try{
			throw new NullPointerException("");
		}catch (Error ex){
			logger.log(Level.WARNING, "trouble sneezing", ex);
		}
		logger.fine("done");
	}

}