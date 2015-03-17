package com.ouyang.thinkinjava;

//: Month2.java
//A more robust enumeration system
public final class Month2
{
	private String name;

	private Month2(String nm)
	{
		name = nm;
	}

	public String toString()
	{
		return name;
	}

	public final static Month2
			JAN = new Month2("January"),
			FEB = new Month2("February"),
			MAR = new Month2("March"),
			APR = new Month2("April"),
			MAY = new Month2("May"),
			JUN = new Month2("June"),
			JUL = new Month2("July"),
			AUG = new Month2("August"),
			SEP = new Month2("September"),
			OCT = new Month2("October"),
			NOV = new Month2("November"),
			DEC = new Month2("December");
	public final static Month2[] month = {
			JAN, FEB, MAR, APR, MAY, JUN,
			JUL, AUG, SEP, OCT, NOV, DEC
	};

	public static void main(String[] args)
	{
		Month2 m = Month2.JAN;
		System.out.println(m);
		m = Month2.month[11];
		System.out.println(m);
		System.out.println(m == Month2.DEC);
		System.out.println(m.equals(Month2.DEC));
		
		Month moth = Month.APR;
		System.out.println(moth.getMothNumber());
	}
} // /:


enum Month
{
	JAN("January"), FEB("February"), MAR("March"), APR("April"), MAY("May"), JUN("June"),
	JUL("July"), AUG("August"), SEP("September"), OCT("October"), NOV("November"), DEC("December");
	private String mothNumber;
	
	public String getMothNumber()
	{
		return this.mothNumber;
	}

	private Month(String mothNumber)
	{
		this.mothNumber = mothNumber;
	}
	
}