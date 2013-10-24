package com.feilong.entity;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class User implements InitializingBean,DisposableBean{

	private final static Logger	log	= LoggerFactory.getLogger(User.class);

	private String				userName;

	private List<String>		list;

	private Map<String, Object>	map;

	private Properties			properties;

	private Set<String>			set;

	private Vector<Object>		vector;

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public void init(){
		log.info("init.....");
		this.userName = "feilong";
	}

	@Override
	public void afterPropertiesSet() throws Exception{
		log.info("afterPropertiesSet.....");
	}

	@Override
	public void destroy() throws Exception{
		log.info("destroy.....");
		this.userName = null;
	}

	public List<String> getList(){
		return list;
	}

	public void setList(List<String> list){
		this.list = list;
	}

	public Map<String, Object> getMap(){
		return map;
	}

	public void setMap(Map<String, Object> map){
		this.map = map;
	}

	public Properties getProperties(){
		return properties;
	}

	public void setProperties(Properties properties){
		this.properties = properties;
	}

	public Set<String> getSet(){
		return set;
	}

	public void setSet(Set<String> set){
		this.set = set;
	}

	public Vector<Object> getVector(){
		return vector;
	}

	public void setVector(Vector<Object> vector){
		this.vector = vector;
	}
}
