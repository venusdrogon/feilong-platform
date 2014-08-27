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
package com.feilong.office.excel.loxia;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.IOUtil;

/**
 * The Class BaseLoxiaExcelReaderTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月22日 上午1:15:08
 * @since 1.0.8
 */
public abstract class BaseLoxiaExcelReaderTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(BaseLoxiaExcelReaderTest.class);

	/**
	 * Aa.
	 *
	 * @param <T>
	 *            the generic type
	 * @param configuration
	 *            the configuration
	 * @param sheet
	 *            the sheet
	 * @param fileName
	 *            the file name
	 * @param dataName
	 *            the data name
	 * @param sheetNo
	 *            the sheet no
	 * @return the list< train sign up entity>
	 * @throws IOException
	 *             the IO exception
	 */
	protected <T> List<T> getList(String configuration,String sheet,String fileName,String dataName,int sheetNo) throws IOException{
		ExcelManipulatorFactory excelManipulatorFactory = new ExcelManipulatorFactory();
		excelManipulatorFactory.setConfig(configuration);

		ExcelReader excelReader = excelManipulatorFactory.createExcelReader(sheet);

		InputStream is = IOUtil.getFileInputStream(fileName);

		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put(dataName, new ArrayList<T>());

		ReadStatus readStatus = excelReader.readSheet(is, sheetNo, beans);

		if (readStatus.getStatus() == ReadStatus.STATUS_SUCCESS){
			@SuppressWarnings("unchecked")
			List<T> trainSignUpEntityList = (List<T>) beans.get(dataName);
			return trainSignUpEntityList;
		}
		return null;
	}

	/**
	 * Write.
	 *
	 * @param configuration
	 *            the configuration
	 * @param sheet
	 *            the sheet
	 * @param templateFileName
	 *            the template file name
	 * @param outputFileName
	 *            the output file name
	 * @param beans
	 *            the beans
	 * @throws IOException
	 *             the IO exception
	 */
	protected void write(String configuration,String sheet,String templateFileName,String outputFileName,Map<String, Object> beans)
					throws IOException{
		ExcelManipulatorFactory excelManipulatorFactory = new ExcelManipulatorFactory();
		excelManipulatorFactory.setConfig(configuration);

		ExcelWriter excelWriter = excelManipulatorFactory.createExcelWriter(sheet);

		InputStream is = IOUtil.getFileInputStream(templateFileName);
		OutputStream os = IOUtil.getFileOutputStream(outputFileName);

		WriteStatus writeStatus = excelWriter.write(is, os, beans);

		if (writeStatus.getStatus() == ReadStatus.STATUS_SUCCESS){
			log.debug("ReadStatus.STATUS_SUCCESS,outputFileName:[{}]", outputFileName);
		}
	}
}
