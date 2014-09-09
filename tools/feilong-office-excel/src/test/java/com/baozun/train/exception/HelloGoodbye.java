package com.baozun.train.exception;

public class HelloGoodbye{

	public static void main(String[] args){
		try{
			System.out.println("Hello world");
			System.exit(0);
			System.out.println("Goodbye world");
		}finally{
			System.out.println("Goodbye world");
		}
	}
}
