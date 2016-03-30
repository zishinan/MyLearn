package com.ouyang.crackjubian;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * 破解游戏-聚变-各个关卡
 * @author <a href="xi.yang@i-jia.net">yangxi</a>
 */
public class CrackJuhe {
	public static void main(String[] args) {
		Level81 levels = new Level81();
		
		String initkey = "";
 		Map<String, StringBuffer> map = new HashMap<String, StringBuffer>();
 		map.put(initkey, levels.getLevelString());
 		Set<String> hasSet = new HashSet<>();
 		while (map.size() > 0) {
 			System.out.println(map.size());
 			Map<String, StringBuffer> temp = new HashMap<String, StringBuffer>();
			for (String key : map.keySet()) {
				StringBuffer current = map.get(key);
				for (int i = 0; i < current.length(); i++) {
					if(current.charAt(i) == '1'){
						continue;
					}
					int num = getNum(current,i,levels.getWidth());
					if(num < 2){
						continue;
					}
					
					StringBuffer afterBuffer = new StringBuffer(current.toString());
					change(afterBuffer,i,levels.getWidth());
					String afterKey = key+"-"+i;
					
					boolean isSuccess = validateBuffer(afterBuffer,levels.getSuc());
					
					if(isSuccess){
						printKey(levels.getLevel(),afterKey, levels.getWidth());
						continue;
					}
					
					if(hasSet.contains(afterBuffer.toString())){
						continue;
					}
					hasSet.add(afterBuffer.toString());
					temp.put(afterKey, afterBuffer);
					
				}
			}
			map.clear();
			map = temp;
		}
		
 		
	}

	private static void printKey(int level, String afterKey, int width) {
		System.out.println("==========================");
		System.out.println(level+"关解法:");
		StringBuffer buffer = initKey(width);
		String[] keys = afterKey.split("-");
		char c = 'A'-1;
		for (String string : keys) {
			if(string.length() <= 0){
				continue;
			}
			c++;
			int loc = Integer.parseInt(string);
			if(buffer.charAt(loc) != '0'){
				printKey(buffer, width);
				buffer = initKey(width);
				System.out.println("==========");
			}
			buffer.setCharAt(loc, c);
		}
		printKey(buffer, width);
		System.out.println("==========================");
	}
	
	private static void printKey(StringBuffer key, int width) {
		for (int i = 0; i < key.length(); i++) {
			if(i > 0 && i%width == 0){
				System.out.println();
			}
			System.out.print(key.charAt(i)+" ");
		}
		System.out.println();
	}


	private static boolean validateBuffer(StringBuffer afterBuffer, int suc) {
		int num = 0;
		int theSuc = -1;
		for (int i = 0; i < afterBuffer.length(); i++) {
			if(afterBuffer.charAt(i) == '1'){
				num++;
				theSuc = i;
			}
		}
		if(num == 1 && suc == theSuc){
			return true;
		}
		return false;
	}


	private static void change(StringBuffer current, int i, int width) {
		int loc = i+1;
		if(getValue(current, i+width, '0') == '1'){
			current.setCharAt(i+width, '0');
		}
		if(getValue(current, i-width, '0') == '1'){
			current.setCharAt(i-width, '0');
		}
		if(loc % width != 1 && getValue(current, i-1, '0') == '1'){
			current.setCharAt(i-1, '0');
		}
		if(loc % width != 0 && getValue(current, i+1, '0') == '1'){
			current.setCharAt(i+1, '0');
		}
		current.setCharAt(i, '1');
	}

	private static int getNum(StringBuffer current, int i, int width) {
		int num = 0;
		int loc = i+1;
		if(getValue(current, i+width, '0') == '1'){
			num++;
		}
		if(getValue(current, i-width, '0') == '1'){
			num++;
		}
		if(loc % width != 1 && getValue(current, i-1, '0') == '1'){
			num++;
		}
		if(loc % width != 0 && getValue(current, i+1, '0') == '1'){
			num++;
		}
		return num;
	}

	private static char getValue(StringBuffer current, int i,char def) {
		char c = def;
		try {
			c = current.charAt(i);
		} catch (Exception e) {
		}
		return c;
	}

	private static StringBuffer initKey(int width) {
		char[] keyChar = new char[width*width];
		for (int i = 0; i < keyChar.length; i++) {
			keyChar[i] = '0';
		}
		return new StringBuffer(new String(keyChar));
	}
}
