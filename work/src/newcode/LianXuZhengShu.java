package newcode;

import java.util.Arrays;
import java.util.Scanner;

// https://www.nowcoder.com/question/next?pid=4236887&qid=78088&tid=7695823
public class LianXuZhengShu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] num = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = sc.nextInt();
		}
		
		Arrays.sort(num);
		int i;
		boolean error = false; 
		int times = 0; // 记录不连续的数字个数
		int k = -1; //记录中间不连续的数字下标
		for (i = 0; i < num.length-1; i++) {
			if (num[i+1]-num[i] > 2) {
				error = true;
				break;
			}else if(num[i+1]-num[i] == 2){
				k = i;
				times++;
			}
		}
		
		if (error == true || times > 1) {
			System.out.println("mistake");
		}else if(times == 1){
			System.out.println(num[k]+1);
		}else {
			if (num[0] == 1) {
				System.out.println(num[n-1]+1);
			}else {
				System.out.println((num[0]-1) + " " + (num[n-1]+1));
			}
		}

	}

}
