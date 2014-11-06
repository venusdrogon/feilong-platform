package com.baidu;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.childandparent.Child;
import com.feilong.childandparent.Parent;

public class Student{

	private static final Logger	log	= LoggerFactory.getLogger(Student.class);

	public static void main(String[] args){
		Stu stu1 = new Stu(18, "sm", 510);
		Stu stu2 = new Stu(18, "wm", 520);
		Stu stu3 = new Stu(18, "qm", 530);
		Stu stu4 = new Stu(18, "rm", 540);
		Stu stu5 = new Stu(18, "qy", 550);
		Stu stu6 = new Stu(18, "qr", 560);
		System.out.println(Stu.getTotalFee());
	}

	static class Stu{

		int			age;

		String		name;

		int			fee;

		static int	totalFee;

		public Stu(int age, String name, int fee){
			this.age = age;
			this.name = name;
			totalFee += fee;
		}

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