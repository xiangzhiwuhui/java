package acmcoder;

import java.util.Arrays;
import java.util.Scanner;
// http://exercise.acmcoder.com/online/online_judge_ques?ques_id=1656&konwledgeId=134
public class FanZhuanShuZu {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNextInt()) {
			int n = sc.nextInt();
			int[] a = new int[n];
			int[] copy = new int[n];
			for (int i = 0; i < a.length; i++) {
				a[i] = sc.nextInt();
				copy[i] = a[i];
			}
			
			Arrays.sort(a);
			int left = 0, right = n-1;
			while(left < n && a[left] == copy[left]) left++;
			while(right >=0 && a[right] == copy[right]) right--;
			
			boolean flag = true; // 指示是否是可翻转的串
			for (int i = 0; i <= right-left; i++) {
				if (a[left] != copy[right]) {
					flag = false;
					break;
				}
				left++;
				right--;
			}
			
			if (flag) {
				System.out.println("yes");
			}else {
				System.out.println("no");
			}
			
			
		}
		
	}

}
