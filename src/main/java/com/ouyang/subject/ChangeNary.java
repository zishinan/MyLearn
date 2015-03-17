package com.ouyang.subject;

/**
 * 写一个函数，输入一个整数（第一个参数）在指定基数（第二个参数，整数）下的表示形式，以字符串形式输出。如输入参数
 * 47和12，此函数输出字符串“3B”。为简单起见，可认为基数最大为16.请检查参数的有效性： 1：(15,2) ---->"1111"; 2：(15,8)
 * ---->"17"; 3：(15,16) ---->"F"; 4：(15,10) ---->"15"; 5：(323,8) ---->"503";
 * 6：(323,16) ---->"143";
 * 
 * @author 阳熙
 * 
 */
public class ChangeNary
{
	public String getResult(int num, int base)
	{
		char[] bits =
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
				'E', 'F' };
		StringBuilder sb = new StringBuilder();
		while (num >= base)
		{
			sb.append(bits[num % base]);
			num = num / base;
		}
		sb.append(bits[num]);

		String result = sb.reverse().toString();
		System.out.println(result);
		return result;
	}
}
