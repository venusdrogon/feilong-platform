package com.feilong.taglib.html;

import javax.servlet.jsp.JspException;

import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.BaseHtmlTag;
import com.feilong.taglib.util.TagUtils;

/**
 * 后台创建一个html标签
 * 
 * @author 金鑫 2010-6-24 上午05:23:52
 * @deprecated
 */
public class HtmlTag extends BaseHtmlTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 标签体是否不为空
	 */
	private boolean				isNotEmpty;

	/** ************************************** */
	/**
	 * 标签类型 div span 默认 div
	 */
	private String				tagType				= "div";

	/**
	 * 需不需要解密 默认false
	 */
	private String				toDecrypt			= "false";

	@Override
	public int doStartTag(){
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doAfterBody() throws JspException{
		String content = bodyContent.getString();
		isNotEmpty = Validator.isNotNullOrEmpty(content);
		// 有内容
		if (isNotEmpty){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("<" + tagType);
			innerCommonAttribute(stringBuilder);
			stringBuilder.append(">");
			// 需不需要解密
			if ("true".equals(toDecrypt)){
				//content = FeiLongSecurity.getDecryptParam(content);
			}
			stringBuilder.append(content);
			TagUtils.getInstance().writePrevious(pageContext, stringBuilder.toString());
			bodyContent.clearBody();
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag(){
		if (isNotEmpty){
			println("</" + tagType + ">");
		}
		return EVAL_PAGE;
	}

	/**
	 * @param tagType
	 *            the tagType to set
	 */
	public void setTagType(String tagType){
		this.tagType = tagType;
	}

	/**
	 * @param toDecrypt
	 *            the toDecrypt to set
	 */
	public void setToDecrypt(String toDecrypt){
		this.toDecrypt = toDecrypt;
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
