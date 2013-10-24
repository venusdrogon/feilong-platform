package com.feilong.taglib.display;

import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.entity.Pager;
import com.feilong.commons.core.util.ObjectUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.AbstractConditionalTag;

/**
 * 没有数据显示的时候
 * 
 * @author 金鑫 2010-6-4 上午06:25:47
 */
public class IsEmptyTag extends AbstractConditionalTag{

	private static final long	serialVersionUID	= 1L;

	@Override
	protected boolean condition(){
		HttpServletRequest request = getHttpServletRequest();
		Object isHasData = request.getAttribute("FeiLongDisplayIsHasData");
		if (null != isHasData){
			// 有数据
			return !ObjectUtil.toBoolean(isHasData);
		}
		Pager pageModel = (Pager) request.getAttribute("pageModel");
		if (null != pageModel){
			return Validator.isNullOrEmpty(pageModel.getItemList());
		}
		return false;
	}
}
