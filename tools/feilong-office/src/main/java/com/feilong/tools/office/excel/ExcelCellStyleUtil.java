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

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 * 生成 excel cell style
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 10, 2014 2:51:03 AM
 */
public final class ExcelCellStyleUtil{

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

	public static HSSFCellStyle getHSSFCellStyleForTitleRowCell(HSSFWorkbook workbook){
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		// 指定的填充模式
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
		// cellStyle.setFillBackgroundColor(HSSFColor.RED.index);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.WHITE.index);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体
		cellStyle.setFont(font);

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

	/**
	 * 获得隔行变色的样式
	 * 
	 * @param workbook
	 *            workbook
	 * @return 获得隔行变色的样式
	 * @author 金鑫
	 * @version 1.0 2010-7-7 下午03:01:23
	 */
	public HSSFCellStyle getRowChangeHSSFCellStyle(HSSFWorkbook workbook){
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		// 指定的填充模式
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	/**
	 * 创建表格样式
	 * 
	 * @author 金鑫
	 * @version 1.0 2010-2-21 下午06:05:19
	 */
	// public void createCellStyle(){
	// HSSFCellStyle cellStyle = workbook.createCellStyle();
	// // 细边框
	// // cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	// // cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	// // cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	// cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	// // 对齐方式
	// // cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	// HSSFFont font = workbook.createFont();
	// // font.setFontName("Arial");
	// // 设置颜色
	// font.setColor(HSSFFont.COLOR_RED);
	// cellStyle.setFont(font);
	// }
}
