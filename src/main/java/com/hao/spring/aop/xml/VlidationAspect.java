package com.hao.spring.aop.xml;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

public class VlidationAspect {

	public static final String TAG = "VlidationAspect: ";
	
	public void vlidationArgs(JoinPoint joinPoint) {
		System.out.println(TAG +"The method " +joinPoint.getSignature().getName() +" begins with " +Arrays.asList(joinPoint.getArgs()));
	}
}
