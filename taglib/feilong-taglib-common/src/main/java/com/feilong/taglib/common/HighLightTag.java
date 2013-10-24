package com.feilong.taglib.common;

import com.feilong.taglib.base.AbstractCommonTag;
import com.feilong.tools.html.HTMLSpan;

/**
 * 关键字高亮显示标签
 * 
 * @author 金鑫 2009-11-11上午10:36:28
 */
@Deprecated
public class HighLightTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 一串文字
	 */
	private String				content;

	/**
	 * 需要高亮显示的文字
	 */
	private String				highLight;

	/**
	 * 高亮字体颜色
	 */
	private String				highLightColor;

	@Override
	public String writeContent(){
		return HTMLSpan.getHighLight(content, highLight, highLightColor);
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content){
		this.content = content;
	}

	/**
	 * @param highLight
	 *            the highLight to set
	 */
	public void setHighLight(String highLight){
		this.highLight = highLight;
	}

	/**
	 * @param highLightColor
	 *            the highLightColor to set
	 */
	public void setHighLightColor(String highLightColor){
		this.highLightColor = highLightColor;
	}
}
