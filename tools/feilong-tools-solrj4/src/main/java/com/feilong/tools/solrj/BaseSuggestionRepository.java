/**
 * Copyright (c) 2010 Jumbomart All Rights Reserved.

 *
 * This software is the confidential and proprietary information of Jumbomart.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jumbo.
 *
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.feilong.tools.solrj;

import java.util.List;

import com.feilong.tools.solrj.paramscommand.SpellingParamCommand;

/**
 * 搜索联想
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-11-29 15:53
 */
public interface BaseSuggestionRepository{

	/**
	 * 根据spellingParamCommand 查找推荐的词素list<br>
	 * 名词Lexeme 来源于 org.wltea.analyzer.Lexeme
	 * 
	 * @param spellingParamCommand
	 * @return
	 */
	List<String> findSuggestionLexeme(SpellingParamCommand spellingParamCommand);
}
