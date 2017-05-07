package huawei;

import java.util.ArrayList;
import java.util.Scanner;

public class MinWuFuCount {

	//集五福：输入用0、1代表一个人是否有其中一个福。有五福用1表示，没有用0表示。
	//每个人拥有五福数用一个五位0、1字符串表示，求所有人一共最多能够集齐的五福数。
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<String> in = new ArrayList<String>();
		
		while (s.hasNextLine()) {
			in.add(s.nextLine());
		}
		System.out.println(cal(in));
		// s.add("11111");
		// s.add("00111");
		// s.add("10000");
		
	}

	public static int cal(ArrayList<String> in) {
		int[] sum = { 0, 0, 0, 0, 0 };

		for (String s : in) {
			char[] c = s.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (c[i] == '1')
					sum[i]++;
			}
		}

		int min = Integer.MAX_VALUE;
		for (int j = 0; j < 5; j++) {
			if (sum[j] < min)
				min = sum[j];
		}

		return min;
	}

}
