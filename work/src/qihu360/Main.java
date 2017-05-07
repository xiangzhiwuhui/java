package qihu360;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
//		String a = "11";
//		String b = new String("1") + new String("1");
//		System.out.println(a == b); //false
//		String c = b.intern();
//		System.out.println(a==b); //false
//		System.out.println(a==c); //true
		
//		String b = new String("11") + new String("11");
//		String c = b.intern();
//		String a = "1111";
//		System.out.println(a == b); //true
//		System.out.println(a == c); //true
		
		//下面代码在jdk1.6中会返回两个false;在jdk1.7、jdk1.8中会返回一个true，一个fase;
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1); //jdk1.7、jdk1.8true jdk1.6false
		
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2); //false

	}

}
