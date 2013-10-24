/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.tools.jfreechart.pie;

import java.util.Map;

import com.feilong.tools.jfreechart.BaseChartEntity;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 14 05:35:47
 */
public class PieChartEntity extends BaseChartEntity{

	private static final long		serialVersionUID	= -7723192818168964170L;

	/**
	 * 文字和 数据
	 * 
	 * <pre>
	 * Map&lt;String, Number&gt; keyAndDataMap = new LinkedHashMap&lt;String, Number&gt;();
	 * keyAndDataMap.put(&quot;失败率&quot;, 50);
	 * keyAndDataMap.put(&quot;成功率&quot;, 250);
	 * </pre>
	 */
	protected Map<String, Number>	keyAndDataMap;

	/**
	 * @return the keyAndDataMap
	 */
	public Map<String, Number> getKeyAndDataMap(){
		return keyAndDataMap;
	}

	/**
	 * @param keyAndDataMap
	 *            the keyAndDataMap to set
	 */
	public void setKeyAndDataMap(Map<String, Number> keyAndDataMap){
		this.keyAndDataMap = keyAndDataMap;
	}
}
