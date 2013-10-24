package com.feilong.spring.aspects;

import org.aspectj.lang.JoinPoint;

public interface MySecurityManager{

	public void security(JoinPoint joinPoint);

}
