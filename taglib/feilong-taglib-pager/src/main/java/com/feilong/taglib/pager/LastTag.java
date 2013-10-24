package com.feilong.taglib.pager;

import com.feilong.taglib.pager.support.JumpTagSupport;

public final class LastTag extends JumpTagSupport{

	private static final long	serialVersionUID	= 1L;

	@Override
	protected int getJumpPage(){
		return (pagerTag.getPageCount() - 1);
	}
}
