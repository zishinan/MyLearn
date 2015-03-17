package com.ouyang.design.adapter;

public class Adapter extends Source implements Targetable {

	@Override
	public void doSomething() {
		System.out.println("do something");
	}

}
