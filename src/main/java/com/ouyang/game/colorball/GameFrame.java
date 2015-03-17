package com.ouyang.game.colorball;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;

import javax.swing.*;

/**
 * 游戏主框架类
 * 
 * @author 樊宜 fanallen_1@sina.com
 * 
 */
public class GameFrame extends JFrame
{
	private static final long serialVersionUID = 6334925997091906407L;
	private static final int gridSize = 32; // 每个格子的长度与宽度
	private static final int gridCount = 10; // 行数、列数
	private static final int itemCount = 5;
	private static final int addBallCount = 3;
	private static final int nothing = 0;
	private static final int linkMinNum = 5;
	private JPanel drawPanel = new JPanel();
	private JPanel contrlPanel = new JPanel();
	private GridUnit[][] uiObjects = new GridUnit[gridCount][gridCount];
	private ImageIcon[] images = new ImageIcon[itemCount + 1];
	private static int nextBalls[] = new int[addBallCount];
	private int preBall = -1;
	private JLabel scoreLabel = new JLabel();
	private JLabel ball1 = new JLabel();
	private JLabel ball2 = new JLabel();
	private JLabel ball3 = new JLabel();
	private int score = 0;

	public GameFrame()
	{
		loadImages();
		initContrlPanel();
		initDrawPanel();
		createBalls();
		createPosition();
		createBalls();
		initControlPanel();
		add(drawPanel, BorderLayout.CENTER);
		add(contrlPanel, BorderLayout.EAST);
		pack();
		setResizable(false);

	}

	private void initControlPanel()
	{
		contrlPanel.setBackground(Color.white);
		scoreLabel = new JLabel();
		scoreLabel.setFont(new Font("宋体", Font.BOLD, 15));
		scoreLabel.setText("记分：" + score);
		FlowLayout fl = new FlowLayout();
		contrlPanel.setLayout(fl);
		fl.setVgap(10);
		fl.setHgap(50);
		ball1.setIcon(images[nextBalls[0]]);
		ball2.setIcon(images[nextBalls[1]]);
		ball3.setIcon(images[nextBalls[2]]);
		contrlPanel.add(scoreLabel);
		contrlPanel.add(ball1);
		contrlPanel.add(ball2);
		contrlPanel.add(ball3);
	}

	private void refreshScore()
	{
		scoreLabel.setText("记分：" + score);
	}

	private void loadImages()
	{
		for (int i = 0; i < itemCount + 1; ++i)
		{
			URL url = this.getClass().getResource(i + ".png");
			System.out.println(url);
			images[i] = new ImageIcon(url);
		}
	}

	private void initContrlPanel()
	{
		contrlPanel.setPreferredSize(new Dimension(100, 100));
	}

	private void initDrawPanel()
	{
		drawPanel.setPreferredSize(new Dimension(gridCount * gridSize,
				gridCount * gridSize));
		drawPanel.setLayout(new GridLayout(gridCount, gridCount));
		for (int i = 0; i < gridCount; ++i)
		{
			for (int j = 0; j < gridCount; ++j)
			{
				uiObjects[i][j] = new GridUnit(i, j, nothing);
				drawPanel.add(uiObjects[i][j]);
			}
		}
	}

	/**
	 * 随机创建三个球
	 */
	private void createBalls()
	{
		Random rand = new Random();
		for (int i = 0; i < addBallCount; ++i)
		{
			while (true)
			{
				int v = rand.nextInt(itemCount);
				if (v != 0)
				{
					nextBalls[i] = v;
					break;
				}
			}
		}
		ball1.setIcon(images[nextBalls[0]]);
		ball2.setIcon(images[nextBalls[1]]);
		ball3.setIcon(images[nextBalls[2]]);
	}

	/*
	 * 随机创建三个位置用于放球
	 */
	private void createPosition()
	{
		Random r = new Random();
		for (int i = 0; i < addBallCount; ++i)
		{
			while (true)
			{
				int v = r.nextInt(gridCount * gridCount);
				int x = v % gridCount;
				int y = v / gridCount;
				if (uiObjects[y][x].id == nothing)
				{
					uiObjects[y][x].setID(nextBalls[i]);
					break;
				}
			}
		}
	}

