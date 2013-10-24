package com.feilong.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 *用户
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-22 下午09:49:19
 */
public class User implements Serializable{

	public User(){
		super();
	}

	public User(int id, String name, Date createDate){
		super();
		this.id = id;
		this.createDate = createDate;
		this.name = name;
	}

	private static final long	serialVersionUID	= -2163104816950892085L;

	private int					id;

	private Date				createDate;

	private String				name;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
}
