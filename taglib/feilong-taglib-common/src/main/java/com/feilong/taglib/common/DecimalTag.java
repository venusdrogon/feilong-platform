package com.feilong.taglib.common;

import com.feilong.taglib.base.AbstractCommonTag;
import com.feilong.tools.html.HTMLSpan;

/**
 * 数字标签
 * 
 * @author 金鑫 2009-7-1下午03:59:46
 */
@Deprecated
public class DecimalTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 数字值
	 */
	private double				value;

	@Override
	public String writeContent(){
		return HTMLSpan.createSpan_decimalAddColor(value);
	}

	/**
	 * 数字值
	 * 
	 * @param value
	 */
	public void setValue(double value){
		this.value = value;
	}
}
