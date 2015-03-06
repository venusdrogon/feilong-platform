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

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.feilong.commons.core.io.FileInfoEntity;
import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class FTPUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:13:27
 */
public class FTPUtilTest extends FileTransferTest{

    /** The Constant log. */
    private static final Logger log             = LoggerFactory.getLogger(FTPUtilTest.class);

    /** The file transfer. */
    @Autowired()
    @Qualifier("nikeFTPUtil")
    private FileTransfer        fileTransfer;

    /** The remote directory. */
    private String              remoteDirectory = "/webstore/InlineSales_Test/2011-07-05/";

    /**
     * Inits the.
     */
    @Before
    public void init(){
    }

    /**
     * 传送单个文件.
     * 
     * @throws Exception
     *             the exception
     */
    @Override
    @Test
    public void sendLocalFileToRemote() throws Exception{
        String singleLocalFileFullPath = "E:/nikestore_china_revenuesss.csv";
        // fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
        singleLocalFileFullPath = "E:/学车.xmind";
        fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
    }

    /**
     * 传送文件夹.
     * 
     * @throws Exception
     *             the exception
     */
    @Override
    @Test
    public void sendLocalFileToRemote_dir() throws Exception{
        String singleLocalFileFullPath = "F:\\2013-12-04-1938";
        fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
    }

    /**
     * 批量传文件.
     * 
     * @throws Exception
     *             the exception
     */
    @Override
    @Test
    public void sendLocalFileToRemote_dirs() throws Exception{
        String[] batchLocalFileFullPaths = { "E:\\test", "E:\\1.jpg" };
        fileTransfer.sendLocalFileToRemote(batchLocalFileFullPaths, remoteDirectory);

        String singleLocalFileFullPath = "E:\\config.jsp";
        fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
    }

    /**
     * 传送文件夹(中文目录).
     * 
     * @throws Exception
     *             the exception
     */
    @Override
    @Test
    public void sendLocalFileToRemote_dir_chinese() throws Exception{
        String localFileFullPath = "E:\\test - 副本";
        fileTransfer.sendLocalFileToRemote(localFileFullPath, remoteDirectory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.FileTransferTest#delete()
     */
    @Override
    @Test
    public void delete() throws Exception{
        String remoteAbsolutePath = "/webstore/InlineSales_Test/2011-07-05/1.jpg";
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
        String remoteAbsolutePath = "/webstore/InlineSales_Test/2011-07-05/test";
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
        String remoteAbsolutePath = "/webstore/InlineSales_Test/a/";
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
        String remoteAbsolutePath = "/webstore/InlineSales_Test/2011-07-051/";
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

    /**
     * Checks if is directory.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void isDirectory() throws Exception{

        fileTransfer.connect();
        // 文件夹
        log.info(fileTransfer.isDirectory("/webstore/InlineSales_Test/2011-07-07") + "");
        // 空的文件夹
        log.info(fileTransfer.isDirectory("/webstore/InlineSales_Test/1") + "");
        // 文件
        log.info(fileTransfer.isDirectory("/webstore/InlineSales_Test/2011-07-07/nikeid_pix_cancel.csv") + "");
        // 不存在的文件
        log.info(fileTransfer.isDirectory("/webstore/InlineSales_Test/2011-07-07/nikeid_pix_cancel1.csv") + "");

        fileTransfer.disconnect();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.FileTransferTest#download()
     */
    @Override
    @Test
    public void download_file() throws Exception{
        String remotePath = "/webstore/InlineSales_Test/nikestore_china_cancel20130103.csv";
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
        String remotePath = "/webstore/InlineSales_Test/2011-07-05";
        String localAbsoluteDirectoryPath = "E:\\test\\1";
        fileTransfer.download(remotePath, localAbsoluteDirectoryPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.net.FileTransferTest#getFileEntityMap()
     */
    @Override
    @Test
    public void testGetFileEntityMap() throws Exception{
        String remotePath = "/webstore/InlineSales_Test/2011-07-05/2013-12-04-1938";
        String[] fileNames = { "SportActivity.dat", "SubCategory.dat", "aaa" };
        Map<String, FileInfoEntity> fileEntityMap = fileTransfer.getFileEntityMap(remotePath, fileNames);

        log.info("fileEntityMap:{}", JsonUtil.format(fileEntityMap));
    }
}
