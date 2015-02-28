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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

/**
 * 解析 excel.
 *
 * @author 金鑫 2010-7-7 上午11:44:53
 */
public class ExcelParseUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ExcelParseUtilTest.class);

	/**
	 * Parses the.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void parse() throws IOException{
		String name = "E:\\Workspaces\\feilong\\feilong-platform\\tools\\feilong-office-excel\\src\\test\\resources\\testCellFormula.xlsx";
		InputStream inputStream = new FileInputStream(name);

		// 2013
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Map<String, Object> map = ExcelParseUtil.getSheetMapForLog(sheet);
		log.info(JsonUtil.format(map));

		Row row = sheet.getRow(0);

		Map<String, Object> rowMapLog = ExcelParseUtil.getRowMapForLog(row);
		log.info(JsonUtil.format(rowMapLog));

		Cell cell = row.getCell(0);

		String cellValue = ExcelParseUtil.getCellValue(cell);

		log.debug("getCellMapForLog0:{}", JsonUtil.format(ExcelParseUtil.getCellMapForLog(cell)));
		log.debug("the param cellValue0:{}", cellValue);

		cell = row.getCell(1);
		cellValue = ExcelParseUtil.getCellValue(cell);
		log.debug("getCellMapForLog1:{}", JsonUtil.format(ExcelParseUtil.getCellMapForLog(cell)));
		log.debug("the param cellValue1:{}", cellValue);
	}
}
