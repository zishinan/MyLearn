package com.ouyang.design.bridge;

public class TestBridge {
	public static void main(String[] args) {
		Bridge bridge = new MyBridge();
		
		Sourceable source = new Sourcesub1();
		
		bridge.setSource(source);
		bridge.method();
		
		source = new Sourcesub2();
		
		bridge.setSource(source);
		bridge.method();
	}

}
