package com.ouyang.subject;

/**
 * 用Java编写一个程序，1到10000之间整数，它加上100后是一个完全平方数，再加上168又是一个完全平方数，请问该数是多少？
 */
public class PerfectSquareCount
{
	public static void main(String[] args)
	{
		for (int i = 1; i < 10000; i++)
		{
			if (isPerfectSquare(i + 100) > 0
					&& isPerfectSquare(i + 100 + 168) > 0)
			{
				System.out.println("这个数是:" + i);
				System.out.println("加100为:" + (i + 100) + ",是"
						+ isPerfectSquare(i + 100) + "的平方.");
				System.out.println("再加168为:" + (i + 100 + 168) + ",是"
						+ isPerfectSquare(i + 100 + 168) + "的平方.");
			}
		}
	}

	public static int isPerfectSquare(int n)
	{
		for (int i = 1; i * i <= n; i++)
		{
			if (i * i == n)
			{
				return i;
			}
		}
		return 0;
	}
}
