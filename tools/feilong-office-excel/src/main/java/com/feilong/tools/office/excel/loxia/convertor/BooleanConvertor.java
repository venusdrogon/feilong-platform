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
package com.feilong.tools.office.excel.loxia.convertor;

import loxia.support.excel.convertor.DataConvertor;
import loxia.support.excel.definition.ExcelCell;
import loxia.support.excel.exception.ErrorCode;
import loxia.support.excel.exception.ExcelManipulateException;

import org.apache.commons.lang3.BooleanUtils;

/**
 * boolean转型转换器，用于loxia excel 类型转换注册使用 ，转换Boolean 类型的值<br>
 * 
 * <pre>
 * {@code
 * 		使用方式: 
 * 		 DataConvertorConfigurator.getInstance().registerDataConvertor(new BooleanConvertor());
 * }
 * </pre>
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月20日 下午8:54:47
 * @since 1.0.8
 */
public class BooleanConvertor implements DataConvertor<Boolean>{

	/*
	 * (non-Javadoc)
	 * 
	 * @see loxia.support.excel.convertor.DataConvertor#convert(java.lang.Object, int, java.lang.String,
	 * loxia.support.excel.definition.ExcelCell)
	 */
	public Boolean convert(Object value,int sheetNo,String cellIndex,ExcelCell cellDefinition) throws ExcelManipulateException{
		if (value == null && cellDefinition.isMandatory()){
			throw new ExcelManipulateException(ErrorCode.WRONG_DATA_NULL, new Object[] {
					sheetNo + 1,
					cellIndex,
					null,
					cellDefinition.getPattern(),
					cellDefinition.getChoiceString() });
		}

		if (value == null){
			return null;
		}

		//*************************************************************************************

		if (value instanceof Boolean){
			return (Boolean) value;
		}else if (value instanceof String){
			String str = (String) value;
			return BooleanUtils.toBooleanObject(str);
		}else if (value instanceof Double){
			Double value2 = (Double) value;
			return BooleanUtils.toBooleanObject(value2.intValue());
		}else
			throw new ExcelManipulateException(ErrorCode.WRONG_DATA_FORMAT, new Object[] {
					sheetNo + 1,
					cellIndex,
					value,
					cellDefinition.getPattern(),
					cellDefinition.getChoiceString() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see loxia.support.excel.convertor.DataConvertor#getDataTypeAbbr()
	 */
	public String getDataTypeAbbr(){
		return "boolean";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see loxia.support.excel.convertor.DataConvertor#supportClass()
	 */
	public Class<Boolean> supportClass(){
		return Boolean.class;
	}
}
