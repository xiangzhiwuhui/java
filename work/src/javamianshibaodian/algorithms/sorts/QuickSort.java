package javamianshibaodian.algorithms.sorts;

//快速排序
public class QuickSort {

	public static void main(String[] args) {
		int[] a ={4,6,2,7,3,5,1,6};
		String to = "Hello Word";
		System.out.println(to.indexOf("l", 3));
		quickSort(a);
		for(int i = 0; i< a.length; i++){
			System.out.println(a[i] + " ");
		}

	}
	
	public static void quickSort(int[] a){
		sort(a, 0, a.length-1);
	}
	public static void sort(int[] a, int low, int high){
		if(low >= high)return;
		
		int i=low,j=high;
		int index = a[i];
		
		while(i < j){
			while(i < j && a[j] >= index){
				j--;
			}
			if(i < j){
				a[i++] = a[j];
			}
			
			while(i < j && a[i] < index){
				i++;
			}
			if(i < j){
				a[j--] = a[i];
			}
			
			a[i] = index;
			sort(a, low, i-1);
			sort(a, i+1, high);
			
		}
	}

}
