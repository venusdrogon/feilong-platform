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
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 4, 2014 11:55:35 AM
 */
public class Test1{

	/**
	 * The Class FirstException.
	 */
	static class FirstException extends Exception{

		/**
		 * 
		 */
		private static final long	serialVersionUID	= 1L;
	}

	/**
	 * The Class SecondException.
	 */
	static class SecondException extends Exception{

		/**
		 * 
		 */
		private static final long	serialVersionUID	= 1L;
	}

	/**
	 * Rethrow exception.
	 *
	 * @param exceptionName
	 *            the exception name
	 * @throws FirstException
	 *             the first exception
	 * @throws SecondException
	 *             the second exception
	 */
	public void rethrowException(String exceptionName) throws FirstException,SecondException{
		try{
			if (exceptionName.equals("First")){
				throw new FirstException();
			}else{
				throw new SecondException();
			}
		}catch (Exception e){
			throw e;
		}
	}
}
