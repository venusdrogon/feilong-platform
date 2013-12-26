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
package com.feilong.tools.om.os;

import java.io.Serializable;

import com.feilong.commons.core.io.FileUtil;

/**
 * 监视信息的JavaBean类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 13, 2013 4:19:32 PM
 */
public class MonitorInfoEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -7195623140326146419L;

	// ***************************************************************************
	/** 操作系统. */
	private String				osName;

	// ***************************************************************************
	/** 返回顶级线程组中活动线程的估计数。. */
	private int					topThreadGroupActiveCount;

	// ****************************************************************
	/** Java 虚拟机中的内存总量(单位字节). */
	private long				runtimeTotalMemory;

	/** Java 虚拟机中的空闲内存量(单位字节). */
	private long				runtimeFreeMemory;

	/** Java 虚拟机试图使用的最大内存量(单位字节). */
	private long				runtimeMaxMemory;

	/** (readonly)Java 虚拟机中的内存总量,格式化输出格式,比如16252928 转成 15.50MB. */
	private String				runtimeFormatTotalMemory;

	/** (readonly)Java 虚拟机中的空闲内存量,格式化输出格式,比如13835384 转成 13.19MB. */
	private String				runtimeFormatFreeMemory;

	/** (readonly)Java 虚拟机试图使用的最大内存量,格式化输出格式,比如259522560 转成 247.50MB. */
	private String				runtimeFormatMaxMemory;

	// ***************************************************************************

	/** 总的物理内存(单位字节). */
	private long				osTotalMemorySize;

	/** 剩余的物理内存(单位字节). */
	private long				osFreePhysicalMemorySize;

	/** 已使用的物理内存(单位字节). */
	private long				osUsedMemory;

	/** (readonly)总的物理内存,格式化输出格式,比如4276649984 转成3.98GB. */
	private String				osFormatTotalMemorySize;

	/** (readonly)剩余的物理内存,格式化输出格式,比如1595015168 转成 1.48GB. */
	private String				osFormatFreePhysicalMemorySize;

	/** (readonly)已使用的物理内存,格式化输出格式,比如2681634816 转成 2.49GB. */
	private String				osFormatUsedMemory;

	/**
	 * Gets the 操作系统.
	 * 
	 * @return the osName
	 */
	public String getOsName(){
		return osName;
	}

	/**
	 * Sets the 操作系统.
	 * 
	 * @param osName
	 *            the osName to set
	 */
	public void setOsName(String osName){
		this.osName = osName;
	}

	/**
	 * Gets the java 虚拟机中的内存总量(单位字节).
	 * 
	 * @return the runtimeTotalMemory
	 */
	public long getRuntimeTotalMemory(){
		return runtimeTotalMemory;
	}

	/**
	 * Sets the java 虚拟机中的内存总量(单位字节).
	 * 
	 * @param runtimeTotalMemory
	 *            the runtimeTotalMemory to set
	 */
	public void setRuntimeTotalMemory(long runtimeTotalMemory){
		this.runtimeTotalMemory = runtimeTotalMemory;
	}

	/**
	 * Gets the java 虚拟机中的空闲内存量(单位字节).
	 * 
	 * @return the runtimeFreeMemory
	 */
	public long getRuntimeFreeMemory(){
		return runtimeFreeMemory;
	}

	/**
	 * Sets the java 虚拟机中的空闲内存量(单位字节).
	 * 
	 * @param runtimeFreeMemory
	 *            the runtimeFreeMemory to set
	 */
	public void setRuntimeFreeMemory(long runtimeFreeMemory){
		this.runtimeFreeMemory = runtimeFreeMemory;
	}

	/**
	 * Gets the java 虚拟机试图使用的最大内存量(单位字节).
	 * 
	 * @return the runtimeMaxMemory
	 */
	public long getRuntimeMaxMemory(){
		return runtimeMaxMemory;
	}

	/**
	 * Sets the java 虚拟机试图使用的最大内存量(单位字节).
	 * 
	 * @param runtimeMaxMemory
	 *            the runtimeMaxMemory to set
	 */
	public void setRuntimeMaxMemory(long runtimeMaxMemory){
		this.runtimeMaxMemory = runtimeMaxMemory;
	}

	/**
	 * Gets the 总的物理内存(单位字节).
	 * 
	 * @return the osTotalMemorySize
	 */
	public long getOsTotalMemorySize(){
		return osTotalMemorySize;
	}

	/**
	 * Sets the 总的物理内存(单位字节).
	 * 
	 * @param osTotalMemorySize
	 *            the osTotalMemorySize to set
	 */
	public void setOsTotalMemorySize(long osTotalMemorySize){
		this.osTotalMemorySize = osTotalMemorySize;
	}

	/**
	 * Gets the 剩余的物理内存(单位字节).
	 * 
	 * @return the osFreePhysicalMemorySize
	 */
	public long getOsFreePhysicalMemorySize(){
		return osFreePhysicalMemorySize;
	}

	/**
	 * Sets the 剩余的物理内存(单位字节).
	 * 
	 * @param osFreePhysicalMemorySize
	 *            the osFreePhysicalMemorySize to set
	 */
	public void setOsFreePhysicalMemorySize(long osFreePhysicalMemorySize){
		this.osFreePhysicalMemorySize = osFreePhysicalMemorySize;
	}

	/**
	 * Gets the 已使用的物理内存(单位字节).
	 * 
	 * @return the osUsedMemory
	 */
	public long getOsUsedMemory(){
		return osUsedMemory;
	}

	/**
	 * Sets the 已使用的物理内存(单位字节).
	 * 
	 * @param osUsedMemory
	 *            the osUsedMemory to set
	 */
	public void setOsUsedMemory(long osUsedMemory){
		this.osUsedMemory = osUsedMemory;
	}

	/**
	 * Java 虚拟机中的内存总量,格式化输出格式,比如16252928 转成 15.50MB.
	 * 
	 * @return the runtimeFormatTotalMemory
	 */
	public String getRuntimeFormatTotalMemory(){
		runtimeFormatTotalMemory = FileUtil.formatFileSize(runtimeTotalMemory);
		return runtimeFormatTotalMemory;
	}

	/**
	 * Java 虚拟机中的空闲内存量,格式化输出格式,比如13835384 转成 13.19MB.
	 * 
	 * @return the runtimeFormatFreeMemory
	 */
	public String getRuntimeFormatFreeMemory(){
		runtimeFormatFreeMemory = FileUtil.formatFileSize(runtimeFreeMemory);
		return runtimeFormatFreeMemory;
	}

	/**
	 * Java 虚拟机试图使用的最大内存量,格式化输出格式,比如259522560 转成 247.50MB.
	 * 
	 * @return the runtimeFormatMaxMemory
	 */
	public String getRuntimeFormatMaxMemory(){
		runtimeFormatMaxMemory = FileUtil.formatFileSize(runtimeMaxMemory);
		return runtimeFormatMaxMemory;
	}

	/**
	 * 总的物理内存,格式化输出格式,比如4276649984 转成3.98GB.
	 * 
	 * @return the osFormatTotalMemorySize
	 */
	public String getOsFormatTotalMemorySize(){
		osFormatTotalMemorySize = FileUtil.formatFileSize(osTotalMemorySize);
		return osFormatTotalMemorySize;
	}

	/**
	 * 剩余的物理内存,格式化输出格式,比如1595015168 转成 1.48GB.
	 * 
	 * @return the osFormatFreePhysicalMemorySize
	 */
	public String getOsFormatFreePhysicalMemorySize(){
		osFormatFreePhysicalMemorySize = FileUtil.formatFileSize(osFreePhysicalMemorySize);
		return osFormatFreePhysicalMemorySize;
	}

	/**
	 * 已使用的物理内存,格式化输出格式,比如2681634816 转成 2.49GB.
	 * 
	 * @return the osFormatUsedMemory
	 */
	public String getOsFormatUsedMemory(){
		osFormatUsedMemory = FileUtil.formatFileSize(osUsedMemory);
		return osFormatUsedMemory;
	}

	/**
	 * Gets the 返回顶级线程组中活动线程的估计数。.
	 * 
	 * @return the topThreadGroupActiveCount
	 */
	public int getTopThreadGroupActiveCount(){
		return topThreadGroupActiveCount;
	}

	/**
	 * Sets the 返回顶级线程组中活动线程的估计数。.
	 * 
	 * @param topThreadGroupActiveCount
	 *            the topThreadGroupActiveCount to set
	 */
	public void setTopThreadGroupActiveCount(int topThreadGroupActiveCount){
		this.topThreadGroupActiveCount = topThreadGroupActiveCount;
	}

	// ****************************************************************

	/** 线程总数. */
	// private int totalThread;

	// ****************************************************************

	/** cpu使用率. */
	// private double cpuRatio;

}
