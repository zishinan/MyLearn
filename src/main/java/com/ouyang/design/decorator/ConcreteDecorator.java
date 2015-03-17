package com.ouyang.design.decorator;

public class ConcreteDecorator extends Decorator
{

	public ConcreteDecorator(Component component)
	{
		super(component);
	}
	
	public void doSomething()
	{
		super.doSomething();
		this.doOtherthing();
	}

	private void doOtherthing()
	{
		System.out.println("Concrete Decorator is running");
	}
}
