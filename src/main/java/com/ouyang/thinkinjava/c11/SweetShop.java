package com.ouyang.thinkinjava.c11;

//: SweetShop.java
// Examination of the way the class load er works
class Candy
{
	static
	{
		System.out.println("Loading Candy");
	}
}

class Gum
{
	static
	{
		System.out.println("Loading Gum");
	}
}

class Cookie
{
	static
	{
		System.out.println("Loading Cookie");
	}
}

public class SweetShop
{
	public static void main(String[] args)
	{
		System.out.println("inside main");
		new Candy();
		System.out.println("After creating Candy");
		try
		{
			Class.forName("com.ouyang.thinkinjava.c11.Gum");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		System.out.println(
				"After Class.forName( \"Gum \")");
		new Cookie();
		System.out.println("After creating Cookie");
	}
} //