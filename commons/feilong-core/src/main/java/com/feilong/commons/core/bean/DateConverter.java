package com.feilong.commons.core.bean;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.beanutils.converters.DateTimeConverter;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.text.DateFormatUtil;
import com.feilong.commons.core.util.Validator;

/**
 * The Class DateConverter,for example:
 * 
 * <pre>
 * User a = new User();
 * a.setId(5L);
 * Date now = new Date();
 * a.setDate(now);
 * User b = new User();
 * DateConverter converter = new DateConverter(DatePattern.forToString, Locale.US);
 * ConvertUtils.register(converter, Date.class);
 * BeanUtil.copyProperty(b, a, &quot;date&quot;);
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014-5-4 0:35:31
 */
public class DateConverter extends DateTimeConverter{

	/** pattern {@link DatePattern}. */
	private String	pattern;

	/** The locale. */
	private Locale	locale	= Locale.getDefault();

	/**
	 * Instantiates a new date converter.
	 * 
	 * @param pattern
	 *            the pattern
	 */
	public DateConverter(String pattern){
		this.pattern = pattern;
	}

	/**
	 * Instantiates a new date converter.
	 * 
	 * @param pattern
	 *            the pattern
	 * @param locale
	 *            the locale
	 */
	public DateConverter(String pattern, Locale locale){
		this.pattern = pattern;
		this.locale = locale;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.beanutils.converters.AbstractConverter#getDefaultType()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected Class getDefaultType(){
		return Date.class;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.beanutils.converters.AbstractConverter#convert(java.lang.Class, java.lang.Object)
	 */
	@Override
	public Object convert(@SuppressWarnings("rawtypes") Class type,Object value){
		if (Validator.isNullOrEmpty(pattern)){
			throw new IllegalArgumentException("value can't be null/empty!");
		}
		if (value == null){
			return (null);
		}
		Date dateObj = null;
		if (value instanceof String){
			dateObj = DateFormatUtil.parse(value.toString(), pattern, locale);
		}
		return dateObj;
	}

	/**
	 * Sets the pattern {@link DatePattern}.
	 * 
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern){
		this.pattern = pattern;
	}

	/**
	 * Sets the locale.
	 * 
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(Locale locale){
		this.locale = locale;
	}
}