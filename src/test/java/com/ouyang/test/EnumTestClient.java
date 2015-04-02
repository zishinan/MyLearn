package com.ouyang.test;

import com.ouyang.thinkinjava.EnumTest;


public class EnumTestClient {
	public static void main(String[] args) {
		for (EnumTest name : EnumTest.values()) {
			System.out.println(name.name() + " : " + name.getContext());
		}
		System.out.println(EnumTest.FRANK.getContext());
		System.out.println(EnumTest.FRANK.getDeclaringClass());
	}
}
