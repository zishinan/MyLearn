package com.ouyang.game.doudizhu;

import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

class Poker
{

	public static char[] p, p1;
	public String Name;
	public int Value;
	public int id;
	static Scanner scanner = new Scanner(System.in);
	public static int paixing = 0;
	public static int turn = 2;
	public static boolean daxiao;
	public static int dizhu;
	public static Integer zuidizhu;
	public static int paixing1, size;
	public static Vector<Integer> v01 = new Vector<Integer>(),
			v02 = new Vector<Integer>(), v03 = new Vector<Integer>(),
			v04 = new Vector<Integer>();
	public static Vector<Integer> v = new Vector<Integer>(),
			v1 = new Vector<Integer>(), v2 = new Vector<Integer>(),
			v3 = new Vector<Integer>(), v4 = new Vector<Integer>();

	public Poker(String name, int value)
	{
		this.Name = name;
		this.Value = value;
	}

	static Vector<Poker> pokers = new Vector<Poker>();
	static Vector<Poker> pokers1 = new Vector<Poker>();
	static Vector<Poker> farmer1 = new Vector<Poker>();
	static Vector<Poker> farmer2 = new Vector<Poker>();
	static Vector<Poker> farmer3 = new Vector<Poker>();
	static Vector<Poker> landlord = new Vector<Poker>();

