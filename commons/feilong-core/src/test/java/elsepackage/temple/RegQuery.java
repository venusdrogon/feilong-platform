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
package elsepackage.temple;

import com.feilong.commons.core.io.RegeditUtil;

public class RegQuery{

	private static final String	REGQUERY_UTIL		= "reg query ";

	private static final String	REGSTR_TOKEN		= "REG_SZ";

	private static final String	REGDWORD_TOKEN		= "REG_DWORD";

	private static final String	PERSONAL_FOLDER_CMD	= REGQUERY_UTIL + "\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\"
															+ "Explorer\\Shell Folders\" /v Personal";

	private static final String	CPU_SPEED_CMD		= REGQUERY_UTIL + "\"HKLM\\HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\0\"" + " /v ~MHz";

	private static final String	CPU_NAME_CMD		= REGQUERY_UTIL + "\"HKLM\\HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\0\""
															+ " /v ProcessorNameString";

	public static String getCurrentUserPersonalFolderPath(){
		String result = RegeditUtil.query(PERSONAL_FOLDER_CMD);
		int p = result.indexOf(REGSTR_TOKEN);
		if (p == -1){
			return null;
		}
		return result.substring(p + REGSTR_TOKEN.length()).trim();
	}

	public static String getCPUSpeed(){
		String result = RegeditUtil.query(CPU_SPEED_CMD);
		int p = result.indexOf(REGDWORD_TOKEN);
		if (p == -1){
			return null;
		}
		// CPU speed in Mhz (minus 1) in HEX notation, convert it to DEC
		String temp = result.substring(p + REGDWORD_TOKEN.length()).trim();
		return Integer.toString((Integer.parseInt(temp.substring("0x".length()), 16) + 1));
	}

	public static String getCPUName(){
		String result = RegeditUtil.query(CPU_NAME_CMD);
		int p = result.indexOf(REGSTR_TOKEN);
		if (p == -1){
			return null;
		}
		return result.substring(p + REGSTR_TOKEN.length()).trim();
	}

	public static void main(String s[]){
		System.out.println("Personal directory : " + getCurrentUserPersonalFolderPath());
		System.out.println("CPU Name : " + getCPUName());
		System.out.println("CPU Speed : " + getCPUSpeed() + " Mhz");
	}
}