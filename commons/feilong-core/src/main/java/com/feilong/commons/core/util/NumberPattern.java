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
package com.feilong.commons.core.util;

import java.text.DecimalFormat;

/**
 * 数字格式<br>
 * <blockquote>
 * <table border=0 cellspacing=3 cellpadding=0 summary="Chart showing symbol, * location, localized, and meaning.">
 * <tr bgcolor="#ccccff">
 * <th align=left>Symbol
 * <th align=left>Location
 * <th align=left>Localized?
 * <th align=left>Meaning
 * <tr valign=top>
 * <td><code>0</code>
 * <td>Number
 * <td>Yes
 * <td>Digit
 * <tr valign=top bgcolor="#eeeeff">
 * <td><code>#</code>
 * <td>Number
 * <td>Yes
 * <td>Digit, zero shows as absent
 * <tr valign=top>
 * <td><code>.</code>
 * <td>Number
 * <td>Yes
 * <td>Decimal separator or monetary decimal separator
 * <tr valign=top bgcolor="#eeeeff">
 * <td><code>-</code>
 * <td>Number
 * <td>Yes
 * <td>Minus sign
 * <tr valign=top>
 * <td><code>,</code>
 * <td>Number
 * <td>Yes
 * <td>Grouping separator
 * <tr valign=top bgcolor="#eeeeff">
 * <td><code>E</code>
 * <td>Number
 * <td>Yes
 * <td>Separates mantissa and exponent in scientific notation. <em>Need not be quoted in prefix or suffix.</em>
 * <tr valign=top>
 * <td><code>;</code>
 * <td>Subpattern boundary
 * <td>Yes
 * <td>Separates positive and negative subpatterns
 * <tr valign=top bgcolor="#eeeeff">
 * <td><code>%</code>
 * <td>Prefix or suffix
 * <td>Yes
 * <td>Multiply by 100 and show as percentage
 * <tr valign=top>
 * <td><code>&#92;u2030</code>
 * <td>Prefix or suffix
 * <td>Yes
 * <td>Multiply by 1000 and show as per mille value
 * <tr valign=top bgcolor="#eeeeff">
 * <td><code>&#164;</code> (<code>&#92;u00A4</code>)
 * <td>Prefix or suffix
 * <td>No
 * <td>Currency sign, replaced by currency symbol. If doubled, replaced by international currency symbol. If present in a pattern, the
 * monetary decimal separator is used instead of the decimal separator.
 * <tr valign=top>
 * <td><code>'</code>
 * <td>Prefix or suffix
 * <td>No
 * <td>Used to quote special characters in a prefix or suffix, for example, <code>"'#'#"</code> formats 123 to <code>"#123"</code>. To
 * create a single quote itself, use two in a row: <code>"# o''clock"</code>.
 * </table>
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-6 下午8:33:23
 * @see {@link DecimalFormat}
 */
public final class NumberPattern{

	/** 百分数的表达式(2位小数点) <code>{@value}</code>. */
	public static final String	percent_with_2Point		= "#0.00%";

	/** 百分数的表达式(无小数点) <code>{@value}</code>. */
	public static final String	percent_with_noPoint	= "##%";
}
