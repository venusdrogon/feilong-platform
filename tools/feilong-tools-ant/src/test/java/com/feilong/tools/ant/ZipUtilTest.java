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

import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DateExtensionUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

/**
 * The Class ZipUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-27 下午04:14:11
 */
@SuppressWarnings("all")
public class ZipUtilTest{

    /** The Constant log. */
    private static final Logger log            = LoggerFactory.getLogger(ZipUtilTest.class);

    /** 需要被解压的zip文件. */
    String                      unZipFileName  = "E:\\test.zip";

    /** 解压到哪个目录. */
    String                      outputFileName = "E:\\test" + DateUtil.date2String(new Date(), DatePattern.TIMESTAMP); // 解压到文件路径

    //@Test
    /**
     * Test zip.
     *
     * @throws IOException
     *             the IO exception
     */
    public void testZip() throws IOException{
        Date date1 = new Date();
        String inputFileName = "E:\\test"; // 你要压缩的文件夹
        String zipFileName = "E:\\test.zip"; // 压缩后的zip文件
        ZipUtil feiLongZip = new ZipUtil();
        ZipUtil.zip(inputFileName, zipFileName);
        Date date2 = new Date();
        log.info("耗时：" + DateExtensionUtil.getIntervalForView(date1, date2));
        // ,/select E:/Workspaces
        // Command.execFileOrDirectoryFocus("E:\\test,E:\\Project");
    }

    /**
     * Test un zip3.
     */
    @Test
    public void testUnZip3(){
        Date date1 = new Date();
        outputFileName = outputFileName + "antUnzip3"; // 解压到文件路径
        ZipUtil feiLongZip = new ZipUtil();
        ZipUtil.unZip(unZipFileName, outputFileName);
        Date date2 = new Date();
        log.info("耗时：" + DateExtensionUtil.getIntervalForView(date1, date2));
    }
}
