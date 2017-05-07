package javaconcurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.Executors;

class ThreadDemo implements Runnable {
	private int tick = 5;
	public void run() {
		for (int i = 0; i < 5; i++) {
			if (tick > 0) {
				System.out.println("tick:" + tick--);
			}
			
		}
	}
}

public class Main1 {
	public static void main(String[] args) throws InterruptedException {
//		Scanner sc = new Scanner(System.in);
//		ArrayList<String> inputs = new ArrayList<String>();
//		
//		while(sc.hasNextLine()){
//			inputs.add(sc.nextLine());
//		}
		
		ThreadDemo runnable = new ThreadDemo();
		new Thread(runnable).start();
		new Thread(runnable).start();
		new Thread(runnable).start();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*int qps = Integer.parseInt(inputs.get(0));
		String[] rtsStr = inputs.get(1).split(",");
		int[] trs = new int[rtsStr.length];
		for (int i = 0; i < rtsStr.length; i++) {
			trs[i] = Integer.parseInt(rtsStr[i]);
		}
		
		int querySum = Integer.parseInt(inputs.get(2));
		int maxThreadNum = Integer.parseInt(inputs.get(3));
		
		System.out.println("qps:" + qps);
		System.out.println("trs:" + Arrays.toString(trs));
		System.out.println("querySum:" + querySum);
		System.out.println("maxThreadNum:" + maxThreadNum);
		
		System.out.println(needTime(qps, trs, querySum, maxThreadNum));
		*/
		
		
		
		
	}

	private static int needTime(int qps, int[] trs, int querySum,
			int maxThreadNum) {
		int time = 0;
		
		
		
		
		return time;
	}
}
