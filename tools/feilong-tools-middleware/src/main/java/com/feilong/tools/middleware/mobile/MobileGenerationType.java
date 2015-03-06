/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.tools.middleware.mobile;

/**
 * 手机号码技术 第几代通讯技术 3rd-generation 2G 代表为GSM。以数字语音传输技术为核心
 * 
 * <pre>
 * 第二代手机通讯技术规格标准有:
 * GSM：基于TDMA所发展、源于欧洲、目前已全球化。 
 * 	 IDEN：基于TDMA所发展、美国独有的系统。被美国电信系统商Nextell使用。 
 * 	 IS-136﹙也叫做D-AMPS﹚：基于TDMA所发展，是美国最简单的TDMA系统，用于美洲。 
 * 	 IS-95﹙也叫做cdmaOne﹚：基于CDMA所发展、是美国最简单的CDMA系统、用于美洲和亚洲一些国家。 
 * 	 PDC﹙Personal Digital Cellular﹚：基于TDMA所发展，仅在日本普及。
 * 
 *  3G  目前3G存在四种标准：CDMA2000，WCDMA，TD-SCDMA，WiMAX。
 * 
 * 3G标准：它们分别是WCDMA（欧洲版）、CDMA2000（美国版）和TD-SCDMA（中国版）。
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-4 下午01:51:52
 */
public enum MobileGenerationType{
    /**
     * 第二代
     */
    Two,
    /**
     * 第三代
     */
    Third;
}
