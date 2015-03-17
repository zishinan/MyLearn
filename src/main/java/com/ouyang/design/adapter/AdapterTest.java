package com.ouyang.design.adapter;

/**
 * 适配器模式
 * @author yangxi
 *
 */
public class AdapterTest {
	public static void main(String[] args) {
		Adapter adapter = new Adapter();
		adapter.saySomething();
		adapter.doSomething();
	}
}
