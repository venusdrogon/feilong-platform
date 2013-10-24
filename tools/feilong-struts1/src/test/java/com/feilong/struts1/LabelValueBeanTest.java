package com.feilong.struts1;

import org.apache.struts.util.LabelValueBean;
import org.junit.Test;

public class LabelValueBeanTest{

	@Test
	public void f(){
		LabelValueBean labelValueBean = new LabelValueBean();
		labelValueBean.setLabel("haha");
		labelValueBean.setValue("ssssss");
		System.out.println(labelValueBean.getLabel());
	}
}
