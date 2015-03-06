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

/**
 * 手机.
 *
 * @author 金鑫 2010-3-25 下午03:38:12
 */
public enum MobileType{
    //移动
    /** 134. */
    NS134("134",MobileOperatorType.ChinaMobile,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 135. */
    NS135("135",MobileOperatorType.ChinaMobile,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 136. */
    NS136("136",MobileOperatorType.ChinaMobile,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 137. */
    NS137("137",MobileOperatorType.ChinaMobile,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** The N s138. */
    NS138("138",MobileOperatorType.ChinaMobile,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 139. */
    NS139("139",MobileOperatorType.ChinaMobile,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 150. */
    NS150("150",MobileOperatorType.ChinaMobile,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 151. */
    NS151("151",MobileOperatorType.ChinaMobile,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 152. */
    NS152("152",MobileOperatorType.ChinaMobile,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 158. */
    NS158("158",MobileOperatorType.ChinaMobile,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 159. */
    NS159("159",MobileOperatorType.ChinaMobile,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 157. */
    NS157("157",MobileOperatorType.ChinaMobile,MobileGenerationType.Third,NetworkStandardType.TD_SCDMA),
    
    /** 187. */
    NS187("187",MobileOperatorType.ChinaMobile,MobileGenerationType.Third,NetworkStandardType.TD_SCDMA),
    
    /** 188. */
    NS188("188",MobileOperatorType.ChinaMobile,MobileGenerationType.Third,NetworkStandardType.TD_SCDMA),
    // 联通
    /** 130. */
    NS130("130",MobileOperatorType.ChinaUnicom,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 131. */
    NS131("131",MobileOperatorType.ChinaUnicom,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 132. */
    NS132("132",MobileOperatorType.ChinaUnicom,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 155. */
    NS155("155",MobileOperatorType.ChinaUnicom,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 156. */
    NS156("156",MobileOperatorType.ChinaUnicom,MobileGenerationType.Two,NetworkStandardType.GSM),
    
    /** 185. */
    NS185("185",MobileOperatorType.ChinaUnicom,MobileGenerationType.Third,NetworkStandardType.WCDMA),
    
    /** 186. */
    NS186("186",MobileOperatorType.ChinaUnicom,MobileGenerationType.Third,NetworkStandardType.WCDMA),
    // 电信
    /** 133. */
    NS133("133",MobileOperatorType.ChinaTelecom,MobileGenerationType.Two,NetworkStandardType.CDMA),
    
    /** 153. */
    NS153("153",MobileOperatorType.ChinaTelecom,MobileGenerationType.Two,NetworkStandardType.CDMA),
    
    /** 180. */
    NS180("180",MobileOperatorType.ChinaTelecom,MobileGenerationType.Third,NetworkStandardType.CDMA2000),
    
    /** 189. */
    NS189("189",MobileOperatorType.ChinaTelecom,MobileGenerationType.Third,NetworkStandardType.CDMA2000);

    //*************************************************************************************************************
    // 号码段
    /** The number segment. */
    private String               numberSegment;

    // 运营商
    /** The mobile operator type. */
    private MobileOperatorType   mobileOperatorType;

    // 第几代通讯技术 3rd-generation
    /** The mobile generation type. */
    private MobileGenerationType mobileGenerationType;

    /** 网络标准. */
    private NetworkStandardType  networkStandardType;

    //*************************************************************************************************************
    /**
     * 通过 numberSegment 获得FeiLongMobileType.
     *
     * @param numberSegment
     *            手机号码段
     * @return MobileType
     */
    public static MobileType getFeiLongMobileType(String numberSegment){
        MobileType mobileType = null;
        for (MobileType feiLongMobileType2 : MobileType.values()){
            if (numberSegment.equals(feiLongMobileType2.getNumberSegment())){
                mobileType = feiLongMobileType2;
                break;
            }
        }
        return mobileType;
    }

    //***********************************************************************************
    /**
     * The Constructor.
     *
     * @param numberSegment
     *            号码段
     * @param mobileOperatorType
     *            the mobile operator type
     * @param mobileGenerationType
     *            the mobile generation type
     * @param networkStandardType
     *            the network standard type
     */
    private MobileType(String numberSegment, MobileOperatorType mobileOperatorType, MobileGenerationType mobileGenerationType,
                    NetworkStandardType networkStandardType){
        this.numberSegment = numberSegment;
        this.mobileOperatorType = mobileOperatorType;
        this.mobileGenerationType = mobileGenerationType;
        this.networkStandardType = networkStandardType;
    }

    //**************************************************************************************
    /**
     * 获得 number segment.
     *
     * @return the numberSegment
     */
    public String getNumberSegment(){
        return numberSegment;
    }

    /**
     * 设置 number segment.
     *
     * @param numberSegment
     *            the numberSegment to set
     */
    public void setNumberSegment(String numberSegment){
        this.numberSegment = numberSegment;
    }

    /**
     * 获得 fei long mobile operator type.
     *
     * @return the mobileOperatorType
     */
    public MobileOperatorType getFeiLongMobileOperatorType(){
        return mobileOperatorType;
    }

    /**
     * 设置 fei long mobile operator type.
     *
     * @param mobileOperatorType
     *            the mobileOperatorType to set
     */
    public void setFeiLongMobileOperatorType(MobileOperatorType mobileOperatorType){
        this.mobileOperatorType = mobileOperatorType;
    }

    /**
     * 获得 fei long mobile generation type.
     *
     * @return the mobileGenerationType
     */
    public MobileGenerationType getFeiLongMobileGenerationType(){
        return mobileGenerationType;
    }

    /**
     * 设置 fei long mobile generation type.
     *
     * @param mobileGenerationType
     *            the mobileGenerationType to set
     */
    public void setFeiLongMobileGenerationType(MobileGenerationType mobileGenerationType){
        this.mobileGenerationType = mobileGenerationType;
    }

    /**
     * 获得 fei long network standard type.
     *
     * @return the networkStandardType
     */
    public NetworkStandardType getFeiLongNetworkStandardType(){
        return networkStandardType;
    }

    /**
     * 设置 fei long network standard type.
     *
     * @param networkStandardType
     *            the networkStandardType to set
     */
    public void setFeiLongNetworkStandardType(NetworkStandardType networkStandardType){
        this.networkStandardType = networkStandardType;
    }
}