	public static void gameStart()
	{
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{
		}
		System.err.println("Game Start!");
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
		}
		showrules();
		// 定义一副新牌
		for (int j = 0; j < 4; j++)
		{
			for (Integer i = 3; i < 16; i++)
			{
				if (i < 10)
				{
					setPoker(i.toString(), i);
				}
				if (i == 10)
				{
					setPoker("十", i);
				}
				switch (i)
				{
				case 11:
					setPoker("J", 11);
					break;
				case 12:
					setPoker("Q", 12);
					break;
				case 13:
					setPoker("K", 13);
					break;
				case 14:
					setPoker("A", 14);
					break;
				case 15:
					setPoker("2", 15);
					break;
				}
			}
		}
		setPoker("W", 17);
		setPoker("w", 16);
		for (int i = 0; i < pokers.size(); i++)
		{
			pokers.get(i).id = i;
		}
		giveout();
		liangpai();
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
		}
		xuandizhu();
		for (int i = zuidizhu; i > -1; i++)
		{
			for (int j = 1; j > 0; j++)
			{
				System.out.print("Player" + i + ":");
				switch (i)
				{
				case 1:
					farmer1 = chupai(farmer1);
					break;
				case 2:
					farmer2 = chupai(farmer2);
					break;
				case 0:
					farmer3 = chupai(farmer3);
					break;
				}
				i = (i + 1) % 3;
				xianjie();
			}
		}
	}

	public static void showrules()
	{
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
		}
		System.out.println("\nRules:(等待10秒)");
		System.out.println("为了简单一点,我没有将这个游戏写成网络版的。");
		System.out.println("所以只能在一台机器上运行。");
		System.out.println("也就是大家都是明牌（^_^)");
		System.out.println("另外：");
		System.out.println("不支持飞机炸弹混合，即类似'44455556669Q'的牌型是非法的。");
		System.out.println("不过'3334445556667777'是合法的，视为四飞机带四。");
		System.out
				.println("通过输入'Ww2AKQJ十9876543','3334','556677','5565'等就可以实现出牌。");
		try
		{
			Thread.sleep(10500);
		}
		catch (InterruptedException e)
		{
		}
	}

	public static void xianjie()
	{
		paixing = paixing1;
		char[] p2 = new char[p.length];
		System.arraycopy(p, 0, p2, 0, p.length);
		p1 = p2;
		liangpai();
		if (checkEnpty())
		{
			showresult();
		}
		v01.removeAllElements();
		v02.removeAllElements();
		v03.removeAllElements();
		v04.removeAllElements();
		v01.addAll(v1);
		v02.addAll(v2);
		v03.addAll(v3);
		v04.addAll(v4);
	}

	public static void showresult()
	{
		// 展示结果
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e1)
		{
		}
		if (landlord.isEmpty())
		{
			System.err.println("地主胜利!\n");
		}
		else
		{
			System.err.println("农民胜利!\n");
		}
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
		}
		// 是否重来
		System.out.println("再来一盘?(yes/no)");
		String yn = scanner.next();
		switch (yn)
		{
		case "yes":
			restart();
			break;
		case "no":
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
			}
			System.out.print("\nBye!\n");
			System.exit(0);
			break;
		default:
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
			}
			System.err.println("少开玩笑!");
			System.exit(-1);
			break;
		}
	}

	public static void setPoker(String names, int values)
	{
		// 为牌定义名称和大小
		Poker poker = new Poker(names, values);
		pokers.addElement(poker);
		pokers1.addElement(poker);
	}

	public static void giveout()
	{
		// 发牌
		System.out.println("\n发牌中......");
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
		}
		int n = 54;
		int N;
		for (int i = 0; i < 17; i++)
		{
			N = (int) (Math.random() * n);
			farmer1.addElement(pokers.get(N));
			pokers.removeElementAt(N);
			n--;
		}
		for (int i = 0; i < 17; i++)
		{
			N = (int) (Math.random() * n);
			farmer2.addElement(pokers.get(N));
			pokers.removeElementAt(N);
			n--;
		}
		for (int i = 0; i < 17; i++)
		{
			N = (int) (Math.random() * n);
			farmer3.addElement(pokers.get(N));
			pokers.removeElementAt(N);
			n--;
		}
	}

	public static Vector<Poker> chupai(Vector<Poker> farmer12)
	{
		// 出牌
		CHUPAI: for (int t = 0; t < 10; t++)
		{
			System.out.println("请出牌:(输入P则为'过')");
			String pai = scanner.next();
			if (pai.equals("P"))
			{
				System.out.println("过!");
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
				}
				turn++;
				if (turn == 3)
				{
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
					}
					System.err.println("都不出牌，就不要玩了!");
					System.exit(-1);
				}
				return farmer12;
			}
			else
			{
				p = pai.toCharArray();
				size = p.length;
				if (youwu(farmer12))
				{
					paixing1 = Poker_Style.paixing(size);
					v4.removeAllElements();
					v4.addAll(Poker_Style.v44);
					v3.removeAllElements();
					v3.addAll(Poker_Style.v33);
					v2.removeAllElements();
					v2.addAll(Poker_Style.v22);
					v1.removeAllElements();
					v1.addAll(Poker_Style.v11);
					if (paixing1 == 0)
					{
						try
						{
							Thread.sleep(100);
						}
						catch (InterruptedException e)
						{
						}
						System.err.println("不能这样出牌!");
						continue CHUPAI;
					}
					if (turn >= 2)
					{
						daxiao = true;
					}
					else
					{
						daxiao = Checkdaxiao.checkdaxiao();
					}
					if (daxiao)
					{
						chupaicg(pai);
						try
						{
							Thread.sleep(1000);
						}
						catch (InterruptedException e)
						{
						}
						farmer12 = jiansao(p, farmer12);
						turn = 0;
						return farmer12;
					}
					else
					{
						System.err.println("出牌不符合规则!");
						try
						{
							Thread.sleep(100);
						}
						catch (InterruptedException e)
						{
						}
						continue CHUPAI;
					}
				}
				else
				{
					System.err.println("你没有这些牌!");
					try
					{
						Thread.sleep(100);
					}
					catch (InterruptedException e)
					{
					}
					continue CHUPAI;
				}
			}
		}
		System.err.println("你都出错10次了,学习学习再来吧!");
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{
		}
		System.exit(-1);
		return farmer12;
	}

	public static Vector<Poker> jiansao(char[] p11, Vector<Poker> farmer11)
	{
		// 出牌后手里的牌应当减少
		Vector<Poker> farmer111 = new Vector<Poker>();
		farmer111.addAll(farmer11);
		char[] p222 = new char[p11.length];
		System.arraycopy(p11, 0, p222, 0, p11.length);
		for (char element : p222)
		{
			farmer111 = jian(element, farmer111);
		}
		return farmer111;
	}

	public static Vector<Poker> jian(char x, Vector<Poker> farmer11)
	{
		Vector<Poker> farmer111 = new Vector<Poker>();
		farmer111.addAll(farmer11);
		for (int i = 0; i < farmer11.size(); i++)
		{
			if (farmer11.get(i).Name.toCharArray()[0] == x)
			{
				farmer111.removeElement(farmer11.get(i));
				return farmer111;
			}
		}
		return farmer111;
	}

	public static void chupaicg(String pai)
	{
		// 显示牌型
		switch (paixing1)
		{
		case 1:
			System.out.print("单牌:");
			break;
		case 2:
			System.out.print("对子:");
			break;
		case 3:
			System.out.print("三个:");
			break;
		case 4:
			System.out.print("三带一:");
			break;
		case 5:
			System.out.print("五顺:");
			break;
		case 6:
			System.out.print("三带一对:");
			break;
		case 7:
			System.out.print("四带一:");
			break;
		case 8:
			System.out.print("六顺:");
			break;
		case 9:
			System.out.print("四带二:");
			break;
		case 10:
			System.out.print("二飞机:");
			break;
		case 11:
			System.out.print("三连对:");
			break;
		case 12:
			System.out.print("七顺:");
			break;
		case 13:
			System.out.print("二飞机:");
			break;
		case 14:
			System.out.print("八顺:");
			break;
		case 15:
			System.out.print("二飞机:");
			break;
		case 16:
			System.out.print("四连对:");
			break;
		case 17:
			System.out.print("九顺:");
			break;
		case 18:
			System.out.print("三飞机:");
			break;
		case 19:
			System.out.print("十顺:");
			break;
		case 20:
			System.out.print("三飞机:");
			break;
		case 21:
			System.out.print("五连对:");
			break;
		case 22:
			System.out.print("十一顺:");
			break;
		case 23:
			System.out.print("三飞机:");
			break;
		case 24:
			System.out.print("十二顺:");
			break;
		case 25:
			System.out.print("三飞机:");
			break;
		case 26:
			System.out.print("四飞机:");
			break;
		case 27:
			System.out.print("六连对:");
			break;
		case 28:
			System.out.print("四飞机:");
			break;
		case 29:
			System.out.print("四飞机:");
			break;
		case 30:
			System.out.print("七连对:");
			break;
		case 31:
			System.out.print("四飞机:");
			break;
		case 32:
			System.out.print("五飞机:");
			break;
		case 33:
			System.out.print("四飞机:");
			break;
		case 34:
			System.out.print("五飞机:");
			break;
		case 35:
			System.out.print("八连对:");
			break;
		case 36:
			System.out.print("五飞机:");
			break;
		case 37:
			System.out.print("五飞机:");
			break;
		case 38:
			System.out.print("六飞机:");
			break;
		case 39:
			System.out.print("九连对:");
			break;
		case 40:
			System.out.print("五飞机:");
			break;
		case 41:
			System.out.print("六飞机:");
			break;
		case 42:
			System.out.print("十连对:");
			break;
		case 43:
			System.out.print("六飞机:");
			break;
		case 44:
			System.out.print("五飞机:");
			break;
		case 99:
			System.out.print("炸弹:");
			break;
		case 100:
			System.out.print("王炸:");
			break;
		default:
			break;
		}
		System.out.println(pai);
	}

	public static boolean checkEnpty()
	{
		// 出牌后，检查牌是否已经出完，如果出完就显示结果
		if (farmer1.isEmpty())
		{
			return true;
		}
		if (farmer2.isEmpty())
		{
			return true;
		}
		if (farmer3.isEmpty())
		{
			return true;
		}
		return false;
	}

	public static boolean youwu(Vector<Poker> farmer12)
	{
		// 检查玩家出的牌他是不是真的有
		int n = 0;
		Vector<Poker> poker4 = new Vector<Poker>();
		poker4.addAll(farmer12);
		int kk = poker4.size();
		String[] a = new String[kk];
		for (int i = 0; i < kk; i++)
		{
			a[i] = poker4.get(i).Name;
		}
		char[] p2 = new char[a.length];
		for (int i = 0; i < farmer12.size(); i++)
		{
			String bString = a[i];
			char[] aa = bString.toCharArray();
			p2[i] = aa[0];
		}
		for (char M : p)
		{
			for (int k = 0; k < p2.length; k++)
			{
				if (M == p2[k])
				{
					n++;
					p2 = cut(p2, k);
					break;
				}
			}
		}
		if (n == p.length)
		{
			return true;
		}
		return false;
	}

	public static char[] cut(char[] p22, int k)
	{
		char[] p23 = new char[p22.length - 1];
		for (int j = 0; j < (p22.length - 1); j++)
		{
			if (j < k)
			{
				p23[j] = p22[j];
			}
			if (j >= k)
			{
				p23[j] = p22[j + 1];
			}
		}
		return p23;
	}

	public static void liangpai()
	{
		// 亮牌,要按次序排好
		farmer1 = paixu(farmer1);
		farmer2 = paixu(farmer2);
		farmer3 = paixu(farmer3);
		String[] p = new String[3];
		if (zuidizhu != null)
		{
			for (int ii = 0; ii < 3; ii++)
			{
				if (zuidizhu == ii)
				{
					p[ii] = "地主:";
				}
				else
				{
					p[ii] = "农民:";
				}
			}
		}
		else
		{
			p[1] = p[2] = p[0] = "农民";
		}
		System.out.print("\n" + p[1] + "Player1:  ");
		for (int i = 0; i < farmer1.size(); i++)
		{
			System.out.print(farmer1.get(i).Name);
		}
		System.out.print("\n" + p[2] + "Player2:  ");
		for (int i = 0; i < farmer2.size(); i++)
		{
			System.out.print(farmer2.get(i).Name);
		}
		System.out.print("\n" + p[0] + "Player0:  ");
		for (int i = 0; i < farmer3.size(); i++)
		{
			System.out.print(farmer3.get(i).Name);
		}
		System.out.println("\n");
	}

	public static Vector<Poker> paixu(Vector<Poker> farmer12)
	{
		// 将随机发放的牌排序
		Vector<Poker> poker3 = new Vector<Poker>();
		Vector<Poker> poker4 = new Vector<Poker>();
		poker4.addAll(farmer12);
		for (int i = 0; i < farmer12.size(); i++)
		{
			int[] a = new int[poker4.size()];
			for (int j = 0; j < poker4.size(); j++)
			{
				a[j] = poker4.get(j).Value;
			}
			int M = MyArray.max(a);
			for (int k = 0; k < a.length; k++)
			{
				if (a[k] == M)
				{
					poker3.addElement(poker4.get(k));
					poker4.removeElementAt(k);
					break;
				}
			}
		}
		return poker3;
	}

	public static void xuandizhu()
	{
		// 选地主
		int qiang = 0;
		dizhu = (int) (Math.random() * 3);
		int kk = dizhu;
		if (qiang == 0)
		{
			System.out.println("现在由Player" + kk + "选地主。");
			System.out.println("选(yes);不选(no)");
		}
		else
		{
			System.out.println("现在由Player" + kk + "抢地主。");
			System.out.println("抢(yes);不抢(no)");
		}
		System.out.print("请输入:");
		String xuanze = scanner.next();
		switch (xuanze)
		{
		case "yes":
			qiang++;
			zuidizhu = dizhu % 3;
			break;
		case "no":
			break;
		default:
			System.err.println("非法操作!失去机会。");
			break;
		}
		kk = (kk + 1) % 3;
		if (qiang == 0)
		{
			System.out.println("现在由Player" + kk + "选地主。");
			System.out.println("选(yes);不选(no)");
		}
		else
		{
			System.out.println("现在由Player" + kk + "抢地主。");
			System.out.println("抢(yes);不抢(no)");
		}
		System.out.print("请输入:");
		String xuanze1 = scanner.next();
		switch (xuanze1)
		{
		case "yes":
			qiang++;
			zuidizhu = (dizhu + 1) % 3;
			break;
		case "no":
			break;
		default:
			System.err.println("非法操作!失去机会。");
			break;
		}
		kk = (kk + 1) % 3;
		if (qiang == 0)
		{
			System.out.println("现在由Player" + kk + "选地主。");
			System.out.println("选(yes);不选(no)");
		}
		else
		{
			System.out.println("现在由Player" + kk + "抢地主。");
			System.out.println("抢(yes);不抢(no)");
		}
		System.out.print("请输入:");
		String xuanze2 = scanner.next();
		switch (xuanze2)
		{
		case "yes":
			qiang++;
			zuidizhu = (dizhu + 2) % 3;
			break;
		case "no":
			break;
		default:
			System.err.println("非法操作!失去机会。");
			break;
		}
		kk = (kk + 1) % 3;
		if (qiang == 0)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
			}
			System.err.println("\n都不选\n重新开始:");
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
			}
			restart();
		}
		else
		{
			if (zuidizhu != dizhu)
			{
				System.out.println("现在由Player" + kk + "抢地主。");
				System.out.println("抢(yes);不抢(no)");
				System.out.print("请输入:");
				String xuanze3 = scanner.next();
				switch (xuanze3)
				{
				case "yes":
					qiang++;
					zuidizhu = (dizhu + 3) % 3;
					break;
				case "no":
					break;
				default:
					System.err.println("非法操作!失去机会。");
					break;
				}
			}
		}
		switch (zuidizhu)
		{
		case 1:
			farmer1.addAll(pokers);
			landlord = farmer1;
			break;
		case 2:
			farmer2.addAll(pokers);
			landlord = farmer2;
			break;
		case 0:
			farmer3.addAll(pokers);
			landlord = farmer3;
			break;
		}
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
		}
		System.err.println("地主:Player" + zuidizhu + "\n大家一起斗地主!");
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
		}
		liangdipai();
		liangpai();
	}

	public static void restart()
	{
		// 重新开始，关键数据归零
		p = null;
		paixing = 0;
		turn = 2;
		pokers = new Vector<Poker>();
		pokers1 = new Vector<Poker>();
		farmer1 = new Vector<Poker>();
		farmer2 = new Vector<Poker>();
		farmer3 = new Vector<Poker>();
		landlord = new Vector<Poker>();
		gameStart();
	}

	public static void liangdipai()
	{
		// 亮底牌
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
		}
		System.err.print("\n底牌:  ");
		for (int i = 0; i < pokers.size(); i++)
		{
			System.out.print(pokers.get(i).Name);
		}
		System.out.println();
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
		}
	}
}

