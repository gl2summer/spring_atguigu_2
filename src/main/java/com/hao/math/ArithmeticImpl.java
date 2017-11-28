package com.hao.math;

public class ArithmeticImpl implements Arithmetic {

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
		return r;
	}

}
