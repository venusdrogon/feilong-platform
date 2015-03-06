/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.commons.db;

import java.util.List;

import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.commons.core.util.Validator;

/**
 * jdbc的工具类.
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
     * @author 金鑫
     * @version 1.0 2010-9-1 下午02:48:27
     * @param simpleProcName
     *            简单的存储过程名称
     * @param paramsList
     *            参数列表
     * @return 完整的存储过程名称
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
     * @author 金鑫
     * @version 1.0 2010-9-1 下午03:05:20
     * @param simpleProcName
     *            简单的存储过程名称
     * @param params
     *            参数列表
     * @return 完整的存储过程名称
     */
    public static String simpleProcNameToProcName(String simpleProcName,Object...params){
        return simpleProcNameToProcName(simpleProcName, ArrayUtil.toList(params));
    }
}
