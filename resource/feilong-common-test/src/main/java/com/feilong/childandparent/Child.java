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
 * The Class Child.
 */
@SuppressWarnings("all")
public class Child extends Parent{

	/** The age. */
	private String	age;

	/**
	 * The Constructor.
	 */
	public Child(){}

	/**
	 * The Constructor.
	 *
	 * @param name
	 *            the name
	 * @param address
	 *            the address
	 */
	public Child(String name, String address){
		super(name, address);
		this.age = age;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.childandparent.Parent#func(java.lang.Integer[])
	 */
	public void func(Integer[] a){
		System.out.println("Child");
	};

	/**
	 * 设置 age.
	 *
	 * @param age
	 *            the age
	 */
	public void setAge(String age){
		this.age = age;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Child other = (Child) obj;
		if (age == null){
			if (other.age != null)
				return false;
		}else if (!age.equals(other.age))
			return false;
		return true;
	}

	/**
	 * 获得 age.
	 *
	 * @return the age
	 */
	public String getAge(){
		return age;
	}
}
