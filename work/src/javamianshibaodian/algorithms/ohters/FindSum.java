package javamianshibaodian.algorithms.ohters;

import java.util.Arrays;

//求数组中两两相加等于20的组合种数
public class FindSum {

	public static void main(String[] args) {
		int[] a ={1,7,17,2,6,3,14};
		Arrays.sort(a);
		findSum(a, 20);
	}
	
	public static void findSum(int[] a, int n){
		int begin = 0;
		int end = a.length-1;
		while(begin < end){
			if (a[begin] + a[end] < n) {
				begin++;
			}else if(a[begin] + a[end] > n){
				end--;
			}else {
				System.out.println(a[begin] + ":" + a[end]);
				begin++;
				end--;
			}
			
		}
		
	}

}
