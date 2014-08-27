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
package designpattern.observer;

import java.util.Observable;

/**
 * The Class House.
 */
public class House extends Observable{

	/** The price. */
	private float	price;

	/**
	 * The Constructor.
	 *
	 * @param price
	 *            the price
	 */
	public House(float price){
		this.price = price;
	}

	/**
	 * 获得 price.
	 *
	 * @return the price
	 */
	public float getPrice(){
		return price;
	}

	/**
	 * 设置 price.
	 *
	 * @param price
	 *            the price
	 */
	public void setPrice(float price){
		super.setChanged();
		// 设置变化点  
		super.notifyObservers(price);
		// 通知所有观察者价格改变  
		this.price = price;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "房子价格为：" + this.price;
	}
}