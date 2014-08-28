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
package com.feilong.childandparent;

/**
 * The Class Parent.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-9-16 上午11:12:08
 * @since 1.0
 */
public class Parent{

	/** The name. */
	private String	name;

	/** The address. */
	private String	address	= null;

	public Parent(){
		super();
	}

	public Parent(String name, String address){
		this.name = name;
		this.address = address;
	}

	public void func(Integer...a){
		System.out.println("Parent");
	};

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
	 *            the new name
	 */
	public void setName(String name){
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (!(obj instanceof Parent)){
			return false;
		}
		Parent other = (Parent) obj;
		if (name == null){
			if (other.name != null){
				return false;
			}
		}else if (!name.equals(other.name)){
			return false;
		}
		return true;
	}

	/**
	 * Gets the address.
	 * 
	 * @return the address
	 */
	public String getAddress(){
		return address;
	}

	/**
	 * Sets the address.
	 * 
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address){
		this.address = address;
	}
}
