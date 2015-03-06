/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.tools.mail;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.configure.ResourceBundleUtil;
import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.io.FileInfoEntity;
import com.feilong.commons.core.io.FileType;
import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.IOReaderUtil;
import com.feilong.tools.mail.entity.MailSenderConfig;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * The Class MailSenderUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2011 11:57:48 PM
 */
@SuppressWarnings("all")
public class MailSenderUtilTest{

    /** The Constant log. */
    private static final Logger   log            = LoggerFactory.getLogger(MailSenderUtilTest.class);

    private static ResourceBundle resourceBundle = ResourceBundleUtil.getResourceBundleByFileName("E:\\DataCommon\\Files\\mail.properties");

    /** 发送. */
    private String[]              tos            = { "xin.jin@baozun.com" };

    /** cc. */
    private String[]              ccs            = { "venusdrogon@163.com" };

    /** bcc. */
    private String[]              bccs           = { "190600641@qq.com", "1151889455@qq.com" };

    private String                personal       = "三国徐晃";

    private MailSenderConfig      mailSenderConfig;

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

    @Test
    public void sendMail1() throws IOException{
        String path = "C:/Users/feilong/feilong/train/1201单元测试/generalRegulation/generalRegulation-20141125194610.html";
        String textContent = IOReaderUtil.getFileContent(path, CharsetType.UTF8);
        mailSenderConfig.setContent(textContent);
    }

    @Test
    public void sendMail(){
        String textContent = "<html><body><hr/><div style='boder:1px #000 solid;color:red'>222222</div></body></html>";
        mailSenderConfig.setContent(textContent);
    }

    @Test
    public void testSendTextMail(){
        String textContent = "测试回执";
        mailSenderConfig.setContent(textContent);
    }

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
        contextKeyValues.put("PREFIX_CONTENTID", MailSenderUtil.PREFIX_CONTENTID);
        contextKeyValues.put("fileInfoEntityList", fileInfoEntityList);
        // ******************************************************************************************

        String textContent = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, contextKeyValues);
        mailSenderConfig.setContent(textContent);

        String fileString = "F:\\Picture 图片\\美女\\1.jpg";
        String[] filenameString = { FileUtil.getFileName(fileString) };
        mailSenderConfig.setAttachFileNames(filenameString);

        List<byte[]> attachList = new ArrayList<byte[]>();
        attachList.add(FileUtil.convertFileToByteArray(new File(fileString)));
        mailSenderConfig.setAttachList(attachList);
    }

    @After
    public void after() throws MessagingException,UnsupportedEncodingException{
        MailSenderUtil mailSenderUtil = new MailSenderUtil();
        mailSenderUtil.sendMail(mailSenderConfig);
    }
}
