package com.hao.spring.aop.xml;


import java.util.Arrays;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//把这个类声明为一个切面：需要把该类放到IOC容器中，再声明为了一个切面
public class LoggingAspect {

	public static final String TAG = "LoggingAspect: ";
	
	public void beforeMethod(JoinPoint joinPoint) {
		
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println(TAG +"The method " +methodName +" begins with " +args + " ");
	}
	
	public void afterMethod(JoinPoint joinPoint) {
		
		String methodName = joinPoint.getSignature().getName();
		System.out.println(TAG +"The method " +methodName +" ends");
	}
	
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println(TAG +"The method " +methodName +" ends with " +result);
	}
	
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println(TAG +"The method " +methodName +" occurs exception " +e);
	}
	
	public Object aroundMethod(ProceedingJoinPoint pjd) {
		
		Object result = null;
		String methodName = pjd.getSignature().getName();
		List<Object> args = Arrays.asList(pjd.getArgs());
		
		try {
			//前置 通知
			System.out.println(TAG +"The method " +methodName +" begins with " +args + " ");
			//执行目标方法
			result = pjd.proceed();
			//返回通知
			System.out.println(TAG +"The method " +methodName +" ends with " +result);
		}catch (Throwable e) {
			//异常通知
			System.out.println(TAG +"The method " +methodName +" occurs exception" +e);
			throw new RuntimeException(e);
		}
		//后置通知
		System.out.println(TAG +"The method " +methodName +" ends");
		return 100.0;
	}
}
