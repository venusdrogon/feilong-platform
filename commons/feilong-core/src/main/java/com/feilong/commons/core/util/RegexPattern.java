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
package com.feilong.commons.core.util;

import java.util.regex.Pattern;

/**
 * 正则表达式格式,内置常用正则表达式
 * 
 * 
 * <h3>开始结束</h3>
 * 
 * <blockquote>
 * <table border="1" cellspacing="0" cellpadding="4">
 * <tr bgcolor="#ccccff">
 * <th align=left>字段</th>
 * <th align=left>说明</th>
 * </tr>
 * <tr valign=top>
 * <td>\</td>
 * <td>将下一个字符标记为一个特殊字符、或一个原义字符、或一个 向后引用、或一个八进制转义符.<br>
 * 例如，'n' 匹配字符 "n".'\n' 匹配一个换行符.序列 '\\' 匹配 "\" 而 "\(" 则匹配 "(".</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>^</td>
 * <td>匹配输入字符串的开始位置.如果设置了 RegExp 对象的 Multiline 属性，^ 也匹配 '\n' 或 '\r' 之后的位置.</td>
 * </tr>
 * <tr valign=top>
 * <td>$</td>
 * <td>匹配输入字符串的结束位置.如果设置了RegExp 对象的 Multiline 属性，$ 也匹配 '\n' 或 '\r' 之前的位置.</td>
 * </tr>
 * 
 * </table>
 * </blockquote>
 * 
 * 
 * 
 * <h3>次数</h3>
 * 
 * <blockquote>
 * <table border="1" cellspacing="0" cellpadding="4">
 * <tr bgcolor="#ccccff">
 * <th align=left>字段</th>
 * <th align=left>说明</th>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>*</td>
 * <td>匹配前面的子表达式 <b>零次</b> 或 <b>多次</b>.<br>
 * 例如，zo* 能匹配 "z" 以及 "zoo".* 等价于{0,}.</td>
 * </tr>
 * <tr valign=top>
 * <td>+</td>
 * <td>匹配前面的子表达式 <b>一次</b> 或 <b>多次</b>.<br>
 * 例如，'zo+' 能匹配 "zo" 以及 "zoo"，但不能匹配 "z".+ 等价于 {1,}.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>?</td>
 * <td>匹配前面的子表达式<b> 零次</b> 或 <b>一次</b>.<br>
 * 例如，"do(es)?" 可以匹配 "do" 或 "does" 中的"do" .? 等价于 {0,1}.</td>
 * </tr>
 * 
 * </table>
 * </blockquote>
 * 
 * 
 * 
 * <h3>简写</h3>
 * 
 * <blockquote>
 * <table border="1" cellspacing="0" cellpadding="4">
 * <tr bgcolor="#ccccff">
 * <th align=left>字段</th>
 * <th align=left>说明</th>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>\d</td>
 * <td>匹配一个数字字符.等价于 [0-9].</td>
 * </tr>
 * <tr valign=top>
 * <td>\D</td>
 * <td>匹配一个非数字字符.等价于 [^0-9].</td>
 * </tr>
 * 
 * <tr valign=top>
 * <td>\s</td>
 * <td>匹配任何空白字符，包括空格、制表符、换页符等等.等价于 [ \f\n\r\t\v].</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>\S</td>
 * <td>匹配任何非空白字符.等价于 [^ \f\n\r\t\v].</td>
 * </tr>
 * 
 * <tr valign=top>
 * <td>\w</td>
 * <td>匹配包括下划线的任何单词字符.等价于'[A-Za-z0-9_]'.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>\W</td>
 * <td>匹配任何非单词字符.等价于 '[^A-Za-z0-9_]'.</td>
 * </tr>
 * </table>
 * </blockquote>
 * 
 * 
 * <h3>特殊</h3>
 * 
 * <blockquote>
 * <table border="1" cellspacing="0" cellpadding="4">
 * <tr bgcolor="#ccccff">
 * <th align=left>字段</th>
 * <th align=left>说明</th>
 * </tr>
 * <tr valign=top>
 * <td>\n</td>
 * <td>匹配一个换行符.等价于 \x0a 和 \cJ.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>\r</td>
 * <td>匹配一个回车符.等价于 \x0d 和 \cM.</td>
 * </tr>
 * <tr valign=top>
 * <td>\t</td>
 * <td>匹配一个制表符.等价于 \x09 和 \cI.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>\v</td>
 * <td>匹配一个垂直制表符.等价于 \x0b 和 \cK.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>\f</td>
 * <td>匹配一个换页符.等价于 \x0c 和 \cL.</td>
 * </tr>
 * </table>
 * </blockquote>
 * 
 * 
 * <h3>全部符号解释</h3>
 * 
 * <blockquote>
 * <table border="1" cellspacing="0" cellpadding="4">
 * <tr bgcolor="#ccccff">
 * <th align=left>字段</th>
 * <th align=left>说明</th>
 * </tr>
 * 
 * <tr valign=top>
 * <td>{n}</td>
 * <td>n 是一个非负整数.匹配确定的 n 次.例如，'o{2}' 不能匹配 "Bob" 中的 'o'，但是能匹配 "food" 中的两个 o.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>{n,}</td>
 * <td>n 是一个非负整数.至少匹配n 次.例如，'o{2,}' 不能匹配 "Bob" 中的 'o'，但能匹配 "foooood" 中的所有 o.'o{1,}' 等价于 'o+'.'o{0,}' 则等价于 'o*'.</td>
 * </tr>
 * <tr valign=top>
 * <td>{n,m}</td>
 * <td>m 和 n 均为非负整数，其中 {@code n <= m}.最少匹配 n 次且最多匹配 m 次.例如，"o{1,3}" 将匹配 "fooooood" 中的前三个 o.'o{0,1}' 等价于 'o?'.请注意在逗号和两个数之间不能有空格.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>?</td>
 * <td>当该字符紧跟在任何一个其他限制符 (*, +, ?, {n}, {n,}, {n,m}) 后面时，匹配模式是非贪婪的.非贪婪模式尽可能少的匹配所搜索的字符串，而默认的贪婪模式则尽可能多的匹配所搜索的字符串.例如，对于字符串 "oooo"，'o+?' 将匹配单个
 * "o"，而 'o+' 将匹配所有 'o'.</td>
 * </tr>
 * <tr valign=top>
 * <td>.</td>
 * <td>匹配除 "\n" 之外的任何单个字符.要匹配包括 '\n' 在内的任何字符，请使用象 '[.\n]' 的模式.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>(pattern)</td>
 * <td>匹配 pattern 并获取这一匹配.所获取的匹配可以从产生的 Matches 集合得到，在VBScript 中使用 SubMatches 集合，在JScript 中则使用 $0…$9 属性.要匹配圆括号字符，请使用 '\(' 或 '\)'.</td>
 * </tr>
 * <tr valign=top>
 * <td>(?:pattern)</td>
 * <td>匹配 pattern 但不获取匹配结果，也就是说这是一个非获取匹配，不进行存储供以后使用.这在使用 "或" 字符 (|) 来组合一个模式的各个部分是很有用.例如， 'industr(?:y|ies) 就是一个比 'industry|industries'
 * 更简略的表达式.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>(?=pattern)</td>
 * <td>正向预查，在任何匹配 pattern 的字符串开始处匹配查找字符串.这是一个非获取匹配，也就是说，该匹配不需要获取供以后使用.例如，'Windows (?=95|98|NT|2000)' 能匹配 "Windows 2000" 中的 "Windows" ，但不能匹配
 * "Windows 3.1" 中的 "Windows".预查不消耗字符，也就是说，在一个匹配发生后，在最后一次匹配之后立即开始下一次匹配的搜索，而不是从包含预查的字符之后开始.</td>
 * </tr>
 * <tr valign=top>
 * <td>(?!pattern)</td>
 * <td>负向预查，在任何不匹配 pattern 的字符串开始处匹配查找字符串.这是一个非获取匹配，也就是说，该匹配不需要获取供以后使用.例如'Windows (?!95|98|NT|2000)' 能匹配 "Windows 3.1" 中的 "Windows"，但不能匹配
 * "Windows 2000" 中的 "Windows".预查不消耗字符，也就是说，在一个匹配发生后，在最后一次匹配之后立即开始下一次匹配的搜索，而不是从包含预查的字符之后开始</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>x|y</td>
 * <td>匹配 x 或 y.例如，'z|food' 能匹配 "z" 或 "food".'(z|f)ood' 则匹配 "zood" 或 "food".</td>
 * </tr>
 * <tr valign=top>
 * <td>[xyz]</td>
 * <td>字符集合.匹配所包含的任意一个字符.例如， '[abc]' 可以匹配 "plain" 中的 'a'.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>[^xyz]</td>
 * <td>负值字符集合.匹配未包含的任意字符.例如， '[^abc]' 可以匹配 "plain" 中的'p'.</td>
 * </tr>
 * <tr valign=top>
 * <td>[a-z]</td>
 * <td>字符范围.匹配指定范围内的任意字符.例如，'[a-z]' 可以匹配 'a' 到 'z' 范围内的任意小写字母字符.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>[^a-z]</td>
 * <td>负值字符范围.匹配任何不在指定范围内的任意字符.例如，'[^a-z]' 可以匹配任何不在 'a' 到 'z' 范围内的任意字符.</td>
 * </tr>
 * <tr valign=top>
 * <td>\b</td>
 * <td>匹配一个单词边界，也就是指单词和空格间的位置.例如， 'er\b' 可以匹配"never" 中的 'er'，但不能匹配 "verb" 中的 'er'.</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>\B</td>
 * <td>匹配非单词边界.'er\B' 能匹配 "verb" 中的 'er'，但不能匹配 "never" 中的 'er'.</td>
 * </tr>
 * <tr valign=top>
 * <td>\cx</td>
 * <td>匹配由 x 指明的控制字符.例如， \cM 匹配一个 Control-M 或回车符.x 的值必须为 A-Z 或 a-z 之一.否则，将 c 视为一个原义的 'c' 字符.</td>
 * </tr>
 * 
 * </table>
 * </blockquote>
 * 
 * 
 * 
 * <pre>
 * {@code
 * 1.字符类是可选自符的集合，用‘[’封装，比如[Jj],[0-9],[A-Za-z]或[^0-9].这里的-表示范围（Unicode落在两个边界之间的所有字符），^表示求补（指定字符外的所有字符 
 * 2.有许多预定以的字符类，像\d（数字）或\p&#123;Sc&#125;（Unicode货币符号），见表12-8和12-9. 
 * 3大多数字符与它们自身匹配，像上例中的java字符. 
 * 4.符号.匹配任何字符（可能行终止符（line terminators）除外，这依赖于标识设置（flag settings）） 
 * 5.\用作转义符，比如\.匹配一个句点，\\匹配一个反斜杠. 
 * 6.^和$分别匹配行头和行尾 
 * 7.如果X和Y都是正则表达式，则XY表示“X的匹配后面跟着Y的匹配”.X|Y表示“任何X或Y的匹配” 
 * 8.可以将量词（quantifier）用到表达式中，X+ 表示X重复1次或多次，X* 表示X重复0次或多次，X? 表示X重复0次或1次 
 * 9.默认地，一个量词总是与使总体成功匹配的最长的可能重复匹配.可以加上后缀？（称为reluctant或stingy 匹配，用以匹配最小的重复数），和+（称为possessive或贪婪匹配，用以即使在总体匹配失败的情况下也匹配最大的重复数）来更改这种属性. 
 * }
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 2, 2014 6:04:23 PM
 * @version 1.0.5 2014-5-4 00:37 change to interface
 * @see Pattern
 * @since 1.0.0
 */
