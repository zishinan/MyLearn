package com.ouyang.crackjubian;

public class Level74 {
	
	int level = 74;
	int width = 7;
	int suc = 32;
	
	StringBuffer levelString= new StringBuffer(""
			+ "0011001"
			+ "1100011"
			+ "1100000"
			+ "0010010"
			+ "0110100"
			+ "1101011"
			+ "0001110");

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
