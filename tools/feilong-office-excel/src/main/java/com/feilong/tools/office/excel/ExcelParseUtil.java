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
package com.feilong.tools.office.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.NotImplementedException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * 处理Excel文档(POI)
 * 
 * @author 金鑫
 * @version 1.0 2009-5-19下午08:08:37
 * @version 1.1 2010年7月7日 12:04:48
 * @version 1.2 2014-2-10 02:11
 */
public class ExcelParseUtil{

	/**
	 * 获得表格值 通过"A4" "D5"的方式
	 * 
	 * @param sheet
	 *            sheet
	 * @param cellLogo
	 *            "A4" "D5"的方式
	 * @return 获得表格值 通过"A4" "D5"的方式
	 * @author 金鑫
	 * @version 1.0 Jul 9, 2010 1:20:08 AM
	 */
	// public static String getCellValue(HSSFSheet sheet,String cellLogo){
	// int cellNum = 0;
	// int rowIndex = 0;
	// HSSFRow row = sheet.getRow(rowIndex);
	// // TODO 未完成
	// return getCellValue(row, cellNum);
	// }

	/**
	 * 获得单元格值
	 * 
	 * @param row
	 *            HSSFRow
	 * @param cellNum
	 *            cell索引
	 * @return 获得单元格值
	 * @author 金鑫
	 * @version 1.0 2010年4月7日 15:45:21
	 */
	public static String getCellValue(Row row,int cellNum){
		Cell cell = row.getCell(cellNum);
		return getCellValue(cell);
	}

	/**
	 * 获得单元格值 当null==cell时,返回""
	 * 
	 * @param cell
	 *            cell
	 * @return 获得单元格值
	 * @author 金鑫
	 * @version 1.0 Jul 9, 2010 1:24:07 AM
	 */
	public static String getCellValue(Cell cell){
		String returnValue = "";
		if (null != cell){
			int cellType = cell.getCellType();
			switch (cellType) {
				case Cell.CELL_TYPE_BLANK:
					break;
				case Cell.CELL_TYPE_ERROR:
					returnValue = cell.getErrorCellValue() + "";
					break;
				case Cell.CELL_TYPE_FORMULA:// 公式
					returnValue = cell.getCellFormula();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					//TODO 重新定义下格式
					//returnValue = NumberUtil.toString(cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_STRING:
					returnValue = cell.getRichStringCellValue().getString().trim();
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					returnValue = String.valueOf(cell.getBooleanCellValue());
					break;
				default:
					throw new NotImplementedException("getCellValue:" + cellType + " is not implemented!");
			}
		}
		return returnValue;
	}

	/**
	 * 通过主键值 主键列位置 找到第一个匹配的行
	 * 
	 * @param sheet
	 * @param beginRowIndex
	 *            开始行号
	 * @param primaryKeyValue
	 *            主键值
	 * @param primaryKeyColumnNum
	 *            主键列位置
	 * @return 通过主键值 主键列位置 找到第一个匹配的行
	 * @author 金鑫
	 * @version 1.0 Jul 9, 2010 3:31:22 AM
	 */
	public static Row getRowByPrimaryKey(Sheet sheet,int beginRowIndex,String primaryKeyValue,int primaryKeyColumnNum){
		int rowCount = getPhysicalNumberOfRows(sheet);
		if (rowCount > 0){
			for (int i = beginRowIndex; i < rowCount; i++){
				Row row = sheet.getRow(i);
				if (primaryKeyValue.equals(getCellValue(row, primaryKeyColumnNum))){
					return row;
				}
			}
		}
		return null;
	}

