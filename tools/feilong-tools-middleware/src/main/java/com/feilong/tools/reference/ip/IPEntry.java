package com.feilong.tools.reference.ip;

/**
 * 一条IP范围记录，不仅包括国家和区域，也包括起始IP和结束IP
 * 
 * @author swallow 2009-9-4上午11:21:20
 */
public class IPEntry{

	/********************************************************************************/
	/**
	 * 起始IP
	 */
	public String	beginIp;

	/**
	 * 结束IP
	 */
	public String	endIp;

	/**
	 * 国家
	 */
	public String	country;

	/**
	 * 区域
	 */
	public String	area;

	/********************************************************************************/
	public IPEntry(){
		beginIp = "";
		endIp = "";
		country = "";
		area = "";
	}

	@Override
	public String toString(){
		return this.area + "  " + this.country + "  IP范围:" + this.beginIp + "-" + this.endIp;
	}
}
