package com.feilong.taglib.pager;

import com.feilong.taglib.pager.support.SkipTagSupport;

public final class SkipTag extends SkipTagSupport{

	private static final long	serialVersionUID	= 1L;

	private int					pages				= 0;

	public final void setPages(int value){
		pages = value;
	}

	public final int getPages(){
		return pages;
	}

	@Override
	protected boolean skip(){
		int skipPage = pagerTag.getPageNumber() + pages;
		boolean hasPage = pagerTag.hasPage(skipPage);
		if (hasPage){
			setPageAttributes(skipPage);
		}
		return hasPage;
	}

	@Override
	public void release(){
		pages = 0;
		super.release();
	}
}
