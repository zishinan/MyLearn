package com.ouyang.design.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client
{
	public static void main(String[] args)
	{
		RealSubject realSubject = new RealSubject();
		InvocationHandler handler = new DynamicSubject(realSubject);

		// 生成代理
		Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass()
				.getClassLoader(), realSubject.getClass().getInterfaces(),
				handler);

		subject.request();
	}
}
