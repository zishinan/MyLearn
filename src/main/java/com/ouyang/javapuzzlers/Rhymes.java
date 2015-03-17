package com.ouyang.javapuzzlers;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Rhymes
{
	private static Random rnd = new Random();

	public static void main(String[] args)
	{
		StringBuffer word = null;
		
		int rndNum = rnd.nextInt(2);
		System.out.println(rndNum);
		switch (rndNum)
		{
		case 1:
			word = new StringBuffer('P');
			break;
		case 2:
			word = new StringBuffer('G');
			break;
		default:
			word = new StringBuffer('M');
			break;
		}
		word.append('a');
		word.append('i');
		word.append('n');
		System.out.println(word);
		
		Set<String> aEs = new HashSet<>();
	}
}