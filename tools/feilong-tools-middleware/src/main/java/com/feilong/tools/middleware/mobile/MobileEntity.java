package com.feilong.tools.middleware.mobile;

import java.io.Serializable;

/**
 * 手机
 * 
 * @author 金鑫 2010-3-25 下午03:38:12
 */
public class MobileEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    // 号码段
    private String            numberSegment;

    // 运营商
    private String            operator;

    // 第几代通讯技术 3rd-generation
    // 2G 代表为GSM。以数字语音传输技术为核心
    /**
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
     */
    private int               generation;

    /**
     * 网络标准
     */
    private String            networkStandard;

    /**
     * @param numberSegment
     *            号码段
     * @param operator
     *            运营商
     * @param generation
     *            第几代通讯技术
     * @param networkStandard
     *            网络标准
     */
    public MobileEntity(String numberSegment, String operator, int generation, String networkStandard){
        super();
        this.numberSegment = numberSegment;
        this.operator = operator;
        this.generation = generation;
        this.networkStandard = networkStandard;
    }

    /**
     * @return the numberSegment
     */
    public String getNumberSegment(){
        return numberSegment;
    }

    /**
     * @return the operator
     */
    public String getOperator(){
        return operator;
    }

    /**
     * @return the generation
     */
    public int getGeneration(){
        return generation;
    }

    /**
     * @return the networkStandard
     */
    public String getNetworkStandard(){
        return networkStandard;
    }
}
