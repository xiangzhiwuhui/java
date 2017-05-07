package sorts;

import java.util.Arrays;

//BinarySearch
 
public class BinarySearch {

	public static void main(String[] args) {
		int[] a = {1, 3, 5, 7, 9, 11, 13, 15};
		Arrays.sort(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println(binarySearch(a, 0, a.length - 1, 11));
		

	}
	
	public static int binarySearch(int[] a, int low, int high, int key) {
		int i = low, j = high;
		if (a == null || i > j || a.length <= 0) {
			return -1;
		}
		
		while(i <= j){
			int mid = (i + j) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                i = mid + 1;
            else if (midVal > key)
                j = mid - 1;
            else
                return mid; // key found
		}
		
		return -1;
	}

}
