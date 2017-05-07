package javamianshibaodian.algorithms.ohters;

//把一个数组循环右移k位
public class ArrayShiftK {

	public static void main(String[] args) {
		int[] a ={1,2,3,4,5,6,7,8};
		int k = 14; //数组右移的位数
		shiftK(a, k);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	public static void shiftK(int[] a, int k){
		k = k%a.length;//为了防止k比a.length大，右移k位更右移k%a.length位结果是一样的
		reverse(a, 0, a.length-k-1);
		reverse(a, a.length-k, a.length-1);
		reverse(a, 0, a.length-1);
	}
	
	public static void reverse(int[] a, int begin, int end){
		int temp;
		while(begin < end){
			temp = a[begin];
			a[begin] = a[end];
			a[end] = temp;
			begin++;
			end--;
		}
	}

}
