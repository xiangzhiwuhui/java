package com.ssl.nio;

import java.util.function.Function;


public class Test {
	public static void main(String[] args) {
		int[] a = {1,3,5,2,8,6};
		Function<Integer, Integer> mf = (x) -> x * x;
		Test t = new Test();
		System.out.println(t.multi(10, mf));
	}
	
	public Integer multi(int x, Function<Integer, Integer> mf){
		return mf.apply(x);
	}
}


