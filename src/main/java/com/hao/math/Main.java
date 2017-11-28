package com.hao.math;

public class Main {

	public static void main(String[] args) {

//		try {
//			Class cls = Class.forName("com.hao.math.ArithmeticImpl");
//			Arithmetic arithmetic = (Arithmetic) cls.newInstance();
//			Double result = arithmetic.add(1.0, 2.0);
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		Arithmetic target = new ArithmeticImpl();
		
		Arithmetic proxy = new ArithmeticLoggingProxy(target).getLoggingProxy();
		
		Double r;
		r = proxy.add(1.0, 2.0);
		System.out.println(r);
		
		r = proxy.div(1.0, 2.0);
		System.out.println(r);
	}
}
