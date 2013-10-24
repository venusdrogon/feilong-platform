package com.feilong.taglib.pager;

import com.feilong.taglib.pager.support.SkipTagSupport;

public final class NextTag extends SkipTagSupport{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
	protected boolean skip(){
		boolean hasPage = pagerTag.hasNextPage();
		if (hasPage){
			setOffsetAttributes(pagerTag.getNextOffset());
		}
		return hasPage;
	}
}
/* vim:set ts=4 sw=4: */
