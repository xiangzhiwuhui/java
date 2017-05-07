package sorts;

import java.util.Arrays;
/**
 * 堆排序
 * 
 * 堆定义：堆是一种特殊的树形数据结构，其每个结点都有一个值，通常提到的堆都是指一棵完全二叉树，
 * 根节点的值小于（或大于）两个子结点的值，同时，根节点的两个子树也分别是一个堆。
 * 
 * 堆排序是一树形选择排序，在排序过程中，将R[1...n]看作一棵完全二叉树的顺序存储结构，利用
 * 完全二叉树中父结点和子结点之间的内在关系来选择最小元素。
 * 
 * 堆一般分为大顶堆和小顶堆两种不同的类型。对于给定n个记录的序列(r(1),r(2), ...,r(n)),
 * 当且仅当满足条件(r(i)>=r(2i))且(r(i)>=r(2i+1)), i=1,2,...,n)时称之为大顶堆，
 * 此时，堆顶元素为最大值。对于给定n个记录的序列(r(1),r(2), ...,r(n)),当且仅当满足条件
 * (r(i)<=r(2i))且(r(i)<=r(2i+1)), i=1,2,...,n)时称之为小顶堆，此时，堆顶元素
 * 为最小值。
 * 
 * 堆排序思想：
 * 堆排序思想是对于给定的n个记录，初始时把这些记录看作一棵顺序存储的二叉树，然后将其调整为一个
 * 大顶堆，然后将堆的最后一个元素和堆顶元素(即二叉树的根结点)进行交换后，堆的最后一个元素即为
 * 最大记录；接着将前(n-1)个元素(即不包括最大记录)重新调整为一个大顶堆，再将堆顶元素与当前堆
 * 的最后一个元素进行交换后得到次大的记录，重复该过程直到调整的堆中只剩一个元素为止，该元素为
 * 最小记录，此时可得到一个有序序列。
 * 
 * 堆排序主要包括两个过程：一是构建堆；二是交换堆顶元素与最后一个元素的位置。
 * 
 * 
 * */
public class HeapSort {
	public static void main(String[] args) {
		int[] a = {5, 4, 9, 8, 7, 6, 0, 1, 3, 2};
		int[] b = {5, 4, 9, 8, 7, 6, 0, 1, 3, 2};
		// 降序
		minHeapSort(a);
		System.out.println(Arrays.toString(a));
		// 升序
		maxHeapSort(b);
		System.out.println(Arrays.toString(b));
	}

	/**
	 * 构建小顶堆来进行排序
	 * */
	public static void minHeapSort(int[] a) {
		int i;
		int len = a.length;
		for (i = len/2-1; i >= 0; i--) { // 首先将数组调整为一个小顶堆
			adjustMinHeap(a, i, len-1);
		}
		
		for (i = len-1; i >= 0; i--) {
			int tmp = a[0];
			a[0] = a[i];
			a[i] = tmp;
			adjustMinHeap(a, 0, i-1);
		}
		
	}

	public static void adjustMinHeap(int[] a, int pos, int len) {
		int temp;
		int child;
		for (temp = a[pos]; 2*pos+1 <= len; pos = child) {
			child = 2*pos + 1;
			if (child < len && a[child] > a[child+1]) { // 找结点a[pos]两个孩子的最小值
				child++;
			}
			if (a[child] < temp) 
				a[pos] = a[child];
			else 
				break;
		}
		
		a[pos] = temp;
		
	}
	
	/**
	 * 构建大顶堆来进行排序
	 * */
	public static void maxHeapSort(int[] a) { // 该函数和minHeapSort一样，代码不变
		int i;
		int len = a.length;
		for (i = len/2-1; i >= 0; i--) { // 首先将数组调整为一个大顶堆
			adjustMaxHeap(a, i, len-1);
		}
		
		for (i = len-1; i >= 0; i--) {
			int tmp = a[0];
			a[0] = a[i];
			a[i] = tmp;
			adjustMaxHeap(a, 0, i-1);
		}
		
	}

	public static void adjustMaxHeap(int[] a, int pos, int len) {
		int temp;
		int child;
		for (temp = a[pos]; 2*pos+1 <= len; pos = child) {
			child = 2*pos + 1;
			if (child < len && a[child] < a[child+1]) { //找结点a[pos]两个孩子的最大值
				child++;
			}
			if (a[child] > temp) 
				a[pos] = a[child];
			else 
				break;
		}
		
		a[pos] = temp;
		
	}
}
