package com.ouyang.design.simplefactory;

public class American implements Person
{

	@Override
	public String sayHello(String name)
	{
		return "Hello ," + name;
	}

	@Override
	public String sayGoodbye(String name)
	{
		return "Goodbye ," + name;
	}

}