	// **************************************************************************
	/**
	 * 创建excel,已经处理了异常
	 * 
	 * @param inputStream
	 *            inputStream
	 * @return HSSFWorkbook
	 * @author 金鑫
	 * @version 1.0 2010-7-8 下午05:15:54
	 */
	public HSSFWorkbook createHSSFWorkbook(InputStream inputStream){
		try{
			return new HSSFWorkbook(inputStream);
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得sheet 总行数
	 * 
	 * @param sheet
	 *            sheet
	 * @return 获得sheet 总行数
	 * @author 金鑫
	 * @version 1.0 Jul 9, 2010 1:56:02 AM
	 */
	public static int getPhysicalNumberOfRows(Sheet sheet){
		return sheet.getPhysicalNumberOfRows();
	}

	/**
	 * @param row
	 */
	public static Map<String, Object> getCellMapForLog(Cell cell){
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("getCellComment", cell.getCellComment());

		int cellType = cell.getCellType();
		map.put("getCellType", cellType);
		switch (cellType) {
			case Cell.CELL_TYPE_BLANK:

				break;
			case Cell.CELL_TYPE_BOOLEAN:
				map.put("getBooleanCellValue", cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_ERROR:
				map.put("getErrorCellValue", cell.getErrorCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:// 公式
				map.put("getCellFormula", cell.getCellFormula());
				map.put("getCachedFormulaResultType", cell.getCachedFormulaResultType());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				map.put("getDateCellValue", cell.getDateCellValue());
				map.put("getNumericCellValue", cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				map.put("getStringCellValue", cell.getStringCellValue());
				map.put("getRichStringCellValue", cell.getRichStringCellValue());
				break;
			default:
				break;
		}

		map.put("getColumnIndex", cell.getColumnIndex());
		map.put("getHyperlink", cell.getHyperlink());
		map.put("getRowIndex", cell.getRowIndex());

		return map;
	}

	/**
	 * @param row
	 */
	public static Map<String, Object> getRowMapForLog(Row row){
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("getFirstCellNum", row.getFirstCellNum());
		map.put("getHeight", row.getHeight());
		map.put("getHeightInPoints", row.getHeightInPoints());
		map.put("getLastCellNum", row.getLastCellNum());
		map.put("getPhysicalNumberOfCells", row.getPhysicalNumberOfCells());
		map.put("getRowNum", row.getRowNum());
		map.put("getRowStyle", row.getRowStyle());
		map.put("getZeroHeight", row.getZeroHeight());

		return map;
	}

	/**
	 * @param sheet
	 * @return
	 */
	public static Map<String, Object> getSheetMapForLog(Sheet sheet){
		int lastRowNum = sheet.getLastRowNum();
		int firstRowNum = sheet.getFirstRowNum();
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("getLastRowNum", lastRowNum);
		map.put("getFirstRowNum", firstRowNum);
		map.put("getPhysicalNumberOfRows", physicalNumberOfRows);
		map.put("getAutobreaks", sheet.getAutobreaks());
		map.put("getDefaultColumnWidth", sheet.getDefaultColumnWidth());
		map.put("getDefaultRowHeight", sheet.getDefaultRowHeight());
		map.put("getDefaultRowHeightInPoints", sheet.getDefaultRowHeightInPoints());
		map.put("getDisplayGuts", sheet.getDisplayGuts());
		map.put("getFitToPage", sheet.getFitToPage());
		map.put("getForceFormulaRecalculation", sheet.getForceFormulaRecalculation());
		map.put("getHorizontallyCenter", sheet.getHorizontallyCenter());
		map.put("getLeftCol", sheet.getLeftCol());
		map.put("getNumMergedRegions", sheet.getNumMergedRegions());
		map.put("getPaneInformation", sheet.getPaneInformation());
		map.put("getProtect", sheet.getProtect());
		map.put("getRowSumsBelow", sheet.getRowSumsBelow());
		map.put("getRowSumsRight", sheet.getRowSumsRight());
		map.put("getScenarioProtect", sheet.getScenarioProtect());
		map.put("getSheetName", sheet.getSheetName());
		map.put("getTopRow", sheet.getTopRow());
		map.put("getVerticallyCenter", sheet.getVerticallyCenter());
		return map;
	}

}
