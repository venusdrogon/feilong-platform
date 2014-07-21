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
package com.feilong.framework.bind.parse.varcommand;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标识QueryResult 字段和 wddxPacket xml 一对一的关系,简化 减少hard coding.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014年5月5日 下午8:27:13
 */
// 表示产生文档，比如通过javadoc产生文档, 将此注解包含在 javadoc 中, 这个Annotation可以被写入javadoc
@Documented
// RetentionPolicy.RUNTIME 在jvm加载class时候有效, VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息
@Retention(RetentionPolicy.RUNTIME)
// ElemenetType.FIELD 字段, 域声明（包括 enum 实例）
@Target({ ElementType.FIELD })
@Inherited
public @interface VarName{

	/**
	 * 对应xml里面的名称.
	 * 
	 * @return the string
	 */
	String name();

	/**
	 * 示例结果(仅供查看的时候,知道这个字段的结果值格式和大致的值,没有其他作用).
	 * 
	 * @return the string
	 */
	String sampleValue() default "";
}
