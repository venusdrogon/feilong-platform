package com.feilong.taglib.pager;

import com.feilong.taglib.pager.support.JumpTagSupport;

@SuppressWarnings("all")public final class LastTag extends JumpTagSupport{

	private static final long	serialVersionUID	= 1L;

	@Override
	protected int getJumpPage(){
		return (pagerTag.getPageCount() - 1);
	}
}
