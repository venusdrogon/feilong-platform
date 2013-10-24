package com.feilong.taglib.html;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.ParamUtil;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.taglib.base.BaseHtmlTag;
import com.feilong.tools.html.HtmlUtil;

/**
 * 自定义select标签 HtmlSelectTag
 * 
 * @author 金鑫 2009-12-4下午05:59:36
 * @deprecated
 */
public class HtmlSelectTag extends BaseHtmlTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * name
	 */
	private String				name;

	/**
	 * 值
	 */
	private String				value;

	/**
	 * 默认选中,当不存在参数且没有value的时候 该选项选中
	 * 
	 * <pre>
	 * 优先级顺序:
	 * value paramNameValue defaultValue
	 * 有value 则使用value
	 * 没有value 使用paramNameValue
	 * 当paramName不存在 则使用defaultValue
	 * </pre>
	 */
	private Object				defaultValue;

	/**
	 * 下拉选项 形式如
	 * 
	 * <pre>
	 * 	&lt;option value=&quot;1&quot;&gt;正常&lt;/option&gt;
	 * 	&lt;option value=&quot;2&quot;&gt;暂停&lt;/option&gt;
	 * 转成1=正常&amp;2=暂停  value在前 label在后
	 * </pre>
	 */
	private String				valueAndTexts;

	/**
	 * 作用域里面的值 jobYearList 默认request范围,valueAndTexts,scopeOptions必须要有一个或者两个都有
	 * 
	 * <pre>
	 * 
	 * 若scopeOptions采用beanName+List后缀命名,则scopeLabel和scopeValue可以省略,自动取到beanName并添加&quot;Id&quot;和&quot;Name&quot;名称
	 * 
	 * 支持jdbc 采用的Result 类型,
	 * </pre>
	 */
	private String				scopeOptions;

	/**
	 * 作用域里面的对象显示的字段.
	 * 
	 * <pre>
	 * 
	 * 若scopeOptions采用beanName+List后缀命名,则scopeLabel和scopeValue可以省略,自动取到beanName并添加&quot;Id&quot;和&quot;Name&quot;名称
	 * </pre>
	 */
	private String				scopeLabel;

	/**
	 * 作用域里面的对象隐藏值
	 * 
	 * <pre>
	 * 
	 * 若scopeOptions采用beanName+List后缀命名,则scopeLabel和scopeValue可以省略,自动取到beanName并添加&quot;Id&quot;和&quot;Name&quot;名称
	 * </pre>
	 */
	private String				scopeValue;

	@Override
	public String writeContent(){
		HttpServletRequest request = getHttpServletRequest();
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append("<select");
		// 添加公共的属性 id name 等
		innerCommonAttribute(stringBuilder);
		if (Validator.isNotNullOrEmpty(name)){
			HtmlUtil.addAttribute(stringBuilder, "name", name);
		}
		if (Validator.isNotNullOrEmpty(value)){
			HtmlUtil.addAttribute(stringBuilder, "value", value);
		}
		stringBuilder.append(">");
		Map<String, String> map = null;
		// valueAndTexts
		if (Validator.isNotNullOrEmpty(valueAndTexts)){
			// TODO
			// map = ParamUtil.convertParametersToMap(valueAndTexts, CharsetType.UTF8);
		}
		// scopeOptions
		if (Validator.isNotNullOrEmpty(scopeOptions)){
			// map = RequestUtil.collectionToMap(map, scopeOptions, scopeLabel, scopeValue, request);
		}
		/**
		 * 参数值
		 */
		String nameValue = "";
		if (Validator.isNotNullOrEmpty(name)){
			nameValue = request.getParameter(name);
		}
		/**
		 * 默认值
		 */
		String defaultValueString = "";
		if (Validator.isNotNullOrEmpty(defaultValue)){
			defaultValueString = defaultValue.toString();
		}
		String selectedString = " selected=\"selected\"";
		if (Validator.isNotNullOrEmpty(map)){
			for (Map.Entry<String, String> entry : map.entrySet()){
				stringBuilder.append("<option");
				if (Validator.isNotNullOrEmpty(value)){
					// 选中状态
					if (value.equals(entry.getKey())){
						stringBuilder.append(selectedString);
					}
				}else{
					// 带不带这个名字参数
					if (RequestUtil.isContainsParam(request, name)){
						if (Validator.isNotNullOrEmpty(nameValue)){
							// 默认选中参数值
							if (nameValue.equals(entry.getKey())){
								stringBuilder.append(selectedString);
							}
						}
					}else if (Validator.isNotNullOrEmpty(defaultValueString)){
						// 默认选中参数值
						if (defaultValueString.equals(entry.getKey())){
							stringBuilder.append(selectedString);
						}
					}
				}
				HtmlUtil.addAttribute(stringBuilder, "value", entry.getKey());
				stringBuilder.append(">");
				stringBuilder.append(entry.getValue());
				stringBuilder.append("</option>");
			}
		}
		stringBuilder.append("</select>");
		return stringBuilder.toString();
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value){
		this.value = value;
	}

	/**
	 * @param defaultValue
	 *            the defaultValue to set
	 */
	public void setDefaultValue(Object defaultValue){
		this.defaultValue = defaultValue;
	}

	/**
	 * @param scopeLabel
	 *            the scopeLabel to set
	 */
	public void setScopeLabel(String scopeLabel){
		this.scopeLabel = scopeLabel;
	}

	/**
	 * @param scopeValue
	 *            the scopeValue to set
	 */
	public void setScopeValue(String scopeValue){
		this.scopeValue = scopeValue;
	}

	/**
	 * @param valueAndTexts
	 *            the valueAndTexts to set
	 */
	public void setValueAndTexts(String valueAndTexts){
		this.valueAndTexts = valueAndTexts;
	}

	/**
	 * @param scopeOptions
	 *            the scopeOptions to set
	 */
	public void setScopeOptions(String scopeOptions){
		this.scopeOptions = scopeOptions;
	}
}
