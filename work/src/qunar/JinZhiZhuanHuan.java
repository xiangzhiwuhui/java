package qunar;

import java.math.BigInteger;
import java.util.Scanner;

public class JinZhiZhuanHuan {
	public static void main(String[] args) {

		 Scanner in = new Scanner(System.in);
		 while(in.hasNextLine())
		 {
		 String s= in.nextLine();
		 cal(s);
		 }
//		cal("ba");
	}

	public static void cal(String in) {
		if (in == null || in.length() == 0)
			return;
		// long ans = 0;
		BigInteger base =new BigInteger("1");
		// StringBuilder s = new StringBuilder();
		BigInteger ans = new BigInteger("0");
		for (int i = in.length() - 1; i >= 0; i--) {
			int c = in.charAt(i) - 'a';

			// ans = ans + c * base;
			BigInteger tmp = base.multiply(new BigInteger(Integer.toString(c)));
			//ans = ans.add(new BigInteger(Integer.toString(c * base)));
			ans = ans.add(tmp);
			//base *= 26;
			base = base.multiply(new BigInteger("26"));
		}
		System.out.println(ans);
	}
}

