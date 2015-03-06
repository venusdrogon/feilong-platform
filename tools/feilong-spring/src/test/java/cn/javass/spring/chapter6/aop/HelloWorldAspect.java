package cn.javass.spring.chapter6.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DateExtensionUtil;

@SuppressWarnings("all")
public class HelloWorldAspect{

    private static final Logger log = LoggerFactory.getLogger(HelloWorldAspect.class);

    private Date                begin;

    private Date                end;

    // 前置通知
    public void beforeAdvice(){
        begin = new Date();
        log.info("1.......before advice,{}", begin);
    }

    // 后置最终通知
    public void afterFinallyAdvice(){
        end = new Date();
        log.info("2.......after finally advice,{},耗时:{}", begin, DateExtensionUtil.getIntervalForView(begin, end));
    }

    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object[] args = { "a" };
        proceedingJoinPoint.proceed();
        log.info("around");
    }
}
