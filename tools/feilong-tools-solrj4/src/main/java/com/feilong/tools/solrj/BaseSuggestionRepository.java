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

package com.feilong.tools.solrj;

import java.util.List;

import com.feilong.tools.solrj.paramscommand.SpellingParamCommand;

/**
 * 搜索联想.
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
	 *            the spelling param command
	 * @return the list
	 */
	List<String> findSuggestionLexeme(SpellingParamCommand spellingParamCommand);
}