class Poker_Style extends Poker
{

	public static Vector<Integer> v11 = new Vector<Integer>(),
			v22 = new Vector<Integer>(), v33 = new Vector<Integer>(),
			v44 = new Vector<Integer>();

	public Poker_Style(String name, int value)
	{
		super(name, value);
	}

	public static int paixing(int size)
	{
		// 检查牌型
		int tpaixing = 0;
		v44 = fenxi(4);
		v33 = fenxi(3);
		v22 = fenxi(2);
		v11 = fenxi(1);
		if (v44.isEmpty() && v33.isEmpty() && v22.isEmpty() && (size == 1))
		{
			// d单牌
			tpaixing = 1;
		}
		else if ((size == 2) && v11.isEmpty() && v33.isEmpty() && v44.isEmpty())
		{
			// 对子
			tpaixing = 2;
		}
		else if ((size == 2) && v22.isEmpty() && v33.isEmpty() && v44.isEmpty()
				&& v11.contains(16) && v11.contains(17))
		{
			// 王炸
			tpaixing = 100;
		}
		else if ((size == 3) && v11.isEmpty() && v22.isEmpty() && v44.isEmpty())
		{
			// 三个
			tpaixing = 3;
		}
		else if (size == 4)
		{
			if (v22.isEmpty() && v44.isEmpty() && (!v33.isEmpty()))
			{
				// 三带一
				tpaixing = 4;
			}
			else if (v11.isEmpty() && v22.isEmpty() && v33.isEmpty())
			{
				// 炸弹
				tpaixing = 99;
			}
		}
		else if (size == 5)
		{
			if (v22.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v11) - MyVector.min(v11)) == (size - 1))
					&& (MyVector.max(v11) < 15))
			{
				// 五顺
				tpaixing = 5;
			}
			else if (v11.isEmpty() && v44.isEmpty() && !v22.isEmpty()
					&& !v33.isEmpty())
			{
				// 三带一对
				tpaixing = 6;
			}
			else if (v22.isEmpty() && v33.isEmpty() && !v11.isEmpty()
					&& !v44.isEmpty())
			{
				// 四带一
				tpaixing = 7;
			}
		}
		else if (size == 6)
		{
			if (v22.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v11) - MyVector.min(v11)) == (size - 1))
					&& (MyVector.max(v11) < 15))
			{
				// 六顺
				tpaixing = 8;
			}
			else if (v33.isEmpty() && !v44.isEmpty())
			{
				// 四带二
				tpaixing = 9;
			}
			else if (v11.isEmpty() && v22.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v33) - MyVector.min(v33)) == 1)
					&& (MyVector.max(v33) < 15))
			{
				// 二飞机不带
				tpaixing = 10;
			}
			else if (v11.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v22) - MyVector.min(v22)) == 2)
					&& (MyVector.max(v22) < 15))
			{
				// 三连对
				tpaixing = 11;
			}
		}
		else if (size == 7)
		{
			if (v22.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v11) - MyVector.min(v11)) == (size - 1))
					&& (MyVector.max(v11) < 15))
			{
				// 七顺
				tpaixing = 12;
			}
			else if (!v11.isEmpty() && v22.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v33) - MyVector.min(v33)) == 1)
					&& (MyVector.max(v33) < 15))
			{
				// 二飞机带一
				tpaixing = 13;
			}
		}
		else if (size == 8)
		{
			if (v22.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v11) - MyVector.min(v11)) == (size - 1))
					&& (MyVector.max(v11) < 15))
			{
				// 八顺
				tpaixing = 14;
			}
			else if (v44.isEmpty() && !v33.isEmpty()
					&& ((MyVector.max(v33) - MyVector.min(v33)) == 1)
					&& (MyVector.max(v33) < 15))
			{
				// 二飞机带二
				tpaixing = 15;
			}
			else if (v11.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v22) - MyVector.min(v22)) == 3)
					&& (MyVector.max(v22) < 15))
			{
				// 四连对
				tpaixing = 16;
			}
		}
		else if (size == 9)
		{
			if (v22.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v11) - MyVector.min(v11)) == (size - 1))
					&& (MyVector.max(v11) < 15))
			{
				// 九顺
				tpaixing = 17;
			}
			else if (v44.isEmpty() && v11.isEmpty() && v22.isEmpty()
					&& !v33.isEmpty()
					&& ((MyVector.max(v33) - MyVector.min(v33)) == 2)
					&& (MyVector.max(v33) < 15))
			{
				// 三飞机不带
				tpaixing = 18;
			}
		}
		else if (size == 10)
		{
			if (v22.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v11) - MyVector.min(v11)) == (size - 1))
					&& (MyVector.max(v11) < 15))
			{
				// 十顺
				tpaixing = 19;
			}
			else if (v44.isEmpty() && !v11.isEmpty() && v22.isEmpty()
					&& !v33.isEmpty()
					&& ((MyVector.max(v33) - MyVector.min(v33)) == 2)
					&& (MyVector.max(v33) < 15))
			{
				// 三飞机带一
				tpaixing = 20;
			}
			else if (v11.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v22) - MyVector.min(v22)) == 4)
					&& (MyVector.max(v22) < 15))
			{
				// 五连对
				tpaixing = 21;
			}
		}
		else if (size == 11)
		{
			if (v22.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v11) - MyVector.min(v11)) == (size - 1))
					&& (MyVector.max(v11) < 15))
			{
				// 十一顺
				tpaixing = 22;
			}
			else if (v44.isEmpty() && !v33.isEmpty()
					&& ((MyVector.max(v33) - MyVector.min(v33)) == 2)
					&& (MyVector.max(v33) < 15))
			{
				// 三飞机带二
				tpaixing = 23;
			}
		}
		else if (size == 12)
		{
			if (v22.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v11) - MyVector.min(v11)) == (size - 1))
					&& (MyVector.max(v11) < 15))
			{
				// 十二顺
				tpaixing = 24;
			}
			else if (v44.isEmpty() && !v33.isEmpty())
			{
				if (((MyVector.max(v33) - MyVector.min(v33)) == 2)
						&& (MyVector.max(v33) < 15))
				{
					// 三飞机带三
					tpaixing = 25;
				}
				else if (MyVector.max(v33) == 15)
				{
					Vector<Integer> v333 = new Vector<Integer>();
					v333.addAll(v33);
					v333.removeElement(15);
					if (((MyVector.max(v333) - MyVector.min(v333)) == 2)
							&& (MyVector.max(v333) < 15))
					{
						// 三飞机带三
						tpaixing = 25;
					}
				}
			}
			else if (v44.isEmpty() && v11.isEmpty() && v22.isEmpty()
					&& !v33.isEmpty()
					&& ((MyVector.max(v33) - MyVector.min(v33)) == 3)
					&& (MyVector.max(v33) < 15))
			{
				// 四飞机不带
				tpaixing = 26;
			}
			else if (v11.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v22) - MyVector.min(v22)) == 5)
					&& (MyVector.max(v22) < 15))
			{
				// 六连对
				tpaixing = 27;
			}
		}
		else if (size == 13)
		{
			if (v44.isEmpty() && !v11.isEmpty() && v22.isEmpty()
					&& !v33.isEmpty()
					&& ((MyVector.max(v33) - MyVector.min(v33)) == 3)
					&& (MyVector.max(v33) < 15))
			{
				// 四飞机带一
				tpaixing = 28;
			}
		}
		else if (size == 14)
		{
			if (v44.isEmpty() && !v33.isEmpty()
					&& ((MyVector.max(v33) - MyVector.min(v33)) == 3)
					&& (MyVector.max(v33) < 15))
			{
				// 四飞机带二
				tpaixing = 29;
			}
			else if (v11.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v22) - MyVector.min(v22)) == 6)
					&& (MyVector.max(v22) < 15))
			{
				// 七连对
				tpaixing = 30;
			}
		}
		else if (size == 15)
		{
			if (!v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v33) - MyVector.min(v33)) == 3)
					&& (MyVector.max(v33) < 15))
			{
				// 四飞机带三
				tpaixing = 31;
			}
			else if (v33.size() == 5)
			{
				if (MyVector.max(v33) == 15)
				{
					Vector<Integer> v333 = new Vector<Integer>();
					v333.addAll(v33);
					v333.removeElement(15);
					if (((MyVector.max(v333) - MyVector.min(v333)) == 3)
							&& (MyVector.max(v333) < 15))
					{
						// 四飞机带三
						tpaixing = 31;
					}
				}
			}
			else if (!v33.isEmpty() && v44.isEmpty() && v11.isEmpty()
					&& v22.isEmpty()
					&& ((MyVector.max(v33) - MyVector.min(v33)) == 4)
					&& (MyVector.max(v33) < 15))
			{
				// 五飞机不带
				tpaixing = 32;
			}
		}
		else if (size == 16)
		{
			if (v33.size() == 4)
			{
				if (((MyVector.max(v33) - MyVector.min(v33)) == 3)
						&& (MyVector.max(v33) < 15))
				{
					// 四飞机带四
					tpaixing = 33;
				}
			}
			else if (v33.size() == 5)
			{
				if (((MyVector.max(v33) - MyVector.min(v33)) == 4)
						&& (MyVector.max(v33) < 15) && (v11.size() == 1))
				{
					// 五飞机带一
					tpaixing = 34;
				}
				else if (MyVector.max(v33) == 15)
				{
					Vector<Integer> v333 = new Vector<Integer>();
					v333.addAll(v33);
					v333.removeElement(15);
					if (((MyVector.max(v333) - MyVector.min(v333)) == 3)
							&& (MyVector.max(v333) < 15))
					{
						// 四飞机带四
						tpaixing = 33;
					}
				}
			}
			else if (v11.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v22) - MyVector.min(v22)) == 7)
					&& (MyVector.max(v22) < 15))
			{
				// 八连对
				tpaixing = 35;
			}
		}
		else if (size == 17)
		{
			if (((MyVector.max(v33) - MyVector.min(v33)) == 4)
					&& (MyVector.max(v33) < 15))
			{
				// 五飞机带二
				tpaixing = 36;
			}
		}
		else if (size == 18)
		{
			if (((MyVector.max(v33) - MyVector.min(v33)) == 4)
					&& (MyVector.max(v33) < 15))
			{
				// 五飞机带三
				tpaixing = 37;
			}
			if (MyVector.max(v33) == 15)
			{
				Vector<Integer> v333 = new Vector<Integer>();
				v333.addAll(v33);
				v333.removeElement(15);
				if (((MyVector.max(v333) - MyVector.min(v333)) == 4)
						&& (MyVector.max(v333) < 15) && v11.isEmpty()
						&& v22.isEmpty())
				{
					// 五飞机带三
					tpaixing = 37;
				}
			}
			if (((MyVector.max(v33) - MyVector.min(v33)) == 5)
					&& (MyVector.max(v33) < 15))
			{
				// 六飞机不带
				tpaixing = 38;
			}
			if (v11.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v22) - MyVector.min(v22)) == 8)
					&& (MyVector.max(v22) < 15))
			{
				// 九连对
				tpaixing = 39;
			}
		}
		else if (size == 19)
		{
			if (((MyVector.max(v33) - MyVector.min(v33)) == 4)
					&& (MyVector.max(v33) < 15))
			{
				// 五飞机带四
				tpaixing = 40;
			}
			if (MyVector.max(v33) == 15)
			{
				Vector<Integer> v333 = new Vector<Integer>();
				v333.addAll(v33);
				v333.removeElement(15);
				if (((MyVector.max(v333) - MyVector.min(v333)) == 4)
						&& (MyVector.max(v333) < 15) && !v11.isEmpty()
						&& v22.isEmpty())
				{
					// 五飞机带四
					tpaixing = 40;
				}
			}
			if (((MyVector.max(v33) - MyVector.min(v33)) == 5)
					&& (MyVector.max(v33) < 15))
			{
				// 六飞机带一
				tpaixing = 41;
			}
		}
		else if (size == 20)
		{
			if (v11.isEmpty() && v33.isEmpty() && v44.isEmpty()
					&& ((MyVector.max(v22) - MyVector.min(v22)) == 9)
					&& (MyVector.max(v22) < 15))
			{
				// 十连对
				tpaixing = 42;
			}
			if (((MyVector.max(v33) - MyVector.min(v33)) == 5)
					&& (MyVector.max(v33) < 15))
			{
				// 六飞机带二
				tpaixing = 43;
			}
			if (((MyVector.max(v33) - MyVector.min(v33)) == 4)
					&& (MyVector.max(v33) < 15))
			{
				// 五飞机带五
				tpaixing = 44;
			}
			if (MyVector.max(v33) == 15)
			{
				Vector<Integer> v333 = new Vector<Integer>();
				v333.addAll(v33);
				v333.removeElement(15);
				if (((MyVector.max(v333) - MyVector.min(v333)) == 4)
						&& (MyVector.max(v333) < 15))
				{
					// 五飞机带五
					tpaixing = 44;
				}
			}
		}
		return tpaixing;
	}

	public static Vector<Integer> fenxi(int c)
	{
		int[] ss = new int[size];
		Vector<Integer> v = new Vector<Integer>();
		for (int j = 0; j < size; j++)
		{
			for (int i = 0; i < pokers1.size(); i++)
			{
				if (pokers1.get(i).Name.toCharArray()[0] == p[j])
				{
					ss[j] = pokers1.get(i).Value;
					v.addElement(ss[j]);
					break;
				}
			}
		}
		Vector<Integer> v4 = new Vector<Integer>();
		v4 = getmembers(v, 4);
		for (int k = 0; k < v4.size(); k++)
		{
			v.removeElement(v4.get(k));
			v.removeElement(v4.get(k));
			v.removeElement(v4.get(k));
			v.removeElement(v4.get(k));
		}
		Vector<Integer> v3 = new Vector<Integer>();
		v3 = getmembers(v, 3);
		for (int k = 0; k < v3.size(); k++)
		{
			v.removeElement(v3.get(k));
			v.removeElement(v3.get(k));
			v.removeElement(v3.get(k));
		}
		Vector<Integer> v2 = new Vector<Integer>();
		v2 = getmembers(v, 2);
		for (int k = 0; k < v2.size(); k++)
		{
			v.removeElement(v2.get(k));
			v.removeElement(v2.get(k));
		}
		Vector<Integer> v1 = new Vector<Integer>();
		v1 = v;
		switch (c)
		{
		case 4:
			return v4;
		case 3:
			return v3;
		case 2:
			return v2;
		case 1:
			return v1;
		}
		return v;
	}

	public static Vector<Integer> getmembers(Vector<Integer> v, int c)
	{
		Vector<Integer> vector = new Vector<Integer>();
		switch (c)
		{
		case 4:
			for (int i = 0; i < v.size(); i++)
			{
				for (int j = 0; (j < v.size()) && (j != i); j++)
				{
					for (int n = 0; (n < v.size()) && (n != i) && (n != j); n++)
					{
						for (int m = 0; (m < v.size()) && (m != n) && (m != j)
								&& (m != i); m++)
						{
							if ((v.get(j) == v.get(i))
									&& (v.get(n) == v.get(j))
									&& (v.get(m) == v.get(n)))
							{
								vector.addElement(v.get(j));
							}
						}
					}
				}
			}
			break;
		case 3:
			for (int i = 0; i < v.size(); i++)
			{
				for (int j = 0; (j < v.size()) && (j != i); j++)
				{
					for (int n = 0; (n < v.size()) && (n != i) && (n != j); n++)
					{
						if ((v.get(j) == v.get(i)) && (v.get(n) == v.get(j)))
						{
							vector.addElement(v.get(j));
						}
					}
				}
			}
			break;
		case 2:
			for (int j = 0; (j < v.size()); j++)
			{
				for (int n = 0; (n < v.size()) && (n != j); n++)
				{
					if ((v.get(n) == v.get(j)))
					{
						vector.addElement(v.get(j));
					}
				}
			}
			break;
		default:
			break;
		}
		vector = chuchong(vector);
		return vector;
	}

	public static Vector<Integer> chuchong(Vector<Integer> v)
	{
		Vector<Integer> v1 = new Vector<Integer>();
		for (int i = 0; i < v.size(); i++)
		{
			if (!v1.contains(v.get(i)))
			{
				v1.addElement(v.get(i));
			}
		}
		return v1;
	}
}

