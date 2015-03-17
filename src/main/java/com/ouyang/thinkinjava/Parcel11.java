package com.ouyang.thinkinjava;

//: Parcel11.java
//Creating inner classes
public class Parcel11
{
	class Contents
	{
		private int i = 11;

		public int value()
		{
			return i;
		}
	}

	class Destination
	{
		private String label;

		Destination(String whereTo)
		{
			label = whereTo;
		}

		String readLabel()
		{
			return label;
		}
	}

	public static void main(String[] args)
	{
		Parcel11 p = new Parcel11();
		// Must use instance of outer class
		// to create an in stances of the inner class:
		Parcel11.Contents c = p.new Contents();
		Parcel11.Destination d =
				p.new Destination("Tanzania");
	}
} // /