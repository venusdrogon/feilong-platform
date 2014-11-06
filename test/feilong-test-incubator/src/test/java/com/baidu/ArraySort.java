package com.baidu;
import java.util.Arrays;
import java.util.Comparator;

public class ArraySort{

	public static void main(String[] args){
		String[] arrays = { "almn", "fba", "cba", "aa", "ab", "bb", "sadfafda", "kadfafda", "cc" };

		Arrays.sort(arrays, new Comparator<String>(){

			public int compare(String s1,String s2){
				Integer length = s1.length();
				Integer length2 = s2.length();

				//先判断长度，长度比较
				int compareTo = length.compareTo(length2);

				//如果长度相等，那么比较自己本身的顺序
				if (0 == compareTo){
					compareTo = s1.compareTo(s2);
				}
				return compareTo;
			}
		});
		for (int i = 0; i < arrays.length; i++){
			System.out.println(arrays[i]);
		}
	}
}