package com.ouyang.design.strategy;

/**
 * 策略模式测试类
 * 
 * @author YangXi
 * 
 */
public class Client
{
	public static void main(String[] args)
	{
		AddStrategy add = new AddStrategy();
		Environment environment = new Environment(add);
		System.out.println(environment.calculate(8, 7));

		SubtractStrategy sub = new SubtractStrategy();
		environment.setStrategy(sub);
		System.out.println(environment.calculate(8, 7));

		MultiplyStrategy mul = new MultiplyStrategy();
		environment.setStrategy(mul);
		System.out.println(environment.calculate(8, 7));

		DivideStrategy div = new DivideStrategy();
		environment.setStrategy(div);
		System.out.println(environment.calculate(8, 7));
	}

}
