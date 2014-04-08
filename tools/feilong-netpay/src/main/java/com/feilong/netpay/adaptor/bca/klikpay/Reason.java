package com.feilong.netpay.adaptor.bca.klikpay;

import java.io.Serializable;

/**
 * The Class Reason.
 */
public class Reason implements Serializable{

	private static final long	serialVersionUID	= 5331964326828362389L;

	/** The english. */
	private String				english;

	/** The indonesian. */
	private String				indonesian;

	/**
	 * Gets the indonesian.
	 * 
	 * @return the indonesian
	 */
	public String getIndonesian(){
		return indonesian;
	}

	/**
	 * Sets the indonesian.
	 * 
	 * @param indonesian
	 *            the indonesian to set
	 */
	public void setIndonesian(String indonesian){
		this.indonesian = indonesian;
	}

	/**
	 * Gets the english.
	 * 
	 * @return the english
	 */
	public String getEnglish(){
		return english;
	}

	/**
	 * Sets the english.
	 * 
	 * @param english
	 *            the english to set
	 */
	public void setEnglish(String english){
		this.english = english;
	}
}