package com.feilong.taglib.common;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 出生日期转换为年龄
 * 
 * @author 金鑫 2009-9-7上午11:49:48
 */
@Deprecated
public class AgeTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 出生日期
	 */
	private String				birthday;

	@Override
	public String writeContent(){
		return DateUtil.convertBirthdayToAge(birthday) + "";
	}

	public void setBirthday(String birthday){
		this.birthday = birthday;
	}
}
