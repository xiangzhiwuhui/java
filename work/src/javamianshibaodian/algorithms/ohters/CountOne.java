package javamianshibaodian.algorithms.ohters;

/**
 * 给定一个整数，求二进制中1的个数
 * */
public class CountOne {

	public static void main(String[] args) {
		System.out.println("对应的二进制中1的个数为："+ countOne(1));

	}
	
	public static int countOne(int n){
		int count = 0;
		while(n > 0){
			n = n&(n-1);
			count++;
		}
		
		return count;
	}

}