public interface RegexPattern{

	/** email 的正则表达式 <code>{@value}</code>. */
	String	EMAIL				= "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

	/** IP 的正则表达式 <code>{@value}</code>. */
	String	IP					= "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";

	/** 电话号码 <code>{@value}</code>. */
	String	TELEPHONE			= "^(\\d{3,4}-)?\\d{6,8}$";

	/** 手机号码 <code>{@value}</code>. */
	String	MOBILEPHONE			= "^[1]+[3,5]+\\d{9}$";

	/** 网址Url 链接 <code>{@value}</code>. */
	String	URLLINK				= "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

	/** 邮政编码 <code>{@value}</code>. */
	String	ZIPCODE				= "^\\d{6}$";

	/** 所有都是字母 <code>{@value}</code>. */
	String	LETTER				= "^[A-Za-z]+$";

	/** 小写字母 <code>{@value}</code>. */
	String	LOWERCASELETTER		= "^[a-z]+$";

	/** 大写字母 <code>{@value}</code>. */
	String	UPPERCASELETTER		= "^[A-Z]+$";

	/**
	 * 两位数小数 <code>{@value}</code>
	 * 
	 * <pre>
	 * 可以是200 也可以是200.00 不可以是 200.0
	 * </pre>
	 */
	String	DECIMAL_TWODIGIT	= "^[0-9]+(.[0-9]{2})?$";

