package com.feilong.tools;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MyEclipseCrack{

	private static final String	LL	= //
									"Decompiling this copyrighted software is a violation of both your license agreement and the Digital Millenium Copyright Act of 1998 (http://www.loc.gov/copyright/legislation/dmca.pdf). Under section 1204 of the DMCA, penalties range up to a $500,000 fine or up to five years imprisonment for a first offense. Think about it; pay for a license, avoid prosecution, and feel better about yourself.";

	/**
	 * <pre>
	 * Subscriber: venusdrogon
	 * Product ID: E3MB (MyEclipse Blue Subscription)
	 * License version: 1.0
	 * Full Maintenance Included
	 * Subscription expiration date (YYYYMMDD): 20111231
	 * Number of licenses: 999
	 * </pre>
	 * 
	 * @param userName
	 * @param version
	 * @param licenseNum
	 * @return
	 */
	public static String getSerial(String userName,String version,String licenseNum){
		NumberFormat numberFormat = new DecimalFormat("000");
		licenseNum = numberFormat.format(Integer.valueOf(licenseNum));
		String verTime = "-1312310";
		String type = "YE3MB-";
		String need = (new StringBuffer(String.valueOf(userName.substring(0, 1))))//
				.append(type)//
				.append(version)//
				.append(licenseNum)//
				.append(verTime).toString();
		String dx = (new StringBuffer(String.valueOf(need))).append(LL).append(userName).toString();
		int suf = decode(dx);
		String code = (new StringBuffer(need.toString())).append(suf + "").toString();
		return change(code);
	}

	private static String change(String s){
		byte abyte0[] = s.getBytes();
		char ac[] = new char[s.length()];
		int i = 0;
		for (int k = abyte0.length; i < k; i++){
			int j = abyte0[i];
			if (j >= 48 && j <= 57)
				j = ((j - 48) + 5) % 10 + 48;
			else if (j >= 65 && j <= 90)
				j = ((j - 65) + 13) % 26 + 65;
			else if (j >= 97 && j <= 122)
				j = ((j - 97) + 13) % 26 + 97;
			ac[i] = (char) j;
		}
		return String.valueOf(ac);
	}

	private static int decode(String s){
		int i = 0;
		char ac[] = s.toCharArray();
		int j = 0;
		for (int k = ac.length; j < k; j++)
			i = 31 * i + ac[j];
		return Math.abs(i);
	}
}