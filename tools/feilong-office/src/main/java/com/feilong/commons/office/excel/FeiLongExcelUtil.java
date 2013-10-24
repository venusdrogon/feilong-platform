package com.feilong.commons.office.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import com.feilong.commons.core.util.ObjectUtil;
import com.feilong.commons.core.util.StringUtil;

/**
 * feilongexcel 工具类
 * 
 * @author 金鑫 2010-7-12 下午11:08:13
 */
public class FeiLongExcelUtil{

	/**
	 * 设置单元格样式和值
	 * 
	 * @param cell
	 *            单元格
	 * @param valueObject
	 *            值
	 * @param rowIndex
	 *            行号
	 * @param feiLongExcelEntity
	 *            实体
	 * @author 金鑫
	 * @version 1.0 2010-7-16 上午11:13:05
	 */
	public static void setCellStyleAndCellValue(HSSFCell cell,Object valueObject,int rowIndex,FeiLongExcelEntity feiLongExcelEntity){
		// 单元格是否含有特殊字符串
		boolean isHasSpecialString = StringUtil.isContain(valueObject, feiLongExcelEntity.getSpecialString());
		// 是否是需要变背景颜色的行
		boolean isChangeColorRow = rowIndex % 2 != 0;
		// 两个有其一就创建cellStyle
		if (isHasSpecialString || isChangeColorRow){
			/**
			 * 有特殊字符 且隔行
			 */
			if (isHasSpecialString && isChangeColorRow){
				/**
				 * 高亮 且隔行变色
				 */
				if (feiLongExcelEntity.isHasSpecialStringToAddStyle() && feiLongExcelEntity.isRowChangeColor()){
					cell.setCellStyle(feiLongExcelEntity.getCellStyle_changeColorRowAndHightLight());
					valueObject = StringUtil.replaceAll(valueObject, feiLongExcelEntity.getSpecialString(), "");
				}
				/**
				 * 高亮不隔行变色
				 */
				else if (feiLongExcelEntity.isHasSpecialStringToAddStyle() && !feiLongExcelEntity.isRowChangeColor()){
					cell.setCellStyle(feiLongExcelEntity.getCellStyle_hightLight());
					valueObject = StringUtil.replaceAll(valueObject, feiLongExcelEntity.getSpecialString(), "");
				}
				/**
				 * 不高亮 隔行变色
				 */
				else if (!feiLongExcelEntity.isHasSpecialStringToAddStyle() && feiLongExcelEntity.isRowChangeColor()){
					cell.setCellStyle(feiLongExcelEntity.getCellStyle_changeColorRow());
				}
			}
			/**
			 * 有特殊字符 但不是隔行
			 */
			else if (isHasSpecialString && !isChangeColorRow){
				if (feiLongExcelEntity.isHasSpecialStringToAddStyle()){
					cell.setCellStyle(feiLongExcelEntity.getCellStyle_hightLight());
					valueObject = StringUtil.replaceAll(valueObject, feiLongExcelEntity.getSpecialString(), "");
				}
			}
			/**
			 * 隔行 没有特殊字符
			 */
			else if (isChangeColorRow && !isHasSpecialString){
				if (feiLongExcelEntity.isRowChangeColor()){
					cell.setCellStyle(feiLongExcelEntity.getCellStyle_changeColorRow());
				}
			}
		}
		FeiLongExcelUtil.setCellValue(cell, valueObject);
	}

	/**
	 * 表格设置值
	 * 
	 * @param cell
	 *            表格
	 * @param value
	 *            值
	 * @author 金鑫
	 * @version 1.0 2010-7-7 下午02:29:19
	 */
	public static void setCellValue(HSSFCell cell,Object value){
		cell.setCellValue(new HSSFRichTextString(ObjectUtil.toString(value)));
	}

	/**
	 * 获得强调的字体
	 * 
	 * @param workbook
	 * @return 获得强调的字体
	 * @author 金鑫
	 * @version 1.0 2010-7-12 下午11:06:52
	 */
	public static HSSFFont getStressHSSFFont(HSSFWorkbook workbook){
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体
		return font;
	}

	/**
	 * 高亮样式
	 * 
	 * @param workbook
	 * @return 高亮样式
	 * @author 金鑫
	 * @version 1.0 2010-7-16 上午10:34:20
	 */
	public static HSSFCellStyle getHSSFCellStyle_HightLight(HSSFWorkbook workbook){
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(getStressHSSFFont(workbook));
		return cellStyle;
	}

	/**
	 * 隔行变色 样式
	 * 
	 * @param workbook
	 * @return 隔行变色
	 * @author 金鑫
	 * @version 1.0 2010-7-16 上午10:29:07
	 */
	public static HSSFCellStyle getHSSFCellStyle_ChangeRowColor(HSSFWorkbook workbook){
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		// 指定的填充模式
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	/**
	 * 隔行变色 样式,并且高亮
	 * 
	 * @param workbook
	 * @return 隔行变色 样式,并且高亮
	 * @author 金鑫
	 * @version 1.0 2010-7-16 上午10:29:07
	 */
	public static HSSFCellStyle getHSSFCellStyle_ChangeRowColorAndHightLight(HSSFWorkbook workbook){
		HSSFCellStyle cellStyle = getHSSFCellStyle_ChangeRowColor(workbook);
		cellStyle.setFont(getStressHSSFFont(workbook));
		return cellStyle;
	}
}
