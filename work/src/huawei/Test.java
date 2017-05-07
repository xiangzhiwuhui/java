package huawei;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
			
		String str = "32+5-";
		char[] strChar = str.toCharArray();
		System.out.println(Arrays.toString(strChar));
		
		String str1 = "aa";
		char[] ch = str1.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] >= 'a' && ch[i] <= 'z') {
				ch[i] = (char)(ch[i] - 32);
			}
		}
		System.out.println(Arrays.toString(ch));
		System.out.println();
	}

}
