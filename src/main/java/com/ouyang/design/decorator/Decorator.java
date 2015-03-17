package com.ouyang.design.decorator;

/**
 * 装饰模式
 * @author yangxi
 *
 */
public class Decorator implements Component
{
	private Component component;
	public Decorator(Component component)
	{
		this.component = component;
	}
	
	@Override
	public void doSomething()
	{
		component.doSomething();
	}

}
