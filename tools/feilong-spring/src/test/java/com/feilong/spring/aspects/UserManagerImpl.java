package com.feilong.spring.aspects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.spring.aop.Log;

public class UserManagerImpl implements UserManager{

	private static final Logger	log	= LoggerFactory.getLogger(UserManagerImpl.class);

	@Log(level="debug")
	public void addUser(String id,String name){
		log.info("---UsreManagerImpl中的addUser方法的实现-----");
	}

	public void delUser(int id){
		log.info("-----UserManagerImpl delUser方法的实现-----");
	}

	public void modifyUser(int id,String name,int age){
		log.info("----UserManagerImpl modifyUser方法的实现-----");
	}
}
