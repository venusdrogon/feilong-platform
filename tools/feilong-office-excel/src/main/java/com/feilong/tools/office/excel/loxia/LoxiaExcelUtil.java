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
package com.feilong.tools.office.excel.loxia;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import loxia.support.excel.ExcelManipulatorFactory;
import loxia.support.excel.ExcelReader;
import loxia.support.excel.ExcelWriter;
import loxia.support.excel.ReadStatus;
import loxia.support.excel.WriteStatus;
import loxia.support.excel.convertor.DataConvertorConfigurator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.UncheckedIOException;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.office.excel.loxia.convertor.BooleanConvertor;

/**
 * The Class LoxiaExcelUtil.
 */
public abstract class LoxiaExcelUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(LoxiaExcelUtil.class);

    static{
        //处理boolean 类型
        DataConvertorConfigurator.getInstance().registerDataConvertor(new BooleanConvertor());
    }

    /**
     * 获得 list.
     *
     * @param <T>
     *            the generic type
     * @param configuration
     *            the configuration
     * @param sheet
     *            the sheet
     * @param dataName
     *            the data name
     * @param fileName
     *            the file name
     * @param sheetNo
     *            the sheet no
     * @return the list
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static <T> List<T> getList(String configuration,String sheet,String dataName,String fileName,int sheetNo)
                    throws UncheckedIOException{
        String[] configurations = { configuration };
        return getList(configurations, sheet, dataName, fileName, sheetNo);
    }

    /**
     * 获得 list.
     *
     * @param <T>
     *            the generic type
     * @param configuration
     *            the configuration
     * @param sheet
     *            the sheet
     * @param dataName
     *            the data name
     * @param is
     *            the is
     * @param sheetNo
     *            the sheet no
     * @return the list
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static <T> List<T> getList(String configuration,String sheet,String dataName,InputStream is,int sheetNo)
                    throws UncheckedIOException{
        String[] configurations = { configuration };
        ExcelReader excelReader = getExcelReader(configurations, sheet);
        return getList(excelReader, dataName, is, sheetNo);
    }

    /**
     * 获得 list.
     *
     * @param <T>
     *            the generic type
     * @param configurations
     *            the configurations
     * @param sheet
     *            the sheet
     * @param dataName
     *            the data name
     * @param fileName
     *            the file name
     * @param sheetNo
     *            the sheet no
     * @return the list
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static <T> List<T> getList(String[] configurations,String sheet,String dataName,String fileName,int sheetNo)
                    throws UncheckedIOException{
        ExcelReader excelReader = getExcelReader(configurations, sheet);
        return getList(excelReader, dataName, fileName, sheetNo);
    }

    /**
     * 获得 excel reader.
     *
     * @param configurations
     *            the configurations
     * @param sheet
     *            the sheet
     * @return the excel reader
     * @since 1.0.9
     */
    private static ExcelReader getExcelReader(String[] configurations,String sheet){
        ExcelManipulatorFactory excelManipulatorFactory = new ExcelManipulatorFactory();
        excelManipulatorFactory.setConfig(configurations);
        ExcelReader excelReader = excelManipulatorFactory.createExcelReader(sheet);
        return excelReader;
    }

    /**
     * 获得 list.
     *
     * @param <T>
     *            the generic type
     * @param excelReader
     *            the excel reader
     * @param dataName
     *            the data name
     * @param fileName
     *            the file name
     * @param sheetNo
     *            the sheet no
     * @return the list
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    private static <T> List<T> getList(ExcelReader excelReader,String dataName,String fileName,int sheetNo) throws UncheckedIOException{
        InputStream is = FileUtil.getFileInputStream(fileName);
        return getList(excelReader, dataName, is, sheetNo);
    }

    /**
     * A.
     *
     * @param <T>
     *            the generic type
     * @param excelReader
     *            the excel reader
     * @param dataName
     *            the data name
     * @param is
     *            the is
     * @param sheetNo
     *            the sheet no
     * @return the list< t>
     * @since 1.0.9
     */
    private static <T> List<T> getList(ExcelReader excelReader,String dataName,InputStream is,int sheetNo){
        Map<String, Object> beans = new HashMap<String, Object>();
        beans.put(dataName, new ArrayList<T>());

        ReadStatus readStatus = excelReader.readSheet(is, sheetNo, beans);

        int status = readStatus.getStatus();
        if (status == ReadStatus.STATUS_SUCCESS){
            @SuppressWarnings("unchecked")
            List<T> trainSignUpEntityList = (List<T>) beans.get(dataName);
            return trainSignUpEntityList;
        }else{

            List<Exception> exceptions = readStatus.getExceptions();

            if (Validator.isNotNullOrEmpty(exceptions)){
                log.error("read excel exception,readStatus:[" + readStatus.getStatus() + "],getMessage:[" + readStatus.getMessage()
                                + "],and exceptions size is :[" + exceptions.size() + "],first exception is:", exceptions.get(0));
            }
            throw new UncheckedIOException(new IOException("read excel exception,and exceptions size is :[" + exceptions.size() + "]"));
        }
    }

    /**
     * Write.
     *
     * @param configurations
     *            the configuration
     * @param sheet
     *            the sheet
     * @param templateFileName
     *            the template file name
     * @param outputFileName
     *            the output file name
     * @param beans
     *            the beans
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static void write(String configurations,String sheet,String templateFileName,String outputFileName,Map<String, Object> beans)
                    throws UncheckedIOException{
        ExcelManipulatorFactory excelManipulatorFactory = new ExcelManipulatorFactory();
        excelManipulatorFactory.setConfig(configurations);

        ExcelWriter excelWriter = excelManipulatorFactory.createExcelWriter(sheet);

        InputStream is = FileUtil.getFileInputStream(templateFileName);
        OutputStream os = FileUtil.getFileOutputStream(outputFileName);

        WriteStatus writeStatus = excelWriter.write(is, os, beans);

        if (writeStatus.getStatus() == ReadStatus.STATUS_SUCCESS){
            log.info("write excel SUCCESS,outputFileName:[{}]", outputFileName);
        }else{
            log.warn(JsonUtil.format(writeStatus));
            throw new UncheckedIOException(new IOException("write excel exception,and writeStatus is :[" + writeStatus + "]"));
        }
    }
}
