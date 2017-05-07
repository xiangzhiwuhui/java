package wangyi;

import java.util.Scanner;

public class ShortestArriveTime {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] tx = new int[n];
		int[] ty = new int[n];
		for(int i = 0;i < n;i ++) {
			tx[i] = sc.nextInt();
		}
		for(int i = 0;i < n;i ++) {
			ty[i] = sc.nextInt();
		}
		int gx = sc.nextInt();
		int gy = sc.nextInt();
		int walktime = sc.nextInt();
		int taxitime = sc.nextInt();
		sc.close();
		
		int[] times = new int[n + 1];
		times[n] = (Math.abs(gx) + Math.abs(gy)) * walktime;
		
		for(int i = 0;i < n;i ++) {
			times[i] = (Math.abs(tx[i]) + Math.abs(ty[i])) * walktime + (Math.abs(tx[i] - gx) + Math.abs(ty[i] - gy)) * taxitime;
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0;i <= n;i ++) {
			if(min > times[i]) {
				min = times[i];
			}
		}
		
		System.out.println(min);
	}

}
