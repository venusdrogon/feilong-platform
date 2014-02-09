/**
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
package com.feilong.tools.om.nginx.comparator;

import java.util.Comparator;

import com.feilong.tools.om.nginx.command.StubStatusCommand;

/**
 * The Class ActiveConnectionsComparator.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 6, 2014 1:43:04 AM
 */
public class ActiveConnectionsComparator implements Comparator<StubStatusCommand>{

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(StubStatusCommand o1,StubStatusCommand o2){
		if (o1.getActiveConnections() == o2.getActiveConnections()){
			return 0;
		}else if (o1.getActiveConnections() < o2.getActiveConnections()){
			return -1;
		}
		return 1;
	}
}