package ali;

//文渊写的程序
public class AliTest2 {

	public static void main(String[] args) {
		int[] n = new int[] { 10, 2, 11, 13, 1, 1, 1, 1, 1 };
		System.out.println(solution(n));
	}

	/* 输入数组 */
	public static boolean solution(int[] n) {
		// sums[i]记录 n[i]之后的 元素之和
		int[] sums = new int[n.length];

		sums[sums.length - 1] = n[n.length - 1];
		for (int i = sums.length - 2; i >= 0; i--) {
			sums[i] = n[i] + sums[i + 1];
		}

		/*
		 * 从前到后确定第一个 分组在哪分割; s表示第一个分组的数字总和， 对每个s分别调用函数f()
		 * 判断剩下的数组元素能否分为3个分组每个分组总和为s;
		 */
		int s = 0;
		for (int i = 0; i < n.length - 6; i++) { // -6 因为后面还有3个分组  
														// 而且需要占用3个分割位置
			s += n[i];
			if (s * 4 > sums[0]) // 减枝，总和不够4个分组
				return false;
			if (f(n, sums, i + 2, s, 3) == true) // 对每个s尝试
				return true;
			else
				continue;
		}

		return false;
	}

	/*
	 * n： 输入的原数组
	 * sums: 记录对应位置之后的 数字之和 数组； 即solution()中的sums
	 * start : 表示从数组哪个位置开始分割
	 * num: 每个分组 数字之和为num,
	 * t: 还需要分为几个分组
	 */
	public static boolean f(int[] n, int[] sums, int start, int num, int t) {
		if (t == 1) // 已经是最后一个分组，此时只要判断该剩余数组是否满足 num即可。
			return n[start] == num; //ssl:应该是sums[start] == num;

		// if (num * t < sums[start])
		// return false;
		int s = 0;
		int i = 0;
		// 从start位置开始，累加直到该分组凑够 num。
		for (i = start; i < n.length && s < num; i++) { //ssl:判断条件应该为：i<n.length-2*(t-1) && s<num;
			s += n[i];
		}
		// 该分组不满足构成num
		if (i > n.length || s > num) //ssl:条件应该为：s != num;
			return false;
		// 该分组已经找到和为num的分组，继续判断下一个分组；
		return f(n, sums, i + 1, num, t - 1); // num依然不变，只是t-1;i+1是因为跳过一个分割元素
	}

}
