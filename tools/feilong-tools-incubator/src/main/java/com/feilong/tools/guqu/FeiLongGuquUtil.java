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
package com.feilong.tools.guqu;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 古曲网util.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-7 上午12:46:49
 */
public class FeiLongGuquUtil{

	/**
	 * 获得古曲论坛(http://bbs.guqu.net)搜索url
	 * 
	 * @param keyword
	 *            关键字 长度不超过20,直接传普通文字,自动encode
	 * @param boardid
	 *            "0"选取所有版面<br>
	 *            "3"╋乐器论坛<br>
	 *            "12" ├笛箫论坛<br>
	 *            "14" ├古筝论坛<br>
	 *            "13" ├胡琴论坛<br>
	 *            "73" | ├胡友自拉作品<br>
	 *            "22" ├古琴论坛<br>
	 *            "16" ├琵琶论坛<br>
	 *            "17" ├葫芦丝论坛<br>
	 *            "23" ├特色乐器<br>
	 *            "25" ├乐谱论坛<br>
	 *            "10"╋音画论坛<br>
	 *            "47" ├大图音画<br>
	 *            "77" | ├音画集展厅<br>
	 *            "66" ├画舞清音<br>
	 *            "64" ├图释心语<br>
	 *            "80" ├头像签名<br>
	 *            "79" ├行摄天下<br>
	 *            "40" ├古典动漫<br>
	 *            "84" | ├Flash学坊<br>
	 *            "48" ├素材贴图<br>
	 *            "58" ├制图学坊<br>
	 *            "85" | ├UC视频教学<br>
	 *            "68" | ├作业展区<br>
	 *            "59" | ├代码教程<br>
	 *            "69" | | ├试帖传图<br>
	 *            "82" | ├电子杂志<br>
	 *            "71" ├PSD分层素材<br>
	 *            "67" | ├滤镜插件<br>
	 *            "2"╋音乐论坛<br>
	 *            "41" ├中华古韵<br>
	 *            "60" ├新世纪音乐<br>
	 *            "31" ├民歌论坛<br>
	 *            "28" ├戏曲论坛<br>
	 *            "29" ├异域风情<br>
	 *            "76" ├单曲赏析<br>
	 *            "1"╋文学论坛<br>
	 *            "24" ├珠联壁合-对联论坛<br>
	 *            "43" | ├书山有路<br>
	 *            "61" | ├学海无涯<br>
	 *            "62" | ├木已成舟<br>
	 *            "63" | ├蟾宫折桂<br>
	 *            "7" ├诗风词韵-诗词论坛<br>
	 *            "45" | ├学习与欣赏<br>
	 *            "42" ├书香门第-现代文学<br>
	 *            "74" | ├他乡之玉<br>
	 *            "34" ├丹壁藏书-评书论坛<br>
	 *            "35" | ├红楼梦论坛<br>
	 *            "36" | ├三国演义论坛<br>
	 *            "37" | ├西游记论坛<br>
	 *            "38" | ├水浒传论坛<br>
	 *            "75"╋江南家园<br>
	 *            "5" ├闲情逸致<br>
	 *            "49" | ├南木清茶馆<br>
	 *            "46" | ├养生保健<br>
	 *            "50" ├诸子百家<br>
	 *            "52" | ├武侠论坛<br>
	 *            "54" | ├佛教论坛<br>
	 *            "55" | ├道教论坛<br>
	 *            "56" | ├周易论坛<br>
	 *            "81" ├古曲电台<br>
	 *            "83" ├书法国画<br>
	 *            "8"╋版务中心<br>
	 *            "72" ├公告活动<br>
	 *            "27" ├投诉建议<br>
	 *            "65" | ├帖子回收站<br>
	 *            "9" ├管理拓展<br>
	 *            "30" | ├VIP认证论坛<br>
	 *            "32" | | ├教程素材<br>
	 *            "78" | | ├闲情逸致-临时版
	 * @param sType
	 *            1作者 <br>
	 *            2主题 <br>
	 *            7内容 <br>
	 * @return the search url
	 */
	public static String getSearchUrl(String keyword,int boardid,int sType){
		if (Validator.isNullOrEmpty(keyword)){
			throw new IllegalArgumentException("keyword must be not null");
		}
		String format = "http://bbs.guqu.net/Query.asp?keyword=%s&boardid=%s&sType=%s";
		String keyword_encode_gb2312 = URIUtil.encode(keyword,CharsetType.GB2312);
		return StringUtil.format(format, keyword_encode_gb2312, boardid, sType);
	}
}
