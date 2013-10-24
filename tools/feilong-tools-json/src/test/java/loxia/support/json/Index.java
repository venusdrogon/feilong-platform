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
package loxia.support.json;


/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 19, 2012 12:43:36 AM
 */
public class Index{


	private String				minute;

	private Integer				createCount;

	private Integer				cancelCount;

	/**
	 * @return the minute
	 */
	public String getMinute(){
		return minute;
	}

	/**
	 * @param minute
	 * @param createCount
	 */
	public Index(String minute, Integer createCount){
		super();
		this.minute = minute;
		this.createCount = createCount;
	}

	/**
	 * @param minute
	 * @param createCount
	 * @param cancelCount
	 */
	public Index(String minute, Integer createCount, Integer cancelCount){
		super();
		this.minute = minute;
		this.createCount = createCount;
		this.cancelCount = cancelCount;
	}

	/**
	 * @param minute
	 *            the minute to set
	 */
	public void setMinute(String minute){
		this.minute = minute;
	}

	/**
	 * @return the createCount
	 */
	public Integer getCreateCount(){
		return createCount;
	}

	/**
	 * @param createCount
	 *            the createCount to set
	 */
	public void setCreateCount(Integer createCount){
		this.createCount = createCount;
	}

	/**
	 * @return the cancelCount
	 */
	public Integer getCancelCount(){
		return cancelCount;
	}

	/**
	 * @param cancelCount
	 *            the cancelCount to set
	 */
	public void setCancelCount(Integer cancelCount){
		this.cancelCount = cancelCount;
	}

}
