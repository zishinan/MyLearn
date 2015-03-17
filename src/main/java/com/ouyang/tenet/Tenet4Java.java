package com.ouyang.tenet;

import java.util.Random;

import org.junit.Test;

public class Tenet4Java {
	//常量要在编译期确定值,如下不可取
	public static final int RAND_CONST = new Random().nextInt();
	//三元表达式类型要一致
	@Test
	public void test3Expr() throws Exception {
		int i = 80;
		String s = String.valueOf(i<100?90:100);
		String s1 = String.valueOf(i<100?90:100.0);
		System.out.println(s + "====" + s1);
		System.out.println("两者是否相等:"+s.equals(s1));
	}
	//建议4： 避免带有变长参数的方法重载
	//建议5： 别让null 值和空值威胁到变长方法
	//建议6： 覆写变长方法也循规蹈矩
	//编译器有一个“最短路径”原则：如果能够在本类中查找到的变量、常量、方法，就不会到其他包或父类、接口中查找，以确保本类中的属性、方法优先。
//	建议12： 避免用序列化类在构造函数中为不变量赋值 反序列化不会调用构造方法
}
