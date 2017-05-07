package dynamicprograming;

import java.util.Scanner;
/**
 * 问题：取数字问题
 * 问题描述：
 * 给定mxn的矩阵，其中的每个元素是-10到10之间的整数，你的任务是从左上角（1,1）
 * 走到右下角(m, n)，每一步只能够向右或者向下，并且不能够走出矩阵的范围。你所经过
 * 的方格里面的数字都必须被选取，请找出一条最适合的道路，使得在路上被选取的数字之和
 * 尽可能小的正整数。
 * 
 * 输入格式：
 * 输入第1行是两个正整数m,n,(2<=m<=10, 2<=n<=10),分别表示矩阵的行和列的数目，
 * 接下来m行，每行包括n个整数，就是矩阵中的每一行的n个元素。
 * 
 * 输出格式：
 * 输出只有一行，就是一个整数，表示所选道路上数字之和所能到达的最小正整数。如果不能
 * 达到任何正整数，输出-1;
 * 
 * */
public class QuShuZi {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		save = new int[m][n];
		int[][] a = new int[m][n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		
		solution(m, n, a);
		int min = Math.min(save[0][1], save[1][0]);
		if (min < 0) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}

	}
	
	static int[][] save = null;
	static void solution(int m, int n, int[][] a) {
		if (a == null ||  a.length <2) {
			return;
		}
		
		save[m-1][n-2] = a[m-1][n-2];
		save[m-2][n-1] = a[m-2][n-1];
		for (int i = a[m-1].length-3; i >=0 ; i--) {
			save[m-1][i] = a[m-1][i];
		}
		
		for (int i = a.length-3; i >=0; i--) {
			save[i][n-1] = a[i][n-1];
		}
		
		for (int i = a.length-2; i >=1; i--) {
			for (int j = a[i].length-2; j >=0; j--) {
				save[i][j] = Math.min(save[i+1][j], save[i][j+1]) + a[i][j];
			}
		}
		
		for (int i = a[0].length-2; i >=1; i--) {
			save[0][i] = Math.min(save[0][i+1], save[1][i]) + a[0][i];
		}
		
	}

}
