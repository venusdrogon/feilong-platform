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
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.feilong.commons.core.lang.ObjectUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 生成excel文档(POI).
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2009-5-19下午08:08:37
 * @version 1.1 2010年7月7日 12:04:48
 * @version 1.2 2014-2-10 02:11
 */
public final class ExcelCreateUtil{

	/**
	 * 将数据集合转换成excel,将excel生成到文件中.
	 * 
	 * @param columnsTitle
	 *            列标题数组
	 * @param list
	 *            list
	 * @param fileName
	 *            文件名称(有路径需要加)
	 * @param excelEntity
	 *            the excel entity
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void createExcel(String[] columnsTitle,List<?> list,String fileName,ExcelConfigEntity excelEntity) throws IOException{
		FileOutputStream outputStream = new FileOutputStream(fileName);
		createExcel(columnsTitle, list, excelEntity, outputStream);
	}

	/**
	 * 将数据集合转换成excel.
	 * 
	 * @param columnsTitle
	 *            列标题数组
	 * @param dataList
	 *            list
	 * @param excelConfigEntity
	 *            excel实体配置
	 * @param outputStream
	 *            outputStream 输出流
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void createExcel(String[] columnsTitle,List<?> dataList,ExcelConfigEntity excelConfigEntity,OutputStream outputStream)
					throws IOException{

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();

		// 总列数
		int columnsLength = columnsTitle.length;
		setSheetStyle(sheet, columnsLength, excelConfigEntity);

		/**
		 * 生成3种样式
		 */
		HSSFCellStyle hssfCellStyle_HightLight = ExcelCellStyleUtil.getHSSFCellStyle_HightLight(workbook);
		HSSFCellStyle hssfCellStyle_ChangeRowColor = ExcelCellStyleUtil.getHSSFCellStyle_ChangeRowColor(workbook);
		HSSFCellStyle hssfCellStyle_ChangeRowColorAndHightLight = ExcelCellStyleUtil.getHSSFCellStyle_ChangeRowColorAndHightLight(workbook);

		excelConfigEntity.setCellStyle_hightLight(hssfCellStyle_HightLight);
		excelConfigEntity.setCellStyle_changeColorRow(hssfCellStyle_ChangeRowColor);
		excelConfigEntity.setCellStyle_changeColorRowAndHightLight(hssfCellStyle_ChangeRowColorAndHightLight);

		// 创建excel标题行
		createExcelTitleRow(workbook, sheet, columnsTitle);
		// excel填充数据
		createExcelDataRow(sheet, dataList, columnsLength, excelConfigEntity);

