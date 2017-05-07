package dynamicprograming;

import java.util.Arrays;

public class ShuTa {
/**
 * 问题：数塔
 * 问题描述：
 * 给你一个数字三角形，形式如下：
 * 1
 * 2 3
 * 4 5 6
 * 7 8 9 10
 * 找出从第一层到最后一层的一条路径使得所经过的权值之和最小或最大。
 * 
 * */
	public static void main(String[] args) {
		int[][] shuTa = {{1}, {2, 3}, {4, 5, 6}, {7, 8, 9, 10}};
		for (int i = 0; i < shuTa.length; i++) {
			System.out.println(Arrays.toString(shuTa[i]));
		}
		solution(shuTa);
		System.out.println(save[0][0]);
		System.out.println(save[2][0]);
		System.out.println("=========solution2===========");
//		for (int i = 0; i < save1.length; i++) {
//			System.out.println(Arrays.toString(save1[i]));
//		}
		
		//solution2
		System.out.println(solution2(0, 0, shuTa));
		System.out.println(solution2(2, 0, shuTa));
		
		//solution3
		System.out.println("========solution3==========");
		solution3(shuTa);
//		for (int i = 0; i < shuTa.length; i++) {
//			System.out.println(Arrays.toString(save3[i]));
//		}
		
		int ans = 0;
		for (int i = 0; i < save3[save3.length-1].length; i++) {
			if (ans < save3[save3.length-1][i]) {
				ans = save3[save3.length-1][i];
			}
		}
		System.out.println(ans);

	}
	
	//以求最大值为例
	/**该方法是首先求出数组中所有元素的最优解，然后需要哪个元素的最优解直接从数组中取就行了*/
	// save[i][j]中存储的是从第i行第j个元素到最底层所经过的权值之和最大值
	static int[][] save = new int[4][4]; 
	public static void solution(int[][] shuTa) {
		
		for (int i = shuTa.length-1; i >= 0 ; i--) {
			if (i == shuTa.length-1) {
				for (int j = 0; j < shuTa[i].length; j++) {
					save[i][j] = shuTa[i][j];
				}
			} else {
				for (int j = 0; j < shuTa[i].length; j++) {
					save[i][j] = Math.max(save[i+1][j], save[i+1][j+1]) + shuTa[i][j];
				}
			}
		}
		
	}
	
	/**该方法并不是求出所有元素的最优解放入数组，而是需要哪个元素，只求该元素需要计算的最优解并放入数据即可*/
	static int[][] save1 = new int[4][4]; // 静态成员变量默认初始化为0
	
	public static int solution2(int i, int j, int[][] shuTa) {
		if (save1[i][j] != 0) {
			return save1[i][j];
		} else {
			if (i == shuTa.length-1) {
				save1[i][j] = shuTa[i][j];
				return save1[i][j];
			} else {
				save1[i][j] = Math.max(solution2(i+1, j, shuTa), solution2(i+1, j+1, shuTa)) + shuTa[i][j];
				return save1[i][j];
			}
		}
		
	}
	
	/**使用循环算法求从顶层到最低层权值和最大的值(自顶向下)*/
	static int[][] save3 =  new int[4][4];
	static void solution3(int[][] shuTa) {
		save3[0][0] = shuTa[0][0];
		for (int i = 1; i < shuTa.length; i++) {
			save3[i][0] = save3[i-1][0] + shuTa[i][0];
			for (int j = 1; j < shuTa[i].length-1; j++) {
				save3[i][j] = Math.max(save3[i-1][j], save3[i-1][j-1]) + shuTa[i][j];
			}
			save3[i][i] = save3[i-1][i-1] + shuTa[i][i];
		}
	}
	

}
















