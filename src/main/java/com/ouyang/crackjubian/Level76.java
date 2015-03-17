package com.ouyang.crackjubian;

public class Level76 {
	
	int level = 76;
	int width = 7;
	int suc = 38;
	
	StringBuffer levelString= new StringBuffer(""
			+ "0110000"
			+ "1001000"
			+ "1000100"
			+ "0011001"
			+ "0000010"
			+ "0010100"
			+ "0010100");

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
