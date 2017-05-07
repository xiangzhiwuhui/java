package newcode;

import java.util.Scanner;

/**
 * 思路：把要测试的单词后再重复下这个单词，如：picture ，变成 picturepicture
 * 然后判断其他要测试的单词是不是这个串的子串（长度要先相等）
 * */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int num = 0;
		boolean[] checks = new boolean[n]; // 判断字符串是否被检查过
		String[] twords = new String[n];
		for (int i = 0; i < n; i++) {
			twords[i] = sc.next();
			checks[i] = false;
		}
		
		for (int j = 0; j < n; j++) {
			if (!checks[j]) {
				String tt = twords[j] + twords[j];
				for (int k = j+1; k < n; k++) {
					if (!checks[k]) {
						if (tt.contains(twords[k]) && twords[k].length() == twords[j].length()) {
							checks[k] = true;
						}
					}
				}
				num++;
			}
		}
		System.out.println(num);
		
		
	}
}
