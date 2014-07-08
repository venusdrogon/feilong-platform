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
package com.feilong.commons.security.oneway;

/**
 * Message Digest algorithm 5，信息摘要算法 <br>
 * 将任意长度的"字节串"变换成一个128bit的大整数.
 * 
 * <pre>
 * 检验你的实现是否正确：
 * MD5Util.encode(&quot;&quot;) = d41d8cd98f00b204e9800998ecf8427e
 * MD5Util.encode(&quot;a&quot;) = 0cc175b9c0f1b6a831c399e269772661
 * MD5Util.encode(&quot;abc&quot;) = 900150983cd24fb0d6963f7d28e17f72
 * MD5Util.encode(&quot;message digest&quot;) = f96b697d7cb7938d525a2f31aaf161d0
 * MD5Util.encode(&quot;abcdefghijklmnopqrstuvwxyz&quot;) = c3fcd3d76192e4007dfb496cca67e13b
 * </pre>
 * 
 * @author 腾讯通
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010年10月26日 17:13:58
 * @version 1.1 2011-10-18 16:49
 * @see <a href="http://www.cmd5.com/">MD5解密网站</a>
 * @since 1.0
 * @see AbstractOnewayEncryption
 * @see OnewayType
 */
public final class MD5Util extends AbstractOnewayEncryption{

	/**
	 * Instantiates a new m d5 util.
	 */
	private MD5Util(){}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.commons.core.security.oneway.OnewayEncryption#getOnewayType()
	 */
	protected OnewayType getOnewayType(){
		return OnewayType.MD5;
	}
}