package sorts;

// InsertSort

public class InsertSort {
	public static void main(String[] args){
		int[] a ={1,7,17,2,6,3,14};
		insertSort(a);
		for(int i = 0; i< a.length; i++){
			System.out.println(a[i] + " ");
		}
	}
	
	public static void insertSort(int[] a){
		int i;
		int j;
		int elem;
		for (i = 1; i < a.length; i++) {
			elem = a[i];
			j = i;
			if(a[j-1] > elem){
				while(j >= 1 && a[j-1] > elem){
					a[j] = a[j-1];
					j--;
				}
			}
			a[j] = elem;
		}
	}
}
