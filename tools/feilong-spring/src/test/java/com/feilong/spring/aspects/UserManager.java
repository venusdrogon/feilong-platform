package com.feilong.spring.aspects;


//@Log
public interface UserManager{

	void addUser(String string,String name);

	void delUser(int id);

	void modifyUser(int id,String name,int age);
}
