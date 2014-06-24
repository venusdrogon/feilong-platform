/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.lang.ThreadUtil;
import com.feilong.commons.core.net.InetAddressUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.ByteUtil;
import com.sun.management.OperatingSystemMXBean;

/**
 * 飞龙操作系统相关类,可以读取操作系统相关数据
 * 
 * @author 金鑫 2010-2-4 上午10:50:47
 */
public final class OperatingSystemUtil{

	private static final Logger	log	= LoggerFactory.getLogger(OperatingSystemUtil.class);

	/**
	 * 获得当前的监控对象.
	 * 
	 * @return
	 */
	public static MonitorInfoEntity getMonitorInfoEntity(){
		Runtime runtime = Runtime.getRuntime();

		// 返回 Java 虚拟机中的内存总量(单位字节)。
		long runtimeTotalMemory = runtime.totalMemory();
		// 返回 Java 虚拟机中的空闲内存量(单位字节)
		long runtimeFreeMemory = runtime.freeMemory();
		// 返回 Java 虚拟机试图使用的最大内存量(单位字节)
		long runtimeMaxMemory = runtime.maxMemory();

		// ************************************************************************

		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

		// 总的物理内存(单位字节)
		long osTotalMemorySize = osmxb.getTotalPhysicalMemorySize();
		// 剩余的物理内存(单位字节)
		long osFreePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
		// 已使用的物理内存(单位字节)
		long osUsedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize());

		// *************************************************************************
		// 操作系统
		String osName = System.getProperty("os.name");

		// *************************************************************************
		int topThreadGroupActiveCount = ThreadUtil.getTopThreadGroupActiveCount();

		// ************************************************************************
		// 构造返回对象
		MonitorInfoEntity monitorInfoEntity = new MonitorInfoEntity();

		monitorInfoEntity.setRuntimeFreeMemory(runtimeFreeMemory);
		monitorInfoEntity.setRuntimeTotalMemory(runtimeTotalMemory);
		monitorInfoEntity.setRuntimeMaxMemory(runtimeMaxMemory);

		monitorInfoEntity.setOsFreePhysicalMemorySize(osFreePhysicalMemorySize);

		monitorInfoEntity.setOsTotalMemorySize(osTotalMemorySize);
		monitorInfoEntity.setOsUsedMemory(osUsedMemory);

		monitorInfoEntity.setTopThreadGroupActiveCount(topThreadGroupActiveCount);

		monitorInfoEntity.setOsName(osName);

