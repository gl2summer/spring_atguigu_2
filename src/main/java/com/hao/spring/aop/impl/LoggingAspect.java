package com.hao.spring.aop.impl;


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
@Order(2)
@Aspect
@Component
public class LoggingAspect {

	public static final String TAG = "LoggingAspect: ";
	
	/**
	 * 定义一个方法，用于声明切入点表达式，一般地，该方法中再不需要添入其他代码
	 * 使用@Pointcut来声明切入点表达式
	 * 后面的其他通知使用方法名来引用当前的切入点表达式。
	 */
	@Pointcut("execution(public * com.hao.spring.aop.impl.Arithmetic.*(..))")
	public void declareJointPointExpression() {}
	
	//声明该方法是一个前置通知：在目标方法开始之前执行
	@Before("declareJointPointExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println(TAG +"The method " +methodName +" begins with " +args + " ");
	}
	
	//后置通知：在目标方法执行后（无论是否发生异常），执行的通知
	//在后置通知中还不能访问目标方法执行的结果
	@After("declareJointPointExpression()")
	public void afterMethod(JoinPoint joinPoint) {
		
		String methodName = joinPoint.getSignature().getName();
		System.out.println(TAG +"The method " +methodName +" ends");
	}
	
	//返回通知：是可以访问到方法的返回值的！
	@AfterReturning(value="declareJointPointExpression()",
			returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println(TAG +"The method " +methodName +" ends with " +result);
	}
	
	//异常通知
	@AfterThrowing(value="declareJointPointExpression()",
			throwing="e")
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println(TAG +"The method " +methodName +" occurs exception " +e);
	}
	
	//环绕通知需要携带ProceedingJoinPoint类型的参数
	//环绕通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法
	//且返回值必须有返回值，返回即为目标方法的返回值
	@Around(value="execution(public * com.hao.spring.aop.impl.Arithmetic.*(..))")
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
