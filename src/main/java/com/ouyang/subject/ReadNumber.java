//package com.ouyang.subject;
//
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class ReadNumber
//{
//	public static void main(String[] args)
//	{
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("请输入8位数以内的数字：");
//		int i = 0;
//		try
//		{
//			i = scanner.nextInt();
//			scanner.close();
//			if (0 <= i && i <= 99999999)
//			{
//				ReadNumber rn = new ReadNumber();
//				String read = rn.read(i);
//				if (read != null)
//				{
//					System.out.println("读取结果是：" + read);
//				}
//			}
//			else
//			{
//				System.err.println("请按要求输入正确的数字！");
//			}
//		}
//		catch (InputMismatchException e)
//		{
//			System.err.println("请按要求输入正确的数字！");
//		}
//
//	}
//
//	public String read(int i)
//	{
//		char[] cs =
//		{ '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
//		String[] bbs =
//		{ "仟", "佰", "拾", "万" };
//		StringBuffer read = new StringBuffer();
//		String s = i + "";
//		char[] bs = s.toCharArray();
//		int size = bs.length;
//		boolean flag = false;
//		if (size > 4)
//		{
//			for (int j = 0; j < size - 4; j++)
//			{
//				int si = Integer.parseUnsignedInt(bs[j] + "");
//				if (0 == si)
//				{
//					flag = true;
//					continue;
//				}
//				else if (flag && 0 != si)
//				{
//					flag = false;
//					read.append(cs[0]);
//					read.append(cs[si]);
//					if (j != size - 5)
//					{
//						read.append(bbs[3 - (size - 4 - j) + 1]);
//					}
//				}
//				else
//				{
//					read.append(cs[si]);
//					if (j != size - 4 - 1)
//					{
//						read.append(bbs[3 - (size - 4 - j) + 1]);
//					}
//				}
//			}
//
//			read.append(bbs[3]);
//
//			flag = false;
//			for (int j = size - 4; j < size; j++)
//			{
//				int si = Integer.parseUnsignedInt(bs[j] + "");
//
//				if (0 == si)
//				{
//					flag = true;
//					continue;
//				}
//				else if (flag && 0 != si)
//				{
//					flag = false;
//					read.append(cs[0]);
//					read.append(cs[si]);
//					if (j != size - 1)
//					{
//						read.append(bbs[4 - (size - j)]);
//					}
//				}
//				else
//				{
//					read.append(cs[si]);
//					if (j != size - 1)
//					{
//						read.append(bbs[4 - (size - j)]);
//					}
//				}
//			}
//
//		}
//		else
//		{
//			for (int j = 0; j < size; j++)
//			{
//				int si = Integer.parseUnsignedInt(bs[j] + "");
//
//				if (0 == si)
//				{
//					flag = true;
//					continue;
//				}
//				else if (flag && 0 != si)
//				{
//					flag = false;
//					read.append(cs[0]);
//					read.append(cs[si]);
//					if (j != size - 1)
//					{
//						read.append(bbs[j]);
//					}
//				}
//				else
//				{
//					read.append(cs[si]);
//					if (j != size - 1)
//					{
//						read.append(bbs[j]);
//					}
//				}
//
//			}
//		}
//
//		return read.toString();
//	}
//}