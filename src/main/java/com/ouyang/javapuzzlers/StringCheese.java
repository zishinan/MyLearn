package com.ouyang.javapuzzlers;

/**
 * @author yangxi
 * 每当你要将一个byte序列转换成一个String时，你都在使用某一个字符集，不管你是否显式地指定了它。如果你想让你的程序的行为是可预知的，那么就请你在每次使用字符集时都明确地指定。
 */
public class StringCheese
{
	public static void main(String[] args)
	{
		byte bytes[] = new byte[256];
		for (int i = 0; i < 256; i++)
			bytes[i] = (byte) i;
		String str = new String(bytes);
		for (int i = 0, n = str.length(); i < n; i++)
			System.out.println((int) str.charAt(i) + " ");
	}
}
