package com.ouyang.test;

public class TestParent {
	public static void main(String[] args) {
		Son parent = new Son();
		parent.setAge(10);
		Parent parent2 = parent;
		Son son = (Son) parent2;
		son.setName("ddd");
		Son son2 = (Son) parent2;
		System.out.println(son2.getName());
	}
}


class Parent{
	int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}

class Son extends Parent{
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}