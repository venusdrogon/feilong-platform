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
package com.feilong.hibernate.search.bridge;

import java.util.Map;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

import com.feilong.hibernate.search.model.master.SkuDynaPropSetting;

/**
 * The Class SkuDynaPropertyBridge.
 */
public class SkuDynaPropertyBridge implements FieldBridge{

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.search.bridge.FieldBridge#set(java.lang.String, java.lang.Object, org.apache.lucene.document.Document,
	 * org.hibernate.search.bridge.LuceneOptions)
	 */
	@SuppressWarnings("unchecked")
	public void set(String name,Object obj,Document document,LuceneOptions luceneOptions){
		Map<SkuDynaPropSetting, String> value = (Map<SkuDynaPropSetting, String>) obj;
		StringBuffer sb = new StringBuffer();
		for (SkuDynaPropSetting sdps : value.keySet()){
			//add dyna prop
			String str = value.get(sdps);
			str = (str == null ? "" : str);
			sb.append(str + " ");
		}
		luceneOptions.addFieldToDocument(name, sb.toString().trim(), document);
	}
}
