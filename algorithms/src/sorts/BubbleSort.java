package sorts;

// BubbleSort

public class BubbleSort {
	public static void main(String[] args) {
		int[] a ={4,6,2,7,3,5,1,6};
		bubbleSort(a);
		for(int i = 0; i< a.length; i++){
			System.out.println(a[i] + " ");
		}
	}
	public static void bubbleSort(int[] a){
		for(int i=0; i < a.length-1; i++){
			for(int j = 0; j < a.length-i-1; j++){
				int temp;
				if(a[j]>a[j+1]){
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
	}
	/*public static void bubbleSort(int[] a){
		int i,j;
		int len = a.length;
		int temp;
		for(i = 0; i< len-1; ++i){
			for(j=0; j < len -i -1; j++){
				if(a[j] > a[j+1]){
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
	}*/
}
