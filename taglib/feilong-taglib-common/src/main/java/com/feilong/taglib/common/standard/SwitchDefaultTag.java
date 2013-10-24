package com.feilong.taglib.common.standard;

import javax.servlet.jsp.tagext.Tag;

import com.feilong.taglib.base.BaseTag;

/**
 * default标签 配合switch标签使用
 * 
 * @author 金鑫 2010年3月19日 11:24:28
 */
public class SwitchDefaultTag extends BaseTag{

	private static final long	serialVersionUID	= 1L;

	@Override
	public int doStartTag(){
		Tag tag = getParent();
		SwitchTag switchTag = (SwitchTag) tag;
		if (!switchTag.isExecuteTag()){
			switchTag.setExecuteTag();
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}
}
