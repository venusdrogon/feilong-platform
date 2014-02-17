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

import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.test.Mp2PersonInfo;
import com.feilong.tools.office.excel.ExcelParseUtil;

/**
 * 解析 excel
 * 
 * @author 金鑫 2010-7-7 上午11:44:53
 */
public class ExcelParseTest{

	private static final Logger	log	= LoggerFactory.getLogger(ExcelParseTest.class);

	@Test
	public void parse() throws IOException{
		String name = "E:\\DataCommon\\Files\\Mindmap\\xmind\\技术\\mp2\\mp2 人员名单统计 2014-02-08.xlsx";
		InputStream inputStream = new FileInputStream(name);

		// 2013
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Map<String, Object> map = ExcelParseUtil.getSheetMapForLog(sheet);
		log.info(JsonFormatUtil.format(map));

		List<Mp2PersonInfo> list = new ArrayList<Mp2PersonInfo>();

		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < physicalNumberOfRows; ++i){
			Row row = sheet.getRow(i);

			Map<String, Object> rowMapLog = ExcelParseUtil.getRowMapForLog(row);
			log.info(JsonFormatUtil.format(rowMapLog));

			Mp2PersonInfo personInfo = new Mp2PersonInfo();
			for (int j = 0; j < 7; ++j){
				Cell cell = row.getCell(j);

				Map<String, Object> cellMapLog = ExcelParseUtil.getCellMapForLog(cell);
				log.info(JsonFormatUtil.format(cellMapLog));
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
		log.info(JsonFormatUtil.format(list));
	}
}
