package com.feilong.taglib.display;

import javax.servlet.jsp.tagext.Tag;

import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 生成列(th/td)
 * 
 * @author 金鑫 2010-5-5 下午02:56:56
 */
public class ColumnTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 每个td的样式
	 */
	private String				tdClass;

	/**
	 * 每个td的样式
	 */
	private String				tdStyle;

	/**
	 * 标题
	 */
	private String				title;

	@Override
	public int doStartTag(){
		Tag tag = findAncestorWithClass(this, TableTag.class);
		TableTag tableTag = (TableTag) tag;
		int lengthCount = tableTag.getLengthCount();
		if (lengthCount == 1){
			tableTag.addTitle(title);
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<td");
		if (Validator.isNotNullOrEmpty(tdClass)){
			stringBuilder.append(" class=\"" + tdClass + "\"");
		}
		if (Validator.isNotNullOrEmpty(tdStyle)){
			stringBuilder.append(" style=\"" + tdStyle + "\"");
		}
		stringBuilder.append(">");
		print(stringBuilder);
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag(){
		println("</td>");
		return EVAL_PAGE;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * @param tdClass
	 *            the tdClass to set
	 */
	public void setTdClass(String tdClass){
		this.tdClass = tdClass;
	}

	/**
	 * @param tdStyle
	 *            the tdStyle to set
	 */
	public void setTdStyle(String tdStyle){
		this.tdStyle = tdStyle;
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.taglib.base.BaseTag#writeContent()
	 */
	@Override
	protected Object writeContent(){
		// TODO Auto-generated method stub
		return null;
	}
}
