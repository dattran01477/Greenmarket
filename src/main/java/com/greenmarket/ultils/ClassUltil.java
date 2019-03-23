package com.greenmarket.ultils;

public final  class ClassUltil {

	// convert String to ClassName
	public static Class classForName(String nameClass)
	{
		Class classObject=null;
		try {
		classObject = Class.forName("com.greenmarket.entity."+nameClass);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return classObject;
	}
	
}