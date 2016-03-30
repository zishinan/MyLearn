package com.ouyang.subject;

import org.apache.log4j.Logger;


/**
 * 破解表格填数字游戏
 * ?+?-9=4
 * + - -
 * ？-？*？=4
 * ! * -
 * ?+?-?=4
 * = = =
 * 4 4 4
 * TODO 该方法仍有问题，可以考虑不完全穷举法
 * @author <a href="xi.yang@i-jia.net">yangxi</a>
 */
public class TheNine {
	private static final Logger logger = Logger.getLogger(TheNine.class);

	public static void main(String[] args) {
		int num = 10000;
		int[][] arr = new int[3][3];
		arr[0][2] = 9;
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j <= num; j++) {
				for (int k = 0; k <= num; k++) {
					arr[0][1] = i;
					arr[1][1] = j;
					arr[1][2] = k;
					arr[0][0] = 13-arr[0][1];
					if(arr[0][0] < 0 || arr[0][0] > num){
						continue;
					}
					if(arr[0][0] == 4){
						continue;
					}
					arr[1][0] = 4+arr[1][1]*arr[1][2];
					if(arr[1][0] < 0 || arr[1][0] > num){
						continue;
					}
					arr[2][0] = arr[1][0]/(4-arr[0][0]);
					if(arr[2][0] < 0 || arr[2][0] > num){
						continue;
					}
					if(arr[2][0] == 0){
						continue;
					}
					if(arr[1][1] != 0){
						arr[2][1] = (arr[0][1]-4)/arr[1][1];
						if(arr[2][1] < 0 || arr[2][1] > num){
							continue;
						}
					}else {
						arr[2][1] = 4;
					}
					arr[2][2] = arr[0][2]-arr[1][2]-4;
					if(arr[2][2] < 0 || arr[2][2] > num){
						continue;
					}
					if(arr[2][0]+arr[2][1]-arr[2][2] != 4){
						continue;
					}
					System.out.println("========================");
					print(arr);
				}
			}
		}
	}
	
	public static void print(int[][] arr){
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	@Deprecated
	private static boolean isRight(int[][] arr){
		if(arr[0][0] + arr[0][1] - arr[0][2] != 4){
			return false;
		}
		if(arr[1][0] - arr[1][1] * arr[1][2] != 4){
			return false;
		}
		if(arr[2][0] + arr[2][1] - arr[2][2] != 4){
			return false;
		}
		if(arr[0][0] + arr[1][0] / arr[2][0] != 4){
			return false;
		}
		if(arr[0][1] - arr[1][1] * arr[2][1] != 4){
			return false;
		}
		if(arr[0][2] - arr[1][2] - arr[2][2] != 4){
			return false;
		}
		return true;
	}
	
}
