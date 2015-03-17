package com.ouyang.game.colorball;

import java.util.*;

/**
 * 在二维表格中寻找起始点到目标点的最短路径算法包装类 二维数组中位置与数组的对应关系举例： 0 1 2 3 4 5 6 7 8 9 10 11
 * 
 * @author 樊宜 fanallen_1@sina.com
 * 
 */
public class PathFinder
{
	static final int nothing = 0; // 位置空闲
	static final int barrier = 1; // 位置已通过
	static final int hasPass = 2; // 障碍
	static final int source = 5; // 起点位置
	static final int dest = 6; // 目标位置

	int[][] pathData; // 地图数据
	int rowCount = 10; // 地图的高度（二维数组的行数）
	int colCount = 10; // 地图宽度（二维数组的列数）

	/**
	 * 用指定的高度、宽度尺寸构造地图
	 * 
	 * @param rowCount
	 *            行数
	 * @param colCount
	 *            列数
	 */
	public PathFinder(int rowCount, int colCount)
	{
		this.rowCount = rowCount;
		this.colCount = colCount;
		pathData = new int[rowCount][colCount];
		for (int i = 0; i < rowCount; ++i)
		{
			Arrays.fill(pathData[i], 0);
		}
	}

	/**
	 * 用指定的高度、宽度尺寸构造地图
	 * 
	 * @param rowCount
	 *            行数
	 * @param colCount
	 *            列数
	 * @param barData
	 *            障碍物，数组中的元素代表位置编号
	 */
	public PathFinder(int rowCount, int colCount, int[] barData)
	{
		this(rowCount, colCount);
		setBarrier(barData);
	}

	/**
	 * 构造完对象后，应立即调用该方法设置地图中的障碍物
	 * 
	 * @param barData
	 *            障碍物，数组中的元素代表位置编号
	 */
	public void setBarrier(int[] barData)
	{
		if (barData != null)
		{
			for (int pos : barData)
			{
				int x = pos / colCount;
				int y = pos % colCount;
				pathData[x][y] = barrier;
			}
		}
	}

	/**
	 * 寻找路径
	 * 
	 * @param from
	 *            起始位置的编号
	 * @param to
	 *            终止位置的编号
	 * @return 路径列表
	 */
	public int[] findShortestPath(int from, int to)
	{
		// 设置起始、结束位置
		int x = from / colCount;
		int y = from % colCount;
		pathData[x][y] = source;
		x = to / colCount;
		y = to % colCount;
		pathData[x][y] = dest;
		// printPathData();
		Node root = buildPathTree(from);
		// printPathData();
		Node bestPath = null;
		for (Node node : root.leaves)
		{
			if (bestPath == null || bestPath.depth > node.depth)
			{
				if (this.isDestPos(node.position))
				{
					bestPath = node;
				}
			}
		}
		if (bestPath == null)
		{
			System.out.println("no path be find!");
			return null;
		}
		Stack<Integer> path = new Stack<Integer>();
		while (bestPath.parent != null)
		{
			path.add(bestPath.position);
			bestPath = bestPath.parent;
		}

		int[] ret = new int[path.size()];
		int i = 0;
		while (!path.isEmpty())
		{
			ret[i++] = path.pop();
		}
		return ret;
	}

	private Node buildPathTree(int from)
	{
		Node root = new Node(from, 0);
		LinkedList<Node> list = new LinkedList<Node>();
		ArrayList<Node> nodes = buildSubTree(root, root);
		saveNodes(list, nodes);
		while (!list.isEmpty())
		{
			saveNodes(list, buildSubTree(list.removeFirst(), root));
		}
		return root;
	}

	/**
	 * 将节点列表保存
	 * 
	 * @param list
	 * @param nodes
	 */
	private void saveNodes(LinkedList<Node> list, ArrayList<Node> nodes)
	{
		for (Node nd : nodes)
		{
			list.add(nd);
		}
	}

	private ArrayList<Node> buildSubTree(Node curNode, Node root)
	{
		assert (curNode != null && root != null);
		ArrayList<Integer> poses = this.searchReachablePos(curNode.position);
		ArrayList<Node> nodes = new ArrayList<Node>();
		if (poses.isEmpty())
		{
			root.addLeaf(curNode);
		}
		else
		{
			for (int pos : poses)
			{
				Node p = new Node(pos, curNode.depth + 1);
				p.setParent(curNode);
				nodes.add(p);
			}
			curNode.setChildren(nodes);
			for (Node node : nodes)
			{
				if (isDestPos(node.position))
				{
					root.addLeaf(node);
				}
				else
				{
					setPassed(node.position);
				}
			}
		}
		// this.printPathData();
		return nodes;
	}

	/**
	 * 设置该位置已经过
	 * 
	 * @param pos
	 *            位置
	 */
	private void setPassed(int pos)
	{
		int x = pos / colCount;
		int y = pos % colCount;
		pathData[x][y] = hasPass;
	}

	/*
	 * private void printPathData(){ for ( int i = 0 ; i < rowCount ; ++i){ for
	 * ( int j = 0 ; j < colCount ; ++j){ System.out.print(pathData[i][j] +
	 * "  "); } System.out.println(); }
	 * System.out.println("========================"); }
	 */
	/**
	 * 判断指定位置是否是目标位置
	 * 
	 * @param pos
	 * @return
	 */
	private boolean isDestPos(int pos)
	{
		int x = pos / colCount;
		int y = pos % colCount;
		return pathData[x][y] == dest;
	}

	/**
	 * 获取位置编号
	 * 
	 * @param row
	 *            行
	 * @param col
	 *            列
	 * @return
	 */
	private int getPosition(int row, int col)
	{
		return row * colCount + col;
	}

	/**
	 * 寻找指定位置的点可达到的周围位置
	 * 
	 * @param pos
	 * @return
	 */
	private ArrayList<Integer> searchReachablePos(int pos)
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int x = pos / colCount;
		int y = pos % colCount;
		if (x - 1 >= 0
				&& (pathData[x - 1][y] == nothing || pathData[x - 1][y] == dest))
		{
			ret.add(getPosition(x - 1, y));
		}
		if (x + 1 < rowCount
				&& (pathData[x + 1][y] == nothing || pathData[x + 1][y] == dest))
		{
			ret.add(getPosition(x + 1, y));
		}
		if (y - 1 >= 0
				&& (pathData[x][y - 1] == nothing || pathData[x][y - 1] == dest))
		{
			ret.add(getPosition(x, y - 1));
		}
		if (y + 1 < colCount
				&& (pathData[x][y + 1] == nothing || pathData[x][y + 1] == dest))
		{
			ret.add(getPosition(x, y + 1));
		}
		return ret;
	}

	static class Node
	{
		public int position;
		public int depth = 0;
		public Node parent;
		public ArrayList<Node> children = new ArrayList<Node>();
		public ArrayList<Node> leaves = new ArrayList<Node>();

		public Node(int pos, int depth)
		{
			this.position = pos;
			this.depth = depth;
		}

		public void setParent(Node node)
		{
			parent = node;
		}

		public void setChildren(ArrayList<Node> nodes)
		{
			for (Node nd : nodes)
			{
				children.add(nd);
			}
		}

		public void addChildren(Node node)
		{
			children.add(node);
		}

		public void addLeaf(Node node)
		{
			leaves.add(node);
		}
	}
}
