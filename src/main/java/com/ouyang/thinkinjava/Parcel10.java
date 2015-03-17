package com.ouyang.thinkinjava;

//: Parcel10.java
//Static inner classes  
abstract class Contents1
{
	abstract public int value();
}

interface Destination1
{
	String readLabel();
}

public class Parcel10
{
	private static class PContents
			extends Contents1
	{
		private int i = 11;

		public int value()
		{
			return i;
		}
	}

	protected static class PDestination
			implements Destination1
	{
		private String label;

		private PDestination(String whereTo)
		{
			label = whereTo;
		}

		public String readLabel()
		{
			return label;
		}
	}

	public static Destination1 dest(String s)
	{
		return new PDestination(s);
	}

	public static Contents1 cont()
	{
		return new PContents();
	}

	public static void main(String[] args)
	{
		Contents1 c = cont();
		Destination1 d = dest("Tanzania");
	}
} // /: