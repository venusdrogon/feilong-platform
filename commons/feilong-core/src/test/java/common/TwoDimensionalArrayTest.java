/*
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
package common;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.entity.JoinStringEntity;
import com.feilong.commons.core.util.ArrayUtil;

/**
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年6月13日 下午8:41:24
 * @since 1.0.7
 */
public class TwoDimensionalArrayTest{

	private static final Logger	log	= LoggerFactory.getLogger(TwoDimensionalArrayTest.class);

	private Integer[][]			array;

	@Before
	public void init(){
		int i = 9;

		//**************构造二维数组************************************
		array = new Integer[i][i];

		for (int j = 0; j < i; ++j){
			array[j] = new Integer[i];

			for (int k = 0; k < i; ++k){
				array[j][k] = i * j + 1 + k;//由于值是从1 开始的,而循环的索引是从0开始的,固需要+1, 处理
			}
		}

		//***************************************************************
		if (log.isDebugEnabled()){
			for (int j = 0; j < array.length; ++j){
				String string = ArrayUtil.toString(array[j], new JoinStringEntity());
				System.out.println(string);
			}
		}

		//***************************************************************
		System.out.println();
		//***************************************************************
	}

	@Test
	public final void test(){
		//总行数
		int rows = array.length;
		//第几行
		for (int row = 0; row < rows; ++row){
			//每一行循环 开始数字 索引列
			int columns = beginColumns(rows, row);

			//columns 列数
			for (int column = columns; column >= 0; --column){
				//以输出整行为出发点
				//row&column 定位 这行输出起始坐标点
				System.out.println(getCurrentLineString(rows, row, column));
			}
		}
	}

	/**
	 * 获得这行输出的结果
	 * 
	 * @param rows
	 *            总行数
	 * @param row
	 *            输出起始行
	 * @param column
	 *            输出起始列
	 * @return
	 */
	private StringBuilder getCurrentLineString(int rows,int row,int column){
		//-------------------------------------------------------------------
		//concatCount表示 当前这个数字 最大连接数字 个数
		int concatCount = (rows - row) - column;

		StringBuilder sb = new StringBuilder();
		//line控制当前行 输出数字的数量
		for (int line = 0; line < concatCount; ++line){
			sb.append(array[row + line][column + line]);
			if (line != concatCount - 1){//不是最后一行 添加个 ""
				sb.append(" ");
			}
		}
		//-------------------------------------------------------------------
		return sb;
	}

	/**
	 * 倒序开始迭代索引,第一行会从 i-1开始,其余行都会从0开始
	 * 
	 * @param i
	 * @param totalLine
	 * @return
	 */
	private int beginColumns(int i,int totalLine){
		//这一行可用循环倒序 索引
		//比如第一行是 i个
		//第二行到第i行都是0
		if (totalLine == 0){
			return i - 1;
		}
		return 0;
	}

	@Test
	public void printArray(){
		int n = array[0].length;
		if (n == 0)
			return;
		if (n == 1){
			System.out.println(array[0][0]);
			return;
		}

		int i = 0, j = n - 1;

		for (int k = 0; k < n * n; k++){
			System.out.print(array[i++][j++] + " ");

			//对角线上部,起始横坐标j与终止纵坐标i相加为n,横坐标要累减,因此下一个起始横坐标j为n-i-1
			if (j > n - 1 && i < n){//
				System.out.println();
				j = n - i - 1;
				i = 0;
			}
			//对角线下部,起始纵坐标i与终止横坐标j相加为n,纵坐标要累加,因此下一个起始纵坐标i为n-j+1
			else if (i > n - 1 && j < n){
				System.out.println();
				i = n - j + 1;
				j = 0;
			}
			//对角线,要由对角线上部转到对角线下部
			else if (i == n && j == n){
				System.out.println();
				i = 1;
				j = 0;
			}

		}
	}

}
