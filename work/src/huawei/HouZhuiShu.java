package huawei;

import java.util.ArrayDeque;
import java.util.Scanner;

public class HouZhuiShu {
	// 输入一个用后缀树表示的字符串，计算该字符串的值。
		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			while (in.hasNextLine()) {
				String s = in.nextLine();
				System.out.println(cal(s));
			}

			// System.out.println(Character.digit('9', 16) );
			// System.out.println(cal("32+5-"));

		}

		public static int cal(String s) {

			ArrayDeque<Integer> myStack = new ArrayDeque<Integer>();
			char[] in = s.toCharArray();

			for (int i = 0; i < in.length; i++) {
				if (Character.isDigit(in[i]) || ('a' <= in[i] && in[i] <= 'f')
						|| ('A' <= in[i] && in[i] <= 'F')) // push
				{
					myStack.push(Character.digit(in[i], 16)); // int
					continue;
				}

				switch (in[i]) {
				case '+': {
					int a = myStack.pop();
					int b = myStack.pop();
					
					int ans = a + b;
					myStack.push(ans);
					break;
				}

				case '-': {
					int a = myStack.pop();
					int b = myStack.pop();
					int ans = b-a;
					myStack.push(ans);
					break;
				}
				case '*': {
					int a = myStack.pop();
					int b = myStack.pop();
					int ans = a * b;
					myStack.push(ans);
					break;
				}
				}
			}

			return myStack.pop(); // top
		}
}
