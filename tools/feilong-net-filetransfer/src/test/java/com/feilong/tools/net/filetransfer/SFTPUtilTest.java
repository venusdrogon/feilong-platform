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
package com.feilong.tools.net.filetransfer;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.feilong.commons.core.io.FileInfoEntity;
import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class SFTPUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 3, 2013 3:21:51 PM
 */
public class SFTPUtilTest extends FileTransferTest{

    /** The Constant log. */
    private static final Logger log             = LoggerFactory.getLogger(SFTPUtilTest.class);

    /** The file transfer. */
    @Autowired
    @Qualifier("nikeSFTPUtil")
    private FileTransfer        fileTransfer;

    //	private String				remoteDirectory	= "/home/appuser/test";
    /** The remote directory. */
    private String              remoteDirectory = "/home/bzuser/test";

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.filetransfer.FileTransferTest#sendLocalFileToRemote()
     */
    @Override
    @Test
    public void sendLocalFileToRemote() throws Exception{
        String singleLocalFileFullPath = "E:\\1.txt";
        fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);

        singleLocalFileFullPath = "E:/hahaha.txt";
        fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.filetransfer.FileTransferTest#sendLocalFileToRemote_dir()
     */
    @Override
    @Test
    public void sendLocalFileToRemote_dir() throws Exception{
        String singleLocalFileFullPath = "C:\\Users\\feilong\\Downloads\\1.pdf";
        fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.filetransfer.FileTransferTest#sendLocalFileToRemote_dirs()
     */
    @Override
    @Test
    public void sendLocalFileToRemote_dirs() throws Exception{
        String[] batchLocalFileFullPaths = { "E:\\test", "E:\\1.txt", "E:\\test1" };
        fileTransfer.sendLocalFileToRemote(batchLocalFileFullPaths, remoteDirectory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.FileTransferTest#delete()
     */
    @Override
    @Test
    public void delete() throws Exception{
        String remoteAbsolutePath = "/home/appuser/test/pg_ctl.conf";
        fileTransfer.delete(remoteAbsolutePath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.FileTransferTest#delete_dir()
     */
    @Override
    @Test
    public void delete_dir() throws Exception{
        String remoteAbsolutePath = "/home/niketest/out/test/2011-07-07";
        fileTransfer.delete(remoteAbsolutePath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.FileTransferTest#delete_dir_empty()
     */
    @Override
    @Test
    public void delete_dir_empty() throws Exception{
        String remoteAbsolutePath = "/home/appuser/test/2013-01-06";
        fileTransfer.delete(remoteAbsolutePath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.FileTransferTest#delete_not_exist()
     */
    @Override
    @Test
    public void delete_not_exist() throws Exception{
        String remoteAbsolutePath = "/home/appuser/test/2011-07-051/";
        fileTransfer.delete(remoteAbsolutePath);
    }

    /**
     * Delete_not_exist1.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void delete_not_exist1() throws Exception{
        String remoteAbsolutePath = "/";
        fileTransfer.delete(remoteAbsolutePath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.FileTransferTest#sendLocalFileToRemote_dir_chinese()
     */
    @Override
    public void sendLocalFileToRemote_dir_chinese() throws Exception{
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.filetransfer.FileTransferTest#download_file()
     */
    @Override
    @Test
    public void download_file() throws Exception{
        String remotePath = "/home/niketest/out/test/2011-07-07/nikeid_pix_cancel.csv";
        String localAbsoluteDirectoryPath = "E:\\test\\1";
        fileTransfer.download(remotePath, localAbsoluteDirectoryPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.FileTransferTest#download_dir()
     */
    @Override
    @Test
    public void download_dir() throws Exception{
        String remotePath = "/home/niketest/out/test";
        String localAbsoluteDirectoryPath = "E:\\test\\1";
        fileTransfer.download(remotePath, localAbsoluteDirectoryPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.filetransfer.FileTransferTest#testGetFileEntityMap()
     */
    @Override
    @Test
    public void testGetFileEntityMap() throws Exception{
        String remoteAbsolutePath = "/home/appuser/test/2013-12-04-1938";
        String[] fileNames = { "SportActivity.dat", "SubCategory.dat", "aaa" };
        Map<String, FileInfoEntity> fileEntityMap = fileTransfer.getFileEntityMap(remoteAbsolutePath, fileNames);

        log.info(JsonUtil.format(fileEntityMap));
    }

}
