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
package com.feilong.tools.office.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.servlet.jsp.ResultUtil;

/**
 * 处理Excel文档(Excel).
 *
 * @author 金鑫
 * @version 1.0 2009-5-19下午08:08:37
 * @version 1.1 2010年7月7日 12:04:48
 */
public class ExcelResult{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ExcelResult.class);

    /**
     * 将生成的excel数据保存到物理路径中.
     *
     * @author 金鑫
     * @version 1.0 2009-5-19下午09:22:31
     * @version 1.1 2010-7-7 下午02:05:29
     * @param result
     *            数据集
     * @param fileName
     *            生成的路径
     * @param excelConfigEntity
     *            feiLongExcelEntity
     */
    public void convertResultToExcel(Result result,String fileName,ExcelConfigEntity excelConfigEntity){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            convertResultToExcel(result, excelConfigEntity, fileOutputStream);
        }catch (Exception e){
            log.error(e.getClass().getName(), e);
        }
    }

    /**
     * 将生成的excel数据保存到流当中.
     *
     * @author 金鑫
     * @version 1.0 2009-5-20上午11:32:29
     * @version 1.1 2010-7-7 下午02:02:49
     * @param result
     *            数据集
     * @param excelConfigEntity
     *            feiLongExcelEntity
     * @param outputStream
     *            流
     * @throws IOException
     *             the IO exception
     * @throws SecurityException
     *             the security exception
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws ClassNotFoundException
     *             the class not found exception
     * @throws NoSuchMethodException
     *             the no such method exception
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    public void convertResultToExcel(Result result,ExcelConfigEntity excelConfigEntity,OutputStream outputStream) throws IOException,
                    SecurityException,IllegalArgumentException,ClassNotFoundException,NoSuchMethodException,InstantiationException,
                    IllegalAccessException,InvocationTargetException{
        /**
         * 标题数组
         */
        String[] columnsTitle = result.getColumnNames();
        List<?> list = ResultUtil.convertResultToList(result, ExcelConfigEntity.class);

        ExcelCreateUtil excel = new ExcelCreateUtil();
        excel.createExcel(columnsTitle, list, excelConfigEntity, outputStream);
    }
}
