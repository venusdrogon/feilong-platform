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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.DirectoryScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class DirectoryScannerUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月26日 下午2:00:26
 * @since 1.0.7
 */
public class DirectoryScannerUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(DirectoryScannerUtil.class);

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

    /**
     * 获得 included file list.
     *
     * @param basedir
     *            the basedir
     * @param includes
     *            the includes
     * @return the included file list
     * @since 1.0.9
     */
    public static List<File> getIncludedFileList(String basedir,String[] includes){
        DirectoryScanner directoryScanner = getDirectoryScanner(basedir, includes);

        List<File> includedFileList = DirectoryScannerUtil.getIncludedFileList(directoryScanner);
        return includedFileList;
    }

    /**
     * 获得 included files.
     *
     * @param basedir
     *            the basedir
     * @param includes
     *            the includes
     * @return the included files
     * @since 1.0.9
     */
    public static String[] getIncludedFiles(String basedir,String[] includes){
        DirectoryScanner directoryScanner = getDirectoryScanner(basedir, includes);
        String[] includedFiles = directoryScanner.getIncludedFiles();
        return includedFiles;
    }

    /**
     * 获得 directory scanner.
     *
     * @param basedir
     *            the basedir
     * @param includes
     *            the includes
     * @return the directory scanner
     * @since 1.0.9
     */
    private static DirectoryScanner getDirectoryScanner(String basedir,String[] includes){
        DirectoryScanner directoryScanner = new DirectoryScanner();

        directoryScanner.setBasedir(basedir);
        directoryScanner.setIncludes(includes);
        //directoryScanner.setExcludes(excludes);

        directoryScanner.scan();

        Map<String, Object> map = DirectoryScannerUtil.getDirectoryScannerMapForLog(directoryScanner);

        if (log.isDebugEnabled()){
            log.debug(JsonUtil.format(map));
        }

        return directoryScanner;
    }

    /**
     * 获得 included file list.
     *
     * @param directoryScanner
     *            the directory scanner
     * @return the included file list
     */
    private static List<File> getIncludedFileList(DirectoryScanner directoryScanner){
        int includedFilesCount = directoryScanner.getIncludedFilesCount();
        String[] includedFiles = directoryScanner.getIncludedFiles();

        if (log.isDebugEnabled()){
            log.debug("includedFilesCount:{},includedFiles:{}", includedFilesCount, JsonUtil.format(includedFiles));
        }
        //*******************************************************************************
        File basedir = directoryScanner.getBasedir();
        List<File> includedFileList = new ArrayList<File>();

        for (int i = 0; i < includedFiles.length; i++){
            String child = includedFiles[i];

            File file = new File(basedir, child);
            includedFileList.add(file);
        }
        if (log.isDebugEnabled()){
            log.debug("will return includedFileList:{}", JsonUtil.format(includedFileList));
        }
        return includedFileList;
    }
}
