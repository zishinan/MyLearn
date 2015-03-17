package com.ouyang.design.observe;

public class ConcreteWatcher implements Watcher
{

	@Override
	public void update(String str)
	{
		System.out.println(str);
	}

}
