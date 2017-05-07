package acmcoder;

import java.util.Scanner;
/**
 * 通过考试
 * 
 * http://exercise.acmcoder.com/online/online_judge_ques?ques_id=4396&konwledgeId=41
 * 
 * */
public class TongGuoKaoShi {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		double[] p = new double[n];
		double[][] dp = new double[n+1][n+1];
		for (int i = 0; i < n; i++) {
			p[i] = (sc.nextInt()*1.0)/100;
		}
		dp[0][0] = 1;
		for (int i = 1; i < n+1; i++) {
			dp[i][0] = dp[i-1][0]*(1-p[i-1]);
			for (int j = 1; j < n+1; j++) {
				dp[i][j] = dp[i-1][j-1]*p[i-1] + dp[i-1][j]*(1-p[i-1]);
			}
		}
		double result = 0;
		for (int i = (3*n+4)/5; i < n+1; i++) {
			result += dp[n][i];
		}
		System.out.printf("%.5f", result);
	}
}
