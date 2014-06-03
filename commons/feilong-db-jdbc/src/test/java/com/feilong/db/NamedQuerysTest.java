package com.feilong.db;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.db.NamedQuerys;

public class NamedQuerysTest{

	private static final Logger	log	= LoggerFactory.getLogger(NamedQuerysTest.class);

	@Test
	public void getQueryByName(){
		NamedQuerys namedQuerys = new NamedQuerys();
		log.info(namedQuerys.getQueryByName("findProductSizeBatchNumberList"));
	}
}
