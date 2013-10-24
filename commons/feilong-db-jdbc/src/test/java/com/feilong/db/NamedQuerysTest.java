package com.feilong.db;

import org.junit.Test;

import com.feilong.commons.db.NamedQuerys;

public class NamedQuerysTest{

	@Test
	public void getQueryByName(){
		NamedQuerys namedQuerys = new NamedQuerys();
		System.out.println(namedQuerys.getQueryByName("findProductSizeBatchNumberList"));
	}
}
