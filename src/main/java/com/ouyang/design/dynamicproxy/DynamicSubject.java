package com.ouyang.design.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理模式
 * @author yangxi
 *
 */
public class DynamicSubject implements InvocationHandler
{
	private Object obj;

	public DynamicSubject(Object obj)
	{
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable
	{
		System.out.println("befor calling : " + method);

		method.invoke(obj, args);

		System.out.println("after calling : " + method);
		return null;
	}

}
