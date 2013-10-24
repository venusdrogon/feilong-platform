package com.feilong.commons.core.io;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

public class CSVUtilTest{

	@Test
	public void testWrite(){
		String path = "/home/webuser/nike_int/johnData/${date}/nikeid_pix_demand.csv";
		path = path.replace("${date}", DateUtil.date2String(DateUtil.getYesterday(new Date()), DatePattern.onlyDate));
		System.out.println(path);
		String[] columnTitles = { "a", "b" };
		List<Object[]> linkedList = new ArrayList<Object[]>();
		for (int i = 0; i < 20; i++){
			Object[] object = { i + "金,鑫", i + "jin'\"xin" };
			linkedList.add(object);
		}
		CSVUtil.write(path, columnTitles, linkedList);
	}
}
