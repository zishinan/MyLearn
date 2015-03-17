package com.ouyang.design.singleton;

/**
 * 单例模式
 * 
 * @author YangXi
 * 
 */
public class SingletonThreadTest
{
	public static void main(String[] args)
	{
		MyThread thread1 = new MyThread();
		MyThread thread2 = new MyThread();
		
		new Thread(thread1).start();
		new Thread(thread2).start();
	}
}

class SingletonThread
{
	private static SingletonThread singleton;

	private SingletonThread()
	{

	}

	public static SingletonThread getInstance()
	{
		if(null == singleton)
		{
			singleton = new SingletonThread();
		}
		return singleton;
	}
}

class MyThread implements Runnable
{
	@Override
	public void run()
	{
		System.out.println(SingletonThread.getInstance());
	}
}