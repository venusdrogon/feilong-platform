package com.feilong.commons.core.bean.command;

public class Customer{

	private long		id;

	private String		name;

	private Address[]	addresses;

	public Customer(){}

	public Customer(long id, String name, Address[] addresses){
		this.id = id;
		this.name = name;
		this.addresses = addresses;
	}

	public Address[] getAddresses(){
		return addresses;
	}

	public void setAddresses(Address[] addresses){
		this.addresses = addresses;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
}