	public int[] findPath(int from, int to)
	{
		ArrayList<Integer> bars = new ArrayList<Integer>();
		PathFinder finder = new PathFinder(gridCount, gridCount);
		for (int i = 0; i < uiObjects.length; i++)
		{
			for (int j = 0; j < gridCount; j++)
			{
				if (uiObjects[i][j].id != nothing)
				{
					bars.add(i * gridCount + j);
				}
			}
		}
		int[] param = new int[bars.size()];
		for (int i = 0; i < param.length; ++i)
		{
			param[i] = bars.get(i);
		}
		finder.setBarrier(param);
		return finder.findShortestPath(from, to);
	}

	private int getPosition(int row, int col)
	{
		return row * gridCount + col;
	}

	/**
	 * 清除指定一系列位置的球，并适当加分
	 * 
	 * @param path
	 *            位置列表
	 * @return 消除成功返回true，反之返回false
	 */
	private boolean clearPosition(ArrayList<Integer> path)
	{
		if (path.size() >= linkMinNum)
		{ // 相同球的数量必须在linkMinNum以上
			score += path.size();
			for (int pos : path)
			{
				int x = pos / gridCount;
				int y = pos % gridCount;
				uiObjects[x][y].setID(nothing);
			}
			refreshScore();
			return true;
		}
		return false;
	}

	/**
	 * 判断指定位置的球是否可以消除
	 * 
	 * @param id
	 *            球的类型代码
	 * @param row
	 *            行
	 * @param col
	 *            列
	 * @return 可以消除则返回true，反之返回false
	 */
	private boolean checkBallLink(int id, int row, int col)
	{
		assert id != nothing;
		ArrayList<Integer> path1 = getUpDownSameBall(id, row, col);
		ArrayList<Integer> path2 = getLeftRightSameBall(id, row, col);
		ArrayList<Integer> path3 = getLurdSameBall(id, row, col);
		ArrayList<Integer> path4 = getLdruSameBall(id, row, col);
		return (clearPosition(path1) || clearPosition(path2)
				|| clearPosition(path3) || clearPosition(path4));
	}

	/**
	 * 返回上下方向有相同代码球的位置列表
	 * 
	 * @param id
	 *            球的类型代码
	 * @param row
	 *            行
	 * @param col
	 *            列
	 * @return 可以消除则返回true，反之返回false
	 */
	private ArrayList<Integer> getUpDownSameBall(int id, int row, int col)
	{
		ArrayList<Integer> path = new ArrayList<Integer>();
		for (int i = row; i >= 0; --i)
		{
			if (uiObjects[i][col].id != id)
			{
				break;
			}
			path.add(getPosition(i, col));
		}
		for (int i = row + 1; i < gridCount; ++i)
		{
			if (uiObjects[i][col].id != id)
			{
				break;
			}
			path.add(getPosition(i, col));
		}
		return path;
	}

	/**
	 * 返回左右方向有相同代码球的位置列表
	 * 
	 * @param id
	 *            球类型代码
	 * @param row
	 *            行
	 * @param col
	 *            列
	 * @return
	 */
	private ArrayList<Integer> getLeftRightSameBall(int id, int row, int col)
	{
		ArrayList<Integer> path = new ArrayList<Integer>();
		for (int i = col; i >= 0; --i)
		{
			if (uiObjects[row][i].id != id)
			{
				break;
			}
			path.add(getPosition(row, i));
		}
		for (int i = col + 1; i < gridCount; ++i)
		{
			if (uiObjects[row][i].id != id)
			{
				break;
			}
			path.add(getPosition(row, i));
		}
		return path;
	}

	/**
	 * 返回左上右下方向有相同代码球的位置列表
	 * 
	 * @param id
	 *            球的类型代码
	 * @param row
	 *            行
	 * @param col
	 *            列
	 * @return 可以消除则返回true，反之返回false
	 */
	private ArrayList<Integer> getLurdSameBall(int id, int row, int col)
	{
		ArrayList<Integer> path = new ArrayList<Integer>();
		for (int i = row, j = col; i >= 0 && j >= 0; --i, --j)
		{
			if (uiObjects[i][j].id != id)
			{
				break;
			}
			path.add(getPosition(i, j));
		}
		for (int i = row + 1, j = col + 1; i < gridCount && j < gridCount; ++i, ++j)
		{
			if (uiObjects[i][j].id != id)
			{
				break;
			}
			path.add(getPosition(i, j));
		}
		return path;
	}

