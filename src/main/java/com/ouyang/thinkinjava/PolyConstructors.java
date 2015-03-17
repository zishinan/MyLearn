package com.ouyang.thinkinjava;

//: PolyConstructors.java
//Constructors and polymorphism
//don't produce what you might expect.
abstract class Glyph
{
	void draw()
	{
		System.out.println("============");
	}

	Glyph()
	{
		System.out.println("Glyph() before draw()");
		this.draw();
		System.out.println("Glyph() after draw()");
	}
}

class RoundGlyph extends Glyph
{
	int radius = 1;

	RoundGlyph(int r)
	{
		radius = r;
		System.out.println(
				"RoundGlyph.RoundGlyph(), radius = "
						+ radius);
	}

	void draw()
	{
		System.out.println(
				"RoundGlyph.draw(), 	radius = " + radius);
	}
}

public class PolyConstructors
{
	public static void main(String[] args)
	{
		new RoundGlyph(5);
	}
} // /: