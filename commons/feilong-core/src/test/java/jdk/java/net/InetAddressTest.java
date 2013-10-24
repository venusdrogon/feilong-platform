/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
package jdk.java.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import com.feilong.commons.core.net.InetAddressUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-12 上午09:44:38
 */
public class InetAddressTest{

	public static void main(String[] args){
		String ipString = "127.0.0.1";
		byte[] ips1 = ipString.getBytes();
		byte[] ips = new byte[] { (byte) 127, (byte) 0, 0, 1 };
		test();
	}

	public static void test1(){
		Enumeration<NetworkInterface> netInterfaces = null;
		try{
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()){
				NetworkInterface ni = netInterfaces.nextElement();
				System.out.println("DisplayName:" + ni.getDisplayName());
				System.out.println("Name:" + ni.getName());
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()){
					System.out.println("IP:" + ips.nextElement().getHostAddress());
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void test(){
		try{
			// 调用中止前的时间（以毫秒为单位）
			int timeOut = 3000;
			int retry = 4;
			// String hostString = "127.0.0.1";
			String hostString = "fe80::6965:c8b8:f570:842%11";
			InetAddress address = InetAddressUtil.getInetAddress(hostString);
			for (int i = 0; i < retry; i++){
				// 测试是否可以达到该地址。
				if (address.isReachable(timeOut)){
					System.out.println(i + " OK");
				}else{
					System.out.println(i + " LOSS");
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
