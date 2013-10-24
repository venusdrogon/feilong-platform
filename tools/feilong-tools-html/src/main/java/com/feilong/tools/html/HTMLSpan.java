package com.feilong.tools.html;

import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.html.entity.HtmlSpanEntity;

/**
 * 专门用于后台生成html span代码的类
 * 
 * @author 金鑫 2010年5月29日 00:46:26
 */
public final class HTMLSpan{

	/**
	 * 创建span标签
	 * 
	 * @param content
	 *            内容
	 * @param className
	 *            class名称
	 * @return span标签
	 */
	public static String createSpan(Object content,String className){
		if (Validator.isNullOrEmpty(content)){
			return "";
		}
		return createSpan(content, className, null);
	}

	/**
	 * 创建span标签
	 * 
	 * @param content
	 *            内容
	 * @param className
	 *            class名称
	 * @param title
	 *            title
	 * @return span标签
	 */
	public static String createSpan(Object content,String className,String title){
		if (Validator.isNullOrEmpty(content)){
			return "";
		}
		HtmlSpanEntity htmlSpanEntity = new HtmlSpanEntity();
		htmlSpanEntity.setContent(content.toString());
		htmlSpanEntity.setTitle(title);
		htmlSpanEntity.setStyleClass(className);
		return createSpan(htmlSpanEntity);
	}

	/**
	 * 创建span标签
	 * 
	 * @param htmlSpanEntity
	 *            htmlSpanEntity
	 * @return 创建span标签
	 */
	public static String createSpan(HtmlSpanEntity htmlSpanEntity){
		if (null == htmlSpanEntity || htmlSpanEntity.isEmpty()){
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder("<span");
		HtmlUtil.addAttribute(stringBuilder, "class", htmlSpanEntity.getStyleClass());
		HtmlUtil.addAttribute(stringBuilder, "title", htmlSpanEntity.getTitle());
		stringBuilder.append(">");
		stringBuilder.append(htmlSpanEntity.getContent());
		stringBuilder.append("</span>");
		return stringBuilder.toString();
	}

	/**
	 * 统计后的返回值
	 * 
	 * @param count
	 *            数值
	 * @return span标签
	 */
	public static String createSpan_If0(int count){
		String colorClass = "";
		if (0 == count){
			colorClass = "color_666";
		}else{
			colorClass = "color_red";
		}
		return createSpan(count, colorClass);
	}

	/**
	 * 后台返回提示:无
	 * 
	 * @return 拼装好的无字符串
	 */
	public static String getWu(){
		return createSpan("无", "color_f50");
	}

	/**
	 * 数字添加颜色 (增加默认的class类)
	 * 
	 * @param value1
	 *            数字
	 * @return 添加样式之后的数据字符串
	 */
	public static String createSpan_decimalAddColor(Number value1){
		StringBuilder sBuilder = new StringBuilder();
		int value = value1.intValue();
		String className = "";
		if (value == 0){
			className = "decimal_0";
		}else if (value < 100){
			className = "decimal_deault";
		}else if (value >= 100 && value < 200){
			className = "decimal_100";
		}else if (value >= 200 && value < 300){
			className = "decimal_200";
		}else if (value >= 300 && value < 500){
			className = "decimal_300";
		}else if (value >= 500 && value < 6000){
			className = "decimal_500";
		}else if (value >= 6000){
			className = "decimal_6000";
		}
		sBuilder.append(createSpan(value, className));
		return sBuilder.toString();
	}

	/**
	 * 高亮显示
	 * 
	 * @param content
	 *            内容
	 * @param highLight
	 *            高亮文字
	 * @param highLightColor
	 *            高亮颜色
	 * @return 拼装好的高亮显示的字符串
	 */
	public static String getHighLight(String content,String highLight,String highLightColor){
		// 高亮文字不是空
		if (Validator.isNotNullOrEmpty(highLight)){
			String default_color = "#FB7E1E";
			highLight = URIUtil.decodeLuanMa_ISO8859(highLight);
			if (Validator.isNotNullOrEmpty(highLightColor)){
				default_color = highLightColor;
			}
			String replaceString = "<span style='color:" + default_color + "'>" + highLight + "</span>";
			return StringUtil.replaceAll(content, highLight, replaceString);
		}
		return content;
	}
}
