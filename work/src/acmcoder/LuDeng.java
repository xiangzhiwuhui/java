package acmcoder;

import java.util.Arrays;
import java.util.Scanner;
/**
 * 路灯
 * http://exercise.acmcoder.com/online/online_judge_ques?ques_id=1500&konwledgeId=134
 * 
 * */
public class LuDeng {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int l = sc.nextInt();
		int[] zb = new int[n];
		for (int i = 0; i < n; i++) {
			zb[i] = sc.nextInt();
		}
		Arrays.sort(zb);
		int left = -1;
		int right = -1;
		int max = 0;
		for (int i = 0; i < n-1; i++) {
			int tmp = zb[i+1]-zb[i];
			if ( tmp > max) {
				max = tmp;
				left = i;
				right = i+1;
			}
		}
		if (zb[0] > 0 && zb[0] > max*1.0/2) {
			System.out.printf("%.2f", zb[0]*1.0);
		}else if (zb[n-1] < l && l-zb[n-1] > max*1.0/2) {
			System.out.printf("%.2f", (l-zb[n-1])*1.0);
		}else {
			System.out.printf("%.2f", max*1.0/2);
		}
		
	}
}
