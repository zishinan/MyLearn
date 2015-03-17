package com.ouyang.subject;

import java.util.ArrayList;
import java.util.List;

/**
 * 甲乙两个人用一个英语单词玩游戏。两个人轮流进行，每个人每次从中删掉任意一个字母，如果剩余的字母序列是严格单调递增的 （按字典序a < b < c
 * <....<z)，则这个人胜利。两个人都足够聪明，甲先开始，问他能赢么？ 输入：
 * 一连串英文小写字母，长度不超过15,保证最开始的状态不是一个严格单增的序列。 输出：1表示甲可以赢，0表示甲不能赢。 例如: 输入 bad，
 * 则甲可以删掉b或者a,剩余的是ad或者bd，他就赢了，输出1。 又如: 输入 aaa， 则甲只能删掉1个a，乙删掉一个a,剩余1个a，乙获胜，输出0。
 * 函数头部： C：int who (const char * word); C++：int who (string word); Java：public
 * static int who(String in); C# ：public static int who(string word);
 * 
 * @author 阳熙
 */
public class DelStringGame
{
	public static String getRe(String s)
	{
		switch (who(s))
		{
		case 0:
			return "B Win";
		case 1:
			return "A Win";
		default:
			return "not really";
		}
	}

	public static int who(String in)
	{
		char[] arr = in.toCharArray();
		List<Character> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++)
		{
			list.add(arr[i]);
		}
		return mainVali(list);
	}

	public static int mainVali(List<Character> list)
	{
		int n = 3;
		if (isWin(list))
		{
			return 1;
		}
		if (isLose(list))
		{
			return 0;
		}
		for (int i = 0; i < list.size(); i++)
		{
			List<Character> tlist = new ArrayList<>();
			tlist.addAll(list);
			tlist.remove(i);
			n = mainVali(tlist);
		}
		return n;
	}

	public static boolean isLose(List<Character> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			List<Character> tlist = new ArrayList<>();
			tlist.addAll(list);
			tlist.remove(i);
			if (!isWin(tlist))
			{
				return false;
			}
		}
		return true;
	}

	public static boolean isWin(List<Character> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			List<Character> tlist = new ArrayList<>();
			tlist.addAll(list);
			tlist.remove(i);
			if (validate(tlist))
			{
				return true;
			}
		}
		return false;
	}

	public static boolean validate(List<Character> list)
	{
		for (int i = 0; i < list.size() - 1; i++)
		{
			if (list.get(i) >= list.get(i + 1))
			{
				return false;
			}
		}
		return true;
	}

}