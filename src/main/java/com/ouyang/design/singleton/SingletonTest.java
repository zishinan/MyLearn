package com.ouyang.design.singleton;

/**
 * 单例模式
 * 
 * @author YangXi
 * 
 */
public class SingletonTest
{
	public static void main(String[] args)
	{
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton1 == singleton2);
	}
}

class Singleton
{
	private static Singleton singleton = new Singleton();

	private Singleton()
	{

	}

	public static Singleton getInstance()
	{
		return singleton;
	}
}