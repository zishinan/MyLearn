package com.ouyang.subject;

/**
 * 小青蛙每个白天可以向上爬m（2<=m<=10）尺，晚上下滑n（1<=n<m）尺。如果告诉你井深h（10<=h<=2000000000）尺，
 * 请计算一下，现在，它第几天可以爬上来。 Input 有三个整数，分别表示 m n h Output 只有一个整数，表示第几天可以爬上来。 Sample
 * Input 3 2 10 Output 8
 * 
 * @author 阳熙
 * 
 */
public class FrogClimb
{
	public int getResult(int m, int n, int h)
	{
		int resualt = 0;
		if (m < 2 || m > 10)
		{
			System.out.println("2<=m<=10");
		}
		if (n < 1 || n > m)
		{
			System.out.println("1<=n<m");
		}
		if (h < 10 || h > 2000000000)
		{
			System.out.println("10<=h<=2000000000");
		}

		int perDay = m - n;
		while (h - perDay * resualt - m >= 0)
		{
			resualt++;
		}
		return resualt;
	}

	public static void main(String[] args)
	{
		FrogClimb frogClimb = new FrogClimb();
		System.out.println(frogClimb.getResult(4, 2, 10));
	}
}
