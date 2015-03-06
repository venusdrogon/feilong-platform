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
package com.feilong.tools.middleware.mobile;

import java.util.ArrayList;
import java.util.List;

import com.feilong.commons.core.util.RegexUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 手机 电话操作
 * 
 * <pre>
 * 我们的手机号码被称为MSISDN
 * MSISDN＝CC（国家码）＋NDC（7位）（国内目的码）＋SN（4位）（用户号码）
 * 
 * 若将国家码CC去除，就成了移动台国内号码，也就是我们平时所讲的手机号。
 * 
 * NDC包括接入号和HLR的识别号，
 * 接入号就是我们平时所讲的139，138，137。。。。。。剩下的就是HLR识别号，表示用户归属的HLR，也表示移动业务本地网号。
 * 
 * 前三位没什么区别,130-133是联通的,134-139还有159是移动的,接下来的4位有特殊含义了,与地区有关,每个地区都有自己的号段,现在为什么有些软件可以查手机的归属地,就是靠这4位,最后4位可以重复,用与区分个人!
 * </pre>
 * 
 * .
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-3-25 下午03:32:04
 * @since 1.0
 */
public class MobileUtil{

    /** The mobile entity list. */
    private List<MobileEntity> mobileEntityList;

    /**
     * 输入 11位手机号码,返回 中间4位或者n位数字为星(*)的号码,默认4个星星
     * <p>
     * Examples:
     * 
     * <pre>
     * MobileUtil.getMobileNumberHided("15001841318") returns 150****1318
     * </pre>
     * 
     * @param mobileNumber
     *            11位mobileNumber
     * @return see javadoc
     */
    public static String getMobileNumberHided(String mobileNumber){
        return getMobileNumberHided(mobileNumber, 4);
    }

    /**
     * 输入 11位手机号码,返回 中间4位或者n位数字为星(*)的号码 <br>
     * 
     * <pre>
     * Examples:
     * MobileUtil.getMobileNumberHided("15001841318",5) returns 150*****318
     * 
     * Return:
     * <table >
     * <tr>
     * <th>描述</th>
     * <th>返回</th>
     * </tr>
     * <tr>
     * <td>Validator.isNull(mobileNumber)</td>
     * <td>throw IllegalArgumentException</td>
     * </tr>
     * <tr>
     * <td>mobileNumber.length()<= 3</td>
     * <td>mobileNumber</td>
     * </tr>
     * <tr>
     * <td>count &lt; 0</td>
     * <td>throw IllegalArgumentException</td>
     * </tr>
     * <tr>
     * <td>0 == count</td>
     * <td>mobileNumber</td>
     * </tr>
     * <tr>
     * <td>else</td>
     * <td>中间4位或者n位数字为星(*)的号码</td>
     * </tr>
     * </table>
     * </pre>
     * 
     * @param mobileNumber
     *            11位mobileNumber,不能为空
     * @param count
     *            Segment后面几个电话数需要隐藏,必须>=0
     * @return see javadoc
     */
    public static String getMobileNumberHided(String mobileNumber,int count){
        if (Validator.isNullOrEmpty(mobileNumber)){
            throw new IllegalArgumentException("mobileNumber can't be null/empty!");
        }
        if (mobileNumber.length() <= 3){
            return mobileNumber;
        }
        if (count < 0){
            throw new IllegalArgumentException("the param count must >=0");
        }
        if (0 == count){
            return mobileNumber;
        }
        StringBuilder mobileNumberHided = new StringBuilder();
        // 运营商编码
        mobileNumberHided.append(getMobileNumberNumberSegment(mobileNumber));
        // **************************
        for (int i = 0; i < count; ++i){
            mobileNumberHided.append("*");
        }
        // **************************
        int lastLenth = 8 - count;
        mobileNumberHided.append(StringUtil.substringLast(mobileNumber, lastLenth));
        return mobileNumberHided.toString();
    }

    /**
     * 获得手机的号码段
     * <p>
     * Examples:
     * 
     * <pre>
     * MobileUtil.getMobileNumberNumberSegment("15001841318") returns 150
     * </pre>
     * 
     * @param mobileNumber
     *            11位mobileNumber
     * @return 获得手机的号码段
     */
    public static String getMobileNumberNumberSegment(String mobileNumber){
        return mobileNumber.substring(0, 3);
    }

    /** 运营商. */
    private String[] operators = { "移动", "联通", "电信" };

