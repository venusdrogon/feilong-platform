package com.feilong.tools.guqu;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.DesktopUtil;

public class FeiLongGuquUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(FeiLongGuquUtilTest.class);

	@Test
	public void getSearchUrl(){
		String keyword = "杜聪专辑";
		keyword = "康美之恋";
		keyword = "四维广大";
		String result = FeiLongGuquUtil.getSearchUrl(keyword, 0, 2);
		log.info(result);
		DesktopUtil.browse(result);
	}
}
