package javamianshibaodian.algorithms.sorts;

import java.util.Arrays;
/**
 * 归并排序
 * 
 * 归并排序是利用递归与分治技术将数据序列划分成为越来越小的半子表，再对半子表排序，最后再用递归方法将排好序的半子表合并成为
 * 越来越大的有序序列。归并排序中，“归”代表的是递归的意思，即递归的讲数组折半的分离为单个数组，例如数组：[2,6,1,0],会先
 * 折半，分为[2,6]和[1,0]两个子数组，然后再折半将数组分离，分为[2]、[6]和[1]、[0]。“并”就是将分开的数据按照从小到大
 * 或者从大到小的顺序存放到一个数组中。如上面的[2]、[6]合并到一个数组中是[2,6],[1]、[0]合并到数组中是[0,1]，然后再将
 * [2,6]和[0,1]合并到一个数组中即为[0,1,2,6]。
 * 
 * 归并排序算法的原理如下：对于给定的一组记录（假设共有n个记录），首先将每两个相邻长度为1的子序列进行归并，得到n/2（向上取整）
 * 个长度为2或1的有序子序列，再将其两两归并，反复执行此过程，直到得到一个有序序列。
 * 
 * 所以，归并排序的关键就是两步：第一步，划分半子表；第二步，合并半子表。
 * 
 * */
public class MergeSort {

	public static void main(String[] args) {
		int[] a = {5, 4, 9, 8, 7, 6, 0, 1, 3, 2};
		int[] b = {5, 4, 9, 8, 7, 6, 0, 1, 3, 2};
		// 降序
		mergeSortDesc(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
		
		// 升序
		mergeSortAsc(b, 0, b.length-1);
		System.out.println(Arrays.toString(b));

	}

	/**
	 * 降序排序
	 * */
	public static void mergeSortDesc(int[] a, int p, int r) { // 函数mergeSortDesc与函数mergeSortAsc函数体完全相同
		if (p < r) {
			int q = (p + r) / 2;
			mergeSortDesc(a, p, q);
			mergeSortDesc(a, q+1, r);
			mergeDesc(a, p, q, r);
			
		}
		
	}
	
	private static void mergeDesc(int[] a, int p, int q, int r) {
		int i, j, k, n1, n2;
		n1 = q - p + 1; // q分割的要排序的左边数组元素个数
		n2 = r - q; // q分割的要排序的右边数组元素个数
		int[] L = new int[n1]; // 该数组用来存放左边要排序的数组元素
		int[] R = new int[n2]; // 该数组用来存放右边要排序的数组元素
		
		for (i = 0, k = p; i < n1; i++, k++) // 将a中左边有序元素放入临时数组L中
			L[i] = a[k];
		
		for (i = 0, k = q + 1; i < n2; i++, k++) // 将a中右边有序元素放入临时数组R中
			R[i] = a[k];
		
		for (k = p, i = 0, j = 0; i < n1 && j < n2; k++) { // 开始合并
			if (L[i] > R[j]) { // 函数mergeDesc与mergeAsc只有此处不一样，其余都一样
				a[k] = L[i];
				i++;
			}else {
				a[k] = R[j];
				j++;
			}
		}
		
		if (i < n1) { // 如果数组L长度比R长度长，则将L中剩余元素放入原数组a中
			for (j = i; j < n1; j++, k++)
				a[k] = L[j];
		}
		
		if (j < n2) { // 如果数组R长度比L长度长，则将R中剩余元素放入原数组a中
			for (i = j; i < n2; i++, k++)
				a[k] = R[i];
		}
	}

	/**
	 * 升序排序
	 * */
	public static void mergeSortAsc(int[] a, int p, int r) { // 函数mergeSortDesc与函数mergeSortAsc函数体完全相同
		if (p < r) {
			int q = (p + r) / 2;
			mergeSortAsc(a, p, q);
			mergeSortAsc(a, q+1, r);
			mergeAsc(a, p, q, r);
			
		}
		
	}

	private static void mergeAsc(int[] a, int p, int q, int r) {
		int i, j, k, n1, n2;
		n1 = q - p + 1; // q分割的要排序的左边数组元素个数
		n2 = r - q; // q分割的要排序的右边数组元素个数
		int[] L = new int[n1]; // 该数组用来存放左边要排序的数组元素
		int[] R = new int[n2]; // 该数组用来存放右边要排序的数组元素
		
		for (i = 0, k = p; i < n1; i++, k++) // 将a中左边有序元素放入临时数组L中
			L[i] = a[k];
		
		for (i = 0, k = q + 1; i < n2; i++, k++) // 将a中右边有序元素放入临时数组R中
			R[i] = a[k];
		
		for (k = p, i = 0, j = 0; i < n1 && j < n2; k++) { // 开始合并
			if (L[i] < R[j]) { // 函数mergeDesc与mergeAsc只有此处不一样，其余都一样
				a[k] = L[i];
				i++;
			}else {
				a[k] = R[j];
				j++;
			}
		}
		
		if (i < n1) { // 如果数组L长度比R长度长，则将L中剩余元素放入原数组a中
			for (j = i; j < n1; j++, k++)
				a[k] = L[j];
		}
		
		if (j < n2) { // 如果数组R长度比L长度长，则将R中剩余元素放入原数组a中
			for (i = j; i < n2; i++, k++)
				a[k] = R[i];
		}
		
	}

}
