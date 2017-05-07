package javamianshibaodian.algorithms.ohters;
//求最大子数组之和，并求最大子数组在原数组中的开始和结束位置
public class MaxSubArraySum {
	private static int begin = -1;//记录子数组的第一位在整个数组中的位置
	private static int end = -1;//记录子数组的最后一位在整个数组中的位置
	public static void main(String[] args) {
		int[] a ={1,-2,4,8,-4,7,-1,-5};
		System.out.println("最大子数组之和为："+ maxSubArraySum(a));
		System.out.println("最大子数组开始位置为："+ begin + ", 结束位置为：" + end);

	}
	
	public static int maxSubArraySum(int[] a){
		int maxSum = Integer.MIN_VALUE;
		int sum;
		for (int i = 0; i < a.length; i++) {
			sum = 0;
			for (int j = i; j < a.length; j++) {
				sum += a[j];
				if(sum > maxSum){
					maxSum = sum;
					begin = i;
					end = j;
				}
			}
		}
		return maxSum;
	}

}
