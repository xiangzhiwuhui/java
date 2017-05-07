package newcode;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class MaiMaoZi {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int result = ans(arr, n);
		System.out.println(result);
	}

	static int ans(int[] arr, int n) {
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			map.put(arr[i], 0);
		}
		
		Iterator<Integer> it = map.keySet().iterator();
		int count = 0;
		while (it.hasNext()) {
			count++;
			if (count < 3) {
				it.next();
			}else {
				break;
			}
		}
		
		if (count == 3) {
			return it.next();
		}else {
			return -1;
		}
		
	}
}


