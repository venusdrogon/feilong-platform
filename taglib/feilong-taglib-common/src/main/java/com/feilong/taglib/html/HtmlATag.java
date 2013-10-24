package com.feilong.taglib.html;

import com.feilong.taglib.base.BaseHtmlTag;
import com.feilong.tools.html.HTMLA;
import com.feilong.tools.html.HTMLSpan;
import com.feilong.tools.html.entity.HtmlAEntity;

/**
 * 存放a标签属性
 * 
 * @author 金鑫 2010-5-6 上午10:01:46
 * @deprecated
 */
public class HtmlATag extends BaseHtmlTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 是否可用
	 */
	protected String			enable				= "true";

	/**
	 * 文字
	 */
	protected String			text;

	/**
	 * 路径
	 */
	protected String			href;

	/**
	 * 打开方式
	 */
	protected String			target				= "_top";

	@Override
	protected Object writeContent(){
		if ("false".equals(enable)){
			return HTMLSpan.createSpan(text, "color_666", title);
		}
		HtmlAEntity a = new HtmlAEntity();
		a.setTarget(target);
		a.setHref(href);
		a.setText(text);
		a.setTitle(title);
		a.setStyle(style);
		a.setStyleClass(styleClass);
		a.setStyleId(styleId);
		return HTMLA.createA(a);
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(String enable){
		this.enable = enable;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text){
		this.text = text;
	}

	/**
	 * @param href
	 *            the href to set
	 */
	public void setHref(String href){
		this.href = href;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target){
		this.target = target;
	}
}
