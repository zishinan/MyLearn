package com.ouyang.test;

import org.junit.Test;

public class TestTyr {
	public int testTry() throws Exception {
		int i = 0;
		try {
			System.out.println("try");
			return i;
		} catch (Exception e) {
			System.out.println("catch");
		}finally{
			i = 1;
			System.out.println("finally");
		}
		return i;
	}
	
	@Test
	public void testT() throws Exception {
		System.out.println("befor");
		System.out.println(testTry());
		System.out.println("after");
	}
}
