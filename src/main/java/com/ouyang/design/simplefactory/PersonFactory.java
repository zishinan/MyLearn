package com.ouyang.design.simplefactory;

/**
 * 简单工厂模式
 * @author yangxi
 *
 */
public class PersonFactory
{
	public Person getPerson(String name)
	{
		if ("chinese".equals(name))
		{
			return new Chinese();
		}
		else if ("american".equals(name))
		{
			return new American();
		}
		else
		{
			return null;
		}
	}

}
