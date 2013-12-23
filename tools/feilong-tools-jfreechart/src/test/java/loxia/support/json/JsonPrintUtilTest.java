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
package loxia.support.json;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.toolkit.ClipboardUtil;
import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.tools.chart.index.ChartIndex;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 8, 2013 11:34:01 AM
 */
public class JsonPrintUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(JsonPrintUtilTest.class);

	/**
	 * /** Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.util.Collection, java.lang.String)}.
	 */
	@Test
	public final void testGetJSONArrayFormatStringCollectionOfQextendsObjectString(){
		List<ChartIndex> list = new ArrayList<ChartIndex>();
		list.add(new ChartIndex("网上支付－网银在线", 502));
		list.add(new ChartIndex("信用卡支付", 325));
		list.add(new ChartIndex("支付宝", 835));
		list.add(new ChartIndex("汇付天下", 1));

		String jsonArrayFormatString = JsonFormatUtil.format(list);
		System.out.println(jsonArrayFormatString);

		ClipboardUtil.setClipboardContent(jsonArrayFormatString);
	}

//	@Test
//	public final void testGetJSONArrayFormatStringCollectionOfQextendsObjectString2(){
//		List<ChartIndex> list = new ArrayList<ChartIndex>();
//		list.add(new ChartIndex("10:17", 1, 0));
//		list.add(new ChartIndex("10:36", 1, 0));
//		list.add(new ChartIndex("10:52", 1, 0));
//		list.add(new ChartIndex("10:55", 1, 0));
//		list.add(new ChartIndex("10:58", 1, 0));
//		list.add(new ChartIndex("11:01", 1, 0));
//		list.add(new ChartIndex("11:03", 1, 0));
//		list.add(new ChartIndex("11:04", 1, 0));
//		list.add(new ChartIndex("11:09", 1, 0));
//		list.add(new ChartIndex("11:13", 2, 0));
//		list.add(new ChartIndex("11:15", 1, 0));
//		list.add(new ChartIndex("11:16", 1, 0));
//		list.add(new ChartIndex("11:17", 1, 0));
//		list.add(new ChartIndex("11:20", 1, 0));
//		list.add(new ChartIndex("11:21", 1, 0));
//		list.add(new ChartIndex("11:23", 2, 0));
//		list.add(new ChartIndex("11:25", 1, 0));
//		list.add(new ChartIndex("11:27", 1, 0));
//		list.add(new ChartIndex("11:30", 1, 0));
//		list.add(new ChartIndex("11:31", 1, 0));
//		list.add(new ChartIndex("11:33", 1, 0));
//		list.add(new ChartIndex("11:34", 1, 0));
//		list.add(new ChartIndex("11:36", 3, 0));
//		list.add(new ChartIndex("11:37", 1, 0));
//		list.add(new ChartIndex("11:40", 1, 0));
//		list.add(new ChartIndex("11:41", 1, 0));
//		list.add(new ChartIndex("11:42", 1, 0));
//		list.add(new ChartIndex("11:43", 1, 0));
//		list.add(new ChartIndex("11:46", 1, 0));
//		list.add(new ChartIndex("11:47", 1, 0));
//		list.add(new ChartIndex("11:48", 1, 0));
//		list.add(new ChartIndex("11:49", 1, 0));
//		list.add(new ChartIndex("11:51", 2, 0));
//		list.add(new ChartIndex("11:52", 1, 0));
//		list.add(new ChartIndex("11:54", 1, 0));
//		list.add(new ChartIndex("11:59", 1, 0));
//		list.add(new ChartIndex("12:00", 2, 0));
//		list.add(new ChartIndex("12:01", 1, 0));
//		list.add(new ChartIndex("12:02", 1, 0));
//		list.add(new ChartIndex("12:03", 2, 0));
//		list.add(new ChartIndex("12:05", 1, 0));
//		list.add(new ChartIndex("12:06", 2, 0));
//		list.add(new ChartIndex("12:10", 1, 0));
//		list.add(new ChartIndex("12:11", 1, 0));
//		list.add(new ChartIndex("12:12", 2, 0));
//		list.add(new ChartIndex("12:13", 1, 0));
//		list.add(new ChartIndex("12:16", 2, 0));
//		list.add(new ChartIndex("12:20", 1, 0));
//		list.add(new ChartIndex("12:21", 1, 0));
//		list.add(new ChartIndex("12:25", 4, 0));
//		list.add(new ChartIndex("12:30", 1, 0));
//		list.add(new ChartIndex("12:33", 1, 0));
//		list.add(new ChartIndex("12:34", 1, 0));
//		list.add(new ChartIndex("12:37", 3, 0));
//		list.add(new ChartIndex("12:38", 1, 0));
//		list.add(new ChartIndex("12:40", 1, 0));
//		list.add(new ChartIndex("12:42", 1, 0));
//		list.add(new ChartIndex("12:44", 1, 0));
//		list.add(new ChartIndex("12:48", 3, 0));
//		list.add(new ChartIndex("12:50", 1, 0));
//		list.add(new ChartIndex("12:52", 1, 0));
//		list.add(new ChartIndex("12:55", 1, 0));
//		list.add(new ChartIndex("12:57", 1, 0));
//		list.add(new ChartIndex("12:59", 1, 0));
//		list.add(new ChartIndex("13:00", 1, 0));
//		list.add(new ChartIndex("13:07", 2, 0));
//		list.add(new ChartIndex("13:11", 2, 0));
//		list.add(new ChartIndex("13:13", 1, 0));
//		list.add(new ChartIndex("13:21", 1, 0));
//		list.add(new ChartIndex("13:22", 2, 0));
//		list.add(new ChartIndex("13:23", 1, 0));
//		list.add(new ChartIndex("13:25", 1, 0));
//		list.add(new ChartIndex("13:26", 1, 0));
//		list.add(new ChartIndex("13:29", 2, 0));
//		list.add(new ChartIndex("13:30", 1, 0));
//		list.add(new ChartIndex("13:31", 1, 0));
//		list.add(new ChartIndex("13:35", 1, 0));
//		list.add(new ChartIndex("13:37", 1, 0));
//		list.add(new ChartIndex("13:38", 1, 0));
//		list.add(new ChartIndex("13:40", 1, 0));
//		list.add(new ChartIndex("13:41", 1, 0));
//		list.add(new ChartIndex("13:45", 1, 0));
//		list.add(new ChartIndex("13:52", 1, 0));
//		list.add(new ChartIndex("13:53", 1, 0));
//		list.add(new ChartIndex("13:59", 1, 0));
//		list.add(new ChartIndex("14:00", 1, 0));
//		list.add(new ChartIndex("14:02", 1, 0));
//		list.add(new ChartIndex("14:10", 1, 0));
//		list.add(new ChartIndex("14:13", 1, 0));
//		list.add(new ChartIndex("14:15", 1, 0));
//		list.add(new ChartIndex("14:17", 1, 0));
//		list.add(new ChartIndex("14:19", 1, 0));
//		list.add(new ChartIndex("14:26", 1, 0));
//		list.add(new ChartIndex("14:47", 2, 0));
//		list.add(new ChartIndex("14:54", 1, 0));
//		list.add(new ChartIndex("14:55", 1, 0));
//		list.add(new ChartIndex("14:57", 1, 0));
//		list.add(new ChartIndex("14:58", 1, 0));
//		list.add(new ChartIndex("15:08", 1, 0));
//		list.add(new ChartIndex("15:11", 1, 0));
//		list.add(new ChartIndex("15:15", 1, 0));
//		list.add(new ChartIndex("15:19", 1, 0));
//		list.add(new ChartIndex("15:21", 2, 0));
//		list.add(new ChartIndex("15:32", 1, 0));
//		list.add(new ChartIndex("15:33", 1, 0));
//		list.add(new ChartIndex("15:34", 2, 0));
//		list.add(new ChartIndex("15:37", 1, 0));
//		list.add(new ChartIndex("15:39", 1, 0));
//		list.add(new ChartIndex("15:43", 1, 0));
//		list.add(new ChartIndex("15:46", 1, 0));
//		list.add(new ChartIndex("15:49", 1, 0));
//		list.add(new ChartIndex("15:51", 1, 0));
//		list.add(new ChartIndex("16:07", 1, 0));
//		list.add(new ChartIndex("16:17", 2, 0));
//		list.add(new ChartIndex("16:28", 1, 0));
//		list.add(new ChartIndex("16:31", 1, 0));
//		list.add(new ChartIndex("16:33", 1, 0));
//		list.add(new ChartIndex("16:36", 1, 0));
//		list.add(new ChartIndex("16:43", 1, 0));
//		list.add(new ChartIndex("16:50", 1, 0));
//		list.add(new ChartIndex("16:55", 1, 0));
//		list.add(new ChartIndex("17:09", 2, 0));
//		list.add(new ChartIndex("17:25", 1, 0));
//		list.add(new ChartIndex("17:35", 1, 0));
//		list.add(new ChartIndex("17:36", 1, 0));
//		list.add(new ChartIndex("17:43", 2, 0));
//		list.add(new ChartIndex("17:58", 1, 0));
//		list.add(new ChartIndex("18:20", 1, 0));
//		list.add(new ChartIndex("18:24", 1, 0));
//		list.add(new ChartIndex("18:30", 1, 0));
//		list.add(new ChartIndex("19:08", 1, 0));
//		list.add(new ChartIndex("19:09", 1, 0));
//		list.add(new ChartIndex("19:16", 1, 0));
//		list.add(new ChartIndex("19:28", 1, 0));
//		list.add(new ChartIndex("19:33", 1, 0));
//		list.add(new ChartIndex("19:41", 2, 0));
//		list.add(new ChartIndex("19:49", 1, 0));
//		list.add(new ChartIndex("19:52", 1, 0));
//
//		String jsonArrayFormatString = JsonFormatUtil.format(list);
//		System.out.println(jsonArrayFormatString);
//
//		ClipboardUtil.setClipboardContent(jsonArrayFormatString);
//	}

	@Test
	public final void testGetJSONArrayFormatStringCollectionOfQextendsObjectString1(){

		List<Shopping> list = new ArrayList<Shopping>();
		list.add(new Shopping(7, "the new ipad", 3700));
		list.add(new Shopping(10, "笛子", 600));
		list.add(new Shopping(11, "笛子培训", 2400));
		list.add(new Shopping(10, "驾校报名", 4900));
		list.add(new Shopping(12, "望湘园会员卡", 2000));
		list.add(new Shopping(7, "美的空调", 2400));
		list.add(new Shopping(8, "海信电视", 1600));
		list.add(new Shopping(8, "沙发", 1000));

		String jsonArrayFormatString = JsonFormatUtil.format(list);
		System.out.println(jsonArrayFormatString);

		ClipboardUtil.setClipboardContent(jsonArrayFormatString);
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.lang.Object, java.lang.String)}.
	 */
	@Test
	public final void testGetJsonObjectFormatStringObjectString(){
		String jsonArrayFormatString = JsonFormatUtil.format(new Shopping(7, "the new ipad", 3700), "month");
		System.out.println(jsonArrayFormatString);
	}
}
