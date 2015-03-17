package com.ouyang.design.decorator;

public class ConcreteDecorator1 extends Decorator
{

	public ConcreteDecorator1(Component component)
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
		System.out.println("Concrete Decorator 1 is running");
	}

}
