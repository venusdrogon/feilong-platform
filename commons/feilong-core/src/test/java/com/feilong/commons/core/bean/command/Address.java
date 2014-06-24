/** Address.java */
package com.feilong.commons.core.bean.command;

public class Address{

	private String	zipCode;

	private String	addr;

	private String	city;

	private String	country;

	public Address(){}

	public Address(String zipCode, String addr, String city, String country){
		this.zipCode = zipCode;
		this.addr = addr;
		this.city = city;
		this.country = country;
	}

	public String getAddr(){
		return addr;
	}

	public void setAddr(String addr){
		this.addr = addr;
	}

	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCountry(){
		return country;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getZipCode(){
		return zipCode;
	}

	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}

}

/** Customer.java */
