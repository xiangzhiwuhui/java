package sorts;

// SelectSort

public class SelectSort {

	public static void main(String[] args) {
		int[] a ={4, 6,2,7,9,5,1,6};
		selectSort(a);
		for(int i = 0; i< a.length; i++){
			System.out.println(a[i] + " ");
		}

	}
	
	public static void selectSort(int[] a){
		int index, min;
		for(int i = 0; i < a.length; i++){
			min = a[i];
			index = i;
			for(int j = i + 1; j < a.length; j++){
				if(a[j] < min){
					min = a[j];
					index = j;
				}
			}
			if(index != i){
				a[index] = a[i];
				a[i] = min;
			}
		}
	}

}

