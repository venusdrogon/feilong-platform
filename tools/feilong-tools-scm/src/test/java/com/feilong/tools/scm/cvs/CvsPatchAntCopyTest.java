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
package com.feilong.tools.scm.cvs;

import org.junit.Test;

import com.feilong.tools.scm.ScmAntCopy;

/**
 * The Class PatchUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-23 下午7:50:42
 */
public class CvsPatchAntCopyTest{

    /**
     * 过滤不想传的文件 采用 endWith 来 匹配.
     */
    private String[]   excludeFileNames = { "log4j.xml", "messages/interface-config.properties" };

    /** The cvs patch util. */
    private ScmAntCopy scmAntCopy       = new CvsPatchAntCopy();

    /**
     * 剪切板patch测试.
     */
    @Test
    public void printlnClipboardContent(){
        scmAntCopy.printlnClipboardContent(excludeFileNames);
    }

    /**
     * 剪切板patch测试.
     */
    @Test
    public void printlnClipboardContent1(){
        scmAntCopy.printlnClipboardContent();
    }

    /**
     * 文件patch测试.
     */
    @Test
    public void printlnFileContent(){
        String fileName = "E:\\Workspaces\\feilong\\feilong-platform\\tools\\feilong-tools-scm\\src\\test\\java\\com\\feilong\\tools\\scm\\cvs\\cvs-add update delete patch.txt";
        scmAntCopy.printlnFileContent(fileName, excludeFileNames);

    }
}
