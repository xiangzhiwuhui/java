package acmcoder;

import java.util.Scanner;
/**回文串
 * http://exercise.acmcoder.com/online/online_judge_ques?ques_id=3013&konwledgeId=134
 * */
public class HuiWenChuan {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String str = sc.next();
			int len = str.length();
			int subLen = getSubstring(str);
			if (len - subLen <= 1)
				System.out.println("Yes");
			else
				System.out.println("No");
		}

	}

	// 将字符串翻转
	public static String reverse(String str) {
		StringBuilder sb = new StringBuilder(str);
		return sb.reverse().toString();
	}

	// 求两个字符串的最长公共子串
	public static int getSubstring(String str) {
		String str2 = reverse(str);
		int i = 0;
		int j = 0;
		int len = str.length();
		int dp[][] = new int[len + 1][len + 1];
		for (i = 1; i <= len; i++) {
			for (j = 1; j <= len; j++) {
				if (str.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[len][len];
	}

}
