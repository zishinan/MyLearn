package com.ouyang.test;



public class TestPrint {
	public static void main(String[] args) {
		String aString = "aaa";
		String bString = aString;
		
		bString = bString + "aaaa";
		System.out.println(aString);
		System.out.println(bString);
	}
}
