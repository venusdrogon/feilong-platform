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
package com.feilong.tools.ant;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tools.ant.DirectoryScanner;

/**
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月26日 下午2:00:26
 * @since 1.0.7
 */
public class DirectoryScannerUtil{

	/**
	 * Gets the directory scanner map for log.
	 * 
	 * @param directoryScanner
	 *            the directory scanner
	 * @return the directory scanner map for log
	 */
	public static Map<String, Object> getDirectoryScannerMapForLog(DirectoryScanner directoryScanner){

		int includedFilesCount = directoryScanner.getIncludedFilesCount();
		String[] includedFiles = directoryScanner.getIncludedFiles();
		File basedir = directoryScanner.getBasedir();

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("directoryScanner.getBasedir()", basedir.getAbsolutePath());

		map.put("directoryScanner.getIncludedFilesCount()", includedFilesCount);
		map.put("directoryScanner.getIncludedFiles()", includedFiles);

		map.put("directoryScanner.getIncludedDirsCount()", directoryScanner.getIncludedDirsCount());
		map.put("directoryScanner.getIncludedDirectories()", directoryScanner.getIncludedDirectories());

		map.put("directoryScanner.getExcludedDirectories()", directoryScanner.getExcludedDirectories());
		map.put("directoryScanner.getExcludedFiles()", directoryScanner.getExcludedFiles());

		map.put("directoryScanner.getDeselectedDirectories()", directoryScanner.getDeselectedDirectories());
		map.put("directoryScanner.getDeselectedFiles()", directoryScanner.getDeselectedFiles());

		map.put("directoryScanner.getNotFollowedSymlinks()", directoryScanner.getNotFollowedSymlinks());
		//map.put("directoryScanner.getNotIncludedDirectories()", directoryScanner.getNotIncludedDirectories());
		//map.put("directoryScanner.getNotIncludedFiles()", directoryScanner.getNotIncludedFiles());

		map.put("DirectoryScanner.getDefaultExcludes()", DirectoryScanner.getDefaultExcludes());
		map.put("directoryScanner.isCaseSensitive()", directoryScanner.isCaseSensitive());
		map.put("directoryScanner.isEverythingIncluded()", directoryScanner.isEverythingIncluded());
		map.put("directoryScanner.isFollowSymlinks()", directoryScanner.isFollowSymlinks());
		return map;
	}
}
