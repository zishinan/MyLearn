package com.ouyang.test;

public class ExtendTest
{
	public static void main(String[] args)
	{
		A a = new B();
		a.a();
	}
}


class A{
	public void a()
	{
		System.out.println("a");
	}
}

class B extends A{
	@Override
	public void a()
	{
		System.out.println("ba");
	}
	
	public void b()
	{
		System.out.println("b");
	}
}
