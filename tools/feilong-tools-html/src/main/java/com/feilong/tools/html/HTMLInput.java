package com.feilong.tools.html;

/**
 * input
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-10-27 下午03:09:44
 */
public class HTMLInput{

	/**
	 * 创建input标签
	 * 
	 * @param type
	 *            类型
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return 创建input标签
	 */
	public static String createInputTag(String type,String name,String value){
		StringBuilder builder = new StringBuilder();
		builder.append("<input ");
		builder.append("type=\"" + type + "\" ");
		builder.append("name=\"" + name + "\" ");
		builder.append("value=\"" + value + "\"");
		builder.append("/>");
		return builder.toString();
	}
}