	/**
	 * 返回左下右上方向有相同代码球的位置列表
	 * 
	 * @param id
	 *            球的类型代码
	 * @param row
	 *            行
	 * @param col
	 *            列
	 * @return 可以消除则返回true，反之返回false
	 */
	private ArrayList<Integer> getLdruSameBall(int id, int row, int col)
	{
		ArrayList<Integer> path = new ArrayList<Integer>();
		for (int i = row, j = col; i < gridCount && j >= 0; ++i, --j)
		{
			if (uiObjects[i][j].id != id)
			{
				break;
			}
			path.add(getPosition(i, j));
		}
		for (int i = row - 1, j = col + 1; i >= 0 && j < gridCount; --i, ++j)
		{
			if (uiObjects[i][j].id != id)
			{
				break;
			}
			path.add(getPosition(i, j));
		}
		return path;
	}

	class GridUnit extends JLabel
	{
		private static final long serialVersionUID = 9154847238370940642L;
		int id = nothing;
		int row = -1;
		int col = -1;
		Thread thread;

		public GridUnit(int row, int col, int id)
		{
			super("", images[id], SwingConstants.CENTER);
			this.row = row;
			this.col = col;
			this.id = id;
			this.setBorder(BorderFactory.createLineBorder(Color.gray));
			this.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					onMouseClicked();
				}
			});
		}

		private void onMouseClicked()
		{
			if (id == nothing)
			{
				if (preBall >= 0)
				{ // 即将移动到这儿
					final int[] path = findPath(preBall, getPosition(row, col));
					if (path == null)
					{
						return;
					}
					new Thread()
					{
						public void run()
						{
							int initId = uiObjects[preBall / gridCount][preBall
									% gridCount].id;
							GridUnit preUnit = uiObjects[preBall / gridCount][preBall
									% gridCount];
							preUnit.stopAnimation();
							preUnit.setID(nothing);
							for (int i = 0; i < path.length; i++)
							{
								int x = path[i];
								uiObjects[x / gridCount][x % gridCount]
										.setID(initId);
								try
								{
									Thread.sleep(150);
								}
								catch (InterruptedException e)
								{
									e.printStackTrace();
								}
								if (i < path.length - 1)
								{
									uiObjects[x / gridCount][x % gridCount]
											.setID(nothing);
								}
								repaint();
							}
							preBall = -1;
							if (!checkBallLink(initId, row, col))
							{
								createPosition();
								createBalls();
							}
						}
					}.start();
				}
			}
			else
			{
				if (thread == null)
				{ // 点选一个球
					if (preBall >= 0)
					{ // 已经选择了一个球，需要选择一个移动到的位置
						int x = preBall % gridCount;
						int y = preBall / gridCount;
						uiObjects[y][x].stopAnimation();
					}
					preBall = getPosition(row, col);
					this.startAnimation();
				}
				else
				{ // 取消选择当前的球
					preBall = -1;
					this.stopAnimation();
				}
			}
		}

		public void setID(int id)
		{
			this.id = id;
			setIcon(images[id]);
		}

		/**
		 * 开始闪烁动画
		 */
		public void startAnimation()
		{
			thread = new Thread()
			{
				public void run()
				{
					while (!isInterrupted())
					{
						try
						{
							setIcon(null);
							sleep(500);
							setIcon(images[id]);
							sleep(500);
						}
						catch (InterruptedException e)
						{
							return;
						}
					}
				}
			};
			thread.start();
		}

		/**
		 * 停止闪烁动画
		 */
		public void stopAnimation()
		{
			setIcon(images[id]);
			if (thread != null)
			{
				thread.interrupt();
				thread = null;
			}
		}
	}

	public static void main(String[] args)
	{
		GameFrame frame = new GameFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2
				- frame.getHeight() / 2);
		frame.setVisible(true);
	}
}
