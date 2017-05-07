package acmcoder;

import java.util.HashSet;
import java.util.Scanner;

public class ZhanDui {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			if (Character.isDigit(s.charAt(i))) {
				int k = s.charAt(i) - '0';
				if (k == 0)
					continue;
				int l = Math.max(0, i - k);
				int r = Math.min(n - 1, i + k);
				for (int x = l; x <= r; x++) {
					if (s.charAt(x) == 'X')
						set.add(x);
				}
			}
		}
		System.out.println(set.size());
	}
}