    /**
     * Instantiates a new mobile util.
     */
    public MobileUtil(){
        mobileEntityList = new ArrayList<MobileEntity>();
        // 移动
        mobileEntityList.add(new MobileEntity("134", operators[0], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("135", operators[0], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("136", operators[0], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("137", operators[0], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("138", operators[0], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("139", operators[0], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("150", operators[0], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("151", operators[0], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("158", operators[0], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("159", operators[0], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("157", operators[0], 3, "TD-SCDMA"));
        mobileEntityList.add(new MobileEntity("187", operators[0], 3, "TD-SCDMA"));
        mobileEntityList.add(new MobileEntity("188", operators[0], 3, "TD-SCDMA"));
        // 联通
        mobileEntityList.add(new MobileEntity("130", operators[1], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("131", operators[1], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("132", operators[1], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("155", operators[1], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("156", operators[1], 2, "GSM"));
        mobileEntityList.add(new MobileEntity("185", operators[1], 3, "WCDMA"));
        mobileEntityList.add(new MobileEntity("186", operators[1], 3, "WCDMA"));
        // 电信
        mobileEntityList.add(new MobileEntity("133", operators[2], 2, "CDMA"));
        mobileEntityList.add(new MobileEntity("153", operators[2], 2, "CDMA"));
        mobileEntityList.add(new MobileEntity("180", operators[2], 3, "CDMA2000"));
        mobileEntityList.add(new MobileEntity("189", operators[2], 3, "CDMA2000"));
    }

    /**
     * 根据手机号码获得其信息.
     * 
     * @param mobile
     *            手机号码
     * @return the mobile entity
     */
    public MobileEntity getMobileEntity(String mobile){
        MobileEntity entity = null;
        String beginNum = mobile.substring(0, 3);
        for (MobileEntity mobileEntity : mobileEntityList){
            if (mobileEntity.getNumberSegment().equals(beginNum)){
                entity = mobileEntity;
                break;
            }
        }
        return entity;
    }

    /**
     * 电话号码类型判断 电话号码 移动、联通和电信最新号段
     * 
     * <pre>
     * 移动：134、135、136、137、138、139、150、151、158、159、157(3g)、187(3g)、188(3g) 
     * 联通：130、131、132、155、156、185(3g)、186(3g) 
     * 电信：133、153、189(3g)、180(3g)
     * GSM：
     * 移动的134、135、136、137、138、139、150、157、158、159，
     * 联通的130、131、132、153、155、156
     * 
     * CDMA：电信的133
     * 
     * 3G：又分为TD-CDMA、WCDMA、CDMA2000，每种手机不通用的，
     * 
     * 移动的TD-CDMA：188（又叫G3），
     * 联通的WCDMA：186（又叫沃），
     * 电信的CDMA2000：189（又叫天翼）。
     * 
     * 中国共有六大运营商，分别是：中国移动、中国联通、中国电信、中国网通、中国铁通、中国卫通。
     * 2009年合并为三家，分别是中国移动、中国联通和中国电信，
     * 又叫新移动、新联通、新电信；
     * 由于称呼习惯，大家还叫移动、联通、电信。
     * 
     * 合并详细如下
     * 	新移动：原移动业务，中国铁通并入移动成为全资子公司。
     * 	新联通：原联通GSM网业务，中国网通并入新联通。
     * 	新电信：原联通CDMA网业务，原电信所有业务、原卫通所有业务并入新电信。
     * 
     *  移动:
     * 	 2G号段(GSM网络)有139,138,137,136,135,134(0-8),159,158,152,151,150 
     * 	 3G号段(TD-SCDMA网络)有157,188,187 
     * 	 147是移动TD上网卡专用号段.
     * 	联通:
     * 	 2G号段(GSM网络)有130,131,132,155,156 
     * 	 3G号段(WCDMA网络)有186,185 
     * 	电信:
     * 	 2G号段(CDMA网络)有133,153 
     * 	 3G号段(CDMA网络)有189,180
     * </pre>
     * 
     * @param mobile
     *            the mobile
     * @return 返回值： 1 移动手机 2 联通手机 3 电信手机 4 固定电话、小灵通 0 未知
     */
    public static int getCardType(String mobile){
        // 移动电话
        if (RegexUtil.matches(mobile, "^\\d{11}$") && mobile.substring(0, 1) != "0"){
            int beginNum = Integer.parseInt(mobile.substring(0, 3));
            switch (beginNum) {
                case 134:
                case 135:
                case 136:
                case 137:
                case 138:
                case 139:
                case 150:
                case 151:
                case 157:// 3g
                case 158:
                case 159:
                case 187:// 3g
                case 188:// 3g
                    return 1;
                case 130:
                case 131:
                case 132:
                case 155:
                case 156:
                case 185:// 3g
                case 186:// 3g
                    return 2;
                case 133:
                case 153:
                case 189:// 3g
                case 180:// 3g
                    return 3;// 中国电信号码段不分3G、2G，所有号码段均能接入3G、2G网络，即只跟手机有关系，和号码段没关系
                default:
                    return 0;
            }
        }else if (RegexUtil.matches(mobile, "^\\d{3,4}-{0,1}\\d{7,8}$")){
            return 4; // 固定电话
        }else{
            return 0;// 未知
        }
    }
}
