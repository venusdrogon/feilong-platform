package com.feilong.struts1;

import org.apache.struts.util.LabelValueBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LabelValueBeanTest{

	private static final Logger	log	= LoggerFactory.getLogger(LabelValueBeanTest.class);

	@Test
	public void f(){
		LabelValueBean labelValueBean = new LabelValueBean();
		labelValueBean.setLabel("haha");
		labelValueBean.setValue("ssssss");
		log.info(labelValueBean.getLabel());
	}
}
