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
package com.feilong.commons.core.io;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class RegQuery.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 15:40:38
 */
public class RegQuery{

	/** The Constant log. */
	private static final Logger	log					= LoggerFactory.getLogger(RegQuery.class);

	/** The Constant REGQUERY_UTIL. */
	private static final String	REGQUERY_UTIL		= "reg query ";

	/** The Constant REGSTR_TOKEN. */
	private static final String	REGSTR_TOKEN		= "REG_SZ";

	/** The Constant REGDWORD_TOKEN. */
	private static final String	REGDWORD_TOKEN		= "REG_DWORD";

	/** The Constant PERSONAL_FOLDER_CMD. */
	private static final String	PERSONAL_FOLDER_CMD	= REGQUERY_UTIL + "\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\"
															+ "Explorer\\Shell Folders\" /v Personal";

	/** The Constant CPU_SPEED_CMD. */
	private static final String	CPU_SPEED_CMD		= REGQUERY_UTIL + "\"HKLM\\HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\0\""
															+ " /v ~MHz";

	/** The Constant CPU_NAME_CMD. */
	private static final String	CPU_NAME_CMD		= REGQUERY_UTIL + "\"HKLM\\HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\0\""
															+ " /v ProcessorNameString";

	/**
	 * Gets the current user personal folder path.
	 * 
	 * @return the current user personal folder path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getCurrentUserPersonalFolderPath() throws IOException{
		String result = RegeditUtil.query(PERSONAL_FOLDER_CMD);
		int p = result.indexOf(REGSTR_TOKEN);
		if (p == -1){
			return null;
		}
		return result.substring(p + REGSTR_TOKEN.length()).trim();
	}

	/**
	 * Gets the CPU speed.
	 * 
	 * @return the CPU speed
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getCPUSpeed() throws IOException{
		String result = RegeditUtil.query(CPU_SPEED_CMD);
		int p = result.indexOf(REGDWORD_TOKEN);
		if (p == -1){
			return null;
		}
		// CPU speed in Mhz (minus 1) in HEX notation, convert it to DEC
		String temp = result.substring(p + REGDWORD_TOKEN.length()).trim();
		return Integer.toString((Integer.parseInt(temp.substring("0x".length()), 16) + 1));
	}

	/**
	 * Gets the CPU name.
	 * 
	 * @return the CPU name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getCPUName() throws IOException{
		String result = RegeditUtil.query(CPU_NAME_CMD);
		int p = result.indexOf(REGSTR_TOKEN);
		if (p == -1){
			return null;
		}
		return result.substring(p + REGSTR_TOKEN.length()).trim();
	}

	/**
	 * The main method.
	 * 
	 * @param s
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String s[]) throws IOException{
		log.info("Personal directory : " + getCurrentUserPersonalFolderPath());
		log.info("CPU Name : " + getCPUName());
		log.info("CPU Speed : " + getCPUSpeed() + " Mhz");
	}
}