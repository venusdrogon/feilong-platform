package com.feilong.taglib.pager;

import com.feilong.taglib.pager.support.JumpTagSupport;

@SuppressWarnings("all")public final class FirstTag extends JumpTagSupport{

	private static final long	serialVersionUID	= 1L;

	@Override
	protected int getJumpPage(){
		return 0;
	}
}
