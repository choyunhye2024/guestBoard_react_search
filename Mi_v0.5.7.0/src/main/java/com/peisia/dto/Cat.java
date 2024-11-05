package com.peisia.dto;

import java.util.ArrayList;

public class Cat {
	String name;
	int age;
	public ArrayList<String> hobby = new ArrayList<>();

	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
