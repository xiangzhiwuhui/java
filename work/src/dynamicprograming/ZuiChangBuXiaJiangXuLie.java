package dynamicprograming;

import java.util.Scanner;

public class ZuiChangBuXiaJiangXuLie {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 输入数字个数
		int[] a = new int[n];
		save = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
			save[i] = 1;
		}
		
		System.out.println(solution(a, n));

	}
	
	static int solution(int[] a, int n) {
		int ans = 0;
		int max = 1;
		for (int i = 0; i < a.length; i++) {
			max = 1;
			for (int j = 0; j < i; j++) {
				if (a[j] <= a[i] && save[j] + 1 > max) {
					max = save[j] + 1;
				}
			}
			save[i] = max;
			ans = Math.max(ans, max);
		}
		
		return ans;
	}

	static int[] save = null;

}
