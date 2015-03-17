package com.ouyang.javapuzzlers;

public class JavaDoubt
{
	public static void main(String[] args)
	{
		System.out.println(2.00 - 1.10);
		System.out.println(24L * 60 * 60 * 1000 * 1000);
		System.out.println(12345 + 5432l);
		System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));
		System.out.println((int)(char)(byte) -1);
		
		
		int x = 1984; // (0x7c0)
		int y = 2001; // (0x7d1)
		x^= y^= x^= y;
		System.out.println("x= " + x + "; y= " + y);
		
		
		char xz = 'X';
		int i = 0;
		System.out.println(true ? xz : 0); 
		System.out.println(false ? i : xz); 
		
		final String pig = "length: 10";
        final String dog = "length: " + pig.length();
        System.out. println("Animals are equal: " + pig == dog);
	}
	
}
