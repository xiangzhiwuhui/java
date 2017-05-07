package javamianshibaodian.algorithms.ohters;

//一个整型数组里除了一个数字之外，其他数字都出现了两次，找出这个只
//出现1次的数字，要求时间复杂度为O(n),空间复杂度为O(1)。
public class FindNotDouble {
	public static void main(String[] args){
		int[] a = {1,1,2,3,3};
		System.out.println(findNotDouble(a));
	}
	
	//使用异或运算，任何一个数字异或它自己都是0，所以从头到尾依次异或数组中
	//的元素，那些出现两次的数字全部在异或中会被抵消，最终的结果刚好是这个
	//只出现1次的数字。
	static int findNotDouble(int[] a){
		int n = a[0];
		for(int i = 1; i < a.length; i++){
			n = n^a[i];
		}
		return n;
	}
}
