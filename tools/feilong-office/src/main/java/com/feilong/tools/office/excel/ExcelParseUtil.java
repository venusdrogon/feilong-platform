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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.feilong.commons.core.util.NumberUtil;

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
	public static String getCellValue(HSSFSheet sheet,String cellLogo){
		int cellNum = 0;
		int rowIndex = 0;
		HSSFRow row = sheet.getRow(rowIndex);
		// TODO 未完成
		return getCellValue(row, cellNum);
	}

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
	public static String getCellValue(HSSFRow row,int cellNum){
		HSSFCell cell = row.getCell(cellNum);
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
	public static String getCellValue(HSSFCell cell){
		String returnValue = "";
		if (null != cell){
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					returnValue = NumberUtil.toString(cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_STRING:
					returnValue = cell.getRichStringCellValue().getString().trim();
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					returnValue = String.valueOf(cell.getBooleanCellValue());
					break;
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
	public static HSSFRow getHSSFRowByPrimaryKey(HSSFSheet sheet,int beginRowIndex,String primaryKeyValue,int primaryKeyColumnNum){
		HSSFRow row = null;
		int rowCount = getPhysicalNumberOfRows(sheet);
		if (rowCount > 0){
			for (int i = beginRowIndex; i < rowCount; i++){
				row = sheet.getRow(i);
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
	public static int getPhysicalNumberOfRows(HSSFSheet sheet){
		return sheet.getPhysicalNumberOfRows();
	}
	// **************************************************************************

	/**
	 * 导入excel表格 获得对应的表数据
	 * 
	 * @param excelPath
	 *            excel路径
	 * @return
	 * @author 金鑫
	 * @version 1.0 2009-5-20下午12:45:49
	 */
	// public HSSFSheet importExcel(String excelPath){
	// InputStream inputStream = null;
	// try{
	// inputStream = new FileInputStream(excelPath);
	// }catch (Exception e){
	// e.printStackTrace();
	// }
	// return importExcel(inputStream);
	// }
	/**
	 * 导入excel表格 获得对应的表数据
	 * 
	 * @param inputStream
	 * @return
	 * @author 金鑫
	 * @version 1.0 2009-5-20下午01:39:33
	 */
	// public HSSFSheet importExcel(InputStream inputStream){
	// try{
	// workbook = new HSSFWorkbook(inputStream);
	// sheet = workbook.getSheetAt(0);
	// // sheet.getFirstRowNum();
	// // sheet.getLastRowNum();
	// }catch (Exception e){
	// e.printStackTrace();
	// }
	// return sheet;
	// }

}
