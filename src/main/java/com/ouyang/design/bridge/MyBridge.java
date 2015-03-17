package com.ouyang.design.bridge;

public class MyBridge extends Bridge {
	@Override
	public void method() {
		getSource().method();
		System.out.println("and do some thing");
	}
}
