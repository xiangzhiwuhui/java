package qihu360;

import java.util.Arrays;
import java.util.Scanner;

public class TaskList {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n,m;
		n=scanner.nextInt();
		m=scanner.nextInt();
		int t[]=new int[n];
		int q[]=new int[m];
		for (int i = 0; i < t.length; i++) {
			t[i]=scanner.nextInt();
		}
		for (int i = 0; i < q.length; i++) {
			q[i]=scanner.nextInt();
		}
		scanner.close();
		Arrays.sort(t);
		for (int i = 0; i < q.length; i++) {
			if (q[i] == t[t.length-1]) {
				System.out.println(q[i] + 1);
				continue;
			}else if(q[i] > t[t.length-1]) {
				System.out.println(q[i]);
				continue;
			}
			
			for (int j = 0; j < t.length; j++) {
				if (t[j]==q[i]) {
					q[i]++;
					j = j-1;
				}
			}
			System.out.println(q[i]);
			
		}
			
		
	}


}









































