/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package elsepackage;

/**
 * 雷劈数：有位叫卡普利加的印度数学家。他在一次旅行中，遇到猛烈的暴风雨，电闪雷鸣过后，他看到路边一块牌子，被雷电劈成了两半，一半上写着30，另一半写着25。<br>
 * 这时，卡普利加的脑中忽然发现了一个绝妙的数学关系：30+25=55 55^2=3025，把劈成两半的数加起来，再平方，正好是原来的数字。<br>
 * 按照第一个发现者的名字，这种怪数被命名为“卡普利加数”或“雷劈数”。
 * 
 * <pre>
 * N=1
 * 　　81 = (8 + 1)^2
 * N=2
 * 　　2025 = (20 + 25)^2
 * 　　3025 = (30 + 25)^2
 * N=3
 * 　　494209 = (494 + 209)^2
 * N=4
 * 　　24502500 = (2450 + 2500)^2
 * 　　25502500 = (2550 + 2500)^2
 * 　　52881984 = (5288 + 1984)^2
 * 　　60481729 = (6048 + 1729)^2
 * N=5
 * 　　6049417284 = (60494 + 17283)^2
 * 　　6832014336 = (68320 + 14336)^2
 * N=6
 * 　　20408122449 = ( 20408 + 122449)^2
 * 　　21948126201 = ( 21948 + 126201)^2
 * 　　33058148761 = ( 33058 + 148761)^2
 * 　　35010152100 = ( 35010 + 152100)^2
 * 　　43470165025 = ( 43470 + 165025)^2
 * 　　101558217124 = (101558 + 217124)^2
 * 　　2位的是81，
 * 　　4位的是2025，3025，9801，
 * 　　6位的 494209，998001，
 * 　　8位的24502500，25502500，52881984，60481729，99980001，
 * 　　10位的6049417284，6832014336，9048004641，9999800001，
 * 　　用程序来计算的话快的多也准确的多
 * 
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-16 上午12:20:55
 */
public class Lightning{

	/**
	 * isDemo(long num)判断参数num是否为雷劈数
	 * 
	 * @param num
	 * @return
	 */
	public boolean isDemon(long num){
		//num1、num2是分出来的数  
		long num1, num2, t_num;
		String ts = String.valueOf(num);
		int length = ts.length();
		//负数  
		if (ts.startsWith("-")){
			return false;
		}
		//位数小于两位  
		if (ts.length() < 2){
			return false;
		}
		//奇数位  
		if (length % 2 != 0){
			return false;
		}
		num1 = Long.parseLong(ts.substring(0, length / 2));
		num2 = Long.parseLong(ts.substring(length / 2, length));
		t_num = num1 + num2;
		if (t_num * t_num == num){
			return true;
		}
		return false;
	}

	public static void main(String args[]){
		Lightning d = new Lightning();
		for (long i = 10; i < 999999999; i++){
			if (d.isDemon(i)){
				System.out.print(i + "  ");
			}
		}
	}
}
