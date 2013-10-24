package cn.javass.spring.chapter6;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.javass.spring.chapter6.service.IHelloWorldService;

public class AopTest{

	@Test
	public void testHelloworld(){
		BeanFactory beanFactory;
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test.xml");
		IHelloWorldService helloworldService = ctx.getBean("helloWorldService", IHelloWorldService.class);
		helloworldService.sayHello();
	}
}
