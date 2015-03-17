package com.ouyang.design.proxy;

public class ProxySubject implements Subject
{
	private RealSubject realSubject;

	@Override
	public void request()
	{
		if (null == realSubject)
		{
			realSubject = new RealSubject();
		}

		this.preRequest();

		realSubject.request();

		this.postRequest();
	}

	private void preRequest()
	{
		System.out.println("我发个广告!");
	}

	private void postRequest()
	{
		System.out.println("给我中介费!");
	}

}
