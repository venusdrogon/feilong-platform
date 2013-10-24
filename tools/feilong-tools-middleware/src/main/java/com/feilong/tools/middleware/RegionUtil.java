package com.feilong.tools.middleware;

/**
 * 地区操作
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-4-15 上午11:11:00
 * @since 1.0
 */
public class RegionUtil{

	/**
	 * 直辖市
	 */
	public static String[]	municipalities					= { "北京市", "上海市", "天津市", "重庆市" };

	/**
	 * 特别行政区
	 */
	public static String[]	specialAdministrativeRegions	= { "香港", "澳门" };

	/**
	 * 是否是直辖市
	 * 
	 * @param provinceName
	 *            省份名称
	 * @return
	 */
	public static boolean isMunicipality(String provinceName){
		boolean flag = false;
		for (String municipality : municipalities){
			if (municipality.equals(provinceName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
