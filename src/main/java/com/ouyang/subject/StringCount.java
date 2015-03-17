package com.ouyang.subject;

public class StringCount
{
	/**
	 * 计算strsi在strdi中出现的次数
	 * 
	 * @author 阳熙
	 * @param strsi
	 *            被包含字符串
	 * @param strdi
	 *            总字符串
	 * @return
	 */
	public static int count(String strsi, String strdi)
	{

		if ((strsi == null) || (strdi == null))
		{
			System.out.println("传入字符串为空!");
			return -1;
		}

		int iLength_Si = strsi.length();
		int iLength_Di = strdi.length();
		int index = 0;
		int iNum = 0;

		if (iLength_Si > iLength_Di)
			return 0;

		while (index < iLength_Di)
		{
			index = strdi.indexOf(strsi, index);
			if (index < 0)
			{
				break;
			}
			else
			{
				index++;
				iNum++;
			}
		}
		return iNum;
	}
}
