package com.feilong.controller.chart.mp2.person;

import java.io.Serializable;

/**
 * 解析 excel.
 * 
 * @author 金鑫 2010-7-7 上午11:44:53
 */
public class Mp2PersonInfo implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 姓名. */
	private String				name;

	/** title. */
	private String				title;

	/** The level. */
	private String				level;

	/** The entry time. */
	private String				entryTime;

	/** The join time. */
	private String				joinTime;

	/** The memo. */
	private String				memo;

	/** 护照办理. */
	private String				passportCase;

	/** 护照状态. */
	private Integer				passportStatus;

	private String				mark;

	/**
	 * Gets the 姓名.
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the 姓名.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * Gets the level.
	 * 
	 * @return the level
	 */
	public String getLevel(){
		return level;
	}

	/**
	 * Sets the level.
	 * 
	 * @param level
	 *            the level to set
	 */
	public void setLevel(String level){
		this.level = level;
	}

	/**
	 * Gets the entry time.
	 * 
	 * @return the entryTime
	 */
	public String getEntryTime(){
		return entryTime;
	}

	/**
	 * Sets the entry time.
	 * 
	 * @param entryTime
	 *            the entryTime to set
	 */
	public void setEntryTime(String entryTime){
		this.entryTime = entryTime;
	}

	/**
	 * Gets the join time.
	 * 
	 * @return the joinTime
	 */
	public String getJoinTime(){
		return joinTime;
	}

	/**
	 * Sets the join time.
	 * 
	 * @param joinTime
	 *            the joinTime to set
	 */
	public void setJoinTime(String joinTime){
		this.joinTime = joinTime;
	}

	/**
	 * Gets the memo.
	 * 
	 * @return the memo
	 */
	public String getMemo(){
		return memo;
	}

	/**
	 * Sets the memo.
	 * 
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo){
		this.memo = memo;
	}

	/**
	 * Gets the 护照办理.
	 * 
	 * @return the passportCase
	 */
	public String getPassportCase(){
		return passportCase;
	}

	/**
	 * Sets the 护照办理.
	 * 
	 * @param passportCase
	 *            the passportCase to set
	 */
	public void setPassportCase(String passportCase){
		this.passportCase = passportCase;
	}

	/**
	 * Gets the 护照状态.
	 * 
	 * @return the passportStatus
	 */
	public Integer getPassportStatus(){
		return passportStatus;
	}

	/**
	 * Sets the 护照状态.
	 * 
	 * @param passportStatus
	 *            the passportStatus to set
	 */
	public void setPassportStatus(Integer passportStatus){
		this.passportStatus = passportStatus;
	}

	/**
	 * @return the mark
	 */
	public String getMark(){
		return mark;
	}

	/**
	 * @param mark
	 *            the mark to set
	 */
	public void setMark(String mark){
		this.mark = mark;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid(){
		return serialVersionUID;
	}

}
