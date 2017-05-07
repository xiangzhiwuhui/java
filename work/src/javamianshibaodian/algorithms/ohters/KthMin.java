package javamianshibaodian.algorithms.ohters;


//找出数组中第k个最小的数
public class KthMin {
	public static void main(String[] args) {
//		int[] a ={1,5,2,6,8,0,6};
		int[] a ={1,7,2,3,4,5,6};
		int k = 4;
		System.out.println(getKMin(a, 4));
		/*quickSort(a);
		System.out.println(a[k-1]);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}*/
	}
	//下面为方法2
	public static int getKMin(int[] a, int k){
		if(a == null || a.length < k){
			return Integer.MIN_VALUE;
		}
		return quickSort1(a, 0, a.length - 1, k);
	}
	
	public static int quickSort1(int[] a, int low, int high, int k){
		if(low > high)return Integer.MIN_VALUE;
		
		int i = low, j = high;
		int index = a[i];
		while(i < j){
			while(i < j && a[j] >= index){
				--j;
			}
			if(i < j){
				a[i] = a[j];
				++i;
			}
			
			while(i < j && a[i] < index){
				++i;
			}
			if(i < j){
				a[j]  = a[i];
				--j;
			}
		}
		a[i] = index;
		if(i == k-1)
			return a[i];
		else if(k-1 > i){
			return quickSort1(a, i + 1, high, k);
		}
		else {
			return quickSort1(a, low, i - 1, k);
		}
	}
	
	//下面为方法1
	public static void quickSort(int[] a){
		sort(a, 0, a.length-1);
	}
	
	public static void sort(int[] a, int low, int high){
		
		int i =low, j = high;
		if(low >= high)return;
		
		int index = a[i];
		while(i < j){
			while(i < j && a[j] >= index){
				j--;
			}
			if (i<j) {
				a[i] = a[j];
				i++;
			}
			
			while(i < j && a[i] < index){
				i++;
			}
			if (i < j) {
				a[j] = a[i];
				j--;
			}
		}
		a[i] = index;
		sort(a, low, i-1);
		sort(a, i+1, high);
	}
}
