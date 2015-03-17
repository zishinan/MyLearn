package com.ouyang.thinkinjava;

//: InheritInner.java
//Inheriting an inner class
class WithInner
{
	class Inner
	{
	}
}

public class InheritInner
		extends WithInner.Inner
{
	// ! InheritInner() {} // Won't compile
	// 必须传一个外部对象，内部类依赖
	InheritInner(WithInner wi)
	{
		wi.super();
	}

	public static void main(String[] args)
	{
		WithInner wi = new WithInner();
		InheritInner ii = new InheritInner(wi);
	}
} // /:~