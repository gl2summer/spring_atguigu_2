package com.hao.math;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticLoggingProxy {

	//要代理的对象
	private Arithmetic target;
	
	public ArithmeticLoggingProxy(Arithmetic target) {
		super();
		this.target = target;
	}

	public Arithmetic getLoggingProxy() {
		Arithmetic proxy = null;
		
		//代理对象由哪一个类加载器负责加载
		ClassLoader loader = target.getClass().getClassLoader();
		
		//代理对象的类型，即其中有哪些方法
		@SuppressWarnings("rawtypes")
		Class[] interfaces = new Class[]{Arithmetic.class};
		
		//当调用代理对象其中的方法时，该执行的代码
		InvocationHandler h = new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//打印前置日志
				System.out.println("The method " +method.getName() + " begin whit " +Arrays.asList(args));
				//执行方法
				Object result = null;
				try {
					result = method.invoke(target, args);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//打印后置日志
				System.out.println("The method " +method.getName() + " ends whit " +result);
				return result;
			}
		};
		
		proxy = (Arithmetic)Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
}
