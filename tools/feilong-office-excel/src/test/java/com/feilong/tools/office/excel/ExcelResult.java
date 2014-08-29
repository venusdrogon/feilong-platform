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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;

import com.feilong.servlet.jsp.ResultUtil;
import com.feilong.tools.office.excel.ExcelConfigEntity;
import com.feilong.tools.office.excel.ExcelCreateUtil;

/**
 * 处理Excel文档(Excel)
 * 
 * @author 金鑫
 * @version 1.0 2009-5-19下午08:08:37
 * @version 1.1 2010年7月7日 12:04:48
 */
public class ExcelResult{

	/**
	 * 将生成的excel数据保存到物理路径中
	 * 
	 * @param result
	 *            数据集
	 * @param fileName
	 *            生成的路径
	 * @param excelConfigEntity
	 *            feiLongExcelEntity
	 * @author 金鑫
	 * @version 1.0 2009-5-19下午09:22:31
	 * @version 1.1 2010-7-7 下午02:05:29
	 */
	public void convertResultToExcel(Result result,String fileName,ExcelConfigEntity excelConfigEntity){
		try{
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			convertResultToExcel(result, excelConfigEntity, fileOutputStream);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 将生成的excel数据保存到流当中
	 * 
	 * @param result
	 *            数据集
	 * @param excelConfigEntity
	 *            feiLongExcelEntity
	 * @param outputStream
	 *            流
	 * @author 金鑫
	 * @version 1.0 2009-5-20上午11:32:29
	 * @version 1.1 2010-7-7 下午02:02:49
	 * @throws IOException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public void convertResultToExcel(Result result,ExcelConfigEntity excelConfigEntity,OutputStream outputStream) throws IOException,
			SecurityException,IllegalArgumentException,ClassNotFoundException,NoSuchMethodException,InstantiationException,
			IllegalAccessException,InvocationTargetException{
		/**
		 * 标题数组
		 */
		String[] columnsTitle = result.getColumnNames();
		List<?> list = ResultUtil.convertResultToList(result, ExcelConfigEntity.class);

		ExcelCreateUtil excel = new ExcelCreateUtil();
		excel.createExcel(columnsTitle, list, excelConfigEntity, outputStream);
	}
}
