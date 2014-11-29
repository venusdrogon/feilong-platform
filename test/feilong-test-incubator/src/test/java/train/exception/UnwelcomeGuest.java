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
package train.exception;

/**
 * The Class UnwelcomeGuest.
 */
public class UnwelcomeGuest{

	/** The Constant GUEST_USER_ID. */
	public static final long	GUEST_USER_ID	= -1;

	/** The Constant USER_ID. */
	private static final long	USER_ID			= getUserIdOrGuest();

	/**
	 * 获得 user id or guest.
	 *
	 * @return the user id or guest
	 */
	private static long getUserIdOrGuest(){
		try{
			return getUserIdFromEnvironment();
		}catch (IdUnavailableException e){
			System.out.println("Logging in as guest");
			return GUEST_USER_ID;
		}
	}

	/**
	 * 获得 user id from environment.
	 *
	 * @return the user id from environment
	 * @throws IdUnavailableException
	 *             the id unavailable exception
	 */
	private static long getUserIdFromEnvironment() throws IdUnavailableException{
		throw new IdUnavailableException();
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the args
	 */
	public static void main(String[] args){
		System.out.println("User ID: " + USER_ID);
	}
}

class IdUnavailableException extends Exception{}
