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
package com.feilong.tools.ant.plugin.jpa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.apache.tools.ant.DirectoryScanner;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.tools.ant.DirectoryScannerUtil;
import com.feilong.tools.json.JsonUtil;

/**
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月26日 下午2:06:28
 * @since 1.0.7
 */
public class ParseJPATest{

	private static final Logger	log	= LoggerFactory.getLogger(ParseJPATest.class);

	/**
	 * Test execute target1.
	 * 
	 * @throws IOException
	 */
	@Test
	public final void getMyTableList() throws IOException{
		//		FileResource fileResource=new FileResource();
		//		fileResource.setDirectory(directory);
		//		FileScanner fileScanner=new  DependScanner();

		List<String> list = new ArrayList<String>();

		list.add("mp2-dataCenter");
		list.add("mp2-member");
		list.add("mp2-platform");
		list.add("mp2-product");
		list.add("mp2-trade");

		for (String projectName : list){

			String basedir = "E:/Workspaces/baozun-else/mp2-new/mp2-modules/" + projectName;

			String[] includes = { "**/repo/*.java" };

			String[] excludes = { //
			"**/package-info.java",//
					"**/BaseModel.java" };

			DirectoryScanner directoryScanner = new DirectoryScanner();

			directoryScanner.setBasedir(basedir);
			directoryScanner.setIncludes(includes);
			directoryScanner.setExcludes(excludes);

			directoryScanner.scan();

			Map<String, Object> map = DirectoryScannerUtil.getDirectoryScannerMapForLog(directoryScanner);

			if (log.isDebugEnabled()){
				log.debug(JsonUtil.format(map));
			}

			ParseJPA parseJPA = new ParseJPA(directoryScanner);
			String format = JsonUtil.format(parseJPA);
			if (log.isDebugEnabled()){
				log.debug(format);
			}

			IOWriteUtil.write(SystemUtils.USER_HOME + "/feilong/dbscan/" + projectName + ".txt", format);
		}
	}
}