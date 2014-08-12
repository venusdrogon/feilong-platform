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
import com.feilong.tools.office.excel.ExcelParseUtil;

/**
 * 解析 excel
 * 
 * @author 金鑫 2010-7-7 上午11:44:53
 */
public class ExcelParseUtil2Test{

	private static final Logger	log	= LoggerFactory.getLogger(ExcelParseUtil2Test.class);

	@Test
	public void parse() throws IOException{
		String name = "E:\\Workspaces\\feilong\\feilong-platform\\tools\\feilong-office-excel\\src\\test\\resources\\Kota Surabaya.xlsx";
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
	}
}
