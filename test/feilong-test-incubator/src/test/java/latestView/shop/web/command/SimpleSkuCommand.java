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
package latestView.shop.web.command;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 简单 sku command ,一般用于 渠道已知的用户 显示 最近访问的历史记录 ...<br>
 * 仅包括:
 * <ul>
 * <li>id</li>
 * <li>code</li>
 * <li>name</li>
 * <li>listPrice</li>
 * <li>fobPirce</li>
 * </ul>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-6 下午1:29:03
 */
public class SimpleSkuCommand implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** id. */
	private Long				id;

	/** code. */
	private String				code;

	/** name. */
	private String				name;

	/** listPrice 售价. */
	private BigDecimal			listPrice;

	/** fobPirce 成本价. */
	private BigDecimal			fobPirce;

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId(){
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id){
		this.id = id;
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode(){
		return code;
	}

	/**
	 * Sets the code.
	 * 
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Gets the listPrice 售价.
	 * 
	 * @return the listPrice
	 */
	public BigDecimal getListPrice(){
		return listPrice;
	}

	/**
	 * Sets the listPrice 售价.
	 * 
	 * @param listPrice
	 *            the listPrice to set
	 */
	public void setListPrice(BigDecimal listPrice){
		this.listPrice = listPrice;
	}

	/**
	 * Gets the fobPirce 成本价.
	 * 
	 * @return the fobPirce
	 */
	public BigDecimal getFobPirce(){
		return fobPirce;
	}

	/**
	 * Sets the fobPirce 成本价.
	 * 
	 * @param fobPirce
	 *            the fobPirce to set
	 */
	public void setFobPirce(BigDecimal fobPirce){
		this.fobPirce = fobPirce;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if (null == obj && null == this){
			return true;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		SimpleSkuCommand simpleSkuCommand = (SimpleSkuCommand) obj;
		// code 相同 表示 一样
		if (simpleSkuCommand.code.equals(this.code)){
			return true;
		}
		return false;
	}
}
