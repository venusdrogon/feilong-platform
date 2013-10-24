package com.feilong.taglib.common.navigation;

import java.io.Serializable;

/**
 * 飞龙面包屑所需要的字段 封装<br>
 * 其中泛型中的T 是 id 主键的类型,可以是Number String 或者其他类型.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-16 下午12:55:43
 */
public class SiteMapEntity<T> implements Serializable{

	private static final long	serialVersionUID	= -2739340747216481761L;

	/** current id, 可以是Number String 或者其他类型.. */
	private T					id;

	/** name,用于拼接. */
	private String				name;

	/** title. */
	private String				title;

	/** 匹配的路径. */
	private String				requestMapping;

	/** parent id ,可以是Number String 或者其他类型.. */
	private T					parentId;

	/**
	 * Instantiates a new site map entity.
	 */
	public SiteMapEntity(){
		super();
	}

	/**
	 * Instantiates a new site map entity.
	 * 
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param title
	 *            the title
	 * @param requestMapping
	 *            the request mapping
	 * @param parentId
	 *            the parent id
	 */
	public SiteMapEntity(T id, String name, String title, String requestMapping, T parentId){
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.requestMapping = requestMapping;
		this.parentId = parentId;
	}

	/**
	 * Gets the name,用于拼接.
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the name,用于拼接.
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
	 * Gets the 匹配的路径.
	 * 
	 * @return the requestMapping
	 */
	public String getRequestMapping(){
		return requestMapping;
	}

	/**
	 * Sets the 匹配的路径.
	 * 
	 * @param requestMapping
	 *            the requestMapping to set
	 */
	public void setRequestMapping(String requestMapping){
		this.requestMapping = requestMapping;
	}

	/**
	 * @return the id
	 */
	public T getId(){
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(T id){
		this.id = id;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(T parentId){
		this.parentId = parentId;
	}

	/**
	 * @return the parentId
	 */
	public T getParentId(){
		return parentId;
	}
}
