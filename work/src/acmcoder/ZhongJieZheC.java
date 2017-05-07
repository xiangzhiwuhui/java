package acmcoder;

import java.util.Scanner;
/**
 * 终结者C
 * http://exercise.acmcoder.com/online/online_judge_ques?ques_id=4401&konwledgeId=41
 * */
public class ZhongJieZheC {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNext()) {
			int n = sc.nextInt();
			int s[] = new int[n];
			int e[] = new int[n];
			for (int i = 0; i < n; i++) {
				s[i] = sc.nextInt();
				e[i] = s[i] + sc.nextInt();
			}
			
			System.out.println(ans(s, e, n));
			
			
		}

	}

	private static int ans(int[] s, int[] e, int n) {
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				int t = 0;
				for (int k = 0; k < n; k++) {
					if ((s[k] <= s[i] && s[i] <= e[k]) || (s[k] <= s[j] && s[j] <= e[k])) {
						t++;
					}
				}
				if (t > result) {
					result = t;
				}
			}
		}
		
		return result;
	}

}
