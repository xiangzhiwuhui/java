package acmcoder;

import java.util.Arrays;
import java.util.Scanner;

/**马路上的路灯
 * http://exercise.acmcoder.com/online/online_judge_ques?ques_id=1652&konwledgeId=134
 * */
public class MaLuShangDeLuDeng {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int m = sc.nextInt();
		int n = sc.nextInt();
		boolean isRemoved[] = new boolean[m+1]; // 标记路灯是否被移除
		Arrays.fill(isRemoved, false);
		
		for (int i = 0; i < n; i++) {
			int left = sc.nextInt();
			int right = sc.nextInt();
			removeLuDeng(isRemoved, left, right);
		}
		
		int sum = 0; // 记录剩余路灯数
		for (int i = 0; i < isRemoved.length; i++) {
			if (isRemoved[i] == false) {
				sum++;
			}
		}
		
		System.out.println(sum);
		
	}

	private static void removeLuDeng(boolean[] isRemoved, int left, int right) {
		for (int i = left; i <= right; i++) {
			isRemoved[i] = true; 
		}
		
	}
}
