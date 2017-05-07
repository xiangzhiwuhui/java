package wangyi;

import java.util.Scanner;

public class JiaoHuanNanNv {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {//
			String s = in.nextLine();
			int ans = cal(s);
			System.out.println(ans);
		}
	}

	public static int cal(String in) {
		int left = calLeft(in);
		int right = calRight(in);
		return Math.min(left, right);
	}

	public static int calLeft(String in) {
		// case G left
		if (in == null || in.length() <= 1)
			return 0;

		int ans = 0;
		char[] input = in.toCharArray();
		int[] nums = new int[input.length];
		if (input[0] == 'B')
			nums[0] = 1;
		for (int i = 1; i < input.length; i++) {
			nums[i] = nums[i - 1];
			if (input[i] == 'B')
				nums[i] += 1;
		}

		for (int j = input.length - 1; j >= 0; j--) {
			if (input[j] == 'G')
				ans += nums[j];
		}

		return ans;
	}

	public static int calRight(String in) {
		// case G left
		if (in == null || in.length() <= 1)
			return 0;

		int ans = 0;
		char[] input = in.toCharArray();
		int[] nums = new int[input.length];
		if (input[0] == 'G')
			nums[0] = 1;
		for (int i = 1; i < input.length; i++) {
			nums[i] = nums[i - 1];
			if (input[i] == 'G')
				nums[i] += 1;
		}

		for (int j = input.length - 1; j >= 0; j--) {
			if (input[j] == 'B')
				ans += nums[j];
		}
		return ans;
	}

}
