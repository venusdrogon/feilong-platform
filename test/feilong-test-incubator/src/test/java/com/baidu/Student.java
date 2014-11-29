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
package com.baidu;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.childandparent.Child;
import com.feilong.childandparent.Parent;

/**
 * The Class Student.
 */
public class Student{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(Student.class);

	/**
	 * The main method.
	 *
	 * @param args
	 *            the args
	 */
	public static void main(String[] args){
		Stu stu1 = new Stu(18, "sm", 510);
		Stu stu2 = new Stu(18, "wm", 520);
		Stu stu3 = new Stu(18, "qm", 530);
		Stu stu4 = new Stu(18, "rm", 540);
		Stu stu5 = new Stu(18, "qy", 550);
		Stu stu6 = new Stu(18, "qr", 560);
		System.out.println(Stu.getTotalFee());
	}

	/**
	 * The Class Stu.
	 */
	static class Stu{

		/** The age. */
		int			age;

		/** The name. */
		String		name;

		/** The fee. */
		int			fee;

		/** The total fee. */
		static int	totalFee;

		/**
		 * The Constructor.
		 *
		 * @param age
		 *            the age
		 * @param name
		 *            the name
		 * @param fee
		 *            the fee
		 */
		public Stu(int age, String name, int fee){
			this.age = age;
			this.name = name;
			totalFee += fee;
		}

		/**
		 * 获得 total fee.
		 *
		 * @return the total fee
		 */
		public static int getTotalFee(){
			return totalFee;
		}

	}

	/**
	 * TestStudent.
	 */
	@Test
	public void testStudent(){
		Parent parent = new Child();
		parent.func(5);

		Child child = new Child();
		//child.func(5);
	}

	/**
	 * TestStudent.
	 */
	@Test
	public void testStudent2(){

		int count = 0;
		for (int i = 0; i < 10; ++i){
			count = count++;
		}
		System.out.println(count);
	}
}