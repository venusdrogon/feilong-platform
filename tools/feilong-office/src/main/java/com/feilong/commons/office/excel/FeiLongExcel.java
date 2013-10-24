package com.feilong.commons.office.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import com.feilong.commons.core.util.NumberUtil;
import com.feilong.commons.core.util.ObjectUtil;

/**
 * 处理Excel文档(Excel)
 * 
 * @author 金鑫
 * @version 1.0 2009-5-19下午08:08:37
 * @version 1.1 2010年7月7日 12:04:48
 */
public class FeiLongExcel{

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

	/**
	 * 将生成的excel数据保存到物理路径中
	 * 
	 * @param result
	 *            数据集
	 * @param fileName
	 *            生成的路径
	 * @param feiLongExcelEntity
	 *            feiLongExcelEntity
	 * @author 金鑫
	 * @version 1.0 2009-5-19下午09:22:31
	 * @version 1.1 2010-7-7 下午02:05:29
	 */
	public void convertResultToExcel(Result result,String fileName,FeiLongExcelEntity feiLongExcelEntity){
		try{
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			convertResultToExcel(result, feiLongExcelEntity, fileOutputStream);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 将数据集合转换成excel,将excel生成到文件中
	 * 
	 * @param columnsTitle
	 *            列标题数组
	 * @param list
	 *            list,hibernate查询的数组集合
	 * @param fileName
	 *            文件名称(有路径需要加)
	 * @author 金鑫
	 * @version 1.0 2010-4-23 下午12:51:48
	 */
	public void convertListToExcel(String[] columnsTitle,List list,String fileName,FeiLongExcelEntity feiLongExcelEntity){
		try{
			FileOutputStream outputStream = new FileOutputStream(fileName);
			convertListToExcel(columnsTitle, list, feiLongExcelEntity, outputStream);
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 将生成的excel数据保存到流当中
	 * 
	 * @param result
	 *            数据集
	 * @param feiLongExcelEntity
	 *            feiLongExcelEntity
	 * @param outputStream
	 *            流
	 * @author 金鑫
	 * @version 1.0 2009-5-20上午11:32:29
	 * @version 1.1 2010-7-7 下午02:02:49
	 */
	public void convertResultToExcel(Result result,FeiLongExcelEntity feiLongExcelEntity,OutputStream outputStream){
		/**
		 * 标题数组
		 */
		String[] columnsTitle = result.getColumnNames();
		convertListToExcel(columnsTitle, result, feiLongExcelEntity, outputStream);
	}

	/**
	 * 将数据集合转换成excel
	 * 
	 * @param columnsTitle
	 *            列标题数组
	 * @param listOrResult
	 *            list,hibernate查询的数组集合
	 * @param feiLongExcelEntity
	 *            excel实体配置
	 * @param outputStream
	 *            outputStream 输出流
	 * @author 金鑫
	 * @version 1.0 2010-2-22 上午11:43:17
	 * @version 1.1 2010年4月23日 10:23:54
	 * @version 1.2 2010年7月7日 11:41:48
	 */
	public void convertListToExcel(String[] columnsTitle,Object listOrResult,FeiLongExcelEntity feiLongExcelEntity,OutputStream outputStream){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		// 打印网格线
		// sheet.setGridsPrinted(true);
		// 显示自动分页符 自动换行
		sheet.setAutobreaks(true);
		// 合并单元格
		// sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 5));
		int columnsLength = columnsTitle.length;
		// 是否冻结窗口
		if (feiLongExcelEntity.isFreezePane()){
			if (columnsLength > 19){// 冻拆窗口
				sheet.createFreezePane(2, 1);
			}else{
				sheet.createFreezePane(0, 1, 0, 1);
			}
		}
		/**
		 * 生成3种样式
		 */
		feiLongExcelEntity.setCellStyle_hightLight(FeiLongExcelUtil.getHSSFCellStyle_HightLight(workbook));
		feiLongExcelEntity.setCellStyle_changeColorRow(FeiLongExcelUtil.getHSSFCellStyle_ChangeRowColor(workbook));
		feiLongExcelEntity.setCellStyle_changeColorRowAndHightLight(FeiLongExcelUtil.getHSSFCellStyle_ChangeRowColorAndHightLight(workbook));
		// 创建excel标题行
		createExcelTitleRow(workbook, sheet, columnsTitle, feiLongExcelEntity);
		// excel填充数据
		createExcelDataRow(workbook, sheet, listOrResult, columnsLength, feiLongExcelEntity);
		try{
			workbook.write(outputStream);
		}catch (IOException e){
			e.printStackTrace();
		}
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
	 * excel填充数据
	 * 
	 * @param sheet
	 *            表
	 * @param listOrResult
	 *            list,hibernate查询的数组集合
	 * @param columnCount
	 *            列数
	 * @author 金鑫
	 * @version 1.0 2010-4-23 上午10:34:42
	 */
	private void createExcelDataRow(HSSFWorkbook workbook,HSSFSheet sheet,Object listOrResult,int columnCount,FeiLongExcelEntity feiLongExcelEntity){
		Object[] objects = null;
		Object valueObject = null;
		SortedMap sortedMaps = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		// 数据行数
		int dataRowCount = 0;
		List dataList = null;
		Result result = null;
		if (listOrResult instanceof List){
			dataList = (List) listOrResult;
			dataRowCount = dataList.size();
		}else if (listOrResult instanceof Result){
			result = (Result) listOrResult;
			dataRowCount = result.getRowCount();
		}
		// 填充excel数据
		for (int i = 0, z = dataRowCount; i < z; i++){
			row = sheet.createRow(i + 1);
			for (int j = 0; j < columnCount; j++){
				cell = row.createCell(j);
				if (listOrResult instanceof List){
					// 取到集合中的每一行数据数组
					objects = (Object[]) dataList.get(i);
					valueObject = objects[j];
				}else if (listOrResult instanceof Result){
					sortedMaps = result.getRows()[i];
					valueObject = sortedMaps.get(result.getColumnNames()[j]);
				}
				FeiLongExcelUtil.setCellStyleAndCellValue(cell, valueObject, i, feiLongExcelEntity);
			}
		}
	}

	/**
	 * 创建excel标题行
	 * 
	 * @param sheet
	 *            HSSFSheet excel单表
	 * @param columnsTitle
	 *            标题数组
	 * @author 金鑫
	 * @version 1.0 2010-4-23 上午10:28:09
	 */
	private void createExcelTitleRow(HSSFWorkbook workbook,HSSFSheet sheet,String[] columnsTitle,FeiLongExcelEntity feiLongExcelEntity){
		// 创建excel标题
		HSSFRow row = sheet.createRow(0);
		// 设置行高
		row.setHeightInPoints(20);
		// 列数
		int columnCount = columnsTitle.length;
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
		HSSFCell cell = null;
		for (int j = 0; j < columnCount; j++){
			cell = row.createCell(j);
			// 设置样式
			cell.setCellStyle(cellStyle);
			FeiLongExcelUtil.setCellValue(cell, columnsTitle[j]);
		}
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
		int rowCount = FeiLongExcel.getPhysicalNumberOfRows(sheet);
		if (rowCount > 0){
			for (int i = beginRowIndex; i < rowCount; i++){
				row = sheet.getRow(i);
				if (primaryKeyValue.equals(FeiLongExcel.getCellValue(row, primaryKeyColumnNum))){
					return row;
				}
			}
		}
		return null;
	}

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
	 * 获得单元格值(BigDecimal)
	 * 
	 * @param row
	 *            HSSFRow
	 * @param cellIndex
	 *            格索引
	 * @return BigDecimal
	 * @author 金鑫
	 * @version 1.0 2010-4-7 下午03:54:25
	 * @deprecated
	 */
	@Deprecated
	public static BigDecimal getCellValueToBigDecimal(HSSFRow row,int cellIndex){
		return ObjectUtil.toBigDecimal(getCellValue(row, cellIndex));
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
	/**
	 * 将List<HSSFRow> 转成excel
	 * 
	 * @param list
	 * @param outputStream
	 * @author 金鑫
	 * @version 1.0 2010-2-21 下午06:04:33
	 */
	// public void createExcelToFile(List<HSSFRow> list,OutputStream outputStream){
	// try{
	// fillWorkbook(list).write(outputStream);
	// }catch (IOException e){
	// e.printStackTrace();
	// }
	// }
	/**
	 * 填充数据
	 * 
	 * @param list
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-2-21 下午06:04:42
	 */
	// public HSSFWorkbook fillWorkbook(List<HSSFRow> list){
	// // workbook = new HSSFWorkbook();
	// // sheet = workbook.createSheet();
	// // for (int i = 0; i < list.size(); i++){
	// // row = sheet.createRow((short) i);
	// // row = list.get(i);
	// // // sheet.
	// // }
	// // System.out.println(sheet.getPhysicalNumberOfRows());
	// // System.out.println(sheet.getRow(0).getCell(0));
	// // return workbook;
	// }
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
