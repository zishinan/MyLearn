package com.ouyang.design.proxy;

public class RealSubject implements Subject
{
	@Override
	public void request()
	{
		System.out.println("我有房子要出租!");
	}

}
