package com.feilong.taglib.pager;

import com.feilong.taglib.pager.support.SkipTagSupport;

public final class PrevTag extends SkipTagSupport{

	private static final long	serialVersionUID	= 1L;

	@Override
	protected boolean skip(){
		boolean hasPage = pagerTag.hasPrevPage();
		if (hasPage){
			setOffsetAttributes(pagerTag.getPrevOffset());
		}
		return hasPage;
	}
}
