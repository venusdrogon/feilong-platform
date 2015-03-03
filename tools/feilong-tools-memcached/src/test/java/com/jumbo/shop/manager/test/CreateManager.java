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
package com.jumbo.shop.manager.test;

/**
 * The Interface CreateManager.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 23, 2013 6:50:32 PM
 */
public interface CreateManager{

	/**
	 * Test create.
	 * 
	 * @param name
	 *            the name
	 * @return the string
	 */
	String testCreate(String name);

	/**
	 * Public sku.
	 * 
	 * @param name
	 *            the name
	 */
	void publicSku(String name);
}
