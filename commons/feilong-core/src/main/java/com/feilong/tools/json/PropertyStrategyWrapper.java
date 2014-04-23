package com.feilong.tools.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONException;
import net.sf.json.util.PropertySetStrategy;

/**
 * The Class PropertyStrategyWrapper.<br>
 * Ignore missing properties with Json-Lib <br>
 * 避免出现 Unknown property 'orderIdAndCodeMap' on class 'class com.baozun.trade.web.controller.payment.result.command.PaymentResultEntity' 异常
 * 
 * @see http://javaskeleton.blogspot.com/2011/05/ignore-missing-properties-with-json-lib.html
 * @see http://envy2002.iteye.com/blog/1682738
 */
public class PropertyStrategyWrapper extends PropertySetStrategy{

	private static final Logger	log	= LoggerFactory.getLogger(PropertyStrategyWrapper.class);

	/** The original. */
	private PropertySetStrategy	propertySetStrategy;

	/**
	 * Instantiates a new property strategy wrapper.
	 * 
	 * @param propertySetStrategy
	 *            the property set strategy
	 */
	public PropertyStrategyWrapper(PropertySetStrategy propertySetStrategy){
		this.propertySetStrategy = propertySetStrategy;
	}

	/*
	 * (non-Javadoc)
	 * @see net.sf.json.util.PropertySetStrategy#setProperty(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setProperty(Object o,String string,Object o1) throws JSONException{
		try{
			propertySetStrategy.setProperty(o, string, o1);
		}catch (Exception e){
			log.warn(e.getMessage());
		}
	}
}