class Checkdaxiao extends Poker
{

	public Checkdaxiao(String name, int value)
	{
		super(name, value);
	}

	public static boolean checkdaxiao()
	{
		// 检查大小
		boolean x = false;
		switch (paixing)
		{
		case 1:
		case 5:
		case 8:
		case 12:
		case 14:
		case 17:
		case 19:
		case 22:
		case 24:
			x = checkvalue1();
			break;
		case 2:
		case 11:
		case 16:
		case 21:
		case 27:
		case 30:
		case 35:
		case 39:
		case 42:
			x = checkvalue2();
			break;
		case 3:
		case 4:
		case 6:
		case 10:
		case 13:
		case 15:
		case 18:
		case 20:
		case 23:
			x = checkvalue3();
			break;
		case 7:
		case 9:
			x = checkvalue4();
			break;
		case 25:
		case 26:
		case 28:
		case 29:
		case 31:
		case 32:
		case 33:
		case 34:
		case 36:
		case 37:
		case 38:
		case 40:
		case 41:
		case 43:
		case 44:
			x = checkvalue5();
			break;
		case 99:
			x = checkvalue6();
			break;
		case 100:
			x = false;
			break;
		}
		return x;
	}

	public static boolean checkvalue1()
	{
		boolean x = false;
		if ((paixing1 == paixing) && (turn < 2))
		{
			if (MyVector.max(v1) > MyVector.max(v01))
			{
				x = true;
			}
		}
		else if ((paixing1 == 99) || (paixing1 == 100))
		{
			x = true;
		}
		return x;
	}

