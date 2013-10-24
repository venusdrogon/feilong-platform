/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.commons.core;

/**
 * Class that exposes the Spring version. Fetches the "Implementation-Version" manifest attribute from the jar file.
 * <p>
 * Note that some ClassLoaders do not expose the package metadata, hence this class might not be able to determine the Spring version in all
 * environments. Consider using a reflection-based check instead: For example, checking for the presence of a specific Spring 2.0 method
 * that you intend to call.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Aug 12, 2013 1:21:20 AM
 */
public class FeiLongVersion{

	/**
	 * Return the full version feilong of the present feilong codebase, or <code>null</code> if it cannot be determined.
	 * 
	 * @see java.lang.Package#getImplementationVersion()
	 */
	public static String getVersion(){
		Package pkg = FeiLongVersion.class.getPackage();
		return (pkg != null ? pkg.getImplementationVersion() : null);
	}

}