	/** 纯数字 <code>{@value}</code>. */
	String	NUMBER				= "^[0-9]*$";

	// alpha numeric space
	/** 字母和数字 (alpha numeric) <code>{@value}</code>. */
	String	AN					= "^[0-9a-zA-Z]+$";

	/** 字母和数字和空格(alpha numeric space)<code>{@value}</code>. */
	String	ANS					= "^[0-9a-zA-Z ]+$";

	// /** 验证输入一个月的31天 <code>{@value}</code>. */
	// String Day = "^((0?[1-9])|((1|2)[0-9])|30|31)$";

	// /** 验证输入一年的12个月 <code>{@value}</code>. */
	// String Month = "^(0?[[1-9]|1[0-2])$";

	// 严格验证时间格式的(匹配[2002-01-31], [1997-04-30], [2004-01-01])不匹配([2002-01-32], [2003-02-29], [04-01-01])
	// String regex =
	// "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((01,3-9])|(1[0-2]))-(29|30)))))$";
	// 没加时间验证的YYYY-MM-DD
	// String regex =
	// "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
	// 加了时间验证的YYYY-MM-DD 00:00:00
	// String Date =
	// "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

	// /** 非零的正整数 <code>{@value}</code>. */
	// String IntNumber = "^\\+?[1-9][0-9]*$";

}