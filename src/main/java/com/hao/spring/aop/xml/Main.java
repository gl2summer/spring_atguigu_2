package com.hao.spring.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//1.
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-aop-xml.xml");
		//2.
		Arithmetic arithmetic = ctx.getBean("arithmetic", Arithmetic.class);
		//3.
		Double r = arithmetic.div(3.0, 1.0);
		System.out.println("result: " +r);
		
//		r = arithmetic.sub(1.0, 2.0);
//		System.out.println("result: " +r);
	}

}
