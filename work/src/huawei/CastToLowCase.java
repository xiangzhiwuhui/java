package huawei;

import java.util.Scanner;

public class CastToLowCase {

	//给定一个字符串将其转换成小写字母，非字母不打印
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
//		String str = "AB2Ca";
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
				sb.append(ch);
			}
		}
		System.out.println(sb.toString().toLowerCase());
		
	}

}
