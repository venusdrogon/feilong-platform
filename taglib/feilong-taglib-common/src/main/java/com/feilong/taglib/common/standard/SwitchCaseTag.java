package com.feilong.taglib.common.standard;

import javax.servlet.jsp.tagext.Tag;

import com.feilong.taglib.base.BaseTag;

/**
 * case标签 配合switch标签使用.
 * 
 * @author 金鑫 2010-3-19 上午11:18:06
 */
public class SwitchCaseTag extends BaseTag{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** 值. */
	private String				value;

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */
	public int doStartTag(){
		Tag tag = getParent();
		SwitchTag switchTag = (SwitchTag) tag;
		// 判断是否可以执行本身。
		if (!switchTag.isExecuteTag()){
			// 如果当前的value=switch的value，
			// 通知父类。表示已经有了一个符合条件的。 否则，忽略标签体。
			String parentValue = switchTag.getValue();
			if (this.value.equals(parentValue)){
				switchTag.setExecuteTag();
				return EVAL_BODY_INCLUDE;
			}
		}
		return SKIP_BODY;
	}

	/**
	 * Sets the 值.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value){
		this.value = value;
	}
}