	public static boolean checkvalue2()
	{
		boolean x = false;
		if ((paixing1 == paixing) && (turn < 2))
		{
			if (MyVector.max(v2) > MyVector.max(v02))
			{
				x = true;
			}
		}
		else if ((paixing1 == 99) || (paixing1 == 100))
		{
			x = true;
		}
		return x;
	}

	public static boolean checkvalue3()
	{
		boolean x = false;
		if ((paixing1 == paixing) && (turn < 2))
		{
			if (MyVector.max(v3) > MyVector.max(v03))
			{
				x = true;
			}
		}
		else if ((paixing1 == 99) || (paixing1 == 100))
		{
			x = true;
		}
		return x;
	}

	public static boolean checkvalue4()
	{
		boolean x = false;
		if ((paixing1 == paixing) && (turn < 2))
		{
			if (MyVector.max(v4) > MyVector.max(v04))
			{
				x = true;
			}
		}
		else if ((paixing1 == 99) || (paixing1 == 100))
		{
			x = true;
		}
		return x;
	}

	public static boolean checkvalue5()
	{
		boolean x = false;
		if ((paixing1 == paixing) && (turn < 2))
		{
			Vector<Integer> v03333 = new Vector<Integer>();
			Vector<Integer> v3333 = new Vector<Integer>();
			v3333.addAll(v3);
			v03333.addAll(v03);
			if (v3.contains(15))
			{
				v3333.removeElement(15);
			}
			if (v03.contains(15))
			{
				v03333.removeElement(15);
			}
			if (MyVector.max(v3333) > MyVector.max(v03333))
			{
				x = true;
			}
		}
		else if ((paixing1 == 99) || (paixing1 == 100))
		{
			x = true;
		}
		return x;
	}

