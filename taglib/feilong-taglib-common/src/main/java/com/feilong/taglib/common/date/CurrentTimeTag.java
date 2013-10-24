package com.feilong.taglib.common.date;

import java.util.Date;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 显示当前时间
 * 
 * @author 金鑫 2010-4-13 下午09:17:10
 */
public class CurrentTimeTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 显示模式
	 */
	private String				pattern				= "yyyy-MM-dd";

	@Override
	public String writeContent(){
		return DateUtil.date2String(new Date(), pattern);
	}

	/**
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern){
		this.pattern = pattern;
	}
}
