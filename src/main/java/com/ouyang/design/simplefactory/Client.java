package com.ouyang.design.simplefactory;

public class Client
{
	public static void main(String[] args)
	{
		Person chinese = new Chinese();

		System.out.println(chinese.sayHello("张三"));
		System.out.println(chinese.sayGoodbye("张三"));

		Person american = new American();

		System.out.println(american.sayHello("Tidy"));
		System.out.println(american.sayGoodbye("Tidy"));

		System.out.println("---------------------------");

		PersonFactory factory = new PersonFactory();

		Person chinese1 = factory.getPerson("chinese");

		System.out.println(chinese1.sayHello("你好"));
		System.out.println(chinese1.sayGoodbye("你好 "));

		Person american1 = factory.getPerson("american");

		System.out.println(american1.sayHello("Tom"));
		System.out.println(american1.sayGoodbye("Tom"));

	}

}
