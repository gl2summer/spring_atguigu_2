package com.hao.spring.aop.xml;

import org.springframework.stereotype.Component;

public class Arithmetic {

	public Double add(Double a, Double b) {
		Double r = a + b;
		return r;
	}

	public Double sub(Double a, Double b) {
		Double r = a - b;
		return r;
	}

	public Double mul(Double a, Double b) {
		Double r = a * b;
		return r;
	}

	public Double div(Double a, Double b) {
		Double r = a / b;
		if(b==0.0)throw new NullPointerException();
		return r;
	}

}
