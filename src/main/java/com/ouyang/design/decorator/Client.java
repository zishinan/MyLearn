package com.ouyang.design.decorator;

public class Client
{
	public static void main(String[] args)
	{
		Component component = new ConcreteComponent();
		
		Component component2 = new ConcreteDecorator(component);
		
		Component component3 = new ConcreteDecorator1(component2);
		
		component3.doSomething();
		
		Component component4 = new ConcreteDecorator1(new ConcreteDecorator(new ConcreteComponent()));
		
		component4.doSomething();
	}
}
