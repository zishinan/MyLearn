package com.ouyang.design.decorator;

public class ConcreteComponent implements Component
{

	@Override
	public void doSomething()
	{
		System.out.println("Concrete Component is runnning");
	}

}
