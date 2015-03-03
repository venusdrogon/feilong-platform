package com.feilong.commons.db;

import java.util.List;

import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.commons.core.util.Validator;

/**
 * jdbc的工具类
 * 
 * @author 金鑫 2010-7-9 上午11:55:22
 */
public class JdbcUtil{

	/**
	 * 简单的存储过程 转换成完整的存储过程名称
	 * 
	 * <pre>
	 * selectRepeate----&gt;{call dbo.selectRepeate(?,?)}
	 * </pre>
	 * 
	 * @param simpleProcName
	 *            简单的存储过程名称
	 * @param paramsList
	 *            参数列表
	 * @return 完整的存储过程名称
	 * @author 金鑫
	 * @version 1.0 2010-9-1 下午02:48:27
	 */
	public static String simpleProcNameToProcName(String simpleProcName,List<Object> paramsList){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{call dbo.");
		stringBuilder.append(simpleProcName);
		stringBuilder.append("(");
		if (Validator.isNotNullOrEmpty(paramsList)){
			for (int i = 0, j = paramsList.size(); i < j; i++){
				stringBuilder.append("?");
				if (i != j - 1){
					stringBuilder.append(",");
				}
			}
		}
		stringBuilder.append(")");
		stringBuilder.append("}");
		return stringBuilder.toString();
	}

	/**
	 * 简单的存储过程 转换成完整的存储过程名称
	 * 
	 * <pre>
	 * selectRepeate----&gt;{call dbo.selectRepeate(?,?)}
	 * </pre>
	 * 
	 * @param simpleProcName
	 *            简单的存储过程名称
	 * @param params
	 *            参数列表
	 * @return 完整的存储过程名称
	 * @author 金鑫
	 * @version 1.0 2010-9-1 下午03:05:20
	 */
	public static String simpleProcNameToProcName(String simpleProcName,Object...params){
		return simpleProcNameToProcName(simpleProcName, ArrayUtil.toList(params));
	}
}
