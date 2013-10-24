package com.feilong.tools.html.entity;

/**
 * a标签实体
 * 
 * @author 金鑫 2010-1-3 上午10:49:47
 */
public class HtmlAEntity extends BaseHTMLEntity{

	public static final String	target_blank	= "_blank";

	public static final String	target_self		= "_self";

	public static final String	target_top		= "_top";

	public static final String	target_parent	= "_parent";

	/**
	 * 链接
	 */
	private String				href;

	/**
	 * 打开方式
	 */
	private String				target;

	/**
	 * 文本
	 */
	private Object				text;

	/**
	 * @return the href
	 */
	public String getHref(){
		return href;
	}

	/**
	 * @param href
	 *            the href to set
	 */
	public void setHref(String href){
		this.href = href;
	}

	/**
	 * @return the target
	 */
	public String getTarget(){
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target){
		this.target = target;
	}

	public Object getText(){
		return text;
	}

	public void setText(Object text){
		this.text = text;
	}
}
