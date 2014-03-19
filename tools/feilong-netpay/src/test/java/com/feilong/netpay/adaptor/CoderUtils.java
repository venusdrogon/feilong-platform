package com.feilong.netpay.adaptor;

/**
 * @date 2009-3-11
 * @author Xing,Xiudong
 * @Email:xingxiuodng[at]gmail.com
 * @index:http://blog.csdn.net/xxd851116
 */
public class CoderUtils{

	public static char ascii2Char(int ASCII){
		return (char) ASCII;
	}

	public static int char2ASCII(char c){
		return (int) c;
	}

	public static String ascii2String(int[] ASCIIs){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ASCIIs.length; i++){
			sb.append((char) ascii2Char(ASCIIs[i]));
		}
		return sb.toString();
	}

	public static String ascii2String(String ASCIIs){
		String[] ASCIIss = ASCIIs.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ASCIIss.length; i++){
			sb.append((char) ascii2Char(Integer.parseInt(ASCIIss[i])));
		}
		return sb.toString();
	}

	public static int[] string2ASCII(String s){// 字符串转换为ASCII码
		if (s == null || "".equals(s)){
			return null;
		}

		char[] chars = s.toCharArray();
		int[] asciiArray = new int[chars.length];

		for (int i = 0; i < chars.length; i++){
			asciiArray[i] = char2ASCII(chars[i]);
		}
		return asciiArray;
	}

	public static String getIntArrayString(int[] intArray){
		return getIntArrayString(intArray, ",");
	}

	public static String getIntArrayString(int[] intArray,String delimiter){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < intArray.length; i++){
			sb.append(intArray[i]).append(delimiter);
		}
		return sb.toString();
	}

	public static String getASCII(int begin,int end){
		StringBuffer sb = new StringBuffer();
		for (int i = begin; i < end; i++){
			sb.append(i).append(":").append((char) i).append("/t");
			// sb.append((char) i).append("/t");
			if (i % 10 == 0){
				sb.append("/n");
			}
		}
		return sb.toString();
	}

	public static String getCHASCII(int begin,int end){
		return getASCII(19968, 40869);
	}

	public static void showASCII(int begin,int end){
		for (int i = begin; i < end; i++){
			// System.out.print(i + ":" + (char) i + "/t");
			System.out.print((char) i + "/t");
			if (i % 10 == 0){
				System.out.println();
			}
		}
	}

	public static void showCHASCII(){
		showASCII(19968, 40869);
	}

	public static void showIntArray(int[] intArray){
		showIntArray(intArray, ",");
	}

	public static void showIntArray(int[] intArray,String delimiter){
		for (int i = 0; i < intArray.length; i++){
			System.out.print(intArray[i] + delimiter);
		}
	}

	public static void main(String[] args){
		String s = "好好学习！天天向上！————笑的自然 2009年3月11日";
		showIntArray(string2ASCII(s), " ");
		System.out.println();
		System.out.println(ascii2String(string2ASCII(s)));
	}
}
