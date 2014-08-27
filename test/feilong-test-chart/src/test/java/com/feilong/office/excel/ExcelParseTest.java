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
package com.feilong.office.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.controller.chart.mp2.person.Mp2PersonInfo;
import com.feilong.tools.office.excel.ExcelParseUtil;

/**
 * 解析 excel.
 * 
 * @author 金鑫 2010-7-7 上午11:44:53
 */
public class ExcelParseTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ExcelParseTest.class);

	/**
	 * Parses the.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void parse() throws IOException{
		String name = "E:\\DataCommon\\Files\\Mindmap\\xmind\\技术\\mp2\\mp2 人员名单统计 2014-02-08.xlsx";
		InputStream inputStream = new FileInputStream(name);

		// 2013
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Map<String, Object> map = ExcelParseUtil.getSheetMapForLog(sheet);
		log.info(JsonUtil.format(map));

		List<Mp2PersonInfo> list = new ArrayList<Mp2PersonInfo>();

		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < physicalNumberOfRows; ++i){
			Row row = sheet.getRow(i);

			Map<String, Object> rowMapLog = ExcelParseUtil.getRowMapForLog(row);
			log.info(JsonUtil.format(rowMapLog));

			Mp2PersonInfo personInfo = new Mp2PersonInfo();
			for (int j = 0; j < 7; ++j){
				Cell cell = row.getCell(j);

				Map<String, Object> cellMapLog = ExcelParseUtil.getCellMapForLog(cell);
				log.info(JsonUtil.format(cellMapLog));
			}
			personInfo.setName(ExcelParseUtil.getCellValue(row.getCell(0)));
			personInfo.setTitle(ExcelParseUtil.getCellValue(row.getCell(1)));
			personInfo.setLevel(ExcelParseUtil.getCellValue(row.getCell(2)));
			personInfo.setEntryTime(ExcelParseUtil.getCellValue(row.getCell(3)));
			personInfo.setJoinTime(ExcelParseUtil.getCellValue(row.getCell(4)));
			personInfo.setMemo(ExcelParseUtil.getCellValue(row.getCell(5)));
			personInfo.setPassportCase(ExcelParseUtil.getCellValue(row.getCell(6)));
			list.add(personInfo);
		}
		log.info(JsonUtil.format(list));
	}
}
