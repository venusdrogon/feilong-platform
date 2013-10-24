package com.feilong.office.excel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.office.excel.FeiLongExcel;
import com.feilong.commons.office.excel.FeiLongExcelEntity;

/**
 * FeiLongExcelTest
 * 
 * @author 金鑫 2010-7-7 上午11:44:53
 */
public class FeiLongExcelTest{

	@Test
	public void convertListToExcel(){
		FeiLongExcel feiLongExcel = new FeiLongExcel();
		try{
			// InputStream inputStream = null;
			// inputStream = new FileInputStream("D:\\1.xlsx");
			// System.out.println(IOUtil.isExcel2007("D:\\1.xlsx"));
			// HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			// HSSFSheet sheet = workbook.getSheetAt(0);
			// System.out.println(sheet.getLastRowNum());
		}catch (Exception e){
			e.printStackTrace();
		}
		String[] columnsTitle = new String[30];
		columnsTitle[0] = "姓名";
		columnsTitle[1] = "地址";
		columnsTitle[2] = "email";
		for (int i = 3; i < 30; i++){
			columnsTitle[i] = "呵呵" + i;
		}
		List list = new ArrayList();
		String[] temp = null;
		for (int i = 0; i < 200; i++){
			temp = new String[30];
			temp[0] = "1";
			temp[1] = "GABC22222";
			temp[2] = "xy2venus@163.com";
			for (int j = 3; j < 30; j++){
				temp[j] = "呵呵" + j;
			}
			list.add(temp);
		}
		FeiLongExcelEntity feiLongExcelEntity = new FeiLongExcelEntity();
		feiLongExcelEntity.setSpecialString("G");
		feiLongExcelEntity.setHasSpecialStringToAddStyle(true);
		feiLongExcelEntity.setRowChangeColor(false);
		feiLongExcel.convertListToExcel(
				columnsTitle,
				list,
				"D://test" + DateUtil.date2String(new Date(), "yyyyMMddHHmmss") + ".xls",
				feiLongExcelEntity);
	}
}
