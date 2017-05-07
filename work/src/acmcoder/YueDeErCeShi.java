package acmcoder;

import java.util.Scanner;

/**
 * 约德尔测试
 * http://exercise.acmcoder.com/online/online_judge_ques?ques_id=1677&konwledgeId=134
 * */
public class YueDeErCeShi {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] ss = sc.nextLine().toCharArray();
		char[] tt = sc.nextLine().toCharArray();
		int n = ss.length;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			if (Character.isLetterOrDigit(ss[i])) {
				sb.append("1");
			}else {
				sb.append("0");
			}
		}
		
		int sum = 0;
		for (int i = 0; i < tt.length; i++) {
			if (sb.charAt(i) == tt[i]) {
				sum++;
			}
		}
		System.out.printf("%.2f%%", (sum*1.0/n)*100);

	}

}
