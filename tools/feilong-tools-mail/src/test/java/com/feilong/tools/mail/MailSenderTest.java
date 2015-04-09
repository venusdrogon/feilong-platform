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
package com.feilong.tools.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.feilong.commons.core.configure.ResourceBundleUtil;
import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.io.FileInfoEntity;
import com.feilong.commons.core.io.FileType;
import com.feilong.commons.core.io.IOReaderUtil;
import com.feilong.tools.mail.entity.MailSenderConfig;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * The Class MailSenderTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年3月26日 下午4:43:11
 * @since 1.0.9
 */
public class MailSenderTest{

    /** The resource bundle. */
    private static ResourceBundle resourceBundle = ResourceBundleUtil.getResourceBundleByFileName("E:\\DataCommon\\Files\\mail.properties");

    /** 发送. */
    private final String[]        tos            = { "xin.jin@baozun.com" };

    /** cc. */
    private final String[]        ccs            = { "venusdrogon@163.com" };

    /** bcc. */
    private final String[]        bccs           = { "190600641@qq.com", "1151889455@qq.com" };

    /** The personal. */
    private final String          personal       = "三国徐晃";

    /** The mail sender config. */
    private MailSenderConfig      mailSenderConfig;

    /**
     * Before.
     */
    @Before
    public void before(){
        mailSenderConfig = new MailSenderConfig();
        mailSenderConfig.setMailServerHost(resourceBundle.getString("mailServerHost"));
        mailSenderConfig.setMailServerPort(resourceBundle.getString("mailServerPort"));

        String userName = resourceBundle.getString("userName");
        mailSenderConfig.setUserName(userName);
        mailSenderConfig.setPassword(resourceBundle.getString("password"));

        String fromAddress = userName;
        //fromAddress = "190600641@qq.com";
        mailSenderConfig.setFromAddress(fromAddress);
        mailSenderConfig.setPersonal(personal);

        mailSenderConfig.setTos(tos);
        mailSenderConfig.setCcs(ccs);
        mailSenderConfig.setBccs(bccs);

        mailSenderConfig.setSubject("feilong mail test");// + DateUtil.date2String(new Date())

        mailSenderConfig.setIsDebug(true);
        mailSenderConfig.setIsNeedReturnReceipt(false);
    }

    /**
     * Send mail1.
     *
     * @throws IOException
     *             the IO exception
     */
    @Test
    public void sendMail1() throws IOException{
        String path = "C:/Users/feilong/feilong/train/1201单元测试/generalRegulation/generalRegulation-20141125194610.html";
        String textContent = IOReaderUtil.getFileContent(path, CharsetType.UTF8);
        mailSenderConfig.setContent(textContent);
    }

    /**
     * Send mail.
     */
    @Test
    public void sendMail(){
        String textContent = "<html><body><hr/><div style='boder:1px #000 solid;color:red'>222222</div></body></html>";
        mailSenderConfig.setContent(textContent);
    }

    /**
     * Test send text mail.
     */
    @Test
    public void testSendTextMail(){
        String textContent = "测试回执";
        mailSenderConfig.setContent(textContent);
    }

    /**
     * Send mail with attach.
     *
     * @throws IOException
     *             the IO exception
     */
    @Test
    public void sendMailWithAttach() throws IOException{
        String templateInClassPath = "velocity/mailtest.vm";
        // ******************************************************************************************
        FileInfoEntity fileInfoEntity = new FileInfoEntity();

        fileInfoEntity.setFileType(FileType.FILE);
        fileInfoEntity.setLastModified(new Date().getTime());
        fileInfoEntity.setName("nikestore_china_cancel20130910.csv");
        fileInfoEntity.setSize(25655L);
        // ******************************************************************************************
        FileInfoEntity fileInfoEntity2 = new FileInfoEntity();

        fileInfoEntity2.setFileType(FileType.FILE);
        fileInfoEntity2.setLastModified(new Date().getTime());
        fileInfoEntity2.setName("nikestore_china_revenue20131022.csv");
        fileInfoEntity2.setSize(25655L);
        // ******************************************************************************************
        FileInfoEntity fileInfoEntity3 = new FileInfoEntity();
        fileInfoEntity3.setFileType(FileType.FILE);
        fileInfoEntity3.setLastModified(new Date().getTime());
        fileInfoEntity3.setName("nikestore_china_return20131022.csv");
        fileInfoEntity3.setSize(25655L);
        // ******************************************************************************************
        FileInfoEntity fileInfoEntity4 = new FileInfoEntity();
        fileInfoEntity4.setFileType(FileType.FILE);
        fileInfoEntity4.setLastModified(new Date().getTime());
        fileInfoEntity4.setName("nikestore_china_demand20130910.csv");
        fileInfoEntity4.setSize(25655L);
        // ******************************************************************************************
        List<FileInfoEntity> fileInfoEntityList = new ArrayList<FileInfoEntity>();
        fileInfoEntityList.add(fileInfoEntity);
        fileInfoEntityList.add(fileInfoEntity2);
        fileInfoEntityList.add(fileInfoEntity3);
        fileInfoEntityList.add(fileInfoEntity4);

        // ******************************************************************************************
        Map<String, Object> contextKeyValues = new HashMap<String, Object>();
        contextKeyValues.put("PREFIX_CONTENTID", DefaultMailSender.PREFIX_CONTENTID);
        contextKeyValues.put("fileInfoEntityList", fileInfoEntityList);
        // ******************************************************************************************

        String textContent = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, contextKeyValues);
        mailSenderConfig.setContent(textContent);

        String fileString = "E:\\DataFixed\\Material\\avatar\\飞龙.png";
        fileString = "E:\\\\Workspaces\\\\baozun\\\\BaozunSql\\\\train\\\\20150417Spring事务\\\\ppt-contents.png";
        mailSenderConfig.setAttachFilePaths(fileString);
    }

    /**
     * After.
     */
    @After
    public void after(){
        MailSender mailSender = new DefaultMailSender();
        mailSender.sendMail(mailSenderConfig);
    }
}