	public static boolean checkvalue6()
	{
		boolean x = false;
		if ((paixing1 == paixing) && (turn < 2))
		{
			if (MyVector.max(v4) > MyVector.max(v04))
			{
				x = true;
			}
		}
		else if ((paixing1 == 100))
		{
			x = true;
		}
		return x;
	}
}

public class ChinesePoker extends Poker
{

	public ChinesePoker(String name, int value)
	{
		super(name, value);
	}

	public static void main(String[] args)
	{
		Poker.gameStart();
	}
}

class MyVector
{

	public static int max(Vector<Integer> a)
	{
		Vector<Integer> aa = new Vector<Integer>();
		aa.addAll(0, a);
		int[] aaa = new int[aa.size()];
		Enumeration<Integer> p = aa.elements();
		p.hasMoreElements();
		int i = 0;
		for (; i < aaa.length; i++)
		{
			aaa[i] = p.nextElement();
		}
		int top = MyArray.max(aaa);
		return top;
	}

	public static int min(Vector<Integer> a)
	{
		Vector<Integer> aa = new Vector<Integer>();
		aa.addAll(0, a);
		int[] aaa = new int[aa.size()];
		Enumeration<Integer> p = aa.elements();
		p.hasMoreElements();
		int i = 0;
		for (; i < aaa.length; i++)
		{
			aaa[i] = p.nextElement();
		}
		int top = MyArray.min(aaa);
		return top;
	}
}

class MyArray
{

	public static int max(int[] a)
	{
		int x;
		int aa[] = new int[a.length];
		System.arraycopy(a, 0, aa, 0, a.length);
		x = aa[0];
		for (int i = 1; i < aa.length; i++)
		{
			if (aa[i] > x)
			{
				x = aa[i];
			}
		}
		return x;
	}

	public static int min(int[] a)
	{
		int x;
		int aa[] = new int[a.length];
		System.arraycopy(a, 0, aa, 0, a.length);
		x = aa[0];
		for (int i = 1; i < aa.length; i++)
		{
			if (aa[i] < x)
			{
				x = aa[i];
			}
		}
		return x;
	}
}
