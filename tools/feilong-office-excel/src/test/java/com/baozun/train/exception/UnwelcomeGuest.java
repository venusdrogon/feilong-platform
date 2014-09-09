package com.baozun.train.exception;

public class UnwelcomeGuest{

	public static final long	GUEST_USER_ID	= -1;

	private static final long	USER_ID			= getUserIdOrGuest();

	private static long getUserIdOrGuest(){
		try{
			return getUserIdFromEnvironment();
		}catch (IdUnavailableException e){
			System.out.println("Logging in as guest");
			return GUEST_USER_ID;
		}
	}

	private static long getUserIdFromEnvironment() throws IdUnavailableException{
		throw new IdUnavailableException();
	}

	public static void main(String[] args){
		System.out.println("User ID: " + USER_ID);
	}
}

class IdUnavailableException extends Exception{}
