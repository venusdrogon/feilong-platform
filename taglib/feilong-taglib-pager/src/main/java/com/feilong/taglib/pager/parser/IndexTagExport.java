package com.feilong.taglib.pager.parser;

@SuppressWarnings("all")public final class IndexTagExport{

	public static final String	ITEM_COUNT	= "itemCount", PAGE_COUNT = "pageCount";

	private String				itemCount	= null;

	private String				pageCount	= null;

	final void setItemCount(String id){
		itemCount = id;
	}

	final void setPageCount(String id){
		pageCount = id;
	}

	public final String getItemCount(){
		return itemCount;
	}

	public final String getPageCount(){
		return pageCount;
	}
}
