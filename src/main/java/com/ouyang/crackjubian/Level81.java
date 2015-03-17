package com.ouyang.crackjubian;

public class Level81 {
	
	int level = 81;
	int width = 8;
	int suc = 7;
	
	StringBuffer levelString= new StringBuffer(""
			+ "00011010"
			+ "00101011"
			+ "00100011"
			+ "01001100"
			+ "01010100"
			+ "11000100"
			+ "00010100"
			+ "11011100");

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getSuc() {
		return suc;
	}

	public void setSuc(int suc) {
		this.suc = suc;
	}

	public StringBuffer getLevelString() {
		return levelString;
	}

	public void setLevelString(StringBuffer levelString) {
		this.levelString = levelString;
	}

}
