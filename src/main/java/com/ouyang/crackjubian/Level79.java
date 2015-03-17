package com.ouyang.crackjubian;

public class Level79 {
	
	int level = 76;
	int width = 7;
	int suc = 25;
	
	StringBuffer levelString= new StringBuffer(""
			+ "0001011"
			+ "1011110"
			+ "0101110"
			+ "0000001"
			+ "0000100"
			+ "0010101"
			+ "0001011");

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