		return monitorInfoEntity;
	}

	/**
	 * 获取本机IP地址
	 * 
	 * @return
	 */
	public static String getLocalHostAddress(){
		InetAddress inetAddress = null;

		Map<String, NetworkInterface> macAddressMap = getMacAddressMap();
		for (Map.Entry<String, NetworkInterface> entry : macAddressMap.entrySet()){

			if (null != inetAddress){
				break;
			}
			NetworkInterface networkInterface = entry.getValue();
			Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
			while (inetAddresses.hasMoreElements()){
				InetAddress _inetAddress = inetAddresses.nextElement();

				Map<String, Object> inetAddressObjectLog = InetAddressUtil.getInetAddressObjectLog(_inetAddress);
				log.debug(JsonUtil.format(inetAddressObjectLog));

				if (_inetAddress.isSiteLocalAddress() // 检查 InetAddress 是否是站点本地地址的实用例行程序。
						&& !_inetAddress.isLoopbackAddress() // 127.开头的都是lookback地址
						&& _inetAddress.getHostAddress().indexOf(":") == -1){// 去掉ip6
					inetAddress = _inetAddress;
					break;
				}
			}
		}
		if (null != inetAddress){
			return inetAddress.getHostAddress();
		}
		return null;
	}

	/**
	 * 获取本机,计算机名
	 * 
	 * @return
	 */
	public static String getLocalHostName(){
		// Gets the host name for this IP address.
		return InetAddressUtil.getInetAddressLocalHost().getHostName();
	}

	/**
	 * 获取所有开启的，正常的网卡的(本机)MAC地址
	 * 
	 * @return key是小写的mac地址，value 是对应的NetworkInterface
	 * @since jdk 1.6
	 */
	public static Map<String, NetworkInterface> getMacAddressMap(){

		Map<String, NetworkInterface> macMap = new HashMap<String, NetworkInterface>();

		try{
			// 返回所有网络接口的一个枚举实例
			Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();

			Map<String, Object> allMap = new HashMap<String, Object>();

			while (enumeration.hasMoreElements()){
				// 获得当前网络接口
				NetworkInterface networkInterface = enumeration.nextElement();

				if (networkInterface != null){
					// 返回网络接口是否已经开启并运行
					boolean isUp = networkInterface.isUp();

					// 返回网络接口是否是回送接口。
					boolean isLoopback = networkInterface.isLoopback();

					// the network interface is a point-to-point interface:
					boolean isPointToPoint = networkInterface.isPointToPoint();

					// Returns whether this interface is a virtual interface (also called subinterface).
					boolean isVirtual = networkInterface.isVirtual();

					String name = networkInterface.getName();

					if (log.isDebugEnabled()){
						Map<String, Object> map = new HashMap<String, Object>();

						String mac = getMacAddress(networkInterface);

						map.put("getDisplayName()", networkInterface.getDisplayName());

						// 此接口的最大传输单元（Maximum Transmission Unit，MTU）
						map.put("getMTU()", networkInterface.getMTU());
						map.put("isVirtual()", isVirtual);
						map.put("isLoopback()", isLoopback);
						map.put("isPointToPoint()", isPointToPoint);
						map.put("isUp()", isUp);
						map.put("getHardwareAddress()", networkInterface.getHardwareAddress());
						map.put("mac", null == mac ? "" : mac);
						Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

						int i = 0;
						while (inetAddresses.hasMoreElements()){
							InetAddress inetAddress = inetAddresses.nextElement();

							// 只输出ip4,ip6尚未普及
							if (inetAddress instanceof Inet4Address){
								map.put("inetAddress" + i, inetAddress.getHostAddress());
								i++;
							}
						}

						map.put("getInterfaceAddresses()", networkInterface.getInterfaceAddresses());
						map.put("getParent()", networkInterface.getParent());
						map.put("supportsMulticast()", networkInterface.supportsMulticast());

						allMap.put(name, map);
					}

					if (isUp && !isLoopback && !isPointToPoint && !isVirtual){
						byte[] hardwareAddress = networkInterface.getHardwareAddress();
						if (hardwareAddress != null && hardwareAddress.length > 1){
							// 取到mac地址
							String macAddress = getMacAddress(networkInterface);
							macMap.put(macAddress, networkInterface);
						}
					}
				}
			}

			if (log.isDebugEnabled()){
				log.debug("the param allMap:{}", JsonUtil.format(allMap));
			}

		}catch (SocketException e){
			e.printStackTrace();
		}
		return macMap;
	}

	/**
	 * 从networkInterface 取到 macAddress 在给定主机名的情况下确定主机的 ip地址。 <br>
	 * 注意:不能使用远程的IP的域名来创建InetAddress对象，否则getByInetAddress将返回null <br>
	 * OperatingSystemUtil.getMacAddressByHost("10.8.17.84")
	 * 
	 * @param host
	 * @return
	 */
	public static String getMacAddressByHost(String host){
		InetAddress inetAddress = InetAddressUtil.getInetAddress(host);
		try{
			NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
			return getMacAddress(networkInterface);
		}catch (SocketException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 搜索具有指定名称的网络接口<br>
	 * example: OperatingSystemUtil.getMacAddressByName("eth3")
	 * 
	 * <pre>
	 * 这个网络接口名并不是计算机名，而是用于标识物理或逻辑网络接口的名字，一般是由操作系统设置的。
	 * 网络接口名在大多数操作系统上（包括Windows、Linux和Unix）是以eth开头，后面是网络接口的索引号，从0开始。
	 * 如本机安了三块网卡，那么网络接口名就依次是eth0、eth1和eth2。
	 * </pre>
	 * 
	 * @param name
	 *            网络接口的名称
	 * @return
	 */
	public static String getMacAddressByName(String name){
		try{
			// 搜索具有指定名称的网络接口
			NetworkInterface networkInterface = NetworkInterface.getByName(name);
			return getMacAddress(networkInterface);
		}catch (SocketException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从networkInterface 取到 macAddress
	 * 
	 * @param networkInterface
	 *            由名称和分配给此接口的 IP 地址列表组成的网络接口。<br>
	 *            它用于标识加入多播组的本地接口。 接口通常是按名称（如 "le0"）区分的。
	 * @return macaddress
	 */
	private static String getMacAddress(NetworkInterface networkInterface){

		if (null != networkInterface){

			// 结果是一个byte数组，每项是一个byte，我们需要转换成常见的十六进制表示
			byte[] hardwareAddress = null;
			try{
				hardwareAddress = networkInterface.getHardwareAddress();
			}catch (SocketException e){
				e.printStackTrace();
			}
			if (hardwareAddress != null && hardwareAddress.length > 1){

				// 获得MAC地址
				StringBuffer sb = new StringBuffer();

				sb.append(ByteUtil.byteToHexStringLowerCase(hardwareAddress[0])).append(":");
				sb.append(ByteUtil.byteToHexStringLowerCase(hardwareAddress[1])).append(":");
				sb.append(ByteUtil.byteToHexStringLowerCase(hardwareAddress[2])).append(":");
				sb.append(ByteUtil.byteToHexStringLowerCase(hardwareAddress[3])).append(":");
				sb.append(ByteUtil.byteToHexStringLowerCase(hardwareAddress[4])).append(":");
				sb.append(ByteUtil.byteToHexStringLowerCase(hardwareAddress[5]));

				return sb.toString();
			}
		}else{
			log.warn("networkInterface is null!");
		}
		return null;
	}

	// ********************************************************************************************************

	/**
	 * 显示 程序运行的 系统环境 信息
	 * 
	 * @return
	 */
	public static String getSystemPropertiesLog(){
		Properties props = System.getProperties();
		// sb.append("\n----------------------Environment Info--------------------------\n");
		// sb.append("os.name : " + props.getProperty("os.name") + "\n"); // 操作系统名称
		// sb.append("os.arch : " + props.getProperty("os.arch") + "\n"); // 操作系统构架
		// sb.append("os.version : " + props.getProperty("os.version") + "\n"); // 操作系统版本
		// sb.append("java.version : " + props.getProperty("java.version") + "\n"); // Java 运行时环境版本
		// sb.append("java.vendor : " + props.getProperty("java.vendor") + "\n"); // Java 运行时环境供应商
		// sb.append("java.vm.name : " + props.getProperty("java.vm.name") + "\n"); // Java 虚拟机实现名称
		// sb.append("java.home : " + props.getProperty("java.home") + "\n"); // Java 安装目录
		// sb.append("java.class.path : " + props.getProperty("java.class.path") + "\n"); // Java 类路径
		// sb.append("java.library.path : " + props.getProperty("java.library.path") + "\n"); // 加载库时搜索的路径列表
		// sb.append("user.name : " + props.getProperty("user.name") + "\n"); // 用户的账户名称
		// sb.append("user.home : " + props.getProperty("user.home") + "\n"); // 用户的主目录
		// sb.append("user.dir : " + props.getProperty("user.dir") + "\n"); // 用户的当前工作目录
		// sb.append("file.encoding : " + props.getProperty("file.encoding") + "\n");
		// sb.append("--------Disk information---------\n");

		return JsonUtil.format(props);
	}

	/** 记录磁盘信息 */
	public static String getDiskInfoLog(){

		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();

		File[] rootFiles = File.listRoots();
		for (File file : rootFiles){
			String name = file.getName();
			String path = file.getPath();

			long freeSpace = file.getFreeSpace();
			long usableSpace = file.getUsableSpace();
			long totalSpace = file.getTotalSpace();

			Map<String, Object> _map = new HashMap<String, Object>();
			_map.put("path", path);
			_map.put("name", name);
			_map.put("Free space", FileUtil.formatSize(freeSpace));
			_map.put("Usable space", FileUtil.formatSize(usableSpace));
			_map.put("Total space", FileUtil.formatSize(totalSpace));

			map.put(path, _map);

		}
		return JsonUtil.format(map);
	}

	// ********************************************************************************

	/**
	 * 判断当前操作系统是否是Windows系统.
	 * 
	 * @return true---是Windows操作系统
	 */
	public static boolean isWindowsOS(){
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		log.debug("the system property os.name:{}", osName);
		if (osName.toLowerCase().indexOf("windows") > -1){
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

}