package cn.javass.spring.chapter6.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.javass.spring.chapter6.service.IHelloWorldService;

@SuppressWarnings("all")
public class HelloWorldService implements IHelloWorldService{

	private static final Logger	log	= LoggerFactory.getLogger(HelloWorldService.class);

	@Override
	public void sayHello(){
		log.info("============Hello World!");
	}
}
