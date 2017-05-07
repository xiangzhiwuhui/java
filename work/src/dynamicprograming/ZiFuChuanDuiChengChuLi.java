package dynamicprograming;

import java.util.Scanner;

/**
 * 问题：字符串对称处理
 * 
 * 问题描述：
 * 要求对任意一个字符串，通过加入若干字符，使其对称。
 * 如Ab3bd,插入两个字符后可以变成dAb3bAd或Adb3bdA,
 * 但插入两个字符以下却无法完成对称性处理。
 * 请求出需要插入的最少字符数。
 * */
public class ZiFuChuanDuiChengChuLi {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			char[] s = sc.nextLine().toCharArray();
			System.out.println(solution(0, s.length-1, s));
		}

	}
	
	static boolean[][] visited = new boolean[100][100];
	static int[][] save = new int[100][100];
	
	//计算从s[i]到s[j]这段字符串如果需要变成对称需要至少插入多少个
	static int solution(int i, int j, char[] s) {
		if (i >= j) return 0;
		if (visited[i][j] == true) {
			return save[i][j];
		}
		visited[i][j] = true;
		if (s[i] == s[j]) {
			save[i][j] = solution(i+1, j-1, s);
		}else {
			int ans1 = solution(i+1, j, s);
			int ans2 = solution(i, j-1, s);
			save[i][j] = Math.min(ans1, ans2) + 1;
		}
		return save[i][j];
	}

}
