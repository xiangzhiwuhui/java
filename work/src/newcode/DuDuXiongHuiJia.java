package newcode;

import java.util.Scanner;

public class DuDuXiongHuiJia {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		
		int reslut = ans(a, n);
		System.out.println(reslut);

	}

	static int ans(int[] a, int n) {
		int sum = 0;
		for (int i = 1; i < n; i++) {
			sum += Math.abs(a[i] - a[i-1]);
		}
		
		int min = sum;
		for (int i = 2; i < n; i++) {
			int temp = sum;
			int w = Math.abs(a[i]-a[i-1]) + Math.abs(a[i-1]-a[i-2]) - Math.abs(a[i]-a[i-2]);
			temp = temp - w;
			if (min > temp) {
				min = temp;
			}
		}
		
		return min;
	}

}
