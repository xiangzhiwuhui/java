package qihu360;

import java.util.Scanner;
//题目网址：http://exercise.acmcoder.com/quesexcuse?paperId=213
public class PaoBu {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int r = sc.nextInt();
		double huDu = l*1.0/r;
		
		double x = Math.cos(huDu)*r;
		double y = Math.sin(huDu)*r;
		
		
		System.out.printf("%.3f %.3f", x, -y);
		System.out.println();
		System.out.printf("%.3f %.3f", x, y);

	}

}