		workbook.write(outputStream);
	}

	/**
	 * 设置 sheet 样式.
	 * 
	 * @param sheet
	 *            the sheet
	 * @param columnsLength
	 *            列长度
	 * @param excelConfig
	 *            the excel config
	 */
	private void setSheetStyle(HSSFSheet sheet,int columnsLength,ExcelConfigEntity excelConfig){
		// 打印网格线
		// sheet.setGridsPrinted(true);
		// 显示自动分页符 自动换行
		sheet.setAutobreaks(true);

		// 合并单元格
		// sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 5));

		// 是否冻结窗口
		if (excelConfig.getIsFreezePane()){
			if (columnsLength > 19){// 冻拆窗口
				sheet.createFreezePane(2, 1);
			}else{
				sheet.createFreezePane(0, 1, 0, 1);
			}
		}
	}

	// **********************************************************************************
	/**
	 * 创建数据行.
	 * 
	 * @author 金鑫
	 * @version 1.0 2010-4-23 上午10:34:42
	 * @param sheet
	 *            表
	 * @param dataList
	 *            the data list
	 * @param columnCount
	 *            列数
	 * @param excelEntity
	 *            the excel entity
	 */
	private void createExcelDataRow(HSSFSheet sheet,List<?> dataList,int columnCount,ExcelConfigEntity excelEntity){

		if (Validator.isNotNullOrEmpty(dataList)){
			// 数据行数
			int z = dataList.size();
			// 填充excel数据
			for (int i = 0; i < z; i++){

				HSSFRow row = sheet.createRow(i + 1);
				// 设置行高
				row.setHeightInPoints(18);

				for (int j = 0; j < columnCount; j++){

					HSSFCell cell = row.createCell(j);

					// 取到集合中的每一行数据数组
					Object[] objects = (Object[]) dataList.get(i);
					Object valueObject = objects[j];

					setCellValueAndStyle(cell, i, valueObject, excelEntity);
				}
			}
		}
	}

	/**
	 * 创建标题行.
	 * 
	 * @param workbook
	 *            the workbook
	 * @param sheet
	 *            the sheet
	 * @param columnsTitle
	 *            the columns title
	 */
	private void createExcelTitleRow(HSSFWorkbook workbook,HSSFSheet sheet,String[] columnsTitle){

		HSSFRow row = sheet.createRow(0);

		// 设置行高
		row.setHeightInPoints(22);

		// 列数
		int columnCount = columnsTitle.length;

		HSSFCellStyle titleRowCellStyle = ExcelCellStyleUtil.getHSSFCellStyleForTitleRowCell(workbook);

		for (int j = 0; j < columnCount; j++){
			HSSFCell cell = row.createCell(j);
			// 设置样式
			cell.setCellStyle(titleRowCellStyle);

			String value = columnsTitle[j];

			setCellValue(cell, value);
		}
	}

	// ***************************************************************************

	/**
	 * 设置单元格值和样式.
	 * 
	 * @param cell
	 *            单元格
	 * @param rowIndex
	 *            行号
	 * @param value
	 *            值
	 * @param excelConfigEntity
	 *            实体
	 */
	private void setCellValueAndStyle(HSSFCell cell,int rowIndex,Object value,ExcelConfigEntity excelConfigEntity){
		// 单元格是否含有特殊字符串
		String specialString = excelConfigEntity.getSpecialString();
		boolean isHasSpecialString = StringUtil.isContain(value, specialString);

		// 是否是需要变背景颜色的行
		boolean isChangeColorRow = rowIndex % 2 != 0;

		// 两个有其一就创建cellStyle
		if (isHasSpecialString || isChangeColorRow){

			boolean isHasSpecialStringToAddStyle = excelConfigEntity.getIsHasSpecialStringToAddStyle();
			boolean isRowChangeColor = excelConfigEntity.getIsRowChangeColor();

			// 有特殊字符 且隔行
			if (isHasSpecialString && isChangeColorRow){

				// 高亮 且隔行变色
				if (isHasSpecialStringToAddStyle && isRowChangeColor){
					cell.setCellStyle(excelConfigEntity.getCellStyle_changeColorRowAndHightLight());
					value = StringUtil.replaceAll(value, specialString, "");
				}

				// 高亮不隔行变色
				else if (isHasSpecialStringToAddStyle && !isRowChangeColor){
					cell.setCellStyle(excelConfigEntity.getCellStyle_hightLight());
					value = StringUtil.replaceAll(value, specialString, "");
				}

				// 不高亮 隔行变色
				else if (!isHasSpecialStringToAddStyle && isRowChangeColor){
					cell.setCellStyle(excelConfigEntity.getCellStyle_changeColorRow());
				}
			}

			// 有特殊字符 但不是隔行
			else if (isHasSpecialString && !isChangeColorRow){
				if (isHasSpecialStringToAddStyle){
					cell.setCellStyle(excelConfigEntity.getCellStyle_hightLight());
					value = StringUtil.replaceAll(value, specialString, "");
				}
			}

			// 隔行 没有特殊字符
			else if (isChangeColorRow && !isHasSpecialString){
				if (isRowChangeColor){
					cell.setCellStyle(excelConfigEntity.getCellStyle_changeColorRow());
				}
			}
		}

		setCellValue(cell, value);
	}

	/**
	 * 表格设置值.
	 * 
	 * @param cell
	 *            表格
	 * @param value
	 *            值
	 */
	private void setCellValue(HSSFCell cell,Object value){
		cell.setCellValue(new HSSFRichTextString(ObjectUtil.toString(value)));
	}
}