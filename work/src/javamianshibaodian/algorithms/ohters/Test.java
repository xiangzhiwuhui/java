package javamianshibaodian.algorithms.ohters;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		int[] a = {1, 3, 5, 7, 9, 11, 13, 15};
		char[] b = {'1', 'a', 'A'};
//		BigInteger bi = new BigInteger("7", 10);
//		System.out.println(bi.toString(2));
//		System.out.println(Character.codePointAt(b, 0));
//		System.out.println(Character.codePointAt(b, 1));
//		System.out.println(Character.codePointAt(b, 2));
//		System.out.println();
		
		char[] c = new char[5];
		System.arraycopy(b, 0, c, 0, 3);
		System.out.println(Arrays.toString(c));
		
//		System.out.println(System.lineSeparator().contains("\r\n"));
//		System.out.println(Arrays.toString(a));
//		BigInteger bi = new BigInteger("1234567890987654321123456789098765432112345678909876543211234567890987654321");
//		System.out.println(bi.add(new BigInteger("1234567890987654321"))); //两个大数相加
//		quickSort(a);
//		System.out.println(Arrays.toString(a));
//		System.out.println(binarySearch(a, 0, a.length - 1, 7));
	}
	
	public static int binarySearch(int[] a, int low, int high, int key) {
		if (low > high) {
			return -1;
		}
		
		int i = low, j = high;
		int mid;
		while(i <= j) {
			mid = (i + j) >>> 1; //使用移位运算加快算法
			if (key < a[mid]) {
				j = mid - 1;
			}else if (key > a[mid]) {
				i = mid + 1;
			}else {
				return mid;
			}
			
		}
		
		return -1;
	}
	
	
	public static void quickSort(int[] a) {
		sort(a, 0, a.length - 1);
	}
	
	public static void sort(int[] a, int low, int high) {
		
		if (low >= high) {
			return;
		}
		int i = low, j = high;
		int index = a[i];
		
		while(i < j){
			while(i < j && a[j] >= index) {
				j--;
			}
			if (i < j) {
				a[i++] = a[j];
			}
			
			while(i < j && a[i] < index) {
				i++;
			}
			if(i < j) {
				a[j--] = a[i];
			}
		}
		
		a[i] = index;
		sort(a, low, i - 1);
		sort(a, i + 1, high);
	}

}
