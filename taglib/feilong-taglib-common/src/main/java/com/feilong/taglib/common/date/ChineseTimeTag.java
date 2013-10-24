package com.feilong.taglib.common.date;

import java.util.Date;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 日期转换成中国特色日期
 * 
 * @author 金鑫 2009-9-7上午11:49:48
 */
public class ChineseTimeTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 日期
	 */
	private Date				date				= null;

	/**
	 * 日期字符串 dateString和pattern结合使用,dateString优先于date
	 */
	private String				dateString			= null;

	/**
	 * 日期字符串模式pattern结合使用
	 */
	private String				pattern				= null;

	/**
	 * 仅仅显示日期
	 */
	private boolean				onlyChinese;

	/**
	 * 带不带颜色
	 */
	private boolean				hasColor;

	@Override
	public String writeContent(){
		/**
		 * 日期字符串 dateString和pattern结合使用,dateString优先于date
		 */
		if (Validator.isNotNullOrEmpty(dateString)){
			date = DateUtil.string2Date(dateString, pattern);
		}
		if (Validator.isNotNullOrEmpty(date)){
			// 仅仅显示日期
			if (onlyChinese){
				return DateUtil.date2String(date, "yyyy年MM月dd日");
			}
			if (hasColor){
				//return DateUtil.convertDateToChineseDate2(date);
			}
			return DateUtil.toHumanizationDateString(date);
		}
		return "";
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date){
		this.date = date;
	}

	/**
	 * @param onlyChinese
	 *            the onlyChinese to set
	 */
	public void setOnlyChinese(boolean onlyChinese){
		this.onlyChinese = onlyChinese;
	}

	/**
	 * @param hasColor
	 *            the hasColor to set
	 */
	public void setHasColor(boolean hasColor){
		this.hasColor = hasColor;
	}

	/**
	 * @param dateString
	 *            the dateString to set
	 */
	public void setDateString(String dateString){
		this.dateString = dateString;
	}

	/**
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern){
		this.pattern = pattern;
	}
}
